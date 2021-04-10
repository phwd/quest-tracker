package com.oculus.panelapp.anytimeui.v2.tablet.apps.models;

import android.content.res.Resources;
import com.oculus.panelapp.anytimeui.R;
import java.util.HashMap;
import java.util.Map;

public enum LibraryAppTileContextMenuItem {
    DETAILS(R.string.anytime_tablet_library_app_tile_context_menu_item_details, "go_to_details"),
    FULL_VERSION(R.string.anytime_tablet_library_app_tile_context_menu_item_full_version, "go_to_full_version"),
    ACHIEVEMENTS(R.string.anytime_tablet_library_app_tile_context_menu_item_achievements, "achievements"),
    LEADERBOARDS(R.string.anytime_tablet_library_app_tile_context_menu_item_leaderboards, "leaderboards"),
    PERMISSIONS(R.string.anytime_tablet_library_app_tile_context_menu_item_permissions, "permissions"),
    UNINSTALL(R.string.anytime_tablet_library_app_tile_context_menu_item_uninstall, "uninstall");
    
    private static final Map<LibraryAppTileContextMenuItem, Integer> LIBRARY_APP_TILE_CONTEXT_MENU_ITEM_MAP = new HashMap();
    private final String mLoggingLabel;
    private final int mStringId;

    static {
        LibraryAppTileContextMenuItem[] values = values();
        for (LibraryAppTileContextMenuItem libraryAppTileContextMenuItem : values) {
            LIBRARY_APP_TILE_CONTEXT_MENU_ITEM_MAP.put(libraryAppTileContextMenuItem, Integer.valueOf(libraryAppTileContextMenuItem.getStringId()));
        }
    }

    private LibraryAppTileContextMenuItem(int i, String str) {
        this.mStringId = i;
        this.mLoggingLabel = str;
    }

    public int getStringId() {
        return this.mStringId;
    }

    public String getLoggingLabel() {
        return this.mLoggingLabel;
    }

    public String getLocalizedString(Resources resources) {
        return resources.getString(this.mStringId);
    }

    public static Map<LibraryAppTileContextMenuItem, Integer> getMap() {
        return LIBRARY_APP_TILE_CONTEXT_MENU_ITEM_MAP;
    }
}
