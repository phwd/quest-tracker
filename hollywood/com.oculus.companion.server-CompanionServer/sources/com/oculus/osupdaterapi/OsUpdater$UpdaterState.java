package com.oculus.osupdaterapi;

public enum OsUpdater$UpdaterState {
    STATE_UNKNOWN,
    STATE_READY_TO_CHECK_FOR_OTA,
    STATE_WAITING_FOR_REBOOT,
    STATE_UPDATE_IN_PROGRESS,
    STATE_OTA_DISABLED_BY_USER,
    STATE_NOT_ALLOWED_BY_SYSTEM,
    STATE_DEVICE_NOT_CONFIGURED_FOR_AB_UPDATES,
    STATE_WIFI_DISABLED
}
