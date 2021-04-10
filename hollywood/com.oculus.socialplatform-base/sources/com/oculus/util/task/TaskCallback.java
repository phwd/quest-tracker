package com.oculus.util.task;

import bolts.Task;
import bolts.TaskCompletionSource;
import com.oculus.http.core.base.ApiError;
import com.oculus.http.core.base.ValidatableApiResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class TaskCallback<T> implements Callback<T> {
    public final TaskCompletionSource<T> mCompletionSource = new TaskCompletionSource<>();

    public static class Error extends Exception {
        public final ApiError apiError;

        public Error(ApiError apiError2) {
            this.apiError = apiError2;
        }
    }

    private void handleError(ApiError apiError) {
        this.mCompletionSource.setError(new Error(apiError));
    }

    private void onResponse(T t) {
        this.mCompletionSource.setResult(t);
    }

    @Override // retrofit.Callback
    public void failure(RetrofitError retrofitError) {
        handleError(new ApiError(retrofitError));
    }

    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: bolts.Task<TResult>, bolts.Task<T> */
    public Task<T> getTask() {
        return (Task<TResult>) this.mCompletionSource.task;
    }

    @Override // retrofit.Callback
    public void success(T t, Response response) {
        if (t instanceof ValidatableApiResponse) {
            try {
                t.validate();
            } catch (RuntimeException e) {
                handleError(ApiError.makeValidationError(e));
                return;
            }
        }
        onResponse(t);
    }
}
