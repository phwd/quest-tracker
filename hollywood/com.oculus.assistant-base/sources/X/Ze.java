package X;

public enum Ze {
    UNKNOWN,
    REQUIRE_3DOF,
    ALLOW_6DOF,
    REQUIRE_6DOF;

    public static Ze fromString(String str) {
        Ze[] values = values();
        for (Ze ze : values) {
            if (ze.name().equals(str)) {
                return ze;
            }
        }
        return UNKNOWN;
    }
}
