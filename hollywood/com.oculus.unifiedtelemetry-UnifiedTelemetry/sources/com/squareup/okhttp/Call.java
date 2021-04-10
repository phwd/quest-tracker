package com.squareup.okhttp;

import X.AnonymousClass06;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.internal.Internal;
import com.squareup.okhttp.internal.NamedRunnable;
import com.squareup.okhttp.internal.http.HttpEngine;
import java.io.IOException;
import java.util.logging.Level;

public class Call {
    public volatile boolean canceled;
    public final OkHttpClient client;
    public HttpEngine engine;
    public boolean executed;
    public Request originalRequest;

    public class ApplicationInterceptorChain implements Interceptor.Chain {
        public final boolean forWebSocket;
        public final int index;
        public final Request request;

        @Override // com.squareup.okhttp.Interceptor.Chain
        public Connection connection() {
            return null;
        }

        public ApplicationInterceptorChain(int i, Request request2, boolean z) {
            this.index = i;
            this.request = request2;
            this.forWebSocket = z;
        }

        @Override // com.squareup.okhttp.Interceptor.Chain
        public Response proceed(Request request2) throws IOException {
            if (this.index >= Call.this.client.interceptors.size()) {
                return Call.this.getResponse(request2, this.forWebSocket);
            }
            ApplicationInterceptorChain applicationInterceptorChain = new ApplicationInterceptorChain(this.index + 1, request2, this.forWebSocket);
            Interceptor interceptor = Call.this.client.interceptors.get(this.index);
            Response intercept = interceptor.intercept(applicationInterceptorChain);
            if (intercept != null) {
                return intercept;
            }
            StringBuilder sb = new StringBuilder("application interceptor ");
            sb.append(interceptor);
            sb.append(" returned null");
            throw new NullPointerException(sb.toString());
        }

        @Override // com.squareup.okhttp.Interceptor.Chain
        public Request request() {
            return this.request;
        }
    }

    public final class AsyncCall extends NamedRunnable {
        public final boolean forWebSocket;
        public final Callback responseCallback;

        public void cancel() {
            Call.this.cancel();
        }

        @Override // com.squareup.okhttp.internal.NamedRunnable
        public void execute() {
            Request request;
            try {
                Response responseWithInterceptorChain = Call.this.getResponseWithInterceptorChain(this.forWebSocket);
                if (Call.this.canceled) {
                    try {
                        this.responseCallback.onFailure(Call.this.originalRequest, new IOException("Canceled"));
                    } catch (IOException e) {
                        Internal.logger.log(Level.INFO, AnonymousClass06.A04("Callback failure for ", Call.this.toLoggableString()), (Throwable) e);
                    } catch (Throwable th) {
                        Call.this.client.dispatcher.finished(this);
                        throw th;
                    }
                } else {
                    this.responseCallback.onResponse(responseWithInterceptorChain);
                }
            } catch (IOException e2) {
                Call call = Call.this;
                HttpEngine httpEngine = call.engine;
                if (httpEngine == null) {
                    request = call.originalRequest;
                } else {
                    request = httpEngine.userRequest;
                }
                this.responseCallback.onFailure(request, e2);
            }
            Call.this.client.dispatcher.finished(this);
        }

        public String host() {
            return Call.this.originalRequest.url.host;
        }

        public Request request() {
            return Call.this.originalRequest;
        }

        public Object tag() {
            return Call.this.originalRequest.tag;
        }

        public Call get() {
            return Call.this;
        }

        public AsyncCall(Callback callback, boolean z) {
            super("OkHttp %s", Call.this.originalRequest.url.toString());
            this.responseCallback = callback;
            this.forWebSocket = z;
        }
    }

    public void cancel() {
        this.canceled = true;
        HttpEngine httpEngine = this.engine;
        if (httpEngine != null) {
            httpEngine.cancel();
        }
    }

    public Response execute() throws IOException {
        synchronized (this) {
            if (!this.executed) {
                this.executed = true;
            } else {
                throw new IllegalStateException("Already Executed");
            }
        }
        try {
            this.client.dispatcher.executed(this);
            Response responseWithInterceptorChain = getResponseWithInterceptorChain(false);
            if (responseWithInterceptorChain != null) {
                return responseWithInterceptorChain;
            }
            throw new IOException("Canceled");
        } finally {
            this.client.dispatcher.finished(this);
        }
    }

    public synchronized boolean isExecuted() {
        return this.executed;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Response getResponseWithInterceptorChain(boolean z) throws IOException {
        Request request = this.originalRequest;
        return new ApplicationInterceptorChain(0, request, z).proceed(request);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String toLoggableString() {
        String str;
        if (this.canceled) {
            str = "canceled call";
        } else {
            str = "call";
        }
        HttpUrl resolve = this.originalRequest.url.resolve("/...");
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" to ");
        sb.append(resolve);
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        r15.engine = r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.squareup.okhttp.Response getResponse(com.squareup.okhttp.Request r16, boolean r17) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 239
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.Call.getResponse(com.squareup.okhttp.Request, boolean):com.squareup.okhttp.Response");
    }

    public Object tag() {
        return this.originalRequest.tag;
    }

    public Call(OkHttpClient okHttpClient, Request request) {
        this.client = okHttpClient.copyWithDefaults();
        this.originalRequest = request;
    }

    public boolean isCanceled() {
        return this.canceled;
    }

    public void enqueue(Callback callback) {
        enqueue(callback, false);
    }

    public void enqueue(Callback callback, boolean z) {
        synchronized (this) {
            if (!this.executed) {
                this.executed = true;
            } else {
                throw new IllegalStateException("Already Executed");
            }
        }
        this.client.dispatcher.enqueue(new AsyncCall(callback, z));
    }
}
