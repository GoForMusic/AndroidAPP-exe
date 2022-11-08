package com.example.androidapp_exe.Entity;

import java.util.ArrayList;

public class Wallet {
    private String uid;
    private float walletBallanceUSD;
    private ArrayList<Crypto> cryptos;

    public Wallet() {
    }

    public String getUid() {
        return uid;
    }

    public ArrayList<Crypto> getCryptos() {
        return cryptos;
    }

    public float getWalletBallanceUSD() {
        return walletBallanceUSD;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setCryptos(ArrayList<Crypto> cryptos) {
        this.cryptos = cryptos;
    }

    public void addCryptos(Crypto crypto){
        cryptos.add(crypto);
    }

    public void setWalletBallanceUSD(float walletBallanceUSD) {
        this.walletBallanceUSD = walletBallanceUSD;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "uid='" + uid + '\'' +
                ", walletBallanceUSD=" + walletBallanceUSD +
                ", cryptos=" + cryptos +
                '}';
    }
}
