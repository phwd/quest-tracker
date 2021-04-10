package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.environment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.library.model.App;
import com.oculus.library.model.AppStatus;
import com.oculus.os.SettingsManager;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIPanelAppBase;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryAppUtils;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.util.SettingsLogger;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.util.HorizonUtil;

public class EnvironmentUtil {
    private static final String ENVIRONMENT_PATH_TEMPLATE = "apk://%s/assets/scene.zip";
    private static final String TAG = LoggingUtil.tag(EnvironmentUtil.class);

    public static void doStatusBasedEnvironmentAction(App app, AnytimeUIPanelAppBase anytimeUIPanelAppBase, boolean z, SettingsLogger settingsLogger) {
        HorizonUtil.markIsSeen(anytimeUIPanelAppBase.getContext(), app.packageName);
        int i = AnonymousClass1.$SwitchMap$com$oculus$library$model$AppStatus[app.status.ordinal()];
        if (i == 1) {
            new SettingsManager().setString(SettingsManager.ENVIRONMENT_SELECTED, getEnvironmentPath(app.packageName));
        } else if (i == 2 || i == 3) {
            if (!z) {
                anytimeUIPanelAppBase.actionNavigate(SystemUXRoute.WIFI, "");
            } else {
                LibraryAppUtils.executeAppInstall(anytimeUIPanelAppBase, app.packageName, false);
            }
        } else if (i == 4 || i == 5) {
            HorizonUtil.cancelDownloadPackage(anytimeUIPanelAppBase.getContext(), app.packageName);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.environment.EnvironmentUtil$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$library$model$AppStatus = new int[AppStatus.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.oculus.library.model.AppStatus[] r0 = com.oculus.library.model.AppStatus.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.environment.EnvironmentUtil.AnonymousClass1.$SwitchMap$com$oculus$library$model$AppStatus = r0
                int[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.environment.EnvironmentUtil.AnonymousClass1.$SwitchMap$com$oculus$library$model$AppStatus     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.library.model.AppStatus r1 = com.oculus.library.model.AppStatus.INSTALLED     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.environment.EnvironmentUtil.AnonymousClass1.$SwitchMap$com$oculus$library$model$AppStatus     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.library.model.AppStatus r1 = com.oculus.library.model.AppStatus.INSTALL_AVAILABLE     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.environment.EnvironmentUtil.AnonymousClass1.$SwitchMap$com$oculus$library$model$AppStatus     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.library.model.AppStatus r1 = com.oculus.library.model.AppStatus.ENTITLED     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.environment.EnvironmentUtil.AnonymousClass1.$SwitchMap$com$oculus$library$model$AppStatus     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.oculus.library.model.AppStatus r1 = com.oculus.library.model.AppStatus.DOWNLOAD_QUEUED     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.environment.EnvironmentUtil.AnonymousClass1.$SwitchMap$com$oculus$library$model$AppStatus     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.oculus.library.model.AppStatus r1 = com.oculus.library.model.AppStatus.DOWNLOADING     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.environment.EnvironmentUtil.AnonymousClass1.<clinit>():void");
        }
    }

    public static String getEnvironmentPath(String str) {
        return String.format(ENVIRONMENT_PATH_TEMPLATE, str);
    }

    public static Drawable getEnvironmentTileCTAIcon(Context context, App app) {
        int i = AnonymousClass1.$SwitchMap$com$oculus$library$model$AppStatus[app.status.ordinal()];
        if (i != 1 && i != 2) {
            return LibraryAppUtils.getAppTileCTAIcon(context, app);
        }
        if (isEnvironmentActive(app.packageName)) {
            return ContextCompat.getDrawable(context, R.drawable.oc_icon_check_circle_filled_24_d2d2d2);
        }
        return ContextCompat.getDrawable(context, R.drawable.oc_icon_play_circle_filled_24_d2d2d2);
    }

    public static String getEnvironmentTileCTAText(Context context, App app) {
        Resources resources = context.getResources();
        int i = AnonymousClass1.$SwitchMap$com$oculus$library$model$AppStatus[app.status.ordinal()];
        if (i != 1 && i != 2) {
            return LibraryAppUtils.getAppTileCTAText(context, app);
        }
        if (isEnvironmentActive(app.packageName)) {
            return resources.getString(R.string.settings_environment_tile_cta_active);
        }
        return resources.getString(R.string.settings_environment_tile_cta_apply);
    }

    public static boolean isEnvironmentActive(String str) {
        return new SettingsManager().getString(SettingsManager.ENVIRONMENT_SELECTED, "").equals(getEnvironmentPath(str));
    }
}
