package com.android.okhttp;

import com.android.okhttp.ConnectionSpec;

public class ConnectionSpecs {
    public static ConnectionSpec.Builder builder(boolean z) {
        return new ConnectionSpec.Builder(z);
    }
}
