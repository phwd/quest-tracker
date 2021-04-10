package com.facebook.inject;

import com.facebook.ultralight.UL;

public class UltralightLazy<T> extends AbstractDefaultScopeLazy<T> {
    private final int mBindingId;

    private UltralightLazy(int i, InjectorLike injectorLike) {
        super(injectorLike);
        this.mBindingId = i;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.inject.AbstractDefaultScopeLazy
    public T onGetInstance(InjectorLike injectorLike) {
        try {
            return (T) UL.factorymap.get(this.mBindingId, injectorLike);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Invalid binding id %d", Integer.valueOf(this.mBindingId)), e);
        }
    }

    public static UltralightLazy get(int i, InjectorLike injectorLike) {
        return new UltralightLazy(i, injectorLike);
    }
}
