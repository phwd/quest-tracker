package com.facebook.inject;

import android.content.Context;
import javax.annotation.concurrent.GuardedBy;

public class ApplicationScopeAwareInjector extends BaseScopeAwareInjector {
    @GuardedBy("this")
    private boolean isInjected = false;
    private final ApplicationScope mApplicationScopeScope;
    private final FbInjector mDelegate;

    public ApplicationScopeAwareInjector(FbInjector fbInjector, ApplicationScope applicationScope) {
        super(fbInjector);
        this.mDelegate = fbInjector;
        this.mApplicationScopeScope = applicationScope;
    }

    @Override // com.facebook.inject.BasicScopeAwareInjector
    public Object enterPreamble() {
        return this.mApplicationScopeScope.enterScope();
    }

    @Override // com.facebook.inject.BasicScopeAwareInjector
    public void exitPostamble(Object obj) {
        this.mApplicationScopeScope.exitScope((InjectorThreadStack) obj);
    }

    @Override // com.facebook.inject.ScopeAwareInjector
    public Context getInjectorContext() {
        return this.mApplicationScopeScope.getContext();
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.inject.AbstractInjector, com.facebook.inject.DelegatingInjector, com.facebook.inject.FbInjector
    public <T> void injectComponent(Class<T> cls, T t) {
        throw new IllegalStateException("injectComponent should only be called on ContextScope");
    }
}
