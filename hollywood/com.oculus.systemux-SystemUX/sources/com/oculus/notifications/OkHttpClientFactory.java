package com.oculus.notifications;

import android.content.Context;
import android.os.Build;
import com.oculus.certificatepinning.FbCertificatePinnerFactory;
import okhttp3.OkHttpClient;

public class OkHttpClientFactory {
    private OkHttpClient mInstance;

    public static OkHttpClientFactory getInstance(Context context) {
        return Loader.getInstance(context);
    }

    private OkHttpClientFactory(Context context) {
        this.mInstance = new OkHttpClient.Builder().addInterceptor(new UserAgentInterceptor(context)).certificatePinner(FbCertificatePinnerFactory.create(Build.TIME)).build();
    }

    public OkHttpClient getClient() {
        return this.mInstance;
    }

    private static class Loader {
        private static volatile OkHttpClientFactory sInstance;

        private Loader() {
        }

        /* access modifiers changed from: private */
        public static final OkHttpClientFactory getInstance(Context context) {
            if (sInstance == null) {
                sInstance = new OkHttpClientFactory(context);
            }
            return sInstance;
        }
    }
}
