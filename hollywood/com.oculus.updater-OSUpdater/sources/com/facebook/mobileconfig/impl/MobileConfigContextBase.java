package com.facebook.mobileconfig.impl;

import com.facebook.mobileconfig.MobileConfigDefaults;
import com.facebook.mobileconfig.factory.ExposureType;
import com.facebook.mobileconfig.factory.MobileConfigContext;
import com.facebook.mobileconfig.factory.MobileConfigOptions;
import com.facebook.mobileconfig.factory.MobileConfigOverridesTable;
import com.facebook.mobileconfig.factory.MobileConfigValueSource;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.annotation.Nullable;

public abstract class MobileConfigContextBase implements MobileConfigContext {
    @Nullable
    private volatile MobileConfigOverridesTable mOverridesTable;

    public abstract boolean getBooleanFromStorage(long j, boolean z, boolean z2);

    public abstract double getDoubleFromStorage(long j, double d, boolean z);

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

    public static String throwableToString(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.close();
        return stringWriter.toString();
    }
}
