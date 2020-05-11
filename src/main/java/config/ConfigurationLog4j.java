package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:log4j2.properties"})
public interface ConfigurationLog4j extends Config {

    @Key("language")
    String language();
}
