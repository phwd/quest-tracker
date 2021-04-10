package com.oculus.osupdaterapi;

import android.os.Bundle;
import android.text.TextUtils;
import com.oculus.osupdaterapi.OsUpdater;

public final class UpdaterOtaAvailability {
    public final Boolean mAreUpdatesAvailable;
    public final Long mCurrentOSVersion;
    public final String mErrorMessage;
    public final Boolean mIsQuerySuccessful;
    public final String mReleaseChannelId;
    public final String mReleaseChannelName;
    public final Long mTargetVersion;
    public final Long mUpdateSizeInBytes;
    public final OsUpdater.UpdaterState mUpdaterState;

    UpdaterOtaAvailability(int resultCode, Bundle resultBundle) {
        if (resultBundle == null) {
            this.mUpdaterState = OsUpdater.UpdaterState.STATE_UNKNOWN;
            this.mCurrentOSVersion = null;
            this.mIsQuerySuccessful = null;
            this.mAreUpdatesAvailable = null;
            this.mTargetVersion = null;
            this.mUpdateSizeInBytes = null;
            this.mErrorMessage = "Null Result bundle received";
            this.mReleaseChannelId = null;
            this.mReleaseChannelName = null;
            return;
        }
        this.mUpdaterState = OsUpdaterImpl.getUpdaterState(resultBundle.getString("ota_updater_state", null));
        this.mCurrentOSVersion = resultBundle.containsKey("current_os_version") ? Long.valueOf(resultBundle.getLong("current_os_version")) : null;
        this.mIsQuerySuccessful = Boolean.valueOf(resultCode == 0);
        this.mAreUpdatesAvailable = this.mIsQuerySuccessful.booleanValue() ? resultBundle.containsKey("ota_are_updates_available") ? Boolean.valueOf(resultBundle.getBoolean("ota_are_updates_available")) : null : null;
        this.mTargetVersion = getValueFromBundle("ota_update_target_version", resultBundle);
        this.mUpdateSizeInBytes = getValueFromBundle("ota_update_size", resultBundle);
        this.mErrorMessage = updateErrorMessage(resultBundle.getString("error_message", null));
        this.mReleaseChannelId = resultBundle.getString("release_channel_id", null);
        this.mReleaseChannelName = resultBundle.getString("release_channel_name", null);
    }

    private Long getValueFromBundle(String key, Bundle resultBundle) {
        if (resultBundle == null || this.mIsQuerySuccessful == null || !this.mIsQuerySuccessful.booleanValue() || this.mAreUpdatesAvailable == null || !this.mAreUpdatesAvailable.booleanValue() || !resultBundle.containsKey(key)) {
            return null;
        }
        return Long.valueOf(resultBundle.getLong(key));
    }

    private String updateErrorMessage(String errorMessage) {
        if (this.mUpdaterState == OsUpdater.UpdaterState.STATE_UNKNOWN) {
            return errorMessage;
        }
        String errorDetails = "";
        if (this.mCurrentOSVersion == null) {
            errorDetails = errorDetails + " Failed to read the Current OS Version.";
        }
        switch (this.mUpdaterState) {
            case STATE_WAITING_FOR_REBOOT:
            case STATE_UPDATE_IN_PROGRESS:
                if (this.mTargetVersion == null) {
                    errorDetails = errorDetails + " Failed to fetch the target Version.";
                    break;
                }
                break;
            case STATE_READY_TO_CHECK_FOR_OTA:
                if (this.mAreUpdatesAvailable != null) {
                    if (this.mAreUpdatesAvailable.booleanValue()) {
                        if (this.mTargetVersion == null) {
                            errorDetails = errorDetails + " Failed to read the target Version.";
                        }
                        if (this.mUpdateSizeInBytes == null) {
                            errorDetails = errorDetails + " Failed to read the Updater Size.";
                            break;
                        }
                    }
                } else {
                    errorDetails = errorDetails + " Failed to read if updates are available";
                    break;
                }
                break;
        }
        if (TextUtils.isEmpty(errorDetails)) {
            return errorMessage;
        }
        if (errorMessage != null) {
            errorDetails = errorMessage + errorDetails;
        }
        return errorDetails;
    }
}
