package com.facebook.tigon.iface;

import X.C1000qR;
import X.Lx;
import X.M4;
import X.M5;
import android.text.TextUtils;
import com.facebook.assistant.oacr.OacrConstants;
import java.util.HashMap;
import java.util.Map;

public class TigonRequestBuilder {
    public long mAddedToMiddlewareSinceEpochMS = -1;
    public long mConnectionTimeoutMS;
    public long mExpectedResponseSizeBytes = -1;
    public boolean mFallbackToBackupHost = false;
    public Map mHeaders = new HashMap();
    public Lx mHttpPriority = new Lx();
    public long mIdleTimeoutMS;
    public boolean mIsReliableMediaEnabled = false;
    public Map mLayerInformation;
    public String mLoggingId = OacrConstants.AUTO_SPEECH_DOMAIN;
    public String mMethod;
    public long mRequestTimeoutMS;
    public int mRequestType;
    public boolean mRetryable = true;
    public long mSoftDeadlineMs = -1;
    public int mStartupStatusOnAdded = -1;
    public int mTigonPriority = 1;
    public String mUrl;

    public static TigonRequest create(String str, String str2, String[] strArr, int i, boolean z, FacebookLoggingRequestInfo facebookLoggingRequestInfo) {
        TigonRequestBuilder tigonRequestBuilder = new TigonRequestBuilder();
        tigonRequestBuilder.mMethod = str;
        tigonRequestBuilder.mUrl = str2;
        tigonRequestBuilder.mTigonPriority = i;
        tigonRequestBuilder.mRetryable = z;
        int length = strArr.length;
        if ((length & 1) == 0) {
            for (int i2 = 0; i2 < length; i2 += 2) {
                String str3 = strArr[i2];
                String str4 = strArr[i2 + 1];
                if (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)) {
                    tigonRequestBuilder.mHeaders.put(str3, str4);
                }
            }
            if (facebookLoggingRequestInfo != null) {
                tigonRequestBuilder.addLayerInformation(M5.A03, facebookLoggingRequestInfo);
            }
            return new C1000qR(tigonRequestBuilder);
        }
        throw new IllegalArgumentException("must have even number of flattened headers");
    }

    public TigonRequestBuilder addLayerInformation(M4 m4, Object obj) {
        Map map = this.mLayerInformation;
        if (map == null) {
            map = new HashMap();
            this.mLayerInformation = map;
        }
        map.put(m4, obj);
        return this;
    }
}
