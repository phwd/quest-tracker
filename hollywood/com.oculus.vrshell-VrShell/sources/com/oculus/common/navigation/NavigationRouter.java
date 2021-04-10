package com.oculus.common.navigation;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Trace;
import android.util.Log;
import com.oculus.common.build.BuildConstants;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.navigation.RssdkHelper;
import com.oculus.vrshell.ShellApplication;
import com.oculus.vrshell.SystemUXRoute;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import jp.co.omronsoft.iwnnime.ml.iwnn.iWnnEngine;

public final class NavigationRouter {
    private static final String ASSISTANT_LAUNCH_INTENT_ACTION = "com.oculus.assistant.LAUNCH";
    private static final String CONTINUE_ABUSE_REPORT_INTENT_ACTION = "com.oculus.vrshell.intent.action.CONTINUE_ABUSE_REPORT";
    private static final String INTENT_KEY_DATA = "intent_data";
    public static final String INTENT_KEY_FORWARDED_INTENT_ORIGINAL_ACTION = "forwarded_intent_original_action";
    public static final String INTENT_KEY_START_IN_MIN_OVERLAY = "start_in_min_overlay";
    private static final String SHELL_ENV_COMMAND_ACTION = "com.oculus.shellenv.SHELL_ENV_CMD";
    private static final String SHELL_LAUNCH_INTENT_ACTION = "com.oculus.vrshell.intent.action.LAUNCH";
    private static final String TAG = LoggingUtil.tag(NavigationRouter.class);
    private static Map<String, Set<String>> supportedReceiverActions;
    private static final EnumSet<SystemUXRoute> systemUXRoutesSupportedInOverlay = EnumSet.of(SystemUXRoute.APP_DOWNLOAD_FAILURE_LOW_STORAGE, SystemUXRoute.AUI_PARTIES, SystemUXRoute.BUG_REPORT, SystemUXRoute.DATE_TIME_SETTINGS, SystemUXRoute.DEFAULT_BROWSER, SystemUXRoute.EVENTS, SystemUXRoute.GAMING_ACTIVITY, SystemUXRoute.EXIT_TO_HOME, SystemUXRoute.FBCONNECT, SystemUXRoute.FRIENDS, SystemUXRoute.INVITE_FRIENDS, SystemUXRoute.KEYBOARD, SystemUXRoute.LAUNCH_CHECK_ENTITLEMENT_SHARING, SystemUXRoute.LAUNCH_IAP, SystemUXRoute.LIVESTREAMING, SystemUXRoute.LOCKPATTERN, SystemUXRoute.MEDIA_PREVIEW, SystemUXRoute.MESSAGES, SystemUXRoute.SOCIAL, SystemUXRoute.SETTINGS, SystemUXRoute.SHARE_MEDIA, SystemUXRoute.SOCIAL_JOIN_PARTY, SystemUXRoute.SOCIAL_OVERLAY_PANEL, SystemUXRoute.SOCIAL_RECEIVE_INVITE_DIALOG, SystemUXRoute.SOCIAL_REQUESTS, SystemUXRoute.STORAGE_MANAGER, SystemUXRoute.STORE, SystemUXRoute.USER_BLOCK, SystemUXRoute.USER_FRIEND_REQUEST, SystemUXRoute.USER_PROFILE, SystemUXRoute.USER_REPORT, SystemUXRoute.USER_UNBLOCK);
    private boolean mCurrentAppHasFocus = false;
    private final RssdkHelper mRssdkHelper;
    private final boolean mUseShellEnvNavigation;

    private static native void nativeInitNavigationContextServer(Context context);

    private static void cacheReceiverActions(Context context) {
        if (supportedReceiverActions == null) {
            supportedReceiverActions = new HashMap();
            PackageManager packageManager = context.getApplicationContext().getPackageManager();
            if (packageManager != null) {
                Intent intent = new Intent();
                intent.setPackage(context.getPackageName());
                for (ResolveInfo resolveInfo : packageManager.queryBroadcastReceivers(intent, 64)) {
                    HashSet hashSet = new HashSet();
                    resolveInfo.filter.actionsIterator().forEachRemaining(new Consumer(hashSet) {
                        /* class com.oculus.common.navigation.$$Lambda$NavigationRouter$KlSHy15JrW5THvMlkrCp6RsSBc4 */
                        private final /* synthetic */ Set f$0;

                        {
                            this.f$0 = r1;
                        }

                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            NavigationRouter.lambda$cacheReceiverActions$0(this.f$0, (String) obj);
                        }
                    });
                    supportedReceiverActions.put(resolveInfo.activityInfo.name, hashSet);
                }
            }
        }
    }

    public NavigationRouter(Context context, boolean z) {
        this.mRssdkHelper = new RssdkHelper(context);
        this.mUseShellEnvNavigation = z;
        NavigationRouterContentProvider.setNavigationRouter(this);
    }

    public NavigationRouter(RssdkHelper rssdkHelper, boolean z) {
        this.mRssdkHelper = rssdkHelper;
        this.mUseShellEnvNavigation = z;
    }

    public void startNavigationContextServer(Context context) {
        nativeInitNavigationContextServer(context);
    }

    public boolean isIntentForReceiver(Context context, Intent intent, BroadcastReceiver broadcastReceiver) {
        cacheReceiverActions(context);
        if (supportedReceiverActions.get(broadcastReceiver.getClass().getName()).contains(intent.getAction())) {
            return true;
        }
        Log.w(TAG, String.format("Unexpected action %s sent to receiver %s", intent.getAction(), broadcastReceiver.getClass().getName()));
        return false;
    }

    public boolean isLaunchAction(Intent intent) {
        String action = intent.getAction();
        return SHELL_LAUNCH_INTENT_ACTION.equals(action) || ASSISTANT_LAUNCH_INTENT_ACTION.equals(action) || CONTINUE_ABUSE_REPORT_INTENT_ACTION.equals(action);
    }

    public void submitAsLaunchIntent(Context context, Intent intent, boolean z) {
        Trace.beginSection("NavigationRouter.submitAsLaunchIntent");
        if (!isLaunchAction(intent)) {
            intent.setAction(SHELL_LAUNCH_INTENT_ACTION);
        }
        if (shouldLaunchAsOverlay(intent, z)) {
            startOverlayIntent(context, intent);
        } else if (!maybeSwitchToShellEnv(context, context.getPackageManager(), intent, false, z)) {
            intent.setComponent(new ComponentName("com.oculus.vrshell", BuildConstants.ACTIVITY_NAME_SHELL));
            intent.setFlags(iWnnEngine.WNNWORD_ATTRIBUTE_CORRECTION_WORD);
            context.startActivity(intent);
        }
        Trace.endSection();
    }

    public boolean needsUpgradeToLaunchIntent(long j) {
        PrimaryPackage primaryPackage = getPrimaryPackage();
        if (primaryPackage.isShellEnv()) {
            return false;
        }
        if (j == 0 || !primaryPackage.isShell()) {
            return true;
        }
        return false;
    }

    public void startOverlayIntent(Context context, Intent intent) {
        intent.setComponent(new ComponentName(context.getPackageName(), "com.oculus.shellenv".equals(intent.getStringExtra(ShellApplication.INTENT_KEY_FROM_PKG)) ? "com.oculus.vrshell.ShellEnvOverlayService" : "com.oculus.vrshell.ShellOverlayService"));
        context.startService(intent);
    }

    public boolean maybeSwitchToShellEnv(Context context, PackageManager packageManager, Intent intent, boolean z) {
        return maybeSwitchToShellEnv(context, packageManager, intent, z, false);
    }

    public boolean maybeSwitchToShellEnv(Context context, PackageManager packageManager, Intent intent, boolean z, boolean z2) {
        if (!this.mUseShellEnvNavigation || SHELL_ENV_COMMAND_ACTION.equals(intent.getAction())) {
            return false;
        }
        try {
            packageManager.getPackageInfo("com.oculus.shellenv", 1);
            Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage("com.oculus.shellenv");
            if (!getPrimaryPackage().isShellEnv()) {
                launchIntentForPackage.addFlags(iWnnEngine.WNNWORD_ATTRIBUTE_CORRECTION_WORD);
                launchIntentForPackage.addFlags(67108864);
            }
            if (intent != null) {
                launchIntentForPackage.setData(intent.getData());
                launchIntentForPackage.putExtras(intent);
                launchIntentForPackage.putExtra(INTENT_KEY_FORWARDED_INTENT_ORIGINAL_ACTION, intent.getAction());
            }
            if (!z && isSystemUxRouteFocusAware(extractSystemUXRouteFromIntent(intent), z2)) {
                launchIntentForPackage.putExtra(INTENT_KEY_START_IN_MIN_OVERLAY, true);
            }
            try {
                context.startActivity(launchIntentForPackage);
                return true;
            } catch (ActivityNotFoundException e) {
                Log.wtf(TAG, "Launching into ShellEnv was attempted and failed, even though it passed the initial PackageManager check.", e);
                return false;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            Log.e(TAG, "ShellEnv is not installed on this device; fallback to using VrShell as the main activity", e2);
            return false;
        }
    }

    public boolean shouldLaunchAsOverlay(Intent intent, boolean z) {
        Log.d(TAG, "shouldLaunchAsOverlay");
        PrimaryPackage primaryPackage = getPrimaryPackage();
        if (primaryPackage.isShell()) {
            Log.d(TAG, "shouldLaunchAsOverlay == false (shell foreground)");
            return false;
        }
        SystemUXRoute extractSystemUXRouteFromIntent = extractSystemUXRouteFromIntent(intent);
        if (!primaryPackage.isFocusAware() || !isSystemUxRouteFocusAware(extractSystemUXRouteFromIntent, z)) {
            Log.d(TAG, "shouldLaunchAsOverlay == false");
            return false;
        }
        Log.d(TAG, "shouldLaunchAsOverlay == true (focus aware and valid route");
        return true;
    }

    private SystemUXRoute extractSystemUXRouteFromIntent(Intent intent) {
        String extractSystemUXRouteStringFromIntent = extractSystemUXRouteStringFromIntent(intent);
        String str = TAG;
        Log.d(str, "systemUxRouteString = " + extractSystemUXRouteStringFromIntent);
        SystemUXRoute fromPath = SystemUXRoute.fromPath(extractSystemUXRouteStringFromIntent);
        if (fromPath != null) {
            String str2 = TAG;
            Log.d(str2, "systemUXRoute = " + fromPath.toString());
        } else {
            Log.d(TAG, "Invalid SystemUxRoute");
        }
        return fromPath;
    }

    private boolean isSystemUxRouteFocusAware(SystemUXRoute systemUXRoute, boolean z) {
        if (systemUXRoute == null || !systemUXRoutesSupportedInOverlay.contains(systemUXRoute)) {
            return false;
        }
        if (systemUXRoute != SystemUXRoute.LAUNCH_IAP || !z) {
            return true;
        }
        return false;
    }

    private String extractSystemUXRouteStringFromIntent(Intent intent) {
        Object obj;
        Bundle extras = intent.getExtras();
        if (extras == null || (obj = extras.get(INTENT_KEY_DATA)) == null) {
            return null;
        }
        String valueOf = String.valueOf(obj);
        String str = TAG;
        Log.d(str, "Data URI is " + valueOf);
        return valueOf;
    }

    public PrimaryPackage getPrimaryPackage() {
        RssdkHelper.CurrentPackageInfo currentPackageInfo = this.mRssdkHelper.getCurrentPackageInfo();
        return new PrimaryPackage(currentPackageInfo.mPackageName, currentPackageInfo.mIsFocusAware);
    }

    public void setCurrentAppFocus(boolean z) {
        this.mCurrentAppHasFocus = z;
    }

    public boolean hasCurrentAppFocus() {
        return this.mCurrentAppHasFocus;
    }
}
