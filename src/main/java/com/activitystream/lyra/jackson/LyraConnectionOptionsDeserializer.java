package com.activitystream.lyra.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import net.jodah.lyra.ConnectionOptions;

import java.io.IOException;

public class LyraConnectionOptionsDeserializer extends StdDeserializer<ConnectionOptions> {

  public LyraConnectionOptionsDeserializer() {
    super(ConnectionOptions.class);
  }

  @Override
  public ConnectionOptions deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    return null;
  }
}
