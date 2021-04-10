package com.facebook.imagepipeline.image;

public enum EncodedImageOrigin {
    NOT_SET("not_set"),
    NETWORK("network"),
    DISK("disk"),
    ENCODED_MEM_CACHE("encoded_mem_cache");
    
    private final String mOrigin;

    private EncodedImageOrigin(String str) {
        this.mOrigin = str;
    }

    public String toString() {
        return this.mOrigin;
    }
}
