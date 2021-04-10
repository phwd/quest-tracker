package com.oculus.localmedia.metadata;

public class ImageHeaderMetadata {
    public String mFormat;
    public boolean mHasAudio;
    public int mOrientationDegrees;
    public String mProjectionType;

    public static class Builder {
        public String mFormat;
        public boolean mHasAudio;
        public int mOrientationDegrees;
        public String mProjectionType;

        public ImageHeaderMetadata build() {
            return new ImageHeaderMetadata(this.mProjectionType, this.mFormat, this.mOrientationDegrees, this.mHasAudio);
        }

        public ImageHeaderMetadata from(CachedMetadata cachedMetadata) {
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
                this.mOrientationDegrees = cachedMetadata.mOrientation;
                this.mHasAudio = cachedMetadata.mHasAudio;
            }
            return build();
        }

        public Builder setFormat(String str) {
            this.mFormat = str;
            return this;
        }

        public Builder setHasAudio(boolean z) {
            this.mHasAudio = z;
            return this;
        }

        public Builder setOrientation(int i) {
            this.mOrientationDegrees = i;
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
        if (str == null || !str.equalsIgnoreCase("VR180")) {
            return false;
        }
        return true;
    }

    public boolean isSpherical() {
        String str = this.mProjectionType;
        if (str == null) {
            return false;
        }
        if (str.equalsIgnoreCase("EQUIRECTANGULAR") || str.equalsIgnoreCase("VR180")) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ImageHeaderMetadata{mProjectionType='");
        sb.append(this.mProjectionType);
        sb.append('\'');
        sb.append(", mFormat='");
        sb.append(this.mFormat);
        sb.append('\'');
        sb.append(", mOrientationDegrees=");
        sb.append(this.mOrientationDegrees);
        sb.append(", mHasAudio=");
        sb.append(this.mHasAudio);
        sb.append('}');
        return sb.toString();
    }

    public String getFormat() {
        return this.mFormat;
    }

    public int getOrientation() {
        return this.mOrientationDegrees;
    }

    public boolean hasAudio() {
        return this.mHasAudio;
    }

    public ImageHeaderMetadata(String str, String str2, int i, boolean z) {
        this.mProjectionType = str;
        this.mFormat = str2;
        this.mOrientationDegrees = i;
        this.mHasAudio = z;
    }
}
