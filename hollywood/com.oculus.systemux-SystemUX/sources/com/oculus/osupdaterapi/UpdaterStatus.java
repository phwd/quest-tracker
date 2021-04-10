package com.oculus.osupdaterapi;

import android.os.Bundle;
import com.oculus.osupdaterapi.OsUpdater;
import com.oculus.updater.core.constants.OSUpdaterConstants;
import javax.annotation.Nullable;

public final class UpdaterStatus {
    @Nullable
    public final Long mCurrentOSVersion;
    @Nullable
    public final String mErrorMessage;
    @Nullable
    public final Long mTimeSinceLastUpdateCheckMSec;
    @Nullable
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
        this.mUpdaterState = OsUpdaterImpl.getUpdaterState(bundle.getString(OSUpdaterConstants.KEY_UPDATER_STATE, null));
        this.mCurrentOSVersion = getValueFromBundle(OSUpdaterConstants.KEY_CURRENT_OS_VERSION, bundle);
        this.mTimeSinceLastUpdateMSec = getValueFromBundle(OSUpdaterConstants.KEY_TIME_SINCE_LAST_UPDATE_MSEC, bundle);
        this.mTimeSinceLastUpdateCheckMSec = getValueFromBundle(OSUpdaterConstants.KEY_TIME_SINCE_LAST_UPDATE_CHECK_MSEC, bundle);
        this.mErrorMessage = bundle.getString("error_message", null);
    }

    @Nullable
    private static Long getValueFromBundle(String str, Bundle bundle) {
        if (bundle != null && bundle.containsKey(str)) {
            return Long.valueOf(bundle.getLong(str));
        }
        return null;
    }
}
