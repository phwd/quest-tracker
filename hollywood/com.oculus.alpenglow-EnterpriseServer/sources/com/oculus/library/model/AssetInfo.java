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
}
