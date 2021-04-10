package com.facebook.tigon.iface;

import X.AnonymousClass0mQ;
import X.C02360id;
import X.C03420mb;
import X.C03430mc;
import android.text.TextUtils;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.HashMap;
import java.util.Map;

@DoNotStrip
public class TigonRequestBuilder {
    public long mAddedToMiddlewareSinceEpochMS = -1;
    public long mExpectedResponseSizeBytes = -1;
    public Map<String, String> mHeaders = new HashMap();
    public AnonymousClass0mQ mHttpPriority = new AnonymousClass0mQ();
    public Map<C03420mb<?>, Object> mLayerInformation;
    public String mLoggingId = "";
    public String mMethod;
    public boolean mRetryable = true;
    public long mSoftDeadlineMs = -1;
    public int mStartupStatusOnAdded = -1;
    public int mTigonPriority = 1;
    public String mUrl;

    @DoNotStrip
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
                C03420mb<FacebookLoggingRequestInfo> r1 = C03430mc.A03;
                Map map = tigonRequestBuilder.mLayerInformation;
                if (map == null) {
                    map = new HashMap();
                    tigonRequestBuilder.mLayerInformation = map;
                }
                map.put(r1, facebookLoggingRequestInfo);
            }
            return new C02360id(tigonRequestBuilder);
        }
        throw new IllegalArgumentException("must have even number of flattened headers");
    }
}
