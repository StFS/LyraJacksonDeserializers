package com.activitystream.lyra.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.jodah.lyra.util.Duration;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

@Test
public class JacksonDurationTest {

  private static class DurationTest{
    public Duration duration5nanoseconds;
    public Duration duration5microseconds;
    public Duration duration5milliseconds;
    public Duration duration5seconds;
    public Duration duration5minutes;
    public Duration duration5hours;
    public Duration duration5days;
    public Duration durationInfinite;
  }

  public void isDurationJacksonConfigurable(){
    InputStream in = this.getClass().getResourceAsStream("/duration.json");

    try {
      ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new LyraSerdeModule());

      DurationTest dt = mapper.readValue(in, DurationTest.class);

      assertEquals(dt.duration5nanoseconds, Duration.nanoseconds(5));
      assertEquals(dt.duration5microseconds, Duration.microseconds(5));
      assertEquals(dt.duration5milliseconds, Duration.milliseconds(5));
      assertEquals(dt.duration5seconds, Duration.seconds(5));
      assertEquals(dt.duration5minutes, Duration.minutes(5));
      assertEquals(dt.duration5hours, Duration.hours(5));
      assertEquals(dt.duration5days, Duration.days(5));
      assertEquals(dt.durationInfinite, Duration.inf());
      
    } catch (IOException e) {
      fail("Could not deserialize DurationTest class instance.", e);
    }
  }
}


