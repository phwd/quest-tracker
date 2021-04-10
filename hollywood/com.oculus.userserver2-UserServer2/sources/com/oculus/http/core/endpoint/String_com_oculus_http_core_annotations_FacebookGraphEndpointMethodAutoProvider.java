package com.oculus.http.core.endpoint;

import X.AbstractC0029Ba;
import X.AnonymousClass06;
import X.Hw;
import android.text.TextUtils;
import com.facebook.annotations.Generated;

@Generated({"By: InjectorProcessor"})
public class String_com_oculus_http_core_annotations_FacebookGraphEndpointMethodAutoProvider extends AbstractC0029Ba<String> {
    public final Object get() {
        String A01 = Hw.A01("debug.oculus.graphtier");
        if (!TextUtils.isEmpty(A01)) {
            return AnonymousClass06.A04("https://graph.", A01, ".facebook.com");
        }
        return EndpointModule.GRAPH_ENDPOINT_FACEBOOK;
    }
}
