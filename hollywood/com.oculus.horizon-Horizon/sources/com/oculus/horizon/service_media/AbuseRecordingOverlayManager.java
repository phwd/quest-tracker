package com.oculus.horizon.service_media;

import X.AnonymousClass0NO;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import com.oculus.horizon.abuse_prevention.AbuseAVCapture;
import org.json.JSONException;
import org.json.JSONObject;

public class AbuseRecordingOverlayManager {
    public static final String EXTRA_ABUSE_REPORT_OBJECT_TYPE = "abuse_report_object_type";
    public static final String EXTRA_ABUSE_REPORT_TYPE = "abuse_report_type";
    public static final String EXTRA_FROM_PKG = "from_pkg";
    public static final String EXTRA_NEEDS_INPUTS = "needs_inputs";
    public static final String EXTRA_PLATFORM_MESSAGE_TYPE = "platform_message_type";
    public static final String EXTRA_PLATFORM_REQUEST_ID = "platform_request_id";
    public static final String EXTRA_PREVENT_PEOPLE_CHOOSER = "prevent_people_chooser";
    public static final String EXTRA_RECORDING_REMAINING_TIME = "recording_remaining_time";
    public static final String EXTRA_RECORDING_UUID = "recording_uuid";
    public static final String EXTRA_REPORT_FLOW_SESSION_ID = "report_flow_session_id";
    public static final String EXTRA_SDK_USER_ID = "sdk_user_id";
    public static final String EXTRA_SOURCE = "source";
    public static final String EXTRA_USE_SOFTWARE_BACK_BUTTON = "use_software_back_button";
    public static final int RECORDING_MAX_LENGTH_IN_MS = 150000;
    public static final String TAG = "AbuseRecordingOverlayManager";
    public String mAbuseReportObjectType = "";
    public String mAbuseReportType = "";
    public String mFromPkg = "";
    public long mOverlayStartTime = 0;
    public String mPlatformMessageType = "";
    public String mPlatformRequestID = "";
    public boolean mPreventPeopleChooser = false;
    public String mRecordingUUID = "";
    public String mReportFlowSessionID = "";
    public String mSdkUserID = "";
    public String mSource = "";
    public boolean mUseSoftwareBackButton = false;

    public AbuseRecordingOverlayManager(JSONObject jSONObject) {
        boolean z;
        try {
            this.mRecordingUUID = jSONObject.getString("recording_uuid");
            this.mAbuseReportType = jSONObject.getString("abuse_report_type");
            this.mFromPkg = jSONObject.getString("from_pkg");
            this.mSource = jSONObject.getString("source");
            this.mSdkUserID = jSONObject.getString("sdk_user_id");
            if (jSONObject.has(EXTRA_USE_SOFTWARE_BACK_BUTTON)) {
                this.mUseSoftwareBackButton = jSONObject.getBoolean(EXTRA_USE_SOFTWARE_BACK_BUTTON);
            }
            if (jSONObject.has("prevent_people_chooser")) {
                if (jSONObject.isNull("prevent_people_chooser")) {
                    z = false;
                } else {
                    z = jSONObject.getBoolean("prevent_people_chooser");
                }
                this.mPreventPeopleChooser = z;
            }
            if (jSONObject.has("platform_message_type")) {
                this.mPlatformMessageType = jSONObject.getString("platform_message_type");
            }
            if (jSONObject.has("platform_request_id")) {
                this.mPlatformRequestID = jSONObject.getString("platform_request_id");
            }
            if (jSONObject.has("report_flow_session_id")) {
                this.mReportFlowSessionID = jSONObject.getString("report_flow_session_id");
            }
            if (jSONObject.has("abuse_report_object_type")) {
                this.mAbuseReportObjectType = jSONObject.getString("abuse_report_object_type");
            }
        } catch (JSONException e) {
            AnonymousClass0NO.A0K(TAG, e, "Cannot parse overlay params from JSONObject input: [%s]", jSONObject.toString());
        }
    }

    @SuppressLint({"BadMethodUse-java.lang.System.currentTimeMillis"})
    public final void A00(Context context) {
        if (this.mOverlayStartTime == 0) {
            this.mOverlayStartTime = System.currentTimeMillis();
        }
        Intent intent = new Intent(OverlayUtils.START_RENDERING_OVERLAY);
        intent.putExtra(OverlayUtils.OVERLAYS_TYPE, "abuse_recording");
        intent.putExtra("recording_uuid", this.mRecordingUUID);
        intent.putExtra("report_flow_session_id", this.mReportFlowSessionID);
        intent.putExtra("abuse_report_type", this.mAbuseReportType);
        intent.putExtra("abuse_report_object_type", this.mAbuseReportObjectType);
        intent.putExtra("from_pkg", this.mFromPkg);
        intent.putExtra("source", this.mSource);
        intent.putExtra("sdk_user_id", this.mSdkUserID);
        intent.putExtra("platform_message_type", this.mPlatformMessageType);
        intent.putExtra("platform_request_id", this.mPlatformRequestID);
        if (this.mPreventPeopleChooser) {
            intent.putExtra("prevent_people_chooser", "true");
        }
        if (this.mUseSoftwareBackButton) {
            intent.putExtra(EXTRA_USE_SOFTWARE_BACK_BUTTON, "true");
            intent.putExtra(EXTRA_NEEDS_INPUTS, true);
        }
        intent.putExtra(EXTRA_RECORDING_REMAINING_TIME, Long.toString(AbuseAVCapture.ABUSE_CAPTURE_AV_RECORDING_TIMELIMIT - (System.currentTimeMillis() - this.mOverlayStartTime)));
        context.sendBroadcast(intent);
    }
}
