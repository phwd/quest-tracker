package com.facebook.gk.store;

import com.facebook.infer.annotation.Nullsafe;
import java.io.File;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class GatekeeperStoreUserManager {
    private static final String USERS_DIR = "users";
    private final GatekeeperStoreConfig mGatekeeperStoreConfig;
    private final File mTopDirectory;

    public GatekeeperStoreUserManager(GatekeeperStoreConfig gatekeeperStoreConfig, File file) {
        this.mGatekeeperStoreConfig = gatekeeperStoreConfig;
        this.mTopDirectory = file;
    }

    public GatekeeperCache loadUserGatekeepers(String str) {
        GatekeeperCache gatekeeperCache = new GatekeeperCache(this.mGatekeeperStoreConfig.getNumberOfGatekeepers());
        GatekeeperRepository createUserRepository = createUserRepository(str);
        if (createUserRepository == null) {
            return gatekeeperCache;
        }
        createUserRepository.load(gatekeeperCache);
        return gatekeeperCache;
    }

    public void saveUserGatekeepers(String str, GatekeeperCache gatekeeperCache) {
        GatekeeperRepository createUserRepository = createUserRepository(str);
        if (createUserRepository != null) {
            createUserRepository.save(gatekeeperCache);
        }
    }

    @Nullable
    private GatekeeperRepository createUserRepository(String str) {
        File createDirectory = createDirectory(str);
        if (createDirectory.exists() || createDirectory.mkdirs()) {
            return new GatekeeperRepository(this.mGatekeeperStoreConfig, createDirectory);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public File createDirectory(String str) {
        return new File(new File(this.mTopDirectory, USERS_DIR), str);
    }
}
