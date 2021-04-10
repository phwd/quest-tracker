package com.facebook.gk.sessionless;

import com.facebook.annotations.Generated;
import com.facebook.gk.store.GkAccessorByName;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class GkAccessorByName_com_facebook_gk_sessionless_SessionlessMethodAutoProvider extends AbstractProvider<GkAccessorByName> {
    @Override // javax.inject.Provider
    public GkAccessorByName get() {
        return GkSessionlessModule.provideSessionlessGkAccessorByName(GkSessionlessModule._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(this));
    }
}
