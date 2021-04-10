package com.oculus.vrshell.home;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class PaymentFlowProvider {
    public static final String TAG = PaymentFlowProvider.class.getName();

    public static int returnIAPResult(boolean success, String response, String componentNameFlatten, String platformRequestId) {
        try {
            Context ctx = HomeApplication.instance.getApplicationContext();
            String returnPackage = ComponentName.unflattenFromString(componentNameFlatten).getPackageName();
            Intent intent = new Intent("com.oculus.iap_shell_result");
            try {
                intent.putExtra("platform_request_id", Long.parseLong(platformRequestId));
            } catch (NumberFormatException e) {
                Log.e(TAG, "StoreIAP: Cannot parse platformRequestId " + platformRequestId + ", returning IAP result without it.", e);
            }
            intent.putExtra("iap_shell_json", response);
            intent.putExtra("is_successful", success);
            intent.putExtra("return_package", returnPackage);
            intent.setPackage(returnPackage);
            ctx.sendBroadcast(intent);
            intent.setPackage("com.oculus.horizon");
            ctx.sendBroadcast(intent);
            return 0;
        } catch (Exception e2) {
            Log.e(TAG, "StoreIAP: Failed to return IAP result to the calling app.", e2);
            return -1;
        }
    }
}
