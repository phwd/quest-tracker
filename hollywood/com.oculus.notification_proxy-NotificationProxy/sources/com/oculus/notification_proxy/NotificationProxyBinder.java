package com.oculus.notification_proxy;

import android.app.NotificationManager;
import android.content.Context;
import java.util.HashMap;
import oculus.internal.INotificationProxy;

class NotificationProxyBinder extends INotificationProxy.Stub {
    private static final String CONTROLLER_FIRMWARE_UPDATE_TAG = "controller_firmware_update";
    private static final String DEBUG_TAG = "debug";
    private static final String TAG = "NotificationProxyBinder";
    private static final String VRUI_GAZE_INSTRUCTION_TAG = "vrui_gaze_instruction";
    private final Context mContext;
    private boolean mDisplayedVrUiNotification = false;
    private HashMap<Long, Integer> mRemotesBatteryMap = new HashMap<>();

    public NotificationProxyBinder(Context context) {
        this.mContext = context;
    }

    public void showControllerFirmwareUpdateProgress(int i, int i2) {
        int i3;
        int i4 = R.string.controller_firmware_update_title;
        if (i == 1) {
            i3 = R.string.controller_firmware_update_success_text;
        } else if (i == 2) {
            i4 = R.string.controller_firmware_update_failed_title;
            i3 = R.string.controller_firmware_update_failed_text;
        } else {
            i3 = R.string.controller_firmware_update_progress_text;
        }
        NotificationBuildingHelper.notifyFormatText(this.mContext, i4, i3, CONTROLLER_FIRMWARE_UPDATE_TAG, NotificationIds.CONTROLLER_FIRMWARE_UPDATE, true, Integer.valueOf(i2));
    }

    public void showControllerFirmwareUpdateProgressWithDeviceType(int i, int i2, int i3) {
        int i4;
        int i5;
        int i6 = i == 0 ? R.string.dual_controller_right_controller_firmware_update_title : i == 1 ? R.string.dual_controller_left_controller_firmware_update_title : R.string.controller_firmware_update_title;
        if (i2 == 1) {
            i4 = R.string.controller_firmware_update_success_text;
        } else if (i2 == 2) {
            if (i == 0) {
                i5 = R.string.dual_controller_right_controller_firmware_update_failed_title;
            } else if (i == 1) {
                i5 = R.string.dual_controller_left_controller_firmware_update_failed_title;
            } else {
                i6 = R.string.controller_firmware_update_failed_title;
                i4 = R.string.controller_firmware_update_failed_text;
            }
            i6 = i5;
            i4 = R.string.controller_firmware_update_failed_text;
        } else {
            i4 = R.string.controller_firmware_update_progress_text;
        }
        NotificationBuildingHelper.notifyFormatText(this.mContext, i6, i4, CONTROLLER_FIRMWARE_UPDATE_TAG, NotificationIds.CONTROLLER_FIRMWARE_UPDATE, true, Integer.valueOf(i3));
    }

    public void showVrUiGazeTutorialNotification() {
        if (this.mContext.getPackageManager().hasSystemFeature("oculus.software.guardian") && !this.mDisplayedVrUiNotification) {
            ((NotificationManager) this.mContext.getSystemService("notification")).cancel(NotificationIds.CONTROLLER_DISCONNECTED);
            NotificationBuildingHelper.notify(this.mContext, (int) R.string.vrui_gaze_input_instruction_title, (int) R.string.vrui_gaze_input_instruction_text, VRUI_GAZE_INSTRUCTION_TAG, (int) NotificationIds.VRUI_GAZE_INSTRUCTION);
            this.mDisplayedVrUiNotification = true;
        }
    }

    public void showDebugToast(String str, String str2) {
        NotificationBuildingHelper.notify(this.mContext, DEBUG_TAG, (int) NotificationIds.DEBUG, str, str2);
    }
}
