package com.facebook.inject;

import com.google.inject.Key;
import javax.inject.Provider;

public class RedirectProvider<T> extends AbstractProvider<T> {
    private final Key<? extends T> mKey;
    private volatile Provider<? extends T> mProvider;

    public RedirectProvider(Key<? extends T> key) {
        this.mKey = key;
    }

    public Key<? extends T> getKey() {
        return this.mKey;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.facebook.inject.ScopeUnawareInjector */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // javax.inject.Provider
    public T get() {
        if (this.mProvider == null) {
            ScopeUnawareInjector scopeUnawareInjector = getScopeUnawareInjector();
            this.mProvider = scopeUnawareInjector.getProvider(this.mKey, scopeUnawareInjector.getInjectorThreadStack().getContext());
        }
        return (T) this.mProvider.get();
    }
}
