package com.oculus.http.core.base;

import com.google.common.base.Preconditions;

public class ApiRequest<T> {
    private transient ApiCallback<T> mCallback;

    public void setCallback(ApiCallback<T> apiCallback) {
        this.mCallback = apiCallback;
    }

    public ApiCallback<T> getCallback() {
        Preconditions.checkArgument(this.mCallback != null);
        return this.mCallback;
    }
}
