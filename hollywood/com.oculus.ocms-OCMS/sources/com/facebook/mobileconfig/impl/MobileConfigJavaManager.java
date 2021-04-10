package com.facebook.mobileconfig.impl;

import android.content.res.AssetManager;
import android.util.Pair;
import com.facebook.common.iolite.FileUtils;
import com.facebook.debug.log.BLog;
import com.facebook.mobileconfig.ConsistencyType;
import com.facebook.mobileconfig.FBConfigUtils;
import com.facebook.mobileconfig.FBMobileConfigTable;
import com.facebook.mobileconfig.MobileConfigConstants;
import com.facebook.mobileconfig.MobileConfigCxxChangeListener;
import com.facebook.mobileconfig.MobileConfigEmergencyPushChangeListener;
import com.facebook.mobileconfig.MobileConfigJavaMmapHandle;
import com.facebook.mobileconfig.MobileConfigManagerHolder;
import com.facebook.mobileconfig.MobileConfigMmapHandle;
import com.facebook.mobileconfig.factory.ExposureType;
import com.facebook.mobileconfig.factory.MobileConfigOverridesTable;
import com.facebook.mobileconfig.metadata.ParamsMapList;
import com.facebook.mobileconfig.specifier.MobileConfigSpecifierUtil;
import com.facebook.stetho.common.Utf8Charset;
import com.oculus.util.constants.OculusConstants;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import javax.inject.Provider;

public class MobileConfigJavaManager implements MobileConfigManagerHolder {
    public static final String APP_UPGRADE_START = "MobileConfigJavaManager: Using translation table.";
    private static final String CONFIGTABLE_SUFFIX = ".mctable";
    public static final int CONFIG_TABLE_MAGIC = 123456;
    private static final int CURRENT_TT_VERSION = 2;
    public static final String NORMAL_START = "MobileConfigJavaManager: No sync fetch was needed";
    private static final String OVERRIDES_FILENAME = "mc_overrides.json";
    private static final String SESSIONLESS_DATA_DIR = "sessionless.data/";
    private static final String SESSION_DATA_DIR_SUFFIX = ".data/";
    private static final String TAG = "MobileConfigJavaManager";
    private final String mBufferPath;
    private final Set<MobileConfigExposure> mCachedExposures = new HashSet();
    private MobileConfigJavaMmapHandle mCachedMmapHandle = null;
    private final Set<Pair<Long, ExposureType>> mCachedSpecifierExposures = new HashSet();
    @Nullable
    private Provider<ParamsMapList> mParamsMap;
    private final String mSessionId;
    private final List<MobileConfigShadowResult> mShadowResults = new ArrayList();
    @Nullable
    private long[][] mTranslationTable = null;

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public void clearCurrentUserData() {
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public void clearEmergencyPushChannel() {
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public void clearOverrides() {
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public void deleteOldUserData(int i) {
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public String getConsistencyLoggingFlagsJSON() {
        return "Internal error: MobileConfig manager not yet initialized";
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public String getDataDirPath() {
        return "";
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public String getFrameworkStatus() {
        return "UNINITIALIZED";
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    @Nullable
    public MobileConfigOverridesTable getNewOverridesTable() {
        return null;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public String getSchemaString() {
        return "";
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean isConsistencyLoggingNeeded(ConsistencyType consistencyType) {
        return false;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean isFetchNeeded() {
        return false;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean isValid() {
        return true;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public void logConfigs(String str, ConsistencyType consistencyType, Map<String, String> map) {
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public void logStorageConsistency() {
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean registerConfigChangeListener(MobileConfigCxxChangeListener mobileConfigCxxChangeListener) {
        return false;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean saveCurrentParamsMapToDisk() {
        return false;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean setEpHandler(MobileConfigEmergencyPushChangeListener mobileConfigEmergencyPushChangeListener) {
        return false;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean tryUpdateConfigs() {
        return false;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean tryUpdateConfigsSynchronously(int i) {
        return false;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean updateConfigsSynchronouslyWithDefaultUpdater(int i) {
        return false;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean updateEmergencyPushConfigs() {
        return false;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean updateEmergencyPushConfigsSynchronously(int i) {
        return false;
    }

    public MobileConfigJavaManager(File file, String str) {
        this.mBufferPath = file.getAbsolutePath() + "/mobileconfig/";
        this.mSessionId = str;
    }

    @Nullable
    public static MobileConfigJavaManager createJavaManager(File file, String str, @Nullable AssetManager assetManager, boolean z) {
        return createJavaManager(file, str, null, assetManager, z, null);
    }

    @Nullable
    public static MobileConfigJavaManager createJavaManager(File file, String str, MobileConfigCaptureError mobileConfigCaptureError, @Nullable AssetManager assetManager, boolean z, @Nullable Provider<ParamsMapList> provider) {
        MobileConfigJavaManager mobileConfigJavaManager = new MobileConfigJavaManager(file, str);
        mobileConfigJavaManager.mParamsMap = provider;
        boolean doesNativeSchemaHashMismatch = mobileConfigJavaManager.doesNativeSchemaHashMismatch(mobileConfigCaptureError);
        if (!doesNativeSchemaHashMismatch || assetManager == null || !z) {
            if (doesNativeSchemaHashMismatch) {
                return null;
            }
            return mobileConfigJavaManager;
        } else if (mobileConfigJavaManager.populateTranslationTable(assetManager, file, str, getConfigTableSchemaHash(mobileConfigCaptureError, mobileConfigJavaManager.getLatestHandle()))) {
            return mobileConfigJavaManager;
        } else {
            return null;
        }
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public MobileConfigMmapHandle getLatestHandle() {
        if (this.mCachedMmapHandle == null) {
            String findPathToLatestFlatbuffer = findPathToLatestFlatbuffer();
            if (!findPathToLatestFlatbuffer.isEmpty()) {
                this.mCachedMmapHandle = new MobileConfigJavaMmapHandle(findPathToLatestFlatbuffer);
            }
        }
        return this.mCachedMmapHandle;
    }

    public List<MobileConfigShadowResult> getBufferedShadowResult() {
        List<MobileConfigShadowResult> unmodifiableList;
        synchronized (this.mShadowResults) {
            unmodifiableList = Collections.unmodifiableList(new ArrayList(this.mShadowResults));
        }
        return unmodifiableList;
    }

    public Set<Pair<Long, ExposureType>> getCachedSpecifierExposures() {
        Set<Pair<Long, ExposureType>> unmodifiableSet;
        synchronized (this.mCachedSpecifierExposures) {
            unmodifiableSet = Collections.unmodifiableSet(new HashSet(this.mCachedSpecifierExposures));
        }
        return unmodifiableSet;
    }

    public Set<MobileConfigExposure> getCachedExposures() {
        Set<MobileConfigExposure> unmodifiableSet;
        synchronized (this.mCachedExposures) {
            unmodifiableSet = Collections.unmodifiableSet(new HashSet(this.mCachedExposures));
        }
        return unmodifiableSet;
    }

    @Nullable
    public long[][] getTranslationTable() {
        return this.mTranslationTable;
    }

    private String getDirectoryPathForSession() {
        String str = this.mSessionId;
        if (str == null || str.isEmpty() || this.mSessionId.equals(OculusConstants.DEFAULT_ENTERPRISE_USER_ID)) {
            return this.mBufferPath + SESSIONLESS_DATA_DIR;
        }
        return this.mBufferPath + this.mSessionId + SESSION_DATA_DIR_SUFFIX;
    }

    private String findPathToLatestFlatbuffer() {
        int i;
        File[] listFiles = new File(getDirectoryPathForSession()).listFiles(new FilenameFilter() {
            /* class com.facebook.mobileconfig.impl.MobileConfigJavaManager.AnonymousClass1 */

            public boolean accept(File file, String str) {
                return str.endsWith(MobileConfigJavaManager.CONFIGTABLE_SUFFIX);
            }
        });
        if (listFiles == null) {
            return "";
        }
        String str = "";
        int i2 = -1;
        for (File file : listFiles) {
            String name = file.getName();
            try {
                i = Integer.parseInt(name.substring(0, name.length() - 8));
            } catch (NumberFormatException unused) {
                i = -1;
            }
            if (i > i2) {
                str = file.getAbsolutePath();
                i2 = i;
            }
        }
        return str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b9  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00c8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean populateTranslationTable(android.content.res.AssetManager r7, java.io.File r8, java.lang.String r9, @javax.annotation.Nullable java.lang.String r10) {
        /*
        // Method dump skipped, instructions count: 210
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.mobileconfig.impl.MobileConfigJavaManager.populateTranslationTable(android.content.res.AssetManager, java.io.File, java.lang.String, java.lang.String):boolean");
    }

    public static String getSchemaHashFromSpecToParamFile(ReadableByteChannel readableByteChannel) {
        try {
            ByteBuffer allocate = ByteBuffer.allocate(6);
            allocate.order(ByteOrder.BIG_ENDIAN);
            if (readableByteChannel.read(allocate) != 6) {
                return "";
            }
            allocate.flip();
            if (Short.valueOf(allocate.getShort()).shortValue() != Short.MAX_VALUE || Short.valueOf(allocate.getShort()).shortValue() != 2) {
                return "";
            }
            Short valueOf = Short.valueOf(allocate.getShort());
            ByteBuffer allocate2 = ByteBuffer.allocate(valueOf.shortValue());
            allocate2.order(ByteOrder.BIG_ENDIAN);
            if (readableByteChannel.read(allocate2) != valueOf.shortValue()) {
                return "";
            }
            allocate2.flip();
            return Charset.forName("US-ASCII").decode(allocate2).toString();
        } catch (IOException e) {
            BLog.wtf(TAG, e, "populateTranslationTableInternal: IOException");
            return "";
        }
    }

    public static boolean populateTranslationTableInternal(ReadableByteChannel readableByteChannel, ReadableByteChannel readableByteChannel2, long[][] jArr) {
        if (jArr == null) {
            BLog.wtf(TAG, "populateTranslationTableInternal: translationTable passed was null");
        }
        try {
            ByteBuffer allocate = ByteBuffer.allocate(16);
            allocate.order(ByteOrder.BIG_ENDIAN);
            ByteBuffer allocate2 = ByteBuffer.allocate(16);
            allocate2.order(ByteOrder.BIG_ENDIAN);
            int read = readableByteChannel.read(allocate);
            int read2 = readableByteChannel2.read(allocate2);
            while (read2 == 16 && read == 16) {
                allocate.flip();
                allocate2.flip();
                long j = allocate2.getLong();
                long j2 = allocate2.getLong();
                long j3 = allocate.getLong();
                long j4 = allocate.getLong();
                if (j3 == j) {
                    if (MobileConfigSpecifierUtil.getParamType(j2) != MobileConfigSpecifierUtil.getParamType(j4)) {
                        BLog.wtf(TAG, "populateTranslationTableInternal: invalid specifiers");
                        return false;
                    }
                    int paramType = MobileConfigSpecifierUtil.getParamType(j2) - 1;
                    int slotId = MobileConfigSpecifierUtil.getSlotId(j2);
                    if (paramType < 0 || paramType >= jArr.length || slotId < 0 || jArr[paramType] == null || slotId >= jArr[paramType].length) {
                        BLog.wtf(TAG, "populateTranslationTableInternal: Type/slot ids out of bound type_id: %d slot_id: %d", Integer.valueOf(paramType), Integer.valueOf(slotId));
                        return false;
                    }
                    jArr[paramType][slotId] = j4;
                    allocate2.clear();
                    int read3 = readableByteChannel2.read(allocate2);
                    allocate.clear();
                    read2 = read3;
                    read = readableByteChannel.read(allocate);
                } else if (j3 > j) {
                    allocate2.clear();
                    read2 = readableByteChannel2.read(allocate2);
                } else {
                    allocate.clear();
                    read = readableByteChannel.read(allocate);
                }
            }
            return true;
        } catch (IOException e) {
            BLog.wtf(TAG, e, "populateTranslationTableInternal: IOException");
            return false;
        } catch (BufferUnderflowException e2) {
            BLog.wtf(TAG, e2, "populateTranslationTableInternal: BufferUnderflowException");
            return false;
        }
    }

    public boolean doesNativeSchemaHashMismatch(MobileConfigCaptureError mobileConfigCaptureError) {
        String configTableSchemaHash = getConfigTableSchemaHash(mobileConfigCaptureError, getLatestHandle());
        if (configTableSchemaHash == null || configTableSchemaHash.isEmpty()) {
            return true;
        }
        int indexOf = configTableSchemaHash.indexOf(58);
        if (indexOf != -1) {
            configTableSchemaHash = configTableSchemaHash.substring(0, indexOf);
        }
        boolean z = !configTableSchemaHash.equals(getGeneratedSchemaHash());
        if (z && mobileConfigCaptureError != null) {
            mobileConfigCaptureError.setError(MobileConfigError.CONFIG_TABLE_SCHEMA_MISMATCH);
        }
        return z;
    }

    private static boolean doesNativeSchemaMatch(String str, String str2) {
        if (str == null || str.isEmpty() || str2 == null || str2.isEmpty()) {
            return false;
        }
        int indexOf = str.indexOf(58);
        if (indexOf != -1) {
            str = str.substring(0, indexOf);
        }
        int indexOf2 = str2.indexOf(58);
        if (indexOf2 != -1) {
            str2 = str2.substring(0, indexOf2);
        }
        return str.equals(str2);
    }

    public void cacheExposureSpecifier(long j, ExposureType exposureType) {
        synchronized (this.mCachedSpecifierExposures) {
            this.mCachedSpecifierExposures.add(new Pair<>(Long.valueOf(j), exposureType));
        }
    }

    @Nullable
    public static String getConfigTableSchemaHash(@Nullable MobileConfigCaptureError mobileConfigCaptureError, MobileConfigMmapHandle mobileConfigMmapHandle) {
        ByteBuffer javaByteBuffer = mobileConfigMmapHandle == null ? null : mobileConfigMmapHandle.getJavaByteBuffer();
        if (javaByteBuffer == null) {
            if (mobileConfigCaptureError != null) {
                mobileConfigCaptureError.setError(MobileConfigError.NO_CONFIG_TABLE_SCHEMA_HASH);
            }
            return "";
        }
        try {
            FBMobileConfigTable rootAsFBMobileConfigTable = FBMobileConfigTable.getRootAsFBMobileConfigTable(javaByteBuffer);
            if (rootAsFBMobileConfigTable.magic() == 123456) {
                return FBConfigUtils.getSchemaHashInternal(rootAsFBMobileConfigTable.schemaHashAsByteBuffer());
            }
            if (mobileConfigCaptureError != null) {
                mobileConfigCaptureError.setError(MobileConfigError.CONFIG_TABLE_MAGIC_MISMATCH);
            }
            return "";
        } catch (IndexOutOfBoundsException | OutOfMemoryError | BufferUnderflowException unused) {
            return "";
        } catch (IllegalArgumentException e) {
            BLog.wtf(TAG, e, "getConfigTableSchemaHash: IllegalArgumentException");
            return "";
        }
    }

    private String getGeneratedSchemaHash() {
        return MobileConfigConstants.SCHEMA_HASH;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean updateConfigs() {
        BLog.i(TAG, "updateConfigs");
        return false;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    @Nullable
    public MobileConfigOverridesTable getNewOverridesTableIfExists() {
        File file = new File(this.mBufferPath + OVERRIDES_FILENAME);
        if (this.mParamsMap == null || !file.exists() || this.mParamsMap.get() == null) {
            return null;
        }
        try {
            return new MobileConfigJavaOverridesTable(FileUtils.readFileToString(file, Charset.forName(Utf8Charset.NAME)), this.mTranslationTable, this.mParamsMap.get());
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public void logExposure(String str, String str2, String str3) {
        this.mCachedExposures.add(new MobileConfigExposure(str, str2, str3));
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public void logExposure(String str, String str2) {
        logExposure(str, str2, "");
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public void logShadowResult(String str, String str2, String str3, String str4, String str5, String str6) {
        synchronized (this.mShadowResults) {
            this.mShadowResults.add(new MobileConfigShadowResult(str, str2, str3, str4, str5, str6));
        }
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public String syncFetchReason() {
        return this.mTranslationTable != null ? APP_UPGRADE_START : NORMAL_START;
    }
}
