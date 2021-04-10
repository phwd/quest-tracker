package com.oculus.osupdaterapi;

import android.os.Bundle;
import com.oculus.osupdaterapi.OsUpdater;

public final class UpdaterStatus {
    public final Long mCurrentOSVersion;
    public final String mErrorMessage;
    public final Long mTimeSinceLastUpdateCheckMSec;
    public final Long mTimeSinceLastUpdateMSec;
    public final OsUpdater.UpdaterState mUpdaterState;

    UpdaterStatus(Bundle bundle) {
        if (bundle == null) {
            this.mUpdaterState = OsUpdater.UpdaterState.STATE_UNKNOWN;
            this.mCurrentOSVersion = null;
            this.mTimeSinceLastUpdateMSec = null;
            this.mTimeSinceLastUpdateCheckMSec = null;
            this.mErrorMessage = "Null Result bundle received.";
            return;
        }
        this.mUpdaterState = OsUpdaterImpl.getUpdaterState(bundle.getString("ota_updater_state", null));
        this.mCurrentOSVersion = getValueFromBundle("current_os_version", bundle);
        this.mTimeSinceLastUpdateMSec = getValueFromBundle("time_since_last_update_msec", bundle);
        this.mTimeSinceLastUpdateCheckMSec = getValueFromBundle("time_since_last_update_check_msec", bundle);
        this.mErrorMessage = bundle.getString("error_message", null);
    }

    private static Long getValueFromBundle(String str, Bundle bundle) {
        if (bundle != null && bundle.containsKey(str)) {
            return Long.valueOf(bundle.getLong(str));
        }
        return null;
    }
}
