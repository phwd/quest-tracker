package com.facebook.mobileconfig.impl;

import com.facebook.debug.log.BLog;
import com.facebook.mobileconfig.ConsistencyType;
import com.facebook.mobileconfig.MobileConfigCxxChangeListener;
import com.facebook.mobileconfig.MobileConfigEmergencyPushChangeListener;
import com.facebook.mobileconfig.MobileConfigManagerHolder;
import com.facebook.mobileconfig.MobileConfigManagerHolderNoop;
import com.facebook.mobileconfig.MobileConfigMmapHandle;
import com.facebook.mobileconfig.factory.MobileConfig;
import com.facebook.mobileconfig.factory.MobileConfigOverridesTable;
import java.util.Map;

public class MobileConfigManagerSingletonHolder implements MobileConfigManagerHolder {
    private static final Class<?> TAG = MobileConfigManagerSingletonHolder.class;
    private MobileConfig mMobileConfig;
    private volatile MobileConfigManagerHolder mMobileConfigManagerHolder = new MobileConfigManagerHolderNoop();

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0020, code lost:
        if ((r10 instanceof com.facebook.mobileconfig.impl.MobileConfigFactoryImpl) == false) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0022, code lost:
        r10 = (com.facebook.mobileconfig.impl.MobileConfigContextBase) ((com.facebook.mobileconfig.impl.MobileConfigFactoryImpl) r10).contextForConfig(0);
        r1 = r1.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0033, code lost:
        if (r1.hasNext() == false) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0035, code lost:
        r2 = r1.next();
        r10.logExposure(((java.lang.Long) r2.first).longValue(), (com.facebook.mobileconfig.factory.ExposureType) r2.second);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004b, code lost:
        r10 = r0.getCachedExposures().iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0057, code lost:
        if (r10.hasNext() == false) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0059, code lost:
        r1 = r10.next();
        r9.logExposure(r1.loggingId, r1.exposureType, r1.stackTrace);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0069, code lost:
        r10 = r0.getBufferedShadowResult();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006d, code lost:
        if (r10 == null) goto L_0x0090;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006f, code lost:
        r10 = r10.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0077, code lost:
        if (r10.hasNext() == false) goto L_0x0090;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0079, code lost:
        r0 = r10.next();
        r9.logShadowResult(r0.configParameter, r0.cachedResult, r0.cachedResultFromFlatbuffer, r0.liveResult, r0.liveResultFromFlatbuffer, r0.debugString);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0090, code lost:
        com.facebook.debug.log.BLog.v(com.facebook.mobileconfig.impl.MobileConfigManagerSingletonHolder.TAG, "Updated managerHolder (java -> cpp): %s", java.lang.Boolean.valueOf(r9.isValid()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x009f, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0016, code lost:
        r0 = (com.facebook.mobileconfig.impl.MobileConfigJavaManager) r0;
        r1 = r0.getCachedSpecifierExposures();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r1 == null) goto L_0x004b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setManagerHolderInstance(com.facebook.mobileconfig.MobileConfigManagerHolder r9, com.facebook.mobileconfig.factory.MobileConfig r10) {
        /*
        // Method dump skipped, instructions count: 182
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.mobileconfig.impl.MobileConfigManagerSingletonHolder.setManagerHolderInstance(com.facebook.mobileconfig.MobileConfigManagerHolder, com.facebook.mobileconfig.factory.MobileConfig):void");
    }

    public synchronized MobileConfigManagerHolder getCurrentInstance() {
        return this.mMobileConfigManagerHolder;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public MobileConfigMmapHandle getLatestHandle() {
        return this.mMobileConfigManagerHolder.getLatestHandle();
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean updateConfigs() {
        BLog.i(TAG, "updateConfigs");
        return this.mMobileConfigManagerHolder.updateConfigs();
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean updateConfigsSynchronouslyWithDefaultUpdater(int i) {
        return this.mMobileConfigManagerHolder.updateConfigsSynchronouslyWithDefaultUpdater(i);
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean tryUpdateConfigs() {
        return this.mMobileConfigManagerHolder.tryUpdateConfigs();
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean tryUpdateConfigsSynchronously(int i) {
        return this.mMobileConfigManagerHolder.tryUpdateConfigsSynchronously(i);
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean updateEmergencyPushConfigs() {
        return this.mMobileConfigManagerHolder.updateEmergencyPushConfigs();
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean updateEmergencyPushConfigsSynchronously(int i) {
        return this.mMobileConfigManagerHolder.updateEmergencyPushConfigsSynchronously(i);
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean isFetchNeeded() {
        return this.mMobileConfigManagerHolder.isFetchNeeded();
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public String getFrameworkStatus() {
        return this.mMobileConfigManagerHolder.getFrameworkStatus();
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public MobileConfigOverridesTable getNewOverridesTable() {
        return this.mMobileConfigManagerHolder.getNewOverridesTable();
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public MobileConfigOverridesTable getNewOverridesTableIfExists() {
        return this.mMobileConfigManagerHolder.getNewOverridesTableIfExists();
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean isValid() {
        return this.mMobileConfigManagerHolder.isValid();
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public void deleteOldUserData(int i) {
        this.mMobileConfigManagerHolder.deleteOldUserData(i);
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public void clearCurrentUserData() {
        this.mMobileConfigManagerHolder.clearCurrentUserData();
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public void clearOverrides() {
        this.mMobileConfigManagerHolder.clearOverrides();
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public String getDataDirPath() {
        return this.mMobileConfigManagerHolder.getDataDirPath();
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean isConsistencyLoggingNeeded(ConsistencyType consistencyType) {
        return this.mMobileConfigManagerHolder.isConsistencyLoggingNeeded(consistencyType);
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean saveCurrentParamsMapToDisk() {
        return this.mMobileConfigManagerHolder.saveCurrentParamsMapToDisk();
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public void logStorageConsistency() {
        this.mMobileConfigManagerHolder.logStorageConsistency();
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public void logConfigs(String str, ConsistencyType consistencyType, Map<String, String> map) {
        this.mMobileConfigManagerHolder.logConfigs(str, consistencyType, map);
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public void logExposure(String str, String str2) {
        logExposure(str, str2, "");
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public void logExposure(String str, String str2, String str3) {
        this.mMobileConfigManagerHolder.logExposure(str, str2, str3);
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public void logShadowResult(String str, String str2, String str3, String str4, String str5, String str6) {
        this.mMobileConfigManagerHolder.logShadowResult(str, str2, str3, str4, str5, str6);
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public String getSchemaString() {
        return this.mMobileConfigManagerHolder.getSchemaString();
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public void clearEmergencyPushChannel() {
        this.mMobileConfigManagerHolder.clearEmergencyPushChannel();
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean registerConfigChangeListener(MobileConfigCxxChangeListener mobileConfigCxxChangeListener) {
        return this.mMobileConfigManagerHolder.registerConfigChangeListener(mobileConfigCxxChangeListener);
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean setEpHandler(MobileConfigEmergencyPushChangeListener mobileConfigEmergencyPushChangeListener) {
        return this.mMobileConfigManagerHolder.setEpHandler(mobileConfigEmergencyPushChangeListener);
    }

    public String getMobileConfigFieldValue(long j) {
        return MobileConfigDebugUtil.getMobileConfigFieldValue(this.mMobileConfig, j).get("value");
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public String syncFetchReason() {
        return this.mMobileConfigManagerHolder.syncFetchReason();
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public String getConsistencyLoggingFlagsJSON() {
        return this.mMobileConfigManagerHolder.getConsistencyLoggingFlagsJSON();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r0 = com.facebook.mobileconfig.impl.MobileConfigJavaManager.getConfigTableSchemaHash(null, getLatestHandle());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        if (r0 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002d, code lost:
        if (r0.equals(r4) == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002f, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean compareSchemaHash(java.lang.String r4) {
        /*
            r3 = this;
            com.facebook.mobileconfig.factory.MobileConfig r0 = r3.mMobileConfig
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            monitor-enter(r0)
            com.facebook.mobileconfig.factory.MobileConfig r2 = r3.mMobileConfig     // Catch:{ all -> 0x0031 }
            boolean r2 = r2 instanceof com.facebook.mobileconfig.impl.MobileConfigFactoryImpl     // Catch:{ all -> 0x0031 }
            if (r2 == 0) goto L_0x001d
            com.facebook.mobileconfig.factory.MobileConfig r2 = r3.mMobileConfig     // Catch:{ all -> 0x0031 }
            com.facebook.mobileconfig.impl.MobileConfigFactoryImpl r2 = (com.facebook.mobileconfig.impl.MobileConfigFactoryImpl) r2     // Catch:{ all -> 0x0031 }
            java.lang.String r2 = r2.getSchemaHashFromCachedLatestContext()     // Catch:{ all -> 0x0031 }
            if (r2 == 0) goto L_0x001d
            boolean r4 = r2.equals(r4)     // Catch:{ all -> 0x0031 }
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            return r4
        L_0x001d:
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            com.facebook.mobileconfig.MobileConfigMmapHandle r0 = r3.getLatestHandle()     // Catch:{ IndexOutOfBoundsException | OutOfMemoryError | BufferUnderflowException -> 0x0030 }
            r2 = 0
            java.lang.String r0 = com.facebook.mobileconfig.impl.MobileConfigJavaManager.getConfigTableSchemaHash(r2, r0)     // Catch:{ IndexOutOfBoundsException | OutOfMemoryError | BufferUnderflowException -> 0x0030 }
            if (r0 == 0) goto L_0x0030
            boolean r4 = r0.equals(r4)     // Catch:{ IndexOutOfBoundsException | OutOfMemoryError | BufferUnderflowException -> 0x0030 }
            if (r4 == 0) goto L_0x0030
            r1 = 1
        L_0x0030:
            return r1
        L_0x0031:
            r4 = move-exception
            monitor-exit(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.mobileconfig.impl.MobileConfigManagerSingletonHolder.compareSchemaHash(java.lang.String):boolean");
    }
}
