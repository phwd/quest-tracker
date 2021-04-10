package X;

public enum Zb {
    UNKNOWN(-1),
    APPS(0),
    CONCEPTS(1),
    EARLY_ACCESS(2),
    ENTERTAINMENT(3),
    ENVIRONMENTS(4),
    GALLERY(5),
    GAMES(6),
    INTERNAL(7),
    SYSTEM(8);
    
    public final int value;

    /* access modifiers changed from: public */
    Zb(int i) {
        this.value = i;
    }

    public static Zb fromString(String str) {
        Zb[] values = values();
        for (Zb zb : values) {
            if (zb.name().equals(str)) {
                return zb;
            }
        }
        return UNKNOWN;
    }
}
