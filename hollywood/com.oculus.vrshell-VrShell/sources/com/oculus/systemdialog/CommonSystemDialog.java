package com.oculus.systemdialog;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.SystemUXRoute;

public enum CommonSystemDialog {
    APP_LAUNCH_BLOCKED_CLOUD_STORAGE("common_system_dialog_app_launch_blocked_cloud_storage"),
    FACEBOOK_BLOCK("common_system_dialog_facebook_block"),
    FILE_PICKER("common_system_dialog_file_picker"),
    FILE_PREVIEWER("common_system_dialog_file_previewer"),
    INTRUSION_DETECTION_NUX("systemux://dialog/intrusion-detection-nux"),
    LOCAL_STREAM_PRIVACY_CHECK("common_system_dialog_local_stream_privacy_check"),
    LOCAL_STREAM_START("common_system_dialog_local_stream_start"),
    LOCAL_STREAM_STOP("common_system_dialog_local_stream_stop"),
    LOCAL_STREAM_TO_BROWSER("common_system_dialog_local_stream_to_browser"),
    MESSENGER_INTEGRITY("common_system_dialog_messenger_integrity"),
    SOCIAL_CREATE_PARTY_PREVIEW("systemux://dialog/create-party-preview"),
    SOCIAL_CREATE_VR_INVITE("common_system_dialog_social_create_vr_invite"),
    SOCIAL_JOIN_PARTY("systemux://dialog/social-join-party"),
    SOCIAL_CONFIRM_JOIN_PARTY("systemux://dialog/social-confirm-join-party"),
    SOCIAL_PARTY_PRIVACY("systemux://dialog/social-party-privacy"),
    STATIONARY_GUARDIAN_V2_NUX("systemux://dialog/stationary-guardian-v2-nux"),
    STATIONARY_GUARDIAN_V2_NUX_USING_ROOMSCALE("systemux://dialog/stationary-guardian-v2-nux-using-roomscale");
    
    private static final String TAG = LoggingUtil.tag(CommonSystemDialog.class);
    private final String mDialogId;

    private CommonSystemDialog(String str) {
        this.mDialogId = str;
    }

    public String getDialogId() {
        return this.mDialogId;
    }

    public static CommonSystemDialog fromRoute(SystemUXRoute systemUXRoute) {
        if (systemUXRoute == null) {
            Log.e(TAG, "fromRoute should not be called with null route.");
            return null;
        }
        switch (systemUXRoute) {
            case FACEBOOK_BLOCK:
                return FACEBOOK_BLOCK;
            case INTRUSION_DETECTION_NUX:
                return INTRUSION_DETECTION_NUX;
            case LAUNCH_CHECK_CLOUD_STORAGE:
                return APP_LAUNCH_BLOCKED_CLOUD_STORAGE;
            case LOCAL_STREAM_PRIVACY_CHECK_DIALOG:
                return LOCAL_STREAM_PRIVACY_CHECK;
            case LOCAL_STREAM_START_FROM_DEVICE_DIALOG:
                return LOCAL_STREAM_START;
            case LOCAL_STREAM_STOP_FROM_DEVICE_DIALOG:
                return LOCAL_STREAM_STOP;
            case LOCAL_STREAM_TO_BROWSER_DIALOG:
                return LOCAL_STREAM_TO_BROWSER;
            case MESSENGER_INTEGRITY:
                return MESSENGER_INTEGRITY;
            case SOCIAL_CREATE_PARTY_PREVIEW:
                return SOCIAL_CREATE_PARTY_PREVIEW;
            case SOCIAL_CREATE_VR_INVITE_DIALOG:
                return SOCIAL_CREATE_VR_INVITE;
            case SOCIAL_JOIN_PARTY:
                return SOCIAL_JOIN_PARTY;
            case SOCIAL_CONFIRM_JOIN_PARTY:
                return SOCIAL_CONFIRM_JOIN_PARTY;
            case SOCIAL_PARTY_PRIVACY:
                return SOCIAL_PARTY_PRIVACY;
            case STATIONARY_GUARDIAN_V2_NUX:
                return STATIONARY_GUARDIAN_V2_NUX;
            case STATIONARY_GUARDIAN_V2_NUX_USING_ROOMSCALE:
                return STATIONARY_GUARDIAN_V2_NUX_USING_ROOMSCALE;
            default:
                Log.i(TAG, String.format("Unable to find common dialog for systemux route \"%s\".", systemUXRoute.toString()));
                return null;
        }
    }

    public static CommonSystemDialog fromId(String str) {
        CommonSystemDialog[] values = values();
        for (CommonSystemDialog commonSystemDialog : values) {
            if (commonSystemDialog.getDialogId().equals(str)) {
                return commonSystemDialog;
            }
        }
        Log.e(TAG, String.format("Unexpected common system dialog ID \"%s\".", str));
        return null;
    }
}
