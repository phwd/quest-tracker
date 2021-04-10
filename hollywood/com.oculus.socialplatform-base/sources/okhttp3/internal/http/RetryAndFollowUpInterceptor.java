package okhttp3.internal.http;

import X.AnonymousClass006;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.HttpRetryException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.Address;
import okhttp3.CertificatePinner;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http2.ConnectionShutdownException;

public final class RetryAndFollowUpInterceptor implements Interceptor {
    public static final int MAX_FOLLOW_UPS = 20;
    public Object callStackTrace;
    public volatile boolean canceled;
    public final OkHttpClient client;
    public final boolean forWebSocket;
    public StreamAllocation streamAllocation;

    public void cancel() {
        this.canceled = true;
        StreamAllocation streamAllocation2 = this.streamAllocation;
        if (streamAllocation2 != null) {
            streamAllocation2.cancel();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00c9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private okhttp3.Request followUpRequest(okhttp3.Response r8) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 242
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http.RetryAndFollowUpInterceptor.followUpRequest(okhttp3.Response):okhttp3.Request");
    }

    private boolean isRecoverable(IOException iOException, boolean z) {
        if (iOException instanceof ProtocolException) {
            return false;
        }
        if (iOException instanceof InterruptedIOException) {
            if (!(iOException instanceof SocketTimeoutException) || z) {
                return false;
            }
            return true;
        } else if ((!(iOException instanceof SSLHandshakeException) || !(iOException.getCause() instanceof CertificateException)) && !(iOException instanceof SSLPeerUnverifiedException)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean recover(IOException iOException, boolean z, Request request) {
        this.streamAllocation.streamFailed(iOException);
        if (!this.client.retryOnConnectionFailure || ((z && (request.body instanceof UnrepeatableRequestBody)) || !isRecoverable(iOException, z) || !this.streamAllocation.hasMoreRoutes())) {
            return false;
        }
        return true;
    }

    private boolean sameConnection(Response response, HttpUrl httpUrl) {
        HttpUrl httpUrl2 = response.request.url;
        if (!httpUrl2.host.equals(httpUrl.host) || httpUrl2.port != httpUrl.port || !httpUrl2.scheme.equals(httpUrl.scheme)) {
            return false;
        }
        return true;
    }

    public RetryAndFollowUpInterceptor(OkHttpClient okHttpClient, boolean z) {
        this.client = okHttpClient;
        this.forWebSocket = z;
    }

    private Address createAddress(HttpUrl httpUrl) {
        HostnameVerifier hostnameVerifier;
        CertificatePinner certificatePinner;
        SSLSocketFactory sSLSocketFactory = null;
        if (httpUrl.isHttps()) {
            OkHttpClient okHttpClient = this.client;
            sSLSocketFactory = okHttpClient.sslSocketFactory;
            hostnameVerifier = okHttpClient.hostnameVerifier;
            certificatePinner = okHttpClient.certificatePinner;
        } else {
            hostnameVerifier = null;
            certificatePinner = null;
        }
        String str = httpUrl.host;
        int i = httpUrl.port;
        OkHttpClient okHttpClient2 = this.client;
        return new Address(str, i, okHttpClient2.dns, okHttpClient2.socketFactory, sSLSocketFactory, hostnameVerifier, certificatePinner, okHttpClient2.proxyAuthenticator, okHttpClient2.proxy, okHttpClient2.protocols, okHttpClient2.connectionSpecs, okHttpClient2.proxySelector);
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        this.streamAllocation = new StreamAllocation(this.client.connectionPool, createAddress(request.url), this.callStackTrace);
        Response response = null;
        int i = 0;
        while (!this.canceled) {
            try {
                Response proceed = ((RealInterceptorChain) chain).proceed(request, this.streamAllocation, null, null);
                if (response != null) {
                    Response.Builder newBuilder = proceed.newBuilder();
                    Response.Builder newBuilder2 = response.newBuilder();
                    newBuilder2.body = null;
                    newBuilder.priorResponse(newBuilder2.build());
                    proceed = newBuilder.build();
                }
                response = proceed;
                request = followUpRequest(proceed);
                if (request == null) {
                    if (!this.forWebSocket) {
                        this.streamAllocation.release();
                    }
                    return response;
                }
                Util.closeQuietly(proceed.body);
                i++;
                if (i > 20) {
                    this.streamAllocation.release();
                    throw new ProtocolException(AnonymousClass006.A03("Too many follow-up requests: ", i));
                } else if (request.body instanceof UnrepeatableRequestBody) {
                    this.streamAllocation.release();
                    throw new HttpRetryException("Cannot retry streamed HTTP body", response.code);
                } else if (!sameConnection(response, request.url)) {
                    this.streamAllocation.release();
                    this.streamAllocation = new StreamAllocation(this.client.connectionPool, createAddress(request.url), this.callStackTrace);
                } else if (this.streamAllocation.codec() != null) {
                    StringBuilder sb = new StringBuilder("Closing the body of ");
                    sb.append(response);
                    sb.append(" didn't close its backing stream. Bad interceptor?");
                    throw new IllegalStateException(sb.toString());
                }
            } catch (RouteException e) {
                if (!recover(e.lastException, false, request)) {
                    throw e.lastException;
                }
            } catch (IOException e2) {
                boolean z = false;
                if (!(e2 instanceof ConnectionShutdownException)) {
                    z = true;
                }
                if (!recover(e2, z, request)) {
                    throw e2;
                }
            } catch (Throwable th) {
                this.streamAllocation.streamFailed(null);
                this.streamAllocation.release();
                throw th;
            }
        }
        this.streamAllocation.release();
        throw new IOException("Canceled");
    }

    public boolean isCanceled() {
        return this.canceled;
    }

    public StreamAllocation streamAllocation() {
        return this.streamAllocation;
    }

    public void setCallStackTrace(Object obj) {
        this.callStackTrace = obj;
    }
}
