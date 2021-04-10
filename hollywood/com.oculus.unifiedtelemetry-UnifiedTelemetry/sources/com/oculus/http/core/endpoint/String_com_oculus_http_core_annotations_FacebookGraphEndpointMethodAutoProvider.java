package com.oculus.http.core.endpoint;

import X.AbstractC0097Hv;
import X.AnonymousClass06;
import X.C0092Hd;
import android.text.TextUtils;
import com.facebook.annotations.Generated;

@Generated({"By: InjectorProcessor"})
public class String_com_oculus_http_core_annotations_FacebookGraphEndpointMethodAutoProvider extends AbstractC0097Hv<String> {
    public final Object get() {
        String A02 = C0092Hd.A02("debug.oculus.graphtier");
        if (!TextUtils.isEmpty(A02)) {
            return AnonymousClass06.A05("https://graph.", A02, ".facebook.com");
        }
        return EndpointModule.GRAPH_ENDPOINT_FACEBOOK;
    }
}
