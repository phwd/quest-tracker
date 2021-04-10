package com.oculus.vrshell.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.oculus.appmanager.info.model.InstallerResult;
import com.oculus.appmanager.info.model.InstallerResultError;
import com.oculus.appmanager.info.model.RequestOrigin;
import com.oculus.library.model.InstallerCallback;
import com.oculus.modules.LibraryModuleImpl;
import com.oculus.secure.trustedapp.AllFamilyPackageNames;
import com.oculus.secure.trustedapp.AllFamilyTrustedSignatures;
import com.oculus.secure.trustedapp.CallerInfoHelper;
import com.oculus.secure.trustedapp.TrustedApp;
import com.oculus.secure.trustedapp.TrustedAppHelper;
import com.oculus.secure.unlockulus.UnlockulusHelper;
import java.util.Arrays;
import java.util.HashSet;

public class DownloadActionReceiver extends BroadcastReceiver {
    private static final String CANCEL_ACTION = "cancel_download";
    private static final String PACKAGE_NAME_STRING = "package_name";
    private static final String RETRY_ACTION = "retry_download";
    private static final String SHELL_LAUNCH_ACTION = "com.oculus.vrshell.intent.action.LAUNCH";
    private static final String SHELL_LAUNCH_DATA = "intent_data";
    private static final String TAG = DownloadActionReceiver.class.getSimpleName();
    private static final TrustedApp mHorizonTrustedApp = TrustedAppHelper.createTrustedApp(AllFamilyTrustedSignatures.OCULUS_PROD, new HashSet(Arrays.asList("com.oculus.horizon", "com.oculus.ocms")));
    private LibraryModuleImpl mLibraryModule;

    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive");
        if (this.mLibraryModule == null) {
            this.mLibraryModule = new LibraryModuleImpl(context);
        }
        try {
            onHandleIntentWithThrows(context, intent);
        } catch (Exception e) {
            Log.e(TAG, "Error handling intent for onReceive.", e);
        }
    }

    private void onHandleIntentWithThrows(final Context context, Intent intent) {
        throwIfNotAllowed(context, intent);
        String packageName = intent.getStringExtra("package_name");
        String action = intent.getAction();
        if (action.equals(CANCEL_ACTION)) {
            Log.i(TAG, "Cancelling download for package: " + packageName + ".");
            this.mLibraryModule.cancelDownloadImpl(packageName);
        } else if (action.equals(RETRY_ACTION)) {
            Log.i(TAG, "Downloading and installing package: " + packageName + ".");
            this.mLibraryModule.downloadAndInstall(packageName, RequestOrigin.NOTIFICATION, new InstallerCallback() {
                /* class com.oculus.vrshell.home.DownloadActionReceiver.AnonymousClass1 */

                @Override // com.oculus.library.model.InstallerCallback
                public void onInstallerResult(InstallerResult result) {
                    if (!result.isSuccess()) {
                        switch (AnonymousClass2.$SwitchMap$com$oculus$appmanager$info$model$InstallerResultError[result.error.ordinal()]) {
                            case 1:
                                Intent lowStroageIntent = new Intent(DownloadActionReceiver.SHELL_LAUNCH_ACTION);
                                lowStroageIntent.putExtra(DownloadActionReceiver.SHELL_LAUNCH_DATA, "systemux://dialog/app-download-failure-low-storage");
                                context.sendBroadcast(lowStroageIntent);
                                return;
                            default:
                                Log.w(DownloadActionReceiver.TAG, "Unhandled download error " + result.error.name());
                                return;
                        }
                    }
                }
            });
        } else {
            throw new IllegalArgumentException("Unsupported intent action: " + action + ".");
        }
    }

    /* renamed from: com.oculus.vrshell.home.DownloadActionReceiver$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$appmanager$info$model$InstallerResultError = new int[InstallerResultError.values().length];

        static {
            try {
                $SwitchMap$com$oculus$appmanager$info$model$InstallerResultError[InstallerResultError.LOW_STORAGE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    private void throwIfNotAllowed(Context context, Intent intent) {
        boolean isIntentFromHorizonOrOCMS = false;
        CallerInfoHelper.CallerInfo callerInfo = CallerInfoHelper.getCallerInfo(intent, false);
        String callerPackageName = callerInfo.callerPackageName;
        if (!callerInfo.isValid || callerPackageName == null) {
            throw new SecurityException("Calling package could not be identified.");
        }
        Log.v(TAG, "Checking package signature for package: " + callerPackageName);
        if (mHorizonTrustedApp.checkTrustedApp(callerInfo.callerUid, context)) {
            Log.v(TAG, "Allowing caller intent because it is release signed.");
            return;
        }
        if ("com.oculus.ocms".equals(callerPackageName) || "com.oculus.horizon".equals(callerPackageName) || AllFamilyPackageNames.OCULUS_HORIZON_DEV.equals(callerPackageName)) {
            isIntentFromHorizonOrOCMS = true;
        }
        boolean isIntentFromDebugPackage = TrustedApp.checkDebugSignatureFromPackageName(context, callerPackageName);
        if (isIntentFromHorizonOrOCMS && isIntentFromDebugPackage && 0 != 0) {
            Log.v(TAG, "Allowing caller intent because it and this package are debug.");
        } else if (UnlockulusHelper.isEmployeeWithWhitelistedDevice(context)) {
            Log.v(TAG, "Allowing caller intent because device passes unlockulus.");
        } else {
            throw new SecurityException("Calling package is not release signed and this package is not debug.");
        }
    }
}
