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

    public InjectorThreadStack(Context context) {
        this.mAppContext = context;
    }

    public void enterContext(Context context) {
        this.mContextStack.add(context);
    }

    public void enterAppContext() {
        this.mContextStack.add(this.mAppContext);
    }

    public void exitContext() {
        Preconditions.checkState(!this.mContextStack.isEmpty());
        List<Context> list = this.mContextStack;
        list.remove(list.size() - 1);
    }

    public void pushInjector(ScopeAwareInjector scopeAwareInjector) {
        this.mScopeAwareInjectorStack.add(scopeAwareInjector);
    }

    public void popInjector() {
        Preconditions.checkState(!this.mScopeAwareInjectorStack.isEmpty());
        List<ScopeAwareInjector> list = this.mScopeAwareInjectorStack;
        list.remove(list.size() - 1);
    }

    public Context getContext() {
        if (this.mContextStack.isEmpty()) {
            return this.mAppContext;
        }
        List<Context> list = this.mContextStack;
        return list.get(list.size() - 1);
    }

    public ScopeAwareInjector getScopeAwareInjector() {
        if (this.mScopeAwareInjectorStack.isEmpty()) {
            return null;
        }
        List<ScopeAwareInjector> list = this.mScopeAwareInjectorStack;
        return list.get(list.size() - 1);
    }
}
