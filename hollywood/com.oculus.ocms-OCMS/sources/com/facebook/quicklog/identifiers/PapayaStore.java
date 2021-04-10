package com.facebook.quicklog.identifiers;

public class PapayaStore {
    public static final int ERASE = 740626293;
    public static final int INIT = 740626238;
    public static final short MODULE_ID = 11301;
    public static final int READ = 740629903;
    public static final int REGISTER_PROPERTY = 740627525;
    public static final int REGISTER_RECORD = 740635009;
    public static final int WRITE = 740625832;

    public static String getMarkerName(int i) {
        return i != 3496 ? i != 3902 ? i != 3957 ? i != 5189 ? i != 7567 ? i != 12673 ? "UNDEFINED_QPL_EVENT" : "PAPAYA_STORE_REGISTER_RECORD" : "PAPAYA_STORE_READ" : "PAPAYA_STORE_REGISTER_PROPERTY" : "PAPAYA_STORE_ERASE" : "PAPAYA_STORE_INIT" : "PAPAYA_STORE_WRITE";
    }
}
