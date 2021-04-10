package com.oculus.panelapp.anytimeui;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.oculus.assistant.service.api.panel.AssistantComponentContract;
import com.oculus.assistant.service.api.panel.AssistantDialogContract;
import com.oculus.common.quickpromotion.QuickPromotionController;
import com.oculus.panelapp.anytimeui.databinding.AnytimeBarActiveAppButtonV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeBarActiveCallBarFullV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeBarActiveCallBarSimpleV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeBarDebugViewV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeBarNavigationButtonV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeBarNavigationStoreButtonV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeBarStatusBatteryDotsViewV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeBarStatusViewV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeBarSystemStatusBindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeBarViewV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletAndroidSettingsDescriptiveTextBindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletAndroidSettingsEnvironmentTileBindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletAndroidSettingsInfoBoxBindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletAndroidSettingsListItemBindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletAndroidSettingsNullStateBindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletAndroidSettingsViewBindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletBadgeBindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletCommonNullStateV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletContentEmptyViewV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletDestinationUiViewV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletDropdownItemBindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletEmptyViewV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletEnterpriseAdminKeypadButtonBindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletEnterpriseProfileAdminKeypadBindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletEnterpriseProfileViewV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletInternalSettingsGeneralViewV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletInternalSettingsNavViewV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryAppTileV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryGetMoreAppsTileV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryMobileOculusGoBannerV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryNullStateBindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibrarySystemAppButtonV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibrarySystemAppsHeaderV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryUnknownSourceRowV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryUnknownSourcesHeaderV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryViewV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLoadingDotsV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLoadingViewV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletManagedLauncherAppTileV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletManagedLauncherViewV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletNotificationsActionButtonV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletNotificationsListItemV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletNotificationsViewV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletProfileViewBindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletSettingsNavViewV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletSettingsViewV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletSharingViewV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletSideNavEmptyViewV2BindingImpl;
import com.oculus.panelapp.anytimeui.databinding.SettingsButtonActionViewBindingImpl;
import com.oculus.panelapp.anytimeui.databinding.SettingsComplexButtonActionViewBindingImpl;
import com.oculus.panelapp.anytimeui.databinding.SettingsDropdownActionViewBindingImpl;
import com.oculus.panelapp.anytimeui.databinding.SettingsNavigationActionViewBindingImpl;
import com.oculus.panelapp.anytimeui.databinding.SettingsSliderActionViewBindingImpl;
import com.oculus.panelapp.anytimeui.databinding.SettingsToggleActionViewBindingImpl;
import com.oculus.panelapp.anytimeui.databinding.SettingsVoiceActivityLogActionViewBindingImpl;
import com.oculus.panelapp.anytimeui.databinding.SharingThumbnailButtonBindingImpl;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import com.oculus.userserver.api.sharing.SharingManagerContract;
import com.oculus.vrshell.panels.systemtooltip.TooltipDefinition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(56);
    private static final int LAYOUT_ANYTIMEBARACTIVEAPPBUTTONV2 = 1;
    private static final int LAYOUT_ANYTIMEBARACTIVECALLBARFULLV2 = 2;
    private static final int LAYOUT_ANYTIMEBARACTIVECALLBARSIMPLEV2 = 3;
    private static final int LAYOUT_ANYTIMEBARDEBUGVIEWV2 = 4;
    private static final int LAYOUT_ANYTIMEBARNAVIGATIONBUTTONV2 = 5;
    private static final int LAYOUT_ANYTIMEBARNAVIGATIONSTOREBUTTONV2 = 6;
    private static final int LAYOUT_ANYTIMEBARSTATUSBATTERYDOTSVIEWV2 = 7;
    private static final int LAYOUT_ANYTIMEBARSTATUSVIEWV2 = 8;
    private static final int LAYOUT_ANYTIMEBARSYSTEMSTATUS = 9;
    private static final int LAYOUT_ANYTIMEBARVIEWV2 = 10;
    private static final int LAYOUT_ANYTIMETABLETANDROIDSETTINGSDESCRIPTIVETEXT = 11;
    private static final int LAYOUT_ANYTIMETABLETANDROIDSETTINGSENVIRONMENTTILE = 12;
    private static final int LAYOUT_ANYTIMETABLETANDROIDSETTINGSINFOBOX = 13;
    private static final int LAYOUT_ANYTIMETABLETANDROIDSETTINGSLISTITEM = 14;
    private static final int LAYOUT_ANYTIMETABLETANDROIDSETTINGSNULLSTATE = 15;
    private static final int LAYOUT_ANYTIMETABLETANDROIDSETTINGSVIEW = 16;
    private static final int LAYOUT_ANYTIMETABLETBADGE = 17;
    private static final int LAYOUT_ANYTIMETABLETCOMMONNULLSTATEV2 = 18;
    private static final int LAYOUT_ANYTIMETABLETCONTENTEMPTYVIEWV2 = 19;
    private static final int LAYOUT_ANYTIMETABLETDESTINATIONUIVIEWV2 = 20;
    private static final int LAYOUT_ANYTIMETABLETDROPDOWNITEM = 21;
    private static final int LAYOUT_ANYTIMETABLETEMPTYVIEWV2 = 22;
    private static final int LAYOUT_ANYTIMETABLETENTERPRISEADMINKEYPADBUTTON = 23;
    private static final int LAYOUT_ANYTIMETABLETENTERPRISEPROFILEADMINKEYPAD = 24;
    private static final int LAYOUT_ANYTIMETABLETENTERPRISEPROFILEVIEWV2 = 25;
    private static final int LAYOUT_ANYTIMETABLETINTERNALSETTINGSGENERALVIEWV2 = 26;
    private static final int LAYOUT_ANYTIMETABLETINTERNALSETTINGSNAVVIEWV2 = 27;
    private static final int LAYOUT_ANYTIMETABLETLIBRARYAPPTILEV2 = 28;
    private static final int LAYOUT_ANYTIMETABLETLIBRARYGETMOREAPPSTILEV2 = 29;
    private static final int LAYOUT_ANYTIMETABLETLIBRARYMOBILEOCULUSGOBANNERV2 = 30;
    private static final int LAYOUT_ANYTIMETABLETLIBRARYNULLSTATE = 31;
    private static final int LAYOUT_ANYTIMETABLETLIBRARYSYSTEMAPPBUTTONV2 = 32;
    private static final int LAYOUT_ANYTIMETABLETLIBRARYSYSTEMAPPSHEADERV2 = 33;
    private static final int LAYOUT_ANYTIMETABLETLIBRARYUNKNOWNSOURCEROWV2 = 34;
    private static final int LAYOUT_ANYTIMETABLETLIBRARYUNKNOWNSOURCESHEADERV2 = 35;
    private static final int LAYOUT_ANYTIMETABLETLIBRARYVIEWV2 = 36;
    private static final int LAYOUT_ANYTIMETABLETLOADINGDOTSV2 = 37;
    private static final int LAYOUT_ANYTIMETABLETLOADINGVIEWV2 = 38;
    private static final int LAYOUT_ANYTIMETABLETMANAGEDLAUNCHERAPPTILEV2 = 39;
    private static final int LAYOUT_ANYTIMETABLETMANAGEDLAUNCHERVIEWV2 = 40;
    private static final int LAYOUT_ANYTIMETABLETNOTIFICATIONSACTIONBUTTONV2 = 41;
    private static final int LAYOUT_ANYTIMETABLETNOTIFICATIONSLISTITEMV2 = 42;
    private static final int LAYOUT_ANYTIMETABLETNOTIFICATIONSVIEWV2 = 43;
    private static final int LAYOUT_ANYTIMETABLETPROFILEVIEW = 44;
    private static final int LAYOUT_ANYTIMETABLETSETTINGSNAVVIEWV2 = 45;
    private static final int LAYOUT_ANYTIMETABLETSETTINGSVIEWV2 = 46;
    private static final int LAYOUT_ANYTIMETABLETSHARINGVIEWV2 = 47;
    private static final int LAYOUT_ANYTIMETABLETSIDENAVEMPTYVIEWV2 = 48;
    private static final int LAYOUT_SETTINGSBUTTONACTIONVIEW = 49;
    private static final int LAYOUT_SETTINGSCOMPLEXBUTTONACTIONVIEW = 50;
    private static final int LAYOUT_SETTINGSDROPDOWNACTIONVIEW = 51;
    private static final int LAYOUT_SETTINGSNAVIGATIONACTIONVIEW = 52;
    private static final int LAYOUT_SETTINGSSLIDERACTIONVIEW = 53;
    private static final int LAYOUT_SETTINGSTOGGLEACTIONVIEW = 54;
    private static final int LAYOUT_SETTINGSVOICEACTIVITYLOGACTIONVIEW = 55;
    private static final int LAYOUT_SHARINGTHUMBNAILBUTTON = 56;

    static {
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_bar_active_app_button_v2, 1);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_bar_active_call_bar_full_v2, 2);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_bar_active_call_bar_simple_v2, 3);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_bar_debug_view_v2, 4);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_bar_navigation_button_v2, 5);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_bar_navigation_store_button_v2, 6);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_bar_status_battery_dots_view_v2, 7);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_bar_status_view_v2, 8);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_bar_system_status, 9);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_bar_view_v2, 10);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_android_settings_descriptive_text, 11);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_android_settings_environment_tile, 12);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_android_settings_info_box, 13);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_android_settings_list_item, 14);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_android_settings_null_state, 15);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_android_settings_view, 16);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_badge, 17);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_common_null_state_v2, 18);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_content_empty_view_v2, 19);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_destination_ui_view_v2, 20);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_dropdown_item, 21);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_empty_view_v2, 22);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_enterprise_admin_keypad_button, 23);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_enterprise_profile_admin_keypad, 24);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_enterprise_profile_view_v2, 25);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_internal_settings_general_view_v2, 26);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_internal_settings_nav_view_v2, 27);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_library_app_tile_v2, 28);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_library_get_more_apps_tile_v2, 29);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_library_mobile_oculus_go_banner_v2, 30);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_library_null_state, 31);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_library_system_app_button_v2, 32);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_library_system_apps_header_v2, 33);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_library_unknown_source_row_v2, 34);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_library_unknown_sources_header_v2, 35);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_library_view_v2, 36);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_loading_dots_v2, 37);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_loading_view_v2, 38);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_managed_launcher_app_tile_v2, 39);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_managed_launcher_view_v2, 40);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_notifications_action_button_v2, 41);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_notifications_list_item_v2, 42);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_notifications_view_v2, 43);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_profile_view, 44);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_settings_nav_view_v2, 45);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_settings_view_v2, 46);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_sharing_view_v2, 47);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_side_nav_empty_view_v2, 48);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.settings_button_action_view, 49);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.settings_complex_button_action_view, 50);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.settings_dropdown_action_view, 51);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.settings_navigation_action_view, 52);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.settings_slider_action_view, 53);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.settings_toggle_action_view, 54);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.settings_voice_activity_log_action_view, 55);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.sharing_thumbnail_button, 56);
    }

    private final ViewDataBinding internalGetViewDataBinding0(DataBindingComponent dataBindingComponent, View view, int i, Object obj) {
        switch (i) {
            case 1:
                if ("layout/anytime_bar_active_app_button_v2_0".equals(obj)) {
                    return new AnytimeBarActiveAppButtonV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_bar_active_app_button_v2 is invalid. Received: " + obj);
            case 2:
                if ("layout/anytime_bar_active_call_bar_full_v2_0".equals(obj)) {
                    return new AnytimeBarActiveCallBarFullV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_bar_active_call_bar_full_v2 is invalid. Received: " + obj);
            case 3:
                if ("layout/anytime_bar_active_call_bar_simple_v2_0".equals(obj)) {
                    return new AnytimeBarActiveCallBarSimpleV2BindingImpl(dataBindingComponent, new View[]{view});
                }
                throw new IllegalArgumentException("The tag for anytime_bar_active_call_bar_simple_v2 is invalid. Received: " + obj);
            case 4:
                if ("layout/anytime_bar_debug_view_v2_0".equals(obj)) {
                    return new AnytimeBarDebugViewV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_bar_debug_view_v2 is invalid. Received: " + obj);
            case 5:
                if ("layout/anytime_bar_navigation_button_v2_0".equals(obj)) {
                    return new AnytimeBarNavigationButtonV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_bar_navigation_button_v2 is invalid. Received: " + obj);
            case 6:
                if ("layout/anytime_bar_navigation_store_button_v2_0".equals(obj)) {
                    return new AnytimeBarNavigationStoreButtonV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_bar_navigation_store_button_v2 is invalid. Received: " + obj);
            case 7:
                if ("layout/anytime_bar_status_battery_dots_view_v2_0".equals(obj)) {
                    return new AnytimeBarStatusBatteryDotsViewV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_bar_status_battery_dots_view_v2 is invalid. Received: " + obj);
            case 8:
                if ("layout/anytime_bar_status_view_v2_0".equals(obj)) {
                    return new AnytimeBarStatusViewV2BindingImpl(dataBindingComponent, new View[]{view});
                }
                throw new IllegalArgumentException("The tag for anytime_bar_status_view_v2 is invalid. Received: " + obj);
            case 9:
                if ("layout/anytime_bar_system_status_0".equals(obj)) {
                    return new AnytimeBarSystemStatusBindingImpl(dataBindingComponent, new View[]{view});
                }
                throw new IllegalArgumentException("The tag for anytime_bar_system_status is invalid. Received: " + obj);
            case 10:
                if ("layout/anytime_bar_view_v2_0".equals(obj)) {
                    return new AnytimeBarViewV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_bar_view_v2 is invalid. Received: " + obj);
            case 11:
                if ("layout/anytime_tablet_android_settings_descriptive_text_0".equals(obj)) {
                    return new AnytimeTabletAndroidSettingsDescriptiveTextBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_android_settings_descriptive_text is invalid. Received: " + obj);
            case 12:
                if ("layout/anytime_tablet_android_settings_environment_tile_0".equals(obj)) {
                    return new AnytimeTabletAndroidSettingsEnvironmentTileBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_android_settings_environment_tile is invalid. Received: " + obj);
            case 13:
                if ("layout/anytime_tablet_android_settings_info_box_0".equals(obj)) {
                    return new AnytimeTabletAndroidSettingsInfoBoxBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_android_settings_info_box is invalid. Received: " + obj);
            case 14:
                if ("layout/anytime_tablet_android_settings_list_item_0".equals(obj)) {
                    return new AnytimeTabletAndroidSettingsListItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_android_settings_list_item is invalid. Received: " + obj);
            case 15:
                if ("layout/anytime_tablet_android_settings_null_state_0".equals(obj)) {
                    return new AnytimeTabletAndroidSettingsNullStateBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_android_settings_null_state is invalid. Received: " + obj);
            case 16:
                if ("layout/anytime_tablet_android_settings_view_0".equals(obj)) {
                    return new AnytimeTabletAndroidSettingsViewBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_android_settings_view is invalid. Received: " + obj);
            case 17:
                if ("layout/anytime_tablet_badge_0".equals(obj)) {
                    return new AnytimeTabletBadgeBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_badge is invalid. Received: " + obj);
            case 18:
                if ("layout/anytime_tablet_common_null_state_v2_0".equals(obj)) {
                    return new AnytimeTabletCommonNullStateV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_common_null_state_v2 is invalid. Received: " + obj);
            case 19:
                if ("layout/anytime_tablet_content_empty_view_v2_0".equals(obj)) {
                    return new AnytimeTabletContentEmptyViewV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_content_empty_view_v2 is invalid. Received: " + obj);
            case 20:
                if ("layout/anytime_tablet_destination_ui_view_v2_0".equals(obj)) {
                    return new AnytimeTabletDestinationUiViewV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_destination_ui_view_v2 is invalid. Received: " + obj);
            case 21:
                if ("layout/anytime_tablet_dropdown_item_0".equals(obj)) {
                    return new AnytimeTabletDropdownItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_dropdown_item is invalid. Received: " + obj);
            case 22:
                if ("layout/anytime_tablet_empty_view_v2_0".equals(obj)) {
                    return new AnytimeTabletEmptyViewV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_empty_view_v2 is invalid. Received: " + obj);
            case 23:
                if ("layout/anytime_tablet_enterprise_admin_keypad_button_0".equals(obj)) {
                    return new AnytimeTabletEnterpriseAdminKeypadButtonBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_enterprise_admin_keypad_button is invalid. Received: " + obj);
            case 24:
                if ("layout/anytime_tablet_enterprise_profile_admin_keypad_0".equals(obj)) {
                    return new AnytimeTabletEnterpriseProfileAdminKeypadBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_enterprise_profile_admin_keypad is invalid. Received: " + obj);
            case 25:
                if ("layout/anytime_tablet_enterprise_profile_view_v2_0".equals(obj)) {
                    return new AnytimeTabletEnterpriseProfileViewV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_enterprise_profile_view_v2 is invalid. Received: " + obj);
            case 26:
                if ("layout/anytime_tablet_internal_settings_general_view_v2_0".equals(obj)) {
                    return new AnytimeTabletInternalSettingsGeneralViewV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_internal_settings_general_view_v2 is invalid. Received: " + obj);
            case 27:
                if ("layout/anytime_tablet_internal_settings_nav_view_v2_0".equals(obj)) {
                    return new AnytimeTabletInternalSettingsNavViewV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_internal_settings_nav_view_v2 is invalid. Received: " + obj);
            case 28:
                if ("layout/anytime_tablet_library_app_tile_v2_0".equals(obj)) {
                    return new AnytimeTabletLibraryAppTileV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_library_app_tile_v2 is invalid. Received: " + obj);
            case 29:
                if ("layout/anytime_tablet_library_get_more_apps_tile_v2_0".equals(obj)) {
                    return new AnytimeTabletLibraryGetMoreAppsTileV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_library_get_more_apps_tile_v2 is invalid. Received: " + obj);
            case 30:
                if ("layout/anytime_tablet_library_mobile_oculus_go_banner_v2_0".equals(obj)) {
                    return new AnytimeTabletLibraryMobileOculusGoBannerV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_library_mobile_oculus_go_banner_v2 is invalid. Received: " + obj);
            case 31:
                if ("layout/anytime_tablet_library_null_state_0".equals(obj)) {
                    return new AnytimeTabletLibraryNullStateBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_library_null_state is invalid. Received: " + obj);
            case 32:
                if ("layout/anytime_tablet_library_system_app_button_v2_0".equals(obj)) {
                    return new AnytimeTabletLibrarySystemAppButtonV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_library_system_app_button_v2 is invalid. Received: " + obj);
            case 33:
                if ("layout/anytime_tablet_library_system_apps_header_v2_0".equals(obj)) {
                    return new AnytimeTabletLibrarySystemAppsHeaderV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_library_system_apps_header_v2 is invalid. Received: " + obj);
            case 34:
                if ("layout/anytime_tablet_library_unknown_source_row_v2_0".equals(obj)) {
                    return new AnytimeTabletLibraryUnknownSourceRowV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_library_unknown_source_row_v2 is invalid. Received: " + obj);
            case 35:
                if ("layout/anytime_tablet_library_unknown_sources_header_v2_0".equals(obj)) {
                    return new AnytimeTabletLibraryUnknownSourcesHeaderV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_library_unknown_sources_header_v2 is invalid. Received: " + obj);
            case 36:
                if ("layout/anytime_tablet_library_view_v2_0".equals(obj)) {
                    return new AnytimeTabletLibraryViewV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_library_view_v2 is invalid. Received: " + obj);
            case 37:
                if ("layout/anytime_tablet_loading_dots_v2_0".equals(obj)) {
                    return new AnytimeTabletLoadingDotsV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_loading_dots_v2 is invalid. Received: " + obj);
            case 38:
                if ("layout/anytime_tablet_loading_view_v2_0".equals(obj)) {
                    return new AnytimeTabletLoadingViewV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_loading_view_v2 is invalid. Received: " + obj);
            case 39:
                if ("layout/anytime_tablet_managed_launcher_app_tile_v2_0".equals(obj)) {
                    return new AnytimeTabletManagedLauncherAppTileV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_managed_launcher_app_tile_v2 is invalid. Received: " + obj);
            case 40:
                if ("layout/anytime_tablet_managed_launcher_view_v2_0".equals(obj)) {
                    return new AnytimeTabletManagedLauncherViewV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_managed_launcher_view_v2 is invalid. Received: " + obj);
            case 41:
                if ("layout/anytime_tablet_notifications_action_button_v2_0".equals(obj)) {
                    return new AnytimeTabletNotificationsActionButtonV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_notifications_action_button_v2 is invalid. Received: " + obj);
            case 42:
                if ("layout/anytime_tablet_notifications_list_item_v2_0".equals(obj)) {
                    return new AnytimeTabletNotificationsListItemV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_notifications_list_item_v2 is invalid. Received: " + obj);
            case 43:
                if ("layout/anytime_tablet_notifications_view_v2_0".equals(obj)) {
                    return new AnytimeTabletNotificationsViewV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_notifications_view_v2 is invalid. Received: " + obj);
            case 44:
                if ("layout/anytime_tablet_profile_view_0".equals(obj)) {
                    return new AnytimeTabletProfileViewBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_profile_view is invalid. Received: " + obj);
            case 45:
                if ("layout/anytime_tablet_settings_nav_view_v2_0".equals(obj)) {
                    return new AnytimeTabletSettingsNavViewV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_settings_nav_view_v2 is invalid. Received: " + obj);
            case 46:
                if ("layout/anytime_tablet_settings_view_v2_0".equals(obj)) {
                    return new AnytimeTabletSettingsViewV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_settings_view_v2 is invalid. Received: " + obj);
            case 47:
                if ("layout/anytime_tablet_sharing_view_v2_0".equals(obj)) {
                    return new AnytimeTabletSharingViewV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_sharing_view_v2 is invalid. Received: " + obj);
            case 48:
                if ("layout/anytime_tablet_side_nav_empty_view_v2_0".equals(obj)) {
                    return new AnytimeTabletSideNavEmptyViewV2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for anytime_tablet_side_nav_empty_view_v2 is invalid. Received: " + obj);
            case 49:
                if ("layout/settings_button_action_view_0".equals(obj)) {
                    return new SettingsButtonActionViewBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for settings_button_action_view is invalid. Received: " + obj);
            case 50:
                if ("layout/settings_complex_button_action_view_0".equals(obj)) {
                    return new SettingsComplexButtonActionViewBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for settings_complex_button_action_view is invalid. Received: " + obj);
            default:
                return null;
        }
    }

    private final ViewDataBinding internalGetViewDataBinding1(DataBindingComponent dataBindingComponent, View view, int i, Object obj) {
        switch (i) {
            case 51:
                if ("layout/settings_dropdown_action_view_0".equals(obj)) {
                    return new SettingsDropdownActionViewBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for settings_dropdown_action_view is invalid. Received: " + obj);
            case 52:
                if ("layout/settings_navigation_action_view_0".equals(obj)) {
                    return new SettingsNavigationActionViewBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for settings_navigation_action_view is invalid. Received: " + obj);
            case 53:
                if ("layout/settings_slider_action_view_0".equals(obj)) {
                    return new SettingsSliderActionViewBindingImpl(dataBindingComponent, new View[]{view});
                }
                throw new IllegalArgumentException("The tag for settings_slider_action_view is invalid. Received: " + obj);
            case 54:
                if ("layout/settings_toggle_action_view_0".equals(obj)) {
                    return new SettingsToggleActionViewBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for settings_toggle_action_view is invalid. Received: " + obj);
            case 55:
                if ("layout/settings_voice_activity_log_action_view_0".equals(obj)) {
                    return new SettingsVoiceActivityLogActionViewBindingImpl(dataBindingComponent, new View[]{view});
                }
                throw new IllegalArgumentException("The tag for settings_voice_activity_log_action_view is invalid. Received: " + obj);
            case 56:
                if ("layout/sharing_thumbnail_button_0".equals(obj)) {
                    return new SharingThumbnailButtonBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for sharing_thumbnail_button is invalid. Received: " + obj);
            default:
                return null;
        }
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag != null) {
            int i3 = (i2 - 1) / 50;
            if (i3 == 0) {
                return internalGetViewDataBinding0(dataBindingComponent, view, i2, tag);
            }
            if (i3 != 1) {
                return null;
            }
            return internalGetViewDataBinding1(dataBindingComponent, view, i2, tag);
        }
        throw new RuntimeException("view must have a tag");
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        int i2;
        if (!(viewArr == null || viewArr.length == 0 || (i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i)) <= 0)) {
            Object tag = viewArr[0].getTag();
            if (tag == null) {
                throw new RuntimeException("view must have a tag");
            } else if (i2 != 3) {
                if (i2 != 53) {
                    if (i2 != 55) {
                        if (i2 != 8) {
                            if (i2 == 9) {
                                if ("layout/anytime_bar_system_status_0".equals(tag)) {
                                    return new AnytimeBarSystemStatusBindingImpl(dataBindingComponent, viewArr);
                                }
                                throw new IllegalArgumentException("The tag for anytime_bar_system_status is invalid. Received: " + tag);
                            }
                        } else if ("layout/anytime_bar_status_view_v2_0".equals(tag)) {
                            return new AnytimeBarStatusViewV2BindingImpl(dataBindingComponent, viewArr);
                        } else {
                            throw new IllegalArgumentException("The tag for anytime_bar_status_view_v2 is invalid. Received: " + tag);
                        }
                    } else if ("layout/settings_voice_activity_log_action_view_0".equals(tag)) {
                        return new SettingsVoiceActivityLogActionViewBindingImpl(dataBindingComponent, viewArr);
                    } else {
                        throw new IllegalArgumentException("The tag for settings_voice_activity_log_action_view is invalid. Received: " + tag);
                    }
                } else if ("layout/settings_slider_action_view_0".equals(tag)) {
                    return new SettingsSliderActionViewBindingImpl(dataBindingComponent, viewArr);
                } else {
                    throw new IllegalArgumentException("The tag for settings_slider_action_view is invalid. Received: " + tag);
                }
            } else if ("layout/anytime_bar_active_call_bar_simple_v2_0".equals(tag)) {
                return new AnytimeBarActiveCallBarSimpleV2BindingImpl(dataBindingComponent, viewArr);
            } else {
                throw new IllegalArgumentException("The tag for anytime_bar_active_call_bar_simple_v2 is invalid. Received: " + tag);
            }
        }
        return null;
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = InnerLayoutIdLookup.sKeys.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i) {
        return InnerBrLookup.sKeys.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(7);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.oculus.common.notifications.DataBinderMapperImpl());
        arrayList.add(new com.oculus.common.ocui.DataBinderMapperImpl());
        arrayList.add(new com.oculus.common.quickpromotion.DataBinderMapperImpl());
        arrayList.add(new com.oculus.common.vrshellutil.DataBinderMapperImpl());
        arrayList.add(new com.oculus.panelapp.social.DataBinderMapperImpl());
        arrayList.add(new com.oculus.tablet.DataBinderMapperImpl());
        return arrayList;
    }

    private static class InnerBrLookup {
        static final SparseArray<String> sKeys = new SparseArray<>(207);

        private InnerBrLookup() {
        }

        static {
            sKeys.put(0, "_all");
            sKeys.put(1, "shouldShowActiveCallBarAdvancedControls");
            sKeys.put(2, "socialViewModel");
            sKeys.put(3, "partySpotsAvailable");
            sKeys.put(4, "subtitleText");
            sKeys.put(5, "muteMicrophoneButtonIcon");
            sKeys.put(6, "title");
            sKeys.put(7, "showLoading");
            sKeys.put(8, "actionButtonText");
            sKeys.put(9, "hoveredOverCard");
            sKeys.put(10, "barLowerSectionVisible");
            sKeys.put(11, "isInvitedUser");
            sKeys.put(12, QuickPromotionController.EVENT_VIEW);
            sKeys.put(13, "showPartyFooter");
            sKeys.put(14, "action");
            sKeys.put(15, "canShowStartParty");
            sKeys.put(16, "isFriend");
            sKeys.put(17, "muted");
            sKeys.put(18, "ctaIcon");
            sKeys.put(19, "isSpeaking");
            sKeys.put(20, "secondaryActionButton");
            sKeys.put(21, "showStartParty");
            sKeys.put(22, "activeCallButtonTitle");
            sKeys.put(23, "showAddFriend");
            sKeys.put(24, "isFBLinked");
            sKeys.put(25, "mutePartyVolume");
            sKeys.put(26, "nameText");
            sKeys.put(27, "primaryActionButton");
            sKeys.put(28, "activeCallBarFullVisible");
            sKeys.put(29, "activeCallBarTitle");
            sKeys.put(30, "subtitle");
            sKeys.put(31, "usernameText");
            sKeys.put(32, "statusText");
            sKeys.put(33, "sharePartyButtonText");
            sKeys.put(34, "name");
            sKeys.put(35, "activeCallBarSimpleVisible");
            sKeys.put(36, "groupLaunchStatusText");
            sKeys.put(37, "party");
            sKeys.put(38, "isMuted");
            sKeys.put(39, "shouldShowSharePartyButton");
            sKeys.put(40, "activeIndicator");
            sKeys.put(41, "icon");
            sKeys.put(42, "progress");
            sKeys.put(43, "progressDrawable");
            sKeys.put(44, "text");
            sKeys.put(45, SharingManagerContract.ARGUMENT_IS_ENABLED);
            sKeys.put(46, "buttonText");
            sKeys.put(47, "inactiveDrawable");
            sKeys.put(48, "sideNavItem");
            sKeys.put(49, "ctaText");
            sKeys.put(50, "badgeCount");
            sKeys.put(51, "isActive");
            sKeys.put(52, "showProgressPercentage");
            sKeys.put(53, "activeDrawable");
            sKeys.put(54, "background");
            sKeys.put(55, AssistantDialogContract.MultiselectionDialog.Section.HEADER);
            sKeys.put(56, "viewModel");
            sKeys.put(57, "titleIcon");
            sKeys.put(58, "splash");
            sKeys.put(59, "isAdminPinRequired");
            sKeys.put(60, "filledKeypadBubble");
            sKeys.put(61, "currentPlatform");
            sKeys.put(62, "companyName");
            sKeys.put(63, "captureAllowed");
            sKeys.put(64, "bio");
            sKeys.put(65, "isKeypadThrottled");
            sKeys.put(66, "castingText");
            sKeys.put(67, "recordingIcon");
            sKeys.put(68, "iconDrawable");
            sKeys.put(69, "buttonActionType");
            sKeys.put(70, "secondaryButtonDrawable");
            sKeys.put(71, "platformDropdownEnabled");
            sKeys.put(72, "drawableStart");
            sKeys.put(73, "app");
            sKeys.put(74, "loadingProfile");
            sKeys.put(75, "isPaused");
            sKeys.put(76, "item");
            sKeys.put(77, "showContextMenu");
            sKeys.put(78, "isCheckingForUpdatesVisible");
            sKeys.put(79, "realityTunerValue");
            sKeys.put(80, "isHandTrackingEnabled");
            sKeys.put(81, "partyButtonVisible");
            sKeys.put(82, "isKeypadBackspaceEnabled");
            sKeys.put(83, "showBiography");
            sKeys.put(84, "showSavingChangesSpinner");
            sKeys.put(85, "settingsViewModel");
            sKeys.put(86, "destinationUIViewModel");
            sKeys.put(87, "paddingStartDip");
            sKeys.put(88, "brightnessIcon");
            sKeys.put(89, "isInternetConnected");
            sKeys.put(90, "seenState");
            sKeys.put(91, "route");
            sKeys.put(92, "ingestedApps");
            sKeys.put(93, "showUnknownSources");
            sKeys.put(94, "sectionHeader");
            sKeys.put(95, "userHasAvatar");
            sKeys.put(96, AssistantDialogContract.MultiselectionDialog.Section.ITEMS);
            sKeys.put(97, "dropdownAction");
            sKeys.put(98, "subtitleUri");
            sKeys.put(99, "systemApp");
            sKeys.put(100, "isHovered");
            sKeys.put(101, "unknownSources");
            sKeys.put(102, "isContextMenuButtonHovered");
            sKeys.put(103, "filterCounts");
            sKeys.put(104, "inviteEnabled");
            sKeys.put(105, "gameScreenshot");
            sKeys.put(106, "displayName");
            sKeys.put(107, "heroImage");
            sKeys.put(108, "panelApp");
            sKeys.put(109, "isTrackingEnabled");
            sKeys.put(110, "complexButtonActionType");
            sKeys.put(111, "sorterOptions");
            sKeys.put(112, "presenceString");
            sKeys.put(113, "presenceStringColor");
            sKeys.put(114, "navigationActionType");
            sKeys.put(115, "isScreenshotVisible");
            sKeys.put(116, "wifiButtonIcon");
            sKeys.put(117, "quitButtonText");
            sKeys.put(118, "showEditControls");
            sKeys.put(119, "hasCompleteProfile");
            sKeys.put(120, "sharingViewModel");
            sKeys.put(121, "sliderAction");
            sKeys.put(122, "hasContextMenu");
            sKeys.put(123, "isSavingChanges");
            sKeys.put(124, "lastSyncTimeText");
            sKeys.put(125, "platformOptions");
            sKeys.put(126, "launcherActions");
            sKeys.put(127, "lastSyncSubtitleText");
            sKeys.put(128, "imageAvailable");
            sKeys.put(129, "showBackButton");
            sKeys.put(130, "isAdminModeEnabled");
            sKeys.put(131, "highlightHome");
            sKeys.put(132, "brightnessSeekbarVisible");
            sKeys.put(133, "showFriendCount");
            sKeys.put(134, "showPresenceString");
            sKeys.put(135, "isPrimaryButtonLoading");
            sKeys.put(136, "parentAppName");
            sKeys.put(137, "showPresenceIcon");
            sKeys.put(138, "showLoadingError");
            sKeys.put(139, TabletDeepLinkingUriUtil.ANDROID_SETTINGS_QUERY_SECTION_KEY);
            sKeys.put(140, "toggleAction");
            sKeys.put(141, "eventsEntryEnabled");
            sKeys.put(142, "keypadEntryLength");
            sKeys.put(143, "primaryButtonType");
            sKeys.put(144, "hasToolbarButton");
            sKeys.put(145, "notification");
            sKeys.put(146, "isAbuseReportRecording");
            sKeys.put(147, "lastUpdateTimeText");
            sKeys.put(148, "isPrototype");
            sKeys.put(149, "browserApp");
            sKeys.put(150, "primaryButtonDrawable");
            sKeys.put(151, "highlightLibrary");
            sKeys.put(152, "isKeypadEntryReadyToBeChecked");
            sKeys.put(153, "handsButtonVisible");
            sKeys.put(154, "thumbnail");
            sKeys.put(155, "recordingText");
            sKeys.put(156, "tvApp");
            sKeys.put(157, "hasInternetConnection");
            sKeys.put(158, "editMode");
            sKeys.put(159, "showProfileContent");
            sKeys.put(160, "currentFilter");
            sKeys.put(161, "presenceIcon");
            sKeys.put(162, "isCheckForUpdatesVisible");
            sKeys.put(163, TooltipDefinition.TOOLTIP_POSITION_KEY);
            sKeys.put(164, "barViewModel");
            sKeys.put(165, "volumeIcon");
            sKeys.put(166, "licenseText");
            sKeys.put(167, AssistantDialogContract.Dialog.DESCRIPTION);
            sKeys.put(168, "infoBox");
            sKeys.put(169, "coloredBackground");
            sKeys.put(170, "realityTunerSliderVisible");
            sKeys.put(171, "currentSorter");
            sKeys.put(172, "secondaryButtonEnabled");
            sKeys.put(173, "wifiButtonCTA");
            sKeys.put(174, "onlinePresenceDotVisible");
            sKeys.put(175, "guardianIcon");
            sKeys.put(176, "nullState");
            sKeys.put(177, "isOverflowButtonLoading");
            sKeys.put(178, "isUserActive");
            sKeys.put(179, "highlight");
            sKeys.put(180, "guardianCTA");
            sKeys.put(181, "showSecondaryButton");
            sKeys.put(182, "statusViewModel");
            sKeys.put(183, "alias");
            sKeys.put(184, "liveStreamAllowed");
            sKeys.put(185, AssistantComponentContract.Components.TextComponent.VALUE);
            sKeys.put(186, "secondaryButtonType");
            sKeys.put(187, "filterOptions");
            sKeys.put(188, "liveStreamText");
            sKeys.put(189, "completeProfileButtonVisible");
            sKeys.put(190, "appName");
            sKeys.put(191, "showHeader");
            sKeys.put(192, "resources");
            sKeys.put(193, "squareImage");
            sKeys.put(194, "isVideo");
            sKeys.put(195, AssistantDialogContract.MultiselectionDialog.Items.LABEL);
            sKeys.put(196, "toolbarButtonTitle");
            sKeys.put(197, "showPrimaryButton");
            sKeys.put(198, "realName");
            sKeys.put(199, "environment");
            sKeys.put(200, "brightness");
            sKeys.put(201, "videoRecordingButtonEnabled");
            sKeys.put(202, "headerColoredSystemAppsEnabled");
            sKeys.put(203, "time");
            sKeys.put(204, "updateCheckState");
            sKeys.put(205, "currentItem");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys = new HashMap<>(56);

        private InnerLayoutIdLookup() {
        }

        static {
            sKeys.put("layout/anytime_bar_active_app_button_v2_0", Integer.valueOf(R.layout.anytime_bar_active_app_button_v2));
            sKeys.put("layout/anytime_bar_active_call_bar_full_v2_0", Integer.valueOf(R.layout.anytime_bar_active_call_bar_full_v2));
            sKeys.put("layout/anytime_bar_active_call_bar_simple_v2_0", Integer.valueOf(R.layout.anytime_bar_active_call_bar_simple_v2));
            sKeys.put("layout/anytime_bar_debug_view_v2_0", Integer.valueOf(R.layout.anytime_bar_debug_view_v2));
            sKeys.put("layout/anytime_bar_navigation_button_v2_0", Integer.valueOf(R.layout.anytime_bar_navigation_button_v2));
            sKeys.put("layout/anytime_bar_navigation_store_button_v2_0", Integer.valueOf(R.layout.anytime_bar_navigation_store_button_v2));
            sKeys.put("layout/anytime_bar_status_battery_dots_view_v2_0", Integer.valueOf(R.layout.anytime_bar_status_battery_dots_view_v2));
            sKeys.put("layout/anytime_bar_status_view_v2_0", Integer.valueOf(R.layout.anytime_bar_status_view_v2));
            sKeys.put("layout/anytime_bar_system_status_0", Integer.valueOf(R.layout.anytime_bar_system_status));
            sKeys.put("layout/anytime_bar_view_v2_0", Integer.valueOf(R.layout.anytime_bar_view_v2));
            sKeys.put("layout/anytime_tablet_android_settings_descriptive_text_0", Integer.valueOf(R.layout.anytime_tablet_android_settings_descriptive_text));
            sKeys.put("layout/anytime_tablet_android_settings_environment_tile_0", Integer.valueOf(R.layout.anytime_tablet_android_settings_environment_tile));
            sKeys.put("layout/anytime_tablet_android_settings_info_box_0", Integer.valueOf(R.layout.anytime_tablet_android_settings_info_box));
            sKeys.put("layout/anytime_tablet_android_settings_list_item_0", Integer.valueOf(R.layout.anytime_tablet_android_settings_list_item));
            sKeys.put("layout/anytime_tablet_android_settings_null_state_0", Integer.valueOf(R.layout.anytime_tablet_android_settings_null_state));
            sKeys.put("layout/anytime_tablet_android_settings_view_0", Integer.valueOf(R.layout.anytime_tablet_android_settings_view));
            sKeys.put("layout/anytime_tablet_badge_0", Integer.valueOf(R.layout.anytime_tablet_badge));
            sKeys.put("layout/anytime_tablet_common_null_state_v2_0", Integer.valueOf(R.layout.anytime_tablet_common_null_state_v2));
            sKeys.put("layout/anytime_tablet_content_empty_view_v2_0", Integer.valueOf(R.layout.anytime_tablet_content_empty_view_v2));
            sKeys.put("layout/anytime_tablet_destination_ui_view_v2_0", Integer.valueOf(R.layout.anytime_tablet_destination_ui_view_v2));
            sKeys.put("layout/anytime_tablet_dropdown_item_0", Integer.valueOf(R.layout.anytime_tablet_dropdown_item));
            sKeys.put("layout/anytime_tablet_empty_view_v2_0", Integer.valueOf(R.layout.anytime_tablet_empty_view_v2));
            sKeys.put("layout/anytime_tablet_enterprise_admin_keypad_button_0", Integer.valueOf(R.layout.anytime_tablet_enterprise_admin_keypad_button));
            sKeys.put("layout/anytime_tablet_enterprise_profile_admin_keypad_0", Integer.valueOf(R.layout.anytime_tablet_enterprise_profile_admin_keypad));
            sKeys.put("layout/anytime_tablet_enterprise_profile_view_v2_0", Integer.valueOf(R.layout.anytime_tablet_enterprise_profile_view_v2));
            sKeys.put("layout/anytime_tablet_internal_settings_general_view_v2_0", Integer.valueOf(R.layout.anytime_tablet_internal_settings_general_view_v2));
            sKeys.put("layout/anytime_tablet_internal_settings_nav_view_v2_0", Integer.valueOf(R.layout.anytime_tablet_internal_settings_nav_view_v2));
            sKeys.put("layout/anytime_tablet_library_app_tile_v2_0", Integer.valueOf(R.layout.anytime_tablet_library_app_tile_v2));
            sKeys.put("layout/anytime_tablet_library_get_more_apps_tile_v2_0", Integer.valueOf(R.layout.anytime_tablet_library_get_more_apps_tile_v2));
            sKeys.put("layout/anytime_tablet_library_mobile_oculus_go_banner_v2_0", Integer.valueOf(R.layout.anytime_tablet_library_mobile_oculus_go_banner_v2));
            sKeys.put("layout/anytime_tablet_library_null_state_0", Integer.valueOf(R.layout.anytime_tablet_library_null_state));
            sKeys.put("layout/anytime_tablet_library_system_app_button_v2_0", Integer.valueOf(R.layout.anytime_tablet_library_system_app_button_v2));
            sKeys.put("layout/anytime_tablet_library_system_apps_header_v2_0", Integer.valueOf(R.layout.anytime_tablet_library_system_apps_header_v2));
            sKeys.put("layout/anytime_tablet_library_unknown_source_row_v2_0", Integer.valueOf(R.layout.anytime_tablet_library_unknown_source_row_v2));
            sKeys.put("layout/anytime_tablet_library_unknown_sources_header_v2_0", Integer.valueOf(R.layout.anytime_tablet_library_unknown_sources_header_v2));
            sKeys.put("layout/anytime_tablet_library_view_v2_0", Integer.valueOf(R.layout.anytime_tablet_library_view_v2));
            sKeys.put("layout/anytime_tablet_loading_dots_v2_0", Integer.valueOf(R.layout.anytime_tablet_loading_dots_v2));
            sKeys.put("layout/anytime_tablet_loading_view_v2_0", Integer.valueOf(R.layout.anytime_tablet_loading_view_v2));
            sKeys.put("layout/anytime_tablet_managed_launcher_app_tile_v2_0", Integer.valueOf(R.layout.anytime_tablet_managed_launcher_app_tile_v2));
            sKeys.put("layout/anytime_tablet_managed_launcher_view_v2_0", Integer.valueOf(R.layout.anytime_tablet_managed_launcher_view_v2));
            sKeys.put("layout/anytime_tablet_notifications_action_button_v2_0", Integer.valueOf(R.layout.anytime_tablet_notifications_action_button_v2));
            sKeys.put("layout/anytime_tablet_notifications_list_item_v2_0", Integer.valueOf(R.layout.anytime_tablet_notifications_list_item_v2));
            sKeys.put("layout/anytime_tablet_notifications_view_v2_0", Integer.valueOf(R.layout.anytime_tablet_notifications_view_v2));
            sKeys.put("layout/anytime_tablet_profile_view_0", Integer.valueOf(R.layout.anytime_tablet_profile_view));
            sKeys.put("layout/anytime_tablet_settings_nav_view_v2_0", Integer.valueOf(R.layout.anytime_tablet_settings_nav_view_v2));
            sKeys.put("layout/anytime_tablet_settings_view_v2_0", Integer.valueOf(R.layout.anytime_tablet_settings_view_v2));
            sKeys.put("layout/anytime_tablet_sharing_view_v2_0", Integer.valueOf(R.layout.anytime_tablet_sharing_view_v2));
            sKeys.put("layout/anytime_tablet_side_nav_empty_view_v2_0", Integer.valueOf(R.layout.anytime_tablet_side_nav_empty_view_v2));
            sKeys.put("layout/settings_button_action_view_0", Integer.valueOf(R.layout.settings_button_action_view));
            sKeys.put("layout/settings_complex_button_action_view_0", Integer.valueOf(R.layout.settings_complex_button_action_view));
            sKeys.put("layout/settings_dropdown_action_view_0", Integer.valueOf(R.layout.settings_dropdown_action_view));
            sKeys.put("layout/settings_navigation_action_view_0", Integer.valueOf(R.layout.settings_navigation_action_view));
            sKeys.put("layout/settings_slider_action_view_0", Integer.valueOf(R.layout.settings_slider_action_view));
            sKeys.put("layout/settings_toggle_action_view_0", Integer.valueOf(R.layout.settings_toggle_action_view));
            sKeys.put("layout/settings_voice_activity_log_action_view_0", Integer.valueOf(R.layout.settings_voice_activity_log_action_view));
            sKeys.put("layout/sharing_thumbnail_button_0", Integer.valueOf(R.layout.sharing_thumbnail_button));
        }
    }
}
