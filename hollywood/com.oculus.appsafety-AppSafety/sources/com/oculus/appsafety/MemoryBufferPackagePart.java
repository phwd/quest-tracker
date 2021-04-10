package com.oculus.appsafety;

import com.oculus.appsafety.PackagePartConfig;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class MemoryBufferPackagePart extends PackagePart<Config.Builder> {
    private final byte[] buffer;

    MemoryBufferPackagePart(byte[] buffer2, String packageIdentifier, String name) {
        this(buffer2, packageIdentifier, name, null, "application/octet-stream");
    }

    MemoryBufferPackagePart(byte[] buffer2, String packageIdentifier, String name, byte[] sha256) {
        this(buffer2, packageIdentifier, name, sha256, "application/octet-stream");
    }

    MemoryBufferPackagePart(byte[] buffer2, String packageIdentifier, String name, byte[] sha256, String mimeType) {
        super(packageIdentifier, name, mimeType, sha256);
        this.buffer = buffer2;
    }

    MemoryBufferPackagePart(Config config) {
        super(config);
        this.buffer = config.buffer;
    }

    @Override // com.oculus.appsafety.PackagePart
    public long getLength() {
        return (long) this.buffer.length;
    }

    @Override // com.oculus.appsafety.PackagePart
    public InputStream getStream() {
        return new ByteArrayInputStream(this.buffer);
    }

    @Override // com.oculus.appsafety.PackagePart
    public PackagePartConfig getConfig() {
        return ((Config.Builder) saveConfig(new Config.Builder())).setData(this.buffer).build();
    }

    public static class Config extends PackagePartConfig {
        public final byte[] buffer;

        Config(Builder builder) {
            super(builder);
            this.buffer = builder.buffer;
        }

        @Override // com.oculus.appsafety.PackagePartConfig
        public PackagePart create() {
            return new MemoryBufferPackagePart(this);
        }

        public static class Builder extends PackagePartConfig.Builder {
            byte[] buffer;

            public Builder setData(byte[] buffer2) {
                this.buffer = buffer2;
                return this;
            }

            @Override // com.oculus.appsafety.PackagePartConfig.Builder
            public PackagePartConfig build() {
                return new Config(this);
            }
        }
    }
}
