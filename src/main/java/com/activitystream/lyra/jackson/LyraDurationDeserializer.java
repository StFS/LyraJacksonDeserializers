package com.activitystream.lyra.jackson;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import net.jodah.lyra.util.Duration;

import java.io.IOException;

public class LyraDurationDeserializer extends StdDeserializer<Duration> {

  public LyraDurationDeserializer() {
    super(Duration.class);
  }

  @Override
  public Duration deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    if (jp.getCurrentToken() == JsonToken.VALUE_STRING) {
      return Duration.of(jp.getValueAsString());
    } else {
      throw new JsonParseException("Expected a value string.", jp.getCurrentLocation());
    }
  }
}
