import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlToStringGenerator {

    private static ArrayList<String> sStrings = new ArrayList<>();
    private static ArrayList<String> sGenerateStrings = new ArrayList<>();
    private static ArrayList<String> sGeneratedKeys = new ArrayList<>();

    public static void main(String[] args) throws IOException {


        FileReader reader = new FileReader("strings.xml");

        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            sStrings.add(line);
        }

        bufferedReader.close();

        System.out.println("Keys generated");

        Pattern patternForKeyValue = Pattern.compile("\"([^\"]*)\"");
        Pattern patternForStringValue = Pattern.compile("\\>(.*?)\\<");

        for (String stringValue : sStrings) {
            Matcher matcherForKeyValue = patternForKeyValue.matcher(stringValue);
            Matcher matcherForStringValue =  patternForStringValue.matcher(stringValue);

            while (matcherForKeyValue.find()) {
                String formattedString = matcherForKeyValue.group(1);
                sGeneratedKeys.add(formattedString);

            }

            while (matcherForStringValue.find()){
                String formattedString = matcherForStringValue.group(1);
                sGenerateStrings.add(formattedString);
            }
        }


        FileWriter writer = new FileWriter("output.txt");
        writer.write("\n----------------------String Keys----------------------------------------\n\n");
        for (String str : sGeneratedKeys) {
            writer.write(str);
            writer.write("\n");
        }


        writer.write("\n\n----------------------String Values----------------------------------------\n\n");
        for (String str : sGenerateStrings) {
            writer.write(str);
            writer.write("\n");
        }

        writer.close();





    }


}
