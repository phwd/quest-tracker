package com.oculus.appmanager.info.model;

import X.AnonymousClass006;
import android.util.Log;

/* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
public final class InstallerResultError extends Enum<InstallerResultError> {
    public static final /* synthetic */ InstallerResultError[] $VALUES;
    public static final InstallerResultError ASSET_NOT_FOUND;
    public static final InstallerResultError AUTHORIZATION_ERROR;
    public static final InstallerResultError CONSISTENCY_FAILURE;
    public static final InstallerResultError DOWNLOADER_DISABLED;
    public static final InstallerResultError DUPLICATE_REQUEST;
    public static final InstallerResultError INSTALLED_APP_NO_SIGNATURES;
    public static final InstallerResultError INSTALLED_APP_SIGNATURE_MISMATCH;
    @Deprecated
    public static final InstallerResultError INSTALLER_DISABLED;
    public static final InstallerResultError INSTALLER_ERROR;
    public static final InstallerResultError INTERNAL_EXCEPTION;
    public static final InstallerResultError INVALID_INSTALL_REQUEST;
    public static final InstallerResultError INVALID_REQUEST;
    public static final int INVALID_VALUE = 0;
    public static final InstallerResultError IO_ERROR;
    public static final InstallerResultError LICENSE_INSTALL_ERROR;
    public static final InstallerResultError LOW_STORAGE;
    public static final InstallerResultError MISSING_ASSET_FULL_VERSION_INFO;
    public static final InstallerResultError NETWORK_ERROR;
    public static final InstallerResultError NOT_INSTALLED;
    public static final InstallerResultError NOT_LOGGED_IN;
    public static final InstallerResultError NOT_MANAGED_BY_OCULUS;
    public static final InstallerResultError NO_ENTITLEMENT;
    public static final InstallerResultError NO_NEW_BINARIES_AVAILABLE;
    public static final InstallerResultError NO_STORAGE_PERMISSION;
    public static final InstallerResultError SERVER_RESPONSE_ERROR;
    public static final InstallerResultError SHIBA_ATTACK_PREVENTION;
    public static final InstallerResultError UNEXPECTED_CHANGE;
    public static final InstallerResultError UNINSTALLER_ERROR;
    public static final InstallerResultError UNKNOWN_ERROR;
    public static final InstallerResultError USER_CANCELLED;
    public static final InstallerResultError VERIFICATION_FAILURE;
    public final int code;

    static {
        InstallerResultError installerResultError = new InstallerResultError("UNKNOWN_ERROR", 0, 1);
        UNKNOWN_ERROR = installerResultError;
        InstallerResultError installerResultError2 = new InstallerResultError("LOW_STORAGE", 1, 2);
        LOW_STORAGE = installerResultError2;
        InstallerResultError installerResultError3 = new InstallerResultError("NO_ENTITLEMENT", 2, 3);
        NO_ENTITLEMENT = installerResultError3;
        InstallerResultError installerResultError4 = new InstallerResultError("NETWORK_ERROR", 3, 4);
        NETWORK_ERROR = installerResultError4;
        InstallerResultError installerResultError5 = new InstallerResultError("SERVER_RESPONSE_ERROR", 4, 5);
        SERVER_RESPONSE_ERROR = installerResultError5;
        InstallerResultError installerResultError6 = new InstallerResultError("INSTALLER_DISABLED", 5, 6);
        INSTALLER_DISABLED = installerResultError6;
        InstallerResultError installerResultError7 = new InstallerResultError("NO_STORAGE_PERMISSION", 6, 7);
        NO_STORAGE_PERMISSION = installerResultError7;
        InstallerResultError installerResultError8 = new InstallerResultError("DUPLICATE_REQUEST", 7, 8);
        DUPLICATE_REQUEST = installerResultError8;
        InstallerResultError installerResultError9 = new InstallerResultError("INSTALLER_ERROR", 8, 9);
        INSTALLER_ERROR = installerResultError9;
        InstallerResultError installerResultError10 = new InstallerResultError("USER_CANCELLED", 9, 10);
        USER_CANCELLED = installerResultError10;
        InstallerResultError installerResultError11 = new InstallerResultError("NOT_LOGGED_IN", 10, 11);
        NOT_LOGGED_IN = installerResultError11;
        InstallerResultError installerResultError12 = new InstallerResultError("INVALID_INSTALL_REQUEST", 11, 12);
        INVALID_INSTALL_REQUEST = installerResultError12;
        InstallerResultError installerResultError13 = new InstallerResultError("IO_ERROR", 12, 13);
        IO_ERROR = installerResultError13;
        InstallerResultError installerResultError14 = new InstallerResultError("UNEXPECTED_CHANGE", 13, 14);
        UNEXPECTED_CHANGE = installerResultError14;
        InstallerResultError installerResultError15 = new InstallerResultError("VERIFICATION_FAILURE", 14, 15);
        VERIFICATION_FAILURE = installerResultError15;
        InstallerResultError installerResultError16 = new InstallerResultError("INTERNAL_EXCEPTION", 15, 16);
        INTERNAL_EXCEPTION = installerResultError16;
        InstallerResultError installerResultError17 = new InstallerResultError("CONSISTENCY_FAILURE", 16, 17);
        CONSISTENCY_FAILURE = installerResultError17;
        InstallerResultError installerResultError18 = new InstallerResultError("DOWNLOADER_DISABLED", 17, 18);
        DOWNLOADER_DISABLED = installerResultError18;
        InstallerResultError installerResultError19 = new InstallerResultError("SHIBA_ATTACK_PREVENTION", 18, 19);
        SHIBA_ATTACK_PREVENTION = installerResultError19;
        InstallerResultError installerResultError20 = new InstallerResultError("INSTALLED_APP_SIGNATURE_MISMATCH", 19, 20);
        INSTALLED_APP_SIGNATURE_MISMATCH = installerResultError20;
        InstallerResultError installerResultError21 = new InstallerResultError("INSTALLED_APP_NO_SIGNATURES", 20, 21);
        INSTALLED_APP_NO_SIGNATURES = installerResultError21;
        InstallerResultError installerResultError22 = new InstallerResultError("NO_NEW_BINARIES_AVAILABLE", 21, 22);
        NO_NEW_BINARIES_AVAILABLE = installerResultError22;
        InstallerResultError installerResultError23 = new InstallerResultError("NOT_MANAGED_BY_OCULUS", 22, 23);
        NOT_MANAGED_BY_OCULUS = installerResultError23;
        InstallerResultError installerResultError24 = new InstallerResultError("NOT_INSTALLED", 23, 24);
        NOT_INSTALLED = installerResultError24;
        InstallerResultError installerResultError25 = new InstallerResultError("UNINSTALLER_ERROR", 24, 25);
        UNINSTALLER_ERROR = installerResultError25;
        InstallerResultError installerResultError26 = new InstallerResultError("ASSET_NOT_FOUND", 25, 26);
        ASSET_NOT_FOUND = installerResultError26;
        InstallerResultError installerResultError27 = new InstallerResultError("LICENSE_INSTALL_ERROR", 26, 27);
        LICENSE_INSTALL_ERROR = installerResultError27;
        InstallerResultError installerResultError28 = new InstallerResultError("INVALID_REQUEST", 27, 28);
        INVALID_REQUEST = installerResultError28;
        InstallerResultError installerResultError29 = new InstallerResultError("MISSING_ASSET_FULL_VERSION_INFO", 28, 29);
        MISSING_ASSET_FULL_VERSION_INFO = installerResultError29;
        InstallerResultError installerResultError30 = new InstallerResultError("AUTHORIZATION_ERROR", 29, 30);
        AUTHORIZATION_ERROR = installerResultError30;
        InstallerResultError[] installerResultErrorArr = new InstallerResultError[30];
        System.arraycopy(new InstallerResultError[]{installerResultError, installerResultError2, installerResultError3, installerResultError4, installerResultError5, installerResultError6, installerResultError7, installerResultError8, installerResultError9, installerResultError10, installerResultError11, installerResultError12, installerResultError13, installerResultError14, installerResultError15, installerResultError16, installerResultError17, installerResultError18, installerResultError19, installerResultError20, installerResultError21, installerResultError22, installerResultError23, installerResultError24, installerResultError25, installerResultError26, installerResultError27}, 0, installerResultErrorArr, 0, 27);
        System.arraycopy(new InstallerResultError[]{installerResultError28, installerResultError29, installerResultError30}, 0, installerResultErrorArr, 27, 3);
        $VALUES = installerResultErrorArr;
    }

    public static InstallerResultError valueOf(String str) {
        return (InstallerResultError) Enum.valueOf(InstallerResultError.class, str);
    }

    public static InstallerResultError[] values() {
        return (InstallerResultError[]) $VALUES.clone();
    }

    public InstallerResultError(String str, int i, int i2) {
        this.code = i2;
    }

    public static InstallerResultError fromInt(int i) {
        InstallerResultError[] values = values();
        for (InstallerResultError installerResultError : values) {
            if (installerResultError.code == i) {
                return installerResultError;
            }
        }
        Log.w("InstallerResultError", AnonymousClass006.A03("Unable to parse error from code: ", i));
        return UNKNOWN_ERROR;
    }
}
