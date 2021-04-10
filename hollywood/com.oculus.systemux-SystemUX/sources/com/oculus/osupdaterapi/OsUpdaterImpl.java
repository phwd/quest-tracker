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
import com.oculus.updater.core.constants.OSUpdaterConstants;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
public final class OsUpdaterImpl {
    private static final String TAG = "OsUpdaterImpl";
    private final Context mContext;

    OsUpdaterImpl(Context context) {
        this.mContext = context;
    }

    static OsUpdater.UpdaterState getUpdaterState(String str) {
        if (str == null) {
            return OsUpdater.UpdaterState.STATE_UNKNOWN;
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1977205868:
                if (str.equals(OSUpdaterConstants.STATE_UPDATE_IN_PROGRESS)) {
                    c = 2;
                    break;
                }
                break;
            case -1917034805:
                if (str.equals(OSUpdaterConstants.STATE_WIFI_DISABLED)) {
                    c = 6;
                    break;
                }
                break;
            case -1665620570:
                if (str.equals(OSUpdaterConstants.STATE_NOT_ALLOWED_BY_SYSTEM)) {
                    c = 4;
                    break;
                }
                break;
            case -252387059:
                if (str.equals(OSUpdaterConstants.STATE_WAITING_FOR_REBOOT)) {
                    c = 1;
                    break;
                }
                break;
            case 475616557:
                if (str.equals(OSUpdaterConstants.STATE_OTA_DISABLED_BY_USER)) {
                    c = 3;
                    break;
                }
                break;
            case 1772672651:
                if (str.equals(OSUpdaterConstants.STATE_DEVICE_NOT_CONFIGURED_FOR_AB_UPDATES)) {
                    c = 5;
                    break;
                }
                break;
            case 1969912373:
                if (str.equals(OSUpdaterConstants.STATE_READY_TO_CHECK_FOR_OTA)) {
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
    public void getUpdaterStatus(final OsUpdater.UpdaterStatusCallback updaterStatusCallback, Handler handler) {
        if (updaterStatusCallback == null) {
            Log.e(TAG, "The UpdaterStatusCallback instance is null. Aborting");
            return;
        }
        createAndSendIntent(this.mContext, OSUpdaterConstants.ACTION_UPDATER_GET_CURRENT_STATE, getReceiverForSending(new ResultReceiver(handler) {
            /* class com.oculus.osupdaterapi.OsUpdaterImpl.AnonymousClass1 */

            /* access modifiers changed from: protected */
            public void onReceiveResult(int i, Bundle bundle) {
                updaterStatusCallback.onReceive(new UpdaterStatus(bundle));
            }
        }), false);
    }

    /* access modifiers changed from: package-private */
    public void checkIfUpdatesAreAvailable(boolean z, Handler handler, final OsUpdater.UpdaterOtaAvailabilityCallback updaterOtaAvailabilityCallback) {
        if (updaterOtaAvailabilityCallback == null) {
            Log.e(TAG, "The UpdateCheckResponseHandler instance is null. Aborting");
            return;
        }
        createAndSendIntent(this.mContext, OSUpdaterConstants.ACTION_CHECK_OTA_AVAILABILITY, getReceiverForSending(new ResultReceiver(handler) {
            /* class com.oculus.osupdaterapi.OsUpdaterImpl.AnonymousClass2 */

            /* access modifiers changed from: protected */
            public void onReceiveResult(int i, Bundle bundle) {
                updaterOtaAvailabilityCallback.onReceive(new UpdaterOtaAvailability(i, bundle));
            }
        }), z);
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

        OtaUpdateStatusReceiver(OsUpdater.OtaUpdateProgressCallback otaUpdateProgressCallback) {
            this.mProgressCallback = otaUpdateProgressCallback;
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        private void updateOtaDownloadProgress(Intent intent) {
            char c;
            String stringExtra = intent.getStringExtra("state");
            switch (stringExtra.hashCode()) {
                case -1510287249:
                    if (stringExtra.equals(OSUpdaterConstants.KEY_CHECKING_FOR_UPDATES)) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -252387059:
                    if (stringExtra.equals(OSUpdaterConstants.STATE_WAITING_FOR_REBOOT)) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case -132584012:
                    if (stringExtra.equals(OSUpdaterConstants.KEY_APPLYING_UPDATE)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 1160636847:
                    if (stringExtra.equals(OSUpdaterConstants.KEY_ERROR_WHILE_APPLYING_UPDATE)) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 1567873686:
                    if (stringExtra.equals(OSUpdaterConstants.KEY_NO_UPDATES_AVAILABLE)) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 1645945172:
                    if (stringExtra.equals(OSUpdaterConstants.KEY_ERROR_WHILE_CHECKING_FOR_UPDATES)) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c == 0) {
                return;
            }
            if (c == 1) {
                OsUpdaterImpl.this.mContext.unregisterReceiver(this);
            } else if (c == 2) {
                this.mProgressCallback.onProgress(intent.getFloatExtra("progress", 0.0f));
            } else if (c == 3) {
                this.mProgressCallback.onProgress(1.0f);
                this.mProgressCallback.onComplete();
                OsUpdaterImpl.this.mContext.unregisterReceiver(this);
            } else if (c == 4 || c == 5) {
                String stringExtra2 = intent.getStringExtra(OSUpdaterConstants.EXTRA_ERROR_DETAILS_ID);
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to download updates. State: ");
                sb.append(stringExtra);
                sb.append(". Error Details: ");
                if (stringExtra2 == null) {
                    stringExtra2 = "";
                }
                sb.append(stringExtra2);
                this.mProgressCallback.onError(sb.toString());
                OsUpdaterImpl.this.mContext.unregisterReceiver(this);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void downloadUpdateIfAvailable(boolean z, Handler handler, final OsUpdater.UpdaterOtaAvailabilityCallback updaterOtaAvailabilityCallback, OsUpdater.OtaUpdateProgressCallback otaUpdateProgressCallback) {
        if (updaterOtaAvailabilityCallback == null) {
            Log.e(TAG, "The UpdateCheckResponseHandler instance is null. Aborting");
            return;
        }
        if (otaUpdateProgressCallback != null) {
            this.mContext.registerReceiver(new OtaUpdateStatusReceiver(otaUpdateProgressCallback), new IntentFilter(OSUpdaterConstants.ACTION_UPDATE_NOTIFICATION));
        }
        createAndSendIntent(this.mContext, z ? OSUpdaterConstants.ACTION_EXT_CHECK_UPDATES_FULL : OSUpdaterConstants.ACTION_EXT_CHECK_UPDATES, getReceiverForSending(new ResultReceiver(handler) {
            /* class com.oculus.osupdaterapi.OsUpdaterImpl.AnonymousClass3 */

            /* access modifiers changed from: protected */
            public void onReceiveResult(int i, Bundle bundle) {
                updaterOtaAvailabilityCallback.onReceive(new UpdaterOtaAvailability(i, bundle));
            }
        }), z);
    }

    /* access modifiers changed from: package-private */
    public void rebootAndApplyUpdate() {
        createAndSendIntent(this.mContext, OSUpdaterConstants.ACTION_EXT_BOOT_INTO_UPDATE, null, false);
    }

    static ResultReceiver getReceiverForSending(ResultReceiver resultReceiver) {
        Parcel obtain = Parcel.obtain();
        resultReceiver.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        ResultReceiver resultReceiver2 = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return resultReceiver2;
    }

    static void createAndSendIntent(Context context, String str, @Nullable ResultReceiver resultReceiver, boolean z) {
        Intent intent = new Intent();
        intent.setAction(str);
        intent.setComponent(new ComponentName("com.oculus.updater", OSUpdaterConstants.UPDATE_SERVICE));
        if (resultReceiver != null) {
            intent.putExtra("result_receiver", resultReceiver);
        }
        intent.putExtra(OSUpdaterConstants.EXTRA_FULL_UPDATE, z);
        context.startService(intent);
    }
}
