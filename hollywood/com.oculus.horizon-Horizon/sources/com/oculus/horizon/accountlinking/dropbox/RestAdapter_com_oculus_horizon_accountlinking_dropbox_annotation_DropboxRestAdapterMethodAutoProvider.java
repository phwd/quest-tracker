package com.oculus.horizon.accountlinking.dropbox;

import X.AnonymousClass0J3;
import X.AnonymousClass117;
import com.facebook.annotations.Generated;
import com.oculus.horizon.accountlinking.dropbox.interceptor.DropboxAPIInterceptor;
import com.oculus.horizon.accountlinking.dropbox.interceptor.DropboxAuthorizationInterceptor;
import com.oculus.http.common.HttpModule;
import com.oculus.http.core.ApiModule;
import com.oculus.http.core.interceptor.UserAgentInterceptor;
import retrofit.RestAdapter;

@Generated({"By: InjectorProcessor"})
public class RestAdapter_com_oculus_horizon_accountlinking_dropbox_annotation_DropboxRestAdapterMethodAutoProvider extends AnonymousClass0J3<RestAdapter> {
    public final Object get() {
        return DropboxAPIModule.A00(HttpModule.A05(this), ApiModule.A06(this), ApiModule.A08(this), (UserAgentInterceptor) AnonymousClass117.A00(187, this), (DropboxAPIInterceptor) AnonymousClass117.A00(533, this), (DropboxAuthorizationInterceptor) AnonymousClass117.A00(132, this));
    }
}
