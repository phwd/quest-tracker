package com.oculus.modules;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.ResultReceiver;
import android.util.Pair;
import com.facebook.debug.log.BLog;
import com.google.gson.Gson;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AbuseReportModule extends RCTBaseJavaModule {
    private static final String ABUSE_REPORT_PACKAGE_NAME = "abuse_report_package_name";
    private static final String ACTION_REQUEST_EVIDENCE_VIDEO = "com.oculus.horizon.abuse_prevention.ACTION_REQUEST_EVIDENCE_VIDEO";
    private static final String BROADCAST_CAPTURE_ABUSE_START = "broadcast_abuse_capture_start";
    private static final String BROADCAST_CAPTURE_ABUSE_STOP = "broadcast_abuse_capture_stop";
    public static final String COPY_MEDIA_FILE_FOR_ABUSE_REPORT = "com.oculus.horizon.COPY_MEDIA_FILE_FOR_ABUSE_REPORT";
    private static final Gson GSON;
    private static final String HORIZON_PACKAGE_NAME = "com.oculus.horizon";
    private static final String KEY_ABUSE_REPORT_OBJECT_TYPE = "abuse_report_object_type";
    private static final String KEY_ABUSE_REPORT_PARAMS = "abuse_report_params";
    private static final String KEY_ABUSE_REPORT_TYPE = "abuse_report_type";
    private static final String KEY_CONTINUE_REPORT_IN_OVERLAY = "continue_report_in_overlay";
    private static final String KEY_COPY_MEDIA_FOR_ABUSE_REPORT_UUID = "copy_media_for_abuse_report_uuid";
    private static final String KEY_FROM_PKG = "from_pkg";
    private static final String KEY_MEDIA_PATH = "media_path";
    private static final String KEY_MESSAGE_TYPE = "message_type";
    private static final String KEY_OVERLAY_PARAMS = "recording_overlay_params";
    private static final String KEY_PLATFORM_MESSGAE_TYPE = "platform_message_type";
    private static final String KEY_PLATFORM_REQUEST_ID = "platform_request_id";
    private static final String KEY_PREVENT_PEOPLE_CHOOSER = "prevent_people_chooser";
    private static final String KEY_RECORDING_COMMAND_STATUS = "recording_command_status";
    private static final String KEY_RECORDING_UUID = "recording_uuid";
    private static final String KEY_REPORT_FLOW_SESSION_ID = "report_flow_session_id";
    private static final String KEY_RESULT_RECEIVER = "result_receiver";
    private static final String KEY_SDK_USER_ID = "sdk_user_id";
    private static final String KEY_SOURCE = "source";
    private static String MODULE_NAME = AbuseReportModule.class.getSimpleName();
    private static final String OVR_MEDIA_SERVICE_NAME = "com.oculus.horizon.service_media.OVRMediaService";
    private static final String TAG = MODULE_NAME;
    private static final String VIDEO_UPLOADER_SERVICE_NAME = "com.oculus.horizon.abuse_prevention.VideoUploaderService";
    private Context mContext = null;

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0030, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0031, code lost:
        r2 = r1;
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0045, code lost:
        r1 = th;
     */
    static {
        /*
            java.lang.Class<com.oculus.modules.AbuseReportModule> r1 = com.oculus.modules.AbuseReportModule.class
            java.lang.String r1 = r1.getSimpleName()
            com.oculus.modules.AbuseReportModule.MODULE_NAME = r1
            java.lang.String r1 = com.oculus.modules.AbuseReportModule.MODULE_NAME
            com.oculus.modules.AbuseReportModule.TAG = r1
            com.oculus.panellib.SystraceBlock r0 = new com.oculus.panellib.SystraceBlock
            java.lang.String r1 = com.oculus.modules.AbuseReportModule.TAG
            java.lang.String r2 = "GSON"
            r0.<init>(r1, r2)
            r2 = 0
            com.google.gson.Gson r1 = new com.google.gson.Gson     // Catch:{ Throwable -> 0x002e, all -> 0x0045 }
            r1.<init>()     // Catch:{ Throwable -> 0x002e, all -> 0x0045 }
            com.oculus.modules.AbuseReportModule.GSON = r1     // Catch:{ Throwable -> 0x002e, all -> 0x0045 }
            if (r0 == 0) goto L_0x0024
            if (r2 == 0) goto L_0x002a
            r0.close()     // Catch:{ Throwable -> 0x0025 }
        L_0x0024:
            return
        L_0x0025:
            r1 = move-exception
            r2.addSuppressed(r1)
            goto L_0x0024
        L_0x002a:
            r0.close()
            goto L_0x0024
        L_0x002e:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0030 }
        L_0x0030:
            r2 = move-exception
            r4 = r2
            r2 = r1
            r1 = r4
        L_0x0034:
            if (r0 == 0) goto L_0x003b
            if (r2 == 0) goto L_0x0041
            r0.close()     // Catch:{ Throwable -> 0x003c }
        L_0x003b:
            throw r1
        L_0x003c:
            r3 = move-exception
            r2.addSuppressed(r3)
            goto L_0x003b
        L_0x0041:
            r0.close()
            goto L_0x003b
        L_0x0045:
            r1 = move-exception
            goto L_0x0034
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.AbuseReportModule.<clinit>():void");
    }

    public AbuseReportModule(Context context) {
        this.mContext = context;
    }

    public String getModuleName() {
        return MODULE_NAME;
    }

    public void startRecordingForReportFlow(JSONObject abuseReportParams, boolean attachAbuseReportParams, final int successCallbackID, final int failureCallbackID) {
        try {
            BLog.i(TAG, "Sending intent to OVRMediaService to start recording with abuse report params=[%s]", abuseReportParams.toString());
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.oculus.horizon", OVR_MEDIA_SERVICE_NAME));
            intent.putExtra(KEY_MESSAGE_TYPE, BROADCAST_CAPTURE_ABUSE_START);
            if (attachAbuseReportParams) {
                intent.putExtra(KEY_ABUSE_REPORT_PARAMS, abuseReportParams.toString());
            }
            intent.putExtra("result_receiver", getCrossPackageResultReceiver(new ResultReceiver(null) {
                /* class com.oculus.modules.AbuseReportModule.AnonymousClass1 */

                /* access modifiers changed from: protected */
                public void onReceiveResult(int resultCode, Bundle resultData) {
                    if (resultCode == -1 && resultData != null) {
                        String recordingUUID = resultData.getString(AbuseReportModule.KEY_RECORDING_UUID);
                        if (Boolean.valueOf(resultData.getBoolean(AbuseReportModule.KEY_RECORDING_COMMAND_STATUS, false)).booleanValue() && recordingUUID != null && !recordingUUID.isEmpty()) {
                            AbuseReportModule.nativeInvokeCallback(AbuseReportModule.this.RVRCtxTag, successCallbackID, AbuseReportModule.GSON.toJson(new String[]{recordingUUID}));
                            return;
                        }
                    }
                    AbuseReportModule.nativeInvokeCallback(AbuseReportModule.this.RVRCtxTag, failureCallbackID, "[" + AbuseReportModule.this.bundleToString(resultData) + "]");
                }
            }));
            this.mContext.startService(intent);
        } catch (Exception exception) {
            nativeInvokeCallback(this.RVRCtxTag, failureCallbackID, "[" + GSON.toJson(exception.getMessage()) + "]");
            BLog.e(TAG, exception, "Sending intent to OVRMediaService to start abuse capture failed.");
        }
    }

    public void stopRecordingAndLaunchReportFlow(JSONObject overlayParams) {
        try {
            final String abuseReportObjectType = overlayParams.getString(KEY_ABUSE_REPORT_OBJECT_TYPE);
            final String abuseReportType = overlayParams.getString(KEY_ABUSE_REPORT_TYPE);
            final String preventPeopleChooser = overlayParams.getString(KEY_PREVENT_PEOPLE_CHOOSER);
            final String recordingUUID = overlayParams.getString(KEY_RECORDING_UUID);
            final String reportFlowSessionID = overlayParams.getString(KEY_REPORT_FLOW_SESSION_ID);
            final String sdkUserID = overlayParams.getString(KEY_SDK_USER_ID);
            overlayParams.getString(KEY_FROM_PKG);
            final String source = overlayParams.getString(KEY_SOURCE);
            final String platformMessageType = overlayParams.getString(KEY_PLATFORM_MESSGAE_TYPE);
            final String platformRequestID = overlayParams.getString(KEY_PLATFORM_REQUEST_ID);
            final Boolean continueReportInOverlay = Boolean.valueOf(overlayParams.getBoolean(KEY_CONTINUE_REPORT_IN_OVERLAY));
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.oculus.horizon", OVR_MEDIA_SERVICE_NAME));
            intent.putExtra(KEY_MESSAGE_TYPE, BROADCAST_CAPTURE_ABUSE_STOP);
            intent.putExtra(KEY_RECORDING_UUID, recordingUUID);
            intent.putExtra("result_receiver", getCrossPackageResultReceiver(new ResultReceiver(null) {
                /* class com.oculus.modules.AbuseReportModule.AnonymousClass2 */

                /* access modifiers changed from: protected */
                public void onReceiveResult(int resultCode, Bundle resultData) {
                    if (resultCode != -1) {
                        BLog.e(AbuseReportModule.TAG, "Something went wrong while stopping abuse capture: [%s]", resultData);
                    }
                    AbuseReportModule.this.launchReportFlow(abuseReportObjectType, abuseReportType, preventPeopleChooser, recordingUUID, reportFlowSessionID, sdkUserID, resultData.getString(AbuseReportModule.ABUSE_REPORT_PACKAGE_NAME), source, platformMessageType, platformRequestID, continueReportInOverlay.booleanValue());
                }
            }));
            this.mContext.startService(intent);
        } catch (Exception ex) {
            BLog.e(TAG, ex, "Sending intent to OVRMediaService to stop abuse capture failed.");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void launchReportFlow(String abuseReportObjectType, String abuseReportType, String preventPeopleChooser, String recordingUUID, String reportFlowSessionID, String sdkUserID, String fromPkg, String source, String platformMessageType, String platformRequestID, boolean continueReportInOverlay) {
        Uri.Builder deeplinkUri = new Uri.Builder().encodedPath("/system_utilities/user_report/").appendPath(fromPkg).appendPath(sdkUserID).appendPath(source);
        if (recordingUUID != null) {
            deeplinkUri.appendQueryParameter(KEY_RECORDING_UUID, recordingUUID);
        }
        if (preventPeopleChooser != null) {
            deeplinkUri.appendQueryParameter(KEY_PREVENT_PEOPLE_CHOOSER, preventPeopleChooser);
        }
        deeplinkUri.appendQueryParameter(KEY_ABUSE_REPORT_OBJECT_TYPE, abuseReportObjectType);
        deeplinkUri.appendQueryParameter(KEY_ABUSE_REPORT_TYPE, abuseReportType);
        deeplinkUri.appendQueryParameter(KEY_REPORT_FLOW_SESSION_ID, reportFlowSessionID);
        deeplinkUri.appendQueryParameter(KEY_PLATFORM_MESSGAE_TYPE, platformMessageType);
        deeplinkUri.appendQueryParameter(KEY_PLATFORM_REQUEST_ID, platformRequestID);
        Intent intent = new Intent().setAction("com.oculus.vrshell.intent.action.CONTINUE_ABUSE_REPORT").setComponent(new ComponentName("com.oculus.vrshell", "com.oculus.vrshell.ShellControlBroadcastReceiver")).putExtra("intent_data", "systemux://user_report").putExtra("uri", deeplinkUri.toString()).putExtra(KEY_CONTINUE_REPORT_IN_OVERLAY, String.valueOf(continueReportInOverlay));
        if (!continueReportInOverlay) {
            intent.setData(Uri.parse("com.oculus.vrshell.home/com.oculus.vrshell.home.SystemUtilitiesService"));
        }
        try {
            this.mContext.sendBroadcast(intent);
        } catch (Exception ex) {
            BLog.e(TAG, "Error broadcasting intent to start report flow", ex);
        }
    }

    private static ResultReceiver getCrossPackageResultReceiver(ResultReceiver resultReceiver) {
        Parcel parcel = Parcel.obtain();
        resultReceiver.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        ResultReceiver crossPackageResultReceiver = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
        parcel.recycle();
        return crossPackageResultReceiver;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private JSONObject bundleToString(Bundle bundle) {
        JSONObject json = new JSONObject();
        if (bundle != null) {
            for (String key : bundle.keySet()) {
                try {
                    json.put(key, JSONObject.wrap(bundle.get(key)));
                } catch (Exception ex) {
                    BLog.e(TAG, "Error converting bundle object to a JSONObject ", ex);
                }
            }
        }
        return json;
    }

    public void launchAbuseRecordingOverlay(JSONObject overlayParams) {
        try {
            BLog.i(TAG, "Sending intent to OVRMediaService to start recording overlay with params=[%s]", overlayParams.toString());
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.oculus.horizon", OVR_MEDIA_SERVICE_NAME));
            intent.putExtra(KEY_MESSAGE_TYPE, "show_abuse_recording_overlay");
            intent.putExtra(KEY_OVERLAY_PARAMS, overlayParams.toString());
            this.mContext.startService(intent);
        } catch (Exception e) {
            BLog.e(TAG, e, "Sending intent to OVRMediaService to launch recording overlay failed.");
        }
    }

    public void launchVideoUploaderService(String reportID, String recordingUUID) {
        try {
            BLog.i(TAG, "Sending intent to the Video Uploader Service with report_id=[%s] recording_uuid=[%s]", reportID, recordingUUID);
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.oculus.horizon", VIDEO_UPLOADER_SERVICE_NAME));
            intent.putExtra(KEY_RECORDING_UUID, recordingUUID);
            intent.putExtra("report_id", reportID);
            this.mContext.startService(intent);
        } catch (Exception e) {
            BLog.e(TAG, e, "Sending intent to the Video Uploader Service failed.");
        }
    }

    public void getAbuseReportVideo(String recordingUUID, final int resolveID, final int rejectID) {
        BLog.i(TAG, "getAbuseReportVideo() called, recordingUUID=%s", recordingUUID);
        Intent intent = new Intent(ACTION_REQUEST_EVIDENCE_VIDEO);
        intent.putExtra(KEY_RECORDING_UUID, recordingUUID);
        intent.setPackage("com.oculus.horizon");
        this.mContext.sendOrderedBroadcast(intent, null, new BroadcastReceiver() {
            /* class com.oculus.modules.AbuseReportModule.AnonymousClass3 */

            public void onReceive(Context context, Intent intent) {
                try {
                    String uriString = getResultExtras(true).getString("URI");
                    BLog.d(AbuseReportModule.TAG, "URI='%s'", uriString);
                    if (uriString == null) {
                        throw new Exception("null uriString");
                    }
                    Uri uri = Uri.parse(uriString);
                    if (uri == null) {
                        throw new Exception("null URI, uriString=" + uriString);
                    }
                    String scheme = uri.getScheme();
                    if (!scheme.equals("content")) {
                        throw new Exception(String.format("expecting content URI, got %s uri: %s", scheme, uri.toString()));
                    }
                    JSONArray promiseArgs = new JSONArray();
                    promiseArgs.put(uri);
                    AbuseReportModule.nativeInvokeCallback(AbuseReportModule.this.RVRCtxTag, resolveID, promiseArgs.toString());
                } catch (Exception exception) {
                    BLog.e(AbuseReportModule.TAG, exception, "getAbuseReportVideo failed");
                    JSONObject promiseObject = new JSONObject();
                    try {
                        promiseObject.put("message", exception.getMessage());
                    } catch (JSONException e) {
                    }
                    JSONArray promiseArgs2 = new JSONArray();
                    promiseArgs2.put(promiseObject);
                    AbuseReportModule.nativeInvokeCallback(AbuseReportModule.this.RVRCtxTag, rejectID, promiseArgs2.toString());
                }
            }
        }, null, -1, null, null);
    }

    public void copyMediaForReport(String mediaPath, final int resolveID, int rejectID) {
        BLog.i(TAG, "copyMediaForReport() called, mediaPath = %s", mediaPath);
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.oculus.horizon", OVR_MEDIA_SERVICE_NAME));
            intent.putExtra(KEY_MESSAGE_TYPE, COPY_MEDIA_FILE_FOR_ABUSE_REPORT);
            intent.putExtra(KEY_MEDIA_PATH, mediaPath);
            intent.putExtra("result_receiver", getCrossPackageResultReceiver(new ResultReceiver(null) {
                /* class com.oculus.modules.AbuseReportModule.AnonymousClass4 */

                /* access modifiers changed from: protected */
                public void onReceiveResult(int resultCode, Bundle resultData) {
                    if (resultCode == -1 && resultData != null) {
                        String videoUUID = resultData.getString(AbuseReportModule.KEY_COPY_MEDIA_FOR_ABUSE_REPORT_UUID);
                        BLog.d(AbuseReportModule.TAG, "onReceiveResult, copied video uuid = %s ", videoUUID);
                        if (videoUUID != null) {
                            JSONArray promiseArgs = new JSONArray();
                            promiseArgs.put(videoUUID);
                            AbuseReportModule.nativeInvokeCallback(AbuseReportModule.this.RVRCtxTag, resolveID, promiseArgs.toString());
                            return;
                        }
                        BLog.e(AbuseReportModule.TAG, "onReceiveResult, copied videoUUID is null.");
                    }
                }
            }));
            this.mContext.startService(intent);
        } catch (Exception exception) {
            BLog.e(TAG, exception, "copyMediaForReport failed.");
            JSONObject promiseObject = new JSONObject();
            try {
                promiseObject.put("message", exception.getMessage());
            } catch (JSONException e) {
            }
            JSONArray promiseArgs = new JSONArray();
            promiseArgs.put(promiseObject);
            nativeInvokeCallback(this.RVRCtxTag, rejectID, promiseArgs.toString());
        }
    }

    public static List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("copyMediaForReport", "+sii"));
        list.add(new Pair<>("getAbuseReportVideo", "+sii"));
        list.add(new Pair<>("launchVideoUploaderService", "ss"));
        list.add(new Pair<>("launchAbuseRecordingOverlay", "j"));
        list.add(new Pair<>("startRecordingForReportFlow", "+jbii"));
        list.add(new Pair<>("stopRecordingAndLaunchReportFlow", "j"));
        return list;
    }

    public void shutdownModule() {
    }
}
