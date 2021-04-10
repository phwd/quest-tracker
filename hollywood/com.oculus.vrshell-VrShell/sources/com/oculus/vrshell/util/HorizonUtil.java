package com.oculus.vrshell.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import com.oculus.appmanager.info.model.InstallerResult;
import com.oculus.appmanager.info.model.RequestOrigin;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.packageutils.PackageHelpers;
import com.oculus.library.model.App;
import com.oculus.library.model.InstallerCallback;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.util.HashSet;
import java.util.Set;

public final class HorizonUtil {
    private static final long PACKAGE_UNINSTALL_CHECK_TIMEOUT_MS = 10000;
    private static final long PACKAGE_UNINSTALL_THREAD_SLEEP_MS = 200;
    private static final String REQUESTED_FEATURE_HAND_TRACKING = "oculus.software.handtracking";
    private static final String TAG = LoggingUtil.tag(HorizonUtil.class);
    private static OVRLibrary ovrLibrary;

    private HorizonUtil() {
    }

    public static App getApplication(Context context, String str) {
        synchronized (HorizonUtil.class) {
            if (ovrLibrary == null) {
                ovrLibrary = new OVRLibrary(context);
            }
        }
        return ovrLibrary.getApp(str);
    }

    public static String getApplicationName(Context context, String str) {
        App application = getApplication(context, str);
        return application != null ? application.displayName : "";
    }

    public static String getSafeApplicationName(Context context, String str) {
        String applicationName = getApplicationName(context, str);
        if (!applicationName.isEmpty()) {
            return applicationName;
        }
        try {
            return (String) context.getPackageManager().getApplicationLabel(context.getPackageManager().getApplicationInfo(str, 0));
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(TAG, e.getMessage());
            return "Unknown";
        }
    }

    public static String getApplicationId(Context context, String str) {
        App application = getApplication(context, str);
        return application != null ? application.id : "";
    }

    public static void uninstallPackage(Context context, String str) {
        synchronized (HorizonUtil.class) {
            if (ovrLibrary == null) {
                ovrLibrary = new OVRLibrary(context);
            }
        }
        ovrLibrary.uninstall(str, RequestOrigin.APP_LAUNCH_PROMPT, null);
    }

    public static void updatePackage(Context context, String str, InstallerCallback installerCallback) {
        synchronized (HorizonUtil.class) {
            if (ovrLibrary == null) {
                ovrLibrary = new OVRLibrary(context);
            }
        }
        ovrLibrary.downloadAndInstall(str, RequestOrigin.APP_LAUNCH_PROMPT, installerCallback);
    }

    public static void restoreOfficialPackage(final Context context, final String str) {
        synchronized (HorizonUtil.class) {
            if (ovrLibrary == null) {
                ovrLibrary = new OVRLibrary(context);
            }
        }
        final UnifiedTelemetryLogger instance = UnifiedTelemetryLogger.getInstance(context);
        final AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_unofficial_binary_check");
        analyticsEvent.setExtra("package_name", str);
        analyticsEvent.setExtra("event_subtype", "restore_app_start");
        instance.reportEvent(analyticsEvent, false);
        ovrLibrary.uninstall(str, RequestOrigin.UNOFFICIAL_APP_PROMPT, new InstallerCallback() {
            /* class com.oculus.vrshell.util.HorizonUtil.AnonymousClass1 */

            @Override // com.oculus.library.model.InstallerCallback
            public void onInstallerResult(InstallerResult installerResult) {
                if (installerResult == null) {
                    String str = HorizonUtil.TAG;
                    Log.e(str, "Restoring " + str + " failed because uninstall was unsuccessful");
                    analyticsEvent.setExtra("event_subtype", "restore_app_uninstalled_failed_no_result");
                    instance.reportEvent(analyticsEvent, false);
                } else if (!installerResult.isSuccess()) {
                    String str2 = HorizonUtil.TAG;
                    Log.e(str2, "Restoring " + str + " failed because uninstall was unsuccessful: " + installerResult.error.name());
                    AnalyticsEvent analyticsEvent = analyticsEvent;
                    StringBuilder sb = new StringBuilder();
                    sb.append("restore_app_uninstalled_failed_result_");
                    sb.append(installerResult.error.name());
                    analyticsEvent.setExtra("event_subtype", sb.toString());
                    instance.reportEvent(analyticsEvent, false);
                } else {
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    while (SystemClock.elapsedRealtime() - elapsedRealtime <= HorizonUtil.PACKAGE_UNINSTALL_CHECK_TIMEOUT_MS && HorizonUtil.isPackageInstalled(context, str)) {
                        try {
                            String str3 = HorizonUtil.TAG;
                            Log.d(str3, "PackageManager detects " + str + " is still installed...");
                            Thread.sleep(HorizonUtil.PACKAGE_UNINSTALL_THREAD_SLEEP_MS);
                        } catch (InterruptedException unused) {
                        }
                    }
                    if (HorizonUtil.isPackageInstalled(context, str)) {
                        String str4 = HorizonUtil.TAG;
                        Log.d(str4, "Package restore uninstall status timed out for: " + str);
                        analyticsEvent.setExtra("event_subtype", "restore_app_fail_uninstall_timeout");
                        instance.reportEvent(analyticsEvent, false);
                        return;
                    }
                    analyticsEvent.setExtra("event_subtype", "restore_app_download_start");
                    analyticsEvent.setExtra("uninstall_check_duration_ms", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                    instance.reportEvent(analyticsEvent, false);
                    HorizonUtil.ovrLibrary.downloadAndInstall(str, RequestOrigin.UNOFFICIAL_APP_PROMPT, new InstallerCallback() {
                        /* class com.oculus.vrshell.util.HorizonUtil.AnonymousClass1.AnonymousClass1 */

                        @Override // com.oculus.library.model.InstallerCallback
                        public void onInstallerResult(InstallerResult installerResult) {
                            if (installerResult == null) {
                                String str = HorizonUtil.TAG;
                                Log.e(str, "Restoring " + str + " failed because download and install was unsuccessful");
                                analyticsEvent.setExtra("event_subtype", "restore_app_dl_install_failed_no_result");
                                instance.reportEvent(analyticsEvent, false);
                            } else if (!installerResult.isSuccess()) {
                                String str2 = HorizonUtil.TAG;
                                Log.e(str2, "Restoring " + str + " failed because download and install was unsuccessful: " + installerResult.error.name());
                                AnalyticsEvent analyticsEvent = analyticsEvent;
                                StringBuilder sb = new StringBuilder();
                                sb.append("restore_app_dl_install_failed_result_");
                                sb.append(installerResult.error.name());
                                analyticsEvent.setExtra("event_subtype", sb.toString());
                                instance.reportEvent(analyticsEvent, false);
                            } else {
                                String str3 = HorizonUtil.TAG;
                                Log.d(str3, "Restoring " + str + " was successful");
                                analyticsEvent.setExtra("event_subtype", "restore_app_success");
                                instance.reportEvent(analyticsEvent, false);
                            }
                        }
                    });
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public static boolean isPackageInstalled(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean hasUpdate(Context context, String str) {
        App application = getApplication(context, str);
        return application != null && application.hasUpdate();
    }

    public static void markIsSeen(Context context, String str) {
        App application = getApplication(context, str);
        if (application != null && application.isUnseen) {
            ovrLibrary.save(new App.Editor(str).withIsUnseen(false));
        }
    }

    public static void markRecentActivity(Context context, String str) {
        if (getApplication(context, str) != null) {
            ovrLibrary.save(new App.Editor(str).withRecentActivityMs(System.currentTimeMillis()));
        }
    }

    /* access modifiers changed from: private */
    public enum InputMethod {
        TOUCH(1),
        HANDS(2);
        
        private int mFlag;

        private InputMethod(int i) {
            this.mFlag = i;
        }

        public int getFlag() {
            return this.mFlag;
        }
    }

    public static int getSupportedInputMethodsBitField(Context context, String str) {
        int i = 0;
        for (InputMethod inputMethod : getSupportedInputMethods(context, str)) {
            i |= inputMethod.getFlag();
        }
        return i;
    }

    private static Set<InputMethod> getSupportedInputMethods(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        try {
            HashSet hashSet = new HashSet();
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 16384);
            hashSet.add(InputMethod.TOUCH);
            if (isPackageAPanelApp(str, packageManager)) {
                hashSet.add(InputMethod.HANDS);
            } else if (packageInfo.reqFeatures != null) {
                FeatureInfo[] featureInfoArr = packageInfo.reqFeatures;
                int length = featureInfoArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    FeatureInfo featureInfo = featureInfoArr[i];
                    if (REQUESTED_FEATURE_HAND_TRACKING.equals(featureInfo.name)) {
                        if ((featureInfo.flags & 1) != 0) {
                            hashSet.remove(InputMethod.TOUCH);
                        }
                        hashSet.add(InputMethod.HANDS);
                    } else {
                        i++;
                    }
                }
            }
            return hashSet;
        } catch (PackageManager.NameNotFoundException unused) {
            HashSet hashSet2 = new HashSet();
            hashSet2.add(InputMethod.TOUCH);
            return hashSet2;
        }
    }

    private static boolean isPackageAPanelApp(String str, PackageManager packageManager) {
        Bundle bundle;
        for (ResolveInfo resolveInfo : packageManager.queryIntentServices(new Intent(PackageHelpers.INTENT_ACTION_PANEL).setPackage(str), 128)) {
            if (!(resolveInfo.serviceInfo == null || (bundle = resolveInfo.serviceInfo.metaData) == null || bundle.containsKey("com.oculus.appshell.is_default_service"))) {
                return true;
            }
        }
        return false;
    }
}
