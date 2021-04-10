package com.oculus.panelapp.anytimeui.v2.abtest;

import android.content.Context;
import android.util.Log;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIPanelAppBase;

public class StoreOptionalityExperiment {
    private static final String TAG = LoggingUtil.tag(StoreOptionalityExperiment.class);
    private boolean mBarButtonsWithLabelsEnabled;
    private Context mContext;
    private boolean mGetMoreAppsButtonEnabled;
    private boolean mLibraryHeaderColoredTilesEnabled;
    private AnytimeUIPanelAppBase mPanelApp;
    private boolean mStoreAlwaysFirstAppTileEnabled;
    private boolean mStorePinnedToBarPositionLeftEnabled;
    private boolean mStorePinnedToBarPositionRightEnabled;
    private boolean mStorePresentInAppTilesEnabled;
    private boolean mSystemAppsAsTilesEnabled;

    private String boolToLogString(boolean z) {
        return z ? "1" : "0";
    }

    public void destroy() {
    }

    public StoreOptionalityExperiment(AnytimeUIPanelAppBase anytimeUIPanelAppBase) {
        this.mPanelApp = anytimeUIPanelAppBase;
        this.mContext = anytimeUIPanelAppBase.getContext();
        initialize();
    }

    private void initialize() {
        deviceConfigAccuracyLogging();
        this.mSystemAppsAsTilesEnabled = this.mPanelApp.isGKEnabled(Gatekeeper.AUI_V2_STORE_OP_SYSTEM_APPS_AS_TILES);
        this.mLibraryHeaderColoredTilesEnabled = this.mPanelApp.isGKEnabled(Gatekeeper.AUI_V2_STORE_OP_LIBRARY_HEADER_COLORED_TILES);
        this.mStorePresentInAppTilesEnabled = this.mPanelApp.isGKEnabled(Gatekeeper.AUI_V2_STORE_OP_STORE_IN_APP_TILES);
        this.mStorePinnedToBarPositionRightEnabled = this.mPanelApp.isGKEnabled(Gatekeeper.AUI_V2_STORE_OP_STORE_IN_BAR_POSITION_RIGHT);
        this.mStorePinnedToBarPositionLeftEnabled = this.mPanelApp.isGKEnabled(Gatekeeper.AUI_V2_STORE_OP_STORE_IN_BAR_POSITION_LEFT);
        this.mBarButtonsWithLabelsEnabled = this.mPanelApp.isGKEnabled(Gatekeeper.AUI_V2_STORE_OP_BAR_LABELS_ENABLED);
        this.mGetMoreAppsButtonEnabled = this.mPanelApp.isGKEnabled(Gatekeeper.AUI_V2_STORE_OP_GET_MORE_APPS);
        this.mStoreAlwaysFirstAppTileEnabled = this.mPanelApp.isGKEnabled(Gatekeeper.AUI_V2_STORE_OP_STORE_ALWAYS_FIRST_APP_TILE);
        logDCValues();
    }

    public boolean isLibraryHeaderEnabled() {
        return !isSystemAppsAsTilesEnabled();
    }

    public boolean isLibraryHeaderEnabledWithColoredTiles() {
        return this.mLibraryHeaderColoredTilesEnabled;
    }

    public boolean isSystemAppsAsTilesEnabled() {
        return this.mSystemAppsAsTilesEnabled;
    }

    public boolean isStorePresentInAppTilesEnabled() {
        return isSystemAppsAsTilesEnabled() && this.mStorePresentInAppTilesEnabled;
    }

    public boolean isStorePinnedToBar() {
        return this.mStorePinnedToBarPositionRightEnabled || this.mStorePinnedToBarPositionLeftEnabled;
    }

    public boolean isStorePinnedToBarPositionRightEnabled() {
        return this.mStorePinnedToBarPositionRightEnabled;
    }

    public boolean isStorePinnedToBarPositionLeftEnabled() {
        return this.mStorePinnedToBarPositionLeftEnabled;
    }

    public boolean isBarButtonsWithLabelsEnabled() {
        return this.mBarButtonsWithLabelsEnabled;
    }

    public boolean isGetMoreAppsButtonEnabled() {
        return this.mGetMoreAppsButtonEnabled;
    }

    public boolean isStoreAlwaysFirstAppTileEnabled() {
        return this.mStoreAlwaysFirstAppTileEnabled;
    }

    private void logDCValues() {
        this.mPanelApp.rawLogEvent("oculus_auiv2_library_store_optionality_v23", "system_apps_as_tiles_enabled", boolToLogString(this.mSystemAppsAsTilesEnabled), "library_header_colored_tiles_enabled", boolToLogString(this.mLibraryHeaderColoredTilesEnabled), "store_present_in_app_tiles_enabled", boolToLogString(this.mStorePresentInAppTilesEnabled), "store_in_bar_position_right_enabled", boolToLogString(this.mStorePinnedToBarPositionRightEnabled), "store_in_bar_position_left_enabled", boolToLogString(this.mStorePinnedToBarPositionLeftEnabled), "bar_button_labels_enabled", boolToLogString(this.mBarButtonsWithLabelsEnabled), "get_more_apps_button_enabled", boolToLogString(this.mGetMoreAppsButtonEnabled), "store_always_first_app_tile_enabled", boolToLogString(this.mStoreAlwaysFirstAppTileEnabled));
        logcatDCValues();
    }

    private void logcatDCValues() {
        Log.d(TAG, "Store Optionality params");
        Log.d(TAG, String.format("mSystemAppsAsTilesEnabled: %b", Boolean.valueOf(this.mSystemAppsAsTilesEnabled)));
        Log.d(TAG, String.format("mLibraryHeaderColoredTilesEnabled: %b", Boolean.valueOf(this.mLibraryHeaderColoredTilesEnabled)));
        Log.d(TAG, String.format("mStorePresentInAppTilesEnabled: %b", Boolean.valueOf(this.mStorePresentInAppTilesEnabled)));
        Log.d(TAG, String.format("mStorePinnedToBarPositionRightEnabled: %b", Boolean.valueOf(this.mStorePinnedToBarPositionRightEnabled)));
        Log.d(TAG, String.format("mStorePinnedToBarPositionLeftEnabled: %b", Boolean.valueOf(this.mStorePinnedToBarPositionLeftEnabled)));
        Log.d(TAG, String.format("mBarButtonsWithLabelsEnabled: %b", Boolean.valueOf(this.mBarButtonsWithLabelsEnabled)));
        Log.d(TAG, String.format("mGetMoreAppsButtonEnabled: %b", Boolean.valueOf(this.mGetMoreAppsButtonEnabled)));
        Log.d(TAG, String.format("mStoreAlwaysFirstAppTileEnabled: %b", Boolean.valueOf(this.mStoreAlwaysFirstAppTileEnabled)));
    }

    private void deviceConfigAccuracyLogging() {
        Log.d(TAG, "Device Config accuracy logging");
        AnytimeUIPanelAppBase anytimeUIPanelAppBase = this.mPanelApp;
        anytimeUIPanelAppBase.rawLogEvent("oculus_auiv2_library_store_optionality_dc_vs_gk_groups", "gk_group_1", boolToLogString(anytimeUIPanelAppBase.isGKEnabled(Gatekeeper.AUI_V2_STORE_OP_GK_GROUP_1)), "dc_group_1", boolToLogString(DeviceConfigHelper.getBoolean(this.mContext, Gatekeeper.STORE_OPTIONALITY_GROUP_1)), "gk_group_2", boolToLogString(this.mPanelApp.isGKEnabled(Gatekeeper.AUI_V2_STORE_OP_GK_GROUP_2)), "dc_group_2", boolToLogString(DeviceConfigHelper.getBoolean(this.mContext, Gatekeeper.STORE_OPTIONALITY_GROUP_2)), "gk_group_3", boolToLogString(this.mPanelApp.isGKEnabled(Gatekeeper.AUI_V2_STORE_OP_GK_GROUP_3)), "dc_group_3", boolToLogString(DeviceConfigHelper.getBoolean(this.mContext, Gatekeeper.STORE_OPTIONALITY_GROUP_3)), "gk_group_4", boolToLogString(this.mPanelApp.isGKEnabled(Gatekeeper.AUI_V2_STORE_OP_GK_GROUP_4)), "dc_group_4", boolToLogString(DeviceConfigHelper.getBoolean(this.mContext, Gatekeeper.STORE_OPTIONALITY_GROUP_4)), "gk_group_5", boolToLogString(this.mPanelApp.isGKEnabled(Gatekeeper.AUI_V2_STORE_OP_GK_GROUP_5)), "dc_group_5", boolToLogString(DeviceConfigHelper.getBoolean(this.mContext, Gatekeeper.STORE_OPTIONALITY_GROUP_5)));
    }
}
