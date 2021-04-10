package com.oculus.modules;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import com.oculus.modules.codegen.AppLaunchModule;
import com.oculus.modules.codegen.Resolver;

public class AppLaunchModuleImpl extends AppLaunchModule {
    public static final String ANDROID_MICROPHONE_PERMISSION = "android.permission.RECORD_AUDIO";
    public static final String BROADCAST_TYPE_LAUNCH_INTENT = "com.oculus.launch_intent_changed";
    public static final String INTENT_KEY_EXTRAS = "intent_cmd";
    public static final String INVITE_ACTION = "com.oculus.invite_accepted";
    public static final String ROOM_ID_KEY = "ovr_room_id";
    public static final String SHARED_PREFERENCES_MICROPHONE_PERMISSION_KEY = "AppLaunchModuleImplMicrophonePermission";
    public static final String TAG = "AppLaunchModuleImpl";
    public final Context mContext;

    private boolean requestsPermission(String str, @NonNull String str2) {
        try {
            Context createPackageContext = this.mContext.createPackageContext(str, 2);
            String[] strArr = createPackageContext.getPackageManager().getPackageInfo(createPackageContext.getPackageName(), 4096).requestedPermissions;
            if (strArr != null) {
                for (String str3 : strArr) {
                    if (str3.equals(str2)) {
                        return true;
                    }
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    @Override // com.oculus.modules.codegen.AppLaunchModule
    public void broadcastAppLaunchImpl(String str, String str2) {
        Intent intent = new Intent(BROADCAST_TYPE_LAUNCH_INTENT);
        intent.setPackage(str);
        intent.putExtra("intent_cmd", str2);
        this.mContext.sendBroadcast(intent);
    }

    @Override // com.oculus.modules.codegen.AppLaunchModule
    public void joinRoomImpl(String str, String str2) {
        Intent launchIntentForPackage = this.mContext.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage != null) {
            launchIntentForPackage.putExtra(ROOM_ID_KEY, str2);
            this.mContext.startActivity(launchIntentForPackage);
            Intent intent = new Intent(INVITE_ACTION);
            intent.setPackage(str);
            intent.putExtra(ROOM_ID_KEY, str2);
            this.mContext.sendBroadcast(intent);
        }
    }

    @Override // com.oculus.modules.codegen.AppLaunchModule
    public void requestsMicrophoneImpl(String str, Resolver<Boolean> resolver) {
        boolean valueOf;
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences(SHARED_PREFERENCES_MICROPHONE_PERMISSION_KEY, 0);
        int i = sharedPreferences.getInt(SHARED_PREFERENCES_MICROPHONE_PERMISSION_KEY, 0);
        if (i >= 3) {
            valueOf = false;
        } else {
            sharedPreferences.edit().putInt(SHARED_PREFERENCES_MICROPHONE_PERMISSION_KEY, i + 1).apply();
            valueOf = Boolean.valueOf(requestsPermission(str, ANDROID_MICROPHONE_PERMISSION));
        }
        resolver.resolve(valueOf);
    }

    public AppLaunchModuleImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.oculus.modules.codegen.AppLaunchModule
    public void launchComponentImpl(String str) {
        ComponentName unflattenFromString = ComponentName.unflattenFromString(str);
        Intent intent = new Intent();
        intent.setComponent(unflattenFromString);
        intent.addFlags(65536);
        intent.addFlags(268435456);
        this.mContext.startActivity(intent);
    }
}
