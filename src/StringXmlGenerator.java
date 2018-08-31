import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StringXmlGenerator {

    private static ArrayList<String> sInputStrings = new ArrayList<>();
    private static ArrayList<String> sGeneratedStrings = new ArrayList<>();

    public static void main(String[] args) throws IOException {


        FileReader reader = new FileReader("input.txt");

        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;

        StringBuilder readingStrings = new StringBuilder("Reading strings.");
        System.out.println(readingStrings);
        while((line = bufferedReader.readLine())!=null){
            System.out.println(readingStrings.append("."));
            sInputStrings.add(line);
        }

        System.out.println("Reading Completed...");

        bufferedReader.close();


        StringBuilder generatingStrings = new StringBuilder("Generating strings.");
        System.out.println(generatingStrings);

        for(String inputString : sInputStrings){
            generatingStrings.append(".");
            String temp1 = inputString.replaceAll("[-+.^:,!?*#$)(%@}{'/\"]", "").trim().toLowerCase();
            String stringWOSpecialChar = temp1.replaceAll(" ", "_");


            String generatedString = String.format("<string name=\"%s\">%s</string>", stringWOSpecialChar, inputString);
            sGeneratedStrings.add(generatedString);

        }
        System.out.println("Generation of strings completed");

        StringBuilder writingStrings = new StringBuilder("Writing strings.");
        System.out.println(writingStrings);

        FileWriter writer = new FileWriter("output.txt");
        for(String str: sGeneratedStrings) {
            writer.write(str);
            writer.write("\n");
        }
        writer.close();

        System.out.println("Writing of Strings completed");









    }


}
