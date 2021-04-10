package com.oculus.appsafety;

public abstract class PackagePartConfig {
    public final String mimeType;
    public final String name;
    public final String packageIdentifier;
    public final byte[] sha256;

    public abstract PackagePart create();

    PackagePartConfig(String packageIdentifier2, String name2, String mimeType2, byte[] sha2562) {
        this.packageIdentifier = packageIdentifier2;
        this.name = name2;
        this.mimeType = mimeType2;
        this.sha256 = sha2562;
    }

    PackagePartConfig(Builder builder) {
        this(builder.packageIdentifier, builder.name, builder.mimeType, builder.sha256);
    }

    public static abstract class Builder {
        String mimeType;
        String name;
        String packageIdentifier;
        byte[] sha256;

        public abstract PackagePartConfig build();

        public Builder setPackageIdentifier(String packageIdentifier2) {
            this.packageIdentifier = packageIdentifier2;
            return this;
        }

        public Builder setName(String name2) {
            this.name = name2;
            return this;
        }

        public Builder setMimeType(String mimeType2) {
            this.mimeType = mimeType2;
            return this;
        }

        public Builder setSHA256(byte[] sha2562) {
            this.sha256 = sha2562;
            return this;
        }
    }
}
