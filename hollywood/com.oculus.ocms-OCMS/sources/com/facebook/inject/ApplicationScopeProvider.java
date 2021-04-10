package com.facebook.inject;

import javax.inject.Provider;

public class ApplicationScopeProvider<T> extends AbstractProvider<T> implements Lazy<T> {
    private boolean mInitializedInstance;
    private T mInstance;
    private final ApplicationScope mScope;
    private final Provider<T> mUnscopedProvider;

    public ApplicationScopeProvider(ApplicationScope applicationScope, Provider<T> provider) {
        this.mScope = applicationScope;
        this.mUnscopedProvider = provider;
    }

    /* JADX INFO: finally extract failed */
    @Override // com.facebook.inject.Lazy, javax.inject.Provider
    public synchronized T get() {
        if (!this.mInitializedInstance) {
            ScopeSet scopeSet = ScopeSet.get();
            byte enterScopes = scopeSet.enterScopes((byte) 1);
            try {
                InjectorThreadStack enterScope = this.mScope.enterScope();
                try {
                    this.mInstance = this.mUnscopedProvider.get();
                    this.mInitializedInstance = true;
                    this.mScope.exitScope(enterScope);
                } catch (Throwable th) {
                    this.mScope.exitScope(enterScope);
                    throw th;
                }
            } finally {
                scopeSet.resetScopes(enterScopes);
            }
        }
        return this.mInstance;
    }
}
