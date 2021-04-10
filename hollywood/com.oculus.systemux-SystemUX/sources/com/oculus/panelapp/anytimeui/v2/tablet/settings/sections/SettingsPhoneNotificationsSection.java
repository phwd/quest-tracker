package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.SettingsManager;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDescriptiveText;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDropdownActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType;
import com.oculus.systemdialog.CommonSystemDialogActions;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogHeroImage;
import com.oculus.vrshell.notifications.NotificationUri;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Supplier;
import org.json.JSONException;
import org.json.JSONObject;

public class SettingsPhoneNotificationsSection extends SettingsSection {
    private static final String IOS_MESSAGES_APP_ID = "com.apple.MobileSMS";
    private static final String IOS_PHONE_APP_ID = "com.apple.mobilephone";
    private static final String PHONE_NOTIFICATIONS_APP_ENABLED_IN_3P_APPS = "enabled_in_3p_apps";
    private static final String PHONE_NOTIFICATIONS_APP_ENABLED_KEY = "enabled";
    private static final String PHONE_NOTIFICATIONS_APP_NAME_KEY = "name";
    private static final int PHONE_NOTIFICATION_ENABLED_LOCATION_ALWAYS = 0;
    private static final int PHONE_NOTIFICATION_ENABLED_LOCATION_NEVER = 2;
    private static final Map<Integer, Integer> PHONE_NOTIFICATION_ENABLED_LOCATION_OPTIONS = new HashMap<Integer, Integer>() {
        /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsPhoneNotificationsSection.AnonymousClass1 */

        {
            put(0, Integer.valueOf(R.string.settings_phone_notifications_location_always));
            put(1, Integer.valueOf(R.string.settings_phone_notifications_location_while_in_home));
            put(2, Integer.valueOf(R.string.settings_phone_notifications_location_never));
        }
    };
    private static final int PHONE_NOTIFICATION_ENABLED_LOCATION_WHILE_IN_HOME = 1;
    private static final String PHONE_SETUP_DIALOG_ID = "notifications_phone_setup_dialog";
    private static final String PHONE_SETUP_DIALOG_IMAGE = "apk://com.oculus.vrshell.home/assets/ui/settings/phone_notifications_setup_instructions_512x288.ktx";
    private static final float PHONE_SETUP_DIALOG_IMAGE_ASPECT_RATIO = 1.7777778f;
    private static final String TAG = LoggingUtil.tag(SettingsPhoneNotificationsSection.class);
    private final Runnable mRefreshView;

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection
    public void destroy() {
    }

    public SettingsPhoneNotificationsSection(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, Runnable runnable) {
        super(context.getResources().getString(R.string.settings_notifications_section_title), "/notifications");
        int i;
        JSONException e;
        this.mRefreshView = runnable;
        ArrayList arrayList = new ArrayList();
        SettingsManager settingsManager = new SettingsManager();
        JSONObject appSettingsMap = getAppSettingsMap(settingsManager);
        arrayList.add(new SettingsItem.Builder(context, anytimeUIAndroidPanelAppV2).withSettingName("iphone-pairing").withTitle(R.string.setting_phone_notifications_enabled_title).withSubtitle(R.string.setting_phone_notifications_enabled_subtitle).withSettingsActionType(new SettingsToggleActionType.Builder().withGetValue(new SettingsToggleActionType.ToggleGetValueHandler(settingsManager) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsPhoneNotificationsSection$UXHypnPH6WoyXSK6jdpxSiaywKI */
            private final /* synthetic */ SettingsManager f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleGetValueHandler
            public final boolean get() {
                return SettingsPhoneNotificationsSection.this.lambda$new$459$SettingsPhoneNotificationsSection(this.f$1);
            }
        }).withSetValue(new SettingsToggleActionType.ToggleSetValueHandler(settingsManager, anytimeUIAndroidPanelAppV2, context) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsPhoneNotificationsSection$hkCtVI73TvaUCXZ7l5VlMwS5r9I */
            private final /* synthetic */ SettingsManager f$1;
            private final /* synthetic */ AnytimeUIAndroidPanelAppV2 f$2;
            private final /* synthetic */ Context f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleSetValueHandler
            public final void set(boolean z, SettingsToggleActionType settingsToggleActionType) {
                SettingsPhoneNotificationsSection.this.lambda$new$460$SettingsPhoneNotificationsSection(this.f$1, this.f$2, this.f$3, z, settingsToggleActionType);
            }
        })));
        arrayList.add(new SettingsDescriptiveText.Builder(context, anytimeUIAndroidPanelAppV2).withHeader(R.string.settings_phone_notifications_core_section_title).visibilityFetcher(new Supplier(settingsManager) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsPhoneNotificationsSection$rqdzMM_HBAkb8BpevnhKTNNDgTU */
            private final /* synthetic */ SettingsManager f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                return SettingsPhoneNotificationsSection.this.lambda$new$461$SettingsPhoneNotificationsSection(this.f$1);
            }
        }));
        arrayList.add(getPhoneNotificationAppItem(context, anytimeUIAndroidPanelAppV2, settingsManager, IOS_MESSAGES_APP_ID, context.getResources().getString(R.string.setting_phone_notifications_messages_title), "messages"));
        arrayList.add(getPhoneNotificationAppItem(context, anytimeUIAndroidPanelAppV2, settingsManager, IOS_PHONE_APP_ID, context.getResources().getString(R.string.setting_phone_notifications_phone_calls_title), NotificationUri.PHONE));
        arrayList.add(new SettingsItem.Builder(context, anytimeUIAndroidPanelAppV2).withSettingName("phone_notifications_enabled_all_apps").withTitle(R.string.setting_phone_notifications_allow_all_apps_title).withSettingsActionType(new SettingsToggleActionType.Builder().withSettingsManagerKey(SettingsManager.PHONE_NOTIFICATION_ALLOW_ALL_APPS)).withVisibilityCondition(new Supplier(settingsManager) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsPhoneNotificationsSection$ImGABzAkOwwSYPIxdCSvL7GO7Ik */
            private final /* synthetic */ SettingsManager f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                return SettingsPhoneNotificationsSection.this.lambda$new$462$SettingsPhoneNotificationsSection(this.f$1);
            }
        }));
        arrayList.add(new SettingsDescriptiveText.Builder(context, anytimeUIAndroidPanelAppV2).withHeader(hasAppSettingsItems(appSettingsMap) ? R.string.settings_phone_notifications_apps_empty_section_title : R.string.settings_phone_notifications_apps_section_title).visibilityFetcher(new Supplier(settingsManager) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsPhoneNotificationsSection$eU4ZgexvBevg2BVx0l7IIPo9gdY */
            private final /* synthetic */ SettingsManager f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                return SettingsPhoneNotificationsSection.this.lambda$new$463$SettingsPhoneNotificationsSection(this.f$1);
            }
        }));
        int i2 = 0;
        Iterator<String> keys = appSettingsMap.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (!isCoreAppSetting(next)) {
                try {
                    i = i2 + 1;
                    try {
                        arrayList.add(getPhoneNotificationAppItem(context, anytimeUIAndroidPanelAppV2, settingsManager, next, appSettingsMap.getJSONObject(next).optString("name", next), String.valueOf(i2)));
                    } catch (JSONException e2) {
                        e = e2;
                    }
                } catch (JSONException e3) {
                    i = i2;
                    e = e3;
                    Log.e(TAG, "Failed to find app in settings map.", e);
                    i2 = i;
                }
                i2 = i;
            }
        }
        addSettingsItems(arrayList);
    }

    public /* synthetic */ void lambda$new$460$SettingsPhoneNotificationsSection(SettingsManager settingsManager, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, Context context, boolean z, SettingsToggleActionType settingsToggleActionType) {
        if (settingsManager.getString(SettingsManager.PHONE_NOTIFICATION_APPS, "").isEmpty()) {
            anytimeUIAndroidPanelAppV2.getDialogManager().showDialog(getSetUpDialog(context));
            settingsToggleActionType.asyncSetValue(false);
            return;
        }
        settingsManager.setBoolean(SettingsManager.PHONE_NOTIFICATION_ENABLED, z);
        this.mRefreshView.run();
    }

    public /* synthetic */ Boolean lambda$new$461$SettingsPhoneNotificationsSection(SettingsManager settingsManager) {
        return Boolean.valueOf(lambda$new$459$SettingsPhoneNotificationsSection(settingsManager));
    }

    public /* synthetic */ Boolean lambda$new$462$SettingsPhoneNotificationsSection(SettingsManager settingsManager) {
        return Boolean.valueOf(lambda$new$459$SettingsPhoneNotificationsSection(settingsManager));
    }

    public /* synthetic */ Boolean lambda$new$463$SettingsPhoneNotificationsSection(SettingsManager settingsManager) {
        return Boolean.valueOf(lambda$new$459$SettingsPhoneNotificationsSection(settingsManager));
    }

    /* access modifiers changed from: private */
    /* renamed from: getPhoneNotificationsEnabled */
    public boolean lambda$new$459$SettingsPhoneNotificationsSection(SettingsManager settingsManager) {
        return settingsManager.getBoolean(SettingsManager.PHONE_NOTIFICATION_ENABLED, false);
    }

    private boolean hasAppSettingsItems(JSONObject jSONObject) {
        if (jSONObject.length() > 2) {
            return true;
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            if (!isCoreAppSetting(keys.next())) {
                return true;
            }
        }
        return false;
    }

    private boolean isCoreAppSetting(String str) {
        return IOS_MESSAGES_APP_ID.equals(str) || IOS_PHONE_APP_ID.equals(str);
    }

    private JSONObject getAppSettingsMap(SettingsManager settingsManager) {
        try {
            return new JSONObject(settingsManager.getString(SettingsManager.PHONE_NOTIFICATION_APPS, null));
        } catch (JSONException e) {
            Log.e(TAG, "Failed to parse phone notification apps.", e);
            return new JSONObject();
        }
    }

    private SettingsItem.Builder getPhoneNotificationAppItem(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, SettingsManager settingsManager, String str, String str2, String str3) {
        int phoneNotificationEnabledLocation = getPhoneNotificationEnabledLocation(settingsManager, str);
        SettingsItem.Builder builder = new SettingsItem.Builder(context, anytimeUIAndroidPanelAppV2);
        return builder.withSettingName("phone-notifs-allow-app-" + str3).withTitle(str2).withSettingsActionType(new SettingsDropdownActionType.Builder().withItems(PHONE_NOTIFICATION_ENABLED_LOCATION_OPTIONS.keySet().toArray(new Integer[PHONE_NOTIFICATION_ENABLED_LOCATION_OPTIONS.keySet().size()])).withSelectedItem(Integer.valueOf(phoneNotificationEnabledLocation)).withTitleMap(PHONE_NOTIFICATION_ENABLED_LOCATION_OPTIONS).withOnSelectHandler(new SettingsDropdownActionType.OnSelectHandler(settingsManager, str, str2) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsPhoneNotificationsSection$bBY4BjsUzRl8a5Dj7KwI_EfJndc */
            private final /* synthetic */ SettingsManager f$1;
            private final /* synthetic */ String f$2;
            private final /* synthetic */ String f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDropdownActionType.OnSelectHandler
            public final boolean onSelect(Object obj) {
                return SettingsPhoneNotificationsSection.this.lambda$getPhoneNotificationAppItem$464$SettingsPhoneNotificationsSection(this.f$1, this.f$2, this.f$3, (Integer) obj);
            }
        })).withVisibilityCondition(new Supplier(settingsManager) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsPhoneNotificationsSection$PZrt0qChHi3w65hhnhPizrv54vs */
            private final /* synthetic */ SettingsManager f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                return SettingsPhoneNotificationsSection.this.lambda$getPhoneNotificationAppItem$465$SettingsPhoneNotificationsSection(this.f$1);
            }
        });
    }

    public /* synthetic */ boolean lambda$getPhoneNotificationAppItem$464$SettingsPhoneNotificationsSection(SettingsManager settingsManager, String str, String str2, Integer num) {
        setPhoneNotificationEnabledLocation(settingsManager, str, str2, num.intValue());
        return true;
    }

    public /* synthetic */ Boolean lambda$getPhoneNotificationAppItem$465$SettingsPhoneNotificationsSection(SettingsManager settingsManager) {
        return Boolean.valueOf(lambda$new$459$SettingsPhoneNotificationsSection(settingsManager));
    }

    private int getPhoneNotificationEnabledLocation(SettingsManager settingsManager, String str) {
        try {
            JSONObject jSONObject = getAppSettingsMap(settingsManager).getJSONObject(str);
            boolean optBoolean = jSONObject.optBoolean("enabled", true);
            boolean optBoolean2 = jSONObject.optBoolean(PHONE_NOTIFICATIONS_APP_ENABLED_IN_3P_APPS, true);
            if (!optBoolean) {
                return 2;
            }
            if (optBoolean2) {
                return 0;
            }
            return 1;
        } catch (JSONException e) {
            Log.e(TAG, "Failed to find app in settings map.", e);
            return 0;
        }
    }

    private void setPhoneNotificationEnabledLocation(SettingsManager settingsManager, String str, String str2, int i) {
        JSONObject appSettingsMap = getAppSettingsMap(settingsManager);
        boolean z = true;
        boolean z2 = i != 2;
        if (i != 0) {
            z = false;
        }
        try {
            JSONObject jSONObject = appSettingsMap.getJSONObject(str);
            jSONObject.put("enabled", z2);
            jSONObject.put(PHONE_NOTIFICATIONS_APP_ENABLED_IN_3P_APPS, z);
        } catch (JSONException e) {
            Log.e(TAG, "Failed to find app in settings map.", e);
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("enabled", z2);
                jSONObject2.put(PHONE_NOTIFICATIONS_APP_ENABLED_IN_3P_APPS, z);
                jSONObject2.put("name", str2);
                appSettingsMap.put(str, jSONObject2);
            } catch (JSONException e2) {
                Log.e(TAG, "Failed to insert app in settings map.", e2);
            }
        }
        settingsManager.setString(SettingsManager.PHONE_NOTIFICATION_APPS, appSettingsMap.toString());
    }

    private DialogDefinitionCustom getSetUpDialog(Context context) {
        Resources resources = context.getResources();
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(PHONE_SETUP_DIALOG_ID, resources.getString(R.string.settings_phone_setup_dialog_title), resources.getString(R.string.settings_phone_setup_dialog_body));
        dialogDefinitionCustom.setHeroImage(new DialogHeroImage(PHONE_SETUP_DIALOG_IMAGE, PHONE_SETUP_DIALOG_IMAGE_ASPECT_RATIO, null));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(CommonSystemDialogActions.CONTINUE, resources.getString(R.string.settings_phone_setup_dialog_button)));
        return dialogDefinitionCustom;
    }
}
