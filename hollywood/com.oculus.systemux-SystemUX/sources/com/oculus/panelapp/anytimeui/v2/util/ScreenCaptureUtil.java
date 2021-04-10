package com.oculus.panelapp.anytimeui.v2.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.ResultReceiver;
import android.util.Log;
import com.facebook.debug.log.BLog;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.CaptureStateUtil;
import com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil;
import com.oculus.vrshell.util.CallerInfoHelper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.json.JSONException;
import org.json.JSONObject;

public final class ScreenCaptureUtil implements CaptureStateUtil.CaptureStateObserver {
    private static final String BROADCAST_CAPTURE_ABUSE_STOP = "broadcast_abuse_capture_stop";
    private static final String CAPTURE_COMPONENT_NAME = "com.oculus.horizon.service_media.OVRMediaService";
    private static final String CAPTURE_PACKAGE_NAME = "com.oculus.horizon";
    private static final String INTENT_KEY_SCREENSHOT_DELAY = "delayInSeconds";
    private static final String INTENT_KEY_SCREENSHOT_HEIGHT = "screenshot_height";
    private static final String INTENT_KEY_SCREENSHOT_WIDTH = "screenshot_width";
    private static final String KEY_ABUSE_REPORT_PARAMS = "abuse_report_params";
    private static final String KEY_CAPTURE_TARGET_PACKAGE = "target_package";
    private static final String KEY_MESSAGE_TYPE = "message_type";
    private static final String KEY_RECORDING_UUID = "recording_uuid";
    private static final String KEY_RESULT_RECEIVER = "result_receiver";
    private static final String START_SCREENSHOT_HORIZON_ACTION = "com.oculus.horizon.TAKE_SCREENSHOT";
    public static final String START_VIDEO_CAPTURE_HORIZON_ACTION = "com.oculus.horizon.START_CAPTURE_TO_DISK";
    private static final String STOP_VIDEO_CAPTURE_HORIZON_ACTION = "com.oculus.horizon.STOP_CAPTURE_TO_DISK";
    private static final String TAG = LoggingUtil.tag(ScreenCaptureUtil.class);
    private String mAbuseRecordingUUID = null;
    private boolean mCaptureAllowed = true;
    private boolean mCapturingVideo = false;
    private Context mContext;
    private String mCurrentPackageForCapture = "";
    private boolean mIsCapturingAbuseReport = false;
    private boolean mIsInitialized = false;
    private boolean mIsLocalStreaming = false;
    private boolean mLiveStreamAllowed = true;
    private boolean mLiveStreaming = false;
    private final List<WeakReference<ChangeObserver>> mObservers = new ArrayList();
    private List<String> mRestrictedPackagesForCapture;

    public static abstract class ChangeObserver {
        public void onAbuseReportCaptureStateChanged(boolean z, Optional<Long> optional) {
        }

        public void onCaptureAllowedChanged(boolean z) {
        }

        public void onLiveStreamStatusChanged(boolean z, boolean z2) {
        }

        public void onLocalStreamStateChanged(boolean z) {
        }

        public void onVideoCaptureStateChanged(boolean z) {
        }
    }

    /* access modifiers changed from: private */
    public interface StateChangeCallback {
        void onStateChanged(ChangeObserver changeObserver);
    }

    public ScreenCaptureUtil(Context context, String str) {
        this.mRestrictedPackagesForCapture = Arrays.asList(context.getResources().getStringArray(R.array.video_capture_restricted_apps));
        this.mIsInitialized = true;
        this.mContext = context.getApplicationContext();
        setCurrentPackageForCapture(str);
        CaptureStateUtil.addCaptureStateObserver(context, this);
    }

    public void destroy() {
        CaptureStateUtil.removeCaptureStateObserver(this);
    }

    public void setCurrentPackageForCapture(String str) {
        this.mCurrentPackageForCapture = str.split("/")[0];
    }

    public void sendTakeScreenshotBroadcast() {
        Log.d(TAG, "sendTakeScreenshotBroadcast");
        Intent intent = new Intent();
        intent.putExtra(INTENT_KEY_SCREENSHOT_DELAY, 5.0d);
        intent.setComponent(new ComponentName("com.oculus.horizon", CAPTURE_COMPONENT_NAME));
        intent.putExtra(KEY_MESSAGE_TYPE, START_SCREENSHOT_HORIZON_ACTION);
        intent.putExtra(KEY_CAPTURE_TARGET_PACKAGE, this.mCurrentPackageForCapture);
        intent.putExtra(INTENT_KEY_SCREENSHOT_HEIGHT, 1440);
        intent.putExtra(INTENT_KEY_SCREENSHOT_WIDTH, 1440);
        CallerInfoHelper.attachCallerInfo(intent, this.mContext, "ScreenCaptureUtil:startScreenshot()");
        this.mContext.startService(intent);
    }

    private static ResultReceiver getCrossPackageResultReceiver(ResultReceiver resultReceiver) {
        Parcel obtain = Parcel.obtain();
        resultReceiver.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        ResultReceiver resultReceiver2 = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return resultReceiver2;
    }

    public void sendStartVideoCaptureBroadcast() {
        Log.d(TAG, "sendStartVideoCaptureBroadcast");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.horizon", CAPTURE_COMPONENT_NAME));
        intent.putExtra(KEY_MESSAGE_TYPE, START_VIDEO_CAPTURE_HORIZON_ACTION);
        intent.putExtra(KEY_CAPTURE_TARGET_PACKAGE, this.mCurrentPackageForCapture);
        CallerInfoHelper.attachCallerInfo(intent, this.mContext, "ScreenCaptureUtil:startVideoCapture()");
        this.mContext.startService(intent);
    }

    public void sendStopVideoCaptureBroadcast() {
        Log.d(TAG, "sendStopVideoCaptureBroadcast");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.horizon", CAPTURE_COMPONENT_NAME));
        intent.putExtra(KEY_MESSAGE_TYPE, STOP_VIDEO_CAPTURE_HORIZON_ACTION);
        CallerInfoHelper.attachCallerInfo(intent, this.mContext, "ScreenCaptureUtil:stopVideoCapture()");
        this.mContext.startService(intent);
    }

    public void sendStopAbuseReportCaptureBroadcast(final AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        String str = TAG;
        String str2 = this.mAbuseRecordingUUID;
        if (str2 == null) {
            str2 = com.facebook.debug.log.LoggingUtil.NO_HASHCODE;
        }
        BLog.i(str, "sendStopAbuseReportCaptureBroadcast for recording %s", str2);
        final String str3 = this.mAbuseRecordingUUID;
        AnonymousClass1 r1 = new ResultReceiver(null) {
            /* class com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil.AnonymousClass1 */

            /* access modifiers changed from: protected */
            public void onReceiveResult(int i, Bundle bundle) {
                if (i != -1) {
                    BLog.e(ScreenCaptureUtil.TAG, "Something went wrong while stopping abuse capture: [%s]", bundle);
                }
                if (bundle.containsKey(ScreenCaptureUtil.KEY_ABUSE_REPORT_PARAMS)) {
                    try {
                        AbuseReportCaptureUtil.continueToReportFlow(str3, anytimeUIAndroidPanelAppV2, new JSONObject(bundle.getString(ScreenCaptureUtil.KEY_ABUSE_REPORT_PARAMS)));
                    } catch (JSONException e) {
                        BLog.e(ScreenCaptureUtil.TAG, e, "Cannot parse abuse report params from bundle");
                    }
                }
            }
        };
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.horizon", CAPTURE_COMPONENT_NAME));
        intent.putExtra(KEY_MESSAGE_TYPE, BROADCAST_CAPTURE_ABUSE_STOP);
        intent.putExtra(KEY_RECORDING_UUID, this.mAbuseRecordingUUID);
        CallerInfoHelper.attachCallerInfo(intent, this.mContext, "ScreenCaptureUtil:sendStopAbuseReportCaptureBroadcast()");
        intent.putExtra("result_receiver", getCrossPackageResultReceiver(r1));
        this.mContext.startService(intent);
    }

    public void setCaptureAllowed(boolean z) {
        if (this.mCaptureAllowed != z) {
            this.mCaptureAllowed = z;
            iterateObservers(new StateChangeCallback() {
                /* class com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil.AnonymousClass2 */

                @Override // com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil.StateChangeCallback
                public void onStateChanged(ChangeObserver changeObserver) {
                    ScreenCaptureUtil screenCaptureUtil = ScreenCaptureUtil.this;
                    boolean isCaptureAllowed = screenCaptureUtil.isCaptureAllowed(screenCaptureUtil.mCurrentPackageForCapture);
                    ScreenCaptureUtil screenCaptureUtil2 = ScreenCaptureUtil.this;
                    boolean isLiveStreamAllowed = screenCaptureUtil2.isLiveStreamAllowed(screenCaptureUtil2.mCurrentPackageForCapture);
                    changeObserver.onCaptureAllowedChanged(isCaptureAllowed);
                    changeObserver.onLiveStreamStatusChanged(ScreenCaptureUtil.this.mLiveStreaming, isLiveStreamAllowed);
                }
            });
        }
    }

    public void setLiveStreamStatus(boolean z, boolean z2) {
        if (this.mLiveStreaming != z || this.mLiveStreamAllowed != z2) {
            this.mLiveStreaming = z;
            this.mLiveStreamAllowed = z2;
            iterateObservers(new StateChangeCallback() {
                /* class com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil.AnonymousClass3 */

                @Override // com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil.StateChangeCallback
                public void onStateChanged(ChangeObserver changeObserver) {
                    ScreenCaptureUtil screenCaptureUtil = ScreenCaptureUtil.this;
                    boolean isCaptureAllowed = screenCaptureUtil.isCaptureAllowed(screenCaptureUtil.mCurrentPackageForCapture);
                    ScreenCaptureUtil screenCaptureUtil2 = ScreenCaptureUtil.this;
                    boolean isLiveStreamAllowed = screenCaptureUtil2.isLiveStreamAllowed(screenCaptureUtil2.mCurrentPackageForCapture);
                    changeObserver.onCaptureAllowedChanged(isCaptureAllowed);
                    changeObserver.onLiveStreamStatusChanged(ScreenCaptureUtil.this.mLiveStreaming, isLiveStreamAllowed);
                }
            });
        }
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.CaptureStateUtil.CaptureStateObserver
    public void onCaptureStateChanged(boolean z, boolean z2, boolean z3, String str, Optional<Long> optional) {
        boolean z4 = z && z2;
        if (this.mCapturingVideo != z4) {
            this.mCapturingVideo = z4;
            iterateObservers(new StateChangeCallback(z4) {
                /* class com.oculus.panelapp.anytimeui.v2.util.$$Lambda$ScreenCaptureUtil$3D6OwTmWwhwOq0puqknsYLB4NrI */
                private final /* synthetic */ boolean f$0;

                {
                    this.f$0 = r1;
                }

                @Override // com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil.StateChangeCallback
                public final void onStateChanged(ScreenCaptureUtil.ChangeObserver changeObserver) {
                    changeObserver.onVideoCaptureStateChanged(this.f$0);
                }
            });
        }
        if (this.mIsCapturingAbuseReport != z3) {
            this.mIsCapturingAbuseReport = z3;
            this.mAbuseRecordingUUID = str;
            iterateObservers(new StateChangeCallback(optional) {
                /* class com.oculus.panelapp.anytimeui.v2.util.$$Lambda$ScreenCaptureUtil$3vUjS7IkL1uHd9ZUB1s3MxOhDcs */
                private final /* synthetic */ Optional f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil.StateChangeCallback
                public final void onStateChanged(ScreenCaptureUtil.ChangeObserver changeObserver) {
                    ScreenCaptureUtil.this.lambda$onCaptureStateChanged$53$ScreenCaptureUtil(this.f$1, changeObserver);
                }
            });
        }
    }

    public /* synthetic */ void lambda$onCaptureStateChanged$53$ScreenCaptureUtil(Optional optional, ChangeObserver changeObserver) {
        changeObserver.onAbuseReportCaptureStateChanged(this.mIsCapturingAbuseReport, optional);
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.CaptureStateUtil.CaptureStateObserver
    public void onLocalStreamStateUpdate(final boolean z) {
        if (this.mIsLocalStreaming != z) {
            this.mIsLocalStreaming = z;
            iterateObservers(new StateChangeCallback() {
                /* class com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil.AnonymousClass4 */

                @Override // com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil.StateChangeCallback
                public void onStateChanged(ChangeObserver changeObserver) {
                    ScreenCaptureUtil screenCaptureUtil = ScreenCaptureUtil.this;
                    boolean isCaptureAllowed = screenCaptureUtil.isCaptureAllowed(screenCaptureUtil.mCurrentPackageForCapture);
                    ScreenCaptureUtil screenCaptureUtil2 = ScreenCaptureUtil.this;
                    boolean isLiveStreamAllowed = screenCaptureUtil2.isLiveStreamAllowed(screenCaptureUtil2.mCurrentPackageForCapture);
                    changeObserver.onCaptureAllowedChanged(isCaptureAllowed);
                    changeObserver.onLiveStreamStatusChanged(ScreenCaptureUtil.this.mLiveStreaming, isLiveStreamAllowed);
                    changeObserver.onLocalStreamStateChanged(z);
                }
            });
        }
    }

    public boolean isCaptureAllowed(String str) {
        if (!this.mIsInitialized) {
            Log.e(TAG, "Not initialized.");
            return false;
        } else if (!this.mCaptureAllowed || this.mLiveStreaming || this.mIsLocalStreaming || this.mRestrictedPackagesForCapture.contains(str)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isCapturingVideo() {
        return this.mCapturingVideo;
    }

    public boolean isCapturingAbuseReportVideo() {
        return this.mIsCapturingAbuseReport;
    }

    public boolean isLiveStreamAllowed(String str) {
        return this.mLiveStreamAllowed && isCaptureAllowed(str);
    }

    public boolean isLiveStreaming() {
        return this.mLiveStreaming;
    }

    public boolean isLocalStreaming() {
        return this.mIsLocalStreaming;
    }

    public boolean shouldQuitOnScreenCapture() {
        return !this.mCurrentPackageForCapture.equals("com.oculus.vrshell") && !this.mCurrentPackageForCapture.equals("com.oculus.systemux") && !this.mCurrentPackageForCapture.equals("com.oculus.shellenv");
    }

    public void addObserver(ChangeObserver changeObserver) {
        this.mObservers.add(new WeakReference<>(changeObserver));
    }

    public void removeObserver(ChangeObserver changeObserver) {
        int i = 0;
        for (WeakReference<ChangeObserver> weakReference : this.mObservers) {
            if (weakReference.get() == changeObserver) {
                this.mObservers.remove(i);
                return;
            }
            i++;
        }
    }

    /* renamed from: refreshObserver */
    public void lambda$refreshAllObservers$54$ScreenCaptureUtil(ChangeObserver changeObserver) {
        changeObserver.onCaptureAllowedChanged(isCaptureAllowed(this.mCurrentPackageForCapture));
        changeObserver.onVideoCaptureStateChanged(this.mCapturingVideo);
        changeObserver.onLiveStreamStatusChanged(this.mLiveStreaming, isLiveStreamAllowed(this.mCurrentPackageForCapture));
        changeObserver.onLocalStreamStateChanged(this.mIsLocalStreaming);
        changeObserver.onAbuseReportCaptureStateChanged(this.mIsCapturingAbuseReport, Optional.empty());
    }

    public void refreshAllObservers() {
        iterateObservers(new StateChangeCallback() {
            /* class com.oculus.panelapp.anytimeui.v2.util.$$Lambda$ScreenCaptureUtil$MPwRkaSKcgjH5B8TG7_vlUWY478 */

            @Override // com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil.StateChangeCallback
            public final void onStateChanged(ScreenCaptureUtil.ChangeObserver changeObserver) {
                ScreenCaptureUtil.this.lambda$refreshAllObservers$54$ScreenCaptureUtil(changeObserver);
            }
        });
    }

    private void iterateObservers(StateChangeCallback stateChangeCallback) {
        for (WeakReference<ChangeObserver> weakReference : this.mObservers) {
            ChangeObserver changeObserver = weakReference.get();
            if (changeObserver != null) {
                stateChangeCallback.onStateChanged(changeObserver);
            }
        }
    }
}
