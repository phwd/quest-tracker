package com.oculus.auth.storage;

import X.AbstractC0246Xt;
import X.AbstractC0247Xu;
import X.C00198b;
import X.C0088Gy;
import X.C0515sp;
import X.I0;
import X.Pq;
import X.eJ;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.InjectorModule;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;

@InjectorModule
public abstract class StorageModule extends I0 {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_auth_storage_AuthDatastore_ULSEP_BINDING_ID = 23;
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC0246Xt _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_auth_storage_AuthDatastore_ULGT__ULSEP_ACCESS_METHOD(AbstractC0247Xu xu) {
        return new C00198b(23, xu);
    }

    @AutoGeneratedAccessMethod
    public static final AuthDatastore _UL__ULSEP_com_oculus_auth_storage_AuthDatastore_ULSEP_ACCESS_METHOD(AbstractC0247Xu xu) {
        return (AuthDatastore) C0515sp.A00(23, xu);
    }

    @AutoGeneratedAccessMethod
    public static final eJ _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_storage_AuthDatastore_ULGT__ULSEP_ACCESS_METHOD(AbstractC0247Xu xu) {
        return new C0088Gy(23, xu);
    }

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForStorageModule {
        public static void bind(Pq pq) {
        }
    }
}
