import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVConsumerMain {

    private static final String COMMA_DELIMITER = ",";

    public static void main(String[] args) {
        System.out.println("Begin Extraction");
        File file = new File("./src");
        for(String fileNames : file.list()) System.out.println(fileNames);
//        List<List<String>> records = new ArrayList<>();
//        try (BufferedReader br = new BufferedReader(new FileReader("D:\\Projects\\Vocera-Assesment\\.idea\\src\\DataInput.csv"))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(COMMA_DELIMITER);
//                records.add(Arrays.asList(values));
//            }
//            System.out.println(records.size());
//        } catch (FileNotFoundException fileNotFoundException) {
//            fileNotFoundException.printStackTrace();
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }
    }
}