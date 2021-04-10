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

    public AssetInfo(String installedBinaryID2, String packageName2, String assetID, String assetName, String assetType, String assetMetadata, String assetUri, long assetSize, String assetEntitlement, boolean isRequired2, String languageCode2, String languageEnglishName2, String languageNativeName2) {
        this.packageName = packageName2;
        this.installedBinaryID = installedBinaryID2;
        this.id = assetID;
        this.name = assetName;
        this.type = assetType;
        this.metadata = assetMetadata;
        this.entitlement = assetEntitlement;
        this.uri = assetUri;
        this.size = assetSize;
        this.isRequired = isRequired2;
        this.languageCode = languageCode2;
        this.languageEnglishName = languageEnglishName2;
        this.languageNativeName = languageNativeName2;
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

        public Builder withPackageName(String packageName2) {
            this.packageName = packageName2;
            return this;
        }

        public Builder withInstalledBinaryID(String installedBinaryID2) {
            this.installedBinaryID = installedBinaryID2;
            return this;
        }

        public Builder withID(String id2) {
            this.id = id2;
            return this;
        }

        public Builder withName(String name2) {
            this.name = name2;
            return this;
        }

        public Builder withType(String type2) {
            this.type = type2;
            return this;
        }

        public Builder withURI(String uri2) {
            this.uri = uri2;
            return this;
        }

        public Builder withMetadata(String metadata2) {
            this.metadata = metadata2;
            return this;
        }

        public Builder withSize(long size2) {
            this.size = size2;
            return this;
        }

        public Builder withEntitlement(String entitlement2) {
            this.entitlement = entitlement2;
            return this;
        }

        public Builder withIsRequired(boolean isRequired2) {
            this.isRequired = isRequired2;
            return this;
        }

        public Builder withLanguageCode(String languageCode2) {
            this.languageCode = languageCode2;
            return this;
        }

        public Builder withLanguageEnglishName(String languageEnglishName2) {
            this.languageEnglishName = languageEnglishName2;
            return this;
        }

        public Builder withLanguageNativeName(String languageNativeName2) {
            this.languageNativeName = languageNativeName2;
            return this;
        }
    }
}
