package com.activitystream.lyra.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import net.jodah.lyra.retry.RetryPolicy;
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

    if (node.has("retryInterval")) {
      rp.withRetryInterval(Duration.of(node.get("retryInterval").asText()));
    }
    if (node.has("maxDuration")) {
      rp.withMaxDuration(Duration.of(node.get("maxDuration").asText()));
    }
    if (node.has("maxRetries")) {
      rp.withMaxRetries(node.get("maxRetries").asInt());
    }

    if (node.has("backoff")) {
      JsonNode backoffNode = node.get("backoff");
      Duration retryInterval = Duration.of(backoffNode.get("retryInterval").asText());
      Duration maxRetryInterval = Duration.of(backoffNode.get("maxRetryInterval").asText());
      if (backoffNode.has("retryIntervalMultiplier")) {
        rp.withBackoff(retryInterval, maxRetryInterval, backoffNode.get("retryIntervalMultiplier").asInt());
      } else {
        rp.withBackoff(retryInterval, maxRetryInterval);
      }
    }

    return rp;
  }
}
