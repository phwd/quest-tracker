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
    public static ApplicationScopeClassInit start(@Nullable Object obj, @Nullable InjectorLike injectorLike) {
        ApplicationScopeClassInit pollFirst;
        if (obj != null || injectorLike == null) {
            return null;
        }
        synchronized (sPool) {
            pollFirst = sPool.pollFirst();
        }
        if (pollFirst == null) {
            pollFirst = new ApplicationScopeClassInit();
        }
        pollFirst.mScopeSet = ScopeSet.get();
        pollFirst.mEnterResult = Byte.valueOf(pollFirst.mScopeSet.enterSingletonForStaticDI());
        pollFirst.mScope = (ApplicationScope) injectorLike.getScope(ApplicationScoped.class);
        pollFirst.mInjectorThreadStack = pollFirst.mScope.enterScope();
        return pollFirst;
    }

    public final void finish() {
        InjectorThreadStack injectorThreadStack = this.mInjectorThreadStack;
        if (injectorThreadStack != null) {
            this.mScope.exitScope(injectorThreadStack);
            this.mInjectorThreadStack = null;
        }
        this.mScope = null;
        Byte b = this.mEnterResult;
        if (b != null) {
            this.mScopeSet.resetScopes(b.byteValue());
            this.mEnterResult = null;
        }
        this.mScopeSet = null;
        synchronized (sPool) {
            sPool.addFirst(this);
        }
    }
}
