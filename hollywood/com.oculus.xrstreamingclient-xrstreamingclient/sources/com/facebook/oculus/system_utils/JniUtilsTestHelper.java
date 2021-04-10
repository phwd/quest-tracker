package com.facebook.oculus.system_utils;

public class JniUtilsTestHelper {
    public static final boolean finalStaticBoolField = true;
    public static final byte finalStaticByteField = 12;
    public static final char finalStaticCharField = 300;
    public static final double finalStaticDoubleField = 0.9d;
    public static final float finalStaticFloatField = 0.5f;
    public static final int finalStaticIntField = 70000;
    public static final long finalStaticLongField = 4294967297L;
    public static final short finalStaticShortField = -300;
    public static final String finalStaticStringField = "abc";
    public static boolean staticBoolField = true;
    public static byte staticByteField = 13;
    public static char staticCharField = 301;
    public static double staticDoubleField = -0.9d;
    public static float staticFloatField = -0.5f;
    public static int staticIntField = -70000;
    public static long staticLongField = -4294967297L;
    public static short staticShortField = -301;
    public static String staticStringField = "def";
    public boolean boolField = true;
    public byte byteField = 14;
    public char charField = 302;
    public double doubleField = -0.8d;
    public float floatField = 1.3f;
    public int intField = -70001;
    public long longField = -4294967299L;
    public short shortField = -302;
    public String stringField = "xyz";

    public static int staticAdd(int i, int i2) {
        return i + i2;
    }

    public int add(int i, int i2) {
        return i + i2;
    }
}
