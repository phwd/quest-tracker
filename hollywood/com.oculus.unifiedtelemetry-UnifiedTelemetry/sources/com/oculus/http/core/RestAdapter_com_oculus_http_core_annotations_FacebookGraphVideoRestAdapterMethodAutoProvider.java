package com.oculus.http.core;

import X.AbstractC0097Hv;
import X.C0135Qn;
import X.LD;
import com.facebook.annotations.Generated;
import com.oculus.http.core.endpoint.EndpointModule;
import com.oculus.http.defaultclient.DefaultClientModule;
import retrofit.RestAdapter;

@Generated({"By: InjectorProcessor"})
public class RestAdapter_com_oculus_http_core_annotations_FacebookGraphVideoRestAdapterMethodAutoProvider extends AbstractC0097Hv<RestAdapter> {
    public final Object get() {
        LD A00 = DefaultClientModule.A00(this);
        String A06 = EndpointModule.A06(this);
        ApiResponseConverter A002 = ApiResponseConverter.A00(this);
        RestAdapter.LogLevel A09 = ApiModule.A09(this);
        LoggingErrorHandler A003 = LoggingErrorHandler.A00(this);
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(A06);
        builder.setErrorHandler(A003);
        builder.setClient(new C0135Qn(A00));
        builder.setConverter(A002);
        builder.setLogLevel(A09);
        return builder.build();
    }
}
