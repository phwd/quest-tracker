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
    public static final String TAG = LoggingUtil.tag(HorizonUtil.class);
    public static OVRLibrary ovrLibrary;

    public interface RefetchHandler {
        void onCompleted(boolean z);
    }

    public static void cancelDownloadPackage(Context context, String str) {
        synchronized (HorizonUtil.class) {
            if (ovrLibrary == null) {
                ovrLibrary = new OVRLibrary(context);
            }
        }
        ovrLibrary.cancelDownload(str, null);
    }

    public static void downloadAndInstallPackage(Context context, String str, InstallerCallback installerCallback) {
        synchronized (HorizonUtil.class) {
            if (ovrLibrary == null) {
                ovrLibrary = new OVRLibrary(context);
            }
        }
        ovrLibrary.downloadAndInstall(str, RequestOrigin.LIBRARY, installerCallback);
    }

    public static App getApplication(Context context, String str) {
        synchronized (HorizonUtil.class) {
            if (ovrLibrary == null) {
                ovrLibrary = new OVRLibrary(context);
            }
        }
        return ovrLibrary.getApp(str);
    }

    public static List<App> getApplications(Context context) {
        synchronized (HorizonUtil.class) {
            if (ovrLibrary == null) {
                ovrLibrary = new OVRLibrary(context);
            }
        }
        return Arrays.asList(ovrLibrary.getAllApps());
    }

    public static void refetch(Context context, @Nullable final RefetchHandler refetchHandler) {
        synchronized (HorizonUtil.class) {
            if (ovrLibrary == null) {
                ovrLibrary = new OVRLibrary(context);
            }
        }
        ovrLibrary.refetch(new ResultReceiver(null) {
            /* class com.oculus.vrshell.util.HorizonUtil.AnonymousClass1 */

            public void onReceiveResult(int i, Bundle bundle) {
                boolean z = false;
                if (i == 0) {
                    z = true;
                }
                RefetchHandler refetchHandler = refetchHandler;
                if (refetchHandler != null) {
                    refetchHandler.onCompleted(z);
                }
            }
        });
    }

    public static void registerAppsChangeListener(Context context, OVRLibrary.OnChangeListener onChangeListener) {
        synchronized (HorizonUtil.class) {
            if (ovrLibrary == null) {
                ovrLibrary = new OVRLibrary(context);
            }
        }
        ovrLibrary.registerAppsChangeListener(onChangeListener);
    }

    public static void uninstallPackage(Context context, String str) {
        synchronized (HorizonUtil.class) {
            if (ovrLibrary == null) {
                ovrLibrary = new OVRLibrary(context);
            }
        }
        ovrLibrary.uninstall(str, RequestOrigin.APP_LAUNCH_PROMPT, null);
    }

    public static void unregisterAppsChangeListener(Context context, OVRLibrary.OnChangeListener onChangeListener) {
        synchronized (HorizonUtil.class) {
            if (ovrLibrary == null) {
                ovrLibrary = new OVRLibrary(context);
            }
        }
        ovrLibrary.unregisterAppChangeListener(onChangeListener);
    }

    public static String getApplicationId(Context context, String str) {
        App application = getApplication(context, str);
        if (application != null) {
            return application.id;
        }
        return "";
    }

    public static String getApplicationName(Context context, String str) {
        App application = getApplication(context, str);
        if (application != null) {
            return application.displayName;
        }
        return "";
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

    public static void markIsSeen(Context context, String str) {
        if (getApplication(context, str) != null) {
            OVRLibrary oVRLibrary = ovrLibrary;
            App.Editor editor = new App.Editor(str);
            editor.withIsUnseen(false);
            oVRLibrary.save(editor);
        }
    }

    public static void overrideRecentActivty(Context context, String str, long j) {
        if (getApplication(context, str) != null) {
            OVRLibrary oVRLibrary = ovrLibrary;
            App.Editor editor = new App.Editor(str);
            editor.withRecentActivityMs(j);
            oVRLibrary.save(editor);
        }
    }
}
