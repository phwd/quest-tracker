package com.facebook.quicklog.identifiers;

public class WhatsappTest {
    public static final short MODULE_ID = 1440;
    public static final int WHATSAPP_TEST_EVENT = 94374812;
    public static final int WHATSAPP_USER_EVENT = 94379032;

    public static String getMarkerName(int i) {
        return i != 2972 ? i != 7192 ? "UNDEFINED_QPL_EVENT" : "WHATSAPP_TEST_WHATSAPP_USER_EVENT" : "WHATSAPP_TEST_WHATSAPP_TEST_EVENT";
    }
}
