package com.oculus.vrruntimeservice;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.util.Log;
import com.oculus.os.ActivityManagerUtils;
import com.oculus.os.NightShiftController;
import com.oculus.vrapi.ContextHelper;
import com.oculus.vrruntimeservice.IRuntimeClient;
import com.oculus.vrruntimeservice.IVrRuntimeService;
import java.io.File;
import java.util.Hashtable;
import java.util.Iterator;

public class VrRuntimeService extends Service {
    private static String ACTION_MOUNT_STATE_CHANGED = "com.oculus.intent.action.MOUNT_STATE_CHANGED";
    private static final String INTENT_COMPOSITOR_SIMULATE_THERMAL = "com.oculus.vrruntimeservice.COMPOSITOR_SIMULATE_THERMAL";
    private static final String INTENT_COMPOSITOR_SKIP_RENDERING = "com.oculus.vrruntimeservice.COMPOSITOR_SKIP_RENDERING";
    private static final String INTENT_PRIMARY_PACKAGE_CHANGED = "com.oculus.vrshell.intent.action.PRIMARY_PACKAGE_CHANGED";
    private static final String INTENT_UPDATE_HMDMESH = "com.oculus.vrruntimeservice.UPDATE_HMDMESH";
    private static final String TAG = "VrRuntimeService";
    private static final String VRSHELL_PKG_NAME = "com.oculus.vrshell";
    private static ActivityManagerUtils activityManagerUtils = null;
    private static CompositorSimulateThermalReceiver compositorSimulateThermalReceiver = null;
    private static CompositorSkipRenderingReceiver compositorSkipRenderingReceiver = null;
    private static final FocusedWindowObserver focusedWindowObserver = new FocusedWindowObserver();
    private static int focusedWindowPid = -1;
    private static IBinder focusedWindowToken = null;
    private static int focusedWindowUid = -1;
    private static final ForegroundClientObserver foregroundActivityObserver = new ForegroundClientObserver();
    private static HmdMeshUpdateReceiver hmdMeshUpdateReceiver = null;
    private static NightShiftModeObserver nightShiftModeObserver = null;
    private static ScreenStateReceiver screenStateReceiver = null;
    private static ShellOverlayClient shellOverlayClient = null;
    private static final Hashtable<Integer, ClientInfo> trustedClientMap = new Hashtable<>();
    private static final Hashtable<Integer, ClientInfo> trustedOverlayClientMap = new Hashtable<>();
    private static UserUnlockReceiver userUnlockReceiver = null;
    private static final Hashtable<IBinder, ClientInfo> windowToClientMap = new Hashtable<>();
    private final IVrRuntimeService.Stub binder = new IVrRuntimeService.Stub() {
        /* class com.oculus.vrruntimeservice.VrRuntimeService.AnonymousClass1 */

        @Override // com.oculus.vrruntimeservice.IVrRuntimeService
        public IRuntimeClient createClient(IBinder token, int clientType, int clientFlags, int clientSessionPlacement, String processName) {
            Exception ex;
            Exception ex2;
            synchronized (VrRuntimeService.class) {
                try {
                    String appPackageName = VrRuntimeService.this.getBaseContext().getPackageManager().getNameForUid(Binder.getCallingUid());
                    if (token == null) {
                        Log.d(VrRuntimeService.TAG, "VrRuntimeService: createClient: token == null. PackageName: " + appPackageName);
                        try {
                            return null;
                        } catch (Throwable th) {
                            ex = th;
                            throw ex;
                        }
                    } else {
                        int clientPid = Binder.getCallingPid();
                        int clientUid = Binder.getCallingUid();
                        int clientId = VrRuntimeService.nativeCreateClient(appPackageName, clientUid, clientPid, clientType, clientFlags, clientSessionPlacement, processName);
                        try {
                            RuntimeClient client = new RuntimeClient(appPackageName, clientType, clientId, clientPid, clientUid, token);
                            token.linkToDeath(client, 0);
                            if (clientType == 0) {
                                synchronized (VrRuntimeService.trustedClientMap) {
                                    try {
                                        VrRuntimeService.trustedClientMap.put(Integer.valueOf(clientId), new ClientInfo(clientId, clientUid, clientPid, appPackageName, clientType));
                                    } catch (Throwable th2) {
                                        th = th2;
                                        throw th;
                                    }
                                }
                            } else if (clientType == 1 && VrRuntimeService.nativeClientHasOverlayPermission(clientId)) {
                                synchronized (VrRuntimeService.trustedOverlayClientMap) {
                                    VrRuntimeService.trustedOverlayClientMap.put(Integer.valueOf(clientId), new ClientInfo(clientId, clientUid, clientPid, appPackageName, clientType));
                                }
                            }
                            return client;
                        } catch (Exception e) {
                            ex2 = e;
                            Log.d(VrRuntimeService.TAG, "VrRuntimeService: createClient exception: " + ex2);
                            return null;
                        } catch (Throwable th3) {
                            ex = th3;
                            throw ex;
                        }
                    }
                } catch (Exception e2) {
                    ex2 = e2;
                    Log.d(VrRuntimeService.TAG, "VrRuntimeService: createClient exception: " + ex2);
                    return null;
                }
            }
        }
    };

    private static native boolean nativeAllowForegroundClientCallback();

    /* access modifiers changed from: private */
    public static native boolean nativeClientHasOverlayPermission(int i);

    /* access modifiers changed from: private */
    public static native int nativeCreateClient(String str, int i, int i2, int i3, int i4, int i5, String str2);

    private static native void nativeDestroyClient(int i);

    /* access modifiers changed from: private */
    public static native boolean nativeEnableRendering(int i, boolean z);

    /* access modifiers changed from: private */
    public static native void nativeFocusedWindowChanged(int i);

    /* access modifiers changed from: private */
    public static native void nativeForegroundClientChanged(int i, int i2);

    private static native void nativeInitVrRuntimeService(Context context, boolean z);

    /* access modifiers changed from: private */
    public static native void nativeNightShiftColorTemperature(int i);

    /* access modifiers changed from: private */
    public static native void nativeScreenStateChanged(boolean z);

    /* access modifiers changed from: private */
    public static native void nativeSetDeviceUnlocked();

    private static native boolean nativeShouldUsePersistentShellOverlayService();

    private static native void nativeShutdownVrRuntimeService();

    /* access modifiers changed from: private */
    public static native void nativeSimulateThermal(String str, int i, int i2);

    /* access modifiers changed from: private */
    public static native void nativeSkipCompositorRendering(int i);

    /* access modifiers changed from: private */
    public static native void nativeUpdateHmdMesh();

    /* access modifiers changed from: private */
    public static class ClientInfo {
        public int ClientType;
        public String PackageName;
        public int id;
        public int pid;
        public int uid;

        public ClientInfo(int clientId, int uid2, int pid2, String packageName, int clientType) {
            this.id = clientId;
            this.uid = uid2;
            this.pid = pid2;
            this.PackageName = packageName;
            this.ClientType = clientType;
        }
    }

    static {
        System.loadLibrary("vrruntimeservice");
    }

    private static final class FocusedWindowObserver implements ActivityManagerUtils.WindowLayoutObserver {
        private FocusedWindowObserver() {
        }

        public void onFocusedWindowChanged(IBinder windowToken, int uid) {
            Log.d(VrRuntimeService.TAG, "onFocusedWindowChanged: focusedWindowToken=" + windowToken + ", focusedWindowUid=" + uid);
            onFocusedWindowChanged(windowToken, uid, -1);
        }

        public void onFocusedWindowChanged(IBinder windowToken, int uid, int pid) {
            Log.d(VrRuntimeService.TAG, "onFocusedWindowChanged: focusedWindowToken=" + windowToken + ", focusedWindowUid=" + uid + ", focusedWindowPid=" + pid);
            int id = 0;
            synchronized (VrRuntimeService.windowToClientMap) {
                if (VrRuntimeService.windowToClientMap.containsKey(windowToken)) {
                    Log.d(VrRuntimeService.TAG, "onFocusedWindowChanged: top window's client already enabled");
                    id = ((ClientInfo) VrRuntimeService.windowToClientMap.get(windowToken)).id;
                }
                if (id == 0 && uid == VrRuntimeService.focusedWindowUid) {
                    Iterator it = VrRuntimeService.windowToClientMap.values().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        ClientInfo info = (ClientInfo) it.next();
                        if (info.uid == uid && info.pid == pid) {
                            Log.d(VrRuntimeService.TAG, "onFocusedWindowChanged: falling back to client with same uid: " + info.id + " and pid " + info.pid);
                            id = info.id;
                            break;
                        }
                    }
                }
            }
            if (id == 0) {
                synchronized (VrRuntimeService.trustedOverlayClientMap) {
                    Iterator it2 = VrRuntimeService.trustedOverlayClientMap.values().iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        ClientInfo info2 = (ClientInfo) it2.next();
                        if (info2.uid == uid && info2.pid == pid) {
                            Log.d(VrRuntimeService.TAG, "onFocusedWindowChanged: found matching overlay client: " + info2.id);
                            id = info2.id;
                            break;
                        }
                    }
                }
            }
            IBinder unused = VrRuntimeService.focusedWindowToken = windowToken;
            int unused2 = VrRuntimeService.focusedWindowUid = uid;
            int unused3 = VrRuntimeService.focusedWindowPid = pid;
            VrRuntimeService.nativeFocusedWindowChanged(id);
        }
    }

    private static final class ForegroundClientObserver implements ActivityManagerUtils.ForegroundActivityObserver {
        private ForegroundClientObserver() {
        }

        public void onForegroundActivitiesChanged(int pid, int uid, boolean foregroundActivities) {
            Log.d(VrRuntimeService.TAG, "onForegroundActivitiesChanged: pid=" + pid + ", uid=" + uid + ", hasForegroundActivities=" + foregroundActivities);
            if (foregroundActivities) {
                VrRuntimeService.nativeForegroundClientChanged(uid, pid);
            }
        }
    }

    private static final class UserUnlockReceiver extends BroadcastReceiver {
        private UserUnlockReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            Log.d(VrRuntimeService.TAG, "Received broadcast: " + intent.getAction());
            if ("android.intent.action.USER_UNLOCKED".equals(intent.getAction())) {
                VrRuntimeService.nativeSetDeviceUnlocked();
            }
        }
    }

    public static int getPackageUid(Context context, String packageName) {
        try {
            return context.getPackageManager().getPackageUid(packageName, 0);
        } catch (Exception e) {
            Log.d(TAG, "Package name " + packageName + " was not found.");
            return -1;
        }
    }

    public static String getPackageNameFromUid(Context context, int uid) {
        try {
            String[] names = context.getPackageManager().getPackagesForUid(uid);
            if (names.length > 0) {
                return names[0];
            }
            return "";
        } catch (Exception e) {
            Log.d(TAG, "Package UID " + uid + " was not found.");
            return "";
        }
    }

    public static boolean applicationRequiresSplash(Context context, String packageName) {
        if (context.getApplicationContext().getPackageManager() == null) {
            return false;
        }
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(packageName, 128);
            if (appInfo == null || appInfo.metaData == null) {
                return false;
            }
            boolean requiresSplash = appInfo.metaData.getBoolean("com.oculus.ossplash", false);
            Log.d(TAG, "Package " + packageName + " has ossplash " + requiresSplash);
            return requiresSplash;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private static ApplicationInfo getApplicationInfoAsUser(String packageName, Context context, int flags, UserHandle userHandle) throws Exception {
        return (ApplicationInfo) PackageManager.class.getMethod("getApplicationInfoAsUser", String.class, Integer.TYPE, UserHandle.class).invoke(context.getPackageManager(), packageName, Integer.valueOf(flags), userHandle);
    }

    private static int userHandleGetIdentifier(UserHandle userHandle) throws Exception {
        return ((Integer) UserHandle.class.getMethod("getIdentifier", new Class[0]).invoke(userHandle, new Object[0])).intValue();
    }

    private static int getUidForPackage(String packageName, Context context) throws Exception {
        UserHandle userHandle = ActivityManagerUtils.getCurrentUser();
        ApplicationInfo appInfo = getApplicationInfoAsUser(packageName, context, 0, userHandle);
        return ((Integer) UserHandle.class.getMethod("getUid", Integer.TYPE, Integer.TYPE).invoke(null, Integer.valueOf(userHandleGetIdentifier(userHandle)), Integer.valueOf(appInfo.uid))).intValue();
    }

    public static int getForegroundClientId(Context context) {
        try {
            int clientId = 0;
            String topActivityPackageName = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1).get(0).topActivity.getPackageName();
            try {
                int uid = getUidForPackage(topActivityPackageName, context);
                Log.d(TAG, "getForegroundClientId: foreground package " + topActivityPackageName + " uid " + uid);
                synchronized (trustedClientMap) {
                    Iterator<ClientInfo> it = trustedClientMap.values().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        ClientInfo info = it.next();
                        if (info.uid == uid) {
                            clientId = info.id;
                            Log.d(TAG, "getForegroundClientId: found client: " + info.PackageName + " id: " + info.id + " uid: " + info.uid);
                            break;
                        }
                    }
                }
                return clientId;
            } catch (Exception ex) {
                Log.d(TAG, "getForegroundClientId error: " + topActivityPackageName + " was not found in client list.", ex);
                return 0;
            }
        } catch (Exception ex2) {
            Log.e(TAG, "getForegroundClientId failed with " + ex2);
            return -1;
        }
    }

    public void onCreate() {
        Log.d(TAG, "onCreate");
        Bundle notificationExtras = new Bundle();
        notificationExtras.putString("oculus_notification_type", "systemdriver_foreground_service");
        startForeground(1, new Notification.Builder(this).setPriority(-1).setSmallIcon(17301659).setContentTitle("VR Runtime Service").setExtras(notificationExtras).build());
        nativeInitVrRuntimeService(getBaseContext(), !((UserManager) getSystemService(UserManager.class)).isUserUnlocked());
        activityManagerUtils = new ActivityManagerUtils();
        ActivityManagerUtils activityManagerUtils2 = activityManagerUtils;
        if (activityManagerUtils2 != null) {
            activityManagerUtils2.setWindowLayoutObserver(focusedWindowObserver, new Handler(Looper.getMainLooper()));
            if (nativeAllowForegroundClientCallback()) {
                activityManagerUtils.setForegroundActivityObserver(foregroundActivityObserver, new Handler(Looper.getMainLooper()));
            }
        }
        screenStateReceiver = new ScreenStateReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.SCREEN_ON");
        filter.addAction("android.intent.action.SCREEN_OFF");
        filter.addAction(ACTION_MOUNT_STATE_CHANGED);
        registerReceiver(screenStateReceiver, filter);
        userUnlockReceiver = new UserUnlockReceiver();
        registerReceiver(userUnlockReceiver, new IntentFilter("android.intent.action.USER_UNLOCKED"));
        hmdMeshUpdateReceiver = new HmdMeshUpdateReceiver();
        registerReceiver(hmdMeshUpdateReceiver, new IntentFilter(INTENT_UPDATE_HMDMESH));
        compositorSkipRenderingReceiver = new CompositorSkipRenderingReceiver();
        registerReceiver(compositorSkipRenderingReceiver, new IntentFilter(INTENT_COMPOSITOR_SKIP_RENDERING));
        compositorSimulateThermalReceiver = new CompositorSimulateThermalReceiver();
        registerReceiver(compositorSimulateThermalReceiver, new IntentFilter(INTENT_COMPOSITOR_SIMULATE_THERMAL));
        nightShiftModeObserver = new NightShiftModeObserver(new Handler(Looper.getMainLooper()), this);
        getContentResolver().registerContentObserver(Settings.Secure.getUriFor("night_display_activated"), false, nightShiftModeObserver);
        getContentResolver().registerContentObserver(Settings.Secure.getUriFor("night_display_color_temperature"), false, nightShiftModeObserver);
        if (nativeShouldUsePersistentShellOverlayService()) {
            shellOverlayClient = new ShellOverlayClient(this);
        }
        super.onCreate();
        Log.d(TAG, "onCreate - finished");
    }

    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        nativeShutdownVrRuntimeService();
        unregisterReceiver(screenStateReceiver);
        screenStateReceiver = null;
        unregisterReceiver(userUnlockReceiver);
        userUnlockReceiver = null;
        unregisterReceiver(hmdMeshUpdateReceiver);
        hmdMeshUpdateReceiver = null;
        unregisterReceiver(compositorSkipRenderingReceiver);
        compositorSkipRenderingReceiver = null;
        unregisterReceiver(compositorSimulateThermalReceiver);
        compositorSimulateThermalReceiver = null;
        getContentResolver().unregisterContentObserver(nightShiftModeObserver);
        nightShiftModeObserver = null;
        ActivityManagerUtils activityManagerUtils2 = activityManagerUtils;
        if (activityManagerUtils2 != null) {
            activityManagerUtils2.clearWindowLayoutObserver();
            activityManagerUtils.clearForegroundActivityObserver();
        }
        activityManagerUtils = null;
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

    public static String getFilesDir(Context context) {
        return context.getFilesDir().getAbsolutePath();
    }

    public static boolean createSubdirStructure(Context context) {
        String filesDir = getFilesDir(context);
        return createDirIfNotExists(filesDir + "/compositor");
    }

    private static boolean createDirIfNotExists(String path) {
        File file = new File(path);
        if (file.exists() || file.mkdirs()) {
            return true;
        }
        return false;
    }

    public static void sendPrimaryPackageChangeBroadcast(Context context) {
        Log.d(TAG, "sendPrimaryPackageChangeBroadcast");
        Intent intent = new Intent(INTENT_PRIMARY_PACKAGE_CHANGED);
        intent.setFlags(268435456);
        intent.setPackage("com.oculus.vrshell");
        ContextHelper.sendBroadcastToAllUsers(context, intent);
    }

    public class HmdMeshUpdateReceiver extends BroadcastReceiver {
        public HmdMeshUpdateReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            Log.d(VrRuntimeService.TAG, "Received broadcast: " + intent.getAction());
            final String action = intent.getAction();
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                /* class com.oculus.vrruntimeservice.VrRuntimeService.HmdMeshUpdateReceiver.AnonymousClass1 */

                public void run() {
                    if (action.equals(VrRuntimeService.INTENT_UPDATE_HMDMESH)) {
                        VrRuntimeService.nativeUpdateHmdMesh();
                    } else {
                        Log.e(VrRuntimeService.TAG, "Invalid intent action");
                    }
                }
            });
        }
    }

    public class CompositorSkipRenderingReceiver extends BroadcastReceiver {
        public CompositorSkipRenderingReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            Log.d(VrRuntimeService.TAG, "Received broadcast: " + intent.getAction());
            if (intent.getAction().equals(VrRuntimeService.INTENT_COMPOSITOR_SKIP_RENDERING)) {
                int msecToSkipFor = intent.getIntExtra("milliseconds", 5000);
                if (msecToSkipFor <= 0) {
                    msecToSkipFor = -1;
                } else if (msecToSkipFor > 60000) {
                    msecToSkipFor = 60000;
                }
                if (msecToSkipFor == -1) {
                    Log.d(VrRuntimeService.TAG, "resuming Compositor rendering");
                } else {
                    Log.d(VrRuntimeService.TAG, "skipping Compositor rendering for: " + msecToSkipFor + " milliseconds");
                }
                VrRuntimeService.nativeSkipCompositorRendering(msecToSkipFor);
                return;
            }
            Log.e(VrRuntimeService.TAG, "Invalid intent action");
        }
    }

    public class CompositorSimulateThermalReceiver extends BroadcastReceiver {
        public CompositorSimulateThermalReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            Log.d(VrRuntimeService.TAG, "Received broadcast: " + intent.getAction());
            if (intent.getAction().equals(VrRuntimeService.INTENT_COMPOSITOR_SIMULATE_THERMAL)) {
                String subsystem = intent.getStringExtra("subsystem");
                if (subsystem == null) {
                    subsystem = new String();
                }
                VrRuntimeService.nativeSimulateThermal(subsystem, intent.getIntExtra("seconds_throttled", 10), intent.getIntExtra("step_down_time", 4));
                return;
            }
            Log.e(VrRuntimeService.TAG, "Invalid intent action");
        }
    }

    public class ScreenStateReceiver extends BroadcastReceiver {
        public ScreenStateReceiver() {
        }

        public void onReceive(Context context, final Intent intent) {
            Log.d(VrRuntimeService.TAG, "Received broadcast: " + intent.getAction());
            final String action = intent.getAction();
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                /* class com.oculus.vrruntimeservice.VrRuntimeService.ScreenStateReceiver.AnonymousClass1 */

                public void run() {
                    if ("android.intent.action.SCREEN_OFF".equals(action)) {
                        VrRuntimeService.nativeScreenStateChanged(false);
                    } else if ("android.intent.action.SCREEN_ON".equals(action)) {
                        VrRuntimeService.nativeScreenStateChanged(true);
                    } else if (VrRuntimeService.ACTION_MOUNT_STATE_CHANGED.equals(action)) {
                        boolean mounted = intent.getExtras().getBoolean("state");
                        Log.d(VrRuntimeService.TAG, "mounted state: " + mounted);
                        if (mounted) {
                            VrRuntimeService.nativeScreenStateChanged(mounted);
                        }
                    } else {
                        Log.e(VrRuntimeService.TAG, "Invalid intent action");
                    }
                }
            });
        }
    }

    public class NightShiftModeObserver extends ContentObserver {
        private final NightShiftController nightShiftController;

        public NightShiftModeObserver(Handler handler, Context context) {
            super(handler);
            this.nightShiftController = new NightShiftController(context);
        }

        public void onChange(boolean selfChange) {
            onChange(selfChange, null);
        }

        public void onChange(boolean selfChange, Uri uri) {
            Log.d(VrRuntimeService.TAG, "onChange: " + uri);
            VrRuntimeService.nativeNightShiftColorTemperature(this.nightShiftController.isActivated() ? this.nightShiftController.getColorTemperature() : 6504);
        }
    }

    private class RuntimeClient extends IRuntimeClient.Stub implements IBinder.DeathRecipient {
        private IBinder clientBinder;
        private int clientId;
        private int clientPid;
        private int clientType;
        private int clientUid;
        private IBinder clientWindow;
        private String packageName;

        public RuntimeClient(String packageName2, int clientType2, int clientId2, int clientPid2, int clientUid2, IBinder clientBinder2) {
            this.clientId = clientId2;
            this.packageName = packageName2;
            this.clientType = clientType2;
            this.clientPid = clientPid2;
            this.clientUid = clientUid2;
            this.clientBinder = clientBinder2;
            VrRuntimeService.createSubdirStructure(VrRuntimeService.this.getBaseContext());
        }

        public void binderDied() {
            synchronized (VrRuntimeService.class) {
                Log.d(VrRuntimeService.TAG, "VrRuntimeService: binderDied: " + this.packageName);
                VrRuntimeService.destroyClientInternal(this.packageName, this.clientId, this.clientPid, this.clientType, this.clientWindow);
            }
        }

        @Override // com.oculus.vrruntimeservice.IRuntimeClient
        public void destroy() {
            synchronized (VrRuntimeService.class) {
                VrRuntimeService.destroyClientInternal(this.packageName, this.clientId, this.clientPid, this.clientType, this.clientWindow);
                this.clientBinder.unlinkToDeath(this, 0);
            }
        }

        @Override // com.oculus.vrruntimeservice.IRuntimeClient
        public boolean enableRendering(IBinder clientWindow2, boolean enable) {
            boolean setFocusedWindow = false;
            synchronized (VrRuntimeService.windowToClientMap) {
                if (enable) {
                    if (clientWindow2 == null) {
                        Log.d(VrRuntimeService.TAG, "enableRendering: invalid clientWindow, testing uid/pid");
                        if (this.clientUid == VrRuntimeService.focusedWindowUid && this.clientPid == VrRuntimeService.focusedWindowPid) {
                            Log.d(VrRuntimeService.TAG, "enableRendering: matched uid " + this.clientUid + " and pid " + this.clientPid);
                            clientWindow2 = VrRuntimeService.focusedWindowToken;
                        }
                    }
                    if (clientWindow2 != null) {
                        if (VrRuntimeService.windowToClientMap.containsKey(clientWindow2)) {
                            Log.d(VrRuntimeService.TAG, "enableRendering: client (" + this.clientId + ") rendering already enabled!");
                        } else {
                            VrRuntimeService.windowToClientMap.put(clientWindow2, new ClientInfo(this.clientId, this.clientUid, this.clientPid, this.packageName, this.clientType));
                            if (VrRuntimeService.focusedWindowToken == null && VrRuntimeService.focusedWindowUid == -1) {
                                Log.d(VrRuntimeService.TAG, "enableRendering: onFocusedWindowChanged event not received. Defaulting to enabled client.");
                                setFocusedWindow = true;
                            } else if (VrRuntimeService.focusedWindowToken == clientWindow2) {
                                setFocusedWindow = true;
                            }
                        }
                    }
                } else if (this.clientWindow != null) {
                    VrRuntimeService.windowToClientMap.remove(this.clientWindow);
                }
                if (enable) {
                    this.clientWindow = clientWindow2;
                } else {
                    this.clientWindow = null;
                }
                if (setFocusedWindow) {
                    VrRuntimeService.nativeFocusedWindowChanged(this.clientId);
                }
            }
            Log.d(VrRuntimeService.TAG, "enableRendering: " + this.clientPid + ":" + this.packageName + ": " + clientWindow2 + " enable: " + enable);
            try {
                return VrRuntimeService.nativeEnableRendering(this.clientId, enable);
            } catch (Exception e) {
                Log.e(VrRuntimeService.TAG, "enableRendering: remote call failed: " + e.toString());
                return false;
            }
        }
    }

    public static void destroyClientInternal(String appPackageName, int clientId, int clientPid, int clientType, IBinder clientWindow) {
        Log.d(TAG, "VrRuntimeService: destroyClientInternal: " + appPackageName + ", pid: " + clientPid + ", window: " + clientWindow);
        nativeDestroyClient(clientId);
        if (clientType == 0) {
            synchronized (trustedClientMap) {
                trustedClientMap.remove(Integer.valueOf(clientId));
            }
        } else if (clientType == 1) {
            synchronized (trustedOverlayClientMap) {
                trustedOverlayClientMap.remove(Integer.valueOf(clientId));
            }
        }
        if (clientWindow != null) {
            synchronized (windowToClientMap) {
                windowToClientMap.remove(clientWindow);
            }
        }
    }
}
