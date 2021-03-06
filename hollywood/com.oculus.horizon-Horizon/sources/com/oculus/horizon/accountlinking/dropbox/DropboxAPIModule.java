package com.oculus.horizon.accountlinking.dropbox;

import X.AbstractC08380wS;
import X.AnonymousClass0J5;
import X.AnonymousClass0N1;
import X.AnonymousClass0UJ;
import X.C04220h4;
import X.C08360wQ;
import X.C08790yb;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.ProviderMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.oculus.horizon.accountlinking.dropbox.annotation.DropboxRestAdapter;
import com.oculus.horizon.accountlinking.dropbox.interceptor.DropboxAPIInterceptor;
import com.oculus.horizon.accountlinking.dropbox.interceptor.DropboxAuthorizationInterceptor;
import com.oculus.http.core.interceptor.NpeExceptionHandlerInterceptor;
import com.oculus.http.core.interceptor.UserAgentInterceptor;
import java.util.List;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

@InjectorModule
public class DropboxAPIModule extends AnonymousClass0J5 {
    public static final String ENDPOINT_DROPBOX = "https://api.dropboxapi.com/2";
    public static volatile RestAdapter _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_horizon_accountlinking_dropbox_annotation_DropboxRestAdapter_ULSEP_INSTANCE;
    public static final Object _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_horizon_accountlinking_dropbox_annotation_DropboxRestAdapter_ULSEP_LOCK = new Object();

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForDropboxAPIModule {
    }

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_horizon_accountlinking_dropbox_DropboxAPIHelper_ULSEP_BINDING_ID = 90;
        public static final int _UL__ULSEP_com_oculus_horizon_accountlinking_dropbox_DropboxAPIMethods_ULSEP_BINDING_ID = 426;
        public static final int _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_horizon_accountlinking_dropbox_annotation_DropboxRestAdapter_ULSEP_BINDING_ID = 365;
    }

    @ApplicationScoped
    @ProviderMethod
    @DropboxRestAdapter
    public static RestAdapter A00(AnonymousClass0N1 r4, ErrorHandler errorHandler, RestAdapter.LogLevel logLevel, UserAgentInterceptor userAgentInterceptor, DropboxAPIInterceptor dropboxAPIInterceptor, DropboxAuthorizationInterceptor dropboxAuthorizationInterceptor) {
        C08360wQ r3 = new C08360wQ(r4);
        List<AbstractC08380wS> list = r3.A0N;
        list.add(userAgentInterceptor);
        list.add(dropboxAPIInterceptor);
        list.add(dropboxAuthorizationInterceptor);
        list.add(new C04220h4());
        list.add(new NpeExceptionHandlerInterceptor());
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(ENDPOINT_DROPBOX);
        builder.setClient(new AnonymousClass0UJ(new AnonymousClass0N1(r3)));
        builder.setErrorHandler(errorHandler);
        builder.converter = new GsonConverter(new C08790yb().A00());
        builder.setLogLevel(logLevel);
        return builder.build();
    }
}
