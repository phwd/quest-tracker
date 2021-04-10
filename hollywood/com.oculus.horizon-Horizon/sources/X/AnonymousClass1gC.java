package X;

/* renamed from: X.1gC  reason: invalid class name */
public enum AnonymousClass1gC {
    NATIVE_WITH_FALLBACK(true, true),
    NATIVE_ONLY(true, false),
    WEB_ONLY(false, true);
    
    public final boolean allowsKatanaAuth;
    public final boolean allowsWebViewAuth;

    /* access modifiers changed from: public */
    AnonymousClass1gC(boolean z, boolean z2) {
        this.allowsKatanaAuth = z;
        this.allowsWebViewAuth = z2;
    }

    public boolean allowsKatanaAuth() {
        return this.allowsKatanaAuth;
    }

    public boolean allowsWebViewAuth() {
        return this.allowsWebViewAuth;
    }
}
