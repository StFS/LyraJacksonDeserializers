package com.activitystream.lyra.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import net.jodah.lyra.config.RetryPolicy;
import net.jodah.lyra.util.Duration;

import java.io.IOException;

public class LyraRetryPolicyDeserializer extends StdDeserializer<RetryPolicy> {

  public LyraRetryPolicyDeserializer() {
    super(RetryPolicy.class);
  }

  @Override
  public RetryPolicy deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    ObjectCodec oc = jp.getCodec();
    JsonNode node = oc.readTree(jp);

    RetryPolicy rp = new RetryPolicy();

    if (node.has("interval")) {
      rp.withInterval(Duration.of(node.get("interval").asText()));
    }
    if (node.has("maxDuration")) {
      rp.withMaxDuration(Duration.of(node.get("maxDuration").asText()));
    }
    if (node.has("maxAttempts")) {
      rp.withMaxAttempts(node.get("maxAttempts").asInt());
    }

    if (node.has("backoff")) {
      JsonNode backoffNode = node.get("backoff");
      Duration interval = Duration.of(backoffNode.get("interval").asText());
      Duration maxInterval = Duration.of(backoffNode.get("maxInterval").asText());
      if (backoffNode.has("intervalMultiplier")) {
        rp.withBackoff(interval, maxInterval, backoffNode.get("intervalMultiplier").asInt());
      } else {
        rp.withBackoff(interval, maxInterval);
      }
    }

    return rp;
  }
}
