package com.oculus.util.task;

import X.AnonymousClass0DD;
import com.oculus.http.core.base.ApiError;
import com.oculus.http.core.base.ValidatableApiResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class TaskCallback<T> implements Callback<T> {
    public final AnonymousClass0DD<T> mCompletionSource = new AnonymousClass0DD<>();

    public static class Error extends Exception {
        public final ApiError apiError;

        public Error(ApiError apiError2) {
            this.apiError = apiError2;
        }
    }

    @Override // retrofit.Callback
    public final void failure(RetrofitError retrofitError) {
        this.mCompletionSource.A01(new Error(new ApiError(retrofitError)));
    }

    @Override // retrofit.Callback
    public final void success(T t, Response response) {
        if (t instanceof ValidatableApiResponse) {
            try {
                t.validate();
            } catch (RuntimeException e) {
                this.mCompletionSource.A01(new Error(new ApiError(ApiError.Type.VALIDATIION_ERROR, e)));
                return;
            }
        }
        this.mCompletionSource.A02(t);
    }
}
