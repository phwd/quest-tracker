package com.oculus.http.core;

import X.AnonymousClass0J3;
import X.AnonymousClass0N1;
import X.AnonymousClass0UJ;
import com.facebook.annotations.Generated;
import com.oculus.http.core.endpoint.EndpointModule;
import com.oculus.http.defaultclient.DefaultClientModule;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;

@Generated({"By: InjectorProcessor"})
public class RestAdapter_com_oculus_http_core_annotations_OculusRestAdapterMethodAutoProvider extends AnonymousClass0J3<RestAdapter> {
    public final Object get() {
        AnonymousClass0N1 A00 = DefaultClientModule.A00(this);
        String A06 = EndpointModule.A06(this);
        ErrorHandler A062 = ApiModule.A06(this);
        ApiResponseConverter A002 = ApiResponseConverter.A00(this);
        RestAdapter.LogLevel A08 = ApiModule.A08(this);
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(A06);
        builder.setErrorHandler(A062);
        builder.setClient(new AnonymousClass0UJ(A00));
        builder.setConverter(A002);
        builder.setLogLevel(A08);
        return builder.build();
    }
}
