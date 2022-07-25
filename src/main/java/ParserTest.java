import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ParserTest {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
//            JsonNode data = objectMapper.readTree(new File("map.json"));
            TileMapConfig map = objectMapper.readValue(new File("map.json"), TileMapConfig.class);
            map.printInfo();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    static public class TileMapConfig{
        public int tileSize;
        public int width;
        public int height;
        public int scale;
        public String map[][];
        public String spriteSheetPath;


        public TileMapConfig(){}

        public void printInfo(){
            System.out.printf("spriteSheetPath: %s\n", spriteSheetPath);
            System.out.printf("tileSize: %d\n", tileSize);
            System.out.printf("width: %d\n", width);
            System.out.printf("height: %d\n", height);
            System.out.printf("scale: %d\n", scale);
            System.out.println("Map: \n");
            for (String[] y : map){
                for (String x : y){
                    System.out.print(x + " ");
                }
                System.out.println();
            }
        }

    }
}
