package com.oculus.auth.util;

import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.Binder;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.base.app.AppInfoModule;

public class UtilModule extends AbstractLibraryModule {

    public static final class UL_id {
        public static final int $ul_$xXXcom_oculus_auth_util_AccessTokenUtils$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXcom_oculus_auth_util_AccessTokenUtils$xXXBINDING_ID : UL.id.dynamicId(Key.get(AccessTokenUtils.class)));
    }

    static class AutoGeneratedBindingsForUtilModule {
        AutoGeneratedBindingsForUtilModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.require(BundledAndroidModule.class);
                binder.require(CredentialsModule.class);
                binder.require(AppInfoModule.class);
                binder.bind(AccessTokenUtils.class).toProvider(new AccessTokenUtilsAutoProvider());
            }
        }
    }
}