package com.bumptech.glide.load;

public enum ImageHeaderParser$ImageType {
    GIF(true),
    JPEG(false),
    RAW(false),
    PNG_A(true),
    PNG(false),
    WEBP_A(true),
    WEBP(false),
    UNKNOWN(false);
    
    public final boolean hasAlpha;

    public boolean hasAlpha() {
        return this.hasAlpha;
    }

    /* access modifiers changed from: public */
    ImageHeaderParser$ImageType(boolean z) {
        this.hasAlpha = z;
    }
}
