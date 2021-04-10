package com.oculus.osupdaterapi;

import android.content.Context;
import android.os.Handler;

public final class OsUpdater {
    private final OsUpdaterImpl mOsUpdaterImpl;

    public interface OtaUpdateProgressCallback {
        void onComplete();

        void onError(String str);

        void onProgress(float f);
    }

    public interface UpdaterOtaAvailabilityCallback {
        void onReceive(UpdaterOtaAvailability updaterOtaAvailability);
    }

    public enum UpdaterState {
        STATE_UNKNOWN,
        STATE_READY_TO_CHECK_FOR_OTA,
        STATE_WAITING_FOR_REBOOT,
        STATE_UPDATE_IN_PROGRESS,
        STATE_OTA_DISABLED_BY_USER,
        STATE_NOT_ALLOWED_BY_SYSTEM,
        STATE_DEVICE_NOT_CONFIGURED_FOR_AB_UPDATES,
        STATE_WIFI_DISABLED
    }

    public interface UpdaterStatusCallback {
        void onReceive(UpdaterStatus updaterStatus);
    }

    public void setUpdaterEnabled(boolean z) {
    }

    public OsUpdater(Context context) {
        if (context != null) {
            this.mOsUpdaterImpl = new OsUpdaterImpl(context);
            return;
        }
        throw new NullPointerException("Received Null Context");
    }

    public void getUpdaterStatus(UpdaterStatusCallback updaterStatusCallback, Handler handler) {
        this.mOsUpdaterImpl.getUpdaterStatus(updaterStatusCallback, handler);
    }

    public void checkIfUpdatesAreAvailable(boolean z, Handler handler, UpdaterOtaAvailabilityCallback updaterOtaAvailabilityCallback) {
        this.mOsUpdaterImpl.checkIfUpdatesAreAvailable(z, handler, updaterOtaAvailabilityCallback);
    }

    public void downloadUpdateIfAvailable(boolean z, Handler handler, UpdaterOtaAvailabilityCallback updaterOtaAvailabilityCallback, OtaUpdateProgressCallback otaUpdateProgressCallback) {
        this.mOsUpdaterImpl.downloadUpdateIfAvailable(z, handler, updaterOtaAvailabilityCallback, otaUpdateProgressCallback);
    }

    public void rebootAndApplyUpdate() {
        this.mOsUpdaterImpl.rebootAndApplyUpdate();
    }
}
