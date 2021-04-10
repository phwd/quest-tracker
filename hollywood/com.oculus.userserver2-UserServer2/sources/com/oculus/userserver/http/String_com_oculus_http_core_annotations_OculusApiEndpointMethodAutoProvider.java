package com.oculus.userserver.http;

import X.AbstractC0029Ba;
import X.AnonymousClass06;
import X.Hw;
import android.text.TextUtils;
import com.facebook.annotations.Generated;
import com.oculus.http.core.endpoint.EndpointModule;

@Generated({"By: InjectorProcessor"})
public class String_com_oculus_http_core_annotations_OculusApiEndpointMethodAutoProvider extends AbstractC0029Ba<String> {
    public final Object get() {
        String A01 = Hw.A01("debug.oculus.graphtier");
        if (!TextUtils.isEmpty(A01)) {
            return AnonymousClass06.A04("https://graph.", A01, ".oculus.com");
        }
        return EndpointModule.ENDPOINT_OCULUS;
    }
}
