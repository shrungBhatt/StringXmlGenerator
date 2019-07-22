package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Model_LokaliseKey implements Serializable {

    @SerializedName("key_name")
    @Expose
    private String keyName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("platforms")
    @Expose
    private ArrayList<String> platforms = null;
    @SerializedName("tags")
    @Expose
    private ArrayList<String> tags = null;
    @SerializedName("translations")
    @Expose
    private ArrayList<Translation> translations = null;
    @SerializedName("is_plural")
    @Expose
    private Boolean isPlural;

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(ArrayList<String> platforms) {
        this.platforms = platforms;
    }

    public ArrayList<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(ArrayList<Translation> translations) {
        this.translations = translations;
    }

    public Boolean getIsPlural() {
        return isPlural;
    }

    public void setIsPlural(Boolean isPlural) {
        this.isPlural = isPlural;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public static class Translation implements Serializable {

        @SerializedName("language_iso")
        @Expose
        private String languageIso;
        @SerializedName("translation")
        @Expose
        private String translation;

        public String getLanguageIso() {
            return languageIso;
        }

        public void setLanguageIso(String languageIso) {
            this.languageIso = languageIso;
        }

        public String getTranslation() {
            return translation;
        }

        public void setTranslation(String translation) {
            this.translation = translation;
        }

    }

    public static class Translation_ implements Serializable{

        @SerializedName("one")
        @Expose
        private String one;
        @SerializedName("other")
        @Expose
        private String other;

        public String getOne() {
            return one;
        }

        public void setOne(String one) {
            this.one = one;
        }

        public String getOther() {
            return other;
        }

        public void setOther(String other) {
            this.other = other;
        }

    }
}
