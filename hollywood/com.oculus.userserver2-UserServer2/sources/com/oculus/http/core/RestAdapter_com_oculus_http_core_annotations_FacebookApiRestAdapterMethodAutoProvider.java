package com.oculus.http.core;

import X.AbstractC0029Ba;
import X.AbstractC0054Ej;
import X.IX;
import X.K1;
import com.facebook.annotations.Generated;
import retrofit.RestAdapter;

@Generated({"By: InjectorProcessor"})
public class RestAdapter_com_oculus_http_core_annotations_FacebookApiRestAdapterMethodAutoProvider extends AbstractC0029Ba<RestAdapter> {
    public final Object get() {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint((String) IX.A00(70, this));
        builder.setErrorHandler((LoggingErrorHandler) IX.A00(58, this));
        builder.setClient(new K1((AbstractC0054Ej) IX.A00(51, this)));
        builder.setConverter((ApiResponseConverter) IX.A00(53, this));
        builder.setLogLevel((RestAdapter.LogLevel) IX.A00(25, this));
        return builder.build();
    }
}
