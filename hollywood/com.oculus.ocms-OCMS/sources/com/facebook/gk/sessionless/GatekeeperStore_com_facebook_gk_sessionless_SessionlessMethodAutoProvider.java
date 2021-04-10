package com.facebook.gk.sessionless;

import com.facebook.annotations.Generated;
import com.facebook.gk.store.GatekeeperStore;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class GatekeeperStore_com_facebook_gk_sessionless_SessionlessMethodAutoProvider extends AbstractProvider<GatekeeperStore> {
    @Override // javax.inject.Provider
    public GatekeeperStore get() {
        return GkSessionlessModule.provideSessionlessGatekeeperStore(GkSessionlessModule._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(this));
    }
}
