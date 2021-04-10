package com.oculus.http.core.endpoint;

import X.AnonymousClass006;
import X.AnonymousClass0I1;
import X.AnonymousClass0J3;
import android.text.TextUtils;
import com.facebook.annotations.Generated;

@Generated({"By: InjectorProcessor"})
public class String_com_oculus_http_core_annotations_FacebookGraphEndpointMethodAutoProvider extends AnonymousClass0J3<String> {
    public final Object get() {
        String A02 = AnonymousClass0I1.A02("debug.oculus.graphtier");
        if (!TextUtils.isEmpty(A02)) {
            return AnonymousClass006.A07("https://graph.", A02, ".facebook.com");
        }
        return EndpointModule.GRAPH_ENDPOINT_FACEBOOK;
    }
}
