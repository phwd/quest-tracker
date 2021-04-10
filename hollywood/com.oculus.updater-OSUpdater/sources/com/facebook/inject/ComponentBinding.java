package com.facebook.inject;

import com.google.inject.Key;

public class ComponentBinding<T> {
    private Class mDeclaringModule;
    private Key<T> mKey;
    private ComponentProvider<T> mProvider;

    public Key<T> getKey() {
        return this.mKey;
    }

    public ComponentProvider<T> getProvider() {
        return this.mProvider;
    }

    public String toString() {
        return String.format("%s{declaringModuleName = %s, key = $s, provider = %s}", getClass().getSimpleName(), this.mDeclaringModule.getCanonicalName(), this.mKey, this.mProvider);
    }
}
