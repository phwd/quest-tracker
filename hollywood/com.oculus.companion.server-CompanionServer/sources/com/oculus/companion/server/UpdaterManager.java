package com.oculus.companion.server;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.os.UserHandle;
import android.util.Log;

public class UpdaterManager {
    public static boolean attemptUpdate(Context context, boolean z) {
        if (CompanionServer.DEBUG) {
            Log.d("CompanionUpdaterManager", "Sending OSUpdater attempt update intent");
        }
        if (z) {
            return sendSimpleUpdaterIntent(context, "ext_check_updates_full");
        }
        return sendSimpleUpdaterIntent(context, "ext_check_updates");
    }

    public static boolean checkUpdateAvailability(Context context, boolean z, final CompanionServer companionServer, final int i) {
        if (CompanionServer.DEBUG) {
            Log.d("CompanionUpdaterManager", "Sending OSUpdater check-availability intent. full update: " + z);
        }
        return sendReceiverUpdaterIntent(context, "check_ota_availability", CompanionService.getCrossPackageReceiver(new ResultReceiver(null) {
            /* class com.oculus.companion.server.UpdaterManager.AnonymousClass1 */

            /* access modifiers changed from: protected */
            public void onReceiveResult(int i, Bundle bundle) {
                String string = bundle != null ? bundle.getString("msg") : null;
                Log.i("CompanionUpdaterManager", "OSUpdater Response: " + string);
                CompanionServer companionServer = companionServer;
                if (companionServer != null) {
                    companionServer.response_OTA_CHECK_AVAILABILITY(i, i == 0, string);
                }
            }
        }), z);
    }

    private static boolean sendSimpleUpdaterIntent(Context context, String str) {
        return sendReceiverUpdaterIntent(context, str, null, false);
    }

    private static boolean sendReceiverUpdaterIntent(Context context, String str, ResultReceiver resultReceiver, boolean z) {
        try {
            Log.i("CompanionUpdaterManager", "Sending OSUpdater intent: " + str);
            ComponentName componentName = new ComponentName("com.oculus.updater", "com.oculus.updater.core.os.OSUpdateService");
            Intent intent = new Intent();
            intent.setAction(str);
            intent.setComponent(componentName);
            if (resultReceiver != null) {
                intent.putExtra("result_receiver", resultReceiver);
                intent.putExtra("full_update", z);
                Log.i("CompanionUpdaterManager", "Intent sent with isFullUpdate: " + z);
            }
            context.startServiceAsUser(intent, UserHandle.SYSTEM);
            return true;
        } catch (IllegalStateException e) {
            Log.e("CompanionUpdaterManager", "IllegalStateException: sendUpdaterIntent: " + str, e);
            return false;
        } catch (SecurityException e2) {
            Log.e("CompanionUpdaterManager", "SecurityException: sendUpdaterIntent: " + str, e2);
            return false;
        }
    }
}
