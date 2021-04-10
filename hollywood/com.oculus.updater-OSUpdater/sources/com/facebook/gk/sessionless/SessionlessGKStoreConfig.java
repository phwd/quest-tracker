package com.facebook.gk.sessionless;

import com.facebook.gk.store.GatekeeperStoreConfig;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class SessionlessGKStoreConfig implements GatekeeperStoreConfig {
    @Override // com.facebook.gk.store.GatekeeperStoreConfig
    public int getNumberOfGatekeepers() {
        return SessionlessGKMeta.count;
    }
}
