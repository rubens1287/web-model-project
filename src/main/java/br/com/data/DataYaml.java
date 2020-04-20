package br.com.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class DataYaml {

    private static File getYamlDataFile(String fileName){
        return new File("./src/test/resources/data/"+System.getProperty("env")+"/"+fileName+".yml");
    }

    public static LinkedHashMap<String,String> getMapYamlValues(String fileName, String titulo){

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        Map<String , Object> maps;
        LinkedHashMap<String,String> values = null;
        try {
            maps = (Map<String, Object>)  mapper.readValue(getYamlDataFile(fileName), Map.class);
            values = (LinkedHashMap<String, String>) maps.get(titulo);
        } catch (IOException e) {
            Assert.fail("Erro ao tentar ler o arquivo de massa "+fileName+".yaml - stackTrace: " + e);
        }
        return values;
    }

}
