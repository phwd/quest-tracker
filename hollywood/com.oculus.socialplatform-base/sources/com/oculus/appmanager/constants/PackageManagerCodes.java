package com.oculus.appmanager.constants;

/* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
public final class PackageManagerCodes extends Enum<PackageManagerCodes> {
    public static final /* synthetic */ PackageManagerCodes[] $VALUES;
    public static final PackageManagerCodes ALREADY_EXISTS;
    public static final PackageManagerCodes BAD_MANIFEST;
    public static final PackageManagerCodes BAD_PACKAGE_NAME;
    public static final PackageManagerCodes BAD_SHARED_USER_ID;
    public static final PackageManagerCodes CERTIFICATE_ENCODING;
    public static final PackageManagerCodes CONFLICTING_PROVIDER;
    public static final PackageManagerCodes CONTAINER_ERROR;
    public static final PackageManagerCodes CPU_ABI_INCOMPATIBLE;
    public static final PackageManagerCodes DEXOPT;
    public static final PackageManagerCodes DUPLICATE_PACKAGE;
    public static final PackageManagerCodes INCONSISTENT_CERTIFICATES;
    public static final PackageManagerCodes INSUFFICIENT_STORAGE;
    public static final PackageManagerCodes INTERNAL_ERROR;
    public static final PackageManagerCodes INVALID_APK;
    public static final PackageManagerCodes INVALID_INSTALL_LOCATION;
    public static final PackageManagerCodes INVALID_URI;
    public static final PackageManagerCodes MANIFEST_EMPTY;
    public static final PackageManagerCodes MANIFEST_MALFORMED;
    public static final PackageManagerCodes MEDIA_UNAVAILABLE;
    public static final PackageManagerCodes MISSING_FEATURE;
    public static final PackageManagerCodes MISSING_SHARED_LIBRARY;
    public static final PackageManagerCodes NEWER_SDK;
    public static final PackageManagerCodes NOT_APK;
    public static final PackageManagerCodes NO_CERTIFICATES;
    public static final PackageManagerCodes NO_SHARED_USER;
    public static final PackageManagerCodes OLDER_SDK;
    public static final PackageManagerCodes PACKAGE_CHANGED;
    public static final PackageManagerCodes REPLACE_COULDNT_DELETE;
    public static final PackageManagerCodes SHARED_USER_INCOMPATIBLE;
    public static final PackageManagerCodes TEST_ONLY;
    public static final PackageManagerCodes UNEXPECTED_EXCEPTION;
    public static final PackageManagerCodes UPDATE_INCOMPATIBLE;
    public static final PackageManagerCodes VERIFICATION_FAILURE;
    public static final PackageManagerCodes VERIFICATION_TIMEOUT;
    public final int code;

    static {
        PackageManagerCodes packageManagerCodes = new PackageManagerCodes("ALREADY_EXISTS", 0, -1);
        ALREADY_EXISTS = packageManagerCodes;
        PackageManagerCodes packageManagerCodes2 = new PackageManagerCodes("INVALID_APK", 1, -2);
        INVALID_APK = packageManagerCodes2;
        PackageManagerCodes packageManagerCodes3 = new PackageManagerCodes("INVALID_URI", 2, -3);
        INVALID_URI = packageManagerCodes3;
        PackageManagerCodes packageManagerCodes4 = new PackageManagerCodes("INSUFFICIENT_STORAGE", 3, -4);
        INSUFFICIENT_STORAGE = packageManagerCodes4;
        PackageManagerCodes packageManagerCodes5 = new PackageManagerCodes("DUPLICATE_PACKAGE", 4, -5);
        DUPLICATE_PACKAGE = packageManagerCodes5;
        PackageManagerCodes packageManagerCodes6 = new PackageManagerCodes("NO_SHARED_USER", 5, -6);
        NO_SHARED_USER = packageManagerCodes6;
        PackageManagerCodes packageManagerCodes7 = new PackageManagerCodes("UPDATE_INCOMPATIBLE", 6, -7);
        UPDATE_INCOMPATIBLE = packageManagerCodes7;
        PackageManagerCodes packageManagerCodes8 = new PackageManagerCodes("SHARED_USER_INCOMPATIBLE", 7, -8);
        SHARED_USER_INCOMPATIBLE = packageManagerCodes8;
        PackageManagerCodes packageManagerCodes9 = new PackageManagerCodes("MISSING_SHARED_LIBRARY", 8, -9);
        MISSING_SHARED_LIBRARY = packageManagerCodes9;
        PackageManagerCodes packageManagerCodes10 = new PackageManagerCodes("REPLACE_COULDNT_DELETE", 9, -10);
        REPLACE_COULDNT_DELETE = packageManagerCodes10;
        PackageManagerCodes packageManagerCodes11 = new PackageManagerCodes("DEXOPT", 10, -11);
        DEXOPT = packageManagerCodes11;
        PackageManagerCodes packageManagerCodes12 = new PackageManagerCodes("OLDER_SDK", 11, -12);
        OLDER_SDK = packageManagerCodes12;
        PackageManagerCodes packageManagerCodes13 = new PackageManagerCodes("CONFLICTING_PROVIDER", 12, -13);
        CONFLICTING_PROVIDER = packageManagerCodes13;
        PackageManagerCodes packageManagerCodes14 = new PackageManagerCodes("NEWER_SDK", 13, -14);
        NEWER_SDK = packageManagerCodes14;
        PackageManagerCodes packageManagerCodes15 = new PackageManagerCodes("TEST_ONLY", 14, -15);
        TEST_ONLY = packageManagerCodes15;
        PackageManagerCodes packageManagerCodes16 = new PackageManagerCodes("CPU_ABI_INCOMPATIBLE", 15, -16);
        CPU_ABI_INCOMPATIBLE = packageManagerCodes16;
        PackageManagerCodes packageManagerCodes17 = new PackageManagerCodes("MISSING_FEATURE", 16, -17);
        MISSING_FEATURE = packageManagerCodes17;
        PackageManagerCodes packageManagerCodes18 = new PackageManagerCodes("CONTAINER_ERROR", 17, -18);
        CONTAINER_ERROR = packageManagerCodes18;
        PackageManagerCodes packageManagerCodes19 = new PackageManagerCodes("INVALID_INSTALL_LOCATION", 18, -19);
        INVALID_INSTALL_LOCATION = packageManagerCodes19;
        PackageManagerCodes packageManagerCodes20 = new PackageManagerCodes("MEDIA_UNAVAILABLE", 19, -20);
        MEDIA_UNAVAILABLE = packageManagerCodes20;
        PackageManagerCodes packageManagerCodes21 = new PackageManagerCodes("VERIFICATION_TIMEOUT", 20, -21);
        VERIFICATION_TIMEOUT = packageManagerCodes21;
        PackageManagerCodes packageManagerCodes22 = new PackageManagerCodes("VERIFICATION_FAILURE", 21, -22);
        VERIFICATION_FAILURE = packageManagerCodes22;
        PackageManagerCodes packageManagerCodes23 = new PackageManagerCodes("PACKAGE_CHANGED", 22, -23);
        PACKAGE_CHANGED = packageManagerCodes23;
        PackageManagerCodes packageManagerCodes24 = new PackageManagerCodes("NOT_APK", 23, -100);
        NOT_APK = packageManagerCodes24;
        PackageManagerCodes packageManagerCodes25 = new PackageManagerCodes("BAD_MANIFEST", 24, -101);
        BAD_MANIFEST = packageManagerCodes25;
        PackageManagerCodes packageManagerCodes26 = new PackageManagerCodes("UNEXPECTED_EXCEPTION", 25, -102);
        UNEXPECTED_EXCEPTION = packageManagerCodes26;
        PackageManagerCodes packageManagerCodes27 = new PackageManagerCodes("NO_CERTIFICATES", 26, -103);
        NO_CERTIFICATES = packageManagerCodes27;
        PackageManagerCodes packageManagerCodes28 = new PackageManagerCodes("INCONSISTENT_CERTIFICATES", 27, -104);
        INCONSISTENT_CERTIFICATES = packageManagerCodes28;
        PackageManagerCodes packageManagerCodes29 = new PackageManagerCodes("CERTIFICATE_ENCODING", 28, -105);
        CERTIFICATE_ENCODING = packageManagerCodes29;
        PackageManagerCodes packageManagerCodes30 = new PackageManagerCodes("BAD_PACKAGE_NAME", 29, -106);
        BAD_PACKAGE_NAME = packageManagerCodes30;
        PackageManagerCodes packageManagerCodes31 = new PackageManagerCodes("BAD_SHARED_USER_ID", 30, -107);
        BAD_SHARED_USER_ID = packageManagerCodes31;
        PackageManagerCodes packageManagerCodes32 = new PackageManagerCodes("MANIFEST_MALFORMED", 31, -108);
        MANIFEST_MALFORMED = packageManagerCodes32;
        PackageManagerCodes packageManagerCodes33 = new PackageManagerCodes("MANIFEST_EMPTY", 32, -109);
        MANIFEST_EMPTY = packageManagerCodes33;
        PackageManagerCodes packageManagerCodes34 = new PackageManagerCodes("INTERNAL_ERROR", 33, -110);
        INTERNAL_ERROR = packageManagerCodes34;
        PackageManagerCodes[] packageManagerCodesArr = new PackageManagerCodes[34];
        System.arraycopy(new PackageManagerCodes[]{packageManagerCodes, packageManagerCodes2, packageManagerCodes3, packageManagerCodes4, packageManagerCodes5, packageManagerCodes6, packageManagerCodes7, packageManagerCodes8, packageManagerCodes9, packageManagerCodes10, packageManagerCodes11, packageManagerCodes12, packageManagerCodes13, packageManagerCodes14, packageManagerCodes15, packageManagerCodes16, packageManagerCodes17, packageManagerCodes18, packageManagerCodes19, packageManagerCodes20, packageManagerCodes21, packageManagerCodes22, packageManagerCodes23, packageManagerCodes24, packageManagerCodes25, packageManagerCodes26, packageManagerCodes27}, 0, packageManagerCodesArr, 0, 27);
        System.arraycopy(new PackageManagerCodes[]{packageManagerCodes28, packageManagerCodes29, packageManagerCodes30, packageManagerCodes31, packageManagerCodes32, packageManagerCodes33, packageManagerCodes34}, 0, packageManagerCodesArr, 27, 7);
        $VALUES = packageManagerCodesArr;
    }

    public static PackageManagerCodes valueOf(String str) {
        return (PackageManagerCodes) Enum.valueOf(PackageManagerCodes.class, str);
    }

    public static PackageManagerCodes[] values() {
        return (PackageManagerCodes[]) $VALUES.clone();
    }

    public PackageManagerCodes(String str, int i, int i2) {
        this.code = i2;
    }
}
