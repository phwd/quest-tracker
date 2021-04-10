package com.facebook.gk.store;

import android.content.Context;
import com.facebook.common.preconditions.Preconditions;
import com.facebook.infer.annotation.Nullsafe;
import java.io.File;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class GatekeeperStoreImpl implements GatekeeperStore, GatekeeperStoreManager, GatekeeperWriter, GkAccessorByName {
    private final GatekeeperCache mGatekeeperCache;
    private final GatekeeperRepository mGatekeeperRepository;
    private final GatekeeperStoreConfig mGatekeeperStoreConfig;
    @Nullable
    private final GatekeeperStoreUserManager mGatekeeperStoreUserManager;
    private boolean mIsLoaded = false;
    @Nullable
    private final GatekeeperStoreLogger mLogger;
    @Nullable
    private final PreExistingGatekeeperStatesProvider mPreExistingGatekeeperStatesProvider;

    @Override // com.facebook.gk.store.GatekeeperStore
    public void refreshSessionState(boolean z) {
    }

    public static Builder forSessionless(Context context) {
        return new Builder(context).forSessionless();
    }

    public GatekeeperStoreImpl(GatekeeperStoreConfig gatekeeperStoreConfig, GatekeeperRepository gatekeeperRepository, @Nullable PreExistingGatekeeperStatesProvider preExistingGatekeeperStatesProvider, @Nullable GatekeeperStoreLogger gatekeeperStoreLogger, @Nullable GatekeeperStoreUserManager gatekeeperStoreUserManager) {
        this.mGatekeeperStoreConfig = gatekeeperStoreConfig;
        this.mGatekeeperRepository = gatekeeperRepository;
        this.mPreExistingGatekeeperStatesProvider = preExistingGatekeeperStatesProvider;
        this.mLogger = gatekeeperStoreLogger;
        this.mGatekeeperStoreUserManager = gatekeeperStoreUserManager;
        this.mGatekeeperCache = new GatekeeperCache(gatekeeperStoreConfig.getNumberOfGatekeepers());
    }

    public static class Builder {
        @Nullable
        private GatekeeperStoreConfig mConfig;
        private final Context mContext;
        private boolean mIsSessionless;
        @Nullable
        private GatekeeperStoreLogger mLogger;
        @Nullable
        private PreExistingGatekeeperStatesProvider mPreExistingGatekeeperStatesProvider;

        Builder(Context context) {
            this.mContext = context;
        }

        public Builder withConfig(GatekeeperStoreConfig gatekeeperStoreConfig) {
            this.mConfig = gatekeeperStoreConfig;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder forSessionless() {
            this.mIsSessionless = true;
            return this;
        }

        public Builder withLogger(GatekeeperStoreLogger gatekeeperStoreLogger) {
            this.mLogger = gatekeeperStoreLogger;
            return this;
        }

        public GatekeeperStoreImpl build() {
            Preconditions.checkState(this.mConfig != null);
            File dir = this.mContext.getDir(this.mIsSessionless ? "sessionless_gatekeepers" : "gatekeepers", 0);
            return new GatekeeperStoreImpl(this.mConfig, new GatekeeperRepository(this.mConfig, dir), this.mPreExistingGatekeeperStatesProvider, this.mLogger, this.mIsSessionless ? null : new GatekeeperStoreUserManager(this.mConfig, dir));
        }
    }
}
