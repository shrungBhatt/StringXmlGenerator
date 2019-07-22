import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class StringXmlGeneratorModified {

    private static ArrayList<String> sKeys = new ArrayList<>();
    private static ArrayList<String> sTargetValues = new ArrayList<>();
    private static ArrayList<String> sGenerateStrings = new ArrayList<>();

    public static void main(String[] args) throws IOException {


        FileReader reader = new FileReader("key.txt");

        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            sKeys.add(line);
        }


        System.out.println("Keys generated");

        bufferedReader.close();


        reader = new FileReader("spanish_strings.txt");
        bufferedReader = new BufferedReader(reader);

        while ((line = bufferedReader.readLine()) != null) {
            sTargetValues.add(line);
        }


        String generateString;

        for(int i = 0;i<sKeys.size();i++){

            generateString = String.format("<string name=\"%s\">%s</string>", sKeys.get(i), sTargetValues.get(i));
            sGenerateStrings.add(generateString);
        }


        FileWriter writer = new FileWriter("spanish_strings.xml");
        for(String str: sGenerateStrings) {
            writer.write(str);
            writer.write("\n");
        }
        writer.close();


    }


}
