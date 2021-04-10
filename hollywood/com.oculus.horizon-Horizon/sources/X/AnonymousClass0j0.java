package X;

/* renamed from: X.0j0  reason: invalid class name */
public enum AnonymousClass0j0 {
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
