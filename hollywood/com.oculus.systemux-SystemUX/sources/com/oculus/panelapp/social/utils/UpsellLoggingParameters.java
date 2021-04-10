package com.oculus.panelapp.social.utils;

public class UpsellLoggingParameters {
    public final String mAction;
    public final String mContainer;
    public final String mEntryPoint;
    public final String mMustInteract;
    public final String mProduct;
    public final String mSource;

    public UpsellLoggingParameters(String str, String str2, String str3, String str4, String str5, String str6) {
        this.mSource = str;
        this.mAction = str2;
        this.mContainer = str3;
        this.mEntryPoint = str4;
        this.mMustInteract = str5;
        this.mProduct = str6;
    }
}
