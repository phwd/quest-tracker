package com.oculus.horizon.endpoint;

import X.AnonymousClass006;
import X.AnonymousClass0I1;
import X.AnonymousClass0J3;
import X.AnonymousClass117;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.facebook.annotations.Generated;
import com.oculus.http.core.endpoint.EndpointModule;

@Generated({"By: InjectorProcessor"})
public class String_com_oculus_http_core_annotations_OculusApiEndpointMethodAutoProvider extends AnonymousClass0J3<String> {
    public final Object get() {
        String str;
        SharedPreferences sharedPreferences = (SharedPreferences) AnonymousClass117.A00(34, this);
        String A02 = AnonymousClass0I1.A02("debug.oculus.graphtier");
        if (!TextUtils.isEmpty(A02)) {
            str = AnonymousClass006.A07("https://graph.", A02, ".oculus.com");
        } else {
            str = EndpointModule.ENDPOINT_OCULUS;
        }
        return sharedPreferences.getString(HorizonEndpointModule.KEY_CUSTOM_API_ENDPOINT, str);
    }
}
