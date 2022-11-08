package com.example.androidapp_exe.Entity;

public class Crypto {
    private String uid;
    private String cryptoName;

    public Crypto() {
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
