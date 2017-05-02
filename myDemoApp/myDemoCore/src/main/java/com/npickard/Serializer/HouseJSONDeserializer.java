package com.npickard.Serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import com.npickard.model.House;

import java.io.IOException;

/**
 * Created by npickard on 5/2/2017.
 */
public class HouseJSONDeserializer  extends StdDeserializer<House> {

        public HouseJSONDeserializer() {
            this(null);
        }

        public HouseJSONDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public House deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {
            JsonNode node = jp.getCodec().readTree(jp);
            int streetNumber = (Integer) ((IntNode) node.get("streetNumber")).numberValue();
            String streetName = node.get("streetName").asText();
            //String cityName = node.get("cityName").asText();
            return new House(streetNumber, streetName);
        }
    }