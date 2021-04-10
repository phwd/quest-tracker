package com.oculus.localmedia.metadata;

public class SphericalVideoMetadata {
    private String mFormat;
    private String mProjectionType;

    public boolean isSpherical() {
        return this.mProjectionType != null && (this.mProjectionType.equalsIgnoreCase("EQUIRECTANGULAR") || this.mProjectionType.equalsIgnoreCase("CUBEMAP") || this.mProjectionType.equalsIgnoreCase("VR180") || this.mProjectionType.equalsIgnoreCase("DUALFISHEYE") || this.mProjectionType.equalsIgnoreCase("SINGLEFISHEYE"));
    }

    public boolean is180() {
        return this.mProjectionType != null && (this.mProjectionType.equalsIgnoreCase("VR180") || this.mProjectionType.equalsIgnoreCase("DUALFISHEYE") || this.mProjectionType.equalsIgnoreCase("SINGLEFISHEYE"));
    }

    public String getFormat() {
        return this.mFormat;
    }

    private SphericalVideoMetadata(String projectionType, String stereoMode) {
        this.mProjectionType = projectionType;
        this.mFormat = stereoMode;
    }

    public String toString() {
        return "SphericalVideoMetadata{, mProjectionType='" + this.mProjectionType + '\'' + ", mFormat='" + this.mFormat + '\'' + '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String mFormat;
        private String mProjectionType;

        public SphericalVideoMetadata from(CachedMetadata metadata) {
            if (metadata != null) {
                this.mProjectionType = metadata.is180() ? "VR180" : metadata.is360() ? "EQUIRECTANGULAR" : null;
                this.mFormat = metadata.isTopBotton() ? "3DTB" : metadata.is3D() ? "3D" : "2D";
            }
            return build();
        }

        public Builder setProjectionType(String projectionType) {
            this.mProjectionType = projectionType;
            return this;
        }

        public Builder setFormat(String format) {
            this.mFormat = format;
            return this;
        }

        public SphericalVideoMetadata build() {
            return new SphericalVideoMetadata(this.mProjectionType, this.mFormat);
        }
    }
}
