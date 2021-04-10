package X;

/* renamed from: X.1Dp  reason: invalid class name */
public enum AnonymousClass1Dp {
    CONTROLLER_INITIALIZATION("controller_initialization"),
    APP_FOREGROUND("app_foreground"),
    LOGOUT("logout"),
    DECRYPTION_EXCEPTION("decryption_exception");
    
    public String mName;

    /* access modifiers changed from: public */
    AnonymousClass1Dp(String str) {
        this.mName = str;
    }

    public String getName() {
        return this.mName;
    }
}
