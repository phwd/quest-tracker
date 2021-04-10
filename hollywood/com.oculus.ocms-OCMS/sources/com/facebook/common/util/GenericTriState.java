package com.facebook.common.util;

import javax.annotation.Nullable;

public class GenericTriState<T> {
    private boolean isSet;
    private T value;

    private GenericTriState() {
        this.isSet = false;
        this.value = null;
    }

    private GenericTriState(T t) {
        this.isSet = true;
        this.value = t;
    }

    public static <T> GenericTriState<T> empty() {
        return new GenericTriState<>();
    }

    public static <T> GenericTriState<T> of(T t) {
        return new GenericTriState<>(t);
    }

    public boolean isSet() {
        return this.isSet;
    }

    public void unset() {
        this.isSet = false;
        this.value = null;
    }

    public T value() {
        if (this.isSet) {
            return this.value;
        }
        throw new IllegalStateException("Must be set in order to get value");
    }

    @Nullable
    public T valueOrNull() {
        if (!this.isSet) {
            return null;
        }
        return this.value;
    }

    public void setValue(T t) {
        this.isSet = true;
        this.value = t;
    }
}
