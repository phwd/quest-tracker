package oculus.internal.app;

import android.app.ActivityThread;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import oculus.internal.Constants;
import oculus.internal.Intent;

public class OculusGoPackageTypeHelper {
    public static final int BLACK_LISTED_PKG = -1;
    private static final Set<String> BLACK_LISTED_PKGS = new HashSet(Arrays.asList("com.android.cts.monkey", "com.android.cts.monkey2", "com.android.cts.net.hostside.app2", "com.android.cts.launcherapps.simpleapp", "com.oculus.home", Constants.HORIZON_PACKAGE, "com.oculus.lockscreen", Constants.MEDIA_PLAYER_PACKAGE, Constants.SYSTEM_ACTIVITIES_PACKAGE, Constants.SHELL_PACKAGE, "com.oculus.govrnux", "com.samsung.android.app.vrsetupwizard", "com.samsung.android.app.vrsetupwizardstub", "com.samsung.android.hmt.vrsvc", "com.samsung.android.hmt.vrsystem"));
    private static final boolean DEBUG = false;
    public static final int INVALID_PKG = -3;
    private static final String OCULUS_INSTALLER = (Build.TYPE.equals("user") ? "com.facebook.system" : "com.facebook.system.dev");
    public static final int OCULUS_STORE_APP = 1;
    public static final int SIDE_LOADED_NON_VR_APP = 3;
    public static final int SIDE_LOADED_VR_APP = 2;
    public static final int SYSTEM_APP = 0;
    private static final String TAG = OculusGoPackageTypeHelper.class.getSimpleName();
    public static final int WHITE_LISTED_NON_VR_APP = -2;
    private static final Set<String> WHITE_LISTED_PKGS = new HashSet(Arrays.asList("com.android.documentsui", "com.mobileiron.anyware.android", "com.airwatch.androidagent"));
    private ActivityInfo mActivityInfo;
    private final ApplicationInfo mApplicationInfo;
    private final boolean mInvalidPackage;
    private final PackageManager mPackageManager;
    private final String mTargetPackageName;

    public static int getPackageType(Context context, String targetPackageName) {
        return getPackageTypeAsUser(context, targetPackageName, UserHandle.getCallingUserId());
    }

    public static int getPackageTypeAsUser(Context context, String targetPackageName, int userId) {
        return new OculusGoPackageTypeHelper(context, targetPackageName, userId).getPackageType();
    }

    public static int getPackageTypeAsUser(Context context, ComponentName cn, int userId) {
        return new OculusGoPackageTypeHelper(context, cn, userId).getPackageType();
    }

    private OculusGoPackageTypeHelper(Context context, String targetPackageName, int userId) {
        this.mPackageManager = context.getPackageManager();
        if (!TextUtils.isEmpty(targetPackageName)) {
            this.mTargetPackageName = targetPackageName;
            ApplicationInfo applicationInfo = null;
            boolean invalidPkg = DEBUG;
            try {
                applicationInfo = this.mPackageManager.getApplicationInfoAsUser(this.mTargetPackageName, 128, userId);
            } catch (PackageManager.NameNotFoundException e) {
                String str = TAG;
                Log.e(str, "Exception while getting package " + this.mTargetPackageName + " info", e);
                invalidPkg = true;
            }
            this.mApplicationInfo = applicationInfo;
            this.mInvalidPackage = invalidPkg;
            return;
        }
        throw new NullPointerException("Target activity package name null");
    }

    private OculusGoPackageTypeHelper(Context context, ComponentName cn, int userId) {
        this(context, cn.getPackageName(), userId);
        this.mActivityInfo = getActivityInfo(cn, userId);
    }

    private int getPackageType() {
        if (this.mInvalidPackage) {
            return -3;
        }
        if (isWhiteListedNonVrApp()) {
            return -2;
        }
        if (isBlackListedPackage()) {
            return -1;
        }
        if (isSystemApp()) {
            return 0;
        }
        if (isOculusStoreApp()) {
            return 1;
        }
        if (isSideloadedVrApp()) {
            return 2;
        }
        return 3;
    }

    private boolean isWhiteListedNonVrApp() {
        return WHITE_LISTED_PKGS.contains(this.mTargetPackageName);
    }

    private boolean isBlackListedPackage() {
        return BLACK_LISTED_PKGS.contains(this.mTargetPackageName);
    }

    private boolean isSystemApp() {
        boolean systemApp = true;
        if ((this.mApplicationInfo.flags & 1) == 0) {
            systemApp = DEBUG;
        }
        return systemApp;
    }

    private boolean isOculusStoreApp() {
        String packageInstaller = this.mPackageManager.getInstallerPackageName(this.mTargetPackageName);
        if (TextUtils.isEmpty(packageInstaller) || !packageInstaller.equals(OCULUS_INSTALLER)) {
            return DEBUG;
        }
        return true;
    }

    private boolean isSideloadedVrApp() {
        boolean sideloadedVrApp = DEBUG;
        ActivityInfo activityInfo = this.mActivityInfo;
        boolean sideloadedVrApp2 = DEBUG;
        if (activityInfo != null) {
            sideloadedVrApp = activityInfo.oculusActivityType == 1;
        }
        ApplicationInfo applicationInfo = this.mApplicationInfo;
        if (applicationInfo == null || sideloadedVrApp) {
            return sideloadedVrApp;
        }
        Bundle bundle = applicationInfo.metaData;
        if (bundle != null && bundle.containsKey(Intent.METADATA_SAMSUNG_VR_MODE)) {
            sideloadedVrApp2 = true;
        }
        return sideloadedVrApp2;
    }

    private ActivityInfo getActivityInfo(ComponentName component, int userId) {
        IPackageManager pm = ActivityThread.getPackageManager();
        if (pm == null) {
            return null;
        }
        try {
            return pm.getActivityInfo(component, 0, userId);
        } catch (RemoteException e) {
            String str = TAG;
            Log.w(str, "Failed to call getActivityInfo for " + component, e);
            return null;
        }
    }
}
