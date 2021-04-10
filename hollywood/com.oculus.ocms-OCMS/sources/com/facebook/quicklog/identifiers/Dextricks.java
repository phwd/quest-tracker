package com.facebook.quicklog.identifiers;

public class Dextricks {
    public static final int ADD_DEX_PATH = 8914508;
    public static final int CHECK_DIRTY = 8912901;
    public static final int DEXLIBLOADER_LOAD_MAIN = 8912897;
    public static final int DEXLIBLOADER_OBTAIN_RES_PROVIDER = 8912898;
    public static final int DEXSTORE_LOAD_ALL = 8912899;
    public static final int INIT_GC_HOOKS = 8912906;
    public static final int MANIFEST_LOAD = 8912900;
    public static final int MDCL_INSTALL = 8912904;
    public static final int MDCL_INSTALL_FIRST = 8912905;
    public static final short MODULE_ID = 136;
    public static final int REGEN_ALL = 8912903;
    public static final int REGEN_MISSING = 8912902;

    public static String getMarkerName(int i) {
        if (i == 1612) {
            return "DEXTRICKS_ADD_DEX_PATH";
        }
        switch (i) {
            case 1:
                return "DEXTRICKS_DEXLIBLOADER_LOAD_MAIN";
            case 2:
                return "DEXTRICKS_DEXLIBLOADER_OBTAIN_RES_PROVIDER";
            case 3:
                return "DEXTRICKS_DEXSTORE_LOAD_ALL";
            case 4:
                return "DEXTRICKS_MANIFEST_LOAD";
            case 5:
                return "DEXTRICKS_CHECK_DIRTY";
            case 6:
                return "DEXTRICKS_REGEN_MISSING";
            case 7:
                return "DEXTRICKS_REGEN_ALL";
            case 8:
                return "DEXTRICKS_MDCL_INSTALL";
            case 9:
                return "DEXTRICKS_MDCL_INSTALL_FIRST";
            case 10:
                return "DEXTRICKS_INIT_GC_HOOKS";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
