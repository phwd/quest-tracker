package com.facebook.gk.store;

import android.content.Context;
import com.facebook.common.preconditions.Preconditions;
import com.facebook.common.util.TriState;
import com.facebook.infer.annotation.Nullsafe;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class GatekeeperStoreImpl implements GatekeeperStore, GatekeeperStoreManager, GatekeeperWriter, GkAccessorByName {
    @Nullable
    private GatekeeperChangeNotifier mChangeNotifier;
    private final GatekeeperCache mGatekeeperCache;
    @Nullable
    private GatekeeperIndices mGatekeeperIndices;
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

    @Override // com.facebook.gk.store.GatekeeperStore
    public GatekeeperWriter writer() {
        return this;
    }

    public static Builder forUser(Context context) {
        return new Builder(context);
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

    @Override // com.facebook.gk.store.GatekeeperStore
    public synchronized boolean get(int i, boolean z) {
        loadIfNeeded();
        return this.mGatekeeperCache.get(i).asBoolean(z);
    }

    @Override // com.facebook.gk.store.GkAccessorByName
    @Deprecated
    public synchronized boolean get(String str, boolean z) {
        return get(getGatekeeperByNameOrThrow(str), z);
    }

    @Override // com.facebook.gk.store.GatekeeperStore
    public synchronized TriState get(int i) {
        loadIfNeeded();
        return this.mGatekeeperCache.get(i);
    }

    @Override // com.facebook.gk.store.GkAccessorByName
    @Deprecated
    public synchronized TriState get(String str) {
        return get(getGatekeeperByNameOrThrow(str));
    }

    @Override // com.facebook.gk.store.GatekeeperStore
    public synchronized TriState getActual(int i) {
        loadIfNeeded();
        return this.mGatekeeperCache.getActual(i);
    }

    @Override // com.facebook.gk.store.GkAccessorByName
    @Deprecated
    public synchronized TriState getActual(String str) {
        return getActual(getGatekeeperByNameOrThrow(str));
    }

    @Override // com.facebook.gk.store.GatekeeperStore
    public synchronized boolean isInitialized(int i) {
        loadIfNeeded();
        return this.mGatekeeperCache.isInitialized(i);
    }

    @Override // com.facebook.gk.store.GkAccessorByName
    @Deprecated
    public synchronized boolean isInitialized(String str) {
        return isInitialized(getGatekeeperByNameOrThrow(str));
    }

    @Override // com.facebook.gk.store.GatekeeperStore
    public synchronized boolean isOverridden(int i) {
        loadIfNeeded();
        return this.mGatekeeperCache.isOverridden(i);
    }

    @Override // com.facebook.gk.store.GkAccessorByName
    @Deprecated
    public synchronized boolean isOverridden(String str) {
        return isOverridden(getGatekeeperByNameOrThrow(str));
    }

    @Override // com.facebook.gk.store.GkAccessorByName
    public boolean exists(String str) {
        return getGatekeeperIndices().get(str) != null;
    }

    @Override // com.facebook.gk.store.GkAccessorByName
    public synchronized SortedMap<String, String> getAllGatekeepers() {
        TreeMap treeMap;
        treeMap = new TreeMap();
        ArrayList<String> gatekeeperNames = this.mGatekeeperStoreConfig.getGatekeeperNames();
        int numberOfGatekeepers = this.mGatekeeperStoreConfig.getNumberOfGatekeepers();
        for (int i = 0; i < numberOfGatekeepers; i++) {
            treeMap.put(gatekeeperNames.get(i), get(i).toString().toLowerCase(Locale.US));
        }
        return treeMap;
    }

    @Override // com.facebook.gk.store.GatekeeperStoreManager
    public synchronized void load() {
        loadIfNeeded();
    }

    @Override // com.facebook.gk.store.GatekeeperStoreManager
    public synchronized void reset() {
        this.mGatekeeperCache.unsetAll();
        this.mGatekeeperCache.clearOverrideAll();
        this.mGatekeeperRepository.save(this.mGatekeeperCache);
    }

    @Override // com.facebook.gk.store.GatekeeperStoreManager
    public synchronized void setChangeNotifier(GatekeeperChangeNotifier gatekeeperChangeNotifier) {
        this.mChangeNotifier = gatekeeperChangeNotifier;
    }

    @Nullable
    private synchronized GatekeeperChangeNotifier getChangeNotifier() {
        return this.mChangeNotifier;
    }

    private int getGatekeeperByName(String str) {
        Integer num = getGatekeeperIndices().get(str);
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int getGatekeeperByNameOrThrow(String str) {
        int gatekeeperByName = getGatekeeperByName(str);
        if (gatekeeperByName != -1) {
            return gatekeeperByName;
        }
        throw new IllegalArgumentException("Unknown gatekeeper: " + str);
    }

    private synchronized GatekeeperIndices getGatekeeperIndices() {
        if (this.mGatekeeperIndices == null) {
            this.mGatekeeperIndices = new GatekeeperIndices(this.mGatekeeperStoreConfig);
        }
        return this.mGatekeeperIndices;
    }

    private void loadIfNeeded() {
        if (!this.mIsLoaded) {
            this.mIsLoaded = true;
            GatekeeperStoreLogger gatekeeperStoreLogger = this.mLogger;
            if (gatekeeperStoreLogger != null) {
                gatekeeperStoreLogger.beforeLoad();
            }
            try {
                loadStates();
            } finally {
                GatekeeperStoreLogger gatekeeperStoreLogger2 = this.mLogger;
                if (gatekeeperStoreLogger2 != null) {
                    gatekeeperStoreLogger2.afterLoad();
                }
            }
        }
    }

    private void loadStates() {
        PreExistingGatekeeperStatesProvider preExistingGatekeeperStatesProvider;
        if (!this.mGatekeeperRepository.load(this.mGatekeeperCache) && (preExistingGatekeeperStatesProvider = this.mPreExistingGatekeeperStatesProvider) != null) {
            fillCacheFromPreExistingStates(preExistingGatekeeperStatesProvider.getPreExistingGatekeeperStates());
            this.mGatekeeperRepository.save(this.mGatekeeperCache);
        }
    }

    private void fillCacheFromPreExistingStates(Map<String, Boolean> map) {
        ArrayList<String> gatekeeperNames = this.mGatekeeperStoreConfig.getGatekeeperNames();
        int numberOfGatekeepers = this.mGatekeeperStoreConfig.getNumberOfGatekeepers();
        for (int i = 0; i < numberOfGatekeepers; i++) {
            Boolean bool = map.get(gatekeeperNames.get(i));
            if (bool != null) {
                this.mGatekeeperCache.set(i, bool.booleanValue());
            }
        }
    }

    @Override // com.facebook.gk.store.GatekeeperStoreManager
    public void loadUserGatekeepersIfExists(String str) {
        GatekeeperStoreUserManager gatekeeperStoreUserManager = this.mGatekeeperStoreUserManager;
        if (gatekeeperStoreUserManager != null) {
            GatekeeperCache loadUserGatekeepers = gatekeeperStoreUserManager.loadUserGatekeepers(str);
            synchronized (this) {
                this.mGatekeeperCache.copyFrom(loadUserGatekeepers);
            }
        }
    }

    @Override // com.facebook.gk.store.GatekeeperStore
    public boolean containsInitializedGatekeepers() {
        return this.mGatekeeperCache.containsInitialized();
    }

    @Override // com.facebook.gk.store.GatekeeperStoreManager
    public void cleanupUserGatekeepers(String str) {
        GatekeeperStoreUserManager gatekeeperStoreUserManager = this.mGatekeeperStoreUserManager;
        if (gatekeeperStoreUserManager != null) {
            gatekeeperStoreUserManager.saveUserGatekeepers(str, this.mGatekeeperCache);
        }
        reset();
    }

    @Override // com.facebook.gk.store.GatekeeperWriter
    public Editor edit() {
        return new Editor();
    }

    public File getDirectory() {
        return this.mGatekeeperRepository.getWorkingDirectory();
    }

    public class Editor extends GatekeeperEditor {
        private Editor() {
            super(GatekeeperStoreImpl.this.mGatekeeperStoreConfig.getNumberOfGatekeepers());
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.gk.store.GatekeeperEditor
        public int gatekeeperByName(String str) {
            return GatekeeperStoreImpl.this.getGatekeeperByNameOrThrow(str);
        }

        @Override // com.facebook.gk.store.GatekeeperEditor, com.facebook.gk.store.GatekeeperWriter.Editor
        public synchronized void commit() {
            GatekeeperStoreImpl.this.commitChanges(this.mGatekeeperStates, this.mGatekeeperOverriddenStates, this.mSkipInitialized, false);
        }

        @Override // com.facebook.gk.store.GatekeeperEditor, com.facebook.gk.store.GatekeeperWriter.Editor
        public synchronized void commit(boolean z) {
            GatekeeperStoreImpl.this.commitChanges(this.mGatekeeperStates, this.mGatekeeperOverriddenStates, this.mSkipInitialized, z);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void commitChanges(TriState[] triStateArr, TriState[] triStateArr2, boolean z, boolean z2) {
        List<Integer> commitChangesAndGetChangedGatekeepers = commitChangesAndGetChangedGatekeepers(triStateArr, triStateArr2, z, z2);
        GatekeeperChangeNotifier changeNotifier = getChangeNotifier();
        if (changeNotifier != null) {
            changeNotifier.gatekeepersChanged(this, commitChangesAndGetChangedGatekeepers);
        }
    }

    private synchronized List<Integer> commitChangesAndGetChangedGatekeepers(TriState[] triStateArr, TriState[] triStateArr2, boolean z, boolean z2) {
        ArrayList arrayList;
        loadIfNeeded();
        arrayList = new ArrayList();
        int length = triStateArr.length;
        for (int i = 0; i < length; i++) {
            if (!z || !this.mGatekeeperCache.isInitialized(i)) {
                TriState triState = this.mGatekeeperCache.get(i);
                TriState triState2 = triStateArr[i];
                if (triState2 != null) {
                    if (triState2 == TriState.UNSET) {
                        this.mGatekeeperCache.unset(i);
                    } else {
                        this.mGatekeeperCache.set(i, triState2.asBoolean(false));
                    }
                }
                TriState triState3 = triStateArr2[i];
                if (triState3 != null) {
                    if (triState3 == TriState.UNSET) {
                        this.mGatekeeperCache.clearOverride(i);
                    } else {
                        this.mGatekeeperCache.setOverride(i, triState3.asBoolean(false));
                    }
                }
                if (z2 && triState != this.mGatekeeperCache.get(i)) {
                    arrayList.add(Integer.valueOf(i));
                }
            }
        }
        saveWithLogging();
        return arrayList;
    }

    private void saveWithLogging() {
        GatekeeperStoreLogger gatekeeperStoreLogger = this.mLogger;
        if (gatekeeperStoreLogger != null) {
            gatekeeperStoreLogger.beforeSave();
        }
        try {
            this.mGatekeeperRepository.save(this.mGatekeeperCache);
        } finally {
            GatekeeperStoreLogger gatekeeperStoreLogger2 = this.mLogger;
            if (gatekeeperStoreLogger2 != null) {
                gatekeeperStoreLogger2.afterSave();
            }
        }
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

        public Builder withPreExistingGatekeeperStates(PreExistingGatekeeperStatesProvider preExistingGatekeeperStatesProvider) {
            this.mPreExistingGatekeeperStatesProvider = preExistingGatekeeperStatesProvider;
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
