package com.facebook.mobileconfig.factory;

public interface MobileConfigOverridesTable {
    boolean boolOverrideForParam(long j, boolean z);

    double doubleOverrideForParam(long j, double d);

    boolean hasBoolOverrideForParam(long j);

    boolean hasDoubleOverrideForParam(long j);

    boolean hasIntOverrideForParam(long j);

    boolean hasStringOverrideForParam(long j);

    long intOverrideForParam(long j, long j2);

    String stringOverrideForParam(long j, String str);
}
