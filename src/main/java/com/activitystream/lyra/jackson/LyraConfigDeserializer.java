package com.activitystream.lyra.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import net.jodah.lyra.config.Config;

import java.io.IOException;

public class LyraConfigDeserializer extends StdDeserializer<Config> {

  public LyraConfigDeserializer() {
    super(Config.class);
  }

  @Override
  public Config deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    return null;
  }
}
