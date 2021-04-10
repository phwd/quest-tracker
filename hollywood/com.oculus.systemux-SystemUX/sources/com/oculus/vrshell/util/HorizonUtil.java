package com.oculus.vrshell.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import androidx.annotation.Nullable;
import com.oculus.appmanager.info.model.RequestOrigin;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.library.model.App;
import com.oculus.library.model.AppStatus;
import com.oculus.library.model.InstallerCallback;
import com.oculus.libraryapi.OVRLibrary;
import java.util.Arrays;
import java.util.List;

public final class HorizonUtil {
    private static final String TAG = LoggingUtil.tag(HorizonUtil.class);
    public static OVRLibrary ovrLibrary;

    public interface RefetchHandler {
        void onCompleted(boolean z);
    }

    public static List<App> getApplications(Context context) {
        synchronized (HorizonUtil.class) {
            if (ovrLibrary == null) {
                ovrLibrary = new OVRLibrary(context);
            }
        }
        return Arrays.asList(ovrLibrary.getAllApps());
    }

    public static boolean isApplicationInstalled(Context context, String str) {
        for (App app : getApplications(context)) {
            if (str.equals(app.id)) {
                return AppStatus.INSTALLED.equals(app.status);
            }
        }
        return false;
    }

    public static boolean isApplicationInstalledByPackageName(Context context, String str) {
        App application = getApplication(context, str);
        if (application == null) {
            return false;
        }
        return AppStatus.INSTALLED.equals(application.status);
    }

    public static App getApplication(Context context, String str) {
        synchronized (HorizonUtil.class) {
            if (ovrLibrary == null) {
                ovrLibrary = new OVRLibrary(context);
            }
        }
        return ovrLibrary.getApp(str);
    }

    private static String getApplicationName(Context context, String str) {
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

    public static void downloadAndInstallPackage(Context context, String str, InstallerCallback installerCallback) {
        synchronized (HorizonUtil.class) {
            if (ovrLibrary == null) {
                ovrLibrary = new OVRLibrary(context);
            }
        }
        ovrLibrary.downloadAndInstall(str, RequestOrigin.LIBRARY, installerCallback);
    }

    public static void cancelDownloadPackage(Context context, String str) {
        synchronized (HorizonUtil.class) {
            if (ovrLibrary == null) {
                ovrLibrary = new OVRLibrary(context);
            }
        }
        ovrLibrary.cancelDownload(str, null);
    }

    public static void registerAppsChangeListener(Context context, OVRLibrary.OnChangeListener onChangeListener) {
        synchronized (HorizonUtil.class) {
            if (ovrLibrary == null) {
                ovrLibrary = new OVRLibrary(context);
            }
        }
        ovrLibrary.registerAppsChangeListener(onChangeListener);
    }

    public static void unregisterAppsChangeListener(Context context, OVRLibrary.OnChangeListener onChangeListener) {
        synchronized (HorizonUtil.class) {
            if (ovrLibrary == null) {
                ovrLibrary = new OVRLibrary(context);
            }
        }
        ovrLibrary.unregisterAppChangeListener(onChangeListener);
    }

    public static void markIsSeen(Context context, String str) {
        if (getApplication(context, str) != null) {
            ovrLibrary.save(new App.Editor(str).withIsUnseen(false));
        }
    }

    public static void overrideRecentActivty(Context context, String str, long j) {
        if (getApplication(context, str) != null) {
            ovrLibrary.save(new App.Editor(str).withRecentActivityMs(j));
        }
    }

    public static void refetch(Context context, @Nullable final RefetchHandler refetchHandler) {
        synchronized (HorizonUtil.class) {
            if (ovrLibrary == null) {
                ovrLibrary = new OVRLibrary(context);
            }
        }
        Log.d(TAG, "Start OVRLibrary Refetch");
        ovrLibrary.refetch(new ResultReceiver(null) {
            /* class com.oculus.vrshell.util.HorizonUtil.AnonymousClass1 */

            /* access modifiers changed from: protected */
            public void onReceiveResult(int i, Bundle bundle) {
                boolean z = i == 0;
                String str = HorizonUtil.TAG;
                Log.d(str, "Completed OVRLibrary Refetch. Success? " + z);
                RefetchHandler refetchHandler = refetchHandler;
                if (refetchHandler != null) {
                    refetchHandler.onCompleted(z);
                }
            }
        });
    }
}
