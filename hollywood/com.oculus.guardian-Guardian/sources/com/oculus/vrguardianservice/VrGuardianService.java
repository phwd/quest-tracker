package com.oculus.vrguardianservice;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.facebook.mobileconfigservice.serviceconstants.MobileConfigServiceConstants;
import com.oculus.vrguardianservice.IVrGuardianService;

public class VrGuardianService extends Service {
    private static final String GUARDIAN_NOTIF_3DOF_ENABLED = "notif_3dof_enabled";
    private static final String GUARDIAN_NOTIF_ID_3DOF_ENABLED = "oculus_mobile_guardian_3dof_enabled";
    private static final String GUARDIAN_NOTIF_ID_PASSTHROUGH_ENVIRONMENT = "oculus_mobile_guardian_passthrough_environment";
    private static final String GUARDIAN_NOTIF_ID_PT_ONDEMAND = "oculus_mobile_guardian_pt_ondemand";
    private static final String GUARDIAN_NOTIF_PASSTHROUGH_ENVIRONMENT = "notif_passthrough_environment_enabled";
    private static final String GUARDIAN_NOTIF_PT_ONDEMAND = "notif_ptondemand";
    private static final String GUARDIAN_NOTIF_TAG = "guardian";
    public static final String INTENT_KEY_DATA = "intent_data";
    public static final String INTENT_KEY_FROM_PKG = "intent_pkg";
    public static final String KEY_GUARDIAN_ACTION_TYPE = "guardian_action_type";
    public static final String KEY_GUARDIAN_EXTRA_DATA = "intent_cmd";
    private static final int MODE_RETURN_3DOF_PLATFORM = 3;
    private static final int MODE_RETURN_ALLOW_6DOF = 1;
    private static final int MODE_RETURN_REQUIRES_3DOF = 0;
    private static final int MODE_RETURN_REQUIRES_6DOF = 2;
    private static final String PLATFORM_TYPE_3DOF = "ANDROID";
    private static Class<?> SystemPropertiesCLASS = null;
    private static final String TAG = "VrGuardianService";
    private static final String TRACKING_MODE_ALLOW_6DOF = "ALLOW_6DOF";
    private static final String TRACKING_MODE_REQUIRES_3DOF = "REQUIRE_3DOF";
    private static final String TRACKING_MODE_REQUIRES_6DOF = "REQUIRE_6DOF";
    private static final String TRACKING_MODE_UNKNOWN = "UNKNOWN";
    private static int nextNotifId = 0;
    private static JsonCmdBroadcastReceiver receiver = null;
    private final IVrGuardianService.Stub binder = new IVrGuardianService.Stub() {
        /* class com.oculus.vrguardianservice.VrGuardianService.AnonymousClass7 */

        private boolean callerHasGuardianPermission() {
            int clientPid = Binder.getCallingPid();
            int clientUid = Binder.getCallingUid();
            if (VrGuardianService.this.checkPermission("com.oculus.permission.GUARDIAN_USER_CONTROL", clientPid, clientUid) == 0) {
                return true;
            }
            Log.d(VrGuardianService.TAG, "GUARDIAN_USER_CONTROL permission denied for pid=" + clientPid + " uid = " + clientUid);
            return false;
        }

        @Override // com.oculus.vrguardianservice.IVrGuardianService
        public void connectUserController(IVrGuardianUserController userController) {
            if (callerHasGuardianPermission()) {
                Log.d(VrGuardianService.TAG, "connectUserController()");
                VrGuardianService.this.guardianUserControllers.put(VrGuardianService.getUserHandleFromUID(Binder.getCallingUid()), userController);
            }
        }

        @Override // com.oculus.vrguardianservice.IVrGuardianService
        public boolean setCurrentUser(int userId, boolean active) {
            if (!callerHasGuardianPermission()) {
                return false;
            }
            Log.d(VrGuardianService.TAG, "setCurrentUser() called with: userId = [" + userId + "], active = [" + active + "]");
            return VrGuardianService.nativeSetCurrentUser(userId, active);
        }

        @Override // com.oculus.vrguardianservice.IVrGuardianService
        public void processJsonCmd(String cmdString) {
            if (cmdString != null) {
                VrGuardianService.nativeJsonCmd(cmdString);
            }
        }
    };
    private SparseArray<IVrGuardianUserController> guardianUserControllers = new SparseArray<>(4);
    private MemoryPressureObserver mMemoryPressureObserver;

    /* access modifiers changed from: private */
    public interface RunAsUser<T> {
        T run(IVrGuardianUserController iVrGuardianUserController) throws RemoteException;
    }

    /* access modifiers changed from: private */
    public static native void memoryPressureChange(int i);

    private static native void nativeInitVrGuardianService(Context context, VrGuardianService vrGuardianService);

    /* access modifiers changed from: private */
    public static native boolean nativeJsonCmd(String str);

    /* access modifiers changed from: private */
    public static native boolean nativeSetCurrentUser(int i, boolean z);

    private static native void nativeShutdownVrGuardianService();

    static {
        try {
            System.loadLibrary("vrguardianservice");
        } catch (UnsatisfiedLinkError e) {
            Log.d(TAG, "vrguardianservice library not found");
        }
        try {
            System.loadLibrary(GUARDIAN_NOTIF_TAG);
        } catch (UnsatisfiedLinkError e2) {
            Log.d(TAG, "guardian library not found");
        }
    }

    private class JsonCmdBroadcastReceiver extends BroadcastReceiver {
        public JsonCmdBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            String cmdString = intent.getStringExtra("cmd");
            Log.d(VrGuardianService.TAG, "JsonCmdBroadcastReceiver. Cmd: " + cmdString);
            if (cmdString != null) {
                VrGuardianService.nativeJsonCmd(cmdString);
            }
        }
    }

    private static String getSystemPropString(String key, String defaultValue) {
        if (SystemPropertiesCLASS == null) {
            try {
                SystemPropertiesCLASS = Class.forName("android.os.SystemProperties");
            } catch (ClassNotFoundException e) {
            }
        }
        try {
            return (String) SystemPropertiesCLASS.getMethod(MobileConfigServiceConstants.PATH_GET, String.class, String.class).invoke(null, key, defaultValue);
        } catch (Exception e2) {
            Log.d(TAG, "getString() returning default " + defaultValue);
            return defaultValue;
        }
    }

    private static boolean isUserDevBuild() {
        Log.d(TAG, "Checking build type");
        String propValue = getSystemPropString("ro.build.fingerprint", null);
        if (propValue == null) {
            return false;
        }
        return !propValue.endsWith("release-keys");
    }

    public void onCreate() {
        Log.d(TAG, "onCreate");
        Bundle notificationExtras = new Bundle();
        notificationExtras.putString("oculus_notification_type", "guardian_foreground_service");
        startForeground(1, new Notification.Builder(this).setPriority(-1).setSmallIcon(17301659).setContentTitle("VR Guardian Service").setExtras(notificationExtras).build());
        nativeInitVrGuardianService(this, this);
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.oculus.vrguardianservice.JsonCmdBroadcast");
        receiver = new JsonCmdBroadcastReceiver();
        registerReceiver(receiver, filter);
        this.mMemoryPressureObserver = new MemoryPressureObserver(new MemoryPressureCallback() {
            /* class com.oculus.vrguardianservice.VrGuardianService.AnonymousClass1 */

            @Override // com.oculus.vrguardianservice.MemoryPressureCallback
            public void OnMemoryPressureStateChanged(MemoryPressure memoryPressure) {
                Log.d(VrGuardianService.TAG, "OnMemoryPressureStateChanged: memoryPressure = " + memoryPressure);
                VrGuardianService.memoryPressureChange(memoryPressure.enumValue());
            }
        });
        super.onCreate();
        Log.d(TAG, "onCreate - finished");
    }

    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        nativeShutdownVrGuardianService();
        unregisterReceiver(receiver);
        super.onDestroy();
        Log.d(TAG, "onDestroy - finished");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return 1;
    }

    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return this.binder;
    }

    /* access modifiers changed from: package-private */
    public boolean createSubdirStructure(int userId) {
        return ((Boolean) withUser(userId, "createSubdirStructure", false, new RunAsUser<Boolean>() {
            /* class com.oculus.vrguardianservice.VrGuardianService.AnonymousClass2 */

            @Override // com.oculus.vrguardianservice.VrGuardianService.RunAsUser
            public Boolean run(IVrGuardianUserController userController) throws RemoteException {
                return Boolean.valueOf(userController.createSubdirStructure());
            }
        })).booleanValue();
    }

    /* access modifiers changed from: package-private */
    public String readUserJSONFile(int userId, final String fileName) {
        return (String) withUser(userId, "readUserJSONFile", null, new RunAsUser<String>() {
            /* class com.oculus.vrguardianservice.VrGuardianService.AnonymousClass3 */

            @Override // com.oculus.vrguardianservice.VrGuardianService.RunAsUser
            public String run(IVrGuardianUserController userController) throws RemoteException {
                return userController.loadUserFile(fileName);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public boolean writeUserJSONFile(int userId, final String fileName, final String json) {
        return ((Boolean) withUser(userId, "writeUserJSONFile", false, new RunAsUser<Boolean>() {
            /* class com.oculus.vrguardianservice.VrGuardianService.AnonymousClass4 */

            @Override // com.oculus.vrguardianservice.VrGuardianService.RunAsUser
            public Boolean run(IVrGuardianUserController userController) throws RemoteException {
                return Boolean.valueOf(userController.saveUserFile(fileName, json));
            }
        })).booleanValue();
    }

    public static long getForegroundAppPSS(Context context) {
        return (long) ((ActivityManager) context.getSystemService("activity")).getProcessMemoryInfo(new int[]{Process.myPid()})[0].getTotalPss();
    }

    public void onDemandPassthroughIntent(Context context, int userId, boolean enable, boolean showDoubleTapNotif) {
        Log.d(TAG, "onDemandPassthroughIntent: " + enable + " for user " + userId);
        if (enable) {
            Intent intent = new Intent(context, PTODActivity.class);
            Bundle bundle = new Bundle();
            bundle.putBoolean("enable", enable);
            bundle.putBoolean("showDoubleTapNotif", showDoubleTapNotif);
            intent.putExtras(bundle);
            startUserActivity(userId, intent);
            return;
        }
        Intent intent2 = new Intent();
        intent2.setAction("com.oculus.vrguardianservice.PTODBroadcast");
        sendUserBroadcast(userId, intent2);
    }

    /* access modifiers changed from: package-private */
    public void startUserActivity(int userId, final Intent intent) {
        withUser(userId, "startUserActivity", null, new RunAsUser<Void>() {
            /* class com.oculus.vrguardianservice.VrGuardianService.AnonymousClass5 */

            @Override // com.oculus.vrguardianservice.VrGuardianService.RunAsUser
            public Void run(IVrGuardianUserController userController) throws RemoteException {
                userController.startUserActivity(intent);
                return null;
            }
        });
    }

    public void sendBroadcastIntent(Context context, int userId, String actionName, String dataStr, String currentPkgStr, int type, String extraInfoStr) {
        Log.d(TAG, "sendBroadcastIntent - action: '" + actionName + "' data: '" + dataStr + "' intent_pkg: " + currentPkgStr + " intent_cmd: " + extraInfoStr);
        Intent intent = new Intent(actionName);
        if (dataStr != null && !dataStr.isEmpty()) {
            intent.putExtra(INTENT_KEY_DATA, dataStr);
        }
        if (currentPkgStr != null && !currentPkgStr.isEmpty()) {
            intent.putExtra(INTENT_KEY_FROM_PKG, currentPkgStr);
        }
        intent.putExtra(KEY_GUARDIAN_ACTION_TYPE, type);
        intent.putExtra(KEY_GUARDIAN_EXTRA_DATA, extraInfoStr);
        intent.setPackage("com.oculus.vrshell");
        intent.addFlags(65536);
        sendUserBroadcast(userId, intent);
    }

    /* access modifiers changed from: package-private */
    public void sendUserBroadcast(int userId, final Intent intent) {
        withUser(userId, "sendUserBroadcast", null, new RunAsUser<Void>() {
            /* class com.oculus.vrguardianservice.VrGuardianService.AnonymousClass6 */

            @Override // com.oculus.vrguardianservice.VrGuardianService.RunAsUser
            public Void run(IVrGuardianUserController userController) throws RemoteException {
                userController.sendUserBroadcast(intent);
                return null;
            }
        });
    }

    public static int getSupportedVrFeatures(Context context, String pkgName) {
        Log.d(TAG, "getSupportedVrFeatures for " + pkgName);
        if (pkgName == null || pkgName.isEmpty()) {
            return -1;
        }
        Cursor cursor = null;
        try {
            ContentResolver contentResolver = context.getContentResolver();
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

    /* access modifiers changed from: private */
    public static int getUserHandleFromUID(int appUid) {
        return appUid / 100000;
    }

    private <T> T withUser(int userId, String operation, T errorRetValue, RunAsUser<T> runAsUser) {
        IVrGuardianUserController guardianUserController = this.guardianUserControllers.get(userId);
        if (guardianUserController == null) {
            Log.w(TAG, "VrGuardianService: cannot call " + operation + " user controller is null for userId = " + userId);
            return errorRetValue;
        }
        try {
            Log.v(TAG, "VrGuardianService: for user: " + userId + " run: " + operation);
            return runAsUser.run(guardianUserController);
        } catch (Exception e) {
            Log.e(TAG, "VrGuardianService: " + operation + "() error ", e);
            return errorRetValue;
        }
    }

    public static String getApplicationName(Context context) {
        return context.getApplicationContext().getPackageManager().getApplicationLabel(context.getApplicationContext().getApplicationInfo()).toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void sendGuardianNotification(android.content.Context r11, java.lang.String r12) {
        /*
        // Method dump skipped, instructions count: 198
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrguardianservice.VrGuardianService.sendGuardianNotification(android.content.Context, java.lang.String):void");
    }

    private static synchronized int getNextNotifId() {
        int i;
        synchronized (VrGuardianService.class) {
            i = nextNotifId;
            nextNotifId = i + 1;
        }
        return i;
    }

    public void onTrimMemory(int level) {
        Log.i(TAG, "onTrimMemory");
        this.mMemoryPressureObserver.onNativeTrimMemory(level);
        super.onTrimMemory(level);
    }
}
