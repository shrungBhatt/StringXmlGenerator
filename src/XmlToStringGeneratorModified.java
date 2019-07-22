import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;
import models.Model_LokaliseKey;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlToStringGeneratorModified {

    private static ArrayList<String> sStringKeys = new ArrayList<>();
    private static ArrayList<String> sUS = new ArrayList<>();
    private static ArrayList<String> sUK = new ArrayList<>();
    private static ArrayList<String> sAU = new ArrayList<>();
    private static ArrayList<String> sDE = new ArrayList<>();
    private static ArrayList<String> sBR = new ArrayList<>();
    private static ArrayList<String> sPT = new ArrayList<>();
    private static ArrayList<String> sMX = new ArrayList<>();
    private static ArrayList<String> sES = new ArrayList<>();
    private static ArrayList<String> sAR = new ArrayList<>();
    private static ArrayList<String> sFR = new ArrayList<>();
    private static ArrayList<String> sCA = new ArrayList<>();
    private static ArrayList<Model_LokaliseKey> sModelLokaliseKeys = new ArrayList<>();

    public static void main(String[] args) throws IOException {


        sStringKeys = getStrings("key.txt");
        sUS = getStrings("string_values/us.txt");
        sUK = getStrings("string_values/uk.txt");
        sAU = getStrings("string_values/au.txt");
        sDE = getStrings("string_values/de.txt");
        sBR = getStrings("string_values/br.txt");
        sPT = getStrings("string_values/pt.txt");
        sMX = getStrings("string_values/mx.txt");
        sES = getStrings("string_values/es.txt");
        sAR = getStrings("string_values/ar.txt");
        sFR = getStrings("string_values/fr.txt");
        sCA = getStrings("string_values/ca.txt");


        ArrayList<String> platforms = new ArrayList<>();
//        platforms.add("android");
//        platforms.add("ios");
//        platforms.add("web");
        platforms.add("other");

        ArrayList<String> tags = new ArrayList<>();
        tags.add("Backend");

        if (sStringKeys.size() == sUS.size()) {
            for (int i = 0; i < sStringKeys.size(); i++) {
                Model_LokaliseKey modelLokaliseKey = new Model_LokaliseKey();
                modelLokaliseKey.setKeyName(sStringKeys.get(i));
                ArrayList<Model_LokaliseKey.Translation> translations = new ArrayList<>();


                translations.add(getTranslation(Locale.ENGLISH_UNITED_STATES, sUS.get(i)));
                translations.add(getTranslation(Locale.ENGLISH_UNITED_KINGDOM, sUK.get(i)));
                translations.add(getTranslation(Locale.ENGLISH_AUSTRALIA, sAU.get(i)));
                translations.add(getTranslation(Locale.DEUTCH_DEUTSCHLAND, sDE.get(i)));
                translations.add(getTranslation(Locale.PORTUGUES_BRASIL, sBR.get(i)));
                translations.add(getTranslation(Locale.PORTUGUES_PORTUGAL, sPT.get(i)));
                translations.add(getTranslation(Locale.ESPANOL_ESPANA, sES.get(i)));
                translations.add(getTranslation(Locale.ESPANOL_MEXICO, sMX.get(i)));
                translations.add(getTranslation(Locale.ESPANOL_ARGENTINA, sAR.get(i)));
                translations.add(getTranslation(Locale.FRANCAIS_FRANCE, sFR.get(i)));
                translations.add(getTranslation(Locale.FRANCAIS_CANADA, sCA.get(i)));


                modelLokaliseKey.setTranslations(translations);
                modelLokaliseKey.setPlatforms(platforms);
                modelLokaliseKey.setTags(tags);
                sModelLokaliseKeys.add(modelLokaliseKey);
            }
        } else {
            System.out.println("Keys and strings array size are not equal");
        }

//
        FileWriter jsonOutput = new FileWriter("json_output.txt");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(new Gson().toJson(new Keys(sModelLokaliseKeys)));
        String prettyJsonString = gson.toJson(je);
        jsonOutput.write(prettyJsonString);

        jsonOutput.close();
    }

    static class Keys {
        @SerializedName("keys")
        private ArrayList<Model_LokaliseKey> mModelLokaliseKeys;

        Keys(ArrayList<Model_LokaliseKey> modelLokaliseKeys) {
            mModelLokaliseKeys = modelLokaliseKeys;
        }

    }

    private static Model_LokaliseKey.Translation getTranslation(String languageISOCode, String value) {
        Model_LokaliseKey.Translation translation = new Model_LokaliseKey.Translation();
        translation.setLanguageIso(languageISOCode);
        translation.setTranslation(value);
        return translation;
    }

    static ArrayList<String> getStrings(String fileName) throws IOException {

        ArrayList<String> strings = new ArrayList<>();

        FileReader reader = new FileReader(fileName);

        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            strings.add(line);
        }

        bufferedReader.close();

        return strings;
    }

    public class Locale {
        public static final String ENGLISH_UNITED_STATES = "en_US";
        public static final String ENGLISH_UNITED_KINGDOM = "en_GB";
        public static final String ENGLISH_AUSTRALIA = "en_AU";
        public static final String DEUTCH_DEUTSCHLAND = "de_DE";
        public static final String ESPANOL_ESPANA = "es_ES";
        public static final String ESPANOL_MEXICO = "es_MX";
        public static final String ESPANOL_ARGENTINA = "es_AR";
        public static final String FRANCAIS_FRANCE = "fr_FR";
        public static final String FRANCAIS_CANADA = "fr_CA";
        public static final String PORTUGUES_BRASIL = "pt_BR";
        public static final String PORTUGUES_PORTUGAL = "pt_PT";
    }


}
