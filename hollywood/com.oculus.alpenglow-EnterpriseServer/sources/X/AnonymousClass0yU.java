package X;

/* renamed from: X.0yU  reason: invalid class name */
public enum AnonymousClass0yU {
    UNKNOWN("Unknown"),
    NoNetwork("NoNetwork"),
    WIFI("Wifi"),
    MOBILE_2G("Cell_2G"),
    MOBILE_3G("Cell_3G"),
    MOBILE_4G("Cell_4G"),
    MOBILE_OTHER("Cell_other"),
    Other("Other");
    
    public final String name;

    /* access modifiers changed from: public */
    AnonymousClass0yU(String str) {
        this.name = str;
    }

    public String toString() {
        return this.name;
    }
}
