package com.example.myhealthapp.model;

public class HealthInfoItem {
    private int id;
    private String textDate; //дата строкой
    private int idType; //тип показания
    private String textInfo;//значение строкой
    private String textDescr;

    public HealthInfoItem() {
    }

    public HealthInfoItem(int id, String textDate, int idType, String textInfo,String textDescr) {
        this.id = id;
        this.textDate = textDate;
        this.idType = idType;
        this.textInfo = textInfo;
        this.textDescr = textDescr;
    }

    public HealthInfoItem(String textDate, int idType, String textInfo, String textDescr) {
        this.textDate = textDate;
        this.textInfo = textInfo;
        this.idType = idType;
        this.textDescr = textDescr;
    }

    public HealthInfoItem(String textDate, int idType, String textInfo) {
        this.textDate = textDate;
        this.idType = idType;
        this.textInfo = textInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int textType) {
        this.idType = idType;
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
                ", тип " + idType +
                ", значение: " + textInfo ;
    }
}
