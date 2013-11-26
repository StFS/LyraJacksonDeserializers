package com.activitystream.lyra.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import net.jodah.lyra.retry.RetryPolicy;

import java.io.IOException;

public class LyraRetryPolicyDeserializer extends StdDeserializer<RetryPolicy> {

    public LyraRetryPolicyDeserializer(Class<?> vc) {
        super(RetryPolicy.class);
    }

    @Override
    public RetryPolicy deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return null;
    }
}
