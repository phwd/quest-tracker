package com.oculus.vrguardianservice;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.oculus.vrguardianservice.IVrGuardianService;
import com.oculus.vrguardianservice.IVrGuardianUserController;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class VrGuardianUserService extends Service {
    public static final String BOOT = "BOOT_COMPLETED";
    public static final String FOREGROUND_CLIENT = "FOREGROUND_CLIENT";
    private static final String TAG = "VrGuardianUserService";
    GuardianServiceConnection guardianServiceConnection;
    private boolean hadValidUser;
    private JsonCmdUserBroadcastReceiver jsonCmdReceiver = null;
    private UserBroadcastReceiver receiver = null;
    private final IVrGuardianUserController.Stub userController = new IVrGuardianUserController.Stub() {
        /* class com.oculus.vrguardianservice.VrGuardianUserService.AnonymousClass1 */

        @Override // com.oculus.vrguardianservice.IVrGuardianUserController
        public boolean createSubdirStructure() {
            return VrGuardianUserService.this.initDirectories();
        }

        @Override // com.oculus.vrguardianservice.IVrGuardianUserController
        public String loadUserFile(String fileName) {
            try {
                String fullPath = VrGuardianUserService.this.getGuardianFilesDir() + fileName;
                Log.d(VrGuardianUserService.TAG, "VrGuardianService: loadUserFile() " + fullPath);
                DataInputStream dis = new DataInputStream(new FileInputStream(fullPath));
                byte[] buffer = new byte[((int) new File(fullPath).length())];
                dis.readFully(buffer);
                return new String(buffer, StandardCharsets.UTF_8);
            } catch (IOException ioe) {
                Log.w(VrGuardianUserService.TAG, "loadUserFile() failed: " + ioe);
                return null;
            }
        }

        @Override // com.oculus.vrguardianservice.IVrGuardianUserController
        public boolean saveUserFile(String fileName, String content) {
            try {
                String fullPath = VrGuardianUserService.this.getGuardianFilesDir() + fileName;
                Log.d(VrGuardianUserService.TAG, "VrGuardianService: saveUserFile() " + fullPath);
                FileWriter fw = new FileWriter(fullPath);
                fw.write(content);
                fw.close();
                return true;
            } catch (IOException ioe) {
                Log.w(VrGuardianUserService.TAG, "saveUserFile() failed: " + ioe);
                return false;
            }
        }

        @Override // com.oculus.vrguardianservice.IVrGuardianUserController
        public void sendUserBroadcast(Intent intent) {
            Log.d(VrGuardianUserService.TAG, "sendUserBroadcast() : " + intent);
            VrGuardianUserService.this.sendBroadcast(intent);
        }

        @Override // com.oculus.vrguardianservice.IVrGuardianUserController
        public void startUserActivity(Intent intent) {
            Log.d(VrGuardianUserService.TAG, "startUserActivity() : " + intent);
            VrGuardianUserService.this.startActivity(intent);
        }
    };

    public void onCreate() {
        connectToGuardianService();
        Log.d(TAG, "onCreate - register user listener");
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.USER_BACKGROUND");
        filter.addAction("android.intent.action.USER_FOREGROUND");
        this.receiver = new UserBroadcastReceiver();
        registerReceiver(this.receiver, filter);
        IntentFilter jsonCmdFilter = new IntentFilter();
        jsonCmdFilter.addAction("com.oculus.vrguardianservice.JsonCmdUserBroadcast");
        this.jsonCmdReceiver = new JsonCmdUserBroadcastReceiver();
        registerReceiver(this.jsonCmdReceiver, jsonCmdFilter);
        initDirectories();
        super.onCreate();
    }

    private class UserBroadcastReceiver extends BroadcastReceiver {
        public UserBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            int user = VrGuardianUserService.getUser(intent);
            Log.d(VrGuardianUserService.TAG, "onReceive " + intent.getAction() + " user = " + user);
            if (user >= 0) {
                if ("android.intent.action.USER_FOREGROUND".equals(intent.getAction())) {
                    VrGuardianUserService.this.setCurrentUser(user, true);
                } else {
                    VrGuardianUserService.this.setCurrentUser(user, false);
                }
            }
        }
    }

    private class JsonCmdUserBroadcastReceiver extends BroadcastReceiver {
        public JsonCmdUserBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            String cmdString = intent.getStringExtra("cmd");
            Log.d(VrGuardianUserService.TAG, "JsonCmdUserBroadcastReceiver. Cmd: " + cmdString);
            VrGuardianUserService.this.processJsonCmd(cmdString);
        }
    }

    /* access modifiers changed from: private */
    public static int getUser(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            Log.e(TAG, "onReceive missing extras (no user)");
            return -1;
        }
        int user = extras.getInt("android.intent.extra.user_handle", -1);
        if (user >= 0) {
            return user;
        }
        Log.e(TAG, "onReceive missing extras (no user)");
        return -1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String getGuardianFilesDir() {
        String ret = getFilesDir().getAbsolutePath() + "/guardian/";
        Log.d(TAG, "getGuardianFilesDir() " + ret);
        return ret;
    }

    private boolean createDirIfNotExists(String path) {
        File file = new File(path);
        if (file.exists() || file.mkdirs()) {
            return true;
        }
        return false;
    }

    public boolean initDirectories() {
        return createDirIfNotExists(getGuardianFilesDir());
    }

    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        disconnect();
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand" + intent);
        if (intent == null) {
            Log.d(TAG, "onStartCommand - intent is null ");
            return 1;
        }
        if (BOOT.equals(intent.getAction()) || takeRenderingClientHint(intent)) {
            int user = getUser(intent);
            Log.d(TAG, "onStartCommand " + intent.getAction() + " user = " + user);
            if (user >= 0) {
                setCurrentUser(user, true);
            }
        }
        return 1;
    }

    private boolean takeRenderingClientHint(Intent intent) {
        return !this.hadValidUser && FOREGROUND_CLIENT.equals(intent.getAction());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void processJsonCmd(String cmdString) {
        GuardianServiceConnection guardianServiceConnection2 = this.guardianServiceConnection;
        if (guardianServiceConnection2 == null) {
            Log.w(TAG, "error calling guardian service: guardianServiceConnection is null");
        } else if (guardianServiceConnection2.vrGuardianService == null) {
            Log.w(TAG, "error calling guardian service: guardianServiceConnection.vrGuardianService is null");
        } else {
            try {
                this.guardianServiceConnection.vrGuardianService.processJsonCmd(cmdString);
            } catch (RemoteException re) {
                Log.e(TAG, "error calling guardian service", re);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCurrentUser(int user, boolean isForeground) {
        GuardianServiceConnection guardianServiceConnection2 = this.guardianServiceConnection;
        if (guardianServiceConnection2 == null) {
            Log.w(TAG, "error calling guardian service: guardianServiceConnection is null");
        } else if (guardianServiceConnection2.vrGuardianService == null) {
            Log.w(TAG, "error calling guardian service: guardianServiceConnection.vrGuardianService is null");
        } else {
            try {
                this.guardianServiceConnection.vrGuardianService.setCurrentUser(user, isForeground);
                this.hadValidUser = true;
            } catch (RemoteException re) {
                Log.e(TAG, "error calling guardian service", re);
            }
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    private void connectToGuardianService() {
        if (this.guardianServiceConnection == null) {
            Intent serviceIntent = new Intent();
            serviceIntent.setComponent(new ComponentName(getPackageName(), "com.oculus.vrguardianservice.VrGuardianService"));
            this.guardianServiceConnection = new GuardianServiceConnection();
            bindService(serviceIntent, this.guardianServiceConnection, 0);
        }
    }

    private void disconnect() {
        GuardianServiceConnection guardianServiceConnection2 = this.guardianServiceConnection;
        if (guardianServiceConnection2 != null) {
            unbindService(guardianServiceConnection2);
            this.guardianServiceConnection = null;
        }
    }

    /* access modifiers changed from: package-private */
    public class GuardianServiceConnection implements ServiceConnection {
        IVrGuardianService vrGuardianService;

        GuardianServiceConnection() {
        }

        public void onServiceConnected(ComponentName name, IBinder binder) {
            Log.i(VrGuardianUserService.TAG, "GuardianServiceConnection: Service connected." + binder);
            this.vrGuardianService = IVrGuardianService.Stub.asInterface(binder);
            try {
                this.vrGuardianService.connectUserController(IVrGuardianUserController.Stub.asInterface(VrGuardianUserService.this.userController));
            } catch (RemoteException re) {
                Log.d(VrGuardianUserService.TAG, "Error calling guardian service", re);
            }
        }

        public void onServiceDisconnected(ComponentName name) {
            Log.i(VrGuardianUserService.TAG, "VrGuardianClient: Service disconnected.");
            this.vrGuardianService = null;
        }
    }
}
