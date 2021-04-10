package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections;

import android.content.Context;
import com.oculus.library.model.App;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
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
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SettingsPermissionsSection extends SettingsSection {
    private final Context mContext;
    private final AnytimeUIAndroidPanelAppV2 mPanelApp;
    private final Runnable mRefreshView;

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection
    public void destroy() {
    }

    public SettingsPermissionsSection(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, Runnable runnable) {
        super(context.getResources().getString(R.string.settings_permissions_section_title), TabletDeepLinkingUriUtil.SETTINGS_APPLICATIONS_URI);
        this.mContext = context;
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mRefreshView = runnable;
        PermissionsUtil.getApps(context, new PermissionsUtil.GetAppsCallback() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsPermissionsSection$QbcOMMusOIGPZ6kVuKEiyEi93M */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.permissions.PermissionsUtil.GetAppsCallback
            public final void onResult(List list) {
                SettingsPermissionsSection.m4lambda$QbcOMMusOIGPZ6kVuKEiyEi93M(SettingsPermissionsSection.this, list);
            }
        });
    }

    /* access modifiers changed from: private */
    public void initializeAppPermissionGroups(List<App> list) {
        Map<PermissionGroup, List<AppPermission>> permissionGroupsFromApps = PermissionsUtil.getPermissionGroupsFromApps(this.mContext, list);
        ArrayList arrayList = new ArrayList(permissionGroupsFromApps.keySet());
        PermissionsUtil.sortPermissionGroupsByDisplayName(arrayList);
        ArrayList arrayList2 = new ArrayList();
        arrayList.forEach(new Consumer(permissionGroupsFromApps, arrayList2) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsPermissionsSection$cSo3kY0Y4kd9sPqmXaZ8SjyJROg */
            private final /* synthetic */ Map f$1;
            private final /* synthetic */ List f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                SettingsPermissionsSection.this.lambda$initializeAppPermissionGroups$457$SettingsPermissionsSection(this.f$1, this.f$2, (PermissionGroup) obj);
            }
        });
        addSettingsItems(arrayList2);
        UiThreadExecutor.getInstance().execute(this.mRefreshView);
    }

    public /* synthetic */ void lambda$initializeAppPermissionGroups$457$SettingsPermissionsSection(Map map, List list, PermissionGroup permissionGroup) {
        List<AppPermission> list2 = (List) map.get(permissionGroup);
        if (list2 != null) {
            list.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName(permissionGroup.getGroup()).withTitle(permissionGroup.getDisplayName()).withSubtitle(this.mContext.getResources().getQuantityString(R.plurals.settings_permissions_apps_granted_subtitle, getTotalApps(list2), Integer.valueOf(getGrantedApps(list2)), Integer.valueOf(getTotalApps(list2)))).withSettingsActionType(new SettingsNavigationActionType.Builder().isInternal(true).withSystemUXRoute(this.mPanelApp, SystemUXRoute.SETTINGS, String.format("%s?group=%s", TabletDeepLinkingUriUtil.SETTINGS_PERMISSIONS_URI, permissionGroup.getGroup()))));
        }
    }

    private int getTotalApps(List<AppPermission> list) {
        return ((Map) list.stream().collect(Collectors.groupingBy($$Lambda$UzO_peGvcfmzNFdSmpF6dWocec0.INSTANCE))).size();
    }

    private int getGrantedApps(List<AppPermission> list) {
        return ((Map) list.stream().filter(new Predicate() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsPermissionsSection$NEN9_VVYOTjOamORyuXIGojbG3k */

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return SettingsPermissionsSection.this.lambda$getGrantedApps$458$SettingsPermissionsSection((AppPermission) obj);
            }
        }).collect(Collectors.groupingBy($$Lambda$UzO_peGvcfmzNFdSmpF6dWocec0.INSTANCE))).size();
    }

    public /* synthetic */ boolean lambda$getGrantedApps$458$SettingsPermissionsSection(AppPermission appPermission) {
        return appPermission.isGranted(this.mContext);
    }
}
