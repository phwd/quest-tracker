package com.facebook.inject;

public class ApplicationScopeAwareInjectorAutoProvider extends AbstractComponentProvider<ApplicationScopeAwareInjector> {
    public void inject(ApplicationScopeAwareInjector applicationScopeAwareInjector) {
        ApplicationScopeAwareInjector._UL_staticInjectMe(this, applicationScopeAwareInjector);
    }

    public boolean equals(Object obj) {
        return obj instanceof ApplicationScopeAwareInjectorAutoProvider;
    }
}
