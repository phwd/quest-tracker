package com.facebook.mobileconfig.factory;

public interface MobileConfigOverridesTable {
    boolean boolOverrideForParam(long j, boolean z);

    double doubleOverrideForParam(long j, double d);

    String experimentOverrideForUniverse(String str);

    String groupOverrideForUniverse(String str);

    boolean hasBoolOverrideForParam(long j);

    boolean hasDoubleOverrideForParam(long j);

    boolean hasIntOverrideForParam(long j);

    boolean hasNullOverrideForParam(long j);

    boolean hasOverrideForUniverse(String str);

    boolean hasStringOverrideForParam(long j);

    long intOverrideForParam(long j, long j2);

    void removeAllOverrides();

    void removeOverrideForParam(long j);

    void removeOverridesForQEUniverse(String str);

    String stringOverrideForParam(long j, String str);

    void updateOverrideForParam(long j, double d);

    void updateOverrideForParam(long j, long j2);

    void updateOverrideForParam(long j, String str);

    void updateOverrideForParam(long j, boolean z);

    void updateOverrideForQE(String str, String str2, String str3);
}
