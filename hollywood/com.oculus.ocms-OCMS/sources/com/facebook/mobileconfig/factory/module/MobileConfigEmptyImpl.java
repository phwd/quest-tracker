package com.facebook.mobileconfig.factory.module;

import android.content.res.Resources;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.mobileconfig.MobileConfigDefaults;
import com.facebook.mobileconfig.factory.MobileConfig;
import com.facebook.mobileconfig.factory.MobileConfigOptions;
import java.util.HashMap;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigEmptyImpl implements MobileConfig {
    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public boolean getBoolean(long j, boolean z) {
        return z;
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public boolean getBooleanWithOptions(long j, boolean z, MobileConfigOptions mobileConfigOptions) {
        return z;
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public double getDouble(long j, double d) {
        return d;
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public double getDoubleWithOptions(long j, double d, MobileConfigOptions mobileConfigOptions) {
        return d;
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public int getInt(long j, int i) {
        return i;
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public int getIntWithoutLogging(long j, int i) {
        return i;
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public long getLong(long j, long j2) {
        return j2;
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public long getLongWithOptions(long j, long j2, MobileConfigOptions mobileConfigOptions) {
        return j2;
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public String getString(long j, String str) {
        return str;
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public String getStringWithOptions(long j, String str, MobileConfigOptions mobileConfigOptions) {
        return str;
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfig
    public boolean isLoaded(boolean z) {
        return true;
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public void logExposure(long j) {
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfig
    public MobileConfig.MobileConfigValueState getCurrentMobileConfigState(boolean z) {
        return MobileConfig.MobileConfigValueState.DefaultValue;
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public boolean getBoolean(long j) {
        return MobileConfigDefaults.getBoolDefaults(j);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public boolean getBooleanWithOptions(long j, MobileConfigOptions mobileConfigOptions) {
        return MobileConfigDefaults.getBoolDefaults(j);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public long getLong(long j) {
        return MobileConfigDefaults.getLongDefaults(j);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public long getLongWithOptions(long j, MobileConfigOptions mobileConfigOptions) {
        return MobileConfigDefaults.getLongDefaults(j);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public String getString(long j) {
        return MobileConfigDefaults.getStringDefaults(j);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public String getStringWithOptions(long j, MobileConfigOptions mobileConfigOptions) {
        return MobileConfigDefaults.getStringDefaults(j);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public String getStringWithOptions(long j, int i, Resources resources, MobileConfigOptions mobileConfigOptions) {
        return resources.getString(i);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public String getString(long j, int i, Resources resources) {
        return resources.getString(i);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public double getDouble(long j) {
        return MobileConfigDefaults.getDoubleDefaults(j);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public double getDoubleWithOptions(long j, MobileConfigOptions mobileConfigOptions) {
        return MobileConfigDefaults.getDoubleDefaults(j);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public Map<Integer, Integer> getEmergencyPushInfo() {
        return new HashMap();
    }
}
