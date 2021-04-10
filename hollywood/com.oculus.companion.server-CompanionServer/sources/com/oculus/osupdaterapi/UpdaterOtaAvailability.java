package com.oculus.osupdaterapi;

import android.os.Bundle;
import android.text.TextUtils;

public final class UpdaterOtaAvailability {
    public final Boolean mAreUpdatesAvailable;
    public final Long mCurrentOSVersion;
    public final String mErrorMessage;
    public final Boolean mIsQuerySuccessful;
    public final String mReleaseChannelId;
    public final String mReleaseChannelName;
    public final Long mTargetVersion;
    public final Long mUpdateSizeInBytes;
    public final OsUpdater$UpdaterState mUpdaterState;

    UpdaterOtaAvailability(int i, Bundle bundle) {
        if (bundle == null) {
            this.mUpdaterState = OsUpdater$UpdaterState.STATE_UNKNOWN;
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
        this.mUpdaterState = OsUpdaterImpl.getUpdaterState(bundle.getString("ota_updater_state", null));
        this.mCurrentOSVersion = bundle.containsKey("current_os_version") ? Long.valueOf(bundle.getLong("current_os_version")) : null;
        this.mIsQuerySuccessful = Boolean.valueOf(i == 0);
        this.mAreUpdatesAvailable = (!this.mIsQuerySuccessful.booleanValue() || !bundle.containsKey("ota_are_updates_available")) ? null : Boolean.valueOf(bundle.getBoolean("ota_are_updates_available"));
        this.mTargetVersion = getValueFromBundle("ota_update_target_version", bundle);
        this.mUpdateSizeInBytes = getValueFromBundle("ota_update_size", bundle);
        this.mErrorMessage = updateErrorMessage(bundle.getString("error_message", null));
        this.mReleaseChannelId = bundle.getString("release_channel_id", null);
        this.mReleaseChannelName = bundle.getString("release_channel_name", null);
    }

    private Long getValueFromBundle(String str, Bundle bundle) {
        Boolean bool;
        Boolean bool2;
        if (bundle == null || (bool = this.mIsQuerySuccessful) == null || !bool.booleanValue() || (bool2 = this.mAreUpdatesAvailable) == null || !bool2.booleanValue() || !bundle.containsKey(str)) {
            return null;
        }
        return Long.valueOf(bundle.getLong(str));
    }

    private String updateErrorMessage(String str) {
        if (this.mUpdaterState == OsUpdater$UpdaterState.STATE_UNKNOWN) {
            return str;
        }
        String str2 = "";
        if (this.mCurrentOSVersion == null) {
            str2 = str2 + " Failed to read the Current OS Version.";
        }
        int i = AnonymousClass1.$SwitchMap$com$oculus$osupdaterapi$OsUpdater$UpdaterState[this.mUpdaterState.ordinal()];
        if (i == 1 || i == 2) {
            if (this.mTargetVersion == null) {
                str2 = str2 + " Failed to fetch the target Version.";
            }
        } else if (i == 3) {
            Boolean bool = this.mAreUpdatesAvailable;
            if (bool == null) {
                str2 = str2 + " Failed to read if updates are available";
            } else if (bool.booleanValue()) {
                if (this.mTargetVersion == null) {
                    str2 = str2 + " Failed to read the target Version.";
                }
                if (this.mUpdateSizeInBytes == null) {
                    str2 = str2 + " Failed to read the Updater Size.";
                }
            }
        }
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        if (str == null) {
            return str2;
        }
        return str + str2;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.osupdaterapi.UpdaterOtaAvailability$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$osupdaterapi$OsUpdater$UpdaterState = new int[OsUpdater$UpdaterState.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.oculus.osupdaterapi.OsUpdater$UpdaterState[] r0 = com.oculus.osupdaterapi.OsUpdater$UpdaterState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.osupdaterapi.UpdaterOtaAvailability.AnonymousClass1.$SwitchMap$com$oculus$osupdaterapi$OsUpdater$UpdaterState = r0
                int[] r0 = com.oculus.osupdaterapi.UpdaterOtaAvailability.AnonymousClass1.$SwitchMap$com$oculus$osupdaterapi$OsUpdater$UpdaterState     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.osupdaterapi.OsUpdater$UpdaterState r1 = com.oculus.osupdaterapi.OsUpdater$UpdaterState.STATE_WAITING_FOR_REBOOT     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.osupdaterapi.UpdaterOtaAvailability.AnonymousClass1.$SwitchMap$com$oculus$osupdaterapi$OsUpdater$UpdaterState     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.osupdaterapi.OsUpdater$UpdaterState r1 = com.oculus.osupdaterapi.OsUpdater$UpdaterState.STATE_UPDATE_IN_PROGRESS     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.osupdaterapi.UpdaterOtaAvailability.AnonymousClass1.$SwitchMap$com$oculus$osupdaterapi$OsUpdater$UpdaterState     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.osupdaterapi.OsUpdater$UpdaterState r1 = com.oculus.osupdaterapi.OsUpdater$UpdaterState.STATE_READY_TO_CHECK_FOR_OTA     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.osupdaterapi.UpdaterOtaAvailability.AnonymousClass1.<clinit>():void");
        }
    }
}
