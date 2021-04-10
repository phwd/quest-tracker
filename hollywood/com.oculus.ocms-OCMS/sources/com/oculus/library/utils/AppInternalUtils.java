package com.oculus.library.utils;

import com.oculus.library.model.App;
import com.oculus.library.model.AppStatus;
import java.util.List;

public class AppInternalUtils {
    public static boolean areTwoAppsEqual(App app, App app2) {
        if (app == null || app2 == null) {
            if (app == app2) {
                return true;
            }
            return false;
        } else if (app.status != app2.status || app.cloudStorageStatus != app2.cloudStorageStatus) {
            return false;
        } else {
            if (app.packageName == null ? app2.packageName != null : !app.packageName.equals(app2.packageName)) {
                return false;
            }
            if (app.downloadSizeBytes != app2.downloadSizeBytes) {
                return false;
            }
            if ((app.status == AppStatus.DOWNLOADING && app.downloadedSizeBytes != app2.downloadedSizeBytes) || app.versionCode != app2.versionCode) {
                return false;
            }
            if (app.versionName == null ? app2.versionName != null : !app.versionName.equals(app2.versionName)) {
                return false;
            }
            if (app.latestVersionCode != app2.latestVersionCode) {
                return false;
            }
            if (app.latestVersionName == null ? app2.latestVersionName != null : !app.latestVersionName.equals(app2.latestVersionName)) {
                return false;
            }
            if (app.latestTargetSdkVersion != app2.latestTargetSdkVersion) {
                return false;
            }
            if (app.id == null ? app2.id != null : !app.id.equals(app2.id)) {
                return false;
            }
            if (app.displayName == null ? app2.displayName != null : !app.displayName.equals(app2.displayName)) {
                return false;
            }
            if (app.images == null ? app2.images != null : !app.images.equals(app2.images)) {
                return false;
            }
            if (!arePermissionsEqual(app.latestPermissions, app2.latestPermissions) || app.isUnseen != app2.isUnseen || app.recentActivityMs != app2.recentActivityMs || app.totalActivityMs != app2.totalActivityMs) {
                return false;
            }
            if (app.entitlementHash == null ? app2.entitlementHash != null : !app.entitlementHash.equals(app2.entitlementHash)) {
                return false;
            }
            if (app.appScopedUserId == null ? app2.appScopedUserId != null : !app.appScopedUserId.equals(app2.appScopedUserId)) {
                return false;
            }
            if (app.applicationGroupingId == null ? app2.applicationGroupingId != null : !app.applicationGroupingId.equals(app2.applicationGroupingId)) {
                return false;
            }
            if (app.applicationOrganizationId == null ? app2.applicationOrganizationId == null : app.applicationOrganizationId.equals(app2.applicationOrganizationId)) {
                return app.microphoneUsage == app2.microphoneUsage && app.minRecommendedVersionCode == app2.minRecommendedVersionCode && app.minRequiredVersionCode == app2.minRequiredVersionCode;
            }
            return false;
        }
    }

    private static boolean arePermissionsEqual(List<String> list, List<String> list2) {
        return (list == null || list2 == null) ? list == list2 : list.size() == list2.size() && list.containsAll(list2) && list2.containsAll(list);
    }
}
