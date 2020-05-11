package data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import localization.MessageParser;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.util.*;


@Log4j2
public class DataYaml {

    private static MessageParser parser = new MessageParser();

    private static File getYamlDataFile(String fileName){
        log.info(parser.parse("data.yaml.get.file", new Object[]{fileName,System.getProperty("env")}));
        return new File("./src/test/resources/data/"+System.getProperty("env")+"/"+fileName+".yml");
    }

    @SneakyThrows
    public static LinkedHashMap<String,String> getMapYamlValues(String fileName, String titulo){

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        Map<String , Object> maps;
        try {
            maps = (LinkedHashMap<String, Object>) mapper.readValue(getYamlDataFile(fileName), Map.class);
            log.info(parser.parse("data.yaml.return", new Object[]{fileName,titulo}));
            return  (LinkedHashMap<String, String>) maps.get(titulo);
        } catch (IOException e) {
            log.error(parser.parse("data.yaml.error", new Object[]{fileName,e}));
            throw new Exception(e);
        }
    }
}
