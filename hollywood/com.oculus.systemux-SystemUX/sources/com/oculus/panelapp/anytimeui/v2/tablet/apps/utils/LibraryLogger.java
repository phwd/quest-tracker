package com.oculus.panelapp.anytimeui.v2.tablet.apps.utils;

import android.content.Context;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.library.model.App;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryViewModel;
import com.oculus.tablet.logging.SectionTrackerEvent;
import java.util.UUID;

public class LibraryLogger {
    private static final String APP_NAME_PARAM = "app_name";
    private static final String AUIV2_ENABLED_PARAM = "auiv2_enabled";
    private static final String AUIV2_ENABLED_VALUE = "true";
    public static final String DROPDOWN_FILTER = "filter";
    private static final String DROPDOWN_OPTION_PARAM = "option";
    private static final String DROPDOWN_PARAM = "dropdown";
    public static final String DROPDOWN_PLATFORM = "platform";
    private static final String DROPDOWN_PREVIOUS_OPTION_PARAM = "previous_option";
    public static final String DROPDOWN_SORTER = "sorter";
    private static final String ENTITLEMENT_STATUS_PARAM = "entitlement_status";
    private static final String FILTER_STATE_PARAM = "filter_state";
    private static final String LIBRARY_APP_DROPDOWN_MENU_CLICK_EVENT = "oculus_library_app_drop_down_menu_click";
    private static final String LIBRARY_APP_INSTALL_EVENT = "oculus_library_app_install";
    private static final String LIBRARY_DROPDOWN_CLICK_EVENT = "oculus_library_dropdown_click";
    private static final String LIBRARY_DROPDOWN_OPTION_CLICK_EVENT = "oculus_library_dropdown_option_click";
    private static final String LIBRARY_INITIAL_APPS_EVENT = "oculus_library_initial_apps";
    private static final String LIBRARY_SCROLL_EVENT = "oculus_library_scroll";
    private static final String LIBRARY_SECTION_ENTRY_EVENT = "oculus_library_section_entry";
    private static final String LIBRARY_TILE_CLICK_EVENT = "oculus_library_tile_click";
    private static final String LIBRARY_TILE_VISIBILITY_EVENT = "oculus_library_tile_visibility";
    private static final String MENU_ITEM_PARAM = "menu_item";
    private static final String ORIGIN_PARAM = "origin";
    private static final String ORIGIN_VALUE = "android_library";
    private static final String PLATFORM_STATE_PARAM = "platform_state";
    private static final String POSITION_PARAM = "position";
    private static final String PURCHASED_APPS_PARAM = "purchased_apps";
    private static final String SECTION_ID_PARAM = "section_id";
    private static final String SESSION_ID_PARAM = "library_session_id";
    private static final String SORTER_STATE_PARAM = "sorter_state";
    private static final String STATE_RESET_EVENT = "oculus_mobile_library_state_auto_reset";
    public static final String STATE_RESET_REASON_AUI_OFF_FOCUS = "aui_off_focus";
    public static final String STATE_RESET_REASON_HMD_INACTIVITY = "hmd_inactivity";
    public static final String STATE_RESET_REASON_HMD_REBOOT = "hmd_reboot";
    public static final String STATE_RESET_REASON_IMMERSIVE_APP_LAUNCH_OR_QUIT = "immersive_app_launch_or_quit";
    private static final String STATE_RESET_REASON_PARAM = "reason";
    private static final String TAG = LoggingUtil.tag(LibraryLogger.class);
    private static final String TARGET_PARAM = "target";
    private static final String TOTAL_APPS_PARAM = "total_apps";
    private String mLibrarySessionId;
    private LibraryViewModel mLibraryViewModel;
    private final UnifiedTelemetryLogger mUnifiedTelemetryLogger;

    public LibraryLogger(Context context, LibraryViewModel libraryViewModel) {
        this.mLibraryViewModel = libraryViewModel;
        this.mUnifiedTelemetryLogger = UnifiedTelemetryLogger.getInstance(context);
        this.mUnifiedTelemetryLogger.init(context.getApplicationContext());
    }

    public void initializeLibrarySessionId() {
        this.mLibrarySessionId = generateLibrarySessionId();
        Log.d(TAG, String.format("Library Session ID Generated: %s", this.mLibrarySessionId));
    }

    public void destroyLibrarySessionId() {
        this.mLibrarySessionId = null;
        Log.d(TAG, "Library Session ID destroyed");
    }

    public void logLibrarySectionEntry() {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(LIBRARY_SECTION_ENTRY_EVENT);
        analyticsEvent.setExtra(SESSION_ID_PARAM, getLibrarySessionId());
        analyticsEvent.setExtra(SECTION_ID_PARAM, SectionTrackerEvent.APPS_TABLET.getTelemetrySectionId());
        analyticsEvent.setExtra(PLATFORM_STATE_PARAM, this.mLibraryViewModel.getCurrentPlatform().toString());
        analyticsEvent.setExtra(SORTER_STATE_PARAM, this.mLibraryViewModel.getCurrentSorter().toString());
        analyticsEvent.setExtra(FILTER_STATE_PARAM, this.mLibraryViewModel.getCurrentFilter().toString());
        this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
    }

    public void logLibraryInitialApps(int i, int i2) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(LIBRARY_INITIAL_APPS_EVENT);
        analyticsEvent.setExtra(SESSION_ID_PARAM, getLibrarySessionId());
        analyticsEvent.setExtra(TOTAL_APPS_PARAM, Integer.valueOf(i));
        analyticsEvent.setExtra(PURCHASED_APPS_PARAM, Integer.valueOf(i2));
        this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
    }

    public void logLibraryStateReset(String str) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(STATE_RESET_EVENT);
        analyticsEvent.setExtra(SESSION_ID_PARAM, getLibrarySessionId());
        analyticsEvent.setExtra(STATE_RESET_REASON_PARAM, str);
        this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
    }

    public void logDropdownClick(String str) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(LIBRARY_DROPDOWN_CLICK_EVENT);
        analyticsEvent.setExtra(SESSION_ID_PARAM, getLibrarySessionId());
        analyticsEvent.setExtra(DROPDOWN_PARAM, str);
        this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
    }

    public void logDropdownOptionClick(String str, String str2, String str3) {
        Log.d(TAG, String.format("Logging dropdown option click, dropdown: %s, previous: %s, new: %s", str, str2, str3));
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(LIBRARY_DROPDOWN_OPTION_CLICK_EVENT);
        analyticsEvent.setExtra(SESSION_ID_PARAM, getLibrarySessionId());
        analyticsEvent.setExtra(DROPDOWN_PARAM, str);
        analyticsEvent.setExtra(DROPDOWN_PREVIOUS_OPTION_PARAM, str2);
        analyticsEvent.setExtra(DROPDOWN_OPTION_PARAM, str3);
        this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
    }

    public void logLibraryScroll(int i) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(LIBRARY_SCROLL_EVENT);
        analyticsEvent.setExtra(SESSION_ID_PARAM, getLibrarySessionId());
        analyticsEvent.setExtra("position", Integer.valueOf(i));
        this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
    }

    public void logAppVisibility(App app, int i) {
        if (app.packageName != null) {
            AnalyticsEvent analyticsEvent = new AnalyticsEvent(LIBRARY_TILE_VISIBILITY_EVENT);
            analyticsEvent.setExtra(SESSION_ID_PARAM, getLibrarySessionId());
            analyticsEvent.setExtra("target", app.packageName);
            int length = app.status.toString().length();
            String str = com.facebook.debug.log.LoggingUtil.NO_HASHCODE;
            analyticsEvent.setExtra(ENTITLEMENT_STATUS_PARAM, length > 0 ? app.status.toString() : str);
            if (app.displayName.length() > 0) {
                str = app.displayName;
            }
            analyticsEvent.setExtra(APP_NAME_PARAM, str);
            analyticsEvent.setExtra("position", Integer.valueOf(i));
            this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
        }
    }

    public void logAppClick(App app, int i) {
        if (app.packageName != null) {
            logAppClick(app.packageName, app.status.toString(), app.displayName, i);
        }
    }

    public void logAppClick(String str, String str2, String str3, int i) {
        if (str != null) {
            AnalyticsEvent analyticsEvent = new AnalyticsEvent(LIBRARY_TILE_CLICK_EVENT);
            analyticsEvent.setExtra(SESSION_ID_PARAM, getLibrarySessionId());
            analyticsEvent.setExtra(AUIV2_ENABLED_PARAM, "true");
            analyticsEvent.setExtra("origin", ORIGIN_VALUE);
            analyticsEvent.setExtra("target", str);
            if (str2.length() <= 0) {
                str2 = com.facebook.debug.log.LoggingUtil.NO_HASHCODE;
            }
            analyticsEvent.setExtra(ENTITLEMENT_STATUS_PARAM, str2);
            if (str3.length() <= 0) {
                str3 = com.facebook.debug.log.LoggingUtil.NO_HASHCODE;
            }
            analyticsEvent.setExtra(APP_NAME_PARAM, str3);
            analyticsEvent.setExtra("position", Integer.valueOf(i));
            analyticsEvent.setExtra(PLATFORM_STATE_PARAM, this.mLibraryViewModel.getCurrentPlatform().toString());
            analyticsEvent.setExtra(SORTER_STATE_PARAM, this.mLibraryViewModel.getCurrentSorter().toString());
            analyticsEvent.setExtra(FILTER_STATE_PARAM, this.mLibraryViewModel.getCurrentFilter().toString());
            this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
        }
    }

    public void logContextMenuClick(App app, String str, int i) {
        if (app.packageName != null && str != null) {
            AnalyticsEvent analyticsEvent = new AnalyticsEvent(LIBRARY_APP_DROPDOWN_MENU_CLICK_EVENT);
            analyticsEvent.setExtra(SESSION_ID_PARAM, getLibrarySessionId());
            analyticsEvent.setExtra(AUIV2_ENABLED_PARAM, "true");
            analyticsEvent.setExtra("origin", ORIGIN_VALUE);
            analyticsEvent.setExtra(MENU_ITEM_PARAM, str);
            analyticsEvent.setExtra("target", app.packageName);
            analyticsEvent.setExtra(APP_NAME_PARAM, app.displayName.length() > 0 ? app.displayName : com.facebook.debug.log.LoggingUtil.NO_HASHCODE);
            analyticsEvent.setExtra("position", Integer.valueOf(i));
            this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
        }
    }

    public void logAppInstall(App app, int i) {
        if (app.packageName != null) {
            AnalyticsEvent analyticsEvent = new AnalyticsEvent(LIBRARY_APP_INSTALL_EVENT);
            analyticsEvent.setExtra(SESSION_ID_PARAM, getLibrarySessionId());
            analyticsEvent.setExtra("target", app.packageName);
            int length = app.status.toString().length();
            String str = com.facebook.debug.log.LoggingUtil.NO_HASHCODE;
            analyticsEvent.setExtra(ENTITLEMENT_STATUS_PARAM, length > 0 ? app.status.toString() : str);
            if (app.displayName.length() > 0) {
                str = app.displayName;
            }
            analyticsEvent.setExtra(APP_NAME_PARAM, str);
            analyticsEvent.setExtra("position", Integer.valueOf(i));
            this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
        }
    }

    private String getLibrarySessionId() {
        if (TextUtils.isEmpty(this.mLibrarySessionId)) {
            initializeLibrarySessionId();
        }
        return this.mLibrarySessionId;
    }

    private static String generateLibrarySessionId() {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            return UUID.randomUUID().toString();
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }
}
