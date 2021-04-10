package okhttp3;

import X.AnonymousClass006;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.internal.NamedRunnable;
import okhttp3.internal.cache.CacheInterceptor;
import okhttp3.internal.connection.ConnectInterceptor;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.BridgeInterceptor;
import okhttp3.internal.http.CallServerInterceptor;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http.RetryAndFollowUpInterceptor;
import okhttp3.internal.platform.Platform;

public final class RealCall implements Call {
    public final OkHttpClient client;
    public boolean executed;
    public final boolean forWebSocket;
    public final Request originalRequest;
    public final RetryAndFollowUpInterceptor retryAndFollowUpInterceptor;

    public final class AsyncCall extends NamedRunnable {
        public final Callback responseCallback;

        public AsyncCall(Callback callback) {
            super("OkHttp %s", RealCall.this.redactedUrl());
            this.responseCallback = callback;
        }

        @Override // okhttp3.internal.NamedRunnable
        public void execute() {
            try {
                Response responseWithInterceptorChain = RealCall.this.getResponseWithInterceptorChain();
                if (RealCall.this.retryAndFollowUpInterceptor.canceled) {
                    try {
                        this.responseCallback.onFailure(RealCall.this, new IOException("Canceled"));
                    } catch (IOException e) {
                        Platform.PLATFORM.log(4, AnonymousClass006.A07("Callback failure for ", RealCall.this.toLoggableString()), e);
                    } catch (Throwable th) {
                        RealCall.this.client.dispatcher.finished(this);
                        throw th;
                    }
                } else {
                    this.responseCallback.onResponse(RealCall.this, responseWithInterceptorChain);
                }
            } catch (IOException e2) {
                this.responseCallback.onFailure(RealCall.this, e2);
            }
            RealCall.this.client.dispatcher.finished(this);
        }

        public String host() {
            return RealCall.this.originalRequest.url.host;
        }

        public Request request() {
            return RealCall.this.originalRequest;
        }

        public RealCall get() {
            return RealCall.this;
        }
    }

    @Override // okhttp3.Call
    public void enqueue(Callback callback) {
        synchronized (this) {
            if (!this.executed) {
                this.executed = true;
            } else {
                throw new IllegalStateException("Already Executed");
            }
        }
        captureCallStackTrace();
        this.client.dispatcher.enqueue(new AsyncCall(callback));
    }

    @Override // okhttp3.Call
    public Response execute() throws IOException {
        synchronized (this) {
            if (!this.executed) {
                this.executed = true;
            } else {
                throw new IllegalStateException("Already Executed");
            }
        }
        captureCallStackTrace();
        try {
            this.client.dispatcher.executed(this);
            Response responseWithInterceptorChain = getResponseWithInterceptorChain();
            if (responseWithInterceptorChain != null) {
                return responseWithInterceptorChain;
            }
            throw new IOException("Canceled");
        } finally {
            this.client.dispatcher.finished(this);
        }
    }

    @Override // okhttp3.Call
    public synchronized boolean isExecuted() {
        return this.executed;
    }

    private void captureCallStackTrace() {
        this.retryAndFollowUpInterceptor.callStackTrace = Platform.PLATFORM.getStackTraceForCloseable("response.body().close()");
    }

    @Override // okhttp3.Call
    public void cancel() {
        this.retryAndFollowUpInterceptor.cancel();
    }

    public Response getResponseWithInterceptorChain() throws IOException {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.client.interceptors);
        arrayList.add(this.retryAndFollowUpInterceptor);
        OkHttpClient okHttpClient = this.client;
        arrayList.add(new BridgeInterceptor(okHttpClient.cookieJar));
        arrayList.add(new CacheInterceptor(okHttpClient.internalCache()));
        arrayList.add(new ConnectInterceptor(okHttpClient));
        if (!this.forWebSocket) {
            arrayList.addAll(okHttpClient.networkInterceptors);
        }
        arrayList.add(new CallServerInterceptor(this.forWebSocket));
        Request request = this.originalRequest;
        return new RealInterceptorChain(arrayList, null, null, null, 0, request).proceed(request);
    }

    @Override // okhttp3.Call
    public boolean isCanceled() {
        return this.retryAndFollowUpInterceptor.canceled;
    }

    public String redactedUrl() {
        return this.originalRequest.url.redact();
    }

    public StreamAllocation streamAllocation() {
        return this.retryAndFollowUpInterceptor.streamAllocation;
    }

    public RealCall(OkHttpClient okHttpClient, Request request, boolean z) {
        this.client = okHttpClient;
        this.originalRequest = request;
        this.forWebSocket = z;
        this.retryAndFollowUpInterceptor = new RetryAndFollowUpInterceptor(okHttpClient, z);
    }

    @Override // okhttp3.Call
    public Request request() {
        return this.originalRequest;
    }

    public String toLoggableString() {
        String str;
        String str2;
        if (isCanceled()) {
            str = "canceled ";
        } else {
            str = "";
        }
        if (this.forWebSocket) {
            str2 = "web socket";
        } else {
            str2 = "call";
        }
        return AnonymousClass006.A0B(str, str2, " to ", redactedUrl());
    }

    @Override // okhttp3.Call, java.lang.Object
    public RealCall clone() {
        return new RealCall(this.client, this.originalRequest, this.forWebSocket);
    }
}
