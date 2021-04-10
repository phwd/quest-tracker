package X;

/* renamed from: X.Zm  reason: case insensitive filesystem */
public enum EnumC0456Zm {
    UNKNOWN,
    ANDROID_6DOF,
    ANDROID;

    public static EnumC0456Zm fromString(String str) {
        EnumC0456Zm[] values = values();
        for (EnumC0456Zm zm : values) {
            if (zm.name().equals(str)) {
                return zm;
            }
        }
        return UNKNOWN;
    }
}
