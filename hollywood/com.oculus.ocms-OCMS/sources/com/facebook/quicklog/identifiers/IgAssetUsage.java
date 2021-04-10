package com.facebook.quicklog.identifiers;

public class IgAssetUsage {
    public static final int ANIMATION_ACCESSED = 38797313;
    public static final int DOWNLOAD_TIME = 38797316;
    public static final int EMOJI_KEYWORD_STORE_ACCESSED = 38797315;
    public static final int FETCH = 38797321;
    public static final int FETCH_FAILURE = 38797322;
    public static final int FONT_ACCESSED = 38797314;
    public static final int FONT_RECEIVED = 38797320;
    public static final short MODULE_ID = 592;
    public static final int NO_FILE = 38797317;
    public static final int RECEIVED_EMOJIS = 38797318;
    public static final int SAVE_FAIL = 38797319;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "IG_ASSET_USAGE_ANIMATION_ACCESSED";
            case 2:
                return "IG_ASSET_USAGE_FONT_ACCESSED";
            case 3:
                return "IG_ASSET_USAGE_EMOJI_KEYWORD_STORE_ACCESSED";
            case 4:
                return "IG_ASSET_USAGE_DOWNLOAD_TIME";
            case 5:
                return "IG_ASSET_USAGE_NO_FILE";
            case 6:
                return "IG_ASSET_USAGE_RECEIVED_EMOJIS";
            case 7:
                return "IG_ASSET_USAGE_SAVE_FAIL";
            case 8:
                return "IG_ASSET_USAGE_FONT_RECEIVED";
            case 9:
                return "IG_ASSET_USAGE_FETCH";
            case 10:
                return "IG_ASSET_USAGE_FETCH_FAILURE";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
