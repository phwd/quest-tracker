package com.facebook.gk.sessionless;

import com.facebook.annotations.Generated;
import com.facebook.gk.store.GatekeeperWriter;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class GatekeeperWriter_com_facebook_gk_sessionless_SessionlessMethodAutoProvider extends AbstractProvider<GatekeeperWriter> {
    @Override // javax.inject.Provider
    public GatekeeperWriter get() {
        return GkSessionlessModule.provideSessionlessGatekeeperWriter(GkSessionlessModule._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(this));
    }
}
