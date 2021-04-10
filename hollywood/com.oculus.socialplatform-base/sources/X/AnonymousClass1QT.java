package X;

/* renamed from: X.1QT  reason: invalid class name */
public enum AnonymousClass1QT {
    UNKNOWN("Unknown"),
    NoNetwork("NoNetwork"),
    WIFI("Wifi"),
    MOBILE_2G("Cell_2G"),
    MOBILE_3G("Cell_3G"),
    MOBILE_4G("Cell_4G"),
    MOBILE_OTHER("Cell_other"),
    Other("Other");
    
    public final String name;

    public String toString() {
        return this.name;
    }

    /* access modifiers changed from: public */
    AnonymousClass1QT(String str) {
        this.name = str;
    }
}
