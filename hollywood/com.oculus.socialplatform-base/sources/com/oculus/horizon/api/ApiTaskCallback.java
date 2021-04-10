package com.oculus.horizon.api;

import bolts.Task;
import bolts.TaskCompletionSource;
import com.oculus.http.core.base.ApiCallback;
import com.oculus.http.core.base.ApiError;

public class ApiTaskCallback<T> extends ApiCallback<T> {
    public final TaskCompletionSource<T> mCompletionSource = new TaskCompletionSource<>();

    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: bolts.Task<TResult>, bolts.Task<T> */
    public Task<T> getTask() {
        return (Task<TResult>) this.mCompletionSource.task;
    }

    @Override // com.oculus.http.core.base.ApiCallback
    public void onError(ApiError apiError) {
        this.mCompletionSource.setError(apiError);
    }

    @Override // com.oculus.http.core.base.ApiCallback
    public void onResponse(T t) {
        this.mCompletionSource.setResult(t);
    }
}
