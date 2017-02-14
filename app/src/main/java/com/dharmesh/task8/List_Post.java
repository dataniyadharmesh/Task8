package com.dharmesh.task8;

/**
 * Created by DHARMESH on 14-02-2017.
 */

public class List_Post {

    int id,cat_id;
    String quotes;
    private static String quote;

    public static String getQuote() {
        return quote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }
}
