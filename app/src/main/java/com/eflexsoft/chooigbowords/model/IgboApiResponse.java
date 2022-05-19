package com.eflexsoft.chooigbowords.model;

import java.util.ArrayList;

public class IgboApiResponse {



    private String wordClass;
    private ArrayList<String> definitions;
    private ArrayList<Object> variations;
    private ArrayList<Object> stems;
    private String word;
//    private Dialects dialects;
    private String pronunciation;
    private ArrayList<Object> antonyms;
    private ArrayList<Object> hypernyms;
    private ArrayList<Object> hyponyms;
    private ArrayList<String> synonyms;
    private String nsibidi;
    private String updatedAt;
    private Attributes attributes;
    private ArrayList<Example> examples;
    private String id;

    public IgboApiResponse(String wordClass, ArrayList<String> definitions, ArrayList<Object> variations, ArrayList<Object> stems, String word, String pronunciation, ArrayList<Object> antonyms, ArrayList<Object> hypernyms, ArrayList<Object> hyponyms, ArrayList<String> synonyms, String nsibidi, String updatedAt, Attributes attributes, ArrayList<Example> examples, String id) {
        this.wordClass = wordClass;
        this.definitions = definitions;
        this.variations = variations;
        this.stems = stems;
        this.word = word;
//        this.dialects = dialects;
        this.pronunciation = pronunciation;
        this.antonyms = antonyms;
        this.hypernyms = hypernyms;
        this.hyponyms = hyponyms;
        this.synonyms = synonyms;
        this.nsibidi = nsibidi;
        this.updatedAt = updatedAt;
        this.attributes = attributes;
        this.examples = examples;
        this.id = id;
    }

    public String getWordClass() {
        return wordClass;
    }

    public void setWordClass(String wordClass) {
        this.wordClass = wordClass;
    }

    public ArrayList<String> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(ArrayList<String> definitions) {
        this.definitions = definitions;
    }

    public ArrayList<Object> getVariations() {
        return variations;
    }

    public void setVariations(ArrayList<Object> variations) {
        this.variations = variations;
    }

    public ArrayList<Object> getStems() {
        return stems;
    }

    public void setStems(ArrayList<Object> stems) {
        this.stems = stems;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

//    public Dialects getDialects() {
//        return dialects;
//    }
//
//    public void setDialects(Dialects dialects) {
//        this.dialects = dialects;
//    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public ArrayList<Object> getAntonyms() {
        return antonyms;
    }

    public void setAntonyms(ArrayList<Object> antonyms) {
        this.antonyms = antonyms;
    }

    public ArrayList<Object> getHypernyms() {
        return hypernyms;
    }

    public void setHypernyms(ArrayList<Object> hypernyms) {
        this.hypernyms = hypernyms;
    }

    public ArrayList<Object> getHyponyms() {
        return hyponyms;
    }

    public void setHyponyms(ArrayList<Object> hyponyms) {
        this.hyponyms = hyponyms;
    }

    public ArrayList<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(ArrayList<String> synonyms) {
        this.synonyms = synonyms;
    }

    public String getNsibidi() {
        return nsibidi;
    }

    public void setNsibidi(String nsibidi) {
        this.nsibidi = nsibidi;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public ArrayList<Example> getExamples() {
        return examples;
    }

    public void setExamples(ArrayList<Example> examples) {
        this.examples = examples;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public class Attributes{

        private boolean isStandardIgbo;
        private boolean isAccented;
        private boolean isSlang;

        public Attributes(boolean isStandardIgbo, boolean isAccented, boolean isSlang) {
            this.isStandardIgbo = isStandardIgbo;
            this.isAccented = isAccented;
            this.isSlang = isSlang;
        }

        public boolean isStandardIgbo() {
            return isStandardIgbo;
        }

        public void setStandardIgbo(boolean standardIgbo) {
            isStandardIgbo = standardIgbo;
        }

        public boolean isAccented() {
            return isAccented;
        }

        public void setAccented(boolean accented) {
            isAccented = accented;
        }

        public boolean isSlang() {
            return isSlang;
        }

        public void setSlang(boolean slang) {
            isSlang = slang;
        }
    }

//    public class Dialects{
//
//    }

    public class Example{
        private String igbo;
        private String english;
        private ArrayList<String> associatedWords;
        private String pronunciation;
        private String updatedAt;
        private String meaning;
        private String style;
        private String id;

        public Example(String igbo, String english, ArrayList<String> associatedWords, String pronunciation, String updatedAt, String meaning, String style, String id) {
            this.igbo = igbo;
            this.english = english;
            this.associatedWords = associatedWords;
            this.pronunciation = pronunciation;
            this.updatedAt = updatedAt;
            this.meaning = meaning;
            this.style = style;
            this.id = id;
        }

        public String getIgbo() {
            return igbo;
        }

        public void setIgbo(String igbo) {
            this.igbo = igbo;
        }

        public String getEnglish() {
            return english;
        }

        public void setEnglish(String english) {
            this.english = english;
        }

        public ArrayList<String> getAssociatedWords() {
            return associatedWords;
        }

        public void setAssociatedWords(ArrayList<String> associatedWords) {
            this.associatedWords = associatedWords;
        }

        public String getPronunciation() {
            return pronunciation;
        }

        public void setPronunciation(String pronunciation) {
            this.pronunciation = pronunciation;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getMeaning() {
            return meaning;
        }

        public void setMeaning(String meaning) {
            this.meaning = meaning;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }



}
