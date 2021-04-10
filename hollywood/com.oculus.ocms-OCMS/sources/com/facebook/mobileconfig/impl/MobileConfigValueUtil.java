package com.facebook.mobileconfig.impl;

import android.util.Pair;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.mobileconfig.MobileConfigDefaults;
import com.facebook.mobileconfig.factory.MobileConfig;
import com.facebook.mobileconfig.factory.MobileConfigOptions;
import com.facebook.mobileconfig.factory.MobileConfigOverridesTable;
import com.facebook.mobileconfig.metadata.ParamsMapEntry;
import com.facebook.mobileconfig.specifier.MobileConfigKeyUtils;
import com.facebook.mobileconfig.specifier.MobileConfigSpecifierUtil;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigValueUtil {
    public static final String FBT_STRING_NULL_VALUE = "__fbt_null__";
    private MobileConfigManagerSingletonHolder mManagerHolder;
    private MobileConfig mMobileConfig;
    protected MobileConfigOverridesTable mOverridesTable = this.mManagerHolder.getNewOverridesTable();
    private MobileConfig mSessionlessMobileConfig;

    public MobileConfigValueUtil(MobileConfigManagerSingletonHolder mobileConfigManagerSingletonHolder, MobileConfig mobileConfig, MobileConfig mobileConfig2) {
        this.mManagerHolder = mobileConfigManagerSingletonHolder;
        this.mMobileConfig = mobileConfig;
        this.mSessionlessMobileConfig = mobileConfig2;
    }

    public void getNewOverridesTable() {
        this.mOverridesTable = this.mManagerHolder.getNewOverridesTable();
    }

    public boolean hasOverrideTable() {
        return this.mOverridesTable != null;
    }

    public static boolean getDefaultBoolean(long j) {
        return MobileConfigDefaults.getBoolDefaults(j);
    }

    @Nullable
    private MobileConfig getMobileConfig(long j) {
        if (MobileConfigSpecifierUtil.getIsSessionless(j)) {
            return this.mSessionlessMobileConfig;
        }
        return this.mMobileConfig;
    }

    public boolean getCachedBoolean(long j) {
        MobileConfig mobileConfig = getMobileConfig(j);
        if (mobileConfig != null) {
            return mobileConfig.getBooleanWithOptions(j, MobileConfigOptions.WITHOUT_LOGGING);
        }
        return getDefaultBoolean(j);
    }

    public boolean isOverriddenBoolean(long j) {
        MobileConfigOverridesTable mobileConfigOverridesTable = this.mOverridesTable;
        if (mobileConfigOverridesTable != null) {
            return mobileConfigOverridesTable.hasBoolOverrideForParam(j);
        }
        return false;
    }

    public boolean getOverrideBoolean(long j) {
        MobileConfigOverridesTable mobileConfigOverridesTable = this.mOverridesTable;
        if (mobileConfigOverridesTable != null) {
            return mobileConfigOverridesTable.boolOverrideForParam(j, getDefaultBoolean(j));
        }
        return getDefaultBoolean(j);
    }

    public void setOverride(long j, boolean z) {
        MobileConfigOverridesTable mobileConfigOverridesTable = this.mOverridesTable;
        if (mobileConfigOverridesTable != null) {
            mobileConfigOverridesTable.updateOverrideForParam(j, z);
            refreshOverridesTable();
        }
    }

    public boolean getLatestBoolean(long j) {
        MobileConfig mobileConfig = getMobileConfig(j);
        if (mobileConfig != null) {
            return mobileConfig.getBooleanWithOptions(j, MobileConfigOptions.create().withoutLogging().getLatest());
        }
        return getDefaultBoolean(j);
    }

    public boolean getUIBoolean(long j) {
        if (isOverriddenBoolean(j)) {
            return getOverrideBoolean(j);
        }
        return getLatestBoolean(j);
    }

    public static double getDefaultDouble(long j) {
        return MobileConfigDefaults.getDoubleDefaults(j);
    }

    public double getCachedDouble(long j) {
        MobileConfig mobileConfig = getMobileConfig(j);
        if (mobileConfig != null) {
            return mobileConfig.getDoubleWithOptions(j, MobileConfigOptions.WITHOUT_LOGGING);
        }
        return getDefaultDouble(j);
    }

    public boolean isOverriddenDouble(long j) {
        MobileConfigOverridesTable mobileConfigOverridesTable = this.mOverridesTable;
        if (mobileConfigOverridesTable != null) {
            return mobileConfigOverridesTable.hasDoubleOverrideForParam(j);
        }
        return false;
    }

    public double getOverrideDouble(long j) {
        MobileConfigOverridesTable mobileConfigOverridesTable = this.mOverridesTable;
        if (mobileConfigOverridesTable != null) {
            return mobileConfigOverridesTable.doubleOverrideForParam(j, getDefaultDouble(j));
        }
        return getDefaultDouble(j);
    }

    public void setOverride(long j, double d) {
        MobileConfigOverridesTable mobileConfigOverridesTable = this.mOverridesTable;
        if (mobileConfigOverridesTable != null) {
            mobileConfigOverridesTable.updateOverrideForParam(j, d);
            refreshOverridesTable();
        }
    }

    public double getLatestDouble(long j) {
        MobileConfig mobileConfig = getMobileConfig(j);
        if (mobileConfig != null) {
            return mobileConfig.getDoubleWithOptions(j, MobileConfigOptions.create().withoutLogging().getLatest());
        }
        return getDefaultDouble(j);
    }

    public double getUIDouble(long j) {
        if (isOverriddenDouble(j)) {
            return getOverrideDouble(j);
        }
        return getLatestDouble(j);
    }

    public static long getDefaultLong(long j) {
        return MobileConfigDefaults.getLongDefaults(j);
    }

    public long getCachedLong(long j) {
        MobileConfig mobileConfig = getMobileConfig(j);
        if (mobileConfig != null) {
            return mobileConfig.getLongWithOptions(j, MobileConfigOptions.WITHOUT_LOGGING);
        }
        return getDefaultLong(j);
    }

    public boolean isOverriddenLong(long j) {
        MobileConfigOverridesTable mobileConfigOverridesTable = this.mOverridesTable;
        if (mobileConfigOverridesTable != null) {
            return mobileConfigOverridesTable.hasIntOverrideForParam(j);
        }
        return false;
    }

    public long getOverrideLong(long j) {
        MobileConfigOverridesTable mobileConfigOverridesTable = this.mOverridesTable;
        if (mobileConfigOverridesTable != null) {
            return mobileConfigOverridesTable.intOverrideForParam(j, getDefaultLong(j));
        }
        return getDefaultLong(j);
    }

    public void setOverride(long j, long j2) {
        MobileConfigOverridesTable mobileConfigOverridesTable = this.mOverridesTable;
        if (mobileConfigOverridesTable != null) {
            mobileConfigOverridesTable.updateOverrideForParam(j, j2);
            refreshOverridesTable();
        }
    }

    public long getLatestLong(long j) {
        MobileConfig mobileConfig = getMobileConfig(j);
        if (mobileConfig != null) {
            return mobileConfig.getLongWithOptions(j, MobileConfigOptions.create().withoutLogging().getLatest());
        }
        return getDefaultLong(j);
    }

    public long getUILong(long j) {
        if (isOverriddenLong(j)) {
            return getOverrideLong(j);
        }
        return getLatestLong(j);
    }

    public static String getDefaultString(long j) {
        return MobileConfigDefaults.getStringDefaults(j);
    }

    public String getCachedString(long j) {
        MobileConfig mobileConfig = getMobileConfig(j);
        if (mobileConfig != null) {
            return mobileConfig.getStringWithOptions(j, MobileConfigOptions.WITHOUT_LOGGING);
        }
        return getDefaultString(j);
    }

    public boolean isOverriddenString(long j) {
        MobileConfigOverridesTable mobileConfigOverridesTable = this.mOverridesTable;
        if (mobileConfigOverridesTable != null) {
            return mobileConfigOverridesTable.hasStringOverrideForParam(j);
        }
        return false;
    }

    public String getOverrideString(long j) {
        MobileConfigOverridesTable mobileConfigOverridesTable = this.mOverridesTable;
        if (mobileConfigOverridesTable != null) {
            return mobileConfigOverridesTable.stringOverrideForParam(j, getDefaultString(j));
        }
        return getDefaultString(j);
    }

    public void setOverride(long j, String str) {
        MobileConfigOverridesTable mobileConfigOverridesTable = this.mOverridesTable;
        if (mobileConfigOverridesTable != null) {
            mobileConfigOverridesTable.updateOverrideForParam(j, str);
            refreshOverridesTable();
        }
    }

    public String getLatestString(long j) {
        MobileConfig mobileConfig = getMobileConfig(j);
        if (mobileConfig != null) {
            return mobileConfig.getStringWithOptions(j, MobileConfigOptions.create().withoutLogging().getLatest());
        }
        return getDefaultString(j);
    }

    public String getUIString(long j) {
        if (isOverriddenString(j)) {
            return getOverrideString(j);
        }
        return getLatestString(j);
    }

    public void setQEOverride(String str, String str2, String str3) {
        MobileConfigOverridesTable mobileConfigOverridesTable = this.mOverridesTable;
        if (mobileConfigOverridesTable != null) {
            mobileConfigOverridesTable.updateOverrideForQE(str, str2, str3);
            refreshOverridesTable();
        }
    }

    public boolean hasOverrideForQEUniverse(String str) {
        MobileConfigOverridesTable mobileConfigOverridesTable = this.mOverridesTable;
        if (mobileConfigOverridesTable != null) {
            return mobileConfigOverridesTable.hasOverrideForUniverse(str);
        }
        return false;
    }

    public String getOverrideExperiment(String str) {
        MobileConfigOverridesTable mobileConfigOverridesTable = this.mOverridesTable;
        return mobileConfigOverridesTable != null ? mobileConfigOverridesTable.experimentOverrideForUniverse(str) : "";
    }

    public String getOverrideGroup(String str) {
        MobileConfigOverridesTable mobileConfigOverridesTable = this.mOverridesTable;
        return mobileConfigOverridesTable != null ? mobileConfigOverridesTable.groupOverrideForUniverse(str) : "";
    }

    public void removeAllOverrides() {
        MobileConfigOverridesTable mobileConfigOverridesTable = this.mOverridesTable;
        if (mobileConfigOverridesTable != null) {
            mobileConfigOverridesTable.removeAllOverrides();
            refreshOverridesTable();
        }
    }

    public void removeOverride(long j) {
        MobileConfigOverridesTable mobileConfigOverridesTable = this.mOverridesTable;
        if (mobileConfigOverridesTable != null) {
            mobileConfigOverridesTable.removeOverrideForParam(j);
            refreshOverridesTable();
        }
    }

    public void removeOverridesForQEUniverse(String str) {
        MobileConfigOverridesTable mobileConfigOverridesTable = this.mOverridesTable;
        if (mobileConfigOverridesTable != null) {
            mobileConfigOverridesTable.removeOverridesForQEUniverse(str);
            refreshOverridesTable();
        }
    }

    public void refreshOverridesTable() {
        getNewOverridesTable();
        MobileConfig mobileConfig = this.mSessionlessMobileConfig;
        if (mobileConfig instanceof MobileConfigFactoryImpl) {
            ((MobileConfigFactoryImpl) mobileConfig).refreshOverridesTable();
        }
        MobileConfig mobileConfig2 = this.mMobileConfig;
        if (mobileConfig2 instanceof MobileConfigFactoryImpl) {
            ((MobileConfigFactoryImpl) mobileConfig2).refreshOverridesTable();
        }
    }

    public static Pair<String, String> getUniqueKeyForEntry(ParamsMapEntry paramsMapEntry) {
        if (MobileConfigKeyUtils.isFakeParamKey(paramsMapEntry.key)) {
            return new Pair<>(paramsMapEntry.configName, paramsMapEntry.paramName);
        }
        return new Pair<>(paramsMapEntry.configName, Integer.toString(paramsMapEntry.key));
    }
}
