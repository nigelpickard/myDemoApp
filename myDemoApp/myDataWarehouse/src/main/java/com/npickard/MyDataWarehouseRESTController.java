package com.npickard;

import com.npickard.model.FlattenedPerson;
import com.npickard.model.Person;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/")
    public String index() {
        return "This is a message from the MyDataWarehouse REST Controller!";
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String getAllPerson() {
        log.info("about to get all flattened persons");
        List<FlattenedPerson> flattenedPersons = flattenedPersonBuilder.getAllPersons();
        StringBuffer sb = new StringBuffer();
        sb.append("<br>");
        for (FlattenedPerson flattenedPerson : flattenedPersons){
            sb.append(flattenedPerson.toString() + "<br>");
        }
        return ("DataWarehouse Service: Flattened Persons persisted in database are " + sb.toString());
    }

}
