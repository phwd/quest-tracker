package com.facebook.quicklog.identifiers;

public class ComposerMediaTemplatePreview {
    public static final int CHIP_PROCESSING = 241569419;
    public static final int CLIENT_TTI = 241582073;
    public static final short MODULE_ID = 3686;
    public static final int PREVIEW_RESULT_CALLBACK = 241575915;
    public static final int PREVIEW_SET_CREATION = 241568984;

    public static String getMarkerName(int i) {
        return i != 3288 ? i != 3723 ? i != 10219 ? i != 16377 ? "UNDEFINED_QPL_EVENT" : "COMPOSER_MEDIA_TEMPLATE_PREVIEW_CLIENT_TTI" : "COMPOSER_MEDIA_TEMPLATE_PREVIEW_PREVIEW_RESULT_CALLBACK" : "COMPOSER_MEDIA_TEMPLATE_PREVIEW_CHIP_PROCESSING" : "COMPOSER_MEDIA_TEMPLATE_PREVIEW_PREVIEW_SET_CREATION";
    }
}
