package com.facebook.quicklog.identifiers;

public class ComposerSprouts {
    public static final int DRAWER_SEARCH_STICKERS_FB4A = 23068675;
    public static final short MODULE_ID = 352;
    public static final int PLUS_BUTTON_TO_SPROUT_DRAWER_RENDER = 23068673;
    public static final int STICKER_KEYBOARD_FB4A = 23068674;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "COMPOSER_SPROUTS_DRAWER_SEARCH_STICKERS_FB4A" : "COMPOSER_SPROUTS_STICKER_KEYBOARD_FB4A" : "COMPOSER_SPROUTS_PLUS_BUTTON_TO_SPROUT_DRAWER_RENDER";
    }
}
