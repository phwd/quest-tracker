package com.facebook.mobileconfig.impl;

import com.facebook.common.errorreporting.FbErrorReporter;
import com.facebook.debug.log.BLog;
import com.facebook.mobileconfig.FBMobileConfigTableOptimized;
import com.facebook.mobileconfig.MobileConfigManagerHolder;
import com.facebook.mobileconfig.factory.ExposureType;
import com.facebook.mobileconfig.factory.MobileConfigOverridesTable;
import com.facebook.mobileconfig.specifier.MobileConfigSpecifierUtil;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;
import javax.inject.Provider;

public class MobileConfigContextV2Impl extends MobileConfigContextBase {
    private ExposureRateLimiter mAutoExposureRateLimiter;
    @Nullable
    private final FBMobileConfigTableOptimized mConfigTable;
    @Nullable
    private Provider<FbErrorReporter> mFbErrorReporterProvider;
    private final MobileConfigManagerHolder mManagerHolder;
    private ExposureRateLimiter mManualExposureRateLimiter;

    public MobileConfigContextV2Impl(ByteBuffer byteBuffer, MobileConfigManagerHolder mobileConfigManagerHolder, MobileConfigOverridesTable mobileConfigOverridesTable, Provider<FbErrorReporter> provider) {
        super(mobileConfigOverridesTable);
        ExposureRateLimiter exposureRateLimiter;
        this.mManagerHolder = mobileConfigManagerHolder;
        this.mFbErrorReporterProvider = provider;
        this.mConfigTable = (byteBuffer == null || byteBuffer.capacity() <= 0) ? null : FBMobileConfigTableOptimized.getRootAsFBMobileConfigTableOptimized(byteBuffer);
        try {
            int i = this.mConfigTable != null ? this.mConfigTable.loggingIdsLength() : 0;
            this.mAutoExposureRateLimiter = new ExposureRateLimiter(i);
            exposureRateLimiter = new ExposureRateLimiter(i);
        } catch (IndexOutOfBoundsException | BufferUnderflowException unused) {
            this.mAutoExposureRateLimiter = new ExposureRateLimiter(0);
            exposureRateLimiter = new ExposureRateLimiter(0);
        } catch (Throwable th) {
            this.mAutoExposureRateLimiter = new ExposureRateLimiter(0);
            this.mManualExposureRateLimiter = new ExposureRateLimiter(0);
            throw th;
        }
        this.mManualExposureRateLimiter = exposureRateLimiter;
    }

    @Override // com.facebook.mobileconfig.impl.MobileConfigContextBase
    public boolean isValid() {
        return this.mConfigTable != null;
    }

    @Override // com.facebook.mobileconfig.impl.MobileConfigContextBase
    public boolean getBooleanFromStorage(long j, boolean z, boolean z2) {
        if (this.mConfigTable == null || MobileConfigSpecifierUtil.getParamType(j) != 1) {
            return z;
        }
        int slotId = MobileConfigSpecifierUtil.getSlotId(j);
        try {
            byte bools = this.mConfigTable.bools(slotId);
            if (!z2 && MobileConfigTableUtil.hasLoggingIdFromMeta(bools)) {
                int i = MobileConfigTableUtil.INVALID_META;
                try {
                    i = this.mConfigTable.boolsMeta(slotId);
                } catch (IndexOutOfBoundsException | BufferUnderflowException unused) {
                }
                logExposure(i, ExposureType.AUTO_EXPOSURE);
            }
            return MobileConfigTableUtil.isNullFromMeta(bools) ? z : MobileConfigTableUtil.getBooleanValue(bools);
        } catch (IndexOutOfBoundsException | BufferUnderflowException unused2) {
            return z;
        }
    }

    @Override // com.facebook.mobileconfig.impl.MobileConfigContextBase
    public long getLongFromStorage(long j, long j2, boolean z) {
        if (this.mConfigTable != null && MobileConfigSpecifierUtil.getParamType(j) == 2) {
            int slotId = MobileConfigSpecifierUtil.getSlotId(j);
            int longsMeta = this.mConfigTable.longsMeta(slotId);
            if (!z && MobileConfigTableUtil.hasLoggingIdFromMeta(longsMeta)) {
                logExposure(longsMeta, ExposureType.AUTO_EXPOSURE);
            }
            if (MobileConfigTableUtil.isNullFromMeta(longsMeta)) {
                return j2;
            }
            try {
                return this.mConfigTable.longs(slotId);
            } catch (IndexOutOfBoundsException | BufferUnderflowException unused) {
            }
        }
        return j2;
    }

    @Override // com.facebook.mobileconfig.impl.MobileConfigContextBase
    public double getDoubleFromStorage(long j, double d, boolean z) {
        if (this.mConfigTable != null && MobileConfigSpecifierUtil.getParamType(j) == 4) {
            int slotId = MobileConfigSpecifierUtil.getSlotId(j);
            int doublesMeta = this.mConfigTable.doublesMeta(slotId);
            if (!z && MobileConfigTableUtil.hasLoggingIdFromMeta(doublesMeta)) {
                logExposure(doublesMeta, ExposureType.AUTO_EXPOSURE);
            }
            if (MobileConfigTableUtil.isNullFromMeta(doublesMeta)) {
                return d;
            }
            try {
                return this.mConfigTable.doubles(slotId);
            } catch (IndexOutOfBoundsException | BufferUnderflowException unused) {
            }
        }
        return d;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:? A[ExcHandler: IndexOutOfBoundsException | BufferUnderflowException (unused java.lang.Throwable), SYNTHETIC, Splitter:B:12:0x002a] */
    @Override // com.facebook.mobileconfig.impl.MobileConfigContextBase
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getStringFromStorage(long r3, java.lang.String r5, boolean r6) {
        /*
            r2 = this;
            com.facebook.mobileconfig.FBMobileConfigTableOptimized r0 = r2.mConfigTable
            if (r0 == 0) goto L_0x0033
            int r0 = com.facebook.mobileconfig.specifier.MobileConfigSpecifierUtil.getParamType(r3)
            r1 = 3
            if (r0 == r1) goto L_0x000c
            goto L_0x0033
        L_0x000c:
            int r3 = com.facebook.mobileconfig.specifier.MobileConfigSpecifierUtil.getSlotId(r3)
            com.facebook.mobileconfig.FBMobileConfigTableOptimized r4 = r2.mConfigTable
            int r4 = r4.stringsMeta(r3)
            if (r6 != 0) goto L_0x0023
            boolean r6 = com.facebook.mobileconfig.impl.MobileConfigTableUtil.hasLoggingIdFromMeta(r4)
            if (r6 == 0) goto L_0x0023
            com.facebook.mobileconfig.factory.ExposureType r6 = com.facebook.mobileconfig.factory.ExposureType.AUTO_EXPOSURE
            r2.logExposure(r4, r6)
        L_0x0023:
            boolean r4 = com.facebook.mobileconfig.impl.MobileConfigTableUtil.isNullFromMeta(r4)
            if (r4 == 0) goto L_0x002a
            return r5
        L_0x002a:
            com.facebook.mobileconfig.FBMobileConfigTableOptimized r4 = r2.mConfigTable     // Catch:{ IndexOutOfBoundsException | BufferUnderflowException -> 0x0033, IndexOutOfBoundsException | BufferUnderflowException -> 0x0033, IndexOutOfBoundsException | BufferUnderflowException -> 0x0033 }
            java.lang.String r3 = r4.strings(r3)     // Catch:{ IndexOutOfBoundsException | BufferUnderflowException -> 0x0033, IndexOutOfBoundsException | BufferUnderflowException -> 0x0033, IndexOutOfBoundsException | BufferUnderflowException -> 0x0033 }
            if (r3 == 0) goto L_0x0033
            return r3
        L_0x0033:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.mobileconfig.impl.MobileConfigContextV2Impl.getStringFromStorage(long, java.lang.String, boolean):java.lang.String");
    }

    private int getMeta(long j) {
        int paramType = MobileConfigSpecifierUtil.getParamType(j);
        int slotId = MobileConfigSpecifierUtil.getSlotId(j);
        if (paramType == 0) {
            logError(String.format("Null type specifier is given: %d", Long.valueOf(j)));
        } else if (paramType == 1) {
            return this.mConfigTable.boolsMeta(slotId);
        } else {
            if (paramType == 2) {
                return this.mConfigTable.longsMeta(slotId);
            }
            if (paramType == 3) {
                return this.mConfigTable.stringsMeta(slotId);
            }
            if (paramType == 4) {
                try {
                    return this.mConfigTable.doublesMeta(slotId);
                } catch (IndexOutOfBoundsException | BufferUnderflowException unused) {
                }
            }
        }
        logError(String.format("Fail to get meta for spec: %d", Long.valueOf(j)));
        return MobileConfigTableUtil.INVALID_META;
    }

    @Override // com.facebook.mobileconfig.impl.MobileConfigContextBase
    public void logExposure(long j, ExposureType exposureType) {
        if (this.mConfigTable != null) {
            int meta = getMeta(j);
            if (MobileConfigTableUtil.hasLoggingIdFromMeta(meta)) {
                logExposure(meta, exposureType);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[ExcHandler: IndexOutOfBoundsException | BufferUnderflowException (unused java.lang.Throwable), SYNTHETIC, Splitter:B:13:0x0023] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void logExposure(int r3, com.facebook.mobileconfig.factory.ExposureType r4) {
        /*
            r2 = this;
            com.facebook.mobileconfig.MobileConfigManagerHolder r0 = r2.mManagerHolder
            if (r0 == 0) goto L_0x0058
            com.facebook.mobileconfig.FBMobileConfigTableOptimized r0 = r2.mConfigTable
            if (r0 == 0) goto L_0x0058
            boolean r0 = com.facebook.mobileconfig.impl.MobileConfigTableUtil.hasLoggingIdFromMeta(r3)
            if (r0 != 0) goto L_0x000f
            goto L_0x0058
        L_0x000f:
            int r0 = com.facebook.mobileconfig.impl.MobileConfigTableUtil.getLoggingSlotIdFromMeta(r3)
            com.facebook.mobileconfig.factory.ExposureType r1 = com.facebook.mobileconfig.factory.ExposureType.AUTO_EXPOSURE
            if (r4 != r1) goto L_0x001a
            com.facebook.mobileconfig.impl.ExposureRateLimiter r1 = r2.mAutoExposureRateLimiter
            goto L_0x001c
        L_0x001a:
            com.facebook.mobileconfig.impl.ExposureRateLimiter r1 = r2.mManualExposureRateLimiter
        L_0x001c:
            boolean r0 = r1.take(r0)
            if (r0 != 0) goto L_0x0023
            return
        L_0x0023:
            com.facebook.mobileconfig.FBMobileConfigTableOptimized r0 = r2.mConfigTable     // Catch:{ IndexOutOfBoundsException | BufferUnderflowException -> 0x0058, IndexOutOfBoundsException | BufferUnderflowException -> 0x0058, IndexOutOfBoundsException | BufferUnderflowException -> 0x0058 }
            int r1 = com.facebook.mobileconfig.impl.MobileConfigTableUtil.getLoggingSlotIdFromMeta(r3)     // Catch:{ IndexOutOfBoundsException | BufferUnderflowException -> 0x0058, IndexOutOfBoundsException | BufferUnderflowException -> 0x0058, IndexOutOfBoundsException | BufferUnderflowException -> 0x0058 }
            java.lang.String r0 = r0.loggingIds(r1)     // Catch:{ IndexOutOfBoundsException | BufferUnderflowException -> 0x0058, IndexOutOfBoundsException | BufferUnderflowException -> 0x0058, IndexOutOfBoundsException | BufferUnderflowException -> 0x0058 }
            if (r0 == 0) goto L_0x0058
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L_0x0036
            goto L_0x0058
        L_0x0036:
            int r1 = com.facebook.mobileconfig.impl.MobileConfigTableUtil.getLoggingModeWithoutCaptureStackFromMeta(r3)
            if (r1 != 0) goto L_0x003d
            return
        L_0x003d:
            boolean r3 = com.facebook.mobileconfig.impl.MobileConfigTableUtil.isCaptureStackFromMeta(r3)
            if (r3 == 0) goto L_0x004d
            java.lang.Throwable r3 = new java.lang.Throwable
            r3.<init>()
            java.lang.String r3 = throwableToString(r3)
            goto L_0x004f
        L_0x004d:
            java.lang.String r3 = ""
        L_0x004f:
            com.facebook.mobileconfig.MobileConfigManagerHolder r1 = r2.mManagerHolder
            java.lang.String r4 = r4.getValue()
            r1.logExposure(r0, r4, r3)
        L_0x0058:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.mobileconfig.impl.MobileConfigContextV2Impl.logExposure(int, com.facebook.mobileconfig.factory.ExposureType):void");
    }

    @Override // com.facebook.mobileconfig.impl.MobileConfigContextBase
    public boolean hasValueFromStorage(long j) {
        if (!isValid()) {
            return false;
        }
        return !MobileConfigTableUtil.isNullFromMeta(getMeta(j));
    }

    @Override // com.facebook.mobileconfig.impl.MobileConfigContextBase
    public boolean hasServerValueFromStorage(long j) {
        if (!isValid()) {
            return false;
        }
        return !MobileConfigTableUtil.isMissingFromMeta(getMeta(j));
    }

    private void logError(String str) {
        BLog.e("MobileConfigContextV2Impl", str);
        Provider<FbErrorReporter> provider = this.mFbErrorReporterProvider;
        FbErrorReporter fbErrorReporter = provider != null ? provider.get() : null;
        if (fbErrorReporter != null) {
            fbErrorReporter.softReport("MobileConfigContextV2Impl", str, 100000);
        }
    }
}
