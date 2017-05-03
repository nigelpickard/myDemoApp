package com.npickard.Serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.npickard.model.House;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by npickard on 5/2/2017.
 */
@Component("houseJAXBSerializer")
public class HouseJAXBSerializer{

    public HouseJAXBSerializer() {
    }

    public String serialize(House house){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(House.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(house, sw);
            return sw.toString();

        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

}