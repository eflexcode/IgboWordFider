package com.eflexsoft.chooigbowords.model;

public class SearchHistory {

    String id;
    String keyword;

    public SearchHistory() {
    }

    public SearchHistory(String id, String keyword) {
        this.id = id;
        this.keyword = keyword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
