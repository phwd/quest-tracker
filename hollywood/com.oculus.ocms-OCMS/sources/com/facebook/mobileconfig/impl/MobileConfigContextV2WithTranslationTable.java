package com.facebook.mobileconfig.impl;

import com.facebook.common.errorreporting.FbErrorReporter;
import com.facebook.debug.log.BLog;
import com.facebook.mobileconfig.MobileConfigManagerHolder;
import com.facebook.mobileconfig.factory.ExposureType;
import com.facebook.mobileconfig.factory.MobileConfigContext;
import com.facebook.mobileconfig.factory.MobileConfigOverridesTable;
import com.facebook.mobileconfig.specifier.MobileConfigSpecifierUtil;
import java.nio.ByteBuffer;
import java.util.Map;
import javax.annotation.Nullable;
import javax.inject.Provider;

public class MobileConfigContextV2WithTranslationTable extends MobileConfigContextV2Impl implements MobileConfigContext {
    private static final String TAG = "MobileConfigContextV2WithTranslationTable";
    private final long[][] mTranslationTable;

    @Override // com.facebook.mobileconfig.impl.MobileConfigContextV2Impl, com.facebook.mobileconfig.factory.MobileConfigContext
    @Nullable
    public Map<Integer, Integer> getEmergencyPushInfo() {
        return null;
    }

    public MobileConfigContextV2WithTranslationTable(ByteBuffer byteBuffer, MobileConfigManagerHolder mobileConfigManagerHolder, MobileConfigOverridesTable mobileConfigOverridesTable, @Nullable Provider<FbErrorReporter> provider, long[][] jArr) {
        super(byteBuffer, mobileConfigManagerHolder, mobileConfigOverridesTable, provider);
        this.mTranslationTable = jArr;
    }

    @Override // com.facebook.mobileconfig.impl.MobileConfigContextV2Impl, com.facebook.mobileconfig.impl.MobileConfigContextBase
    public boolean getBooleanFromStorage(long j, boolean z, boolean z2) {
        long updatedSpecifier = getUpdatedSpecifier(j, this.mTranslationTable);
        if (updatedSpecifier == 0) {
            return z;
        }
        return super.getBooleanFromStorage(updatedSpecifier, z, z2);
    }

    @Override // com.facebook.mobileconfig.impl.MobileConfigContextV2Impl, com.facebook.mobileconfig.impl.MobileConfigContextBase
    public long getLongFromStorage(long j, long j2, boolean z) {
        long updatedSpecifier = getUpdatedSpecifier(j, this.mTranslationTable);
        if (updatedSpecifier == 0) {
            return j2;
        }
        return super.getLongFromStorage(updatedSpecifier, j2, z);
    }

    @Override // com.facebook.mobileconfig.impl.MobileConfigContextV2Impl, com.facebook.mobileconfig.impl.MobileConfigContextBase
    public double getDoubleFromStorage(long j, double d, boolean z) {
        long updatedSpecifier = getUpdatedSpecifier(j, this.mTranslationTable);
        if (updatedSpecifier == 0) {
            return d;
        }
        return super.getDoubleFromStorage(updatedSpecifier, d, z);
    }

    @Override // com.facebook.mobileconfig.impl.MobileConfigContextV2Impl, com.facebook.mobileconfig.impl.MobileConfigContextBase
    public String getStringFromStorage(long j, String str, boolean z) {
        long updatedSpecifier = getUpdatedSpecifier(j, this.mTranslationTable);
        if (updatedSpecifier == 0) {
            return str;
        }
        return super.getStringFromStorage(updatedSpecifier, str, z);
    }

    @Override // com.facebook.mobileconfig.impl.MobileConfigContextV2Impl, com.facebook.mobileconfig.impl.MobileConfigContextBase
    public int getLoggingModeWithoutCaptureStack(long j) {
        long updatedSpecifier = getUpdatedSpecifier(j, this.mTranslationTable);
        if (updatedSpecifier == 0) {
            return 0;
        }
        return super.getLoggingModeWithoutCaptureStack(updatedSpecifier);
    }

    @Override // com.facebook.mobileconfig.impl.MobileConfigContextV2Impl, com.facebook.mobileconfig.impl.MobileConfigContextBase
    public int getLoggingMode(long j) {
        long updatedSpecifier = getUpdatedSpecifier(j, this.mTranslationTable);
        if (updatedSpecifier == 0) {
            return 0;
        }
        return super.getLoggingMode(updatedSpecifier);
    }

    @Override // com.facebook.mobileconfig.impl.MobileConfigContextV2Impl, com.facebook.mobileconfig.impl.MobileConfigContextBase
    @Nullable
    public String getLoggingId(long j) {
        long updatedSpecifier = getUpdatedSpecifier(j, this.mTranslationTable);
        if (updatedSpecifier == 0) {
            return null;
        }
        return super.getLoggingId(updatedSpecifier);
    }

    @Override // com.facebook.mobileconfig.impl.MobileConfigContextV2Impl, com.facebook.mobileconfig.impl.MobileConfigContextBase
    public void logExposure(long j, ExposureType exposureType) {
        long updatedSpecifier = getUpdatedSpecifier(j, this.mTranslationTable);
        if (updatedSpecifier != 0) {
            super.logExposure(updatedSpecifier, exposureType);
        }
    }

    public static long getUpdatedSpecifier(long j, long[][] jArr) {
        if (jArr == null) {
            BLog.wtf(TAG, "TranslationTable passed was null");
        }
        int paramType = MobileConfigSpecifierUtil.getParamType(j) - 1;
        int slotId = MobileConfigSpecifierUtil.getSlotId(j);
        if (paramType < 0 || paramType >= jArr.length || slotId < 0 || jArr[paramType] == null || slotId >= jArr[paramType].length) {
            return 0;
        }
        long j2 = jArr[paramType][slotId];
        if (j2 == 0 || MobileConfigSpecifierUtil.getParamType(j2) == MobileConfigSpecifierUtil.getParamType(j)) {
            return j2;
        }
        BLog.wtf(TAG, String.format("Translated to invalid param for config idx: %d param key: %d", Integer.valueOf(MobileConfigSpecifierUtil.getConfigIndex(j)), Integer.valueOf(MobileConfigSpecifierUtil.getParamKey(j))));
        return 0;
    }
}
