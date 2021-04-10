package com.oculus.appmanager.info.model;

import android.util.Log;

public enum InstallerResultError {
    UNKNOWN_ERROR(1),
    LOW_STORAGE(2),
    NO_ENTITLEMENT(3),
    NETWORK_ERROR(4),
    SERVER_RESPONSE_ERROR(5),
    INSTALLER_DISABLED(6),
    NO_STORAGE_PERMISSION(7),
    DUPLICATE_REQUEST(8),
    INSTALLER_ERROR(9),
    USER_CANCELLED(10),
    NOT_LOGGED_IN(11),
    INVALID_INSTALL_REQUEST(12),
    IO_ERROR(13),
    UNEXPECTED_CHANGE(14),
    VERIFICATION_FAILURE(15),
    INTERNAL_EXCEPTION(16),
    CONSISTENCY_FAILURE(17),
    DOWNLOADER_DISABLED(18),
    SHIBA_ATTACK_PREVENTION(19),
    INSTALLED_APP_SIGNATURE_MISMATCH(20),
    INSTALLED_APP_NO_SIGNATURES(21),
    NO_NEW_BINARIES_AVAILABLE(22),
    NOT_MANAGED_BY_OCULUS(23),
    NOT_INSTALLED(24),
    UNINSTALLER_ERROR(25),
    ASSET_NOT_FOUND(26),
    LICENSE_INSTALL_ERROR(27),
    INVALID_REQUEST(28),
    MISSING_ASSET_FULL_VERSION_INFO(29);
    
    public final int code;

    private InstallerResultError(int i) {
        this.code = i;
    }

    public static InstallerResultError fromInt(int i) {
        InstallerResultError[] values = values();
        for (InstallerResultError installerResultError : values) {
            if (installerResultError.code == i) {
                return installerResultError;
            }
        }
        Log.w(InstallerResultError.class.getSimpleName(), "Unable to parse error from code: " + i);
        return UNKNOWN_ERROR;
    }
}
