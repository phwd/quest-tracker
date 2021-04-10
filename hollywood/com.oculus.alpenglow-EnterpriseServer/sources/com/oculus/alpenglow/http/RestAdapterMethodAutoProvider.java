package com.oculus.alpenglow.http;

import X.AnonymousClass0Li;
import X.AnonymousClass0Qs;
import X.AnonymousClass0Vp;
import com.facebook.annotations.Generated;
import com.oculus.http.defaultclient.DefaultClientModule;
import retrofit.RestAdapter;

@Generated({"By: InjectorProcessor"})
public class RestAdapterMethodAutoProvider extends AnonymousClass0Li<RestAdapter> {
    public final Object get() {
        AnonymousClass0Qs A00 = DefaultClientModule.A00(this);
        String A06 = HttpModule.A06(this);
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(A06);
        builder.setClient(new AnonymousClass0Vp(A00));
        builder.setLogLevel(RestAdapter.LogLevel.FULL);
        return builder.build();
    }
}
