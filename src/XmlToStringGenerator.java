import com.google.gson.annotations.SerializedName;
import models.Model_LokaliseKey;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlToStringGenerator {

    private static ArrayList<String> sStringKeys = new ArrayList<>();
    private static ArrayList<String> sGenerateStrings = new ArrayList<>();
    private static ArrayList<String> sEnglishStrings = new ArrayList<>();
    private static ArrayList<String> sGermanStrings = new ArrayList<>();
    private static ArrayList<String> sPortugueseStrings = new ArrayList<>();
    private static ArrayList<String> sSpanishStrings = new ArrayList<>();
    private static ArrayList<String> sFrenchStrings = new ArrayList<>();
    private static ArrayList<Model_LokaliseKey> sModelLokaliseKeys = new ArrayList<>();

    public static void main(String[] args) throws IOException {


        FileReader reader = new FileReader("strings.xml");

        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            sStringKeys.add(line);
        }

        bufferedReader.close();

        System.out.println("Keys generated");

        Pattern patternForKeyValue = Pattern.compile("\"([^\"]*)\"");
        Pattern patternForStringValue = Pattern.compile("\\>(.*?)\\<");

        for (String stringValue : sStringKeys) {
            Matcher matcherForKeyValue = patternForKeyValue.matcher(stringValue);
            Matcher matcherForStringValue = patternForStringValue.matcher(stringValue);

            while (matcherForKeyValue.find()) {
                String formattedString = matcherForKeyValue.group(1);
                sEnglishStrings.add(formattedString);

            }

            while (matcherForStringValue.find()) {
                String formattedString = matcherForStringValue.group(1);
                sGenerateStrings.add(formattedString);
            }
        }


        FileWriter writer = new FileWriter("output.txt");
        writer.write("\n----------------------String Keys----------------------------------------\n\n");
        for (String str : sEnglishStrings) {
            writer.write(str);
            writer.write("\n");
        }


        writer.write("\n\n----------------------String Values----------------------------------------\n\n");
        for (String str : sGenerateStrings) {
            writer.write(str);
            writer.write("\n");
        }

        writer.close();

        ArrayList<String> platforms = new ArrayList<>();
        platforms.add("android");
        platforms.add("ios");
        platforms.add("web");

        if (sEnglishStrings.size() == sGenerateStrings.size()) {
            for (int i = 0; i < sEnglishStrings.size(); i++) {
                Model_LokaliseKey modelLokaliseKey = new Model_LokaliseKey();
                modelLokaliseKey.setKeyName(sEnglishStrings.get(i));
                ArrayList<Model_LokaliseKey.Translation> translations = new ArrayList<>();

                Model_LokaliseKey.Translation translation = new Model_LokaliseKey.Translation();
                translation.setLanguageIso("en");
                translation.setTranslation(sGenerateStrings.get(i));
                translations.add(translation);




                modelLokaliseKey.setTranslations(translations);
                modelLokaliseKey.setPlatforms(platforms);
                sModelLokaliseKeys.add(modelLokaliseKey);
            }
        } else {
            System.out.println("Keys and strings array size are not equal");
        }

//
//        FileWriter jsonOutput = new FileWriter("json_output.txt");
//        jsonOutput.write(new Gson().toJson(new Keys(sModelLokaliseKeys)));
//        writer.close();

//        OkHttpClient client = new OkHttpClient();
//
//        MediaType mediaType = MediaType.parse("application/json");
//        String jsonBody = new Gson().toJson(new Keys(sModelLokaliseKeys));
//        RequestBody body = RequestBody.create(mediaType, jsonBody);
//        Request request = new Request.Builder()
//                .url("https://api.lokalise.co/api2/projects/936839755ceb66746199a0.08392357/keys")
//                .post(body)
//                .addHeader("content-type", "application/json")
//                .addHeader("x-api-token", "dbad033d1a376f4a51fad88695758c104e22274d")
//                .build();
//
//        Response response = client.newCall(request).execute();

    }

    static class Keys {
        @SerializedName("keys")
        private ArrayList<Model_LokaliseKey> mModelLokaliseKeys;

        Keys(ArrayList<Model_LokaliseKey> modelLokaliseKeys) {
            mModelLokaliseKeys = modelLokaliseKeys;
        }

    }


}
