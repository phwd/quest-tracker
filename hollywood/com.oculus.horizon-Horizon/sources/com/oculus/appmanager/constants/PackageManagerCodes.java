package com.oculus.appmanager.constants;

import com.oculus.defaultapps.DefaultAppsPreloadInstallerBroadcastReceiver;

public enum PackageManagerCodes {
    ALREADY_EXISTS(-1),
    INVALID_APK(-2),
    INVALID_URI(-3),
    INSUFFICIENT_STORAGE(-4),
    DUPLICATE_PACKAGE(-5),
    NO_SHARED_USER(-6),
    UPDATE_INCOMPATIBLE(-7),
    SHARED_USER_INCOMPATIBLE(-8),
    MISSING_SHARED_LIBRARY(-9),
    REPLACE_COULDNT_DELETE(-10),
    DEXOPT(-11),
    OLDER_SDK(-12),
    CONFLICTING_PROVIDER(-13),
    NEWER_SDK(-14),
    TEST_ONLY(-15),
    CPU_ABI_INCOMPATIBLE(-16),
    MISSING_FEATURE(-17),
    CONTAINER_ERROR(-18),
    INVALID_INSTALL_LOCATION(-19),
    MEDIA_UNAVAILABLE(-20),
    VERIFICATION_TIMEOUT(-21),
    VERIFICATION_FAILURE(-22),
    PACKAGE_CHANGED(-23),
    NOT_APK(-100),
    BAD_MANIFEST(-101),
    UNEXPECTED_EXCEPTION(-102),
    NO_CERTIFICATES(-103),
    INCONSISTENT_CERTIFICATES(-104),
    CERTIFICATE_ENCODING(-105),
    BAD_PACKAGE_NAME(-106),
    BAD_SHARED_USER_ID(-107),
    MANIFEST_MALFORMED(-108),
    MANIFEST_EMPTY(-109),
    INTERNAL_ERROR(DefaultAppsPreloadInstallerBroadcastReceiver.PACKAGE_MANAGER_INSTALL_FAILED_INTERNAL_ERROR);
    
    public final int code;

    /* access modifiers changed from: public */
    PackageManagerCodes(int i) {
        this.code = i;
    }
}
