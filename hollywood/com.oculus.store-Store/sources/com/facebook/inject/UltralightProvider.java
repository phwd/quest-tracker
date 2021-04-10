package com.facebook.inject;

import com.facebook.ultralight.UL;

public class UltralightProvider<T> extends AbstractDefaultScopeProvider<T> {
    private final int mBindingId;

    private UltralightProvider(int bindingId, InjectorLike injector) {
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

    public static UltralightProvider get(int bindingId, InjectorLike injector) {
        return new UltralightProvider(bindingId, injector);
    }
}
