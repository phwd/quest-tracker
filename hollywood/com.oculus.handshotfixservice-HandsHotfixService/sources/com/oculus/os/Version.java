package com.oculus.os;

import android.os.SystemProperties;

public class Version {
    public static final int CURRENT_SDK_VERSION = getIntProperty("ro.ovr.os.api.version");
    public static final int RELEASE_3_51 = 0;
    public static final int RELEASE_3_53 = 1;
    public static final int VERSION_0 = 0;
    public static final int VERSION_1 = 1;
    public static final int VERSION_10 = 10;
    public static final int VERSION_11 = 11;
    public static final int VERSION_12 = 12;
    public static final int VERSION_13 = 13;
    public static final int VERSION_14 = 14;
    public static final int VERSION_15 = 15;
    public static final int VERSION_16 = 16;
    public static final int VERSION_17 = 17;
    public static final int VERSION_18 = 18;
    public static final int VERSION_19 = 19;
    public static final int VERSION_2 = 2;
    public static final int VERSION_20 = 20;
    public static final int VERSION_21 = 21;
    public static final int VERSION_22 = 22;
    public static final int VERSION_23 = 23;
    public static final int VERSION_24 = 24;
    public static final int VERSION_25 = 25;
    public static final int VERSION_26 = 26;
    public static final int VERSION_27 = 27;
    public static final int VERSION_28 = 28;
    public static final int VERSION_29 = 29;
    public static final int VERSION_3 = 3;
    public static final int VERSION_30 = 30;
    public static final int VERSION_31 = 31;
    public static final int VERSION_32 = 32;
    public static final int VERSION_33 = 33;
    public static final int VERSION_34 = 34;
    public static final int VERSION_35 = 35;
    public static final int VERSION_36 = 36;
    public static final int VERSION_37 = 37;
    public static final int VERSION_38 = 38;
    public static final int VERSION_39 = 39;
    public static final int VERSION_4 = 4;
    public static final int VERSION_40 = 40;
    public static final int VERSION_41 = 41;
    public static final int VERSION_42 = 42;
    public static final int VERSION_43 = 43;
    public static final int VERSION_44 = 44;
    public static final int VERSION_45 = 45;
    public static final int VERSION_46 = 46;
    public static final int VERSION_47 = 47;
    public static final int VERSION_48 = 48;
    public static final int VERSION_49 = 49;
    public static final int VERSION_5 = 5;
    public static final int VERSION_50 = 50;
    public static final int VERSION_51 = 51;
    public static final int VERSION_52 = 52;
    public static final int VERSION_53 = 53;
    public static final int VERSION_54 = 54;
    public static final int VERSION_55 = 55;
    public static final int VERSION_57 = 57;
    public static final int VERSION_58 = 58;
    public static final int VERSION_59 = 59;
    public static final int VERSION_6 = 6;
    public static final int VERSION_60 = 60;
    public static final int VERSION_61 = 61;
    public static final int VERSION_62 = 62;
    public static final int VERSION_63 = 63;
    public static final int VERSION_64 = 64;
    public static final int VERSION_65 = 65;
    public static final int VERSION_66 = 66;
    public static final int VERSION_67 = 67;
    public static final int VERSION_68 = 68;
    public static final int VERSION_69 = 69;
    public static final int VERSION_7 = 7;
    public static final int VERSION_70 = 70;
    public static final int VERSION_71 = 71;
    public static final int VERSION_72 = 72;
    public static final int VERSION_8 = 8;
    public static final int VERSION_9 = 9;

    private static int getIntProperty(String property) {
        try {
            return Integer.parseInt(SystemProperties.get(property));
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
