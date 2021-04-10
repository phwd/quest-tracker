package com.facebook.inject;

public class ContextScopeAwareInjectorAutoProvider extends AbstractComponentProvider<ContextScopeAwareInjector> {
    public void inject(ContextScopeAwareInjector contextScopeAwareInjector) {
        ContextScopeAwareInjector._UL_staticInjectMe(this, contextScopeAwareInjector);
    }

    public boolean equals(Object obj) {
        return obj instanceof ContextScopeAwareInjectorAutoProvider;
    }
}
