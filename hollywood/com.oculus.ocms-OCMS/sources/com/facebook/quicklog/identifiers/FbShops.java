package com.facebook.quicklog.identifiers;

public class FbShops {
    public static final int BLOKS_SHOP_SCREEN_TIME = 869735433;
    public static final short MODULE_ID = 13271;
    public static final int UCI_LOAD_TIME = 869738748;
    public static final int UCI_TTRC = 869733070;

    public static String getMarkerName(int i) {
        return i != 4814 ? i != 7177 ? i != 10492 ? "UNDEFINED_QPL_EVENT" : "FB_SHOPS_UCI_LOAD_TIME" : "FB_SHOPS_BLOKS_SHOP_SCREEN_TIME" : "FB_SHOPS_UCI_TTRC";
    }
}
