package com.oculus.license;

import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.Binder;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.InjectorModule;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import com.oculus.horizon.api.ApiModule;
import com.oculus.http.common.HttpModule;
import com.oculus.http.core.endpoint.EndpointModule;
import com.oculus.http.core.interceptor.InterceptorModule;
import com.oculus.http.useragent.UserAgentModule;
import com.oculus.ossdk.inject.OsSdkModule;

@InjectorModule
public class LicenseModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_license_LicenseHelper_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_license_LicenseHelper_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(LicenseHelper.class)));
        public static final int _UL__ULSEP_com_oculus_license_LicenseMethods_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_license_LicenseMethods_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(LicenseMethods.class)));
    }

    @AutoGeneratedBinder
    static class AutoGeneratedBindingsForLicenseModule {
        AutoGeneratedBindingsForLicenseModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.require(BundledAndroidModule.class);
                binder.require(ApiModule.class);
                binder.require(HttpModule.class);
                binder.require(com.oculus.http.core.ApiModule.class);
                binder.require(EndpointModule.class);
                binder.require(InterceptorModule.class);
                binder.require(UserAgentModule.class);
                binder.require(OsSdkModule.class);
                binder.bind(LicenseHelper.class).toProvider(new LicenseHelperAutoProvider());
                binder.bind(LicenseMethods.class).toProvider(new LicenseMethodsAutoProvider());
            }
        }
    }
}
