package com.oculus.localmedia.metadata;

public class ImageHeaderMetadata {
    private String mFormat;
    private boolean mHasAudio;
    private int mOrientationDegrees;
    private String mProjectionType;

    public boolean isSpherical() {
        return this.mProjectionType != null && (this.mProjectionType.equalsIgnoreCase("EQUIRECTANGULAR") || this.mProjectionType.equalsIgnoreCase("VR180"));
    }

    public boolean is180() {
        return this.mProjectionType != null && this.mProjectionType.equalsIgnoreCase("VR180");
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

    private ImageHeaderMetadata(String projectionType, String format, int degrees, boolean hasAudio) {
        this.mProjectionType = projectionType;
        this.mFormat = format;
        this.mOrientationDegrees = degrees;
        this.mHasAudio = hasAudio;
    }

    public String toString() {
        return "ImageHeaderMetadata{mProjectionType='" + this.mProjectionType + '\'' + ", mFormat='" + this.mFormat + '\'' + ", mOrientationDegrees=" + this.mOrientationDegrees + ", mHasAudio=" + this.mHasAudio + '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String mFormat;
        private boolean mHasAudio;
        private int mOrientationDegrees;
        private String mProjectionType;

        public ImageHeaderMetadata from(CachedMetadata metadata) {
            if (metadata != null) {
                this.mProjectionType = metadata.is180() ? "VR180" : metadata.is360() ? "EQUIRECTANGULAR" : null;
                this.mFormat = metadata.isTopBotton() ? "3DTB" : metadata.is3D() ? "3D" : "2D";
                this.mOrientationDegrees = metadata.getOrientation();
                this.mHasAudio = metadata.hasAudio();
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

        public Builder setOrientation(int degrees) {
            this.mOrientationDegrees = degrees;
            return this;
        }

        public Builder setHasAudio(boolean hasAudio) {
            this.mHasAudio = hasAudio;
            return this;
        }

        public ImageHeaderMetadata build() {
            return new ImageHeaderMetadata(this.mProjectionType, this.mFormat, this.mOrientationDegrees, this.mHasAudio);
        }
    }
}
