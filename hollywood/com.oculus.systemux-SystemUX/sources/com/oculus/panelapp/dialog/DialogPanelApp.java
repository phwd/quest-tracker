package com.oculus.panelapp.dialog;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.dialog.commonsystemdialogs.AppDownloadFailureLowStorageDialog;
import com.oculus.panelapp.dialog.commonsystemdialogs.IntrusionDetectionNUXDialogDefinition;
import com.oculus.panelapp.dialog.commonsystemdialogs.OculusLinkAvailableDialog;
import com.oculus.panelapp.dialog.commonsystemdialogs.OculusLinkDisconnectedDialog;
import com.oculus.panelapp.dialog.commonsystemdialogs.StationaryGuardianV2NUXDialogDefinition;
import com.oculus.panelapp.dialog.commonsystemdialogs.StationaryGuardianV2NUXUsingRoomscaleDialogDefinition;
import com.oculus.panelapp.dialog.commonsystemdialogs.applaunch.AppLaunchBlockedCloudStorageDialog;
import com.oculus.panelapp.dialog.commonsystemdialogs.applaunch.AppLaunchBlockedControllerRequiredDialog;
import com.oculus.panelapp.dialog.commonsystemdialogs.applaunch.AppLaunchBlockedHandsRequiredDialog;
import com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairingDialog;
import com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStartDialog;
import com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStopDialog;
import com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamToBrowserDialog;
import com.oculus.panelapp.dialog.commonsystemdialogs.social.SocialCreatePartyPreviewDialog;
import com.oculus.panelapp.dialog.commonsystemdialogs.social.SocialJoinPartyDialog;
import com.oculus.panelapp.dialog.commonsystemdialogs.trackedkeyboardconnected.TrackedKeyboardConnectedDialog;
import com.oculus.systemdialog.CommonSystemDialog;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.AndroidPanelApp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class DialogPanelApp {
    private static final String TAG = LoggingUtil.tag(DialogPanelApp.class);
    private final Context mContext;
    private final Map<String, String> mEnvironmentArgs = new HashMap();
    private final long mNativePanelApp;

    private native long nativeCreateInstance(String[] strArr, Surface[] surfaceArr, String[] strArr2);

    private native boolean nativeIsGatekeeperEnabled(long j, String str);

    private native void nativeQueueRawCommand(long j, String str);

    public DialogPanelApp(Application application, Context context, Surface surface, Map<String, Surface> map, Bundle bundle) {
        Log.d(TAG, "DialogPanelApp()");
        this.mContext = context;
        String[] strArr = (String[]) map.keySet().toArray(new String[(map.size() + 1)]);
        strArr[strArr.length - 1] = AndroidPanelApp.MAIN_LAYER;
        Surface[] surfaceArr = (Surface[]) map.values().toArray(new Surface[(map.size() + 1)]);
        surfaceArr[surfaceArr.length - 1] = surface;
        ArrayList arrayList = new ArrayList();
        for (String str : bundle.keySet()) {
            String string = bundle.getString(str);
            this.mEnvironmentArgs.put(str, string);
            if (string != null) {
                arrayList.add(str);
                arrayList.add(string);
            }
        }
        this.mNativePanelApp = nativeCreateInstance(strArr, surfaceArr, (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    public final long getNativePointer() {
        return this.mNativePanelApp;
    }

    public String getEnvironmentArg(String str) {
        return this.mEnvironmentArgs.get(str);
    }

    public final Context getContext() {
        return this.mContext;
    }

    public String getProgressBarText(float f) {
        return this.mContext.getResources().getString(R.string.dialog_progress_bar_text, Integer.valueOf(Math.min(Math.max((int) (f * 100.0f), 0), 100)));
    }

    public CommonDialog getCommonSystemDialog(String str, String str2) {
        Log.i(TAG, String.format("Getting common system dialog definition for dialog ID \"%s\".", str));
        CommonSystemDialog fromId = CommonSystemDialog.fromId(str);
        if (fromId == null) {
            Log.e(TAG, String.format("Unable to find common system dialog with id \"%s\".", str));
            return null;
        }
        Map<String, String> commonSystemDialogParameters = getCommonSystemDialogParameters(str2);
        switch (fromId) {
            case APP_DOWNLOAD_FAILURE_LOW_STORAGE:
                return new AppDownloadFailureLowStorageDialog(this.mContext, this);
            case APP_LAUNCH_BLOCKED_CLOUD_STORAGE:
                return new AppLaunchBlockedCloudStorageDialog(this.mContext, commonSystemDialogParameters);
            case APP_LAUNCH_BLOCKED_CONTROLLER_REQUIRED:
                return new AppLaunchBlockedControllerRequiredDialog(this.mContext, commonSystemDialogParameters, this);
            case APP_LAUNCH_BLOCKED_HANDS_REQUIRED:
                return new AppLaunchBlockedHandsRequiredDialog(this.mContext, commonSystemDialogParameters, this);
            case CONTROLLER_PAIRING:
                return new ControllerPairingDialog(this.mContext, this);
            case INTRUSION_DETECTION_NUX:
                return new IntrusionDetectionNUXDialogDefinition(this.mContext);
            case LOCAL_STREAM_START:
                return new LocalStreamStartDialog(this.mContext, this);
            case LOCAL_STREAM_STOP:
                return new LocalStreamStopDialog(this.mContext);
            case LOCAL_STREAM_TO_BROWSER:
                return new LocalStreamToBrowserDialog(this.mContext);
            case OCULUS_LINK_AVAILABLE:
                return new OculusLinkAvailableDialog(this.mContext, this);
            case OCULUS_LINK_DISCONNECTED:
                return new OculusLinkDisconnectedDialog(this.mContext, commonSystemDialogParameters);
            case TRACKED_KEYBOARD_CONNECTED:
                return new TrackedKeyboardConnectedDialog(this.mContext);
            case SOCIAL_CREATE_PARTY_PREVIEW:
                return new SocialCreatePartyPreviewDialog(this.mContext, commonSystemDialogParameters);
            case SOCIAL_JOIN_PARTY:
                return new SocialJoinPartyDialog(this.mContext, commonSystemDialogParameters);
            case STATIONARY_GUARDIAN_V2_NUX:
                return new StationaryGuardianV2NUXDialogDefinition(this.mContext);
            case STATIONARY_GUARDIAN_V2_NUX_USING_ROOMSCALE:
                return new StationaryGuardianV2NUXUsingRoomscaleDialogDefinition(this.mContext);
            default:
                Log.e(TAG, "Common system dialog should not be null.");
                return null;
        }
    }

    private Map<String, String> getCommonSystemDialogParameters(String str) {
        HashMap hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.getString(next));
            }
        } catch (JSONException e) {
            Log.e(TAG, "Unable to parse dialog parameters JSON.", e);
        }
        return hashMap;
    }

    public boolean isGatekeeperEnabled(Gatekeeper gatekeeper) {
        return nativeIsGatekeeperEnabled(this.mNativePanelApp, gatekeeper.toString());
    }

    public final void launch(SystemUXRoute systemUXRoute) {
        queueRawCommand("launch " + systemUXRoute.path());
    }

    public final void queueRawCommand(String str) {
        nativeQueueRawCommand(this.mNativePanelApp, str);
    }
}
