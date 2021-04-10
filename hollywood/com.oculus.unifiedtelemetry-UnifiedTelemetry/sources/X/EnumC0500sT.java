package X;

/* renamed from: X.sT  reason: case insensitive filesystem */
public enum EnumC0500sT {
    EXPLICIT("explicit"),
    TIMEOUT("timeout"),
    SESSION_END("session_end"),
    RESTART("restart"),
    ACTIONS_FULL("actions_full");
    
    public String mType;

    /* access modifiers changed from: public */
    EnumC0500sT(String str) {
        this.mType = str;
    }

    public String tag() {
        return this.mType;
    }
}
