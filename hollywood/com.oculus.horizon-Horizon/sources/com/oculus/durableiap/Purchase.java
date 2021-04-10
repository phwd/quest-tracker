package com.oculus.durableiap;

public class Purchase {
    public final String expirationTime;
    public final String grantTime;
    public final String purchaseID;
    public final String sku;

    public Purchase(String str, String str2, String str3, String str4) {
        this.sku = str;
        this.purchaseID = str2;
        this.grantTime = str3;
        this.expirationTime = str4;
    }
}
