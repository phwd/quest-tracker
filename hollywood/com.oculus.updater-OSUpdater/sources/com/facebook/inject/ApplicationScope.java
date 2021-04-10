package com.facebook.inject;

import android.content.Context;
import com.google.inject.Key;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Provider;

@ApplicationScoped
public class ApplicationScope implements Scope {
    private static final Map<Integer, Object> applicationScopedCache = Collections.synchronizedMap(new HashMap());
    private final Context mAppContext;
    private final FbInjector mInjector;
    private final ApplicationScopeAwareInjector mScopeAwareInjector = new ApplicationScopeAwareInjector(this.mInjector, this);

    public ApplicationScope(FbInjector fbInjector) {
        this.mInjector = fbInjector;
        this.mAppContext = fbInjector.getInjectorThreadStack().getContext();
    }

    @Override // com.facebook.inject.Scope
    public <T> Provider<T> scope(Key<T> key, Provider<T> provider) {
        return new ApplicationScopeProvider(this, provider);
    }

    public InjectorThreadStack enterScope() {
        InjectorThreadStack injectorThreadStack = this.mInjector.getInjectorThreadStack();
        injectorThreadStack.enterAppContext();
        injectorThreadStack.pushInjector(this.mScopeAwareInjector);
        return injectorThreadStack;
    }

    public void exitScope(InjectorThreadStack injectorThreadStack) {
        injectorThreadStack.exitContext();
        injectorThreadStack.popInjector();
    }

    public Context getContext() {
        return this.mAppContext;
    }
}
