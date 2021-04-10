package com.oculus.device;

import android.content.Context;
import android.util.Log;
import com.oculus.modules.DeviceEnvironmentModuleImpl;
import java.io.IOException;
import java.util.Map;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class APIInterceptor implements Interceptor {
    private static final String TAG = APIInterceptor.class.getSimpleName();
    private final Context mContext;

    public APIInterceptor(Context context) {
        this.mContext = context;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Log.d(TAG, "intercept original headers:");
        logHeaders(originalRequest.headers());
        Request.Builder builder = originalRequest.newBuilder();
        for (Map.Entry<String, String> entry : NetworkHeaders.getHeaderMap(this.mContext).entrySet()) {
            builder = builder.header(entry.getKey(), entry.getValue());
        }
        builder.url(originalRequest.url().newBuilder().addQueryParameter("forced_locale", DeviceEnvironmentModuleImpl.getDeviceLocale(this.mContext)).build());
        Request finalRequest = builder.build();
        Log.d(TAG, "intercept modified headers:");
        logHeaders(finalRequest.headers());
        return chain.proceed(finalRequest);
    }

    private static void logHeaders(Headers headers) {
        for (int i = 0; i < headers.size(); i++) {
            Log.d(TAG, headers.name(i) + ": " + headers.value(i));
        }
    }
}
