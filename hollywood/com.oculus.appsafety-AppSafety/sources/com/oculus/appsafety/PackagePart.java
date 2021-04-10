package com.oculus.appsafety;

import com.oculus.appsafety.PackagePartConfig;
import com.oculus.appsafety.PackagePartConfig.Builder;
import java.io.InputStream;

public abstract class PackagePart<T extends PackagePartConfig.Builder> {
    private final String mimeType;
    private final String name;
    private final String packageIdentifier;
    private final byte[] sha256;

    public abstract PackagePartConfig getConfig();

    public abstract long getLength();

    public abstract InputStream getStream();

    protected PackagePart(PackagePartConfig config) {
        this(config.packageIdentifier, config.name, config.mimeType, config.sha256);
    }

    protected PackagePart(String packageIdentifier2, String name2, String mimeType2, byte[] sha2562) {
        this.packageIdentifier = packageIdentifier2;
        this.name = name2;
        this.mimeType = mimeType2;
        this.sha256 = sha2562;
    }

    public String getPackageIdentifier() {
        return this.packageIdentifier;
    }

    public String getName() {
        return this.name;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public byte[] getSHA256() {
        return this.sha256;
    }

    /* access modifiers changed from: protected */
    public <T extends PackagePartConfig.Builder> T saveConfig(T builder) {
        builder.setPackageIdentifier(this.packageIdentifier).setName(this.name).setMimeType(this.mimeType).setSHA256(this.sha256);
        return builder;
    }
}
