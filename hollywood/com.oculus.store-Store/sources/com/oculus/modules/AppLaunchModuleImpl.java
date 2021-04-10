package com.oculus.modules;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.util.Log;
import androidx.annotation.NonNull;
import com.oculus.modules.codegen.AppLaunchModule;
import com.oculus.modules.codegen.Resolver;

public class AppLaunchModuleImpl extends AppLaunchModule {
    private static final String ANDROID_MICROPHONE_PERMISSION = "android.permission.RECORD_AUDIO";
    private static final String BROADCAST_TYPE_LAUNCH_INTENT = "com.oculus.launch_intent_changed";
    private static final String INTENT_KEY_EXTRAS = "intent_cmd";
    private static final String INVITE_ACTION = "com.oculus.invite_accepted";
    private static final String ROOM_ID_KEY = "ovr_room_id";
    private static final String SHARED_PREFERENCES_MICROPHONE_PERMISSION_KEY = "AppLaunchModuleImplMicrophonePermission";
    private static final String TAG = AppLaunchModuleImpl.class.getSimpleName();
    private final Context mContext;

    public AppLaunchModuleImpl(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AppLaunchModule
    public void launchComponentImpl(String componentNameFlattened) {
        ComponentName componentName = ComponentName.unflattenFromString(componentNameFlattened);
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.addFlags(65536);
        intent.addFlags(268435456);
        this.mContext.startActivity(intent);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AppLaunchModule
    public void joinRoomImpl(String packageName, String roomId) {
        Log.d(TAG, "joinRoom( " + packageName + ", " + roomId + " )");
        Intent intent = this.mContext.getPackageManager().getLaunchIntentForPackage(packageName);
        if (intent != null) {
            intent.putExtra(ROOM_ID_KEY, roomId);
            this.mContext.startActivity(intent);
            Intent roomIntent = new Intent(INVITE_ACTION);
            roomIntent.setPackage(packageName);
            roomIntent.putExtra(ROOM_ID_KEY, roomId);
            this.mContext.sendBroadcast(roomIntent);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AppLaunchModule
    public void broadcastAppLaunchImpl(String packageName, String intentCmd) {
        Intent broadcastIntent = new Intent(BROADCAST_TYPE_LAUNCH_INTENT);
        broadcastIntent.setPackage(packageName);
        broadcastIntent.putExtra(INTENT_KEY_EXTRAS, intentCmd);
        this.mContext.sendBroadcast(broadcastIntent);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AppLaunchModule
    public void requestsMicrophoneImpl(String packageName, Resolver<Boolean> resolver) {
        SharedPreferences sharedPrefs = this.mContext.getSharedPreferences(SHARED_PREFERENCES_MICROPHONE_PERMISSION_KEY, 0);
        int toastCount = sharedPrefs.getInt(SHARED_PREFERENCES_MICROPHONE_PERMISSION_KEY, 0);
        if (toastCount >= 3) {
            resolver.resolve(false);
            return;
        }
        sharedPrefs.edit().putInt(SHARED_PREFERENCES_MICROPHONE_PERMISSION_KEY, toastCount + 1).apply();
        resolver.resolve(Boolean.valueOf(requestsPermission(packageName, ANDROID_MICROPHONE_PERMISSION)));
    }

    private boolean requestsPermission(String packageName, @NonNull String permission) {
        try {
            Context appContext = this.mContext.createPackageContext(packageName, 2);
            PackageInfo packageInfo = appContext.getPackageManager().getPackageInfo(appContext.getPackageName(), 4096);
            if (packageInfo.requestedPermissions == null) {
                return false;
            }
            for (String p : packageInfo.requestedPermissions) {
                if (p.equals(permission)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            Log.d(TAG, "Exception caught while checking permissions: " + e);
            return false;
        }
    }
}
