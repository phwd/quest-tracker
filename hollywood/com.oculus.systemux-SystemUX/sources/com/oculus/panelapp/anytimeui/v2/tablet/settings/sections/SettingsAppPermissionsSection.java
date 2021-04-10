package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections;

import android.content.Context;
import com.oculus.library.model.App;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDescriptiveText;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsNavigationActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.permissions.AppPermission;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.permissions.PermissionGroup;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.permissions.PermissionsUtil;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.util.HorizonUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class SettingsAppPermissionsSection extends SettingsSection {
    private final Context mContext;
    private final AnytimeUIAndroidPanelAppV2 mPanelApp;

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection
    public void destroy() {
    }

    public SettingsAppPermissionsSection(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, String str) {
        super(null, TabletDeepLinkingUriUtil.SETTINGS_APPLICATIONS_URI);
        App application = HorizonUtil.getApplication(context, str);
        setTitle(application.displayName);
        this.mContext = context;
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        List<AppPermission> permissionsForApp = PermissionsUtil.getPermissionsForApp(context, application);
        if (permissionsForApp.isEmpty()) {
            initializeEmptyState();
        } else {
            initializePermissions(str, permissionsForApp);
        }
    }

    private void initializeEmptyState() {
        addSettingsItem(new SettingsDescriptiveText.Builder(this.mContext, this.mPanelApp).withBody(R.string.settings_applications_no_permissions_heading));
    }

    private void initializePermissions(String str, List<AppPermission> list) {
        Map map = (Map) list.stream().collect(Collectors.groupingBy($$Lambda$Qe33PDwYYm2KgyQkWvWKIusuKn8.INSTANCE));
        ArrayList arrayList = new ArrayList(map.keySet());
        PermissionsUtil.sortPermissionGroupsByDisplayName(arrayList);
        ArrayList arrayList2 = new ArrayList();
        arrayList.forEach(new Consumer(map, arrayList2, str) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAppPermissionsSection$aBOighYJEELSkbw9ZpIWlWUhE8 */
            private final /* synthetic */ Map f$1;
            private final /* synthetic */ List f$2;
            private final /* synthetic */ String f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                SettingsAppPermissionsSection.this.lambda$initializePermissions$319$SettingsAppPermissionsSection(this.f$1, this.f$2, this.f$3, (PermissionGroup) obj);
            }
        });
        addSettingsItems(arrayList2);
    }

    public /* synthetic */ void lambda$initializePermissions$319$SettingsAppPermissionsSection(Map map, List list, String str, PermissionGroup permissionGroup) {
        StringBuilder sb = new StringBuilder();
        List<AppPermission> list2 = (List) map.get(permissionGroup);
        if (list2 != null) {
            for (AppPermission appPermission : list2) {
                sb.append(appPermission.getLabel());
                sb.append("\n");
            }
            SettingsItem.Builder builder = new SettingsItem.Builder(this.mContext, this.mPanelApp);
            list.add(builder.withSettingName("permissions_for_type_" + permissionGroup.getGroup().toLowerCase(Locale.ROOT)).withTitle(permissionGroup.getDisplayName()).withSubtitle(sb.toString()).withSettingsActionType(new SettingsNavigationActionType.Builder().isInternal(false).withStringRoute(this.mPanelApp, String.format("%s/%s", SystemUXRoute.PERMISSIONS.path(), str), "")));
        }
    }
}
