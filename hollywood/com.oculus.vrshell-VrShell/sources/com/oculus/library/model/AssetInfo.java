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

    public static class Builder {
        private String entitlement;
        private String id;
        private String installedBinaryID;
        private boolean isRequired;
        private String languageCode;
        private String languageEnglishName;
        private String languageNativeName;
        private String metadata;
        private String name;
        private String packageName;
        private long size;
        private String type;
        private String uri;

        public Builder() {
        }

        public Builder(AssetInfo assetInfo) {
            this.packageName = assetInfo.packageName;
            this.installedBinaryID = assetInfo.installedBinaryID;
            this.id = assetInfo.id;
            this.name = assetInfo.name;
            this.type = assetInfo.type;
            this.metadata = assetInfo.metadata;
            this.entitlement = assetInfo.entitlement;
            this.uri = assetInfo.uri;
            this.size = assetInfo.size;
            this.isRequired = assetInfo.isRequired;
            this.languageCode = assetInfo.languageCode;
            this.languageEnglishName = assetInfo.languageEnglishName;
            this.languageNativeName = assetInfo.languageNativeName;
        }

        public AssetInfo build() {
            return new AssetInfo(this.installedBinaryID, this.packageName, this.id, this.name, this.type, this.metadata, this.uri, this.size, this.entitlement, this.isRequired, this.languageCode, this.languageEnglishName, this.languageNativeName);
        }

        public Builder withPackageName(String str) {
            this.packageName = str;
            return this;
        }

        public Builder withInstalledBinaryID(String str) {
            this.installedBinaryID = str;
            return this;
        }

        public Builder withID(String str) {
            this.id = str;
            return this;
        }

        public Builder withName(String str) {
            this.name = str;
            return this;
        }

        public Builder withType(String str) {
            this.type = str;
            return this;
        }

        public Builder withURI(String str) {
            this.uri = str;
            return this;
        }

        public Builder withMetadata(String str) {
            this.metadata = str;
            return this;
        }

        public Builder withSize(long j) {
            this.size = j;
            return this;
        }

        public Builder withEntitlement(String str) {
            this.entitlement = str;
            return this;
        }

        public Builder withIsRequired(boolean z) {
            this.isRequired = z;
            return this;
        }

        public Builder withLanguageCode(String str) {
            this.languageCode = str;
            return this;
        }

        public Builder withLanguageEnglishName(String str) {
            this.languageEnglishName = str;
            return this;
        }

        public Builder withLanguageNativeName(String str) {
            this.languageNativeName = str;
            return this;
        }
    }
}
