package data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.util.*;


@Log4j2
public class DataYaml {

    private static File getYamlDataFile(String fileName){
        return new File("./src/test/resources/data/"+System.getProperty("env")+"/"+fileName+".yml");
    }

    @SneakyThrows
    public static LinkedHashMap<String,String> getMapYamlValues(String fileName, String titulo){

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        Map<String , Object> maps;
        try {
            maps = (LinkedHashMap<String, Object>) mapper.readValue(getYamlDataFile(fileName), Map.class);
            return  (LinkedHashMap<String, String>) maps.get(titulo);
        } catch (IOException e) {
            log.error("Erro ao tentar ler o arquivo de massa "+fileName+".yaml - stackTrace: " + e);
            throw new Exception(e);
        }
    }
}
