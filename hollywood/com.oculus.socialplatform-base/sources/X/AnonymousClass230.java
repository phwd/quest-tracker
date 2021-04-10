package X;

/* renamed from: X.230  reason: invalid class name */
public enum AnonymousClass230 implements AnonymousClass24F {
    DEFAULT(1),
    G2(2),
    G3(3),
    G4(4),
    G5(5),
    LTE(6),
    WIFI(7);
    
    public final int value;

    @Override // X.AnonymousClass24F
    public int getValue() {
        return this.value;
    }

    /* access modifiers changed from: public */
    AnonymousClass230(int i) {
        this.value = i;
    }

    public static AnonymousClass230 findByValue(int i) {
        switch (i) {
            case 1:
                return DEFAULT;
            case 2:
                return G2;
            case 3:
                return G3;
            case 4:
                return G4;
            case 5:
                return G5;
            case 6:
                return LTE;
            case 7:
                return WIFI;
            default:
                return null;
        }
    }
}
