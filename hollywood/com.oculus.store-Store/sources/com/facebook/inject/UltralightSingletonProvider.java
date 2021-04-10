package com.facebook.inject;

import com.facebook.ultralight.UL;

public class UltralightSingletonProvider<T> extends AbstractDefaultScopeProvider<T> implements Lazy<T> {
    private final int mBindingId;

    public UltralightSingletonProvider(int bindingId, InjectorLike injector) {
        super(injector);
        this.mBindingId = bindingId;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.inject.AbstractDefaultScopeProvider
    public T onGetInstance(InjectorLike injector) {
        try {
            return (T) UL.factorymap.get(this.mBindingId, injector);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Invalid binding id %d", Integer.valueOf(this.mBindingId)), e);
        }
    }

    public static UltralightSingletonProvider get(int bindingId, InjectorLike injector) {
        return new UltralightSingletonProvider(bindingId, injector);
    }
}
