package com.oculus.localmedia;

public enum MediaType {
    UNKNOWN(0),
    VIDEO(1),
    IMAGE(2),
    FOLDER(4),
    THREEDMODEL(5),
    UNCATEGORIZED(8);
    
    public int mValue;

    public static int list(MediaType... mediaTypeArr) {
        int i = 0;
        for (MediaType mediaType : mediaTypeArr) {
            i |= mediaType.getValue();
        }
        return i;
    }

    public static int none() {
        return 0;
    }

    public static int all() {
        return VIDEO.getValue() | IMAGE.getValue() | FOLDER.getValue() | THREEDMODEL.getValue();
    }

    public static MediaType parse(String str) {
        MediaType mediaType = VIDEO;
        if (!mediaType.toString().equalsIgnoreCase(str)) {
            mediaType = IMAGE;
            if (!mediaType.toString().equalsIgnoreCase(str)) {
                mediaType = FOLDER;
                if (!mediaType.toString().equalsIgnoreCase(str)) {
                    mediaType = THREEDMODEL;
                    if (!mediaType.toString().equalsIgnoreCase(str)) {
                        mediaType = UNCATEGORIZED;
                        if (!mediaType.toString().equalsIgnoreCase(str)) {
                            return UNKNOWN;
                        }
                    }
                }
            }
        }
        return mediaType;
    }

    /* access modifiers changed from: public */
    MediaType(int i) {
        this.mValue = i;
    }

    public int getValue() {
        return this.mValue;
    }

    public boolean match(int i) {
        if ((i & getValue()) != 0) {
            return true;
        }
        return false;
    }
}
