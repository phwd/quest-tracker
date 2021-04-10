package com.oculus.systemdialog;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;

public enum CommonSystemDialog {
    APP_DOWNLOAD_FAILURE_LOW_STORAGE("common_system_dialog_app_download_failure_low_storage"),
    APP_LAUNCH_BLOCKED_CLOUD_STORAGE("common_system_dialog_app_launch_blocked_cloud_storage"),
    APP_LAUNCH_BLOCKED_CONTROLLER_REQUIRED("common_system_dialog_app_launch_blocked_controller_required"),
    APP_LAUNCH_BLOCKED_HANDS_REQUIRED("common_system_dialog_app_launch_blocked_hands_required"),
    CONTROLLER_PAIRING("common_system_dialog_controller_pairing"),
    ENTERPRISE_CAST_TO_BROWSER_PIN("common_system_dialog_enterprise_cast_to_browser_pin"),
    ENTERPRISE_KIOSK_NOT_INSTALLED("common_system_dialog_enterprise_kiosk_not_installed"),
    FACEBOOK_BLOCK("common_system_dialog_facebook_block"),
    FILE_PICKER("common_system_dialog_file_picker"),
    FILE_PREVIEWER("common_system_dialog_file_previewer"),
    INTRUSION_DETECTION_NUX("systemux://dialog/intrusion-detection-nux"),
    LOCAL_STREAM_PRIVACY_CHECK("common_system_dialog_local_stream_privacy_check"),
    LOCAL_STREAM_START("common_system_dialog_local_stream_start"),
    LOCAL_STREAM_STOP("common_system_dialog_local_stream_stop"),
    LOCAL_STREAM_TO_BROWSER("common_system_dialog_local_stream_to_browser"),
    MESSENGER_INTEGRITY("common_system_dialog_messenger_integrity"),
    OCULUS_LINK_AVAILABLE("common_system_dialog_oculus_link_available"),
    OCULUS_LINK_DISCONNECTED("common_system_dialog_oculus_link_disconnected"),
    SOCIAL_CREATE_PARTY_PREVIEW("systemux://dialog/create-party-preview"),
    SOCIAL_CREATE_VR_INVITE("common_system_dialog_social_create_vr_invite"),
    SOCIAL_JOIN_PARTY("systemux://dialog/social-join-party"),
    SOCIAL_CONFIRM_JOIN_PARTY("systemux://dialog/social-confirm-join-party"),
    SOCIAL_PARTY_PRIVACY("systemux://dialog/social-party-privacy"),
    STATIONARY_GUARDIAN_V2_NUX("systemux://dialog/stationary-guardian-v2-nux"),
    STATIONARY_GUARDIAN_V2_NUX_USING_ROOMSCALE("systemux://dialog/stationary-guardian-v2-nux-using-roomscale"),
    TRACKED_KEYBOARD_CONNECTED("common_system_dialog_tracked_keyboard_connected");
    
    private static final String TAG = LoggingUtil.tag(CommonSystemDialog.class);
    private final String mDialogId;

    private CommonSystemDialog(String str) {
        this.mDialogId = str;
    }

    public String getDialogId() {
        return this.mDialogId;
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
