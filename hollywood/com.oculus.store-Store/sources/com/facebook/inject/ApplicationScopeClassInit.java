package com.facebook.inject;

import java.util.ArrayDeque;
import javax.annotation.Nullable;

public final class ApplicationScopeClassInit {
    private static final ArrayDeque<ApplicationScopeClassInit> sPool = new ArrayDeque<>(32);
    @Nullable
    private Byte mEnterResult;
    @Nullable
    private InjectorThreadStack mInjectorThreadStack;
    @Nullable
    private ApplicationScope mScope;
    private ScopeSet mScopeSet;

    @Nullable
    public static ApplicationScopeClassInit start(@Nullable Object instance, @Nullable InjectorLike injector) {
        ApplicationScopeClassInit result;
        if (instance != null || injector == null) {
            return null;
        }
        synchronized (sPool) {
            result = sPool.pollFirst();
        }
        if (result == null) {
            result = new ApplicationScopeClassInit();
        }
        result.mScopeSet = ScopeSet.get();
        result.mEnterResult = Byte.valueOf(result.mScopeSet.enterSingletonForStaticDI());
        result.mScope = (ApplicationScope) injector.getScope(ApplicationScoped.class);
        result.mInjectorThreadStack = result.mScope.enterScope();
        return result;
    }

    public final void finish() {
        if (this.mInjectorThreadStack != null) {
            this.mScope.exitScope(this.mInjectorThreadStack);
            this.mInjectorThreadStack = null;
        }
        this.mScope = null;
        if (this.mEnterResult != null) {
            this.mScopeSet.resetScopes(this.mEnterResult.byteValue());
            this.mEnterResult = null;
        }
        this.mScopeSet = null;
        synchronized (sPool) {
            sPool.addFirst(this);
        }
    }
}
