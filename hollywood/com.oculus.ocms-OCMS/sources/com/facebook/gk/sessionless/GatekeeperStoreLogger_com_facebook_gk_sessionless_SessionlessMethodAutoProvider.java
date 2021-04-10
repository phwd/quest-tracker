package com.facebook.gk.sessionless;

import com.facebook.annotations.Generated;
import com.facebook.gk.store.GatekeeperStoreLogger;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class GatekeeperStoreLogger_com_facebook_gk_sessionless_SessionlessMethodAutoProvider extends AbstractProvider<GatekeeperStoreLogger> {
    @Override // javax.inject.Provider
    public GatekeeperStoreLogger get() {
        return GkSessionlessModule.provideGatekeeperStoreLogger();
    }
}
