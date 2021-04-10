package com.oculus.horizon.abuse_prevention;

import X.AbstractC06640p5;
import X.AnonymousClass03h;
import X.AnonymousClass04J;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.AnonymousClass117;
import X.C003108z;
import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import androidx.core.app.NotificationCompat$BigTextStyle;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.R;
import com.oculus.horizon.abuse_prevention.interfaces.AbuseCaptureProvider;
import com.oculus.horizon.logging.OculusLogger;
import com.oculus.horizon.notifications.core.NotificationBuilder;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_logging_OculusLogger_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_abuse_ULUNDERSCORE_prevention_interfaces_AbuseCaptureProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_notifications_core_NotificationBuilder_ULSEP_BINDING_ID"})
public class AbuseAVCapture {
    public static final long ABUSE_CAPTURE_AV_RECORDING_TIMELIMIT = 150000;
    public static final String EXTRA_ABUSE_REPORT_OBJECT_TYPE = "abuse_report_object_type";
    public static final String EXTRA_ABUSE_REPORT_TYPE = "abuse_report_type";
    public static final String EXTRA_PLATFORM_MESSAGE_TYPE = "platform_message_type";
    public static final String EXTRA_PLATFORM_REQUEST_ID = "platform_request_id";
    public static final String EXTRA_PREVENT_PEOPLE_CHOOSER = "prevent_people_chooser";
    public static final String EXTRA_REPORT_FLOW_SESSION_ID = "report_flow_session_id";
    public static final String EXTRA_SDK_USER_ID = "sdk_user_id";
    public static final String EXTRA_SOURCE = "source";
    public static final String TAG = "AbuseAVCapture";
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    @Eager
    public final AbuseCaptureProvider mAbuseCapture;
    @Nullable
    public JSONObject mAbuseReportParams;
    public final Map<String, AbuseCaptureTimeoutTask> mCaptureTimeoutTasks = Collections.synchronizedMap(new HashMap());
    @UnsafeContextInjection
    @Inject
    @Eager
    public final Context mContext;
    public final Handler mHandler = new Handler();
    public final Object mLock = new Object();
    @Inject
    @Eager
    public final OculusLogger mOculusLogger;
    @Nullable
    public String mRecordingUUID;

    public class AbuseCaptureTimeoutTask implements Runnable {
        @Nullable
        public final JSONObject mAbuseReportParams;
        public final String mPackageName;
        public final String mRecordingUUID;

        public AbuseCaptureTimeoutTask(String str, String str2, @Nullable JSONObject jSONObject) {
            this.mPackageName = str;
            this.mRecordingUUID = str2;
            this.mAbuseReportParams = jSONObject;
        }

        public final void run() {
            AbuseAVCapture.this.A05(this.mPackageName, this.mRecordingUUID, StopSource.RECORDING_TIMEOUT);
            JSONObject jSONObject = this.mAbuseReportParams;
            if (jSONObject != null) {
                AbuseAVCapture abuseAVCapture = AbuseAVCapture.this;
                String str = this.mPackageName;
                String str2 = this.mRecordingUUID;
                jSONObject.toString();
                try {
                    String string = jSONObject.getString("source");
                    String string2 = jSONObject.getString("sdk_user_id");
                    Uri.Builder appendQueryParameter = new Uri.Builder().encodedPath("/system_utilities/user_report/").appendPath(str).appendPath(string2).appendPath(string).appendQueryParameter("abuse_report_type", jSONObject.getString("abuse_report_type")).appendQueryParameter("recording_uuid", str2);
                    if (jSONObject.has("prevent_people_chooser") && !jSONObject.isNull("prevent_people_chooser") && jSONObject.getBoolean("prevent_people_chooser")) {
                        appendQueryParameter.appendQueryParameter("prevent_people_chooser", "true");
                    }
                    if (jSONObject.has("platform_message_type")) {
                        appendQueryParameter.appendQueryParameter("platform_message_type", jSONObject.getString("platform_message_type"));
                    }
                    if (jSONObject.has("platform_request_id")) {
                        appendQueryParameter.appendQueryParameter("platform_request_id", jSONObject.getString("platform_request_id"));
                    }
                    if (jSONObject.has("report_flow_session_id")) {
                        appendQueryParameter.appendQueryParameter("report_flow_session_id", jSONObject.getString("report_flow_session_id"));
                    }
                    if (jSONObject.has("abuse_report_object_type")) {
                        appendQueryParameter.appendQueryParameter("abuse_report_object_type", jSONObject.getString("abuse_report_object_type"));
                    }
                    abuseAVCapture.mContext.sendBroadcast(new Intent().setAction("com.oculus.vrshell.intent.action.CONTINUE_ABUSE_REPORT").setComponent(new ComponentName("com.oculus.vrshell", "com.oculus.vrshell.ShellControlBroadcastReceiver")).putExtra("intent_data", "systemux://user_report").putExtra("uri", appendQueryParameter.toString()).setData(Uri.parse("com.oculus.vrshell.home/com.oculus.vrshell.home.SystemUtilitiesService")));
                } catch (JSONException e) {
                    AnonymousClass0NO.A0K(AbuseAVCapture.TAG, e, "Cannot parse overlay params from JSONObject input: [%s]", jSONObject.toString());
                }
            }
        }
    }

    public enum CancelSource {
        USER,
        NEW_START,
        START_FAILURE,
        OTHER_CAPTURE
    }

    public enum StopSource {
        USER,
        RECORDING_TIMEOUT
    }

    private void A00(String str, String str2) throws IOException {
        synchronized (this.mLock) {
            if (!A03() || str2 == null || str2.isEmpty() || !this.mRecordingUUID.equals(str2)) {
                String format = String.format("stopRecording failed because input uuid: %s did not match current: %s", str2, this.mRecordingUUID);
                AnonymousClass0NO.A08(TAG, format);
                throw new IOException(format);
            }
        }
        this.mAbuseCapture.A9R(str);
        synchronized (this.mLock) {
            this.mRecordingUUID = null;
            this.mAbuseReportParams = null;
        }
        AbuseCaptureTimeoutTask remove = this.mCaptureTimeoutTasks.remove(str2);
        if (remove != null) {
            this.mHandler.removeCallbacks(remove);
        }
    }

    @Nullable
    public final String A01(String str, @Nullable JSONObject jSONObject) {
        String str2;
        File[] listFiles;
        synchronized (this.mLock) {
            if (A03()) {
                A04(str, this.mRecordingUUID, CancelSource.NEW_START);
            }
            this.mAbuseCapture.A9R(str);
            File file = new File(this.mContext.getCacheDir(), AbuseReportFileUtils.RECORDING_PATH);
            if (file.exists() && file.isDirectory() && (listFiles = file.listFiles()) != null) {
                for (File file2 : listFiles) {
                    new File(file2, AbuseReportFileUtils.VIDEO_RECORDING_FILE).delete();
                    file2.delete();
                }
            }
            String obj = UUID.randomUUID().toString();
            this.mRecordingUUID = obj;
            this.mAbuseReportParams = jSONObject;
            try {
                AbuseCaptureProvider abuseCaptureProvider = this.mAbuseCapture;
                File A00 = AbuseReportFileUtils.A00(this.mContext, obj);
                AbuseReportFileUtils.A04(A00);
                abuseCaptureProvider.A9F(str, new File(A00, AbuseReportFileUtils.VIDEO_RECORDING_FILE), this.mRecordingUUID);
                String str3 = this.mRecordingUUID;
                AbuseCaptureTimeoutTask abuseCaptureTimeoutTask = new AbuseCaptureTimeoutTask(str, str3, jSONObject);
                this.mHandler.postDelayed(abuseCaptureTimeoutTask, ABUSE_CAPTURE_AV_RECORDING_TIMELIMIT);
                this.mCaptureTimeoutTasks.put(str3, abuseCaptureTimeoutTask);
                Context context = this.mContext;
                ComponentName componentName = new ComponentName(context, VideoUploaderCleanerService.class);
                JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
                Iterator<JobInfo> it = jobScheduler.getAllPendingJobs().iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().getId() == 8192) {
                            break;
                        }
                    } else {
                        jobScheduler.schedule(new JobInfo.Builder(8192, componentName).setPeriodic(TimeUnit.DAYS.toMillis(1)).setRequiresDeviceIdle(true).setPersisted(true).build());
                        break;
                    }
                }
                this.mOculusLogger.A01(this.mRecordingUUID, "");
                str2 = this.mRecordingUUID;
            } catch (IOException e) {
                A04(str, this.mRecordingUUID, CancelSource.START_FAILURE);
                this.mOculusLogger.A01(this.mRecordingUUID, e.toString());
                AnonymousClass0NO.A0B(TAG, "Failed to start Abuse Recording", e);
                str2 = null;
            } catch (Exception e2) {
                this.mOculusLogger.A01(this.mRecordingUUID, e2.toString());
                AnonymousClass0NO.A0B(TAG, "Failed to start Abuse Recording", e2);
                throw e2;
            }
        }
        return str2;
    }

    public final void A02(String str) {
        JSONObject jSONObject;
        String string;
        String string2;
        Resources resources;
        int i;
        synchronized (this.mLock) {
            jSONObject = this.mAbuseReportParams;
        }
        if (jSONObject != null) {
            if (str.equals("com.oculus.shellenv")) {
                string = this.mContext.getString(R.string.abuse_recording_title);
                string2 = this.mContext.getString(R.string.abuse_recording_how_to_stop_subtitle_no_app_running);
                resources = this.mContext.getResources();
                i = R.drawable.ic_notif_share;
            } else {
                string = this.mContext.getString(R.string.abuse_recording_title);
                string2 = this.mContext.getString(R.string.abuse_recording_how_to_stop_subtitle_in_app);
                resources = this.mContext.getResources();
                i = R.drawable.ic_notif_oculus;
            }
            Bitmap decodeResource = BitmapFactory.decodeResource(resources, i);
            int incrementAndGet = ((NotificationBuilder) AnonymousClass0J2.A03(0, 14, this._UL_mInjectionContext)).mNextNotificationId.incrementAndGet();
            Bundle bundle = new Bundle();
            bundle.putString("oculus_notification_type", NotificationBuilder.ABUSE_CAPTURE_RECORDING);
            bundle.putBoolean("vrshell_aui_persist", false);
            bundle.putString("oculus_category", "social");
            bundle.putString("aui_notif_duration", "LONG");
            AnonymousClass03h r2 = new AnonymousClass03h(this.mContext, null);
            r2.A0E = AnonymousClass03h.A00(string);
            r2.A09.icon = R.drawable.status_icon;
            r2.A04(decodeResource);
            r2.A07(string);
            r2.A0D = AnonymousClass03h.A00(string2);
            NotificationCompat$BigTextStyle notificationCompat$BigTextStyle = new NotificationCompat$BigTextStyle();
            notificationCompat$BigTextStyle.bigText(string2);
            r2.A06(notificationCompat$BigTextStyle);
            r2.A05 = AnonymousClass04J.A00(this.mContext, R.color.oculus_black_10);
            r2.A02();
            r2.A06 = 1;
            r2.A05(bundle);
            NotificationManager notificationManager = (NotificationManager) this.mContext.getSystemService("notification");
            if (notificationManager != null) {
                notificationManager.notify(incrementAndGet, r2.A01());
            }
        }
    }

    public final boolean A03() {
        boolean z;
        synchronized (this.mLock) {
            z = false;
            if (this.mRecordingUUID != null) {
                z = true;
            }
        }
        return z;
    }

    public final boolean A04(String str, String str2, CancelSource cancelSource) {
        Throwable th;
        boolean z = true;
        try {
            A00(str, str2);
            AbuseReportFileUtils.A01(this.mContext, str2);
            this.mOculusLogger.A02(str2, cancelSource.toString(), true, "");
            return true;
        } catch (IOException e) {
            AnonymousClass0NO.A0B(TAG, "Cancel failed.", e);
            String obj = e.toString();
            AbuseReportFileUtils.A01(this.mContext, str2);
            this.mOculusLogger.A02(str2, cancelSource.toString(), false, obj);
            return false;
        } catch (Exception e2) {
            AnonymousClass0NO.A0B(TAG, "Cancel failed.", e2);
            e2.toString();
            throw e2;
        } catch (Throwable th2) {
            th = th2;
            z = false;
            AbuseReportFileUtils.A01(this.mContext, str2);
            this.mOculusLogger.A02(str2, cancelSource.toString(), z, "");
            throw th;
        }
    }

    public final boolean A05(String str, String str2, StopSource stopSource) {
        Throwable th;
        boolean z = true;
        try {
            A00(str, str2);
            AbuseReportFileUtils.A02(this.mContext, str2);
            this.mOculusLogger.A03(str2, stopSource.toString(), true, "");
            return true;
        } catch (IOException e) {
            AnonymousClass0NO.A0B(TAG, "Stop failed.", e);
            this.mOculusLogger.A03(str2, stopSource.toString(), false, e.toString());
            return false;
        } catch (Exception e2) {
            AnonymousClass0NO.A0B(TAG, "Stop failed.", e2);
            e2.toString();
            throw e2;
        } catch (Throwable th2) {
            th = th2;
            z = false;
            this.mOculusLogger.A03(str2, stopSource.toString(), z, "");
            throw th;
        }
    }

    @Inject
    public AbuseAVCapture(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
        this.mContext = C003108z.A02(r3);
        this.mOculusLogger = (OculusLogger) AnonymousClass117.A00(574, r3);
        this.mAbuseCapture = (AbuseCaptureProvider) AnonymousClass117.A00(323, r3);
    }
}
