package com.oculus.http.core.base;

import com.google.common.base.Preconditions;

public class ApiRequest<T> {
    public transient ApiCallback<T> mCallback;

    public ApiCallback<T> getCallback() {
        boolean z = false;
        if (this.mCallback != null) {
            z = true;
        }
        Preconditions.checkArgument(z);
        return this.mCallback;
    }

    public void setCallback(ApiCallback<T> apiCallback) {
        this.mCallback = apiCallback;
    }
}
