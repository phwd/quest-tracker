package com.oculus.library.model;

public class AssetInfo {
    public final String entitlement;
    public final String id;
    public final String installedBinaryID;
    public final boolean isRequired;
    public final String languageCode;
    public final String languageEnglishName;
    public final String languageNativeName;
    public final String metadata;
    public final String name;
    public final String packageName;
    public final long size;
    public final String type;
    public final String uri;

    public static class Builder {
        public String entitlement;
        public String id;
        public String installedBinaryID;
        public boolean isRequired;
        public String languageCode;
        public String languageEnglishName;
        public String languageNativeName;
        public String metadata;
        public String name;
        public String packageName;
        public long size;
        public String type;
        public String uri;
    }

    public AssetInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, long j, String str8, boolean z, String str9, String str10, String str11) {
        this.packageName = str2;
        this.installedBinaryID = str;
        this.id = str3;
        this.name = str4;
        this.type = str5;
        this.metadata = str6;
        this.entitlement = str8;
        this.uri = str7;
        this.size = j;
        this.isRequired = z;
        this.languageCode = str9;
        this.languageEnglishName = str10;
        this.languageNativeName = str11;
    }
}
