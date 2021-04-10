package com.oculus.horizon.api;

import bolts.Task;
import bolts.TaskCompletionSource;
import com.oculus.http.core.base.ApiCallback;
import com.oculus.http.core.base.ApiError;

public class ApiTaskCallback<T> extends ApiCallback<T> {
    private final TaskCompletionSource<T> mCompletionSource = new TaskCompletionSource<>();

    @Override // com.oculus.http.core.base.ApiCallback
    public void onResponse(T t) {
        this.mCompletionSource.setResult(t);
    }

    @Override // com.oculus.http.core.base.ApiCallback
    public void onError(ApiError apiError) {
        this.mCompletionSource.setError(apiError);
    }

    public Task<T> getTask() {
        return this.mCompletionSource.getTask();
    }
}
