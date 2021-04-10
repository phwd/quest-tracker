package com.facebook.inject;

import com.facebook.ultralight.UL;

public class UltralightProvider<T> extends AbstractDefaultScopeProvider<T> {
    private final int mBindingId;

    private UltralightProvider(int i, InjectorLike injectorLike) {
        super(injectorLike);
        this.mBindingId = i;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.inject.AbstractDefaultScopeProvider
    public T onGetInstance(InjectorLike injectorLike) {
        try {
            return (T) UL.factorymap.get(this.mBindingId, injectorLike);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Invalid binding id %d", Integer.valueOf(this.mBindingId)), e);
        }
    }

    public static UltralightProvider get(int i, InjectorLike injectorLike) {
        return new UltralightProvider(i, injectorLike);
    }
}
