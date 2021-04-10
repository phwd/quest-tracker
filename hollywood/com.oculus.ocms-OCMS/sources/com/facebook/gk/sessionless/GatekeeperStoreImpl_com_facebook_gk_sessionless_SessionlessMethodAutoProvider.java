package com.facebook.gk.sessionless;

import com.facebook.annotations.Generated;
import com.facebook.gk.store.GatekeeperStoreImpl;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class GatekeeperStoreImpl_com_facebook_gk_sessionless_SessionlessMethodAutoProvider extends AbstractProvider<GatekeeperStoreImpl> {
    @Override // javax.inject.Provider
    public GatekeeperStoreImpl get() {
        return GkSessionlessModule.provideGatekeeperStore(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this), GkSessionlessModule._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreConfig_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(this), GkSessionlessModule._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreLogger_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(this));
    }
}
