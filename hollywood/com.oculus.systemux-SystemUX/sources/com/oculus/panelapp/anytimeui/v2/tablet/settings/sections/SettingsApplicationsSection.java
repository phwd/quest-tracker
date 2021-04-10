package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections;

import android.content.Context;
import android.net.Uri;
import com.oculus.library.model.App;
import com.oculus.library.model.Image;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDescriptiveText;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsInfoBox;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsNavigationActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.permissions.PermissionsUtil;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.util.UiThreadExecutor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class SettingsApplicationsSection extends SettingsSection {
    private final Context mContext;
    private final AnytimeUIAndroidPanelAppV2 mPanelApp;
    private final Runnable mRefreshView;

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection
    public void destroy() {
    }

    public SettingsApplicationsSection(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, Runnable runnable) {
        super(context.getResources().getString(R.string.settings_application_section_title), null);
        this.mContext = context;
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mRefreshView = runnable;
        initializeApplicationDisclosure();
        initializeApplicationPermissionsItem();
        initializeAppPermissionDescription();
        initializeApplications();
    }

    private void initializeApplicationDisclosure() {
        addSettingsItem(new SettingsInfoBox.Builder(this.mPanelApp).withInfo(R.string.settings_application_permissions_disclosure));
    }

    private void initializeApplicationPermissionsItem() {
        addSettingsItem(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("permissions_by_type_subsection").withTitle(R.string.settings_application_permissions_navigation_title).withSubtitle(R.string.settings_application_permissions_navigation_subtitle).withIcon(R.drawable.oc_icon_privacy_filled_24_d2d2d2).withSettingsActionType(new SettingsNavigationActionType.Builder().isInternal(true).withSystemUXRoute(this.mPanelApp, SystemUXRoute.SETTINGS, TabletDeepLinkingUriUtil.SETTINGS_PERMISSIONS_URI)));
    }

    private void initializeAppPermissionDescription() {
        addSettingsItem(new SettingsDescriptiveText.Builder(this.mContext, this.mPanelApp).withBody(R.string.settings_application_permissions_description));
    }

    private void initializeApplications() {
        PermissionsUtil.getApps(this.mContext, new PermissionsUtil.GetAppsCallback(new ArrayList()) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsApplicationsSection$MX5M5rTJWEKSEx0nufxxVewXLTs */
            private final /* synthetic */ List f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.permissions.PermissionsUtil.GetAppsCallback
            public final void onResult(List list) {
                SettingsApplicationsSection.this.lambda$initializeApplications$320$SettingsApplicationsSection(this.f$1, list);
            }
        });
    }

    public /* synthetic */ void lambda$initializeApplications$320$SettingsApplicationsSection(List list, List list2) {
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            App app = (App) it.next();
            SettingsItem.Builder withSettingsActionType = new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("all_permissions_for_entitlement").withTitle(app.displayName).withSettingsActionType(new SettingsNavigationActionType.Builder().isInternal(true).withSystemUXRoute(this.mPanelApp, SystemUXRoute.SETTINGS, String.format("%s?package=%s", TabletDeepLinkingUriUtil.SETTINGS_APPLICATIONS_URI, app.packageName)));
            Image image = app.images.get(Image.ImageName.SOURCE_SQUARE);
            if (!(image == null || image.uri == null)) {
                withSettingsActionType.withImageUri(Uri.parse(image.uri));
            }
            list.add(withSettingsActionType);
        }
        addSettingsItems(list);
        UiThreadExecutor.getInstance().execute(this.mRefreshView);
    }
}
