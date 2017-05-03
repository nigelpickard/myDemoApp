package com.npickard.Serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import com.npickard.model.House;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

/**
 * Created by npickard on 5/2/2017.
 */
@Component("houseJAXBDeserializer")
public class HouseJAXBDeserializer {

        public HouseJAXBDeserializer() {
        }

        public House deserialize(String text){
            try {
               JAXBContext jaxbContext = JAXBContext.newInstance(House.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                StringReader sr = new StringReader(text);
                House house = (House) jaxbUnmarshaller.unmarshal(sr);
                return house;
            } catch (JAXBException e) {
                e.printStackTrace();
            }
            return null;
        }
    }