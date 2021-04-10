package com.facebook.mobileconfig.impl;

import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.mobileconfig.MobileConfigDefaults;
import com.facebook.mobileconfig.factory.MobileConfigContext;
import com.facebook.mobileconfig.factory.MobileConfigOptions;
import com.facebook.mobileconfig.factory.MobileConfigValueSource;
import com.facebook.mobileconfig.impl.MC;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigTranslationTableVerifier {
    private static final String TAG = "MobileConfigTranslationTableVerifier";

    public static boolean verify(MobileConfigContext mobileConfigContext) {
        BLog.i(TAG, "Start to verify translation table.");
        boolean z = true;
        if (!((((((((((((verifyTranslationTableBoolParam(mobileConfigContext, MC.mobileconfig_verify_tt.bool1, "bool1")) && verifyTranslationTableBoolParam(mobileConfigContext, MC.mobileconfig_verify_tt.bool2, "bool2")) && verifyTranslationTableBoolParam(mobileConfigContext, MC.mobileconfig_verify_tt.bool3, "bool3")) && verifyTranslationTableBoolParam(mobileConfigContext, MC.mobileconfig_verify_tt.bool4, "bool4")) && verifyTranslationTableLongParam(mobileConfigContext, MC.mobileconfig_verify_tt.int1, "int1")) && verifyTranslationTableLongParam(mobileConfigContext, MC.mobileconfig_verify_tt.int2, "int2")) && verifyTranslationTableLongParam(mobileConfigContext, MC.mobileconfig_verify_tt.int3, "int3")) && verifyTranslationTableDoubleParam(mobileConfigContext, MC.mobileconfig_verify_tt.double1, "double1")) && verifyTranslationTableDoubleParam(mobileConfigContext, MC.mobileconfig_verify_tt.double2, "double2")) && verifyTranslationTableDoubleParam(mobileConfigContext, MC.mobileconfig_verify_tt.double3, "double3")) && verifyTranslationTableStringParam(mobileConfigContext, MC.mobileconfig_verify_tt.string1, "string1")) && verifyTranslationTableStringParam(mobileConfigContext, MC.mobileconfig_verify_tt.string2, "string2")) || !verifyTranslationTableStringParam(mobileConfigContext, MC.mobileconfig_verify_tt.string3, "string3")) {
            z = false;
        }
        BLog.i(TAG, "Translation table is valid: %s.", z ? "yes" : "no");
        return z;
    }

    private static boolean verifyTranslationTableBoolParam(MobileConfigContext mobileConfigContext, long j, String str) {
        boolean boolDefaults = MobileConfigDefaults.getBoolDefaults(j);
        MobileConfigOptions requestForValueSource = MobileConfigOptions.create().requestForValueSource();
        return logErrorIfMismatch(Boolean.valueOf(boolDefaults), Boolean.valueOf(mobileConfigContext.getBooleanWithOptions(j, requestForValueSource)), str, requestForValueSource);
    }

    private static boolean verifyTranslationTableLongParam(MobileConfigContext mobileConfigContext, long j, String str) {
        long longDefaults = MobileConfigDefaults.getLongDefaults(j);
        MobileConfigOptions requestForValueSource = MobileConfigOptions.create().requestForValueSource();
        return logErrorIfMismatch(Long.valueOf(longDefaults), Long.valueOf(mobileConfigContext.getLongWithOptions(j, requestForValueSource)), str, requestForValueSource);
    }

    private static boolean verifyTranslationTableDoubleParam(MobileConfigContext mobileConfigContext, long j, String str) {
        double doubleDefaults = MobileConfigDefaults.getDoubleDefaults(j);
        MobileConfigOptions requestForValueSource = MobileConfigOptions.create().requestForValueSource();
        return logErrorIfMismatch(Double.valueOf(doubleDefaults), Double.valueOf(mobileConfigContext.getDoubleWithOptions(j, requestForValueSource)), str, requestForValueSource);
    }

    private static boolean verifyTranslationTableStringParam(MobileConfigContext mobileConfigContext, long j, String str) {
        String stringDefaults = MobileConfigDefaults.getStringDefaults(j);
        MobileConfigOptions requestForValueSource = MobileConfigOptions.create().requestForValueSource();
        return logErrorIfMismatch(stringDefaults, mobileConfigContext.getStringWithOptions(j, requestForValueSource), str, requestForValueSource);
    }

    private static <T> boolean logErrorIfMismatch(T t, T t2, String str, MobileConfigOptions mobileConfigOptions) {
        if (t.equals(t2) || mobileConfigOptions.getValueSource() == MobileConfigValueSource.OVERRIDE) {
            return true;
        }
        BLog.wtf(TAG, "Failed to verify mobileconfig_verify_tt.%s, expected:%s, actual:%s. actual_src:%s", str, t, t2, mobileConfigOptions.getValueSource());
        return false;
    }
}
