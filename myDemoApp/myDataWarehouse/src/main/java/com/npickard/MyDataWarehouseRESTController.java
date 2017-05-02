package com.npickard;

import com.npickard.model.FlattenedCar;
import com.npickard.model.FlattenedHouse;
import com.npickard.model.FlattenedPerson;
import com.npickard.model.Person;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by npickard on 4/30/2017.
 */
@RestController
public class MyDataWarehouseRESTController {
    private static final Log log = LogFactory.getLog(MyDataWarehouseRESTController.class);
    //private static final MessagePersistenceMode messagePersistenceMode = MessagePersistenceMode.PERSIST;
    private static final MessagePersistenceMode messagePersistenceMode = MessagePersistenceMode.MESSAGE;

    @Autowired
    FlattenedPersonBuilder flattenedPersonBuilder;

    @Autowired
    FlattenedCarBuilder flattenedCarBuilder;

    @Autowired
    FlattenedHouseBuilder flattenedHouseBuilder;

    @Autowired
    DefaultMessageListenerContainer messageListenerContainer;

    @RequestMapping("/")
    public String index() {
        return "This is a message from the MyDataWarehouse REST Controller!";
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String getAllPersons() {
        log.info("about to get all flattened persons");
        List<FlattenedPerson> flattenedPersons = flattenedPersonBuilder.getAllPersons();
        StringBuffer sb = new StringBuffer();
        sb.append("<br>");
        for (FlattenedPerson flattenedPerson : flattenedPersons){
            sb.append(flattenedPerson.toString() + "<br>");
        }
        return ("DataWarehouse Service: Flattened Persons persisted in database are " + sb.toString());
    }

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public String getAllCars() {
        log.info("about to get all flattened cars");
        List<FlattenedCar> flattenedCars = flattenedCarBuilder.getAllCars();
        StringBuffer sb = new StringBuffer();
        sb.append("<br>");
        for (FlattenedCar flattenedCar : flattenedCars){
            sb.append(flattenedCar.toString() + "<br>");
        }
        return ("DataWarehouse Service: Flattened Cars persisted in database are " + sb.toString());
    }

    @RequestMapping(value = "/houses", method = RequestMethod.GET)
    public String getAllHouses() {
        log.info("about to get all flattened houses");
        List<FlattenedHouse> flattenedHouses = flattenedHouseBuilder.getAllHouses();
        StringBuffer sb = new StringBuffer();
        sb.append("<br>");
        for (FlattenedHouse flattenedHouse : flattenedHouses){
            sb.append(flattenedHouse.toString() + "<br>");
        }
        return ("DataWarehouse Service: Flattened Houses persisted in database are " + sb.toString());
    }

    /**
     * see bug
     * https://jira.spring.io/browse/SPR-14604
     * @param selector
     * @return
     */

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public String createMessageSelectorByRequestParam(@RequestParam("selector") String selector) {
        String val = "Manufacturer = '" + selector + "'";
        messageListenerContainer.stop();
        messageListenerContainer.setMessageSelector(val);
        messageListenerContainer.start();
        return "Message selector is Manufacturer = '" + selector + "'";
    }

}
