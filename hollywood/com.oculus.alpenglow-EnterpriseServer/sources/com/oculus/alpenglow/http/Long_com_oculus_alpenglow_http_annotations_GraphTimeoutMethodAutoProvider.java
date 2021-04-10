package com.oculus.alpenglow.http;

import X.AnonymousClass0Li;
import android.os.SystemProperties;
import android.text.TextUtils;
import com.facebook.annotations.Generated;

@Generated({"By: InjectorProcessor"})
public class Long_com_oculus_alpenglow_http_annotations_GraphTimeoutMethodAutoProvider extends AnonymousClass0Li<Long> {
    public final Object get() {
        long j;
        if (TextUtils.isEmpty(SystemProperties.get(HttpModule.HARDWARE_GRAPH_ENDPOINT_SYSPROP))) {
            j = 15000;
        } else {
            j = 120000;
        }
        return Long.valueOf(j);
    }
}
