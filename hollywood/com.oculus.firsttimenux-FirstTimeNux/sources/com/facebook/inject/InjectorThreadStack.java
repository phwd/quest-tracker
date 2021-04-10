package com.facebook.inject;

import android.content.Context;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class InjectorThreadStack {
    private Context mAppContext;
    private final List<Context> mContextStack = Lists.newArrayList();
    private final List<ScopeAwareInjector> mScopeAwareInjectorStack = Lists.newArrayList();

    public InjectorThreadStack(Context appContext) {
        this.mAppContext = appContext;
    }

    public void setAppContext(Context appContext) {
        this.mAppContext = appContext;
    }

    public void enterContext(Context context) {
        this.mContextStack.add(context);
    }

    public void enterAppContext() {
        this.mContextStack.add(this.mAppContext);
    }

    public void exitContext() {
        Preconditions.checkState(!this.mContextStack.isEmpty());
        this.mContextStack.remove(this.mContextStack.size() - 1);
    }

    public void pushInjector(ScopeAwareInjector injector) {
        this.mScopeAwareInjectorStack.add(injector);
    }

    public void popInjector() {
        Preconditions.checkState(!this.mScopeAwareInjectorStack.isEmpty());
        this.mScopeAwareInjectorStack.remove(this.mScopeAwareInjectorStack.size() - 1);
    }

    public Context getContext() {
        return this.mContextStack.isEmpty() ? this.mAppContext : this.mContextStack.get(this.mContextStack.size() - 1);
    }

    public ScopeAwareInjector getScopeAwareInjector() {
        if (this.mScopeAwareInjectorStack.isEmpty()) {
            return null;
        }
        return this.mScopeAwareInjectorStack.get(this.mScopeAwareInjectorStack.size() - 1);
    }
}
