package com.facebook.quicklog.identifiers;

public class WhatsappCommerce {
    public static final int CATALOG_HOME_TTI = 673788184;
    public static final int CATALOG_PDP_TTI = 673782200;
    public static final short MODULE_ID = 10281;

    public static String getMarkerName(int i) {
        return i != 6584 ? i != 12568 ? "UNDEFINED_QPL_EVENT" : "WHATSAPP_COMMERCE_CATALOG_HOME_TTI" : "WHATSAPP_COMMERCE_CATALOG_PDP_TTI";
    }
}
