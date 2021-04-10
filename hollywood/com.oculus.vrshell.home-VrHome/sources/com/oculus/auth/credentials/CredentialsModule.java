package com.oculus.auth.credentials;

import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.Binder;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import java.util.Set;
import javax.annotation.Nullable;
import javax.inject.Provider;

public abstract class CredentialsModule extends AbstractLibraryModule {

    public static final class UL_id {
        public static final int $ul_$xXXcom_oculus_auth_credentials_Credentials$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXcom_oculus_auth_credentials_Credentials$xXXBINDING_ID : UL.id.dynamicId(Key.get(Credentials.class)));
        public static final int $ul_$xXXcom_oculus_auth_credentials_CredentialsManager$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXcom_oculus_auth_credentials_CredentialsManager$xXXBINDING_ID : UL.id.dynamicId(Key.get(CredentialsManager.class)));
        public static final int $ul_$xXXjava_util_Set$x3Ccom_oculus_auth_credentials_CredentialsChangedHandler$x3E$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXjava_util_Set$x3Ccom_oculus_auth_credentials_CredentialsChangedHandler$x3E$xXXBINDING_ID : UL.id.dynamicId(Key.get(new TypeLiteral<Set<CredentialsChangedHandler>>() {
            /* class com.oculus.auth.credentials.CredentialsModule.UL_id.AnonymousClass1 */
        })));
    }

    public static final Lazy $ul_$xXXcom_facebook_inject_Lazy$x3Cjava_util_Set$x3Ccom_oculus_auth_credentials_CredentialsChangedHandler$x3E$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightLazy.get(UL_id.$ul_$xXXjava_util_Set$x3Ccom_oculus_auth_credentials_CredentialsChangedHandler$x3E$xXXBINDING_ID, $ul_injector);
    }

    public static final Set $ul_$xXXjava_util_Set$x3Ccom_oculus_auth_credentials_CredentialsChangedHandler$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return (Set) UL.factorymap.get(UL_id.$ul_$xXXjava_util_Set$x3Ccom_oculus_auth_credentials_CredentialsChangedHandler$x3E$xXXBINDING_ID, $ul_injector);
    }

    public static final Provider $ul_$xXXjavax_inject_Provider$x3Ccom_oculus_auth_credentials_Credentials$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightProvider.get(UL_id.$ul_$xXXcom_oculus_auth_credentials_Credentials$xXXBINDING_ID, $ul_injector);
    }

    public static final Provider $ul_$xXXjavax_inject_Provider$x3Cjava_util_Set$x3Ccom_oculus_auth_credentials_CredentialsChangedHandler$x3E$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightProvider.get(UL_id.$ul_$xXXjava_util_Set$x3Ccom_oculus_auth_credentials_CredentialsChangedHandler$x3E$xXXBINDING_ID, $ul_injector);
    }

    /* access modifiers changed from: package-private */
    public abstract Set<CredentialsChangedHandler> getCredentialsChangedHandlers();

    static class AutoGeneratedBindingsForCredentialsModule {
        AutoGeneratedBindingsForCredentialsModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.assertBindingInstalled(CredentialsManager.class);
                binder.require(BundledAndroidModule.class);
                binder.declareMultiBinding(CredentialsChangedHandler.class);
                binder.bindDefault(Credentials.class).toProvider(new CredentialsMethodAutoProvider());
            }
        }
    }

    public static final Lazy $ul_$xXXcom_facebook_inject_Lazy$x3Ccom_oculus_auth_credentials_Credentials$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightLazy.get(UL_id.$ul_$xXXcom_oculus_auth_credentials_Credentials$xXXBINDING_ID, $ul_injector);
    }

    public static final Lazy $ul_$xXXcom_facebook_inject_Lazy$x3Ccom_oculus_auth_credentials_CredentialsManager$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightLazy.get(UL_id.$ul_$xXXcom_oculus_auth_credentials_CredentialsManager$xXXBINDING_ID, $ul_injector);
    }

    public static final Credentials $ul_$xXXcom_oculus_auth_credentials_Credentials$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return (Credentials) UL.factorymap.get(UL_id.$ul_$xXXcom_oculus_auth_credentials_Credentials$xXXBINDING_ID, $ul_injector);
    }

    public static final Credentials $ul_$xXXcom_oculus_auth_credentials_Credentials$xXXFACTORY_METHOD(InjectorLike $ul_injector) {
        return provideCredentials($ul_$xXXcom_oculus_auth_credentials_CredentialsManager$xXXACCESS_METHOD($ul_injector));
    }

    public static final CredentialsManager $ul_$xXXcom_oculus_auth_credentials_CredentialsManager$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return (CredentialsManager) UL.factorymap.get(UL_id.$ul_$xXXcom_oculus_auth_credentials_CredentialsManager$xXXBINDING_ID, $ul_injector);
    }

    public static final Provider $ul_$xXXjavax_inject_Provider$x3Ccom_oculus_auth_credentials_CredentialsManager$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightProvider.get(UL_id.$ul_$xXXcom_oculus_auth_credentials_CredentialsManager$xXXBINDING_ID, $ul_injector);
    }

    @Nullable
    static Credentials provideCredentials(CredentialsManager credentialsManager) {
        return credentialsManager.getCredentials();
    }
}