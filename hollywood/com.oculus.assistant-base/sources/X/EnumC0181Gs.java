package X;

/* renamed from: X.Gs  reason: case insensitive filesystem */
public enum EnumC0181Gs {
    VERBOSE(0),
    INFO(1),
    WARNING(2),
    ERROR(3),
    FATAL(4);
    
    public final int value;

    /* access modifiers changed from: public */
    EnumC0181Gs(int i) {
        this.value = i;
    }

    public static EnumC0181Gs fromValue(int i) {
        EnumC0181Gs[] values = values();
        for (EnumC0181Gs gs : values) {
            if (gs.value == i) {
                return gs;
            }
        }
        throw new IllegalArgumentException("Unsupported level!");
    }

    public int getValue() {
        return this.value;
    }
}
