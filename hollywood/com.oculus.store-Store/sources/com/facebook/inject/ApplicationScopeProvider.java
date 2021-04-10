package com.facebook.inject;

import javax.inject.Provider;

public class ApplicationScopeProvider<T> extends AbstractProvider<T> implements Lazy<T> {
    private boolean mInitializedInstance;
    private T mInstance;
    private final ApplicationScope mScope;
    private final Provider<T> mUnscopedProvider;

    public ApplicationScopeProvider(ApplicationScope scope, Provider<T> unscopedProvider) {
        this.mScope = scope;
        this.mUnscopedProvider = unscopedProvider;
    }

    /* JADX INFO: finally extract failed */
    @Override // com.facebook.inject.Lazy, javax.inject.Provider
    public synchronized T get() {
        if (!this.mInitializedInstance) {
            ScopeSet scopeSet = ScopeSet.get();
            byte enterResult = scopeSet.enterScopes((byte) 1);
            try {
                InjectorThreadStack injectorThreadStack = this.mScope.enterScope();
                try {
                    this.mInstance = this.mUnscopedProvider.get();
                    this.mInitializedInstance = true;
                    this.mScope.exitScope(injectorThreadStack);
                } catch (Throwable th) {
                    this.mScope.exitScope(injectorThreadStack);
                    throw th;
                }
            } finally {
                scopeSet.resetScopes(enterResult);
            }
        }
        return this.mInstance;
    }
}
