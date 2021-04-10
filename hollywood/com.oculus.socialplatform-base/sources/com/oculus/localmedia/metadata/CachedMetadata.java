package com.oculus.localmedia.metadata;

import com.oculus.localmedia.MediaType;

public class CachedMetadata {
    public boolean mHasAudio;
    public boolean mIs180;
    public boolean mIs360;
    public boolean mIs3D;
    public boolean mIsTopBotton;
    public int mOrientation;
    public MediaType mType;

    public static class Builder {
        public boolean mHasAudio;
        public boolean mIs180;
        public boolean mIs360;
        public boolean mIs3D;
        public boolean mIsTopBotton;
        public int mOrientation;
        public MediaType mType;

        public CachedMetadata build() {
            return new CachedMetadata(this.mType, this.mIs360, this.mIs180, this.mIs3D, this.mIsTopBotton, this.mOrientation, this.mHasAudio);
        }

        public Builder setHasAudio(boolean z) {
            this.mHasAudio = z;
            return this;
        }

        public Builder setIs180(boolean z) {
            this.mIs180 = z;
            return this;
        }

        public Builder setIs360(boolean z) {
            this.mIs360 = z;
            return this;
        }

        public Builder setIs3D(boolean z) {
            this.mIs3D = z;
            return this;
        }

        public Builder setIsTopBotton(boolean z) {
            this.mIsTopBotton = z;
            return this;
        }

        public Builder setOrientation(int i) {
            this.mOrientation = i;
            return this;
        }

        public Builder setType(MediaType mediaType) {
            this.mType = mediaType;
            return this;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:7:0x002d, code lost:
            if (r1 != false) goto L_0x002f;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.oculus.localmedia.metadata.CachedMetadata from(com.oculus.localmedia.metadata.ImageHeaderMetadata r4) {
            /*
                r3 = this;
                com.oculus.localmedia.MediaType r0 = com.oculus.localmedia.MediaType.IMAGE
                r3.mType = r0
                if (r4 == 0) goto L_0x003a
                boolean r0 = r4.isSpherical()
                r3.mIs360 = r0
                boolean r0 = r4.is180()
                r3.mIs180 = r0
                java.lang.String r2 = r4.mFormat
                java.lang.String r0 = "3DTB"
                boolean r1 = r0.equalsIgnoreCase(r2)
                r3.mIsTopBotton = r1
                java.lang.String r0 = "3D"
                boolean r0 = r0.equalsIgnoreCase(r2)
                if (r0 != 0) goto L_0x002f
                java.lang.String r0 = "3DLR"
                boolean r0 = r0.equalsIgnoreCase(r2)
                if (r0 != 0) goto L_0x002f
                r0 = 0
                if (r1 == 0) goto L_0x0030
            L_0x002f:
                r0 = 1
            L_0x0030:
                r3.mIs3D = r0
                int r0 = r4.mOrientationDegrees
                r3.mOrientation = r0
                boolean r0 = r4.mHasAudio
                r3.mHasAudio = r0
            L_0x003a:
                com.oculus.localmedia.metadata.CachedMetadata r0 = r3.build()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.localmedia.metadata.CachedMetadata.Builder.from(com.oculus.localmedia.metadata.ImageHeaderMetadata):com.oculus.localmedia.metadata.CachedMetadata");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:7:0x002e, code lost:
            if (r2 != false) goto L_0x0030;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.oculus.localmedia.metadata.CachedMetadata from(com.oculus.localmedia.metadata.SphericalVideoMetadata r5) {
            /*
                r4 = this;
                com.oculus.localmedia.MediaType r0 = com.oculus.localmedia.MediaType.VIDEO
                r4.mType = r0
                if (r5 == 0) goto L_0x0037
                boolean r0 = r5.isSpherical()
                r4.mIs360 = r0
                boolean r0 = r5.is180()
                r4.mIs180 = r0
                java.lang.String r3 = r5.mFormat
                java.lang.String r0 = "3DTB"
                boolean r2 = r0.equalsIgnoreCase(r3)
                r4.mIsTopBotton = r2
                java.lang.String r0 = "3D"
                boolean r0 = r0.equalsIgnoreCase(r3)
                r1 = 0
                if (r0 != 0) goto L_0x0030
                java.lang.String r0 = "3DLR"
                boolean r0 = r0.equalsIgnoreCase(r3)
                if (r0 != 0) goto L_0x0030
                r0 = 0
                if (r2 == 0) goto L_0x0031
            L_0x0030:
                r0 = 1
            L_0x0031:
                r4.mIs3D = r0
                r4.mOrientation = r1
                r4.mHasAudio = r1
            L_0x0037:
                com.oculus.localmedia.metadata.CachedMetadata r0 = r4.build()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.localmedia.metadata.CachedMetadata.Builder.from(com.oculus.localmedia.metadata.SphericalVideoMetadata):com.oculus.localmedia.metadata.CachedMetadata");
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("CachedMetadata{mType=");
        sb.append(this.mType);
        sb.append(", mIs360=");
        sb.append(this.mIs360);
        sb.append(", mIs180=");
        sb.append(this.mIs180);
        sb.append(", mIs3D=");
        sb.append(this.mIs3D);
        sb.append(", mIsTopBotton=");
        sb.append(this.mIsTopBotton);
        sb.append(", mOrientation=");
        sb.append(this.mOrientation);
        sb.append(", mHasAudio=");
        sb.append(this.mHasAudio);
        sb.append('}');
        return sb.toString();
    }

    public CachedMetadata(MediaType mediaType, boolean z, boolean z2, boolean z3, boolean z4, int i, boolean z5) {
        this.mType = mediaType;
        this.mIs360 = z;
        this.mIs180 = z2;
        this.mIs3D = z3;
        this.mIsTopBotton = z4;
        this.mOrientation = i;
        this.mHasAudio = z5;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public MediaType getType() {
        return this.mType;
    }

    public boolean hasAudio() {
        return this.mHasAudio;
    }

    public boolean is180() {
        return this.mIs180;
    }

    public boolean is360() {
        return this.mIs360;
    }

    public boolean is3D() {
        return this.mIs3D;
    }

    public boolean isTopBotton() {
        return this.mIsTopBotton;
    }
}
