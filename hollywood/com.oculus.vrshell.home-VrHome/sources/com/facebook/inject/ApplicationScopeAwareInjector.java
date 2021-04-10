package com.facebook.inject;

public class ApplicationScopeAwareInjector extends BaseScopeAwareInjector implements ScopeAwareInjector {
    private final ApplicationScope mApplicationScopeScope;

    @Override // com.facebook.inject.BasicScopeAwareInjector
    public Object enterPreamble() {
        return this.mApplicationScopeScope.enterScope();
    }

    @Override // com.facebook.inject.BasicScopeAwareInjector
    public void exitPostamble(Object token) {
        this.mApplicationScopeScope.exitScope((InjectorThreadStack) token);
    }
}
