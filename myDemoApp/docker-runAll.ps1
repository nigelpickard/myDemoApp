#############################################
# This is for local development on Windows
# RUN USING THE FOLLOWING COMMAND
# powershell -executionpolicy remotesigned -File %MYDEMO_PATH%\docker-runAll.ps1 [container name part] [database name] [stop]
#
# e.g.
# powershell -executionpolicy remotesigned -File %MYDEMO_PATH%\docker-runAll.ps1 myDemo myDemoDb stop
#
#
#############################################

$demoName = $args[0]
$dbName = $args[1]
$stop = $args[2]
Write-Host 'Running and create all services -arguments are demoName: '$demoName'  database name: '$dbName
Write-Host 'MYDEMO_PATH='$Env:MYDEMO_PATH


##########################################################################################
#
# ACTIVE MQ
#
##########################################################################################
#get active mq up and running
$cmd = $Env:MYDEMO_PATH + '\docker-runActiveMQ.ps1 ' + $demoName + ' ' + $stop
Write-Host $cmd
Invoke-Expression $cmd



##########################################################################################
#
# APP AND DBS
#
##########################################################################################
$dbPortNumbersArray = (3307,3308)
$demoPortNumbersArray = (8081, 8082)

for($i = 0; $i -le $dbPortNumbersArray.count -1; $i++) {
    $cmd = $Env:MYDEMO_PATH + '\docker-runMySQL.ps1 ' + $demoName + ' ' + $dbName + ' ' + $dbPortNumbersArray[$i]  + ' ' + $stop
    Write-Host $cmd
    Invoke-Expression $cmd

    if("stop" -ne $stop){
        #make sure container is started
        $cmd = 'Start-Sleep -s 10'
        Write-Host $cmd
        Invoke-Expression $cmd
    }

    $cmd = $Env:MYDEMO_PATH + '\docker-runMyDemo.ps1 ' + $demoName + ' ' + $demoPortNumbersArray[$i] + ' ' + $dbName + ' ' + $dbPortNumbersArray[$i]  + ' ' + $stop
    Write-Host $cmd
    Invoke-Expression $cmd
}


##########################################################################################
#
# DATAWAREHOUSE AND DBS
#
##########################################################################################
$dbPortNumbersArray = (3306)
$demoPortNumbersArray = (8080)

for($i = 0; $i -le $dbPortNumbersArray.count -1; $i++) {
    $cmd = $Env:MYDEMO_PATH + '\docker-runMySQL.ps1 ' + $demoName + ' ' + $dbName + ' ' + $dbPortNumbersArray[$i]  + ' ' + $stop
    Write-Host $cmd
    Invoke-Expression $cmd

    if("stop" -ne $stop){
        #make sure container is started
        $cmd = 'Start-Sleep -s 10'
        Write-Host $cmd
        Invoke-Expression $cmd
    }

    $cmd = $Env:MYDEMO_PATH + '\docker-runMyDataWarehouse.ps1 ' + $demoName + ' ' + $demoPortNumbersArray[$i] + ' ' + $dbName + ' ' + $dbPortNumbersArray[$i]  + ' ' + $stop
    Write-Host $cmd
    Invoke-Expression $cmd
}

