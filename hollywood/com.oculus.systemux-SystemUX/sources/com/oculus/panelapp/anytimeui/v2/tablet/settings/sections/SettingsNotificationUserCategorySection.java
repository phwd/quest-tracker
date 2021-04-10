package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.oculus.certificatepinning.FbCertificatePinnerFactory;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDescriptiveText;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.notifications.DeveloperApplicationPreference;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.notifications.NotificationSettingsGraphQLUtil;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.notifications.NotificationUserControlCategory;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.notifications.NotificationUserControlCategoryPreference;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.util.UiThreadExecutor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import okhttp3.OkHttpClient;

public class SettingsNotificationUserCategorySection extends SettingsSection {
    private static final String TAG = LoggingUtil.tag(SettingsNotificationUserCategorySection.class);
    private final Context mContext;
    private OkHttpClient mOkHttpClient;
    private final AnytimeUIAndroidPanelAppV2 mPanelApp;
    private final Runnable mRefreshView;

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection
    public void destroy() {
    }

    public SettingsNotificationUserCategorySection(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, Runnable runnable, String str) {
        super(context.getResources().getString(R.string.settings_notifications_section_title), "/notifications");
        this.mContext = context;
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mRefreshView = runnable;
        if (anytimeUIAndroidPanelAppV2.isAccessTokenValid()) {
            this.mOkHttpClient = new OkHttpClient.Builder().connectTimeout(6, TimeUnit.SECONDS).certificatePinner(FbCertificatePinnerFactory.create(Build.TIME)).build();
            NotificationSettingsGraphQLUtil.queryUserControlCategories(this.mOkHttpClient, anytimeUIAndroidPanelAppV2.getAccessToken(), new NotificationSettingsGraphQLUtil.UserControlCategoriesHandler(str) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsNotificationUserCategorySection$o6Dwo5kFWYeqSr9EiLKyu7ed7Zo */
                private final /* synthetic */ String f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.notifications.NotificationSettingsGraphQLUtil.UserControlCategoriesHandler
                public final void onResult(String str, List list) {
                    SettingsNotificationUserCategorySection.this.lambda$new$437$SettingsNotificationUserCategorySection(this.f$1, str, list);
                }
            }, new NotificationSettingsGraphQLUtil.QueryErrorHandler() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsNotificationUserCategorySection$h96JG_j8S2oqXdMkfyizicsAY */

                @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.notifications.NotificationSettingsGraphQLUtil.QueryErrorHandler
                public final void onError(String str) {
                    SettingsNotificationUserCategorySection.this.lambda$new$438$SettingsNotificationUserCategorySection(str);
                }
            });
        }
    }

    public /* synthetic */ void lambda$new$437$SettingsNotificationUserCategorySection(String str, String str2, List list) {
        Optional findFirst = list.stream().filter(new Predicate(str) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsNotificationUserCategorySection$ole92cNWb7dlTZlh22XdfHIAsE0 */
            private final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((NotificationUserControlCategory) obj).getCategory().equalsIgnoreCase(this.f$0);
            }
        }).findFirst();
        if (findFirst.isPresent()) {
            UiThreadExecutor.getInstance().execute(new Runnable(findFirst, str2) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsNotificationUserCategorySection$mOhUTyuR3_JQ5hJj8EboT9Ej_wo */
                private final /* synthetic */ Optional f$1;
                private final /* synthetic */ String f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    SettingsNotificationUserCategorySection.this.lambda$null$436$SettingsNotificationUserCategorySection(this.f$1, this.f$2);
                }
            });
            return;
        }
        String str3 = TAG;
        Log.d(str3, "Failed to find user control category: " + str);
        navigateToTopLevelNotifications();
    }

    public /* synthetic */ void lambda$null$436$SettingsNotificationUserCategorySection(Optional optional, String str) {
        NotificationUserControlCategory notificationUserControlCategory = (NotificationUserControlCategory) optional.get();
        setTitle(notificationUserControlCategory.getTitle());
        addNotificationPreferences(str, notificationUserControlCategory);
        if (notificationUserControlCategory.isDeveloperApplication()) {
            queryDeveloperApplicationPreferences();
        }
    }

    public /* synthetic */ void lambda$new$438$SettingsNotificationUserCategorySection(String str) {
        String str2 = TAG;
        Log.d(str2, "Failed to get user control categories: " + str);
        navigateToTopLevelNotifications();
    }

    private void queryDeveloperApplicationPreferences() {
        NotificationSettingsGraphQLUtil.queryDeveloperApplicationPreferences(this.mOkHttpClient, this.mPanelApp.getAccessToken(), new NotificationSettingsGraphQLUtil.DeveloperApplicationPreferencesHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsNotificationUserCategorySection$QYCiPlIEYhTiUAuZYpQT1Is7GnA */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.notifications.NotificationSettingsGraphQLUtil.DeveloperApplicationPreferencesHandler
            public final void onResult(String str, String str2, List list) {
                SettingsNotificationUserCategorySection.this.lambda$queryDeveloperApplicationPreferences$440$SettingsNotificationUserCategorySection(str, str2, list);
            }
        }, $$Lambda$SettingsNotificationUserCategorySection$N1IchYtZb5LTozv_KccRe7tRc.INSTANCE);
    }

    public /* synthetic */ void lambda$queryDeveloperApplicationPreferences$440$SettingsNotificationUserCategorySection(String str, String str2, List list) {
        UiThreadExecutor.getInstance().execute(new Runnable(str, str2, list) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsNotificationUserCategorySection$erPfawdtsY7DDyo438LArgQGJQ */
            private final /* synthetic */ String f$1;
            private final /* synthetic */ String f$2;
            private final /* synthetic */ List f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void run() {
                SettingsNotificationUserCategorySection.this.lambda$null$439$SettingsNotificationUserCategorySection(this.f$1, this.f$2, this.f$3);
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: addDeveloperApplicationPreferences */
    public void lambda$null$439$SettingsNotificationUserCategorySection(String str, String str2, List<DeveloperApplicationPreference> list) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingsDescriptiveText.Builder(this.mContext, this.mPanelApp).withBody(str2));
        for (DeveloperApplicationPreference developerApplicationPreference : list) {
            SettingsItem.Builder withImageUri = new SettingsItem.Builder(this.mContext, this.mPanelApp).withTitle(developerApplicationPreference.getDisplayName()).withImageUri(developerApplicationPreference.getImageUri());
            SettingsToggleActionType.Builder builder = new SettingsToggleActionType.Builder();
            developerApplicationPreference.getClass();
            arrayList.add(withImageUri.withSettingsActionType(builder.withGetValue(new SettingsToggleActionType.ToggleGetValueHandler() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$pPliHzlg0zPUp5VwXwgi2yQU2H0 */

                @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleGetValueHandler
                public final boolean get() {
                    return DeveloperApplicationPreference.this.isEnabled();
                }
            }).withSetValue(new SettingsToggleActionType.ToggleSetValueHandler(developerApplicationPreference, str) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsNotificationUserCategorySection$Tqn2gUkDgGdhPRJhdcdMJFeRecA */
                private final /* synthetic */ DeveloperApplicationPreference f$1;
                private final /* synthetic */ String f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleSetValueHandler
                public final void set(boolean z, SettingsToggleActionType settingsToggleActionType) {
                    SettingsNotificationUserCategorySection.this.lambda$addDeveloperApplicationPreferences$442$SettingsNotificationUserCategorySection(this.f$1, this.f$2, z, settingsToggleActionType);
                }
            })));
        }
        addSettingsItems(arrayList);
        this.mRefreshView.run();
    }

    public /* synthetic */ void lambda$addDeveloperApplicationPreferences$442$SettingsNotificationUserCategorySection(DeveloperApplicationPreference developerApplicationPreference, String str, boolean z, SettingsToggleActionType settingsToggleActionType) {
        developerApplicationPreference.setEnabled(Boolean.valueOf(z));
        NotificationSettingsGraphQLUtil.setDeveloperApplicationPreferenceEnabled(this.mOkHttpClient, this.mPanelApp.getAccessToken(), str, developerApplicationPreference);
    }

    private void addNotificationPreferences(String str, NotificationUserControlCategory notificationUserControlCategory) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingsDescriptiveText.Builder(this.mContext, this.mPanelApp).withBody(notificationUserControlCategory.getDescription()));
        for (NotificationUserControlCategoryPreference notificationUserControlCategoryPreference : notificationUserControlCategory.getPreferences()) {
            SettingsItem.Builder withIcon = new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName(notificationUserControlCategoryPreference.getMedium()).withTitle(notificationUserControlCategoryPreference.getTitle()).withSubtitle(notificationUserControlCategoryPreference.getDescription()).withIcon(notificationUserControlCategoryPreference.getIcon());
            SettingsToggleActionType.Builder builder = new SettingsToggleActionType.Builder();
            notificationUserControlCategoryPreference.getClass();
            arrayList.add(withIcon.withSettingsActionType(builder.withGetValue(new SettingsToggleActionType.ToggleGetValueHandler() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$lrl80QgWoJ_3FxTWc3ZrHh8ZuOo */

                @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleGetValueHandler
                public final boolean get() {
                    return NotificationUserControlCategoryPreference.this.isEnabled();
                }
            }).withSetValue(new SettingsToggleActionType.ToggleSetValueHandler(notificationUserControlCategoryPreference, str, notificationUserControlCategory) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsNotificationUserCategorySection$xKW_4uwxGTfXif7jUPViNpCr78 */
                private final /* synthetic */ NotificationUserControlCategoryPreference f$1;
                private final /* synthetic */ String f$2;
                private final /* synthetic */ NotificationUserControlCategory f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleSetValueHandler
                public final void set(boolean z, SettingsToggleActionType settingsToggleActionType) {
                    SettingsNotificationUserCategorySection.this.lambda$addNotificationPreferences$443$SettingsNotificationUserCategorySection(this.f$1, this.f$2, this.f$3, z, settingsToggleActionType);
                }
            })));
        }
        addSettingsItems(arrayList);
        this.mRefreshView.run();
    }

    public /* synthetic */ void lambda$addNotificationPreferences$443$SettingsNotificationUserCategorySection(NotificationUserControlCategoryPreference notificationUserControlCategoryPreference, String str, NotificationUserControlCategory notificationUserControlCategory, boolean z, SettingsToggleActionType settingsToggleActionType) {
        notificationUserControlCategoryPreference.setEnabled(Boolean.valueOf(z));
        NotificationSettingsGraphQLUtil.setUserControlCategoryEnabled(this.mOkHttpClient, this.mPanelApp.getAccessToken(), str, notificationUserControlCategory, notificationUserControlCategoryPreference);
    }

    private void navigateToTopLevelNotifications() {
        this.mPanelApp.actionNavigate(SystemUXRoute.SETTINGS, "/notifications");
    }
}
