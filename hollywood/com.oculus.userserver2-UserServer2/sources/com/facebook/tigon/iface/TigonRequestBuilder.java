package com.facebook.tigon.iface;

import X.C0111Ng;
import X.C0127Ru;
import X.NN;
import X.Ot;
import android.text.TextUtils;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.HashMap;
import java.util.Map;

@DoNotStrip
public class TigonRequestBuilder {
    public Ot A00 = new Ot();
    public Map<String, String> A01 = new HashMap();
    public Map<C0111Ng<?>, Object> A02;

    @DoNotStrip
    public static TigonRequest create(String str, String str2, String[] strArr, int i, boolean z, FacebookLoggingRequestInfo facebookLoggingRequestInfo) {
        TigonRequestBuilder tigonRequestBuilder = new TigonRequestBuilder();
        int length = strArr.length;
        if ((length & 1) == 0) {
            for (int i2 = 0; i2 < length; i2 += 2) {
                String str3 = strArr[i2];
                String str4 = strArr[i2 + 1];
                if (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)) {
                    tigonRequestBuilder.A01.put(str3, str4);
                }
            }
            if (facebookLoggingRequestInfo != null) {
                C0111Ng<FacebookLoggingRequestInfo> ng = NN.A03;
                Map map = tigonRequestBuilder.A02;
                if (map == null) {
                    map = new HashMap();
                    tigonRequestBuilder.A02 = map;
                }
                map.put(ng, facebookLoggingRequestInfo);
            }
            return new C0127Ru(tigonRequestBuilder);
        }
        throw new IllegalArgumentException("must have even number of flattened headers");
    }
}
