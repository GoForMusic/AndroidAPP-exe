package com.example.androidapp_exe.Entity;

public class Crypto {
    private String uid;
    private String cryptoName;
    private float value;

    public Crypto() {
    }

    public float getValue() {
        return value;
    }

    public void setValue(float valueæ) {
        this.value = valueæ;
    }

    public String getUid() {
        return uid;
    }

    public String getCryptoName() {
        return cryptoName;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setCryptoName(String cryptoName) {
        this.cryptoName = cryptoName;
    }

    @Override
    public String toString() {
        return "Crypto{" +
                "uid='" + uid + '\'' +
                ", cryptoName='" + cryptoName + '\'' +
                '}';
    }
}
