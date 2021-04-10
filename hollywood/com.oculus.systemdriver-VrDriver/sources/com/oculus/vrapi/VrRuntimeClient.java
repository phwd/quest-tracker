package com.oculus.vrapi;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.util.Log;
import com.oculus.os.ContextUtils;
import com.oculus.os.DialogContext;
import com.oculus.os.WindowTokenContext;
import com.oculus.vrruntimeservice.IRuntimeClient;
import com.oculus.vrruntimeservice.IVrRuntimeService;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

class VrRuntimeClient {
    private static final int FEATURE_MISSING = 0;
    private static final int FEATURE_PRESENT = 2;
    private static final int FEATURE_REQUIRED = 1;
    public static final String INTENT_KEY_DATA = "intent_data";
    public static final String INTENT_KEY_FROM_PKG = "intent_pkg";
    private static final int MODE_RETURN_3DOF_PLATFORM = 3;
    private static final int MODE_RETURN_ALLOW_6DOF = 1;
    private static final int MODE_RETURN_REQUIRES_3DOF = 0;
    private static final int MODE_RETURN_REQUIRES_6DOF = 2;
    private static final String PLATFORM_TYPE_3DOF = "ANDROID";
    private static String ProcessName = null;
    private static final String REMOTE_RESOURCE_PKG_NAME = "com.oculus.systemdriver";
    private static final int SECURE_TRACKING_AUDIT = 2;
    private static final int SECURE_TRACKING_NONE = 0;
    private static final int SECURE_TRACKING_SECURE = 1;
    private static final String SHELL_KEYBOARD_CLOSE_CMD = "keyboard_close";
    private static final String SHELL_KEYBOARD_SHOW_DATA = "systemux://keyboard";
    private static final String SHELL_LAUNCH_ACTION = "com.oculus.vrshell.intent.action.LAUNCH";
    private static final String SHELL_OVERLAY_ACTION = "com.oculus.vrshell.intent.action.OVERLAY";
    private static final String SHELL_OVERLAY_COMMAND = "com.oculus.vrshell.OVERLAY_COMMAND";
    private static final String TAG = "VrRuntimeClient";
    private static final String TRACKING_MODE_ALLOW_6DOF = "ALLOW_6DOF";
    private static final String TRACKING_MODE_REQUIRES_3DOF = "REQUIRE_3DOF";
    private static final String TRACKING_MODE_REQUIRES_6DOF = "REQUIRE_6DOF";
    private static final String TRACKING_MODE_UNKNOWN = "UNKNOWN";
    private static final String VRSHELL_BROADCAST_RECEIVER = "com.oculus.vrshell.ShellControlBroadcastReceiver";
    private static final String VRSHELL_PKG_NAME = "com.oculus.vrshell";
    private static WeakReference<Context> cachedContext = null;
    private static int clientFlags = 0;
    private static int clientSessionPlacement = 0;
    private static IBinder clientToken = new Binder();
    private static int clientType = 0;
    private static Handler handler = null;
    private static HandlerThread handlerThread = null;
    private static LinkDeathHandler linkDeathHandler = new LinkDeathHandler();
    private static RuntimeServiceConnection serviceConnection = null;
    private static IRuntimeClient vrRuntimeClient = null;
    private static IVrRuntimeService vrRuntimeService = null;

    /* access modifiers changed from: private */
    public static native void nativeInstanceIsLost();

    /* access modifiers changed from: private */
    public static native void nativeRuntimeServiceConnected();

    private static native void nativeRuntimeServiceDisconnected();

    VrRuntimeClient() {
    }

    /* access modifiers changed from: private */
    public static class LinkDeathHandler implements IBinder.DeathRecipient {
        private LinkDeathHandler() {
        }

        public void binderDied() {
            Log.e(VrRuntimeClient.TAG, "VrRuntimeClient: runtime service has died, finishing");
            VrRuntimeClient.nativeInstanceIsLost();
            VrRuntimeClient.finishClient();
            VrRuntimeClient.doDisconnect();
        }
    }

    private static Context getContext() {
        WeakReference<Context> weakReference = cachedContext;
        if (weakReference == null || weakReference.get() == null) {
            return null;
        }
        return cachedContext.get();
    }

    private static Intent CreateServiceIntent() {
        Intent serviceIntent = new Intent();
        serviceIntent.setComponent(new ComponentName("com.oculus.systemdriver", "com.oculus.vrruntimeservice.VrRuntimeService"));
        return serviceIntent;
    }

    public static void initialize(Context inContext, int clientType_, int clientFlags_, int clientSessionPlacement_) {
        try {
            BindServiceWrapper.init();
        } catch (Exception ex) {
            Log.e(TAG, "VrRuntimeClient: BindServiceWrapper.Init exception: " + ex);
        }
        cachedContext = new WeakReference<>(inContext);
        clientType = clientType_;
        clientFlags = clientFlags_;
        clientSessionPlacement = clientSessionPlacement_;
        Log.d(TAG, "VrRuntimeClient: initialize. inContext: " + inContext);
        handlerThread = new HandlerThread("VrRuntimeClientConnectionThread");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());
        Context context = getContext();
        if (context != null) {
            try {
                context.startService(CreateServiceIntent());
            } catch (Exception ex2) {
                Log.e(TAG, "VrRuntimeClient:initialize: startService exception: " + ex2);
            }
        }
    }

    public static void shutdown() {
        Log.d(TAG, "VrRuntimeClient: shutdown.");
        doDisconnect();
        HandlerThread handlerThread2 = handlerThread;
        if (handlerThread2 != null) {
            handlerThread2.quitSafely();
            handlerThread = null;
        }
        handler = null;
        cachedContext = null;
    }

    public static int getClient6dofEntitlementMode(String pkgName) {
        Log.d(TAG, "getClient6dofEntitlementMode for " + pkgName);
        if (pkgName == null || pkgName.isEmpty()) {
            return -1;
        }
        Cursor cursor = null;
        try {
            ContentResolver contentResolver = getContext().getContentResolver();
            Cursor cursor2 = contentResolver.query(Uri.parse("content://com.oculus.ocms.publiclibrary/apps/" + pkgName), null, null, null, null);
            if (cursor2 != null) {
                if (cursor2.moveToNext()) {
                    String platformType = cursor2.getString(cursor2.getColumnIndexOrThrow("platform"));
                    if (platformType == null || !platformType.equals(PLATFORM_TYPE_3DOF)) {
                        String trackingType = cursor2.getString(cursor2.getColumnIndexOrThrow("head_tracking"));
                        if (trackingType == null) {
                            cursor2.close();
                            return -1;
                        } else if (trackingType.equals(TRACKING_MODE_REQUIRES_3DOF)) {
                            cursor2.close();
                            return 0;
                        } else if (trackingType.equals(TRACKING_MODE_ALLOW_6DOF)) {
                            cursor2.close();
                            return 1;
                        } else if (trackingType.equals(TRACKING_MODE_REQUIRES_6DOF)) {
                            cursor2.close();
                            return 2;
                        } else {
                            cursor2.close();
                            return -1;
                        }
                    } else {
                        cursor2.close();
                        return 3;
                    }
                }
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            return -1;
        } catch (Exception ex) {
            Log.d(TAG, "Failed getClient6dofEntitlement " + ex);
            if (0 != 0) {
                cursor.close();
            }
            return -1;
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public static class RuntimeServiceConnection implements ServiceConnection {
        CountDownLatch mSignalOnConnected = null;

        public RuntimeServiceConnection(CountDownLatch signalOnConnected) {
            this.mSignalOnConnected = signalOnConnected;
        }

        public void onServiceConnected(ComponentName name, IBinder binder) {
            synchronized (VrRuntimeClient.class) {
                Log.d(VrRuntimeClient.TAG, "VrRuntimeClient: Service connected.");
                if (VrRuntimeClient.serviceConnection != this) {
                    Log.e(VrRuntimeClient.TAG, "VrRuntimeClient: onServiceConnected with stale serviceConnection object!!!");
                }
                IVrRuntimeService unused = VrRuntimeClient.vrRuntimeService = IVrRuntimeService.Stub.asInterface(binder);
                try {
                    binder.linkToDeath(VrRuntimeClient.linkDeathHandler, 0);
                } catch (Exception ex) {
                    Log.d(VrRuntimeClient.TAG, "VrRuntimeClient: onServiceConnected: linkToDeath Exception: " + ex);
                }
                try {
                    IRuntimeClient unused2 = VrRuntimeClient.vrRuntimeClient = VrRuntimeClient.vrRuntimeService.createClient(VrRuntimeClient.clientToken, VrRuntimeClient.clientType, VrRuntimeClient.clientFlags, VrRuntimeClient.clientSessionPlacement, VrRuntimeClient.ProcessName);
                } catch (Exception ex2) {
                    Log.d(VrRuntimeClient.TAG, "VrRuntimeClient: createClient exception " + ex2);
                }
                VrRuntimeClient.nativeRuntimeServiceConnected();
                if (this.mSignalOnConnected != null) {
                    this.mSignalOnConnected.countDown();
                }
            }
        }

        public void onServiceDisconnected(ComponentName name) {
            synchronized (VrRuntimeClient.class) {
                if (VrRuntimeClient.serviceConnection == this) {
                    Log.d(VrRuntimeClient.TAG, "VrRuntimeClient: Service disconnected.");
                    VrRuntimeClient.doDisconnect();
                } else {
                    Log.e(VrRuntimeClient.TAG, "VrRuntimeClient: Service disconnected (ignoring from older service connection).");
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0017  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean isConnected() {
        /*
            java.lang.Class<com.oculus.vrapi.VrRuntimeClient> r0 = com.oculus.vrapi.VrRuntimeClient.class
            monitor-enter(r0)
            com.oculus.vrruntimeservice.IRuntimeClient r1 = com.oculus.vrapi.VrRuntimeClient.vrRuntimeClient     // Catch:{ all -> 0x004e }
            r2 = 0
            r3 = 1
            if (r1 != 0) goto L_0x0014
            com.oculus.vrruntimeservice.IVrRuntimeService r1 = com.oculus.vrapi.VrRuntimeClient.vrRuntimeService     // Catch:{ all -> 0x004e }
            if (r1 != 0) goto L_0x0014
            com.oculus.vrapi.VrRuntimeClient$RuntimeServiceConnection r1 = com.oculus.vrapi.VrRuntimeClient.serviceConnection     // Catch:{ all -> 0x004e }
            if (r1 == 0) goto L_0x0012
            goto L_0x0014
        L_0x0012:
            r1 = 0
            goto L_0x0015
        L_0x0014:
            r1 = 1
        L_0x0015:
            if (r1 == 0) goto L_0x004c
            com.oculus.vrruntimeservice.IRuntimeClient r4 = com.oculus.vrapi.VrRuntimeClient.vrRuntimeClient     // Catch:{ all -> 0x004e }
            if (r4 != 0) goto L_0x0028
            java.lang.String r3 = "VrRuntimeClient"
            java.lang.String r4 = "vrRuntimeClient is invalid but shouldBeValid is true"
            android.util.Log.w(r3, r4)     // Catch:{ all -> 0x004e }
            doDisconnect()     // Catch:{ all -> 0x004e }
            goto L_0x004c
        L_0x0028:
            com.oculus.vrruntimeservice.IVrRuntimeService r4 = com.oculus.vrapi.VrRuntimeClient.vrRuntimeService     // Catch:{ all -> 0x004e }
            if (r4 != 0) goto L_0x0039
            java.lang.String r3 = "VrRuntimeClient"
            java.lang.String r4 = "vrRuntimeService is invalid but shouldBeValid is true"
            android.util.Log.w(r3, r4)     // Catch:{ all -> 0x004e }
            doDisconnect()     // Catch:{ all -> 0x004e }
            goto L_0x004c
        L_0x0039:
            com.oculus.vrapi.VrRuntimeClient$RuntimeServiceConnection r4 = com.oculus.vrapi.VrRuntimeClient.serviceConnection     // Catch:{ all -> 0x004e }
            if (r4 != 0) goto L_0x004a
            java.lang.String r3 = "VrRuntimeClient"
            java.lang.String r4 = "serviceConnection is invalid but shouldBeValid is true"
            android.util.Log.w(r3, r4)     // Catch:{ all -> 0x004e }
            doDisconnect()     // Catch:{ all -> 0x004e }
            goto L_0x004c
        L_0x004a:
            monitor-exit(r0)     // Catch:{ all -> 0x004e }
            return r3
        L_0x004c:
            monitor-exit(r0)     // Catch:{ all -> 0x004e }
            return r2
        L_0x004e:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004e }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrapi.VrRuntimeClient.isConnected():boolean");
    }

    private static boolean EnsureRuntimeService() {
        if (isConnected()) {
            return true;
        }
        Log.d(TAG, "VrRuntimeClient: creating new service");
        Context context = getContext();
        if (context == null) {
            Log.d(TAG, "VrRuntimeClient: No context.");
            return false;
        }
        for (int numTries = 0; numTries < 5; numTries++) {
            if (numTries > 0) {
                Log.e(TAG, "EnsureConnection (runtime service): Trying again: " + numTries);
            }
            CountDownLatch connectedSignal = new CountDownLatch(1);
            serviceConnection = new RuntimeServiceConnection(connectedSignal);
            Intent serviceIntent = CreateServiceIntent();
            try {
                context.startService(serviceIntent);
            } catch (Exception ex) {
                Log.e(TAG, "VrRuntimeClient:EnsureRuntimeService: startService exception: " + ex);
            }
            try {
                if (BindServiceWrapper.bindServiceWithHandler(context, serviceIntent, serviceConnection, 0, handler)) {
                    Log.d(TAG, "VrRuntimeClient: Waiting on connection...");
                    boolean connectionIsReady = connectedSignal.await(5, TimeUnit.SECONDS);
                    Log.d(TAG, "VrRuntimeClient: connectionIsReady: " + connectionIsReady);
                } else {
                    Log.e(TAG, "VrRuntimeClient: bindServiceWithHandler failed.");
                    serviceConnection = null;
                    vrRuntimeService = null;
                    vrRuntimeClient = null;
                }
            } catch (Exception ex2) {
                Log.e(TAG, "VrRuntimeClient: EnsureRuntimeService. bindServiceWithHandler exception:" + ex2);
                serviceConnection = null;
                vrRuntimeService = null;
                vrRuntimeClient = null;
            }
            if (vrRuntimeService != null && vrRuntimeClient != null) {
                Log.d(TAG, "VrRuntimeClient: context.bindService SUCCESS");
                return true;
            }
        }
        Log.e(TAG, "VrRuntimeClient: context.bindService FAILURE.");
        return false;
    }

    /* access modifiers changed from: private */
    public static void doDisconnect() {
        synchronized (VrRuntimeClient.class) {
            Log.d(TAG, "VrRuntimeClient: Performing disconnect");
            if (vrRuntimeService != null) {
                try {
                    vrRuntimeClient.destroy();
                } catch (Exception ex) {
                    Log.d(TAG, "VrRuntimeClient: destroyClient: Exception: " + ex);
                }
                try {
                    vrRuntimeService.asBinder().unlinkToDeath(linkDeathHandler, 0);
                } catch (Exception ex2) {
                    Log.d(TAG, "VrRuntimeClient: unlinkToDeath: Exception: " + ex2);
                }
                vrRuntimeService = null;
                vrRuntimeClient = null;
            }
            if (serviceConnection != null) {
                Context context = getContext();
                if (context != null) {
                    try {
                        context.unbindService(serviceConnection);
                    } catch (Exception ex3) {
                        Log.d(TAG, "VrRuntimeClient: unbindService: Exception: " + ex3);
                    }
                }
                serviceConnection = null;
            }
            nativeRuntimeServiceDisconnected();
        }
    }

    public static boolean connectToService(String processName) {
        Log.d(TAG, "connectToService");
        ProcessName = processName;
        return EnsureRuntimeService();
    }

    public static void finishClient() {
        Context context = getContext();
        if (context == null) {
            Log.e(TAG, "VrRuntimeClient: client not finished, invalid context");
        } else if (context instanceof Activity) {
            Log.d(TAG, "VrRuntimeClient: finishing client Activity");
            ((Activity) context).finishAffinity();
        } else if (context instanceof ContextWrapper) {
            Log.d(TAG, "VrRuntimeClient: ContextWrapper found.");
            Context baseContext = ((ContextWrapper) context).getBaseContext();
            if (baseContext instanceof Service) {
                Log.d(TAG, "VrRuntimeClient: stopping ContextWrapper Service. stopself called");
                ((Service) baseContext).stopSelf();
            } else if (baseContext instanceof Activity) {
                Log.d(TAG, "VrRuntimeClient: stopping ContextWrapper Activity. finishAffinity called");
                ((Activity) baseContext).finishAffinity();
            }
        } else {
            Log.e(TAG, "VrRuntimeClient: client not finished, unknown context type" + context);
        }
    }

    public static boolean enableRendering(boolean enable, Context context) {
        if (!EnsureRuntimeService()) {
            Log.d(TAG, "VrRuntimeClient: enableRendering: EnsureRuntimeService failed");
            return false;
        }
        if (context == null) {
            context = getContext();
        }
        if (enable && context != null && clientType == 0) {
            safePokeGuardianUserServiceForThisClient(context);
        }
        IBinder clientWindow = null;
        if (enable) {
            if (context != null) {
                if (context instanceof Activity) {
                    Log.d(TAG, "VrRuntimeClient: enableRendering: activity client");
                } else if (context instanceof DialogContext) {
                    Log.d(TAG, "VrRuntimeClient: enableRendering: dialog client");
                } else if (context instanceof WindowTokenContext) {
                    Log.d(TAG, "VrRuntimeClient: enableRendering: windowtoken client");
                }
                try {
                    clientWindow = ContextUtils.getWindowToken(context);
                    Log.d(TAG, "VrRuntimeClient: enableRendering: Using clientWindow=" + clientWindow);
                } catch (Exception ex) {
                    Log.e(TAG, "VrRuntimeClient: enableRendering: unable to get clientWindow token: " + ex);
                }
            } else {
                Log.e(TAG, "VrRuntimeClient: enableRendering: null Context");
            }
            if (clientWindow == null) {
                Log.e(TAG, "VrRuntimeClient: enableRendering: null clientWindow");
            }
        }
        try {
            return vrRuntimeClient.enableRendering(clientWindow, enable);
        } catch (Exception ex2) {
            Log.d(TAG, "VrRuntimeClient: enableRendering: Exception: " + ex2);
            return false;
        }
    }

    public static void sendBroadcastIntent(String actionName, String dataStr, String currentPkgStr) {
        Log.d(TAG, "sendBroadcastIntent - action: '" + actionName + "' data: '" + dataStr + "' intent_pkg: " + currentPkgStr);
        Intent intent = new Intent(actionName);
        if (dataStr != null && !dataStr.isEmpty()) {
            intent.putExtra("intent_data", dataStr);
        }
        if (currentPkgStr != null && !currentPkgStr.isEmpty()) {
            intent.putExtra("intent_pkg", currentPkgStr);
        }
        intent.addFlags(65536);
        getContext().sendBroadcast(intent);
    }

    public static byte[] loadDriverResource(String resourceName) {
        Log.d(TAG, "loadDriverResource - resourceName: '" + resourceName + "' ");
        byte[] buffer = null;
        InputStream resourceStream = null;
        try {
            Resources resources = getContext().getApplicationContext().getPackageManager().getResourcesForApplication("com.oculus.systemdriver");
            resourceStream = resources.openRawResource(resources.getIdentifier(resourceName, "raw", "com.oculus.systemdriver"));
            buffer = new byte[resourceStream.available()];
            resourceStream.read(buffer);
            try {
                resourceStream.close();
            } catch (Exception e) {
            }
        } catch (Exception e2) {
            Log.d(TAG, "loadDriverResource - Couldn't load resource from package com.oculus.systemdriver");
            if (resourceStream != null) {
                resourceStream.close();
            }
        } catch (Throwable th) {
            if (resourceStream != null) {
                try {
                    resourceStream.close();
                } catch (Exception e3) {
                }
            }
            throw th;
        }
        return buffer;
    }

    public static String[] getPackageRequestedFeatures(Context ctx, String packageName) {
        try {
            PackageInfo info = ctx.getPackageManager().getPackageInfo(packageName, 16384);
            if (info.reqFeatures == null) {
                return null;
            }
            ArrayList<String> featuresList = new ArrayList<>();
            FeatureInfo[] featureInfoArr = info.reqFeatures;
            for (FeatureInfo f : featureInfoArr) {
                if (f.name != null) {
                    featuresList.add(f.name);
                }
            }
            return (String[]) featuresList.toArray(new String[0]);
        } catch (Throwable e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }

    public static String[] getPackageRequestedPermissions(Context ctx, String packageName) {
        try {
            PackageInfo info = ctx.getPackageManager().getPackageInfo(packageName, 4096);
            if (info.requestedPermissions != null) {
                return info.requestedPermissions;
            }
            return null;
        } catch (Throwable e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }

    public static int checkPackageHasFeature(Context ctx, String packageName, String featureName) {
        StringBuffer result = new StringBuffer();
        int foundManifestValue = 0;
        try {
            PackageInfo info = ctx.getPackageManager().getPackageInfo(packageName, 16384);
            if (info.reqFeatures != null) {
                FeatureInfo[] featureInfoArr = info.reqFeatures;
                for (FeatureInfo f : featureInfoArr) {
                    if (f.name != null && f.name.equals(featureName)) {
                        foundManifestValue = f.flags != 0 ? 1 : 2;
                    }
                }
            }
        } catch (Throwable e) {
            result.append("Could not retrieve data: ");
            result.append(e.getMessage());
        }
        return foundManifestValue;
    }

    public static boolean checkPackageHasPermission(Context ctx, String packageName, String permissionName) {
        StringBuffer result = new StringBuffer();
        try {
            PackageInfo info = ctx.getPackageManager().getPackageInfo(packageName, 4096);
            if (info.requestedPermissions != null) {
                for (String s : info.requestedPermissions) {
                    if (s.compareTo(permissionName) == 0) {
                        return true;
                    }
                }
            }
        } catch (Throwable e) {
            result.append("Could not retrieve data: ");
            result.append(e.getMessage());
        }
        return false;
    }

    public static String getApplicationMetadata(Context context, String key, String defaultValue) {
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getApplicationContext().getPackageName(), 128);
            if (appInfo.metaData != null) {
                return appInfo.metaData.getString(key, defaultValue);
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        return defaultValue;
    }

    public static String getApplicationDevicesSupportedList(Context context) {
        return getApplicationMetadata(context, "com.oculus.supportedDevices", "quest");
    }

    public static int getSecureTrackingRequest(Context context) {
        Log.d(TAG, "getSecureTrackingRequest OSSDKTRACKING " + context.getPackageName());
        boolean requestSecureTracking = false;
        boolean requestAuditSecureTracking = false;
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle == null) {
                Log.w(TAG, "OSSDKTRACKING No package metadata available for " + context.getPackageName());
            } else {
                requestSecureTracking = bundle.getBoolean("com.oculus.tracking_service.request_secure_tracking");
                requestAuditSecureTracking = bundle.getBoolean("com.oculus.tracking_service.request_audit_secure_tracking");
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "OSSDKTRACKING Failed to find package: " + e.getMessage());
        }
        if (requestSecureTracking) {
            return 1;
        }
        if (requestAuditSecureTracking) {
            return 2;
        }
        return 0;
    }

    public static void safePokeGuardianUserServiceForThisClient(Context context) {
        try {
            pokeGuardianUserServiceForThisClient(context);
        } catch (Exception e) {
            Log.w(TAG, "pokeGuardianUserServiceForThisClient: failed", e);
        }
    }

    private static void pokeGuardianUserServiceForThisClient(Context context) {
        if (context.getApplicationInfo() == null) {
            Log.d(TAG, "pokeGuardianUserServiceForThisClient: failed, no application info");
            return;
        }
        int appUid = context.getApplicationInfo().uid;
        Log.d(TAG, "pokeGuardianUserServiceForThisClient: " + context.getPackageName() + ":" + appUid);
        context.startService(new Intent("FOREGROUND_CLIENT").setComponent(new ComponentName(SystemProps.getString("persist.oculus.guardian.package", "com.oculus.guardian"), "com.oculus.vrguardianservice.VrGuardianUserService")).putExtra("android.intent.extra.user_handle", appUid / 100000));
    }
}
