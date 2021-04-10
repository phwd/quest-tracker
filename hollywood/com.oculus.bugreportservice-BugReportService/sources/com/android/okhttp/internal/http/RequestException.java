package com.android.okhttp.internal.http;

import java.io.IOException;

public final class RequestException extends Exception {
    @Override // java.lang.Throwable
    public IOException getCause() {
        return (IOException) super.getCause();
    }
}
