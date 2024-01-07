package com.example.myhealthapp.model;

public class HealthInfoItem {
    private long id;
    private String textDate; //дата строкой
    private String textType; //тип показания
    private String textInfo;//значение строкой

    public HealthInfoItem() {
    }

    public HealthInfoItem(long id, String textDate, String textType, String textInfo) {
        this.id = id;
        this.textDate = textDate;
        this.textType = textType;
        this.textInfo = textInfo;
    }

    public HealthInfoItem(String textDate, String textType, String textInfo) {
        this.textDate = textDate;
        this.textInfo = textInfo;
        this.textType = textType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
