package com.facebook.inject;

import com.google.inject.Key;
import java.lang.annotation.Annotation;
import javax.inject.Provider;

public class Binding<T> {
    public static final byte FLAG_IS_DEFAULT_BINDING = 1;
    private Class mDeclaringModule;
    private byte mFlags;
    private Key<T> mKey;
    private Provider<? extends T> mOriginalProvider;
    private Provider<? extends T> mProvider;
    private Class<? extends Annotation> mScopeAnnotation;

    public Class getDeclaringModule() {
        return this.mDeclaringModule;
    }

    public void setModule(Class cls) {
        this.mDeclaringModule = cls;
    }

    public Key<T> getKey() {
        return this.mKey;
    }

    public void setKey(Key<T> key) {
        this.mKey = key;
    }

    public Provider<? extends T> getProvider() {
        return this.mProvider;
    }

    public void setProvider(Provider<? extends T> provider) {
        this.mProvider = provider;
    }

    public Class<? extends Annotation> getScope() {
        return this.mScopeAnnotation;
    }

    public void setScope(Class<? extends Annotation> cls) {
        this.mScopeAnnotation = cls;
    }

    public boolean isDefaultBinding() {
        return getFlag((byte) 1);
    }

    public void setDefaultBinding(boolean z) {
        setFlag((byte) 1, z);
    }

    private boolean getFlag(byte b) {
        return (this.mFlags & b) == b;
    }

    private void setFlag(byte b, boolean z) {
        if (z) {
            this.mFlags = (byte) (b | this.mFlags);
            return;
        }
        this.mFlags = (byte) ((b ^ -1) & this.mFlags);
    }

    public Provider<? extends T> getOriginalProvider() {
        return this.mOriginalProvider;
    }

    public void setOriginalProvider(Provider<? extends T> provider) {
        this.mOriginalProvider = provider;
    }

    public String toString() {
        return String.format("%s{declaringModuleName = %s, key = %s, provider = %s, scope = %s, default = %s}", getClass().getSimpleName(), this.mDeclaringModule.getCanonicalName(), this.mKey, this.mProvider, this.mScopeAnnotation, Boolean.valueOf(isDefaultBinding()));
    }
}
