package com.oculus.auth.service;

import X.AbstractC06640p5;
import X.AnonymousClass0J5;
import X.AnonymousClass0Pi;
import X.AnonymousClass0Pp;
import X.AnonymousClass0p1;
import X.AnonymousClass117;
import X.C01010Iv;
import X.C02780bN;
import X.C02870bf;
import X.C02880bg;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.ProviderMethod;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.oculus.common.build.BuildConstants;
import com.oculus.horizon.notifications.legacy.contract.NotificationsContract;
import com.oculus.userserver.api.OculusUserManager;
import com.oculus.util.constants.OculusConstants;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import javax.inject.Provider;

@InjectorModule
public class AuthModule extends AnonymousClass0J5 {
    public static volatile C02870bf _UL__ULSEP_com_facebook_secure_trustedapp_TrustedApp_ULSEP_com_oculus_auth_service_ForAuthModule_ULSEP_INSTANCE;
    public static final Object _UL__ULSEP_com_facebook_secure_trustedapp_TrustedApp_ULSEP_com_oculus_auth_service_ForAuthModule_ULSEP_LOCK = new Object();

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_facebook_secure_trustedapp_TrustedApp_ULSEP_com_oculus_auth_service_ForAuthModule_ULSEP_BINDING_ID = 186;
        public static final int _UL__ULSEP_com_oculus_auth_service_AuthManager_ULSEP_BINDING_ID = 349;
        public static final int _UL__ULSEP_com_oculus_auth_service_AuthTrustedAppVerifier_ULSEP_BINDING_ID = NotificationsContract.GENERIC_NOTIFICATION_ID;
        public static final int _UL__ULSEP_com_oculus_auth_service_FacebookAuthManager_ULSEP_BINDING_ID = 561;
    }

    @AutoGeneratedAccessMethod
    public static final AnonymousClass0p1 _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_facebook_secure_trustedapp_TrustedApp_ULGT__ULSEP_com_oculus_auth_service_ForAuthModule_ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01010Iv(186, r2);
    }

    @AutoGeneratedAccessMethod
    public static final C02870bf _UL__ULSEP_com_facebook_secure_trustedapp_TrustedApp_ULSEP_com_oculus_auth_service_ForAuthModule_ULSEP_ACCESS_METHOD(AbstractC06640p5 r1) {
        return (C02870bf) AnonymousClass117.A00(186, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final C02870bf _UL__ULSEP_com_facebook_secure_trustedapp_TrustedApp_ULSEP_com_oculus_auth_service_ForAuthModule_ULSEP_FACTORY_METHOD(AbstractC06640p5 r3) {
        if (_UL__ULSEP_com_facebook_secure_trustedapp_TrustedApp_ULSEP_com_oculus_auth_service_ForAuthModule_ULSEP_INSTANCE == null) {
            synchronized (_UL__ULSEP_com_facebook_secure_trustedapp_TrustedApp_ULSEP_com_oculus_auth_service_ForAuthModule_ULSEP_LOCK) {
                AnonymousClass0Pi A00 = AnonymousClass0Pi.A00(_UL__ULSEP_com_facebook_secure_trustedapp_TrustedApp_ULSEP_com_oculus_auth_service_ForAuthModule_ULSEP_INSTANCE, r3);
                if (A00 != null) {
                    try {
                        r3.getApplicationInjector();
                        _UL__ULSEP_com_facebook_secure_trustedapp_TrustedApp_ULSEP_com_oculus_auth_service_ForAuthModule_ULSEP_INSTANCE = provideTrustedAppForAppVerifier();
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_com_facebook_secure_trustedapp_TrustedApp_ULSEP_com_oculus_auth_service_ForAuthModule_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_facebook_secure_trustedapp_TrustedApp_ULGT__ULSEP_com_oculus_auth_service_ForAuthModule_ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01010Iv(186, r2);
    }

    @ForAuthModule
    @ApplicationScoped(enableScopeValidation = false)
    @ProviderMethod
    public static C02870bf provideTrustedAppForAppVerifier() {
        return C02880bg.A02(C02780bN.A1F, Collections.unmodifiableSet(new HashSet(Arrays.asList(BuildConstants.PACKAGE_NAME_ASSISTANT, "com.oculus.explore", "com.oculus.firsttimenux", "com.oculus.horizon", "com.oculus.vrshell.home", "com.oculus.vrshell", "com.oculus.socialplatform", "com.oculus.store", OculusConstants.PACKAGE_NAME_SYSTEM_UTILITIES, "com.oculus.systemux", "com.oculus.ocms", OculusUserManager.SERVICE_IMPL_PKG))));
    }

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForAuthModule {
        public static void bind(AnonymousClass0Pp r0) {
        }
    }
}