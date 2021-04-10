package X;

import com.facebook.internal.NativeProtocol;

/* renamed from: X.1Zb  reason: invalid class name */
public enum AnonymousClass1Zb {
    NONE(null),
    ONLY_ME(NativeProtocol.AUDIENCE_ME),
    FRIENDS(NativeProtocol.AUDIENCE_FRIENDS),
    EVERYONE(NativeProtocol.AUDIENCE_EVERYONE);
    
    public final String nativeProtocolAudience;

    /* access modifiers changed from: public */
    AnonymousClass1Zb(String str) {
        this.nativeProtocolAudience = str;
    }

    public String getNativeProtocolAudience() {
        return this.nativeProtocolAudience;
    }
}
