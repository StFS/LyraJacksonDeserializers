package com.activitystream.lyra.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.jodah.lyra.retry.RetryPolicy;
import net.jodah.lyra.util.Duration;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

@Test
public class JacksonRetryPolicyTest {

  public void isRetryPolicyJacksonConfigurable(){
    InputStream in = this.getClass().getResourceAsStream("/retry-policy.json");

    try {
      ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(new LyraSerdeModule());

      RetryPolicy rp = mapper.readValue(in, RetryPolicy.class);

      System.out.println(rp);

    } catch (IOException e) {
      fail("Could not deserialize DurationTest class instance.", e);
    }

  }
}
