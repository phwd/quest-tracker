package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections;

import android.util.Log;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.notifications.NotificationSettingsGraphQLUtil;

/* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.-$$Lambda$SettingsNotificationUserCategorySection$N1IchYtZb5L-Tozv_KccR-e7tRc  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$SettingsNotificationUserCategorySection$N1IchYtZb5LTozv_KccRe7tRc implements NotificationSettingsGraphQLUtil.QueryErrorHandler {
    public static final /* synthetic */ $$Lambda$SettingsNotificationUserCategorySection$N1IchYtZb5LTozv_KccRe7tRc INSTANCE = new $$Lambda$SettingsNotificationUserCategorySection$N1IchYtZb5LTozv_KccRe7tRc();

    private /* synthetic */ $$Lambda$SettingsNotificationUserCategorySection$N1IchYtZb5LTozv_KccRe7tRc() {
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.notifications.NotificationSettingsGraphQLUtil.QueryErrorHandler
    public final void onError(String str) {
        Log.d(SettingsNotificationUserCategorySection.TAG, "Failed to get developer application preferences");
    }
}
