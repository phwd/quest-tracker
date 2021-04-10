package com.oculus.modules;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.oculus.modules.codegen.SystemActivitiesModule;
import com.oculus.secure.trustedapp.CallerInfoHelper;
import org.json.JSONException;
import org.json.JSONObject;

public class SystemActivitiesModuleImpl extends SystemActivitiesModule {
    private static final String INTENT_EXIT_TO_HOME = "exitToHome";
    private static final String INTENT_REORIENT = "reorient";
    private static final String INTENT_RETURN_TO_LAUNCHER = "returnToLauncher";
    private static final int PLATFORM_UI_VERSION = 4;
    private static final String SYSTEM_ACTIVITY_INTENT = "com.oculus.system_activity";
    private static final String TAG = SystemActivitiesModuleImpl.class.getSimpleName();
    private Context mContext = null;

    public SystemActivitiesModuleImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.oculus.modules.codegen.SystemActivitiesModule
    public void sendIntentImpl(String command) {
        Log.d(TAG, "SystemActivitiesModule.sendIntent( " + command + " )");
        sendIntentToPkg(command, "com.oculus.vrshell");
    }

    @Override // com.oculus.modules.codegen.SystemActivitiesModule
    public void sendIntentToPkgImpl(String command, String packageName) {
        Log.d(TAG, "SystemActivitiesModule.sendIntentToPkg(" + command + ", " + packageName + ")");
        Intent intent = new Intent(SYSTEM_ACTIVITY_INTENT);
        intent.setPackage(packageName);
        intent.putExtra("intent_cmd", createIntentCmd(command, packageName));
        intent.putExtra("intent_pkg", this.mContext.getPackageName());
        CallerInfoHelper.attachCallerInfo(intent, this.mContext, TAG + ":sendIntentToPkg()");
        this.mContext.sendBroadcast(intent);
    }

    private String createIntentCmd(String command, String toPackage) {
        try {
            JSONObject cmd = new JSONObject();
            cmd.put("Command", command);
            cmd.put("PlatformUIVersion", 4);
            cmd.put("ToPackage", toPackage);
            return cmd.toString();
        } catch (JSONException e) {
            Log.e(TAG, "JSON Error: " + e.getMessage());
            return "";
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.SystemActivitiesModule
    public String marshallJSConstantIntentExitToHome() {
        return INTENT_EXIT_TO_HOME;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.SystemActivitiesModule
    public String marshallJSConstantIntentReorient() {
        return INTENT_REORIENT;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.SystemActivitiesModule
    public String marshallJSConstantIntentReturnToLauncher() {
        return INTENT_RETURN_TO_LAUNCHER;
    }
}
