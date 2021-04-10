package com.facebook.mobileconfig.factory;

import android.content.res.Resources;
import com.facebook.infer.annotation.Functional;
import com.facebook.infer.annotation.ThreadSafe;
import java.util.Map;
import javax.annotation.Nullable;

@ThreadSafe
public interface MobileConfigContext {
    @Functional
    boolean getBoolean(long j);

    @Functional
    @Deprecated
    boolean getBoolean(long j, boolean z);

    @Functional
    boolean getBooleanWithOptions(long j, MobileConfigOptions mobileConfigOptions);

    @Functional
    boolean getBooleanWithOptions(long j, boolean z, MobileConfigOptions mobileConfigOptions);

    @Functional
    double getDouble(long j);

    @Functional
    @Deprecated
    double getDouble(long j, double d);

    @Functional
    double getDoubleWithOptions(long j, double d, MobileConfigOptions mobileConfigOptions);

    @Functional
    double getDoubleWithOptions(long j, MobileConfigOptions mobileConfigOptions);

    @Nullable
    @Functional
    Map<Integer, Integer> getEmergencyPushInfo();

    @Functional
    @Deprecated
    int getInt(long j, int i);

    @Functional
    @Deprecated
    int getIntWithoutLogging(long j, int i);

    @Functional
    @ThreadSafe
    long getLong(long j);

    @Functional
    @Deprecated
    long getLong(long j, long j2);

    @Functional
    long getLongWithOptions(long j, long j2, MobileConfigOptions mobileConfigOptions);

    @Functional
    long getLongWithOptions(long j, MobileConfigOptions mobileConfigOptions);

    @Functional
    String getString(long j);

    @Functional
    String getString(long j, int i, Resources resources);

    @Functional
    String getString(long j, String str);

    @Functional
    String getStringWithOptions(long j, int i, Resources resources, MobileConfigOptions mobileConfigOptions);

    @Functional
    String getStringWithOptions(long j, MobileConfigOptions mobileConfigOptions);

    @Functional
    String getStringWithOptions(long j, String str, MobileConfigOptions mobileConfigOptions);

    void logExposure(long j);
}
