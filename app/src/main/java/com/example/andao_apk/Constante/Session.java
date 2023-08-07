package com.example.andao_apk.Constante;

public class Session {
    private static Session instance;
    private String myValue = "";

    private Session() {}

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public String getMyValue() {
        return myValue;
    }

    public void setMyValue(String value) {
        myValue = value;
    }
}
