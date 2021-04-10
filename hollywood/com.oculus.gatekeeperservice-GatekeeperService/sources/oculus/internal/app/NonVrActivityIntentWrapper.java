package oculus.internal.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import oculus.internal.Constants;

public class NonVrActivityIntentWrapper {
    private static final boolean DEBUG = false;
    private static final String TAG = NonVrActivityIntentWrapper.class.getSimpleName();
    private static final Uri VR_DESKTOP_APK_URI = Uri.parse("apk://com.oculus.vrshell.desktop");
    private static final String VR_DESKTOP_SCHEME = "vrdesktop";
    private static final String VR_SHELL_KEY_URI = "uri";
    private static final String VR_SHELL_MAIN_ACTIVITY = "com.oculus.vrshell.MainActivity";

    public static Intent checkAndConvertToVrDesktopIntentAsUser(Context context, Intent intent, int userId) {
        if (!(intent == null || intent.getComponent() == null)) {
            int packageType = OculusGoPackageTypeHelper.getPackageTypeAsUser(context, intent.getComponent(), userId);
            if ((isMainLauncherIntent(intent) || packageType == -2) && intent.getExtras() == null && (packageType == -2 || packageType == 3)) {
                return buildVrDesktopIntent(intent);
            }
        }
        return intent;
    }

    private static boolean isMainLauncherIntent(Intent intent) {
        boolean hasActionMain = "android.intent.action.MAIN".equals(intent.getAction());
        boolean hasRequiredLauncherCategory = intent.hasCategory("android.intent.category.INFO") || intent.hasCategory("android.intent.category.LAUNCHER");
        if (!hasActionMain || !hasRequiredLauncherCategory) {
            return DEBUG;
        }
        return true;
    }

    private static Intent buildVrDesktopIntent(Intent targetIntent) {
        String packageName = targetIntent.getComponent().getPackageName();
        Uri uri = new Uri.Builder().scheme(VR_DESKTOP_SCHEME).authority(packageName).appendPath(targetIntent.getComponent().getClassName()).build();
        Intent vrDesktopIntent = new Intent(targetIntent.getAction());
        vrDesktopIntent.addFlags(65536);
        vrDesktopIntent.setComponent(new ComponentName(Constants.SHELL_PACKAGE, VR_SHELL_MAIN_ACTIVITY));
        vrDesktopIntent.putExtra(VR_SHELL_KEY_URI, uri.toString());
        vrDesktopIntent.setData(VR_DESKTOP_APK_URI);
        return vrDesktopIntent;
    }
}
