package com.facebook.quicklog.identifiers;

public class Stickers {
    public static final short MODULE_ID = 25;
    public static final int STICKER_KEYBOARD = 1638401;
    public static final int STICKER_POST = 1638402;
    public static final int STICKER_STORE = 1638403;
    public static final int STICKER_STORE_WITH_PACK = 1638404;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "STICKERS_STICKER_STORE_WITH_PACK" : "STICKERS_STICKER_STORE" : "STICKERS_STICKER_POST" : "STICKERS_STICKER_KEYBOARD";
    }
}
