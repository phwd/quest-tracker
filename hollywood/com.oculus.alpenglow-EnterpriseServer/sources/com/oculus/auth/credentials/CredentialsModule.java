package com.oculus.auth.credentials;

import X.AbstractC01750Lk;
import X.AbstractC02980bI;
import X.AbstractC02990bJ;
import X.AbstractC07240oz;
import X.AnonymousClass0Ql;
import X.AnonymousClass13m;
import X.C01330Gf;
import X.C01730Le;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.DeclareMultiBinding;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.ProviderMethod;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import java.util.Set;
import javax.annotation.Nullable;

@InjectorModule
public abstract class CredentialsModule extends AbstractC01750Lk {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_auth_credentials_CredentialsManager_ULSEP_BINDING_ID = 52;
        public static final int _UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID = 111;
        public static final int _UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_credentials_CredentialsChangedHandler_ULGT__ULSEP_BINDING_ID = 2080;
    }

    @DeclareMultiBinding
    public abstract Set<CredentialsChangedHandler> getCredentialsChangedHandlers();

    @AutoGeneratedAccessMethod
    public static final AbstractC02980bI _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_auth_credentials_CredentialsManager_ULGT__ULSEP_ACCESS_METHOD(AbstractC02990bJ r2) {
        return new C01330Gf(52, r2);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC02980bI _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(AbstractC02990bJ r2) {
        return new C01330Gf(111, r2);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC02980bI _UL__ULSEP_com_facebook_inject_Lazy_ULLT_java_util_Set_ULLT_com_oculus_auth_credentials_CredentialsChangedHandler_ULGT__ULGT__ULSEP_ACCESS_METHOD(AbstractC02990bJ r2) {
        return new C01330Gf(2080, r2);
    }

    @AutoGeneratedAccessMethod
    public static final CredentialsManager _UL__ULSEP_com_oculus_auth_credentials_CredentialsManager_ULSEP_ACCESS_METHOD(AbstractC02990bJ r1) {
        return (CredentialsManager) AnonymousClass13m.A00(52, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Credentials _UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_ACCESS_METHOD(AbstractC02990bJ r1) {
        return (Credentials) AnonymousClass13m.A00(111, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Set _UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_credentials_CredentialsChangedHandler_ULGT__ULSEP_ACCESS_METHOD(AbstractC02990bJ r1) {
        return (Set) AnonymousClass13m.A00(2080, r1);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC07240oz _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_CredentialsManager_ULGT__ULSEP_ACCESS_METHOD(AbstractC02990bJ r2) {
        return new C01730Le(52, r2);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC07240oz _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(AbstractC02990bJ r2) {
        return new C01730Le(111, r2);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC07240oz _UL__ULSEP_javax_inject_Provider_ULLT_java_util_Set_ULLT_com_oculus_auth_credentials_CredentialsChangedHandler_ULGT__ULGT__ULSEP_ACCESS_METHOD(AbstractC02990bJ r2) {
        return new C01730Le(2080, r2);
    }

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForCredentialsModule {
        public static void bind(AnonymousClass0Ql r0) {
        }
    }

    @AutoGeneratedFactoryMethod
    public static final Credentials _UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_FACTORY_METHOD(AbstractC02990bJ r0) {
        return _UL__ULSEP_com_oculus_auth_credentials_CredentialsManager_ULSEP_ACCESS_METHOD(r0).getCredentials();
    }

    @ProviderMethod(asDefault = true)
    @Nullable
    public static Credentials provideCredentials(CredentialsManager credentialsManager) {
        return credentialsManager.getCredentials();
    }
}