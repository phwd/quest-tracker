package com.oculus.http.core;

import X.AnonymousClass0J3;
import X.AnonymousClass0N1;
import X.AnonymousClass0UJ;
import com.facebook.annotations.Generated;
import com.oculus.http.core.endpoint.EndpointModule;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;

@Generated({"By: InjectorProcessor"})
public class RestAdapter_com_oculus_http_core_annotations_CustomUserAgentOculusRestAdapterMethodAutoProvider extends AnonymousClass0J3<RestAdapter> {
    public final Object get() {
        AnonymousClass0N1 A01 = ApiModule.A01(this);
        String A06 = EndpointModule.A06(this);
        ErrorHandler A062 = ApiModule.A06(this);
        ApiResponseConverter A00 = ApiResponseConverter.A00(this);
        RestAdapter.LogLevel A08 = ApiModule.A08(this);
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(A06);
        builder.setErrorHandler(A062);
        builder.setClient(new AnonymousClass0UJ(A01));
        builder.setConverter(A00);
        builder.setLogLevel(A08);
        return builder.build();
    }
}
