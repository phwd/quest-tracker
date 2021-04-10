package com.facebook.gk.store;

import com.facebook.infer.annotation.Nullsafe;
import java.io.File;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class GatekeeperStoreUserManager {
    private final GatekeeperStoreConfig mGatekeeperStoreConfig;
    private final File mTopDirectory;

    public GatekeeperStoreUserManager(GatekeeperStoreConfig gatekeeperStoreConfig, File file) {
        this.mGatekeeperStoreConfig = gatekeeperStoreConfig;
        this.mTopDirectory = file;
    }
}
