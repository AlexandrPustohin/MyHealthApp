package com.example.myhealthapp.model;

public class HealthInfoItem {
    private int id;
    private String textDate; //дата строкой
    private String textType; //тип показания
    private String textInfo;//значение строкой
    private String textDescr;

    public HealthInfoItem() {
    }

    public HealthInfoItem(int id, String textDate, String textType, String textInfo,String textDescr) {
        this.id = id;
        this.textDate = textDate;
        this.textType = textType;
        this.textInfo = textInfo;
        this.textDescr = textDescr;
    }

    public HealthInfoItem(String textDate, String textType, String textInfo, String textDescr) {
        this.textDate = textDate;
        this.textInfo = textInfo;
        this.textType = textType;
        this.textDescr = textDescr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextType() {
        return textType;
    }

    public void setTextType(String textType) {
        this.textType = textType;
    }

    public String getTextDate() {
        return textDate;
    }

    public void setTextDate(String textDate) {
        this.textDate = textDate;
    }

    public String getTextInfo() {
        return textInfo;
    }

    public void setTextInfo(String textInfo) {
        this.textInfo = textInfo;
    }

    public String getTextDescr() {
        return textDescr;
    }

    public void setTextDescr(String textDescr) {
        this.textDescr = textDescr;
    }

    @Override
    public String toString() {
        return "Показатель" +
                " № " + id +
                ", дата " + textDate +
                ", тип " + textType +
                ", значение: " + textInfo ;
    }
}
