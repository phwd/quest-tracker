package com.facebook.mobileconfig.impl;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.SparseArray;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.errorreporting.FbErrorReporter;
import com.facebook.common.primitives.Ints;
import com.facebook.debug.log.BLog;
import com.facebook.debug.tracer.Tracer;
import com.facebook.gk.store.GatekeeperStore;
import com.facebook.ipc.activity.ActivityConstants;
import com.facebook.mobileconfig.MobileConfigCxxChangeListener;
import com.facebook.mobileconfig.MobileConfigDefaults;
import com.facebook.mobileconfig.MobileConfigEmergencyPushChangeListener;
import com.facebook.mobileconfig.MobileConfigManagerHolder;
import com.facebook.mobileconfig.MobileConfigManagerHolderNoop;
import com.facebook.mobileconfig.MobileConfigUserIdProvider;
import com.facebook.mobileconfig.factory.MobileConfig;
import com.facebook.mobileconfig.factory.MobileConfigContext;
import com.facebook.mobileconfig.factory.MobileConfigOptions;
import com.facebook.mobileconfig.factory.MobileConfigOverridesTable;
import com.facebook.mobileconfig.factory.MobileConfigValueSource;
import com.facebook.mobileconfig.metadata.ParamsMapList;
import com.facebook.mobileconfig.specifier.MobileConfigSpecifierUtil;
import com.facebook.quicklog.QuickPerformanceLogger;
import com.oculus.util.constants.OculusConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReferenceArray;
import javax.annotation.Nullable;
import javax.inject.Provider;

public class MobileConfigFactoryImpl implements MobileConfig, MobileConfigCxxChangeListener, MobileConfigEmergencyPushChangeListener {
    static final String INVALID_SPECIFIER = "MobileConfigFactoryImpl: invalid specifier";
    private static final int LOGGING_FREQUENCY = 200000;
    private static final String TAG = "MobileConfigFactoryImpl";
    private static final int mMaxConfigIndex = 10000;
    private final Set<MobileConfigContext> mAllCachedContexts;
    @Nullable
    private volatile Provider<MobileConfigApi2Logger> mApi2Logger;
    @Nullable
    private AssetManager mAssetManager;
    private volatile MobileConfigContext mCachedLatestContext;
    private volatile AtomicReferenceArray<MobileConfigContext> mConfigCaches;
    @Nullable
    private volatile ManagerHolderCreator mCppManagerCreator;
    private File mDataDir;
    private boolean mEnableAutoApi2Logging;
    private final AtomicBoolean mEnableInitManagerOnDemand;
    @Nullable
    private final MobileConfigEmergencyPush mEpHandler;
    @Nullable
    private volatile Provider<FbErrorReporter> mFbErrorReporter;
    @Nullable
    private Provider<GatekeeperStore> mGatekeeperStoreLazy;
    private final AtomicBoolean mInitCppManagerAttempted;
    private final Object mInitCppManagerLock;
    private final AtomicBoolean mInitNoopJavaManager;
    private final AtomicBoolean mIsManagerInited;
    private boolean mIsSessionless;
    private final MobileConfigManagerHolder mManagerHolder;
    private MobileConfigOverridesTable mOverridesTable;
    @Nullable
    private Provider<ParamsMapList> mParamsMap;
    @Nullable
    private Provider<QuickPerformanceLogger> mQPL;
    private final Random mRandom;
    private MobileConfigSampledAccessListener mSampledAccessListener;
    private final AtomicBoolean mSkipLightWeightManagerInit;
    private final boolean mUseTranslationTable;
    @Nullable
    private MobileConfigUserIdProvider mUserIdProvider;

    @FunctionalInterface
    public interface ManagerHolderCreator {
        MobileConfigManagerHolder create(File file, String str);
    }

    /* synthetic */ MobileConfigFactoryImpl(MobileConfigManagerHolder mobileConfigManagerHolder, MobileConfigEmergencyPush mobileConfigEmergencyPush, boolean z, AnonymousClass1 r4) {
        this(mobileConfigManagerHolder, mobileConfigEmergencyPush, z);
    }

    private MobileConfigFactoryImpl(MobileConfigManagerHolder mobileConfigManagerHolder, MobileConfigEmergencyPush mobileConfigEmergencyPush, boolean z) {
        this.mSkipLightWeightManagerInit = new AtomicBoolean(false);
        this.mInitNoopJavaManager = new AtomicBoolean(true);
        this.mInitCppManagerAttempted = new AtomicBoolean(false);
        this.mInitCppManagerLock = new Object();
        this.mIsManagerInited = new AtomicBoolean(false);
        this.mEnableInitManagerOnDemand = new AtomicBoolean(false);
        this.mAllCachedContexts = new HashSet();
        this.mEnableAutoApi2Logging = false;
        this.mRandom = new Random();
        this.mManagerHolder = mobileConfigManagerHolder;
        this.mOverridesTable = this.mManagerHolder.getNewOverridesTableIfExists();
        this.mCachedLatestContext = null;
        this.mFbErrorReporter = null;
        this.mEpHandler = mobileConfigEmergencyPush;
        this.mConfigCaches = new AtomicReferenceArray<>((int) mMaxConfigIndex);
        this.mUseTranslationTable = z;
    }

    public static class Builder {
        private final MobileConfigFactoryImpl mInstance;

        /* synthetic */ Builder(MobileConfigManagerHolder mobileConfigManagerHolder, MobileConfigEmergencyPush mobileConfigEmergencyPush, boolean z, AnonymousClass1 r4) {
            this(mobileConfigManagerHolder, mobileConfigEmergencyPush, z);
        }

        private Builder(MobileConfigManagerHolder mobileConfigManagerHolder, MobileConfigEmergencyPush mobileConfigEmergencyPush, boolean z) {
            this.mInstance = new MobileConfigFactoryImpl(mobileConfigManagerHolder, mobileConfigEmergencyPush, z, null);
        }

        public Builder dataDir(File file) {
            this.mInstance.mDataDir = file;
            return this;
        }

        public Builder assetManager(AssetManager assetManager) {
            this.mInstance.mAssetManager = assetManager;
            return this;
        }

        public Builder sampledAccessListener(MobileConfigSampledAccessListener mobileConfigSampledAccessListener) {
            this.mInstance.mSampledAccessListener = mobileConfigSampledAccessListener;
            return this;
        }

        public Builder api2Logger(Provider<MobileConfigApi2Logger> provider, boolean z) {
            this.mInstance.mApi2Logger = provider;
            this.mInstance.mEnableAutoApi2Logging = z;
            return this;
        }

        public Builder paramsMap(Provider<ParamsMapList> provider) {
            this.mInstance.mParamsMap = provider;
            return this;
        }

        public Builder userIdProvider(MobileConfigUserIdProvider mobileConfigUserIdProvider) {
            this.mInstance.mUserIdProvider = mobileConfigUserIdProvider;
            return this;
        }

        public Builder qpl(Provider<QuickPerformanceLogger> provider) {
            this.mInstance.mQPL = provider;
            return this;
        }

        public Builder gkStore(Provider<GatekeeperStore> provider) {
            this.mInstance.mGatekeeperStoreLazy = provider;
            return this;
        }

        public MobileConfigFactoryImpl build() {
            MobileConfigFactoryImpl mobileConfigFactoryImpl = this.mInstance;
            mobileConfigFactoryImpl.mIsSessionless = mobileConfigFactoryImpl.mUserIdProvider == null;
            BLog.i(MobileConfigFactoryImpl.TAG, "Created MobileConfigFactoryImpl, isSessionless:%s", Boolean.valueOf(this.mInstance.mIsSessionless));
            if (this.mInstance.mSampledAccessListener == null) {
                this.mInstance.mSampledAccessListener = new MobileConfigSampledAccessListenerStub();
            }
            return this.mInstance;
        }

        public Builder setCppManagerCreator(ManagerHolderCreator managerHolderCreator) {
            this.mInstance.mCppManagerCreator = managerHolderCreator;
            return this;
        }
    }

    public static Builder builder(MobileConfigManagerHolder mobileConfigManagerHolder, MobileConfigEmergencyPush mobileConfigEmergencyPush, boolean z) {
        return new Builder(mobileConfigManagerHolder, mobileConfigEmergencyPush, z, null);
    }

    public static Builder builder(MobileConfigManagerHolder mobileConfigManagerHolder) {
        return builder(mobileConfigManagerHolder, null, false);
    }

    public boolean isInitManagerOnDemandEnabled() {
        return this.mEnableInitManagerOnDemand.get();
    }

    public void enableInitManagerOnDemand() {
        this.mEnableInitManagerOnDemand.set(true);
    }

    public boolean shouldUseTranslationTable() {
        return this.mUseTranslationTable;
    }

    public MobileConfigManagerHolder getManagerHolder() {
        return this.mManagerHolder;
    }

    public void setErrorReporter(Provider<FbErrorReporter> provider) {
        if (provider != null) {
            this.mFbErrorReporter = provider;
        }
    }

    public synchronized void refreshOverridesTable() {
        this.mOverridesTable = this.mManagerHolder.getNewOverridesTableIfExists();
        for (MobileConfigContext mobileConfigContext : this.mAllCachedContexts) {
            if (mobileConfigContext != null && (mobileConfigContext instanceof MobileConfigContextBase)) {
                ((MobileConfigContextBase) mobileConfigContext).refreshOverridesTable(this.mOverridesTable);
            }
        }
        if (this.mCachedLatestContext != null && (this.mCachedLatestContext instanceof MobileConfigContextBase)) {
            ((MobileConfigContextBase) this.mCachedLatestContext).refreshOverridesTable(this.mOverridesTable);
        }
    }

    public void refreshSessionState() {
        refreshSessionState(true);
    }

    private void refreshSessionState(boolean z) {
        Provider<GatekeeperStore> provider;
        boolean isSessionBasedAndLoggedOut = isSessionBasedAndLoggedOut();
        BLog.i(TAG, "refreshSessionState refreshGK: %s isLoggedOut: %s", Boolean.valueOf(z), Boolean.valueOf(isSessionBasedAndLoggedOut));
        synchronized (this) {
            this.mOverridesTable = this.mManagerHolder.getNewOverridesTableIfExists();
            this.mAllCachedContexts.clear();
            this.mConfigCaches = new AtomicReferenceArray<>((int) mMaxConfigIndex);
            this.mCachedLatestContext = null;
            if (this.mEnableInitManagerOnDemand.get() && isSessionBasedAndLoggedOut) {
                this.mIsManagerInited.set(false);
                this.mSkipLightWeightManagerInit.set(false);
                this.mInitNoopJavaManager.set(true);
                this.mInitCppManagerAttempted.set(false);
                this.mApi2Logger = null;
            }
        }
        if (z && (provider = this.mGatekeeperStoreLazy) != null && provider.get() != null) {
            this.mGatekeeperStoreLazy.get().refreshSessionState(false);
        }
    }

    private static boolean isValidCachedContext(int i, MobileConfigContext mobileConfigContext) {
        if (mobileConfigContext == null) {
            return false;
        }
        if (!MobileConfigDefaults.preferWaitForNonDefault(i)) {
            return true;
        }
        return (mobileConfigContext instanceof MobileConfigContextBase) && ((MobileConfigContextBase) mobileConfigContext).isValid();
    }

    public void forceRefresh(int i) {
        if (i < 0 || i >= this.mConfigCaches.length()) {
            BLog.w(TAG, "Cannot refresh config index(%d) from config caches", Integer.valueOf(i));
            return;
        }
        this.mConfigCaches.set(i, null);
    }

    public MobileConfigContext contextForConfig(int i) {
        AtomicReferenceArray<MobileConfigContext> atomicReferenceArray = this.mConfigCaches;
        if (i < 0 || i >= atomicReferenceArray.length()) {
            BLog.w(TAG, "Attempt to read invalid config index(%d) from config caches, isSessionless: %s", Integer.valueOf(i), Boolean.valueOf(this.mIsSessionless));
            return MobileConfigContextNoop.getInstance();
        }
        MobileConfigContext mobileConfigContext = atomicReferenceArray.get(i);
        if (!isValidCachedContext(i, mobileConfigContext)) {
            if (!this.mEnableInitManagerOnDemand.get() || !isSessionBasedAndLoggedOut()) {
                MobileConfigContext latestContextForConfig = latestContextForConfig(i);
                mobileConfigContext = !atomicReferenceArray.compareAndSet(i, mobileConfigContext, latestContextForConfig) ? atomicReferenceArray.get(i) : latestContextForConfig;
                MobileConfigContextBase mobileConfigContextBase = (MobileConfigContextBase) mobileConfigContext;
                Provider<MobileConfigApi2Logger> provider = this.mApi2Logger;
                if (provider != null && this.mEnableAutoApi2Logging) {
                    if (this.mIsSessionless) {
                        provider.get().log(i, mobileConfigContextBase);
                    } else {
                        String userId = getUserId();
                        if (!(userId == null || userId == "")) {
                            provider.get().log(i, mobileConfigContextBase);
                        }
                    }
                }
            } else {
                BLog.w(TAG, "Attempt to read config (index:%d) after logout, see https://fburl.com/bicj8iz0", Integer.valueOf(i));
                return MobileConfigContextNoop.getInstance();
            }
        }
        return mobileConfigContext;
    }

    public int[] accessedConfigs() {
        AtomicReferenceArray<MobileConfigContext> atomicReferenceArray = this.mConfigCaches;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < atomicReferenceArray.length(); i++) {
            if (atomicReferenceArray.get(i) != null) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        return Ints.toArray((List<Integer>) arrayList);
    }

    private MobileConfigContext contextForConfigParameter(long j) {
        return contextForConfig(MobileConfigSpecifierUtil.getConfigIndex(j));
    }

    @Override // com.facebook.mobileconfig.MobileConfigEmergencyPushChangeListener
    public boolean onEpConfigChanged(String[] strArr, String[] strArr2) {
        boolean z;
        if (!(strArr == null || strArr.length == 0 || strArr2 == null || strArr2.length == 0)) {
            EmergencyPushConfigsList emergencyPushConfigsList = new EmergencyPushConfigsList();
            emergencyPushConfigsList.metadata = new SparseArray<>();
            int length = strArr.length;
            int i = 0;
            while (true) {
                boolean z2 = true;
                if (i >= length) {
                    break;
                }
                String[] split = strArr[i].split(",");
                if (split.length == 6) {
                    String str = split[0];
                    int parseInt = str.matches("\\d+") ? Integer.parseInt(str) : -1;
                    if (parseInt != -1) {
                        String str2 = split[1];
                        String str3 = split[2];
                        int parseInt2 = str3.matches("\\d+") ? Integer.parseInt(str3) : -1;
                        if (parseInt2 != -1) {
                            String str4 = split[3];
                            boolean z3 = str4.matches("\\d+") ? Integer.parseInt(str4) != 0 : false;
                            String str5 = split[4];
                            int parseInt3 = (!z3 || !str5.matches("\\d+")) ? -1 : Integer.parseInt(str5);
                            String str6 = split[5];
                            if (str6.matches("\\d+")) {
                                if (Integer.parseInt(str6) == 0) {
                                    z2 = false;
                                }
                                z = z2;
                            } else {
                                z = false;
                            }
                            emergencyPushConfigsList.metadata.put(parseInt, new ConfigEmergencyPushMetadata(str2, parseInt2, z3, parseInt3, z));
                        }
                    }
                }
                i++;
            }
            for (String str7 : strArr2) {
                String[] split2 = str7.split(",");
                String str8 = split2[0];
                int parseInt4 = str8.matches("\\d+") ? Integer.parseInt(str8) : -1;
                if (parseInt4 != -1) {
                    HashSet hashSet = new HashSet();
                    for (int i2 = 1; i2 < split2.length; i2++) {
                        String str9 = split2[i2];
                        long parseLong = str9.matches("\\d+") ? Long.parseLong(str9) : -1;
                        if (parseLong != -1) {
                            hashSet.add(Long.valueOf(parseLong));
                        }
                    }
                    if (emergencyPushConfigsList.metadata.indexOfKey(parseInt4) >= 0) {
                        emergencyPushConfigsList.metadata.get(parseInt4).params = hashSet;
                    } else {
                        emergencyPushConfigsList.metadata.remove(parseInt4);
                    }
                }
            }
            MobileConfigEmergencyPush mobileConfigEmergencyPush = this.mEpHandler;
            if (mobileConfigEmergencyPush != null) {
                return mobileConfigEmergencyPush.onEpConfigChanged(emergencyPushConfigsList, this);
            }
        }
        return false;
    }

    @Override // com.facebook.mobileconfig.MobileConfigCxxChangeListener
    public void onConfigChanged(String[] strArr) {
        if (strArr != null && strArr.length != 0) {
            synchronized (this) {
                this.mCachedLatestContext = null;
                if (!(this.mGatekeeperStoreLazy == null || this.mGatekeeperStoreLazy.get() == null)) {
                    this.mGatekeeperStoreLazy.get().refreshSessionState(true);
                }
            }
        }
    }

    public MobileConfigContext latestContextForConfigParameter(long j) {
        return latestContextForConfig(MobileConfigSpecifierUtil.getConfigIndex(j));
    }

    public void logConfig(int i) {
        String userId;
        Provider<MobileConfigApi2Logger> provider = this.mApi2Logger;
        if (provider != null && !this.mIsSessionless && (userId = getUserId()) != null && !userId.equals("")) {
            provider.get().log(i, contextForConfig(i));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b4, code lost:
        com.facebook.debug.log.BLog.i(com.facebook.mobileconfig.impl.MobileConfigFactoryImpl.TAG, "Updated cached latest contextV2 - isValid: %s, isSessionless: %s withTranslationTable: %s", java.lang.Boolean.valueOf(((com.facebook.mobileconfig.impl.MobileConfigContextBase) r9).isValid()), java.lang.Boolean.valueOf(r8.mIsSessionless), java.lang.Boolean.valueOf(r9 instanceof com.facebook.mobileconfig.impl.MobileConfigContextV2WithTranslationTable));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.mobileconfig.factory.MobileConfigContext latestContextForConfig(int r9) {
        /*
        // Method dump skipped, instructions count: 215
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.mobileconfig.impl.MobileConfigFactoryImpl.latestContextForConfig(int):com.facebook.mobileconfig.factory.MobileConfigContext");
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfig
    public boolean isLoaded(boolean z) {
        if (this.mIsSessionless == z && this.mCachedLatestContext != null) {
            return true;
        }
        return false;
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfig
    public MobileConfig.MobileConfigValueState getCurrentMobileConfigState(boolean z) {
        if (this.mIsSessionless != z) {
            return MobileConfig.MobileConfigValueState.DefaultValue;
        }
        initLightweightManagerIfNeeded();
        if ((this.mManagerHolder.getLatestHandle() == null || this.mManagerHolder.getLatestHandle().getJavaByteBuffer() == null) ? false : true) {
            return MobileConfig.MobileConfigValueState.MobileConfigValue;
        }
        return MobileConfig.MobileConfigValueState.DefaultValue;
    }

    public boolean isManagerInited() {
        return this.mIsManagerInited.get();
    }

    public void initLightweightManagerIfNeeded() {
        initLightweightManagerIfNeeded(false);
    }

    public void initLightweightManagerIfNeeded(boolean z) {
        if (z || !this.mSkipLightWeightManagerInit.get()) {
            String userId = getUserId();
            if (this.mIsSessionless || (!userId.isEmpty() && !userId.equals(OculusConstants.DEFAULT_ENTERPRISE_USER_ID))) {
                synchronized (this) {
                    if (this.mSkipLightWeightManagerInit.compareAndSet(false, true)) {
                        if (this.mDataDir != null) {
                            if (this.mManagerHolder instanceof MobileConfigManagerSingletonHolder) {
                                Tracer.startTracer("MobileConfigFactoryImpl.initLightweightManager%s", this.mIsSessionless ? "Sessionless" : "");
                                try {
                                    MobileConfigCaptureError mobileConfigCaptureError = new MobileConfigCaptureError();
                                    MobileConfigManagerHolder createLightweightJavaManager = createLightweightJavaManager(this.mDataDir, userId, this.mAssetManager, this.mUseTranslationTable, mobileConfigCaptureError);
                                    if (createLightweightJavaManager != null) {
                                        ((MobileConfigManagerSingletonHolder) this.mManagerHolder).setManagerHolderInstance(createLightweightJavaManager, this);
                                        this.mIsManagerInited.set(true);
                                        refreshSessionState(false);
                                    } else {
                                        MobileConfigManagerHolder currentInstance = ((MobileConfigManagerSingletonHolder) this.mManagerHolder).getCurrentInstance();
                                        if (currentInstance instanceof MobileConfigManagerHolderNoop) {
                                            MobileConfigManagerHolderNoop mobileConfigManagerHolderNoop = (MobileConfigManagerHolderNoop) currentInstance;
                                            if (mobileConfigCaptureError.getError() != null) {
                                                int i = AnonymousClass1.$SwitchMap$com$facebook$mobileconfig$impl$MobileConfigError[mobileConfigCaptureError.getError().ordinal()];
                                                if (i == 1) {
                                                    mobileConfigManagerHolderNoop.setStartType("FreshInstall");
                                                } else if (i == 2 || i == 3) {
                                                    mobileConfigManagerHolderNoop.setStartType("AppUpgrade");
                                                }
                                            }
                                        }
                                    }
                                } finally {
                                    Tracer.stopTracer();
                                }
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                    return;
                }
            }
            initNoopManagerIfNeeded();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.facebook.mobileconfig.impl.MobileConfigFactoryImpl$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$mobileconfig$impl$MobileConfigError = new int[MobileConfigError.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.facebook.mobileconfig.impl.MobileConfigError[] r0 = com.facebook.mobileconfig.impl.MobileConfigError.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.mobileconfig.impl.MobileConfigFactoryImpl.AnonymousClass1.$SwitchMap$com$facebook$mobileconfig$impl$MobileConfigError = r0
                int[] r0 = com.facebook.mobileconfig.impl.MobileConfigFactoryImpl.AnonymousClass1.$SwitchMap$com$facebook$mobileconfig$impl$MobileConfigError     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.facebook.mobileconfig.impl.MobileConfigError r1 = com.facebook.mobileconfig.impl.MobileConfigError.NO_CONFIG_TABLE_SCHEMA_HASH     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.facebook.mobileconfig.impl.MobileConfigFactoryImpl.AnonymousClass1.$SwitchMap$com$facebook$mobileconfig$impl$MobileConfigError     // Catch:{ NoSuchFieldError -> 0x001f }
                com.facebook.mobileconfig.impl.MobileConfigError r1 = com.facebook.mobileconfig.impl.MobileConfigError.CONFIG_TABLE_SCHEMA_MISMATCH     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.facebook.mobileconfig.impl.MobileConfigFactoryImpl.AnonymousClass1.$SwitchMap$com$facebook$mobileconfig$impl$MobileConfigError     // Catch:{ NoSuchFieldError -> 0x002a }
                com.facebook.mobileconfig.impl.MobileConfigError r1 = com.facebook.mobileconfig.impl.MobileConfigError.CONFIG_TABLE_MAGIC_MISMATCH     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.mobileconfig.impl.MobileConfigFactoryImpl.AnonymousClass1.<clinit>():void");
        }
    }

    public void initCppManagerIfNeeded() {
        if (!this.mInitCppManagerAttempted.get()) {
            String userId = getUserId();
            synchronized (this.mInitCppManagerLock) {
                if (this.mInitCppManagerAttempted.compareAndSet(false, true)) {
                    if (this.mCppManagerCreator == null) {
                        BLog.w(TAG, "Calling initCppManagerIfNeeded with null mCppManagerCreator, userId:%s", userId);
                    } else if (this.mManagerHolder instanceof MobileConfigManagerSingletonHolder) {
                        if (this.mIsSessionless || (!userId.isEmpty() && !userId.equals(OculusConstants.DEFAULT_ENTERPRISE_USER_ID))) {
                            Tracer.startTracer("MobileConfigFactoryImpl.initCppManager%s", this.mIsSessionless ? "Sessionless" : "");
                            try {
                                MobileConfigManagerHolder create = this.mCppManagerCreator.create(this.mDataDir, userId);
                                if (create != null) {
                                    BLog.i(TAG, "Registered %s factory change listener: %s", this.mIsSessionless ? "SL" : "SB", create.registerConfigChangeListener(this) ? "ok" : "fail");
                                    int[] accessedConfigs = accessedConfigs();
                                    synchronized (this) {
                                        this.mIsManagerInited.set(true);
                                        ((MobileConfigManagerSingletonHolder) this.mManagerHolder).setManagerHolderInstance(create, this);
                                        this.mManagerHolder.setEpHandler(this);
                                        BLog.i(TAG, "Set Java EP Handler");
                                        refreshSessionState(false);
                                    }
                                    for (int i : accessedConfigs) {
                                        contextForConfig(i);
                                    }
                                }
                            } finally {
                                Tracer.stopTracer();
                            }
                        }
                    }
                }
            }
        }
    }

    public void initAnyManagerIfNeeded() {
        if (!isManagerInited()) {
            initLightweightManagerIfNeeded();
            if (!isManagerInited()) {
                initCppManagerIfNeeded();
            }
        }
    }

    private void initNoopManagerIfNeeded() {
        if (this.mInitNoopJavaManager.get()) {
            synchronized (this) {
                if (this.mInitNoopJavaManager.compareAndSet(true, false)) {
                    MobileConfigManagerHolder currentInstance = ((MobileConfigManagerSingletonHolder) this.mManagerHolder).getCurrentInstance();
                    if (currentInstance instanceof MobileConfigManagerHolderNoop) {
                        ((MobileConfigManagerHolderNoop) currentInstance).setStartType("Logout");
                    }
                }
            }
        }
    }

    private String getUserId() {
        MobileConfigUserIdProvider mobileConfigUserIdProvider = this.mUserIdProvider;
        if (mobileConfigUserIdProvider == null) {
            return "";
        }
        String userId = mobileConfigUserIdProvider.getUserId();
        if (userId != null) {
            return userId;
        }
        return "";
    }

    private boolean isSessionBasedAndLoggedOut() {
        return !this.mIsSessionless && "".equals(getUserId());
    }

    private void validateParamSpecifier(long j, int i) {
        if (this.mIsSessionless != MobileConfigSpecifierUtil.getIsSessionless(j)) {
            String str = this.mIsSessionless ? "Sessionless factory used for session-based param" : "Session-based factory used for sessionless param";
            if (this.mFbErrorReporter != null) {
                this.mFbErrorReporter.get().softReport(INVALID_SPECIFIER, str);
            }
            BLog.w(TAG, str);
        }
        if (i != 0 && i != MobileConfigSpecifierUtil.getParamType(j)) {
            String str2 = "Invalid param type used for config: " + MobileConfigSpecifierUtil.getConfigIndex(j) + ", param: " + MobileConfigSpecifierUtil.getParamKey(j);
            if (this.mFbErrorReporter != null) {
                this.mFbErrorReporter.get().softReport(INVALID_SPECIFIER, str2);
            }
            BLog.w(TAG, str2);
        }
    }

    private void checkIfRequireCallsiteDefault(long j, String str) {
        if (BuildConfig.DEBUG && MobileConfigSpecifierUtil.getRequireCallsiteDefault(j)) {
            StringBuilder sb = new StringBuilder();
            sb.append("QE param does not have a universe default. Please call `");
            sb.append(str);
            sb.append("(specifier, defaultValue)` instead and provide a call-site default value. ");
            sb.append("https://fburl.com/callsitedefault");
            if (this.mFbErrorReporter != null) {
                this.mFbErrorReporter.get().softReport(String.format("%s cfg:%d param:%d", INVALID_SPECIFIER, Integer.valueOf(MobileConfigSpecifierUtil.getConfigKey(j)), Integer.valueOf(MobileConfigSpecifierUtil.getParamKey(j))), sb.toString());
            }
            BLog.w(TAG, sb.toString());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0066, code lost:
        if (com.facebook.mobileconfig.impl.MobileConfigTranslationTableVerifier.verify(new com.facebook.mobileconfig.impl.MobileConfigContextV2WithTranslationTable(r7, r12, r15.mOverridesTable, r15.mFbErrorReporter, r11)) == false) goto L_0x006a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00b3  */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.facebook.mobileconfig.MobileConfigManagerHolder createLightweightJavaManager(java.io.File r16, java.lang.String r17, @javax.annotation.Nullable android.content.res.AssetManager r18, boolean r19, com.facebook.mobileconfig.impl.MobileConfigCaptureError r20) {
        /*
        // Method dump skipped, instructions count: 199
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.mobileconfig.impl.MobileConfigFactoryImpl.createLightweightJavaManager(java.io.File, java.lang.String, android.content.res.AssetManager, boolean, com.facebook.mobileconfig.impl.MobileConfigCaptureError):com.facebook.mobileconfig.MobileConfigManagerHolder");
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public boolean getBoolean(long j) {
        return getBooleanWithOptions(j, MobileConfigOptions.NONE);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public boolean getBooleanWithOptions(long j, MobileConfigOptions mobileConfigOptions) {
        checkIfRequireCallsiteDefault(j, "getBooleanWithOptions");
        return getBooleanWithOptions(j, MobileConfigDefaults.getBoolDefaults(j), mobileConfigOptions);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public boolean getBooleanWithOptions(long j, boolean z, MobileConfigOptions mobileConfigOptions) {
        MobileConfigContext mobileConfigContext;
        boolean z2;
        validateParamSpecifier(j, 1);
        boolean z3 = false;
        boolean z4 = this.mRandom.nextInt(LOGGING_FREQUENCY) == 0;
        MobileConfigOptions requestForValueSource = z4 ? mobileConfigOptions.requestForValueSource() : mobileConfigOptions;
        if (requestForValueSource.isLatestValueRequested()) {
            mobileConfigContext = latestContextForConfigParameter(j);
        } else {
            mobileConfigContext = contextForConfigParameter(j);
        }
        MobileConfigManagerHolder mobileConfigManagerHolder = this.mManagerHolder;
        if (!(mobileConfigManagerHolder instanceof MobileConfigManagerSingletonHolder) || !(((MobileConfigManagerSingletonHolder) mobileConfigManagerHolder).getCurrentInstance() instanceof MobileConfigManagerHolderNoop)) {
            z2 = mobileConfigContext.getBooleanWithOptions(j, z, requestForValueSource);
        } else {
            if (requestForValueSource.isValueSourceRequested()) {
                requestForValueSource.setValueSource(MobileConfigValueSource.DEFAULT__ACCESSED_BEFORE_MC_INIT);
            }
            z2 = z;
        }
        if (z4) {
            MobileConfigValueSource valueSource = requestForValueSource.getValueSource();
            String syncFetchReason = this.mManagerHolder.syncFetchReason();
            String str = z2 ? ActivityConstants.Extras.WATCH_FEED_INJECTION : OculusConstants.DEFAULT_ENTERPRISE_USER_ID;
            MobileConfigSampledAccessListener mobileConfigSampledAccessListener = this.mSampledAccessListener;
            if (valueSource.getSource() > 1) {
                z3 = true;
            }
            mobileConfigSampledAccessListener.onMobileConfigAccess(j, LOGGING_FREQUENCY, z3, valueSource.name(), syncFetchReason, str, this.mUseTranslationTable);
        }
        return z2;
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public boolean getBoolean(long j, boolean z) {
        validateParamSpecifier(j, 1);
        return contextForConfigParameter(j).getBoolean(j, z);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public long getLong(long j) {
        return getLongWithOptions(j, MobileConfigOptions.NONE);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public long getLongWithOptions(long j, MobileConfigOptions mobileConfigOptions) {
        checkIfRequireCallsiteDefault(j, "getLongWithOptions");
        return getLongWithOptions(j, MobileConfigDefaults.getLongDefaults(j), mobileConfigOptions);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public long getLongWithOptions(long j, long j2, MobileConfigOptions mobileConfigOptions) {
        MobileConfigContext mobileConfigContext;
        long j3;
        validateParamSpecifier(j, 2);
        boolean z = this.mRandom.nextInt(LOGGING_FREQUENCY) == 0;
        MobileConfigOptions requestForValueSource = z ? mobileConfigOptions.requestForValueSource() : mobileConfigOptions;
        if (requestForValueSource.isLatestValueRequested()) {
            mobileConfigContext = latestContextForConfigParameter(j);
        } else {
            mobileConfigContext = contextForConfigParameter(j);
        }
        MobileConfigManagerHolder mobileConfigManagerHolder = this.mManagerHolder;
        if (!(mobileConfigManagerHolder instanceof MobileConfigManagerSingletonHolder) || !(((MobileConfigManagerSingletonHolder) mobileConfigManagerHolder).getCurrentInstance() instanceof MobileConfigManagerHolderNoop)) {
            j3 = mobileConfigContext.getLongWithOptions(j, j2, requestForValueSource);
        } else {
            if (requestForValueSource.isValueSourceRequested()) {
                requestForValueSource.setValueSource(MobileConfigValueSource.DEFAULT__ACCESSED_BEFORE_MC_INIT);
            }
            j3 = j2;
        }
        if (z) {
            MobileConfigValueSource valueSource = requestForValueSource.getValueSource();
            this.mSampledAccessListener.onMobileConfigAccess(j, LOGGING_FREQUENCY, valueSource.getSource() > 1, valueSource.name(), this.mManagerHolder.syncFetchReason(), Long.toString(j3), this.mUseTranslationTable);
        }
        return j3;
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public long getLong(long j, long j2) {
        validateParamSpecifier(j, 2);
        return contextForConfigParameter(j).getLong(j, j2);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public String getString(long j) {
        return getStringWithOptions(j, MobileConfigOptions.NONE);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public String getStringWithOptions(long j, MobileConfigOptions mobileConfigOptions) {
        checkIfRequireCallsiteDefault(j, "getStringWithOptions");
        return getStringWithOptions(j, MobileConfigDefaults.getStringDefaults(j), mobileConfigOptions);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public String getStringWithOptions(long j, int i, Resources resources, MobileConfigOptions mobileConfigOptions) {
        String stringWithOptions = getStringWithOptions(j, null, mobileConfigOptions);
        return stringWithOptions != null ? stringWithOptions : resources.getString(i);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public String getStringWithOptions(long j, String str, MobileConfigOptions mobileConfigOptions) {
        MobileConfigContext mobileConfigContext;
        String str2;
        validateParamSpecifier(j, 3);
        boolean z = true;
        boolean z2 = this.mRandom.nextInt(LOGGING_FREQUENCY) == 0;
        MobileConfigOptions requestForValueSource = z2 ? mobileConfigOptions.requestForValueSource() : mobileConfigOptions;
        if (requestForValueSource.isLatestValueRequested()) {
            mobileConfigContext = latestContextForConfigParameter(j);
        } else {
            mobileConfigContext = contextForConfigParameter(j);
        }
        MobileConfigManagerHolder mobileConfigManagerHolder = this.mManagerHolder;
        if (!(mobileConfigManagerHolder instanceof MobileConfigManagerSingletonHolder) || !(((MobileConfigManagerSingletonHolder) mobileConfigManagerHolder).getCurrentInstance() instanceof MobileConfigManagerHolderNoop)) {
            str2 = mobileConfigContext.getStringWithOptions(j, str, requestForValueSource);
        } else {
            if (requestForValueSource.isValueSourceRequested()) {
                requestForValueSource.setValueSource(MobileConfigValueSource.DEFAULT__ACCESSED_BEFORE_MC_INIT);
            }
            str2 = str;
        }
        if (z2) {
            MobileConfigValueSource valueSource = requestForValueSource.getValueSource();
            String syncFetchReason = this.mManagerHolder.syncFetchReason();
            MobileConfigSampledAccessListener mobileConfigSampledAccessListener = this.mSampledAccessListener;
            if (valueSource.getSource() <= 1) {
                z = false;
            }
            mobileConfigSampledAccessListener.onMobileConfigAccess(j, LOGGING_FREQUENCY, z, valueSource.name(), syncFetchReason, getStringValueForSampleLogging(str2), this.mUseTranslationTable);
        }
        return str2;
    }

    private static String getStringValueForSampleLogging(String str) {
        if (str == null) {
            return "<null>";
        }
        int length = str.length();
        if (length <= 12) {
            return str;
        }
        return str.substring(0, 5) + ".." + str.substring(length - 5, length);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public String getString(long j, String str) {
        validateParamSpecifier(j, 3);
        return contextForConfigParameter(j).getString(j, str);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public String getString(long j, int i, Resources resources) {
        validateParamSpecifier(j, 3);
        return contextForConfigParameter(j).getString(j, i, resources);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public double getDouble(long j) {
        return getDoubleWithOptions(j, MobileConfigOptions.NONE);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public double getDoubleWithOptions(long j, MobileConfigOptions mobileConfigOptions) {
        checkIfRequireCallsiteDefault(j, "getDoubleWithOptions");
        return getDoubleWithOptions(j, MobileConfigDefaults.getDoubleDefaults(j), mobileConfigOptions);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public double getDoubleWithOptions(long j, double d, MobileConfigOptions mobileConfigOptions) {
        MobileConfigContext mobileConfigContext;
        double d2;
        validateParamSpecifier(j, 4);
        boolean z = this.mRandom.nextInt(LOGGING_FREQUENCY) == 0;
        MobileConfigOptions requestForValueSource = z ? mobileConfigOptions.requestForValueSource() : mobileConfigOptions;
        if (requestForValueSource.isLatestValueRequested()) {
            mobileConfigContext = latestContextForConfigParameter(j);
        } else {
            mobileConfigContext = contextForConfigParameter(j);
        }
        MobileConfigManagerHolder mobileConfigManagerHolder = this.mManagerHolder;
        if (!(mobileConfigManagerHolder instanceof MobileConfigManagerSingletonHolder) || !(((MobileConfigManagerSingletonHolder) mobileConfigManagerHolder).getCurrentInstance() instanceof MobileConfigManagerHolderNoop)) {
            d2 = mobileConfigContext.getDoubleWithOptions(j, d, requestForValueSource);
        } else {
            if (requestForValueSource.isValueSourceRequested()) {
                requestForValueSource.setValueSource(MobileConfigValueSource.DEFAULT__ACCESSED_BEFORE_MC_INIT);
            }
            d2 = d;
        }
        if (z) {
            MobileConfigValueSource valueSource = requestForValueSource.getValueSource();
            this.mSampledAccessListener.onMobileConfigAccess(j, LOGGING_FREQUENCY, valueSource.getSource() > 1, valueSource.name(), this.mManagerHolder.syncFetchReason(), Double.toString(d2), this.mUseTranslationTable);
        }
        return d2;
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public double getDouble(long j, double d) {
        validateParamSpecifier(j, 4);
        return contextForConfigParameter(j).getDouble(j, d);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public int getInt(long j, int i) {
        validateParamSpecifier(j, 2);
        return contextForConfigParameter(j).getInt(j, i);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public int getIntWithoutLogging(long j, int i) {
        validateParamSpecifier(j, 2);
        return contextForConfigParameter(j).getIntWithoutLogging(j, i);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public Map<Integer, Integer> getEmergencyPushInfo() {
        return latestContextForConfig(-1).getEmergencyPushInfo();
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public void logExposure(long j) {
        validateParamSpecifier(j, 0);
        contextForConfigParameter(j).logExposure(j);
    }

    @VisibleForTesting
    public MobileConfigContext getCachedLatestContext() {
        return this.mCachedLatestContext;
    }

    @Nullable
    public String getSchemaHashFromCachedLatestContext() {
        if (this.mCachedLatestContext instanceof MobileConfigContextV2Impl) {
            return ((MobileConfigContextV2Impl) this.mCachedLatestContext).getSchemaHash();
        }
        return null;
    }
}
