package com.oculus.horizoncontent.utils;

import androidx.annotation.Nullable;

public class AsyncQueryHandle {
    @Nullable
    AsyncQueryHandlerBase<?> mAsyncQueryHandler;
    @Nullable
    Object mCallback;

    public AsyncQueryHandle(AsyncQueryHandlerBase<?> asyncQueryHandlerBase, Object obj) {
        this.mAsyncQueryHandler = asyncQueryHandlerBase;
        this.mCallback = obj;
    }

    public void destroy() {
        AsyncQueryHandlerBase<?> asyncQueryHandlerBase = this.mAsyncQueryHandler;
        if (asyncQueryHandlerBase != null) {
            asyncQueryHandlerBase.destroy();
            this.mAsyncQueryHandler = null;
        }
        this.mCallback = null;
    }
}
