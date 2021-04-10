package com.facebook.inject;

import com.google.inject.Key;

public class ComponentBinding<T> {
    private Class mDeclaringModule;
    private Key<T> mKey;
    private ComponentProvider<T> mProvider;

    public void setModule(Class declaringModule) {
        this.mDeclaringModule = declaringModule;
    }

    public Key<T> getKey() {
        return this.mKey;
    }

    public void setKey(Key<T> key) {
        this.mKey = key;
    }

    public ComponentProvider<T> getProvider() {
        return this.mProvider;
    }

    public void setProvider(ComponentProvider<T> provider) {
        this.mProvider = provider;
    }

    public String toString() {
        return String.format("%s{declaringModuleName = %s, key = $s, provider = %s}", getClass().getSimpleName(), this.mDeclaringModule.getCanonicalName(), this.mKey, this.mProvider);
    }
}
