import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Service {

    public static HashMap<String,Integer> determinationValues = new HashMap<>();
    public static HashMap<String,Integer> determinationValues2 = new HashMap<>();
    public static int length;
    public static int length2;

    public static ArrayList<ObservationTrain> parseTrain(String path){
        ArrayList<ObservationTrain> observations = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))){
            String line;
            while (null != (line = bufferedReader.readLine())){
                String[] strings = line.split(",");
                length = strings.length - 1;
                String[] values = new String[length];
                for (int i = 0; i < values.length; i++){
                    values[i] = strings[i].trim();
                }
                determinationValues.put(strings[length], null );
                observations.add(new ObservationTrain(strings[length], values ));
            }
        } catch (Exception exception) { System.out.println(exception.getMessage()); }

        int identifier = 0;
        for (Map.Entry<String,Integer> entry : determinationValues.entrySet())
            entry.setValue(identifier++);

        return observations;
    }

    public static ArrayList<ObservationTest> parseTest(String path){
        ArrayList<ObservationTest> observations = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))){
            String line;
            while (null != (line = bufferedReader.readLine())){
                String[] strings = line.split(",");
                length = strings.length;
                String[] values = new String[length];
                for (int i = 0; i < values.length; i++){
                    values[i] = strings[i].trim();
                }
                observations.add(new ObservationTest(strings));
            }
        } catch (Exception exception) { System.out.println(exception.getMessage()); }

        return observations;
    }


}
