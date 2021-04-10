package com.facebook.inject;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class ScopeSet {
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
}
