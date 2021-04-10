package com.oculus.appsafety;

public class BinaryCheckRequestObject {
    public final String accessToken;
    public final String[] certFingerprints;
    public final String packageName;

    public BinaryCheckRequestObject(String accessToken2, String packageName2, String[] certFingerprints2) {
        this.accessToken = accessToken2;
        this.packageName = packageName2;
        this.certFingerprints = certFingerprints2;
    }
}
