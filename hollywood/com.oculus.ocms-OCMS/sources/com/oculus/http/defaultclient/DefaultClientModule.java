package com.oculus.http.defaultclient;

import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.Binder;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.Lazy;
import com.facebook.inject.RequiresBinding;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import java.lang.annotation.Annotation;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

@InjectorModule
public abstract class DefaultClientModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(OkHttpClient.class, (Class<? extends Annotation>) DefaultHttpClient.class)));
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_okhttp3_OkHttpClient_ULGT__ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_okhttp3_OkHttpClient_ULGT__ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UL_id._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final OkHttpClient _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (OkHttpClient) UL.factorymap.get(UL_id._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_BINDING_ID, injectorLike);
    }

    /* access modifiers changed from: package-private */
    @RequiresBinding
    @DefaultHttpClient
    public abstract OkHttpClient assertOkHttpClient();

    @AutoGeneratedBinder
    static class AutoGeneratedBindingsForDefaultClientModule {
        AutoGeneratedBindingsForDefaultClientModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.assertBindingInstalled(OkHttpClient.class, DefaultHttpClient.class);
                binder.require(BundledAndroidModule.class);
            }
        }
    }
}
