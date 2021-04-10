package com.oculus.http.core;

import X.AbstractC0097Hv;
import X.C0135Qn;
import X.LD;
import com.facebook.annotations.Generated;
import com.oculus.http.core.endpoint.EndpointModule;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;

@Generated({"By: InjectorProcessor"})
public class RestAdapter_com_oculus_http_core_annotations_CustomUserAgentOculusRestAdapterMethodAutoProvider extends AbstractC0097Hv<RestAdapter> {
    public final Object get() {
        LD A01 = ApiModule.A01(this);
        String A07 = EndpointModule.A07(this);
        ErrorHandler A06 = ApiModule.A06(this);
        ApiResponseConverter A00 = ApiResponseConverter.A00(this);
        RestAdapter.LogLevel A09 = ApiModule.A09(this);
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(A07);
        builder.setErrorHandler(A06);
        builder.setClient(new C0135Qn(A01));
        builder.setConverter(A00);
        builder.setLogLevel(A09);
        return builder.build();
    }
}
