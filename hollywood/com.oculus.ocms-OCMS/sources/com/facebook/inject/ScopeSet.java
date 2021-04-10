package com.facebook.inject;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class ScopeSet {
    public static final byte CONTEXT_SCOPE = 8;
    public static final byte SINGLETON_SCOPE = 1;
    public static final byte USER_SCOPE = 4;
    private static final ThreadLocal<ScopeSet> sScopeSetThreadLocal = new ThreadLocal<ScopeSet>() {
        /* class com.facebook.inject.ScopeSet.AnonymousClass1 */

        /* access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public ScopeSet initialValue() {
            return new ScopeSet();
        }
    };
    byte mScopes = 0;

    ScopeSet() {
    }

    public static ScopeSet get() {
        return sScopeSetThreadLocal.get();
    }

    @Deprecated
    public byte enterSingletonForStaticDI() {
        return enterScopes((byte) 1);
    }

    public boolean hasScope(byte b) {
        return (b & this.mScopes) != 0;
    }

    public byte getSeenScopes() {
        return this.mScopes;
    }

    public byte enterScopes(byte b) {
        byte b2 = this.mScopes;
        this.mScopes = (byte) (b | b2);
        return b2;
    }

    public void resetScopes(byte b) {
        this.mScopes = b;
    }

    public void assertNoInvalidScopesInSet(byte b, byte... bArr) {
        for (byte b2 : bArr) {
            if (hasScope(b2)) {
                throw new ProvisioningException("Scope violation. Should not call inject " + scopeToString(b) + " into " + scopeToString(b2));
            }
        }
    }

    private static String scopeToString(byte b) {
        if (b == 1) {
            return "ApplicationScoped";
        }
        if (b == 4) {
            return "UserScope";
        }
        if (b == 8) {
            return "ContextScope";
        }
        throw new IllegalArgumentException(String.format("Invalid scope value %s", Integer.toBinaryString(b)));
    }
}
