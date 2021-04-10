package com.facebook.tigon.iface;

import android.text.TextUtils;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.tigon.iface.TigonRequestLayers;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

@DoNotStrip
public class TigonRequestBuilder {
    private long mAddedToMiddlewareSinceEpochMS;
    private long mConnectionTimeoutMS;
    private long mExpectedResponseSizeBytes;
    private boolean mFallbackToBackupHost;
    Map<String, String> mHeaders;
    HttpPriority mHttpPriority;
    private long mIdleTimeoutMS;
    private boolean mIsReliableMediaEnabled;
    Map<TigonRequestLayers.LayerInfo<?>, Object> mLayerInformation;
    private String mLoggingId;
    String mMethod;
    private long mRequestTimeoutMS;
    private int mRequestType;
    boolean mRetryable;
    private long mSoftDeadlineMs;
    private int mStartupStatusOnAdded;
    int mTigonPriority;
    String mUrl;

    public TigonRequestBuilder() {
        this.mRetryable = true;
        this.mSoftDeadlineMs = -1;
        this.mExpectedResponseSizeBytes = -1;
        this.mFallbackToBackupHost = false;
        this.mLoggingId = "";
        this.mStartupStatusOnAdded = -1;
        this.mAddedToMiddlewareSinceEpochMS = -1;
        this.mIsReliableMediaEnabled = false;
        this.mHeaders = new HashMap();
        this.mTigonPriority = 1;
        this.mHttpPriority = new HttpPriority();
    }

    public TigonRequestBuilder(TigonRequest tigonRequest) {
        this.mRetryable = true;
        this.mSoftDeadlineMs = -1;
        this.mExpectedResponseSizeBytes = -1;
        this.mFallbackToBackupHost = false;
        this.mLoggingId = "";
        this.mStartupStatusOnAdded = -1;
        this.mAddedToMiddlewareSinceEpochMS = -1;
        this.mIsReliableMediaEnabled = false;
        this.mMethod = tigonRequest.method();
        this.mUrl = tigonRequest.url();
        this.mHeaders = new HashMap(tigonRequest.headers());
        this.mTigonPriority = tigonRequest.tigonPriority();
        this.mHttpPriority = tigonRequest.httpPriority();
        this.mRetryable = tigonRequest.retryable();
        this.mLayerInformation = new HashMap(TigonRequestLayers.ALL_LAYERS.length);
        for (int i = 0; i < TigonRequestLayers.ALL_LAYERS.length; i++) {
            TigonRequestLayers.LayerInfo<?> layerInfo = TigonRequestLayers.ALL_LAYERS[i];
            Object layerInformation = tigonRequest.getLayerInformation(layerInfo);
            if (layerInformation != null) {
                this.mLayerInformation.put(layerInfo, layerInformation);
            }
        }
    }

    @DoNotStrip
    private static TigonRequest create(String str, String str2, String[] strArr, int i, boolean z, FacebookLoggingRequestInfo facebookLoggingRequestInfo) {
        TigonRequestBuilder retryable = new TigonRequestBuilder().setMethod(str).setUrl(str2).setTigonPriority(i).setRetryable(z);
        if ((strArr.length & 1) == 0) {
            for (int i2 = 0; i2 < strArr.length; i2 += 2) {
                retryable.addHeader(strArr[i2], strArr[i2 + 1]);
            }
            if (facebookLoggingRequestInfo != null) {
                retryable.addLayerInformation(TigonRequestLayers.FACEBOOK_LOGGING, facebookLoggingRequestInfo);
            }
            return retryable.build();
        }
        throw new IllegalArgumentException("must have even number of flattened headers");
    }

    public TigonRequestBuilder setMethod(String str) {
        this.mMethod = str;
        return this;
    }

    public TigonRequestBuilder setUrl(String str) {
        this.mUrl = str;
        return this;
    }

    public TigonRequestBuilder setTigonPriority(int i) {
        this.mTigonPriority = i;
        return this;
    }

    public TigonRequestBuilder setHttpPriority(HttpPriority httpPriority) {
        this.mHttpPriority = httpPriority;
        return this;
    }

    public TigonRequestBuilder setRetryable(boolean z) {
        this.mRetryable = z;
        return this;
    }

    public TigonRequestBuilder addHeader(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            this.mHeaders.put(str, str2);
        }
        return this;
    }

    public TigonRequestBuilder addHeaders(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            addHeader(entry.getKey(), entry.getValue());
        }
        return this;
    }

    public <T> TigonRequestBuilder addLayerInformation(TigonRequestLayers.LayerInfo<T> layerInfo, T t) {
        if (this.mLayerInformation == null) {
            this.mLayerInformation = new HashMap();
        }
        this.mLayerInformation.put(layerInfo, t);
        return this;
    }

    public TigonRequestBuilder setConnectionTimeoutMS(long j) {
        if (j >= 0) {
            this.mConnectionTimeoutMS = j;
            return this;
        }
        throw new IllegalArgumentException("ConnectionTimeout should be nonzero.");
    }

    public TigonRequestBuilder setIdleTimeoutMS(long j) {
        if (j >= 0) {
            this.mIdleTimeoutMS = j;
            return this;
        }
        throw new IllegalArgumentException("IdleTimeout should be nonzero.");
    }

    public TigonRequestBuilder setRequestTimeoutMS(long j) {
        if (j >= 0) {
            this.mRequestTimeoutMS = j;
            return this;
        }
        throw new IllegalArgumentException("RequestTimeout should be nonzero.");
    }

    public TigonRequestBuilder setSoftDeadlineMs(long j) {
        this.mSoftDeadlineMs = j;
        return this;
    }

    public TigonRequestBuilder setExpectedResponseSizeBytes(long j) {
        this.mExpectedResponseSizeBytes = j;
        return this;
    }

    public TigonRequestBuilder setRequestType(int i) {
        this.mRequestType = i;
        return this;
    }

    public TigonRequestBuilder enableFallbackToBackupHost(boolean z) {
        this.mFallbackToBackupHost = z;
        return this;
    }

    public TigonRequestBuilder enableReliableMedia(boolean z) {
        this.mIsReliableMediaEnabled = true;
        return this;
    }

    public TigonRequestBuilder setLoggingId(String str) {
        this.mLoggingId = str;
        return this;
    }

    public TigonRequestBuilder setStartupStatusOnAdded(int i) {
        this.mStartupStatusOnAdded = i;
        return this;
    }

    public TigonRequestBuilder setAddedToMiddlewareSinceEpochMS(long j) {
        this.mAddedToMiddlewareSinceEpochMS = j;
        return this;
    }

    public TigonRequest build() {
        return new Impl(this);
    }

    /* access modifiers changed from: private */
    public static class Impl implements TigonRequest {
        private final long mAddedToMiddlewareSinceEpochMS;
        private final long mConnectionTimeoutMS;
        private final long mExpectedResponseSizeBytes;
        private final boolean mFallbackToBackupHost;
        private final Map<String, String> mHeaders;
        private final HttpPriority mHttpPriority;
        private final long mIdleTimeoutMS;
        private final boolean mIsReliableMediaEnabled;
        @Nullable
        private final Map<TigonRequestLayers.LayerInfo<?>, Object> mLayerInformation;
        private final String mLoggingId;
        private final String mMethod;
        private final long mRequestTimeoutMS;
        private final int mRequestType;
        private final boolean mRetryable;
        private final long mSoftDeadlineMs;
        private final int mStartupStatusOnAdded;
        private final int mTigonPriority;
        private final String mUrl;

        Impl(TigonRequestBuilder tigonRequestBuilder) {
            this.mMethod = tigonRequestBuilder.mMethod;
            this.mUrl = tigonRequestBuilder.mUrl;
            this.mHeaders = Collections.unmodifiableMap(tigonRequestBuilder.mHeaders);
            this.mTigonPriority = tigonRequestBuilder.mTigonPriority;
            this.mHttpPriority = tigonRequestBuilder.mHttpPriority;
            this.mRetryable = tigonRequestBuilder.mRetryable;
            this.mLayerInformation = tigonRequestBuilder.mLayerInformation != null ? Collections.unmodifiableMap(tigonRequestBuilder.mLayerInformation) : null;
            this.mConnectionTimeoutMS = tigonRequestBuilder.mConnectionTimeoutMS;
            this.mIdleTimeoutMS = tigonRequestBuilder.mIdleTimeoutMS;
            this.mRequestTimeoutMS = tigonRequestBuilder.mRequestTimeoutMS;
            this.mSoftDeadlineMs = tigonRequestBuilder.mSoftDeadlineMs;
            this.mExpectedResponseSizeBytes = tigonRequestBuilder.mExpectedResponseSizeBytes;
            this.mRequestType = tigonRequestBuilder.mRequestType;
            this.mFallbackToBackupHost = tigonRequestBuilder.mFallbackToBackupHost;
            this.mIsReliableMediaEnabled = tigonRequestBuilder.mIsReliableMediaEnabled;
            this.mLoggingId = tigonRequestBuilder.mLoggingId;
            this.mStartupStatusOnAdded = tigonRequestBuilder.mStartupStatusOnAdded;
            this.mAddedToMiddlewareSinceEpochMS = tigonRequestBuilder.mAddedToMiddlewareSinceEpochMS;
        }

        @Override // com.facebook.tigon.iface.TigonRequest
        public String method() {
            return this.mMethod;
        }

        @Override // com.facebook.tigon.iface.TigonRequest
        public String url() {
            return this.mUrl;
        }

        @Override // com.facebook.tigon.iface.TigonRequest
        public Map<String, String> headers() {
            return this.mHeaders;
        }

        @Override // com.facebook.tigon.iface.TigonRequest
        public int tigonPriority() {
            return this.mTigonPriority;
        }

        @Override // com.facebook.tigon.iface.TigonRequest
        public HttpPriority httpPriority() {
            return this.mHttpPriority;
        }

        @Override // com.facebook.tigon.iface.TigonRequest
        public boolean retryable() {
            return this.mRetryable;
        }

        @Override // com.facebook.tigon.iface.TigonRequest
        @Nullable
        public <T> T getLayerInformation(TigonRequestLayers.LayerInfo<T> layerInfo) {
            Map<TigonRequestLayers.LayerInfo<?>, Object> map = this.mLayerInformation;
            if (map == null) {
                return null;
            }
            return (T) map.get(layerInfo);
        }

        @Override // com.facebook.tigon.iface.TigonRequest
        public long connectionTimeoutMS() {
            return this.mConnectionTimeoutMS;
        }

        @Override // com.facebook.tigon.iface.TigonRequest
        public long idleTimeoutMS() {
            return this.mIdleTimeoutMS;
        }

        @Override // com.facebook.tigon.iface.TigonRequest
        public long requestTimeoutMS() {
            return this.mRequestTimeoutMS;
        }

        @Override // com.facebook.tigon.iface.TigonRequest
        public long softDeadlineMS() {
            return this.mSoftDeadlineMs;
        }

        @Override // com.facebook.tigon.iface.TigonRequest
        public long expectedResponseSizeBytes() {
            return this.mExpectedResponseSizeBytes;
        }

        @Override // com.facebook.tigon.iface.TigonRequest
        public int requestType() {
            return this.mRequestType;
        }

        @Override // com.facebook.tigon.iface.TigonRequest
        public boolean fallbackToBackupHost() {
            return this.mFallbackToBackupHost;
        }

        @Override // com.facebook.tigon.iface.TigonRequest
        public boolean isReliableMediaEnabled() {
            return this.mIsReliableMediaEnabled;
        }

        @Override // com.facebook.tigon.iface.TigonRequest
        public String loggingId() {
            return this.mLoggingId;
        }

        @Override // com.facebook.tigon.iface.TigonRequest
        public int startupStatusOnAdded() {
            return this.mStartupStatusOnAdded;
        }

        @Override // com.facebook.tigon.iface.TigonRequest
        public long addedToMiddlewareSinceEpochMS() {
            return this.mAddedToMiddlewareSinceEpochMS;
        }
    }
}
