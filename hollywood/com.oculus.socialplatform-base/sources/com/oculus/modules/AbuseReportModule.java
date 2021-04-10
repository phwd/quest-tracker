package com.oculus.modules;

import X.AnonymousClass006;
import X.AnonymousClass0MD;
import X.AnonymousClass13N;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.ResultReceiver;
import android.util.Pair;
import com.oculus.horizon.logging.LoggingKeys;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AbuseReportModule extends RCTBaseJavaModule {
    public static final String ABUSE_REPORT_PACKAGE_NAME = "abuse_report_package_name";
    public static final String ACTION_REQUEST_EVIDENCE_VIDEO = "com.oculus.horizon.abuse_prevention.ACTION_REQUEST_EVIDENCE_VIDEO";
    public static final String BROADCAST_CAPTURE_ABUSE_START = "broadcast_abuse_capture_start";
    public static final String BROADCAST_CAPTURE_ABUSE_STOP = "broadcast_abuse_capture_stop";
    public static final String COPY_MEDIA_FILE_FOR_ABUSE_REPORT = "com.oculus.horizon.COPY_MEDIA_FILE_FOR_ABUSE_REPORT";
    public static final AnonymousClass13N GSON;
    public static final String HORIZON_PACKAGE_NAME = "com.oculus.horizon";
    public static final String KEY_ABUSE_REPORT_OBJECT_TYPE = "abuse_report_object_type";
    public static final String KEY_ABUSE_REPORT_PARAMS = "abuse_report_params";
    public static final String KEY_ABUSE_REPORT_TYPE = "abuse_report_type";
    public static final String KEY_CONTINUE_REPORT_IN_OVERLAY = "continue_report_in_overlay";
    public static final String KEY_COPY_MEDIA_FOR_ABUSE_REPORT_UUID = "copy_media_for_abuse_report_uuid";
    public static final String KEY_FROM_PKG = "from_pkg";
    public static final String KEY_MEDIA_PATH = "media_path";
    public static final String KEY_MESSAGE_TYPE = "message_type";
    public static final String KEY_OVERLAY_PARAMS = "recording_overlay_params";
    public static final String KEY_PLATFORM_MESSGAE_TYPE = "platform_message_type";
    public static final String KEY_PLATFORM_REQUEST_ID = "platform_request_id";
    public static final String KEY_PREVENT_PEOPLE_CHOOSER = "prevent_people_chooser";
    public static final String KEY_RECORDING_COMMAND_STATUS = "recording_command_status";
    public static final String KEY_RECORDING_UUID = "recording_uuid";
    public static final String KEY_REPORT_FLOW_SESSION_ID = "report_flow_session_id";
    public static final String KEY_RESULT_RECEIVER = "result_receiver";
    public static final String KEY_SDK_USER_ID = "sdk_user_id";
    public static final String KEY_SOURCE = "source";
    public static final String MODULE_NAME = "AbuseReportModule";
    public static final String OVR_MEDIA_SERVICE_NAME = "com.oculus.horizon.service_media.OVRMediaService";
    public static final String TAG = MODULE_NAME;
    public static final String VIDEO_UPLOADER_SERVICE_NAME = "com.oculus.horizon.abuse_prevention.VideoUploaderService";
    public Context mContext = null;

    public void shutdownModule() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0018, code lost:
        r0 = move-exception;
     */
    static {
        /*
            java.lang.String r2 = "AbuseReportModule"
            com.oculus.modules.AbuseReportModule.TAG = r2
            java.lang.String r0 = "GSON"
            com.oculus.panellib.SystraceBlock r1 = new com.oculus.panellib.SystraceBlock
            r1.<init>(r2, r0)
            X.13N r0 = new X.13N     // Catch:{ all -> 0x0016 }
            r0.<init>()     // Catch:{ all -> 0x0016 }
            com.oculus.modules.AbuseReportModule.GSON = r0     // Catch:{ all -> 0x0016 }
            r1.close()
            return
        L_0x0016:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0018 }
        L_0x0018:
            r0 = move-exception
            r1.close()     // Catch:{ all -> 0x001c }
        L_0x001c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.AbuseReportModule.<clinit>():void");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private JSONObject bundleToString(Bundle bundle) {
        JSONObject jSONObject = new JSONObject();
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                try {
                    jSONObject.put(str, JSONObject.wrap(bundle.get(str)));
                } catch (Exception e) {
                    AnonymousClass0MD.A07(MODULE_NAME, "Error converting bundle object to a JSONObject ", e);
                }
            }
        }
        return jSONObject;
    }

    public static List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("copyMediaForReport", "+sii"));
        arrayList.add(new Pair("getAbuseReportVideo", "+sii"));
        arrayList.add(new Pair("launchVideoUploaderService", "ss"));
        arrayList.add(new Pair("launchAbuseRecordingOverlay", "j"));
        arrayList.add(new Pair("startRecordingForReportFlow", "+jbii"));
        arrayList.add(new Pair("stopRecordingAndLaunchReportFlow", "j"));
        return arrayList;
    }

    public void copyMediaForReport(String str, final int i, int i2) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.oculus.horizon", "com.oculus.horizon.service_media.OVRMediaService"));
            intent.putExtra("message_type", COPY_MEDIA_FILE_FOR_ABUSE_REPORT);
            intent.putExtra(KEY_MEDIA_PATH, str);
            intent.putExtra("result_receiver", getCrossPackageResultReceiver(new ResultReceiver(null) {
                /* class com.oculus.modules.AbuseReportModule.AnonymousClass4 */

                public void onReceiveResult(int i, Bundle bundle) {
                    if (i == -1 && bundle != null) {
                        String string = bundle.getString(AbuseReportModule.KEY_COPY_MEDIA_FOR_ABUSE_REPORT_UUID);
                        if (string != null) {
                            JSONArray jSONArray = new JSONArray();
                            jSONArray.put(string);
                            RCTBaseJavaModule.nativeInvokeCallback(AbuseReportModule.this.RVRCtxTag, i, jSONArray.toString());
                            return;
                        }
                        AnonymousClass0MD.A04(AbuseReportModule.MODULE_NAME, "onReceiveResult, copied videoUUID is null.");
                    }
                }
            }));
            this.mContext.startService(intent);
        } catch (Exception e) {
            AnonymousClass0MD.A0C(MODULE_NAME, e, "copyMediaForReport failed.");
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("message", e.getMessage());
            } catch (JSONException unused) {
            }
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject);
            RCTBaseJavaModule.nativeInvokeCallback(this.RVRCtxTag, i2, jSONArray.toString());
        }
    }

    public void getAbuseReportVideo(String str, final int i, final int i2) {
        Intent intent = new Intent(ACTION_REQUEST_EVIDENCE_VIDEO);
        intent.putExtra("recording_uuid", str);
        intent.setPackage("com.oculus.horizon");
        this.mContext.sendOrderedBroadcast(intent, null, new BroadcastReceiver() {
            /* class com.oculus.modules.AbuseReportModule.AnonymousClass3 */

            public void onReceive(Context context, Intent intent) {
                try {
                    String string = getResultExtras(true).getString("URI");
                    if (string != null) {
                        Uri parse = Uri.parse(string);
                        if (parse != null) {
                            String scheme = parse.getScheme();
                            if (scheme.equals(LoggingKeys.REFERRER_CONTENT)) {
                                JSONArray jSONArray = new JSONArray();
                                jSONArray.put(parse);
                                RCTBaseJavaModule.nativeInvokeCallback(AbuseReportModule.this.RVRCtxTag, i, jSONArray.toString());
                                return;
                            }
                            throw new Exception(String.format("expecting content URI, got %s uri: %s", scheme, parse.toString()));
                        }
                        throw new Exception(AnonymousClass006.A07("null URI, uriString=", string));
                    }
                    throw new Exception("null uriString");
                } catch (Exception e) {
                    AnonymousClass0MD.A0C(AbuseReportModule.MODULE_NAME, e, "getAbuseReportVideo failed");
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("message", e.getMessage());
                    } catch (JSONException unused) {
                    }
                    JSONArray jSONArray2 = new JSONArray();
                    jSONArray2.put(jSONObject);
                    RCTBaseJavaModule.nativeInvokeCallback(AbuseReportModule.this.RVRCtxTag, i2, jSONArray2.toString());
                }
            }
        }, null, -1, null, null);
    }

    public void launchVideoUploaderService(String str, String str2) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.oculus.horizon", VIDEO_UPLOADER_SERVICE_NAME));
            intent.putExtra("recording_uuid", str2);
            intent.putExtra(LoggingKeys.REPORT_ID, str);
            this.mContext.startService(intent);
        } catch (Exception e) {
            AnonymousClass0MD.A0C(MODULE_NAME, e, "Sending intent to the Video Uploader Service failed.");
        }
    }

    public void stopRecordingAndLaunchReportFlow(JSONObject jSONObject) {
        try {
            final String string = jSONObject.getString(KEY_ABUSE_REPORT_OBJECT_TYPE);
            final String string2 = jSONObject.getString(KEY_ABUSE_REPORT_TYPE);
            final String string3 = jSONObject.getString(KEY_PREVENT_PEOPLE_CHOOSER);
            final String string4 = jSONObject.getString("recording_uuid");
            final String string5 = jSONObject.getString(KEY_REPORT_FLOW_SESSION_ID);
            final String string6 = jSONObject.getString(KEY_SDK_USER_ID);
            jSONObject.getString(KEY_FROM_PKG);
            final String string7 = jSONObject.getString("source");
            final String string8 = jSONObject.getString(KEY_PLATFORM_MESSGAE_TYPE);
            final String string9 = jSONObject.getString(KEY_PLATFORM_REQUEST_ID);
            final Boolean valueOf = Boolean.valueOf(jSONObject.getBoolean(KEY_CONTINUE_REPORT_IN_OVERLAY));
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.oculus.horizon", "com.oculus.horizon.service_media.OVRMediaService"));
            intent.putExtra("message_type", BROADCAST_CAPTURE_ABUSE_STOP);
            intent.putExtra("recording_uuid", string4);
            intent.putExtra("result_receiver", getCrossPackageResultReceiver(new ResultReceiver(null) {
                /* class com.oculus.modules.AbuseReportModule.AnonymousClass2 */

                public void onReceiveResult(int i, Bundle bundle) {
                    if (i != -1) {
                        AnonymousClass0MD.A09(AbuseReportModule.MODULE_NAME, "Something went wrong while stopping abuse capture: [%s]", bundle);
                    }
                    AbuseReportModule.this.launchReportFlow(string, string2, string3, string4, string5, string6, bundle.getString(AbuseReportModule.ABUSE_REPORT_PACKAGE_NAME), string7, string8, string9, valueOf.booleanValue());
                }
            }));
            this.mContext.startService(intent);
        } catch (Exception e) {
            AnonymousClass0MD.A0C(MODULE_NAME, e, "Sending intent to OVRMediaService to stop abuse capture failed.");
        }
    }

    public AbuseReportModule(Context context) {
        this.mContext = context;
    }

    public static /* synthetic */ String access$400() {
        return MODULE_NAME;
    }

    public static ResultReceiver getCrossPackageResultReceiver(ResultReceiver resultReceiver) {
        Parcel obtain = Parcel.obtain();
        resultReceiver.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        ResultReceiver resultReceiver2 = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return resultReceiver2;
    }

    public String getModuleName() {
        return MODULE_NAME;
    }

    public void launchAbuseRecordingOverlay(JSONObject jSONObject) {
        try {
            jSONObject.toString();
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.oculus.horizon", "com.oculus.horizon.service_media.OVRMediaService"));
            intent.putExtra("message_type", "show_abuse_recording_overlay");
            intent.putExtra(KEY_OVERLAY_PARAMS, jSONObject.toString());
            this.mContext.startService(intent);
        } catch (Exception e) {
            AnonymousClass0MD.A0C(MODULE_NAME, e, "Sending intent to OVRMediaService to launch recording overlay failed.");
        }
    }

    public void startRecordingForReportFlow(JSONObject jSONObject, boolean z, final int i, final int i2) {
        try {
            jSONObject.toString();
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.oculus.horizon", "com.oculus.horizon.service_media.OVRMediaService"));
            intent.putExtra("message_type", BROADCAST_CAPTURE_ABUSE_START);
            if (z) {
                intent.putExtra(KEY_ABUSE_REPORT_PARAMS, jSONObject.toString());
            }
            intent.putExtra("result_receiver", getCrossPackageResultReceiver(new ResultReceiver(null) {
                /* class com.oculus.modules.AbuseReportModule.AnonymousClass1 */

                public void onReceiveResult(int i, Bundle bundle) {
                    long j;
                    int i2;
                    String obj;
                    if (i == -1 && bundle != null) {
                        String string = bundle.getString("recording_uuid");
                        if (Boolean.valueOf(bundle.getBoolean(AbuseReportModule.KEY_RECORDING_COMMAND_STATUS, false)).booleanValue() && string != null && !string.isEmpty()) {
                            j = AbuseReportModule.this.RVRCtxTag;
                            i2 = i;
                            obj = AbuseReportModule.GSON.A06(new String[]{string});
                            RCTBaseJavaModule.nativeInvokeCallback(j, i2, obj);
                        }
                    }
                    AbuseReportModule abuseReportModule = AbuseReportModule.this;
                    j = abuseReportModule.RVRCtxTag;
                    i2 = i2;
                    StringBuilder sb = new StringBuilder("[");
                    sb.append(abuseReportModule.bundleToString(bundle));
                    sb.append("]");
                    obj = sb.toString();
                    RCTBaseJavaModule.nativeInvokeCallback(j, i2, obj);
                }
            }));
            this.mContext.startService(intent);
        } catch (Exception e) {
            RCTBaseJavaModule.nativeInvokeCallback(this.RVRCtxTag, i2, AnonymousClass006.A09("[", GSON.A06(e.getMessage()), "]"));
            AnonymousClass0MD.A0C(MODULE_NAME, e, "Sending intent to OVRMediaService to start abuse capture failed.");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void launchReportFlow(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, boolean z) {
        Uri.Builder appendPath = new Uri.Builder().encodedPath("/system_utilities/user_report/").appendPath(str7).appendPath(str6).appendPath(str8);
        if (str4 != null) {
            appendPath.appendQueryParameter("recording_uuid", str4);
        }
        if (str3 != null) {
            appendPath.appendQueryParameter(KEY_PREVENT_PEOPLE_CHOOSER, str3);
        }
        appendPath.appendQueryParameter(KEY_ABUSE_REPORT_OBJECT_TYPE, str);
        appendPath.appendQueryParameter(KEY_ABUSE_REPORT_TYPE, str2);
        appendPath.appendQueryParameter(KEY_REPORT_FLOW_SESSION_ID, str5);
        appendPath.appendQueryParameter(KEY_PLATFORM_MESSGAE_TYPE, str9);
        appendPath.appendQueryParameter(KEY_PLATFORM_REQUEST_ID, str10);
        Intent putExtra = new Intent().setAction("com.oculus.vrshell.intent.action.CONTINUE_ABUSE_REPORT").setComponent(new ComponentName("com.oculus.vrshell", "com.oculus.vrshell.ShellControlBroadcastReceiver")).putExtra("intent_data", "systemux://user_report").putExtra("uri", appendPath.toString()).putExtra(KEY_CONTINUE_REPORT_IN_OVERLAY, String.valueOf(z));
        if (!z) {
            putExtra.setData(Uri.parse("com.oculus.vrshell.home/com.oculus.vrshell.home.SystemUtilitiesService"));
        }
        try {
            this.mContext.sendBroadcast(putExtra);
        } catch (Exception e) {
            AnonymousClass0MD.A07(MODULE_NAME, "Error broadcasting intent to start report flow", e);
        }
    }
}
