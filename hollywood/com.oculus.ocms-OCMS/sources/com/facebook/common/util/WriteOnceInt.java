package com.facebook.common.util;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class WriteOnceInt {
    private boolean mIsSet;
    private int mValue;

    public WriteOnceInt() {
    }

    public WriteOnceInt(int i) {
        this.mIsSet = true;
        this.mValue = i;
    }

    public boolean isSet() {
        return this.mIsSet;
    }

    public int get() {
        if (this.mIsSet) {
            return this.mValue;
        }
        throw new IllegalStateException();
    }

    public void set(int i) {
        if (!this.mIsSet) {
            this.mValue = i;
            this.mIsSet = true;
            return;
        }
        throw new IllegalStateException();
    }

    public boolean trySet(int i) {
        if (this.mIsSet) {
            return false;
        }
        this.mValue = i;
        this.mIsSet = true;
        return true;
    }
}
