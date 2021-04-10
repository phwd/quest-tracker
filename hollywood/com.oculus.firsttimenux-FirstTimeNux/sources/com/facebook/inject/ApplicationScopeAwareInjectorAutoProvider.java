package com.facebook.inject;

public class ApplicationScopeAwareInjectorAutoProvider extends AbstractComponentProvider<ApplicationScopeAwareInjector> {
    public void inject(ApplicationScopeAwareInjector instance) {
        ApplicationScopeAwareInjector.$ul_staticInjectMe(this, instance);
    }

    public boolean equals(Object o) {
        return o instanceof ApplicationScopeAwareInjectorAutoProvider;
    }
}
