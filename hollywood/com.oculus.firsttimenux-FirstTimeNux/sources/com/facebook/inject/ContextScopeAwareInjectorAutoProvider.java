package com.facebook.inject;

public class ContextScopeAwareInjectorAutoProvider extends AbstractComponentProvider<ContextScopeAwareInjector> {
    public void inject(ContextScopeAwareInjector instance) {
        ContextScopeAwareInjector.$ul_staticInjectMe(this, instance);
    }

    public boolean equals(Object o) {
        return o instanceof ContextScopeAwareInjectorAutoProvider;
    }
}
