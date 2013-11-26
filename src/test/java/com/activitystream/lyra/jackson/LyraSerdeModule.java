package com.activitystream.lyra.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import net.jodah.lyra.config.Config;
import net.jodah.lyra.util.Duration;

public class LyraSerdeModule extends SimpleModule {
    public LyraSerdeModule() {
        addDeserializer(Duration.class, new LyraDurationDeserializer());
        addDeserializer(Config.class, new LyraConfigDeserializer());
    }
}
