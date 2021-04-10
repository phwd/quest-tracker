package com.oculus.common.packagescache;

public interface PackagesListener {

    public enum PackageAction {
        ADDED,
        REMOVED,
        UPDATED
    }

    void onPackageChanged(PackageAction packageAction, String str);
}
