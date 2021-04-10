package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.oculus.certificatepinning.FbCertificatePinnerFactory;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.SettingsManager;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDescriptiveText;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsNavigationActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.notifications.NotificationOptOutPreference;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.notifications.NotificationSettingsGraphQLUtil;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.notifications.NotificationUserControlCategory;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.util.UiThreadExecutor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;

public final class SettingsNotificationsSection extends SettingsSection {
    private static final String TAG = LoggingUtil.tag(SettingsNotificationsSection.class);
    private final Context mContext;
    private final OkHttpClient mOkHttpClient = new OkHttpClient.Builder().connectTimeout(6, TimeUnit.SECONDS).certificatePinner(FbCertificatePinnerFactory.create(Build.TIME)).build();
    private final AnytimeUIAndroidPanelAppV2 mPanelApp;
    private final Runnable mRefreshView;

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection
    public void destroy() {
    }

    public SettingsNotificationsSection(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, Runnable runnable) {
        super(context.getResources().getString(R.string.settings_notifications_section_title), null);
        this.mContext = context;
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mRefreshView = runnable;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingsItem.Builder(context, anytimeUIAndroidPanelAppV2).withSettingName(SettingsManager.DO_NOT_DISTURB).withTitle(R.string.settings_do_not_disturb_title).withSubtitle(R.string.settings_do_not_disturb_subtitle).withSettingsActionType(new SettingsToggleActionType.Builder().withGetValue($$Lambda$ziE5c5EZy5GNewNMMb09TB20Bs4.INSTANCE).withSetValue($$Lambda$SettingsNotificationsSection$1SqtClJ4BJFGVAx7ixW97APw1hU.INSTANCE)));
        arrayList.add(new SettingsDescriptiveText.Builder(context, anytimeUIAndroidPanelAppV2).withHeader(R.string.settings_notifications_sounds_title).withGatekeepers(Gatekeeper.SETTINGS_NEW_NOTIFICATIONS_SOUND));
        arrayList.add(new SettingsItem.Builder(context, anytimeUIAndroidPanelAppV2).withSettingName(SettingsManager.NEW_NOTIFICATIONS_SOUND).withTitle(R.string.settings_new_notifications_title).withSubtitle(R.string.settings_new_notifications_subtitle).withGatekeepers(Gatekeeper.SETTINGS_NEW_NOTIFICATIONS_SOUND).withSettingsActionType(new SettingsToggleActionType.Builder().withSettingsManagerKey(SettingsManager.NEW_NOTIFICATIONS_SOUND)));
        if (hasPhoneNotifications()) {
            arrayList.add(getNotificationHeader(this.mContext, this.mPanelApp));
        }
        arrayList.add(new SettingsItem.Builder(context, anytimeUIAndroidPanelAppV2).withSettingName("phone_notifications_subsection").withTitle(R.string.settings_phone_notifications_item_title).withSubtitle(R.string.settings_phone_notifications_item_subtitle).withGatekeepers(Gatekeeper.OCULUS_PHONE_NOTIFICATIONS).withSettingsActionType(new SettingsNavigationActionType.Builder().isInternal(true).withSystemUXRoute(anytimeUIAndroidPanelAppV2, SystemUXRoute.SETTINGS, TabletDeepLinkingUriUtil.SETTINGS_PHONE_NOTIFICATIONS_URI)));
        addSettingsItems(arrayList);
        fetchNotificationSettings();
    }

    private void fetchNotificationSettings() {
        if (this.mPanelApp.isAccessTokenValid()) {
            if (this.mPanelApp.isGKEnabled(Gatekeeper.SETTINGS_NOTIFS_USER_CONTROL)) {
                NotificationSettingsGraphQLUtil.queryUserControlCategories(this.mOkHttpClient, this.mPanelApp.getAccessToken(), new NotificationSettingsGraphQLUtil.UserControlCategoriesHandler() {
                    /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsNotificationsSection$6vyXaSsZXsiQwMmMysCVzmnPQeA */

                    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.notifications.NotificationSettingsGraphQLUtil.UserControlCategoriesHandler
                    public final void onResult(String str, List list) {
                        SettingsNotificationsSection.this.lambda$fetchNotificationSettings$446$SettingsNotificationsSection(str, list);
                    }
                }, $$Lambda$SettingsNotificationsSection$5Qf86nt4iA8x1X7zf98ATbETfL8.INSTANCE);
            } else {
                NotificationSettingsGraphQLUtil.queryOptOutPreferences(this.mOkHttpClient, this.mPanelApp.getAccessToken(), new NotificationSettingsGraphQLUtil.OptOutPreferencesHandler() {
                    /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsNotificationsSection$rOmsyJgPpF0dsGauCOPJeLMDsI4 */

                    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.notifications.NotificationSettingsGraphQLUtil.OptOutPreferencesHandler
                    public final void onResult(List list) {
                        SettingsNotificationsSection.this.lambda$fetchNotificationSettings$449$SettingsNotificationsSection(list);
                    }
                }, $$Lambda$SettingsNotificationsSection$n8ii9Eghil34ii_CnXmB_5tAqMg.INSTANCE);
            }
        }
    }

    public /* synthetic */ void lambda$fetchNotificationSettings$446$SettingsNotificationsSection(String str, List list) {
        String str2 = TAG;
        Log.d(str2, "Got user control categories, results: " + list.size());
        UiThreadExecutor.getInstance().execute(new Runnable(list) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsNotificationsSection$EMwIhoZd3YF3s_z2rBD27UCGwJI */
            private final /* synthetic */ List f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                SettingsNotificationsSection.this.lambda$null$445$SettingsNotificationsSection(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$null$445$SettingsNotificationsSection(List list) {
        addSettingsItems(getUserControlCategories(this.mContext, this.mPanelApp, list, hasPhoneNotifications()));
        this.mRefreshView.run();
    }

    static /* synthetic */ void lambda$fetchNotificationSettings$447(String str) {
        String str2 = TAG;
        Log.d(str2, "Failed to get user control categories: " + str);
    }

    public /* synthetic */ void lambda$fetchNotificationSettings$449$SettingsNotificationsSection(List list) {
        String str = TAG;
        Log.d(str, "Got opt out preferences, results: " + list.size());
        UiThreadExecutor.getInstance().execute(new Runnable(list) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsNotificationsSection$vQlcUDYK8NzfVY4Us0uon5etZ8 */
            private final /* synthetic */ List f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                SettingsNotificationsSection.this.lambda$null$448$SettingsNotificationsSection(this.f$1);
            }
        });
    }

    static /* synthetic */ void lambda$fetchNotificationSettings$450(String str) {
        String str2 = TAG;
        Log.d(str2, "Failed to get opt out preferences: " + str);
    }

    @VisibleForTesting
    public static List<BaseSettingsItem.Builder> getUserControlCategories(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, List<NotificationUserControlCategory> list, boolean z) {
        ArrayList arrayList = new ArrayList();
        if (!list.isEmpty()) {
            if (!z) {
                arrayList.add(getNotificationHeader(context, anytimeUIAndroidPanelAppV2));
            }
            for (NotificationUserControlCategory notificationUserControlCategory : list) {
                arrayList.add(new SettingsItem.Builder(context, anytimeUIAndroidPanelAppV2).withSettingName(notificationUserControlCategory.getCategory()).withTitle(notificationUserControlCategory.getTitle()).withSubtitle(notificationUserControlCategory.getDescription()).withSettingsActionType(new SettingsNavigationActionType.Builder().isInternal(true).withSystemUXRoute(anytimeUIAndroidPanelAppV2, SystemUXRoute.SETTINGS, String.format("%s?category=%s", "/notifications", notificationUserControlCategory.getCategory()))));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    /* renamed from: addOptOutPreferences */
    public void lambda$null$448$SettingsNotificationsSection(List<NotificationOptOutPreference> list) {
        if (!list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            if (!hasPhoneNotifications()) {
                arrayList.add(getNotificationHeader(this.mContext, this.mPanelApp));
            }
            for (NotificationOptOutPreference notificationOptOutPreference : list) {
                arrayList.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName(notificationOptOutPreference.getKey()).withTitle(notificationOptOutPreference.getName()).withSubtitle(notificationOptOutPreference.getDescription()).withSettingsActionType(new SettingsToggleActionType.Builder().withGetValue(new SettingsToggleActionType.ToggleGetValueHandler() {
                    /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsNotificationsSection$tjLfVSqee3RaTG1A25L8Fd5ycJ8 */

                    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleGetValueHandler
                    public final boolean get() {
                        return SettingsNotificationsSection.lambda$addOptOutPreferences$451(NotificationOptOutPreference.this);
                    }
                }).withSetValue(new SettingsToggleActionType.ToggleSetValueHandler(notificationOptOutPreference) {
                    /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsNotificationsSection$gUmVWlKJOVI7vCGJh_4B_NcrQQ */
                    private final /* synthetic */ NotificationOptOutPreference f$1;

                    {
                        this.f$1 = r2;
                    }

                    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleSetValueHandler
                    public final void set(boolean z, SettingsToggleActionType settingsToggleActionType) {
                        SettingsNotificationsSection.this.lambda$addOptOutPreferences$452$SettingsNotificationsSection(this.f$1, z, settingsToggleActionType);
                    }
                })));
            }
            addSettingsItems(arrayList);
            this.mRefreshView.run();
        }
    }

    static /* synthetic */ boolean lambda$addOptOutPreferences$451(NotificationOptOutPreference notificationOptOutPreference) {
        return !notificationOptOutPreference.getOptOutValue();
    }

    public /* synthetic */ void lambda$addOptOutPreferences$452$SettingsNotificationsSection(NotificationOptOutPreference notificationOptOutPreference, boolean z, SettingsToggleActionType settingsToggleActionType) {
        notificationOptOutPreference.setOptOutValue(!z);
        NotificationSettingsGraphQLUtil.setOptOutPreference(this.mOkHttpClient, this.mPanelApp.getAccessToken(), notificationOptOutPreference);
    }

    private static SettingsDescriptiveText.Builder getNotificationHeader(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        return new SettingsDescriptiveText.Builder(context, anytimeUIAndroidPanelAppV2).withHeader(R.string.settings_notifications_section_title).withGatekeepers(Gatekeeper.SETTINGS_NEW_NOTIFICATIONS_SOUND);
    }

    private boolean hasPhoneNotifications() {
        return this.mPanelApp.isGKEnabled(Gatekeeper.OCULUS_PHONE_NOTIFICATIONS);
    }
}
