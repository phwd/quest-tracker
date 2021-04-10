package com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.enterprise.Configuration;
import com.oculus.os.enterprise.EnterpriseServer;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.models.LauncherAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* access modifiers changed from: package-private */
public class Config {
    private static final String TAG = LoggingUtil.tag(Config.class);
    final List<LauncherAction> launcherActions;
    final boolean showUnknownSources;

    private Config(List<LauncherAction> list, boolean z) {
        this.launcherActions = list;
        this.showUnknownSources = z;
    }

    static Config readEnterprise(Context context) {
        ApplicationInfo applicationInfo;
        Configuration configuration = EnterpriseServer.getConfiguration(context);
        ArrayList arrayList = new ArrayList();
        String[] applications = configuration.getModes()[configuration.getDefaultModeIndex()].getApplications();
        for (String str : applications) {
            Log.d(TAG, "Adding enterprise activity: " + str);
            String str2 = str.split("/")[0];
            PackageManager packageManager = context.getPackageManager();
            try {
                applicationInfo = packageManager.getApplicationInfo(str2, 0);
            } catch (PackageManager.NameNotFoundException e) {
                Log.d(TAG, "Name not found for enterprise package: " + str2, e);
                applicationInfo = null;
            }
            String string = context.getResources().getString(R.string.anytime_tablet_managed_launcher_tile_default_app_name);
            if (applicationInfo != null) {
                string = (String) packageManager.getApplicationLabel(applicationInfo);
            }
            Log.d(TAG, "Adding enterprise app with name: " + string);
            arrayList.add(new LauncherAction(string, str2, str));
        }
        return new Config(Collections.unmodifiableList(arrayList), configuration.getShowUnknownSources());
    }
}
