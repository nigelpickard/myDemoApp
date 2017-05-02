package com.npickard.Serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.npickard.model.House;

import java.io.IOException;

/**
 * Created by npickard on 5/2/2017.
 */
public class HouseJSONSerializer extends StdSerializer<House> {

    public HouseJSONSerializer() {
        this(null);
    }

    public HouseJSONSerializer(Class<House> t) {
        super(t);
    }

    @Override
    public void serialize(
            House value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeNumberField("streetNumber", value.getStreetNumber());
        jgen.writeStringField("streetName", value.getStreetName());
        //jgen.writeStringField("cityName", value.getCityName());
        jgen.writeEndObject();
    }
}