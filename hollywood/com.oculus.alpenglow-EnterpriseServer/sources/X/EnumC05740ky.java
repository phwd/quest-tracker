package X;

/* renamed from: X.0ky  reason: invalid class name and case insensitive filesystem */
public enum EnumC05740ky {
    ANY,
    SCALAR,
    ARRAY,
    OBJECT,
    NUMBER,
    NUMBER_FLOAT,
    NUMBER_INT,
    STRING,
    BOOLEAN;

    public boolean isNumeric() {
        if (this == NUMBER || this == NUMBER_INT || this == NUMBER_FLOAT) {
            return true;
        }
        return false;
    }

    public boolean isStructured() {
        if (this == OBJECT || this == ARRAY) {
            return true;
        }
        return false;
    }
}
