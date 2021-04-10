package com.oculus.cloudstoragehelper;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.oculus.ipc.client.CallerIdentityHandshakeClient;
import com.oculus.ipc.common.ParcelableCallbackReceiver;

public class CloudStorageHelper {
    public static final String EXTRA_KEY_APP_ID = "app_id";
    public static final String EXTRA_KEY_CALLBACK_RECEIVER = "callback_receiver";
    public static final String EXTRA_KEY_CONFLICT_RESOLUTION = "conflict_resolution";
    public static final String EXTRA_KEY_WORK_TYPE = "work_type";
    private static final String TAG = "CloudStorageHelper";
    private static final String TARGET_INTENT_SERVICE_CLASS = "com.oculus.horizon.cloudstorage2.CloudStorageIntentService";
    private static final String TARGET_SERVICE_PACKAGE = "com.oculus.horizon";

    public enum JobType {
        UPLOAD,
        DOWNLOAD
    }

    public enum ResolutionType {
        USE_LOCAL,
        USE_REMOTE
    }

    public enum RunType {
        LAUNCH_SYNC,
        RESOLVE_CONFLICT
    }

    public static void runLaunchSync(Context context, String str, CloudStorageCallback cloudStorageCallback) {
        Log.d(TAG, "Start service for a launch sync.");
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_KEY_WORK_TYPE, RunType.LAUNCH_SYNC.name());
        bundle.putString("app_id", str);
        bundle.putParcelable(EXTRA_KEY_CALLBACK_RECEIVER, new ParcelableCallbackReceiver(cloudStorageCallback).getReceiverForIPC());
        run(context, bundle);
    }

    public static void runConflictResolve(Context context, String str, ResolutionType resolutionType, CloudStorageCallback cloudStorageCallback) {
        Log.d(TAG, "Start service for conflict resolution.");
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_KEY_WORK_TYPE, RunType.RESOLVE_CONFLICT.name());
        bundle.putString("app_id", str);
        bundle.putString(EXTRA_KEY_CONFLICT_RESOLUTION, resolutionType.name());
        bundle.putParcelable(EXTRA_KEY_CALLBACK_RECEIVER, new ParcelableCallbackReceiver(cloudStorageCallback).getReceiverForIPC());
        run(context, bundle);
    }

    private static void run(Context context, Bundle bundle) {
        try {
            CallerIdentityHandshakeClient.addToBundle(bundle, context.getPackageManager().getApplicationInfo("com.oculus.horizon", 0).uid);
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.oculus.horizon", TARGET_INTENT_SERVICE_CLASS));
            intent.putExtras(bundle);
            context.startService(intent);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Failed to lookup id for target service.", e);
        }
    }
}
