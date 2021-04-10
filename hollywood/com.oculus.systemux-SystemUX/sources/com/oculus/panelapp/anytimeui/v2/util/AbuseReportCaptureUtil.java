package com.oculus.panelapp.anytimeui.v2.util;

import android.net.Uri;
import android.text.format.DateUtils;
import com.facebook.debug.log.BLog;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.social.SocialBundleConstants;
import com.oculus.vrshell.SystemUXRoute;
import org.json.JSONException;
import org.json.JSONObject;

public class AbuseReportCaptureUtil {
    public static final long ABUSE_CAPTURE_AV_RECORDING_TIMELIMIT = 150000;
    private static final String EXTRA_ABUSE_REPORT_OBJECT_TYPE = "abuse_report_object_type";
    private static final String EXTRA_ABUSE_REPORT_TYPE = "abuse_report_type";
    private static final String EXTRA_FROM_PKG = "from_pkg";
    private static final String EXTRA_NEEDS_INPUTS = "needs_inputs";
    private static final String EXTRA_PLATFORM_MESSAGE_TYPE = "platform_message_type";
    private static final String EXTRA_PLATFORM_REQUEST_ID = "platform_request_id";
    private static final String EXTRA_PREVENT_PEOPLE_CHOOSER = "prevent_people_chooser";
    private static final String EXTRA_RECORDING_REMAINING_TIME = "recording_remaining_time";
    private static final String EXTRA_RECORDING_UUID = "recording_uuid";
    private static final String EXTRA_REPORT_FLOW_SESSION_ID = "report_flow_session_id";
    private static final String EXTRA_SDK_USER_ID = "sdk_user_id";
    private static final String EXTRA_SOURCE = "source";
    private static final String EXTRA_USE_SOFTWARE_BACK_BUTTON = "use_software_back_button";
    public static final long MS_IN_10_MINUTES = 600000;
    private static final String TAG = LoggingUtil.tag(AbuseReportCaptureUtil.class);

    public static void continueToReportFlow(String str, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, JSONObject jSONObject) {
        boolean z;
        BLog.i(TAG, "Continuing the report flow with abuse report params=[%s]", jSONObject.toString());
        try {
            jSONObject.getString(EXTRA_FROM_PKG);
            Uri.Builder appendQueryParameter = new Uri.Builder().encodedPath("/system_utilities/user_report/").appendPath(anytimeUIAndroidPanelAppV2.getReturnComponent()).appendPath(jSONObject.getString(EXTRA_SDK_USER_ID)).appendPath(jSONObject.getString("source")).appendQueryParameter(EXTRA_ABUSE_REPORT_TYPE, jSONObject.getString(EXTRA_ABUSE_REPORT_TYPE)).appendQueryParameter(EXTRA_RECORDING_UUID, str);
            if (jSONObject.has(EXTRA_PREVENT_PEOPLE_CHOOSER)) {
                if (jSONObject.isNull(EXTRA_PREVENT_PEOPLE_CHOOSER)) {
                    z = false;
                } else {
                    z = jSONObject.getBoolean(EXTRA_PREVENT_PEOPLE_CHOOSER);
                }
                if (z) {
                    appendQueryParameter.appendQueryParameter(EXTRA_PREVENT_PEOPLE_CHOOSER, SocialBundleConstants.FB_UPSELL_MUST_INTERACT);
                }
            }
            if (jSONObject.has(EXTRA_PLATFORM_MESSAGE_TYPE)) {
                appendQueryParameter.appendQueryParameter(EXTRA_PLATFORM_MESSAGE_TYPE, jSONObject.getString(EXTRA_PLATFORM_MESSAGE_TYPE));
            }
            if (jSONObject.has(EXTRA_PLATFORM_REQUEST_ID)) {
                appendQueryParameter.appendQueryParameter(EXTRA_PLATFORM_REQUEST_ID, jSONObject.getString(EXTRA_PLATFORM_REQUEST_ID));
            }
            if (jSONObject.has(EXTRA_REPORT_FLOW_SESSION_ID)) {
                appendQueryParameter.appendQueryParameter(EXTRA_REPORT_FLOW_SESSION_ID, jSONObject.getString(EXTRA_REPORT_FLOW_SESSION_ID));
            }
            if (jSONObject.has(EXTRA_ABUSE_REPORT_OBJECT_TYPE)) {
                appendQueryParameter.appendQueryParameter(EXTRA_ABUSE_REPORT_OBJECT_TYPE, jSONObject.getString(EXTRA_ABUSE_REPORT_OBJECT_TYPE));
            }
            anytimeUIAndroidPanelAppV2.actionNavigate(SystemUXRoute.USER_REPORT, appendQueryParameter.build().toString());
        } catch (JSONException e) {
            BLog.e(TAG, e, "Cannot parse overlay params from JSONObject input: [%s]", jSONObject.toString());
        }
    }

    public static String getFormattedTimelimit() {
        return DateUtils.formatElapsedTime(150).substring(1);
    }
}
