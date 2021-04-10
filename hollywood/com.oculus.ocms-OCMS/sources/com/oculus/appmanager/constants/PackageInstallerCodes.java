package com.oculus.appmanager.constants;

public enum PackageInstallerCodes {
    SUCCESS(0),
    BLOCKED(2),
    ABORTED(3),
    INVALID(4),
    CONFLICT(5),
    STORAGE(6),
    INCOMPATIBLE(7);
    
    public final int code;

    private PackageInstallerCodes(int i) {
        this.code = i;
    }
}
