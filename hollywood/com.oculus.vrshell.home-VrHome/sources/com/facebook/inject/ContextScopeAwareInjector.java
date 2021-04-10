package com.facebook.inject;

import android.content.Context;
import javax.annotation.concurrent.GuardedBy;

public class ContextScopeAwareInjector extends BaseScopeAwareInjector implements ScopeAwareInjector {
    @GuardedBy("this")
    private boolean isInjected = false;
    private final Context mContext;

    public ContextScopeAwareInjector(FbInjector delegate, Context context) {
        super(delegate);
        this.mContext = context;
    }

    public Context getInjectorContext() {
        return this.mContext;
    }

    @Override // com.facebook.inject.BasicScopeAwareInjector
    public InjectorThreadStack enterPreamble() {
        InjectorThreadStack injectorThreadStack = getInjectorThreadStack();
        injectorThreadStack.enterContext(getInjectorContext());
        injectorThreadStack.pushInjector(this);
        return injectorThreadStack;
    }

    @Override // com.facebook.inject.BasicScopeAwareInjector
    public void exitPostamble(Object token) {
        exitPostamble((InjectorThreadStack) token);
    }

    private void exitPostamble(InjectorThreadStack injectorThreadStack) {
        injectorThreadStack.popInjector();
        injectorThreadStack.exitContext();
    }
}
