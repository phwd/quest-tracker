package com.facebook.inject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class ApplicationScope implements Scope {
    private static final Map<Integer, Object> applicationScopedCache = Collections.synchronizedMap(new HashMap());
    private final FbInjector mInjector;
    private final ApplicationScopeAwareInjector mScopeAwareInjector;

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
}
