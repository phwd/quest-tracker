package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.permissions;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import com.oculus.library.model.App;
import com.oculus.library.model.AppStatus;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibrarySorter;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryUtils;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.permissions.PermissionsUtil;
import com.oculus.vrshell.util.HorizonUtil;
import com.oculus.vrshell.util.ThreadExecutor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class PermissionsUtil {

    public interface GetAppsCallback {
        void onResult(List<App> list);
    }

    public static Map<PermissionGroup, List<AppPermission>> getPermissionGroupsFromApps(Context context, List<App> list) {
        HashMap hashMap = new HashMap();
        for (App app : list) {
            for (AppPermission appPermission : getPermissionsForApp(context, app)) {
                PermissionGroup permissionGroup = appPermission.getPermissionGroup();
                List list2 = (List) hashMap.get(permissionGroup);
                if (list2 == null) {
                    list2 = new ArrayList();
                }
                list2.add(appPermission);
                hashMap.put(permissionGroup, list2);
            }
        }
        return hashMap;
    }

    public static List<AppPermission> getPermissionsForApp(Context context, App app) {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = context.getPackageManager();
        for (String str : app.latestPermissions) {
            try {
                PermissionInfo permissionInfo = packageManager.getPermissionInfo(str, 0);
                String groupOfPermission = com.oculus.os.pm.PackageManager.getGroupOfPermission(permissionInfo);
                arrayList.add(new AppPermission(app, str, new PermissionGroup(groupOfPermission, packageManager.getPermissionGroupInfo(groupOfPermission, 0).loadLabel(packageManager).toString()), permissionInfo.loadLabel(packageManager).toString()));
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return arrayList;
    }

    public static void getApps(Context context, GetAppsCallback getAppsCallback) {
        ThreadExecutor.getInstance().execute(new Callable(context, getAppsCallback) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.permissions.$$Lambda$PermissionsUtil$mZYLvrsZFGJB85a2LVw2FvLltg */
            private final /* synthetic */ Context f$0;
            private final /* synthetic */ PermissionsUtil.GetAppsCallback f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return PermissionsUtil.lambda$getApps$486(this.f$0, this.f$1);
            }
        });
    }

    static /* synthetic */ Object lambda$getApps$486(Context context, GetAppsCallback getAppsCallback) throws Exception {
        List<App> excludeHiddenApps = LibraryUtils.excludeHiddenApps((List) HorizonUtil.getApplications(context).stream().filter($$Lambda$PermissionsUtil$3DgYGsVxAFyMqm302i6j2N7ZiP4.INSTANCE).collect(Collectors.toList()));
        LibraryUtils.sortAppsBySorter(excludeHiddenApps, LibrarySorter.A_TO_Z);
        getAppsCallback.onResult(excludeHiddenApps);
        return null;
    }

    static /* synthetic */ boolean lambda$null$485(App app) {
        return app.status == AppStatus.INSTALLED || app.status == AppStatus.UNINSTALLING;
    }

    public static void sortPermissionGroupsByDisplayName(List<PermissionGroup> list) {
        Collections.sort(list, $$Lambda$PermissionsUtil$VjQOKie8SnXkMK9q0n3valbwMYw.INSTANCE);
    }
}
