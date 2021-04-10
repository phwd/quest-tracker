package okhttp3.internal.http;

import okhttp3.Interceptor;

public final class CallServerInterceptor implements Interceptor {
    public final boolean forWebSocket;

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0035, code lost:
        if (r7 == null) goto L_0x0037;
     */
    @Override // okhttp3.Interceptor
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public okhttp3.Response intercept(okhttp3.Interceptor.Chain r10) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 225
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http.CallServerInterceptor.intercept(okhttp3.Interceptor$Chain):okhttp3.Response");
    }

    public CallServerInterceptor(boolean z) {
        this.forWebSocket = z;
    }
}
