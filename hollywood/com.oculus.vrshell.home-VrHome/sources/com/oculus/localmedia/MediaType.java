package com.oculus.localmedia;

public enum MediaType {
    UNKNOWN(0),
    VIDEO(1),
    IMAGE(2),
    FOLDER(4),
    THREEDMODEL(5),
    UNCATEGORIZED(8);
    
    private int mValue;

    private MediaType(int value) {
        this.mValue = value;
    }

    public int getValue() {
        return this.mValue;
    }

    public boolean match(int types) {
        return (getValue() & types) != 0;
    }

    public static int all() {
        return VIDEO.getValue() | IMAGE.getValue() | FOLDER.getValue() | THREEDMODEL.getValue();
    }

    public static int none() {
        return 0;
    }

    public static int list(MediaType... types) {
        int bitmask = 0;
        for (MediaType type : types) {
            bitmask |= type.getValue();
        }
        return bitmask;
    }

    public static MediaType parse(String value) {
        if (VIDEO.toString().equalsIgnoreCase(value)) {
            return VIDEO;
        }
        if (IMAGE.toString().equalsIgnoreCase(value)) {
            return IMAGE;
        }
        if (FOLDER.toString().equalsIgnoreCase(value)) {
            return FOLDER;
        }
        if (THREEDMODEL.toString().equalsIgnoreCase(value)) {
            return THREEDMODEL;
        }
        if (UNCATEGORIZED.toString().equalsIgnoreCase(value)) {
            return UNCATEGORIZED;
        }
        return UNKNOWN;
    }
}
