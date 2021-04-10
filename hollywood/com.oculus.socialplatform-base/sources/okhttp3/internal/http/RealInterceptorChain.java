package okhttp3.internal.http;

import java.io.IOException;
import java.util.List;
import okhttp3.Connection;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.connection.StreamAllocation;

public final class RealInterceptorChain implements Interceptor.Chain {
    public int calls;
    public final Connection connection;
    public final HttpCodec httpCodec;
    public final int index;
    public final List<Interceptor> interceptors;
    public final Request request;
    public final StreamAllocation streamAllocation;

    private boolean sameConnection(HttpUrl httpUrl) {
        if (!httpUrl.host.equals(this.connection.route().address.url.host) || httpUrl.port != this.connection.route().address.url.port) {
            return false;
        }
        return true;
    }

    public RealInterceptorChain(List<Interceptor> list, StreamAllocation streamAllocation2, HttpCodec httpCodec2, Connection connection2, int i, Request request2) {
        this.interceptors = list;
        this.connection = connection2;
        this.streamAllocation = streamAllocation2;
        this.httpCodec = httpCodec2;
        this.index = i;
        this.request = request2;
    }

    @Override // okhttp3.Interceptor.Chain
    public Connection connection() {
        return this.connection;
    }

    public HttpCodec httpStream() {
        return this.httpCodec;
    }

    @Override // okhttp3.Interceptor.Chain
    public Request request() {
        return this.request;
    }

    public StreamAllocation streamAllocation() {
        return this.streamAllocation;
    }

    @Override // okhttp3.Interceptor.Chain
    public Response proceed(Request request2) throws IOException {
        return proceed(request2, this.streamAllocation, this.httpCodec, this.connection);
    }

    public Response proceed(Request request2, StreamAllocation streamAllocation2, HttpCodec httpCodec2, Connection connection2) throws IOException {
        if (this.index < this.interceptors.size()) {
            this.calls++;
            if (this.httpCodec != null && !sameConnection(request2.url)) {
                StringBuilder sb = new StringBuilder();
                sb.append("network interceptor ");
                sb.append(this.interceptors.get(this.index - 1));
                sb.append(" must retain the same host and port");
                throw new IllegalStateException(sb.toString());
            } else if (this.httpCodec == null || this.calls <= 1) {
                RealInterceptorChain realInterceptorChain = new RealInterceptorChain(this.interceptors, streamAllocation2, httpCodec2, connection2, this.index + 1, request2);
                Interceptor interceptor = this.interceptors.get(this.index);
                Response intercept = interceptor.intercept(realInterceptorChain);
                if (httpCodec2 != null && this.index + 1 < this.interceptors.size() && realInterceptorChain.calls != 1) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("network interceptor ");
                    sb2.append(interceptor);
                    sb2.append(" must call proceed() exactly once");
                    throw new IllegalStateException(sb2.toString());
                } else if (intercept != null) {
                    return intercept;
                } else {
                    StringBuilder sb3 = new StringBuilder("interceptor ");
                    sb3.append(interceptor);
                    sb3.append(" returned null");
                    throw new NullPointerException(sb3.toString());
                }
            } else {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("network interceptor ");
                sb4.append(this.interceptors.get(this.index - 1));
                sb4.append(" must call proceed() exactly once");
                throw new IllegalStateException(sb4.toString());
            }
        } else {
            throw new AssertionError();
        }
    }
}
