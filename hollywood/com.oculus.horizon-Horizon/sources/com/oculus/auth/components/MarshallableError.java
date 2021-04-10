package com.oculus.auth.components;

import android.annotation.SuppressLint;
import android.os.Bundle;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@SuppressLint({"CheckSerializableClass"})
@Immutable
public class MarshallableError extends Exception {
    public final int mCode;
    @Nullable
    public final String mTitle;

    public Bundle marshal() {
        Bundle bundle = new Bundle();
        bundle.putInt("error_code", this.mCode);
        String str = this.mTitle;
        if (str != null) {
            bundle.putString("error_title", str);
        }
        String message = getMessage();
        if (message != null) {
            bundle.putString("error_message", message);
        }
        return bundle;
    }

    public MarshallableError(int i) {
        this(i, null, null);
    }

    @SuppressLint({"Parameter Not Nullable"})
    public MarshallableError(int i, @Nullable String str, @Nullable String str2) {
        super(str2);
        this.mCode = i;
        this.mTitle = str;
    }

    public MarshallableError(int i, Throwable th) {
        super(th);
        this.mCode = i;
        this.mTitle = null;
    }
}
