package okhttp3.internal.connection;

import com.facebook.tigon.iface.TigonRequest;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.RealInterceptorChain;

public final class ConnectInterceptor implements Interceptor {
    public final OkHttpClient client;

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        RealInterceptorChain realInterceptorChain = (RealInterceptorChain) chain;
        Request request = realInterceptorChain.request();
        StreamAllocation streamAllocation = realInterceptorChain.streamAllocation;
        return realInterceptorChain.proceed(request, streamAllocation, streamAllocation.newStream(this.client, !request.method.equals(TigonRequest.GET)), streamAllocation.connection());
    }

    public ConnectInterceptor(OkHttpClient okHttpClient) {
        this.client = okHttpClient;
    }
}
