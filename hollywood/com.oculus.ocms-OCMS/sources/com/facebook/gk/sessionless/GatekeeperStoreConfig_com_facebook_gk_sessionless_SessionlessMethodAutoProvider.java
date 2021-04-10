package com.facebook.gk.sessionless;

import com.facebook.annotations.Generated;
import com.facebook.gk.store.GatekeeperStoreConfig;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class GatekeeperStoreConfig_com_facebook_gk_sessionless_SessionlessMethodAutoProvider extends AbstractProvider<GatekeeperStoreConfig> {
    @Override // javax.inject.Provider
    public GatekeeperStoreConfig get() {
        return GkSessionlessModule.provideGatekeeperStoreConfig();
    }
}
