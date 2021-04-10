package com.oculus.dsatauth;

import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.Binder;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.InjectorModule;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.authapi.inject.OVRAuthModule;

@InjectorModule
public class DsatauthModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_dsatauth_DsatFetcher_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_dsatauth_DsatFetcher_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(DsatFetcher.class)));
    }

    @AutoGeneratedBinder
    static class AutoGeneratedBindingsForDsatauthModule {
        AutoGeneratedBindingsForDsatauthModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.require(BundledAndroidModule.class);
                binder.require(CredentialsModule.class);
                binder.require(OVRAuthModule.class);
                binder.bind(DsatFetcher.class).toProvider(new DsatFetcherAutoProvider()).in(ApplicationScoped.class);
            }
        }
    }
}