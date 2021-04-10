package com.oculus.common.httpclient;

import android.os.Build;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpClient {
    public static final long CACHE_SIZE = 104857600;
    private static final String TAG = LoggingUtil.tag(HttpClient.class);
    private static HttpClient sInstance;
    private static final Object sLock = new Object();
    private static long sRefCount;
    private OkHttpClient mClient;

    public enum CacheDirective {
        ALLOW_CACHE,
        FORCE_CACHE,
        FORCE_UPDATE
    }

    private native void nativeClose();

    private native void nativeInit();

    public static OkHttpClient getOkHttpCient(Interceptor interceptor, String str) {
        OkHttpClient.Builder certificatePinner = new OkHttpClient.Builder().certificatePinner(FbCertificatePinnerFactory.create(Build.TIME));
        if (interceptor != null) {
            certificatePinner.addInterceptor(interceptor);
        }
        try {
            Cache cache = new Cache(new File(str), CACHE_SIZE);
            cache.initialize();
            return certificatePinner.cache(cache).build();
        } catch (IOException e) {
            throw new RuntimeException("Invalid cache specified for network.", e);
        }
    }

    public static HttpClient getInstance(Interceptor interceptor, String str) {
        HttpClient httpClient;
        synchronized (sLock) {
            if (sRefCount == 0) {
                sInstance = new HttpClient(interceptor, str);
            }
            sRefCount++;
            httpClient = sInstance;
        }
        return httpClient;
    }

    public static String getOkHttpCacheDir(String str, String str2) {
        return str + "/okhttp/cache/" + str2;
    }

    public void release() {
        synchronized (sLock) {
            sRefCount--;
            if (sRefCount == 0) {
                close();
                sInstance = null;
            }
        }
    }

    private HttpClient(Interceptor interceptor, String str) {
        this.mClient = getOkHttpCient(interceptor, str);
        nativeInit();
    }

    private void close() {
        nativeClose();
    }

    public OkHttpClient getOkHttpClient() {
        return this.mClient;
    }

    public static class Transfer implements Closeable, Callback {
        private static final String TAG = LoggingUtil.tag(Transfer.class);
        private long mBytesRead = 0;
        private Call mCall;
        private boolean mComplete = false;
        private boolean mFailed = false;
        private final OkHttpClient mHttpClient;
        private final Request.Builder mRequestBuilder;
        private Response mResponse;

        public static class ReadResult {
            public byte[] buffer;
            public int bytesRead;
        }

        public Transfer(OkHttpClient okHttpClient, Request.Builder builder) {
            this.mHttpClient = okHttpClient;
            this.mRequestBuilder = builder;
        }

        public synchronized boolean start() {
            if (this.mCall == null) {
                this.mCall = this.mHttpClient.newCall(this.mRequestBuilder.build());
                this.mCall.enqueue(this);
                return true;
            }
            Log.e(TAG, "Call to method start after request has been started.");
            return false;
        }

        public synchronized boolean addHeader(String str, String str2) {
            if (this.mCall == null) {
                this.mRequestBuilder.addHeader(str, str2);
                return true;
            }
            Log.e(TAG, "Call to method addHeader after request has been started.");
            return false;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() {
            this.mFailed = true;
            if (this.mCall != null) {
                this.mCall.cancel();
                if (this.mResponse != null) {
                    this.mResponse.close();
                }
            }
        }

        public synchronized boolean isComplete() {
            return this.mComplete && !this.mFailed;
        }

        public synchronized ReadResult read() {
            ReadResult readResult = new ReadResult();
            if (!this.mFailed) {
                if (!this.mComplete) {
                    if (this.mResponse != null) {
                        if (this.mResponse.body() != null) {
                            long size = this.mResponse.body().source().buffer().size();
                            if (size > 2147483647L) {
                                size = 2147483647L;
                            }
                            try {
                                readResult.buffer = new byte[((int) size)];
                                readResult.bytesRead = this.mResponse.body().source().read(readResult.buffer);
                                this.mBytesRead += (long) readResult.bytesRead;
                                Log.d(TAG, String.format("new readResult, last buffer size %d, last bytes read %d, total bytes read %d", Integer.valueOf(readResult.buffer.length), Integer.valueOf(readResult.bytesRead), Long.valueOf(this.mBytesRead)));
                                if (readResult.bytesRead == -1) {
                                    this.mComplete = true;
                                }
                            } catch (IOException e) {
                                Log.w(TAG, e);
                                this.mFailed = true;
                                readResult.bytesRead = -1;
                            }
                            return readResult;
                        }
                    }
                    readResult.bytesRead = 0;
                    return readResult;
                }
            }
            readResult.bytesRead = -1;
            return readResult;
        }

        @Override // okhttp3.Callback
        public synchronized void onFailure(Call call, IOException iOException) {
            Log.w(TAG, String.format("onFailure: IOException: %s", iOException));
            this.mFailed = true;
        }

        @Override // okhttp3.Callback
        public synchronized void onResponse(Call call, Response response) throws IOException {
            this.mResponse = response;
            if (!this.mResponse.isSuccessful()) {
                this.mFailed = true;
            }
        }
    }

    public Transfer get(String str, CacheDirective cacheDirective) {
        Request.Builder url = new Request.Builder().url(str);
        if (cacheDirective == CacheDirective.FORCE_UPDATE) {
            url.cacheControl(CacheControl.FORCE_NETWORK);
        } else if (cacheDirective == CacheDirective.FORCE_CACHE) {
            url.cacheControl(CacheControl.FORCE_CACHE);
        }
        return new Transfer(this.mClient, url);
    }

    public Transfer get(String str, int i) {
        CacheDirective cacheDirective = CacheDirective.ALLOW_CACHE;
        if (i == 0) {
            cacheDirective = CacheDirective.ALLOW_CACHE;
        } else if (i == 1) {
            cacheDirective = CacheDirective.FORCE_CACHE;
        } else if (i == 2) {
            cacheDirective = CacheDirective.FORCE_UPDATE;
        }
        return get(str, cacheDirective);
    }
}
