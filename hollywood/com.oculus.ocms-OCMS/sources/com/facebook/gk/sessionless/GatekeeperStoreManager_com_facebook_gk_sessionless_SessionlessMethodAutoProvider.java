package com.facebook.gk.sessionless;

import com.facebook.annotations.Generated;
import com.facebook.gk.store.GatekeeperStoreManager;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class GatekeeperStoreManager_com_facebook_gk_sessionless_SessionlessMethodAutoProvider extends AbstractProvider<GatekeeperStoreManager> {
    @Override // javax.inject.Provider
    public GatekeeperStoreManager get() {
        return GkSessionlessModule.provideSessionlessGatekeeperStoreManager(GkSessionlessModule._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(this));
    }
}
