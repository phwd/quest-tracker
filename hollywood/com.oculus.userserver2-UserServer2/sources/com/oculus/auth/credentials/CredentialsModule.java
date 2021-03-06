package com.oculus.auth.credentials;

import X.AbstractC0031Bc;
import X.AbstractC0192Xx;
import X.AnonymousClass8B;
import X.BW;
import X.IX;
import X.OQ;
import X.SY;
import X.SZ;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.DeclareMultiBinding;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.ProviderMethod;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.oculus.common.build.BuildConfig;
import java.util.Set;
import javax.annotation.Nullable;

@InjectorModule
public abstract class CredentialsModule extends AbstractC0031Bc {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_auth_credentials_CredentialsManager_ULSEP_BINDING_ID = 72;
        public static final int _UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID = 48;
        public static final int _UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_credentials_CredentialsChangedHandler_ULGT__ULSEP_BINDING_ID = 2055;
    }

    @DeclareMultiBinding
    public abstract Set<CredentialsChangedHandler> getCredentialsChangedHandlers();

    @AutoGeneratedAccessMethod
    public static final SY _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_auth_credentials_CredentialsManager_ULGT__ULSEP_ACCESS_METHOD(SZ sz) {
        return new AnonymousClass8B(72, sz);
    }

    @AutoGeneratedAccessMethod
    public static final SY _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(SZ sz) {
        return new AnonymousClass8B(48, sz);
    }

    @AutoGeneratedAccessMethod
    public static final SY _UL__ULSEP_com_facebook_inject_Lazy_ULLT_java_util_Set_ULLT_com_oculus_auth_credentials_CredentialsChangedHandler_ULGT__ULGT__ULSEP_ACCESS_METHOD(SZ sz) {
        return new AnonymousClass8B(2055, sz);
    }

    @AutoGeneratedAccessMethod
    public static final CredentialsManager _UL__ULSEP_com_oculus_auth_credentials_CredentialsManager_ULSEP_ACCESS_METHOD(SZ sz) {
        return (CredentialsManager) IX.A00(72, sz);
    }

    @AutoGeneratedAccessMethod
    public static final Credentials _UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_ACCESS_METHOD(SZ sz) {
        return (Credentials) IX.A00(48, sz);
    }

    @AutoGeneratedAccessMethod
    public static final Set _UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_credentials_CredentialsChangedHandler_ULGT__ULSEP_ACCESS_METHOD(SZ sz) {
        return (Set) IX.A00(2055, sz);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC0192Xx _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_CredentialsManager_ULGT__ULSEP_ACCESS_METHOD(SZ sz) {
        return new BW(72, sz);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC0192Xx _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(SZ sz) {
        return new BW(48, sz);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC0192Xx _UL__ULSEP_javax_inject_Provider_ULLT_java_util_Set_ULLT_com_oculus_auth_credentials_CredentialsChangedHandler_ULGT__ULGT__ULSEP_ACCESS_METHOD(SZ sz) {
        return new BW(2055, sz);
    }

    @AutoGeneratedFactoryMethod
    public static final Credentials _UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_FACTORY_METHOD(SZ sz) {
        return _UL__ULSEP_com_oculus_auth_credentials_CredentialsManager_ULSEP_ACCESS_METHOD(sz).getCredentials();
    }

    @ProviderMethod(asDefault = BuildConfig.IS_LIBCXX_BUILD)
    @Nullable
    public static Credentials provideCredentials(CredentialsManager credentialsManager) {
        return credentialsManager.getCredentials();
    }

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForCredentialsModule {
        public static void bind(OQ oq) {
        }
    }
}
