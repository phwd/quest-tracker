package com.facebook.common.util;

public final class WriteOnce<T> {
    private boolean mIsSet;
    private T mValue;

    public WriteOnce() {
    }

    public WriteOnce(T t) {
        this.mIsSet = true;
        this.mValue = t;
    }

    public boolean isSet() {
        return this.mIsSet;
    }

    public T get() {
        if (this.mIsSet) {
            return this.mValue;
        }
        throw new IllegalStateException();
    }

    public void set(T t) {
        if (!this.mIsSet) {
            this.mValue = t;
            this.mIsSet = true;
            return;
        }
        throw new IllegalStateException();
    }

    public boolean trySet(T t) {
        if (this.mIsSet) {
            return false;
        }
        this.mValue = t;
        this.mIsSet = true;
        return true;
    }
}
