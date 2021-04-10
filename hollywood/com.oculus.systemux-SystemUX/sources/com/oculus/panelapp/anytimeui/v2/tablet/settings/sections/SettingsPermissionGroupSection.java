package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections;

import android.content.Context;
import android.net.Uri;
import com.oculus.library.model.App;
import com.oculus.library.model.Image;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibrarySorter;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryUtils;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsNavigationActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.permissions.AppPermission;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.permissions.PermissionGroup;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.permissions.PermissionsUtil;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.util.UiThreadExecutor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SettingsPermissionGroupSection extends SettingsSection {
    private final Context mContext;
    private final String mGroup;
    private final AnytimeUIAndroidPanelAppV2 mPanelApp;
    private final Runnable mRefreshView;

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection
    public void destroy() {
    }

    public SettingsPermissionGroupSection(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, String str, Runnable runnable) {
        super(context.getResources().getString(R.string.settings_application_section_title), TabletDeepLinkingUriUtil.SETTINGS_PERMISSIONS_URI);
        this.mContext = context;
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mGroup = str;
        this.mRefreshView = runnable;
        PermissionsUtil.getApps(context, new PermissionsUtil.GetAppsCallback() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsPermissionGroupSection$C0SHAtxst7fed4_HNjfrc6XzKrA */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.permissions.PermissionsUtil.GetAppsCallback
            public final void onResult(List list) {
                SettingsPermissionGroupSection.lambda$C0SHAtxst7fed4_HNjfrc6XzKrA(SettingsPermissionGroupSection.this, list);
            }
        });
    }

    /* access modifiers changed from: private */
    public void initializeAppsList(List<App> list) {
        List<AppPermission> list2 = PermissionsUtil.getPermissionGroupsFromApps(this.mContext, list).get(new PermissionGroup(this.mGroup, ""));
        if (list2 != null) {
            ArrayList<App> arrayList = new ArrayList(((Map) list2.stream().collect(Collectors.groupingBy($$Lambda$UzO_peGvcfmzNFdSmpF6dWocec0.INSTANCE))).keySet());
            LibraryUtils.sortAppsBySorter(arrayList, LibrarySorter.A_TO_Z);
            List<BaseSettingsItem.Builder> arrayList2 = new ArrayList<>();
            for (App app : arrayList) {
                SettingsItem.Builder withSettingsActionType = new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName(app.packageName).withTitle(app.displayName).withSettingsActionType(new SettingsNavigationActionType.Builder().isInternal(false).withStringRoute(this.mPanelApp, String.format("%s/%s", SystemUXRoute.PERMISSIONS.path(), app.packageName), ""));
                Image image = app.images.get(Image.ImageName.SOURCE_SQUARE);
                if (!(image == null || image.uri == null)) {
                    withSettingsActionType.withImageUri(Uri.parse(image.uri));
                }
                arrayList2.add(withSettingsActionType);
            }
            addSettingsItems(arrayList2);
            UiThreadExecutor.getInstance().execute(this.mRefreshView);
        }
    }
}
