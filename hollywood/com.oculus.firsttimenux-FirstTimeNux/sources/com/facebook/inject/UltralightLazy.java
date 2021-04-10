package com.facebook.inject;

import com.facebook.ultralight.UL;

public class UltralightLazy<T> extends AbstractDefaultScopeLazy<T> {
    private final int mBindingId;

    private UltralightLazy(int bindingId, InjectorLike injector) {
        super(injector);
        this.mBindingId = bindingId;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.inject.AbstractDefaultScopeLazy
    public T onGetInstance(InjectorLike injector) {
        try {
            return (T) UL.factorymap.get(this.mBindingId, injector);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Invalid binding id %d", Integer.valueOf(this.mBindingId)), e);
        }
    }

    public static UltralightLazy get(int bindingId, InjectorLike injector) {
        return new UltralightLazy(bindingId, injector);
    }
}
