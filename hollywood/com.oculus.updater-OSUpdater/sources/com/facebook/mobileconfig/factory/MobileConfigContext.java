package com.facebook.mobileconfig.factory;

import com.facebook.infer.annotation.Functional;
import com.facebook.infer.annotation.ThreadSafe;

@ThreadSafe
public interface MobileConfigContext {
    @Functional
    boolean getBooleanWithOptions(long j, MobileConfigOptions mobileConfigOptions);

    @Functional
    boolean getBooleanWithOptions(long j, boolean z, MobileConfigOptions mobileConfigOptions);

    @Functional
    double getDoubleWithOptions(long j, double d, MobileConfigOptions mobileConfigOptions);

    @Functional
    double getDoubleWithOptions(long j, MobileConfigOptions mobileConfigOptions);

    @Functional
    long getLongWithOptions(long j, long j2, MobileConfigOptions mobileConfigOptions);

    @Functional
    long getLongWithOptions(long j, MobileConfigOptions mobileConfigOptions);

    @Functional
    String getStringWithOptions(long j, MobileConfigOptions mobileConfigOptions);

    @Functional
    String getStringWithOptions(long j, String str, MobileConfigOptions mobileConfigOptions);
}
