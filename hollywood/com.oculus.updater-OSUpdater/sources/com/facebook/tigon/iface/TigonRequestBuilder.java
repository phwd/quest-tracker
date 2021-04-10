package com.facebook.tigon.iface;

import android.text.TextUtils;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.tigon.iface.TigonRequestLayers;
import com.oculus.common.build.BuildConfig;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

@DoNotStrip
public class TigonRequestBuilder {
    private long mAddedToMiddlewareSinceEpochMS = -1;
    private long mConnectionTimeoutMS;
    private long mExpectedResponseSizeBytes = -1;
    private boolean mFallbackToBackupHost = false;
    Map<String, String> mHeaders = new HashMap();
    HttpPriority mHttpPriority = new HttpPriority();
    private long mIdleTimeoutMS;
    private boolean mIsReliableMediaEnabled = false;
    Map<TigonRequestLayers.LayerInfo<?>, Object> mLayerInformation;
    private String mLoggingId = BuildConfig.PROVIDER_SUFFIX;
    String mMethod;
    private long mRequestTimeoutMS;
    private int mRequestType;
    boolean mRetryable = true;
    private long mSoftDeadlineMs = -1;
    private int mStartupStatusOnAdded = -1;
    int mTigonPriority = 1;
    String mUrl;

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

    public <T> TigonRequestBuilder addLayerInformation(TigonRequestLayers.LayerInfo<T> layerInfo, T t) {
        if (this.mLayerInformation == null) {
            this.mLayerInformation = new HashMap();
        }
        this.mLayerInformation.put(layerInfo, t);
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
    }
}
