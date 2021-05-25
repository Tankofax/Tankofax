package at.htlkaindorf.tankofax.beans;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeDeserializer extends StdDeserializer<LocalTime> {
    private static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("HH:mm");

    protected LocalTimeDeserializer() {
        super(LocalTime.class);
    }

    @Override
    public LocalTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return LocalTime.parse(ctxt.readValue(jp, String.class), DTF);
    }
}