package com.oculus.localmedia.metadata;

import com.oculus.localmedia.MediaType;

public class CachedMetadata {
    private boolean mHasAudio;
    private boolean mIs180;
    private boolean mIs360;
    private boolean mIs3D;
    private boolean mIsTopBotton;
    private int mOrientation;
    private MediaType mType;

    public CachedMetadata(MediaType type, boolean is360, boolean is180, boolean is3D, boolean isTopBotton, int orientation, boolean hasAudio) {
        this.mType = type;
        this.mIs360 = is360;
        this.mIs180 = is180;
        this.mIs3D = is3D;
        this.mIsTopBotton = isTopBotton;
        this.mOrientation = orientation;
        this.mHasAudio = hasAudio;
    }

    public MediaType getType() {
        return this.mType;
    }

    public boolean is360() {
        return this.mIs360;
    }

    public boolean is180() {
        return this.mIs180;
    }

    public boolean is3D() {
        return this.mIs3D;
    }

    public boolean isTopBotton() {
        return this.mIsTopBotton;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public boolean hasAudio() {
        return this.mHasAudio;
    }

    public String toString() {
        return "CachedMetadata{mType=" + this.mType + ", mIs360=" + this.mIs360 + ", mIs180=" + this.mIs180 + ", mIs3D=" + this.mIs3D + ", mIsTopBotton=" + this.mIsTopBotton + ", mOrientation=" + this.mOrientation + ", mHasAudio=" + this.mHasAudio + '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private boolean mHasAudio;
        private boolean mIs180;
        private boolean mIs360;
        private boolean mIs3D;
        private boolean mIsTopBotton;
        private int mOrientation;
        private MediaType mType;

        public CachedMetadata from(SphericalVideoMetadata metadata) {
            this.mType = MediaType.VIDEO;
            if (metadata != null) {
                this.mIs360 = metadata.isSpherical();
                this.mIs180 = metadata.is180();
                this.mIsTopBotton = "3DTB".equalsIgnoreCase(metadata.getFormat());
                this.mIs3D = "3D".equalsIgnoreCase(metadata.getFormat()) || "3DLR".equalsIgnoreCase(metadata.getFormat()) || this.mIsTopBotton;
                this.mOrientation = 0;
                this.mHasAudio = false;
            }
            return build();
        }

        public CachedMetadata from(ImageHeaderMetadata metadata) {
            this.mType = MediaType.IMAGE;
            if (metadata != null) {
                this.mIs360 = metadata.isSpherical();
                this.mIs180 = metadata.is180();
                this.mIsTopBotton = "3DTB".equalsIgnoreCase(metadata.getFormat());
                this.mIs3D = "3D".equalsIgnoreCase(metadata.getFormat()) || "3DLR".equalsIgnoreCase(metadata.getFormat()) || this.mIsTopBotton;
                this.mOrientation = metadata.getOrientation();
                this.mHasAudio = metadata.hasAudio();
            }
            return build();
        }

        public Builder setType(MediaType type) {
            this.mType = type;
            return this;
        }

        public Builder setIs360(boolean is360) {
            this.mIs360 = is360;
            return this;
        }

        public Builder setIs180(boolean is180) {
            this.mIs180 = is180;
            return this;
        }

        public Builder setIs3D(boolean is3D) {
            this.mIs3D = is3D;
            return this;
        }

        public Builder setIsTopBotton(boolean mIsTopBotton2) {
            this.mIsTopBotton = mIsTopBotton2;
            return this;
        }

        public Builder setOrientation(int degrees) {
            this.mOrientation = degrees;
            return this;
        }

        public Builder setHasAudio(boolean hasAudio) {
            this.mHasAudio = hasAudio;
            return this;
        }

        public CachedMetadata build() {
            return new CachedMetadata(this.mType, this.mIs360, this.mIs180, this.mIs3D, this.mIsTopBotton, this.mOrientation, this.mHasAudio);
        }
    }
}
