package com.oculus.mediaupload.api;

import X.AnonymousClass0J3;
import X.AnonymousClass0N1;
import X.AnonymousClass0UJ;
import X.AnonymousClass117;
import com.facebook.annotations.Generated;
import com.oculus.http.core.ApiModule;
import com.oculus.http.core.ApiResponseConverter;
import com.oculus.http.core.LoggingErrorHandler;
import com.oculus.http.core.endpoint.EndpointModule;
import retrofit.RestAdapter;

@Generated({"By: InjectorProcessor"})
public class RestAdapter_com_oculus_mediaupload_api_FacebookGraphRestAdapterMethodAutoProvider extends AnonymousClass0J3<RestAdapter> {
    public final Object get() {
        String A04 = EndpointModule.A04(this);
        RestAdapter.LogLevel A08 = ApiModule.A08(this);
        ApiResponseConverter A00 = ApiResponseConverter.A00(this);
        LoggingErrorHandler A002 = LoggingErrorHandler.A00(this);
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(A04);
        builder.setErrorHandler(A002);
        builder.setClient(new AnonymousClass0UJ((AnonymousClass0N1) AnonymousClass117.A00(473, this)));
        builder.setConverter(A00);
        builder.setLogLevel(A08);
        return builder.build();
    }
}
