package com.oculus.appsafety;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import com.oculus.license.SecurityPrincipal;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import oculus.internal.AuthManager2;
import oculus.internal.BuildCompat;

public final class AppSafetyService extends Service {
    public static final String EXTRA_VERIFICATION_RESULT_RECEIVER = "com.oculus.appsafety.VERIFICATION_RESULT_RECEIVER";
    public static final String PACKAGE_VERIFIED = "package_verified";
    public static final int RESULT_FAIL = 1;
    public static final int RESULT_SUCCESS = 0;
    private static final String TAG = "AppSafetyService";
    public static final String VERIFY_PACKAGE = "com.oculus.appsafety.VERIFY_PACKAGE";
    private Handler mHandler;
    private HandlerThread mThread;

    public void onCreate() {
        Log.d(TAG, "Service starting");
        this.mThread = new HandlerThread("AppSafetyService thread", 10);
        this.mThread.start();
        super.onCreate();
        this.mHandler = new Handler(this.mThread.getLooper());
    }

    public void onDestroy() {
        super.onDestroy();
        HandlerThread handlerThread = this.mThread;
        if (handlerThread != null) {
            handlerThread.quit();
        }
        Log.d(TAG, "Service stopping");
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        if (intent == null) {
            Log.e(TAG, "onStartCommand called with null intent");
            return 2;
        } else if (intent.getAction() == null) {
            Log.d(TAG, "service started with no action");
            return 2;
        } else if (intent.getAction().equals(VERIFY_PACKAGE)) {
            Bundle extras = intent.getExtras();
            ResultReceiver resultReceiver = null;
            String installerPackage = null;
            int installFlags = intent.getIntExtra("android.content.pm.extra.VERIFICATION_INSTALL_FLAGS", 0);
            if (extras != null) {
                resultReceiver = (ResultReceiver) extras.getParcelable(EXTRA_VERIFICATION_RESULT_RECEIVER);
                installerPackage = extras.getString("android.content.pm.extra.VERIFICATION_INSTALLER_PACKAGE", "");
            }
            this.mHandler.post(new ExceptionLoggingFutureTask(new VerifyPackage(this, intent.getData(), intent.getType(), resultReceiver, installerPackage, installFlags, new PackageVerifier(new Handler(Looper.myLooper()))), TAG));
            return 2;
        } else {
            Log.e(TAG, "onStartCommand called with unsupported action: " + intent.getAction());
            return 2;
        }
    }

    private static class VerifyPackage implements Callable<Void> {
        final Uri mApkUri;
        final Context mContext;
        final String mDataType;
        final int mInstallFlags;
        final String mInstallerPackage;
        final PackageVerifier mPackageVerifier;
        final ResultReceiver mResultReceiver;

        public VerifyPackage(Context context, Uri apkUri, String dataType, ResultReceiver resultReceiver, String installerPackage, int installFlags, PackageVerifier packageVerifier) {
            this.mContext = context;
            this.mApkUri = apkUri;
            this.mDataType = dataType;
            this.mResultReceiver = resultReceiver;
            this.mInstallerPackage = installerPackage;
            this.mInstallFlags = installFlags;
            this.mPackageVerifier = packageVerifier;
        }

        @Override // java.util.concurrent.Callable
        public Void call() {
            Set<String> identifiers = new HashSet<>();
            AuthManager2.Credentials credentials = AuthManager2.getInstance(this.mContext).getCredentialsBlocking(false);
            if (credentials != null && !TextUtils.isEmpty(credentials.getUserId())) {
                identifiers.add(credentials.getUserId());
            }
            identifiers.add(new BuildCompat().getSerial());
            boolean verified = this.mPackageVerifier.verifyPackage(this.mContext, this.mApkUri, this.mDataType, this.mInstallerPackage, SecurityPrincipal.compute(identifiers), this.mInstallFlags);
            Bundle result = new Bundle();
            result.putBoolean(AppSafetyService.PACKAGE_VERIFIED, verified);
            ResultReceiver resultReceiver = this.mResultReceiver;
            if (resultReceiver == null) {
                return null;
            }
            resultReceiver.send(0, result);
            return null;
        }
    }
}
