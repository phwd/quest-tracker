package com.facebook.quicklog.identifiers;

public class Superpack {
    public static final int CREATE_SECONDAY_DEX_ARCHIVE = 34603009;
    public static final int FB_SO_LOADER_COMPRESSION_TOTAL = 34603021;
    public static final int FB_SO_LOADER_SUPERPACK_XZ_TOTAL = 34603019;
    public static final int FB_SO_LOADER_SUPERPACK_ZSTD_TOTAL = 34603020;
    public static final short MODULE_ID = 528;
    public static final int NEXT_SECONDARY_DEX = 34603010;
    public static final int SUPERPACK_CREATE_SECONDAY_DEX_ARCHIVE = 34603015;
    public static final int SUPERPACK_NEXT_SECONDARY_DEX = 34603016;
    public static final int SUPERPACK_TOTAL_SECONDARY_DEX_UNPACKING = 34603017;
    public static final int SUPERPACK_TOTAL_SECONDARY_DEX_XZS_UNPACKING = 34603018;
    public static final int TOTAL_SECONDARY_DEX_UNPACKING = 34603011;
    public static final int TOTAL_SECONDARY_DEX_XZS_UNPACKING = 34603012;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "SUPERPACK_CREATE_SECONDAY_DEX_ARCHIVE";
            case 2:
                return "SUPERPACK_NEXT_SECONDARY_DEX";
            case 3:
                return "SUPERPACK_TOTAL_SECONDARY_DEX_UNPACKING";
            case 4:
                return "SUPERPACK_TOTAL_SECONDARY_DEX_XZS_UNPACKING";
            case 5:
            case 6:
            default:
                return "UNDEFINED_QPL_EVENT";
            case 7:
                return "SUPERPACK_SUPERPACK_CREATE_SECONDAY_DEX_ARCHIVE";
            case 8:
                return "SUPERPACK_SUPERPACK_NEXT_SECONDARY_DEX";
            case 9:
                return "SUPERPACK_SUPERPACK_TOTAL_SECONDARY_DEX_UNPACKING";
            case 10:
                return "SUPERPACK_SUPERPACK_TOTAL_SECONDARY_DEX_XZS_UNPACKING";
            case 11:
                return "SUPERPACK_FB_SO_LOADER_SUPERPACK_XZ_TOTAL";
            case 12:
                return "SUPERPACK_FB_SO_LOADER_SUPERPACK_ZSTD_TOTAL";
            case 13:
                return "SUPERPACK_FB_SO_LOADER_COMPRESSION_TOTAL";
        }
    }
}
