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

    public boolean hasScope(byte scope) {
        return (this.mScopes & scope) != 0;
    }

    public byte getSeenScopes() {
        return this.mScopes;
    }

    public byte enterScopes(byte seenScopes) {
        byte ret = this.mScopes;
        this.mScopes = (byte) (this.mScopes | seenScopes);
        return ret;
    }

    public void resetScopes(byte enterSeenScopesResult) {
        this.mScopes = enterSeenScopesResult;
    }

    public void assertNoInvalidScopesInSet(byte currentScope, byte... scopesToCheck) {
        for (byte scope : scopesToCheck) {
            if (hasScope(scope)) {
                throw new ProvisioningException("Scope violation. Should not call inject " + scopeToString(currentScope) + " into " + scopeToString(scope));
            }
        }
    }

    private static String scopeToString(byte scope) {
        switch (scope) {
            case 1:
                return "ApplicationScoped";
            case 4:
                return "UserScope";
            case 8:
                return "ContextScope";
            default:
                throw new IllegalArgumentException(String.format("Invalid scope value %s", Integer.toBinaryString(scope)));
        }
    }
}
