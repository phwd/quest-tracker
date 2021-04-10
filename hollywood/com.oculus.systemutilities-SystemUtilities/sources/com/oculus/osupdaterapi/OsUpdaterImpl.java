package com.oculus.osupdaterapi;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.ResultReceiver;
import android.util.Log;
import com.oculus.osupdaterapi.OsUpdater;

/* access modifiers changed from: package-private */
public final class OsUpdaterImpl {
    private static final String TAG = OsUpdaterImpl.class.getSimpleName();
    private final Context mContext;

    OsUpdaterImpl(Context context) {
        this.mContext = context;
    }

    static OsUpdater.UpdaterState getUpdaterState(String stateString) {
        if (stateString == null) {
            return OsUpdater.UpdaterState.STATE_UNKNOWN;
        }
        char c = 65535;
        switch (stateString.hashCode()) {
            case -1977205868:
                if (stateString.equals("ota_update_in_progress")) {
                    c = 2;
                    break;
                }
                break;
            case -1917034805:
                if (stateString.equals("Check for updates stopped due to no wifi connection")) {
                    c = 6;
                    break;
                }
                break;
            case -1665620570:
                if (stateString.equals("state_not_allowed_by_system")) {
                    c = 4;
                    break;
                }
                break;
            case -252387059:
                if (stateString.equals("waiting_for_reboot")) {
                    c = 1;
                    break;
                }
                break;
            case 475616557:
                if (stateString.equals("state_ota_disabled")) {
                    c = 3;
                    break;
                }
                break;
            case 1772672651:
                if (stateString.equals("Device is not configured for AB updates")) {
                    c = 5;
                    break;
                }
                break;
            case 1969912373:
                if (stateString.equals("state_ready_to_check_for_ota")) {
                    c = 0;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return OsUpdater.UpdaterState.STATE_READY_TO_CHECK_FOR_OTA;
            case 1:
                return OsUpdater.UpdaterState.STATE_WAITING_FOR_REBOOT;
            case 2:
                return OsUpdater.UpdaterState.STATE_UPDATE_IN_PROGRESS;
            case 3:
                return OsUpdater.UpdaterState.STATE_OTA_DISABLED_BY_USER;
            case 4:
                return OsUpdater.UpdaterState.STATE_NOT_ALLOWED_BY_SYSTEM;
            case 5:
                return OsUpdater.UpdaterState.STATE_DEVICE_NOT_CONFIGURED_FOR_AB_UPDATES;
            case 6:
                return OsUpdater.UpdaterState.STATE_WIFI_DISABLED;
            default:
                return OsUpdater.UpdaterState.STATE_UNKNOWN;
        }
    }

    /* access modifiers changed from: package-private */
    public void checkIfUpdatesAreAvailable(boolean isFullUpdate, Handler handler, final OsUpdater.UpdaterOtaAvailabilityCallback otaAvailabilityCallback) {
        if (otaAvailabilityCallback == null) {
            Log.e(TAG, "The UpdateCheckResponseHandler instance is null. Aborting");
            return;
        }
        createAndSendIntent(this.mContext, "check_ota_availability", getReceiverForSending(new ResultReceiver(handler) {
            /* class com.oculus.osupdaterapi.OsUpdaterImpl.AnonymousClass2 */

            /* access modifiers changed from: protected */
            public void onReceiveResult(int resultCode, Bundle resultBundle) {
                otaAvailabilityCallback.onReceive(new UpdaterOtaAvailability(resultCode, resultBundle));
            }
        }), isFullUpdate);
    }

    /* access modifiers changed from: private */
    public class OtaUpdateStatusReceiver extends BroadcastReceiver {
        private OsUpdater.OtaUpdateProgressCallback mProgressCallback;

        public void onReceive(Context context, Intent intent) {
            if (this.mProgressCallback != null) {
                updateOtaDownloadProgress(intent);
                return;
            }
            Log.e(OsUpdaterImpl.TAG, "Null ProgressCallback instance provided. Unregistering the Broadcast Receiver");
            OsUpdaterImpl.this.mContext.unregisterReceiver(this);
        }

        OtaUpdateStatusReceiver(OsUpdater.OtaUpdateProgressCallback progressCallback) {
            this.mProgressCallback = progressCallback;
        }

        private void updateOtaDownloadProgress(Intent intent) {
            String status = intent.getStringExtra("state");
            char c = 65535;
            switch (status.hashCode()) {
                case -1510287249:
                    if (status.equals("checking_for_updates")) {
                        c = 0;
                        break;
                    }
                    break;
                case -252387059:
                    if (status.equals("waiting_for_reboot")) {
                        c = 3;
                        break;
                    }
                    break;
                case -132584012:
                    if (status.equals("applying_update")) {
                        c = 2;
                        break;
                    }
                    break;
                case 1160636847:
                    if (status.equals("error_while_applying_update")) {
                        c = 4;
                        break;
                    }
                    break;
                case 1567873686:
                    if (status.equals("no_updates_available")) {
                        c = 1;
                        break;
                    }
                    break;
                case 1645945172:
                    if (status.equals("error_while_checking_for_updates")) {
                        c = 5;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                default:
                    return;
                case 1:
                    OsUpdaterImpl.this.mContext.unregisterReceiver(this);
                    return;
                case 2:
                    this.mProgressCallback.onProgress(intent.getFloatExtra("progress", 0.0f));
                    return;
                case 3:
                    this.mProgressCallback.onProgress(1.0f);
                    this.mProgressCallback.onComplete();
                    OsUpdaterImpl.this.mContext.unregisterReceiver(this);
                    return;
                case 4:
                case 5:
                    String error = intent.getStringExtra("error_details");
                    StringBuilder append = new StringBuilder().append("Failed to download updates. State: ").append(status).append(". Error Details: ");
                    if (error == null) {
                        error = "";
                    }
                    this.mProgressCallback.onError(append.append(error).toString());
                    OsUpdaterImpl.this.mContext.unregisterReceiver(this);
                    return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void downloadUpdateIfAvailable(boolean isFullUpdate, Handler handler, final OsUpdater.UpdaterOtaAvailabilityCallback otaAvailabilityCallback, OsUpdater.OtaUpdateProgressCallback updateProgressCallback) {
        if (otaAvailabilityCallback == null) {
            Log.e(TAG, "The UpdateCheckResponseHandler instance is null. Aborting");
            return;
        }
        if (updateProgressCallback != null) {
            this.mContext.registerReceiver(new OtaUpdateStatusReceiver(updateProgressCallback), new IntentFilter("com.oculus.updater.STATE_NOTIFICATION"));
        }
        createAndSendIntent(this.mContext, isFullUpdate ? "ext_check_updates_full" : "ext_check_updates", getReceiverForSending(new ResultReceiver(handler) {
            /* class com.oculus.osupdaterapi.OsUpdaterImpl.AnonymousClass3 */

            /* access modifiers changed from: protected */
            public void onReceiveResult(int resultCode, Bundle resultBundle) {
                otaAvailabilityCallback.onReceive(new UpdaterOtaAvailability(resultCode, resultBundle));
            }
        }), isFullUpdate);
    }

    /* access modifiers changed from: package-private */
    public void rebootAndApplyUpdate() {
        createAndSendIntent(this.mContext, "ext_boot_into_update", null, false);
    }

    static ResultReceiver getReceiverForSending(ResultReceiver actualReceiver) {
        Parcel parcel = Parcel.obtain();
        actualReceiver.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        ResultReceiver receiverForSending = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
        parcel.recycle();
        return receiverForSending;
    }

    static void createAndSendIntent(Context context, String action, ResultReceiver resultReceiver, boolean isFullUpdate) {
        Intent intent = new Intent();
        intent.setAction(action);
        intent.setComponent(new ComponentName("com.oculus.updater", "com.oculus.updater.core.os.OSUpdateService"));
        if (resultReceiver != null) {
            intent.putExtra("result_receiver", resultReceiver);
        }
        intent.putExtra("full_update", isFullUpdate);
        context.startService(intent);
    }
}
