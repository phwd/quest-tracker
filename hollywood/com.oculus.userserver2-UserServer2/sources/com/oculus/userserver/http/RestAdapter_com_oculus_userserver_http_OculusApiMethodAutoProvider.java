package com.oculus.userserver.http;

import X.AbstractC0029Ba;
import X.AbstractC0054Ej;
import X.IX;
import X.K1;
import com.facebook.annotations.Generated;
import com.oculus.http.core.ApiResponseConverter;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;

@Generated({"By: InjectorProcessor"})
public class RestAdapter_com_oculus_userserver_http_OculusApiMethodAutoProvider extends AbstractC0029Ba<RestAdapter> {
    public final Object get() {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint((String) IX.A00(60, this));
        builder.setErrorHandler((ErrorHandler) IX.A00(82, this));
        builder.setClient(new K1((AbstractC0054Ej) IX.A00(46, this)));
        builder.setConverter((ApiResponseConverter) IX.A00(53, this));
        builder.setLogLevel((RestAdapter.LogLevel) IX.A00(25, this));
        return builder.build();
    }
}
