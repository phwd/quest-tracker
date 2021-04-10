package com.oculus.panelapp.anytimeui.v2.tablet.profile;

import com.oculus.panelapp.anytimeui.R;
import java.util.HashMap;
import java.util.Map;

public enum ProfileContextMenuItem {
    SHARE(R.string.anytime_tablet_profile_share, R.drawable.oc_icon_share_filled_24_d2d2d2),
    PRIVACY(R.string.anytime_tablet_profile_privacy_settings, R.drawable.oc_icon_privacy_filled_24_d2d2d2),
    FB_REPORT(R.string.anytime_tablet_profile_report, R.drawable.oc_icon_friends_report_filled_24_d2d2d2),
    FB_BLOCK(R.string.anytime_tablet_profile_block, R.drawable.oc_icon_friends_block_filled_24_d2d2d2),
    VR_BLOCK(R.string.anytime_tablet_profile_block, R.drawable.oc_icon_friends_block_filled_24_d2d2d2),
    VR_UNBLOCK(R.string.anytime_tablet_profile_unblock, R.drawable.oc_icon_friends_block_filled_24_d2d2d2),
    VR_REPORT(R.string.anytime_tablet_profile_report, R.drawable.oc_icon_friends_report_filled_24_d2d2d2),
    UNFRIEND_USER(R.string.anytime_tablet_profile_unfriend_button, R.drawable.oc_icon_friends_remove_filled_24_d2d2d2),
    REJECT_FRIEND_REQUEST(R.string.anytime_tablet_profile_reject_friend_request_button, R.drawable.oc_icon_friends_remove_filled_24_d2d2d2),
    VIEW_FULL_PROFILE(R.string.anytime_tablet_profile_view_profile, R.drawable.oc_icon_open_panel_filled_24_d2d2d2);
    
    private static final Map<ProfileContextMenuItem, Integer> PROFILE_CONTEXT_MENU_ITEM_ICON_MAP = new HashMap();
    private static final Map<ProfileContextMenuItem, Integer> PROFILE_CONTEXT_MENU_ITEM_TITLE_MAP = new HashMap();
    private final int mIconId;
    private final int mStringId;

    static {
        ProfileContextMenuItem[] values = values();
        for (ProfileContextMenuItem profileContextMenuItem : values) {
            PROFILE_CONTEXT_MENU_ITEM_TITLE_MAP.put(profileContextMenuItem, Integer.valueOf(profileContextMenuItem.getStringId()));
            PROFILE_CONTEXT_MENU_ITEM_ICON_MAP.put(profileContextMenuItem, Integer.valueOf(profileContextMenuItem.getIconId()));
        }
    }

    private ProfileContextMenuItem(int i, int i2) {
        this.mStringId = i;
        this.mIconId = i2;
    }

    public int getStringId() {
        return this.mStringId;
    }

    public int getIconId() {
        return this.mIconId;
    }

    public static Map<ProfileContextMenuItem, Integer> getTitleMap() {
        return PROFILE_CONTEXT_MENU_ITEM_TITLE_MAP;
    }

    public static Map<ProfileContextMenuItem, Integer> getIconMap() {
        return PROFILE_CONTEXT_MENU_ITEM_ICON_MAP;
    }
}
