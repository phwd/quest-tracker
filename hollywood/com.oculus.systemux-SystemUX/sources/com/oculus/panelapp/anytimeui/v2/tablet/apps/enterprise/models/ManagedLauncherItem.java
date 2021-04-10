package com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.models;

import com.oculus.library.model.App;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.UnknownSource;
import com.oculus.vrshell.SystemUXRoute;

public class ManagedLauncherItem {
    private String mDisplayName;
    private int mDrawableId;
    private App mIngestedApp;
    private ItemType mItemType;
    private String mPackageName;
    private SystemUXRoute mRoute;

    public enum ItemType {
        APP,
        INGESTED_APP,
        SYSTEM_UX_ROUTE_APP
    }

    private ManagedLauncherItem() {
    }

    public static ManagedLauncherItem createApp(String str, String str2) {
        ManagedLauncherItem managedLauncherItem = new ManagedLauncherItem();
        managedLauncherItem.mItemType = ItemType.APP;
        managedLauncherItem.mDisplayName = str;
        managedLauncherItem.mPackageName = str2;
        return managedLauncherItem;
    }

    public static ManagedLauncherItem createSystemUxRouteApp(String str, SystemUXRoute systemUXRoute, int i) {
        ManagedLauncherItem managedLauncherItem = new ManagedLauncherItem();
        managedLauncherItem.mItemType = ItemType.SYSTEM_UX_ROUTE_APP;
        managedLauncherItem.mDisplayName = str;
        managedLauncherItem.mRoute = systemUXRoute;
        managedLauncherItem.mDrawableId = i;
        return managedLauncherItem;
    }

    public static ManagedLauncherItem createIngestedApp(App app) {
        ManagedLauncherItem managedLauncherItem = new ManagedLauncherItem();
        managedLauncherItem.mItemType = ItemType.INGESTED_APP;
        managedLauncherItem.mIngestedApp = app;
        managedLauncherItem.mPackageName = app.packageName;
        return managedLauncherItem;
    }

    public static ManagedLauncherItem createUnknownSource(UnknownSource unknownSource) {
        ManagedLauncherItem managedLauncherItem = new ManagedLauncherItem();
        managedLauncherItem.mItemType = ItemType.APP;
        managedLauncherItem.mDisplayName = unknownSource.getApplicationName();
        managedLauncherItem.mPackageName = unknownSource.getPackageName();
        return managedLauncherItem;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public SystemUXRoute getRoute() {
        return this.mRoute;
    }

    public App getIngestedApp() {
        return this.mIngestedApp;
    }

    public int getDrawableId() {
        return this.mDrawableId;
    }

    public ItemType getItemType() {
        return this.mItemType;
    }
}
