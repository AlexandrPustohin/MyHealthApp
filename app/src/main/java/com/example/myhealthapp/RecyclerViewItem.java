package com.example.myhealthapp;

public class RecyclerViewItem {
    private String textDate; //дата строкой
    private String textPress;//давление строкой

    public RecyclerViewItem(String textDate, String textPress) {
        this.textDate = textDate;
        this.textPress = textPress;
    }

    public String getTextDate() {
        return textDate;
    }

    public void setTextDate(String textDate) {
        this.textDate = textDate;
    }

    public String getTextPress() {
        return textPress;
    }

    public void setTextPress(String textPress) {
        this.textPress = textPress;
    }
}
