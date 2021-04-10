package com.facebook.gk.sessionless;

import com.facebook.gk.store.GatekeeperStoreConfig;
import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class SessionlessGKStoreConfig implements GatekeeperStoreConfig {
    @Override // com.facebook.gk.store.GatekeeperStoreConfig
    public int getNumberOfGatekeepers() {
        return SessionlessGKMeta.count;
    }

    @Override // com.facebook.gk.store.GatekeeperStoreConfig
    public ArrayList<String> getGatekeeperNames() {
        return SessionlessGKNames.getCopy();
    }

    @Override // com.facebook.gk.store.GatekeeperStoreConfig
    public String getGatekeeperNamesHash() {
        return SessionlessGKMeta.hash;
    }
}
