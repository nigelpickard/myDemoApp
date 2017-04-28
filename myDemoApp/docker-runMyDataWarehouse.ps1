#############################################
# This is for local development on Windows
# RUN USING THE FOLLOWING COMMAND
# powershell -executionpolicy remotesigned -File %MYDEMO_PATH%\docker-runMyDataWarehouse.ps1 [container name part] [this mapped port number] [database name] [db mapped port] [stop]
#
# e.g.
# powershell -executionpolicy remotesigned -File %MYDEMO_PATH%\docker-runMyDataWarehouse.ps1 myDemo 8080 myDemoDb 3306 stop
#
#
#############################################

$demoName = $args[0]
$demoMappedPort = $args[1]
$dbName = $args[2]
$dbMappedPort = $args[3]
$stop = $args[4]
Write-Host 'MyDemo arguments are demoName: '$demoName' mapped demo port: '$demoMappedPort' database name: '$dbName' mapped database port: '$dbMappedPort

#--------------------------------------------------------------------------------------
# APP
#
$myDemoName = $demoName + '-' + 'DataWarehouse' + '-' + $demoMappedPort + '-Db-' + $dbMappedPort
$myDemoHostName = 'local.' + $demoName + '.com'

# Obtain the ip address
$ip=get-WmiObject Win32_NetworkAdapterConfiguration|Where {$_.Ipaddress.length -gt 1}

#first stop and remove any existing SQL containers
Write-Host 'Stopping and removing any App container with name of '$myDemoName
docker stop $myDemoName
docker rm $myDemoName

#we now have the mysql running, now run the demo app
if("stop" -ne $stop){
    #$cmd = 'docker run --name=' + $myDemoName + ' -e MYDEMO_NAME=' + $demoName + ' -e MYDEMO_HOSTNAME=' + $myDemoHostName + ' -e MYDEMO_DB_PORT=' + $dbMappedPort + ' -e MYDEMO_DB_NAME=' + $dbName + ' --add-host ' + $myDemoHostName + ':' + $ip.ipaddress[0] + ' -p ' +  $demoMappedPort + ':8080 -d -v $Env:MYDEMO_PATH\myDataWarehouse\target\myDataWarehouse-0.0.1-SNAPSHOT.jar:/docker/myDataWarehouse-0.0.1-SNAPSHOT.jar java:8u40 java -jar /docker/myDataWarehouse-0.0.1-SNAPSHOT.jar'
    $cmd = 'docker run --name=' + $myDemoName + ' -e MYDEMO_NAME=' + $demoName + ' -e MYDEMO_HOSTNAME=' + $myDemoHostName + ' -e MYDEMO_DB_PORT=' + $dbMappedPort + ' -e MYDEMO_DB_NAME=' + $dbName + ' --add-host ' + $myDemoHostName + ':' + '10.10.10.1' + ' -p ' +  $demoMappedPort + ':8080 -d -v $Env:MYDEMO_PATH\myDataWarehouse\target\myDataWarehouse-0.0.1-SNAPSHOT.jar:/docker/myDataWarehouse-0.0.1-SNAPSHOT.jar java:8u40 java -jar /docker/myDataWarehouse-0.0.1-SNAPSHOT.jar'
    Write-Host $cmd
    Invoke-Expression $cmd
}