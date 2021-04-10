package com.facebook.mobileconfig.impl;

import android.content.res.Resources;
import com.facebook.mobileconfig.MobileConfigDefaults;
import com.facebook.mobileconfig.factory.ExposureType;
import com.facebook.mobileconfig.factory.MobileConfigContext;
import com.facebook.mobileconfig.factory.MobileConfigOptions;
import com.facebook.mobileconfig.factory.MobileConfigOverridesTable;
import com.facebook.mobileconfig.factory.MobileConfigValueSource;
import com.facebook.mobileconfig.specifier.MobileConfigSpecifierUtil;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.annotation.Nullable;

public abstract class MobileConfigContextBase implements MobileConfigContext {
    public static final String FBT_STRING_NULL_VALUE = "__fbt_null__";
    private static final String TAG = "MobileConfigContextBase";
    @Nullable
    private volatile MobileConfigOverridesTable mOverridesTable;

    public abstract boolean getBooleanFromStorage(long j, boolean z, boolean z2);

    public abstract double getDoubleFromStorage(long j, double d, boolean z);

    @Nullable
    public abstract String getLoggingId(long j);

    public abstract int getLoggingMode(long j);

    public abstract int getLoggingModeWithoutCaptureStack(long j);

    public abstract long getLongFromStorage(long j, long j2, boolean z);

    public abstract String getStringFromStorage(long j, String str, boolean z);

    public abstract boolean hasServerValueFromStorage(long j);

    public abstract boolean hasValueFromStorage(long j);

    public abstract boolean isValid();

    public abstract void logExposure(long j, ExposureType exposureType);

    protected MobileConfigContextBase(MobileConfigOverridesTable mobileConfigOverridesTable) {
        this.mOverridesTable = mobileConfigOverridesTable;
    }

    private MobileConfigValueSource getParamValueSource(long j) {
        if (!isValid()) {
            return MobileConfigValueSource.DEFAULT__NO_DATA_ON_DISK;
        }
        if (hasValueFromStorage(j)) {
            return MobileConfigValueSource.SERVER;
        }
        if (hasServerValueFromStorage(j)) {
            return MobileConfigValueSource.DEFAULT__SERVER_RETURNED_NULL;
        }
        return MobileConfigValueSource.DEFAULT__MISSING_SERVER_VALUE;
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public boolean getBoolean(long j) {
        return getBooleanWithOptions(j, MobileConfigOptions.NONE);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public boolean getBoolean(long j, boolean z) {
        return getBooleanWithOptions(j, z, MobileConfigOptions.NONE);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public boolean getBooleanWithOptions(long j, MobileConfigOptions mobileConfigOptions) {
        return getBooleanWithOptions(j, MobileConfigDefaults.getBoolDefaults(j), mobileConfigOptions);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public boolean getBooleanWithOptions(long j, boolean z, MobileConfigOptions mobileConfigOptions) {
        if (this.mOverridesTable != null && this.mOverridesTable.hasBoolOverrideForParam(j)) {
            if (mobileConfigOptions.isValueSourceRequested()) {
                mobileConfigOptions.setValueSource(MobileConfigValueSource.OVERRIDE);
            }
            return this.mOverridesTable.boolOverrideForParam(j, z);
        } else if (!mobileConfigOptions.isValueSourceRequested()) {
            return getBooleanFromStorage(j, z, mobileConfigOptions.isWithoutLoggingRequested());
        } else {
            MobileConfigValueSource paramValueSource = getParamValueSource(j);
            mobileConfigOptions.setValueSource(paramValueSource);
            if (paramValueSource == MobileConfigValueSource.SERVER || paramValueSource == MobileConfigValueSource.DEFAULT__SERVER_RETURNED_NULL) {
                return getBooleanFromStorage(j, z, mobileConfigOptions.isWithoutLoggingRequested());
            }
            return z;
        }
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public long getLong(long j) {
        return getLongWithOptions(j, MobileConfigOptions.NONE);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public long getLong(long j, long j2) {
        return getLongWithOptions(j, j2, MobileConfigOptions.NONE);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public long getLongWithOptions(long j, MobileConfigOptions mobileConfigOptions) {
        return getLongWithOptions(j, MobileConfigDefaults.getLongDefaults(j), mobileConfigOptions);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public long getLongWithOptions(long j, long j2, MobileConfigOptions mobileConfigOptions) {
        if (this.mOverridesTable != null && this.mOverridesTable.hasIntOverrideForParam(j)) {
            if (mobileConfigOptions.isValueSourceRequested()) {
                mobileConfigOptions.setValueSource(MobileConfigValueSource.OVERRIDE);
            }
            return this.mOverridesTable.intOverrideForParam(j, j2);
        } else if (!mobileConfigOptions.isValueSourceRequested()) {
            return getLongFromStorage(j, j2, mobileConfigOptions.isWithoutLoggingRequested());
        } else {
            MobileConfigValueSource paramValueSource = getParamValueSource(j);
            mobileConfigOptions.setValueSource(paramValueSource);
            if (paramValueSource == MobileConfigValueSource.SERVER || paramValueSource == MobileConfigValueSource.DEFAULT__SERVER_RETURNED_NULL) {
                return getLongFromStorage(j, j2, mobileConfigOptions.isWithoutLoggingRequested());
            }
            return j2;
        }
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public double getDouble(long j) {
        return getDoubleWithOptions(j, MobileConfigOptions.NONE);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public double getDouble(long j, double d) {
        return getDoubleWithOptions(j, d, MobileConfigOptions.NONE);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public double getDoubleWithOptions(long j, MobileConfigOptions mobileConfigOptions) {
        return getDoubleWithOptions(j, MobileConfigDefaults.getDoubleDefaults(j), mobileConfigOptions);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public double getDoubleWithOptions(long j, double d, MobileConfigOptions mobileConfigOptions) {
        if (this.mOverridesTable != null && this.mOverridesTable.hasDoubleOverrideForParam(j)) {
            if (mobileConfigOptions.isValueSourceRequested()) {
                mobileConfigOptions.setValueSource(MobileConfigValueSource.OVERRIDE);
            }
            return this.mOverridesTable.doubleOverrideForParam(j, d);
        } else if (!mobileConfigOptions.isValueSourceRequested()) {
            return getDoubleFromStorage(j, d, mobileConfigOptions.isWithoutLoggingRequested());
        } else {
            MobileConfigValueSource paramValueSource = getParamValueSource(j);
            mobileConfigOptions.setValueSource(paramValueSource);
            if (paramValueSource == MobileConfigValueSource.SERVER || paramValueSource == MobileConfigValueSource.DEFAULT__SERVER_RETURNED_NULL) {
                return getDoubleFromStorage(j, d, mobileConfigOptions.isWithoutLoggingRequested());
            }
            return d;
        }
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public String getString(long j) {
        return getStringWithOptions(j, MobileConfigOptions.NONE);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public String getString(long j, String str) {
        return getStringWithOptions(j, str, MobileConfigOptions.NONE);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public String getString(long j, int i, Resources resources) {
        return getStringWithOptions(j, i, resources, MobileConfigOptions.NONE);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public String getStringWithOptions(long j, MobileConfigOptions mobileConfigOptions) {
        return getStringWithOptions(j, MobileConfigDefaults.getStringDefaults(j), mobileConfigOptions);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public String getStringWithOptions(long j, String str, MobileConfigOptions mobileConfigOptions) {
        if (this.mOverridesTable != null && this.mOverridesTable.hasStringOverrideForParam(j)) {
            if (mobileConfigOptions.isValueSourceRequested()) {
                mobileConfigOptions.setValueSource(MobileConfigValueSource.OVERRIDE);
            }
            String stringOverrideForParam = this.mOverridesTable.stringOverrideForParam(j, str);
            return "__fbt_null__".equals(stringOverrideForParam) ? str : stringOverrideForParam;
        } else if (!mobileConfigOptions.isValueSourceRequested()) {
            return getStringFromStorage(j, str, mobileConfigOptions.isWithoutLoggingRequested());
        } else {
            MobileConfigValueSource paramValueSource = getParamValueSource(j);
            mobileConfigOptions.setValueSource(paramValueSource);
            if (paramValueSource == MobileConfigValueSource.SERVER || paramValueSource == MobileConfigValueSource.DEFAULT__SERVER_RETURNED_NULL) {
                return getStringFromStorage(j, str, mobileConfigOptions.isWithoutLoggingRequested());
            }
            return str;
        }
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public String getStringWithOptions(long j, int i, Resources resources, MobileConfigOptions mobileConfigOptions) {
        String stringWithOptions = getStringWithOptions(j, null, mobileConfigOptions);
        return stringWithOptions != null ? stringWithOptions : resources.getString(i);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public int getInt(long j, int i) {
        return getInt(j, i, false);
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public int getIntWithoutLogging(long j, int i) {
        return getInt(j, i, true);
    }

    public int getInt(long j, int i, boolean z) {
        MobileConfigOptions mobileConfigOptions;
        long j2 = (long) i;
        if (z) {
            mobileConfigOptions = MobileConfigOptions.WITHOUT_LOGGING;
        } else {
            mobileConfigOptions = MobileConfigOptions.NONE;
        }
        long longWithOptions = getLongWithOptions(j, j2, mobileConfigOptions);
        int i2 = (int) longWithOptions;
        return ((long) i2) == longWithOptions ? i2 : i;
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigContext
    public void logExposure(long j) {
        logExposure(j, ExposureType.MANUAL_EXPOSURE);
    }

    public static String throwableToString(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.close();
        return stringWriter.toString();
    }

    public boolean hasOverride(long j) {
        if (this.mOverridesTable == null) {
            return false;
        }
        int paramType = MobileConfigSpecifierUtil.getParamType(j);
        if (paramType == 1) {
            return this.mOverridesTable.hasBoolOverrideForParam(j);
        }
        if (paramType == 2) {
            return this.mOverridesTable.hasIntOverrideForParam(j);
        }
        if (paramType == 3) {
            return this.mOverridesTable.hasStringOverrideForParam(j);
        }
        if (paramType != 4) {
            return false;
        }
        return this.mOverridesTable.hasDoubleOverrideForParam(j);
    }

    public synchronized void refreshOverridesTable(MobileConfigOverridesTable mobileConfigOverridesTable) {
        this.mOverridesTable = mobileConfigOverridesTable;
    }
}
