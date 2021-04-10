package com.facebook.inject;

import android.content.Context;
import javax.annotation.concurrent.GuardedBy;

public class ContextScopeAwareInjector extends BaseScopeAwareInjector {
    @GuardedBy("this")
    private boolean isInjected = false;
    private final Context mContext;

    public ContextScopeAwareInjector(FbInjector fbInjector, Context context) {
        super(fbInjector);
        this.mContext = context;
    }

    @Override // com.facebook.inject.ScopeAwareInjector
    public Context getInjectorContext() {
        return this.mContext;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.inject.AbstractInjector, com.facebook.inject.DelegatingInjector, com.facebook.inject.FbInjector
    public <T> void injectComponent(Class<T> cls, T t) {
        InjectorThreadStack enterPreamble = enterPreamble();
        try {
            this.mDelegate.injectComponent(cls, t);
        } finally {
            exitPostamble(enterPreamble);
        }
    }

    @Override // com.facebook.inject.BasicScopeAwareInjector
    public InjectorThreadStack enterPreamble() {
        InjectorThreadStack injectorThreadStack = getInjectorThreadStack();
        injectorThreadStack.enterContext(getInjectorContext());
        injectorThreadStack.pushInjector(this);
        return injectorThreadStack;
    }

    @Override // com.facebook.inject.BasicScopeAwareInjector
    public void exitPostamble(Object obj) {
        exitPostamble((InjectorThreadStack) obj);
    }

    private void exitPostamble(InjectorThreadStack injectorThreadStack) {
        injectorThreadStack.popInjector();
        injectorThreadStack.exitContext();
    }
}
