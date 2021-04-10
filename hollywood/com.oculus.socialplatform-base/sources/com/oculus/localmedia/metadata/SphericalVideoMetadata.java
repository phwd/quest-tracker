package com.oculus.localmedia.metadata;

public class SphericalVideoMetadata {
    public String mFormat;
    public String mProjectionType;

    public static class Builder {
        public String mFormat;
        public String mProjectionType;

        public SphericalVideoMetadata build() {
            return new SphericalVideoMetadata(this.mProjectionType, this.mFormat);
        }

        public SphericalVideoMetadata from(CachedMetadata cachedMetadata) {
            String str;
            String str2;
            if (cachedMetadata != null) {
                if (cachedMetadata.mIs180) {
                    str = "VR180";
                } else if (cachedMetadata.mIs360) {
                    str = "EQUIRECTANGULAR";
                } else {
                    str = null;
                }
                this.mProjectionType = str;
                if (cachedMetadata.mIsTopBotton) {
                    str2 = "3DTB";
                } else if (cachedMetadata.mIs3D) {
                    str2 = "3D";
                } else {
                    str2 = "2D";
                }
                this.mFormat = str2;
            }
            return build();
        }

        public Builder setFormat(String str) {
            this.mFormat = str;
            return this;
        }

        public Builder setProjectionType(String str) {
            this.mProjectionType = str;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean is180() {
        String str = this.mProjectionType;
        if (str == null) {
            return false;
        }
        if (str.equalsIgnoreCase("VR180") || str.equalsIgnoreCase("DUALFISHEYE") || str.equalsIgnoreCase("SINGLEFISHEYE")) {
            return true;
        }
        return false;
    }

    public boolean isSpherical() {
        String str = this.mProjectionType;
        if (str == null) {
            return false;
        }
        if (str.equalsIgnoreCase("EQUIRECTANGULAR") || str.equalsIgnoreCase("CUBEMAP") || str.equalsIgnoreCase("VR180") || str.equalsIgnoreCase("DUALFISHEYE") || str.equalsIgnoreCase("SINGLEFISHEYE")) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SphericalVideoMetadata{, mProjectionType='");
        sb.append(this.mProjectionType);
        sb.append('\'');
        sb.append(", mFormat='");
        sb.append(this.mFormat);
        sb.append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getFormat() {
        return this.mFormat;
    }

    public SphericalVideoMetadata(String str, String str2) {
        this.mProjectionType = str;
        this.mFormat = str2;
    }
}
