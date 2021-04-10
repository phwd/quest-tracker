package com.facebook.quicklog.identifiers;

public class Avatars {
    public static final int AVATARS_AUTOGEN_LOAD = 27656202;
    public static final int AVATARS_LOADING_SCREEN_LOAD = 27656200;
    public static final int AVATARS_NUX_IMAGE_LOAD = 27656201;
    public static final int AVATARS_PAGINATION_LOAD = 27656198;
    public static final int AVATARS_PAGINATION_TTRC = 27656199;
    public static final int AVATARS_PREVIEW_IMAGE_LOAD = 27656196;
    public static final int AVATARS_STICKER_GENERATION_TTRC = 27656197;
    public static final int AVATAR_CATEGORY_LOAD = 27656195;
    public static final int AVATAR_EDITOR_CATEGORY_TTRC = 27656193;
    public static final int EDITOR_LOAD = 27656194;
    public static final short MODULE_ID = 422;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "AVATARS_AVATAR_EDITOR_CATEGORY_TTRC";
            case 2:
                return "AVATARS_EDITOR_LOAD";
            case 3:
                return "AVATARS_AVATAR_CATEGORY_LOAD";
            case 4:
                return "AVATARS_AVATARS_PREVIEW_IMAGE_LOAD";
            case 5:
                return "AVATARS_AVATARS_STICKER_GENERATION_TTRC";
            case 6:
                return "AVATARS_AVATARS_PAGINATION_LOAD";
            case 7:
                return "AVATARS_AVATARS_PAGINATION_TTRC";
            case 8:
                return "AVATARS_AVATARS_LOADING_SCREEN_LOAD";
            case 9:
                return "AVATARS_AVATARS_NUX_IMAGE_LOAD";
            case 10:
                return "AVATARS_AVATARS_AUTOGEN_LOAD";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
