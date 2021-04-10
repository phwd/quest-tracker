package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.permissions;

import android.content.Context;
import com.oculus.library.model.App;

public class AppPermission {
    private final App mApp;
    private final PermissionGroup mGroup;
    private final String mLabel;
    private final String mName;

    public AppPermission(App app, String str, PermissionGroup permissionGroup, String str2) {
        this.mApp = app;
        this.mName = str;
        this.mGroup = permissionGroup;
        this.mLabel = str2;
    }

    public App getApp() {
        return this.mApp;
    }

    public String getName() {
        return this.mName;
    }

    public PermissionGroup getPermissionGroup() {
        return this.mGroup;
    }

    public String getLabel() {
        return this.mLabel;
    }

    public boolean isGranted(Context context) {
        return context.getPackageManager().checkPermission(this.mName, this.mApp.packageName) == 0;
    }
}
