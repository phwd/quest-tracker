package com.oculus.panelapp.anytimeui.v2.util;

import android.util.Log;
import android.view.View;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.vrshell.SystemUXRoute;

public class ScreenCaptureButtonUtil {
    private static final String TAG = LoggingUtil.tag(ScreenCaptureButtonUtil.class);
    private ScreenCaptureUtil mScreenCaptureUtil;

    public ScreenCaptureButtonUtil(ScreenCaptureUtil screenCaptureUtil) {
        this.mScreenCaptureUtil = screenCaptureUtil;
    }

    public void initializeCastingButton(View view, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, ClickEventButtonId clickEventButtonId, ClickEventButtonId clickEventButtonId2) {
        Log.d(TAG, "Initializing casting button");
        view.setOnClickListener(new View.OnClickListener(anytimeUIAndroidPanelAppV2, clickEventButtonId2, clickEventButtonId) {
            /* class com.oculus.panelapp.anytimeui.v2.util.$$Lambda$ScreenCaptureButtonUtil$ztNCQ9RURYPNfNncdvSZIWXwNE */
            private final /* synthetic */ AnytimeUIAndroidPanelAppV2 f$1;
            private final /* synthetic */ ClickEventButtonId f$2;
            private final /* synthetic */ ClickEventButtonId f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void onClick(View view) {
                ScreenCaptureButtonUtil.this.lambda$initializeCastingButton$65$ScreenCaptureButtonUtil(this.f$1, this.f$2, this.f$3, view);
            }
        });
        Log.d(TAG, "Initialized casting button");
    }

    public /* synthetic */ void lambda$initializeCastingButton$65$ScreenCaptureButtonUtil(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, ClickEventButtonId clickEventButtonId, ClickEventButtonId clickEventButtonId2, View view) {
        if (this.mScreenCaptureUtil.isLocalStreaming()) {
            anytimeUIAndroidPanelAppV2.actionNavigate(SystemUXRoute.LOCAL_STREAM_STOP_FROM_DEVICE_DIALOG, anytimeUIAndroidPanelAppV2.getReturnComponent());
            anytimeUIAndroidPanelAppV2.logButtonClick(clickEventButtonId);
            return;
        }
        anytimeUIAndroidPanelAppV2.actionNavigate(SystemUXRoute.LOCAL_STREAM_START_FROM_DEVICE_DIALOG, anytimeUIAndroidPanelAppV2.getReturnComponent());
        anytimeUIAndroidPanelAppV2.logButtonClick(clickEventButtonId2);
    }

    public void initializeLiveStreamButton(View view, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, ClickEventButtonId clickEventButtonId, ClickEventButtonId clickEventButtonId2) {
        Log.d(TAG, "Initializing livestream button");
        view.setOnClickListener(new View.OnClickListener(anytimeUIAndroidPanelAppV2, clickEventButtonId2, clickEventButtonId) {
            /* class com.oculus.panelapp.anytimeui.v2.util.$$Lambda$ScreenCaptureButtonUtil$M8Hequ4gvJpMgXfD_Qd3_rsOvw4 */
            private final /* synthetic */ AnytimeUIAndroidPanelAppV2 f$1;
            private final /* synthetic */ ClickEventButtonId f$2;
            private final /* synthetic */ ClickEventButtonId f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void onClick(View view) {
                ScreenCaptureButtonUtil.this.lambda$initializeLiveStreamButton$66$ScreenCaptureButtonUtil(this.f$1, this.f$2, this.f$3, view);
            }
        });
        Log.d(TAG, "Initialized livestream button");
    }

    public /* synthetic */ void lambda$initializeLiveStreamButton$66$ScreenCaptureButtonUtil(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, ClickEventButtonId clickEventButtonId, ClickEventButtonId clickEventButtonId2, View view) {
        anytimeUIAndroidPanelAppV2.actionNavigate(SystemUXRoute.LIVESTREAMING, anytimeUIAndroidPanelAppV2.getReturnComponent());
        if (this.mScreenCaptureUtil.isLiveStreaming()) {
            anytimeUIAndroidPanelAppV2.logButtonClick(clickEventButtonId);
        } else {
            anytimeUIAndroidPanelAppV2.logButtonClick(clickEventButtonId2);
        }
    }

    public void initializeScreenRecordingButton(View view, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, ClickEventButtonId clickEventButtonId, ClickEventButtonId clickEventButtonId2, ClickEventButtonId clickEventButtonId3) {
        Log.d(TAG, "Initializing screen recording button");
        view.setOnClickListener(new View.OnClickListener(anytimeUIAndroidPanelAppV2, clickEventButtonId2, clickEventButtonId3, clickEventButtonId) {
            /* class com.oculus.panelapp.anytimeui.v2.util.$$Lambda$ScreenCaptureButtonUtil$0ziNJpwNVlhdJYeNgEHKwOD45j8 */
            private final /* synthetic */ AnytimeUIAndroidPanelAppV2 f$1;
            private final /* synthetic */ ClickEventButtonId f$2;
            private final /* synthetic */ ClickEventButtonId f$3;
            private final /* synthetic */ ClickEventButtonId f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            public final void onClick(View view) {
                ScreenCaptureButtonUtil.this.lambda$initializeScreenRecordingButton$67$ScreenCaptureButtonUtil(this.f$1, this.f$2, this.f$3, this.f$4, view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeScreenRecordingButton$67$ScreenCaptureButtonUtil(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, ClickEventButtonId clickEventButtonId, ClickEventButtonId clickEventButtonId2, ClickEventButtonId clickEventButtonId3, View view) {
        if (this.mScreenCaptureUtil.isCapturingVideo()) {
            Log.d(TAG, "sendStopVideoCaptureBroadcast");
            this.mScreenCaptureUtil.sendStopVideoCaptureBroadcast();
            anytimeUIAndroidPanelAppV2.logButtonClick(clickEventButtonId);
        } else if (!this.mScreenCaptureUtil.isCapturingAbuseReportVideo() || !anytimeUIAndroidPanelAppV2.isGKEnabled(Gatekeeper.SOCIAL_ABUSE_REPORT_REMOVE_OVERLAY)) {
            Log.d(TAG, "VideoCapture V2 Path");
            anytimeUIAndroidPanelAppV2.actionNavigate(SystemUXRoute.AUI_TABLET_NONE, "");
            this.mScreenCaptureUtil.sendStartVideoCaptureBroadcast();
            maybeQuitOnScreenCapture(anytimeUIAndroidPanelAppV2);
            anytimeUIAndroidPanelAppV2.logButtonClick(clickEventButtonId3);
        } else {
            Log.d(TAG, "sendStopAbuseReportCaptureBroadcast");
            this.mScreenCaptureUtil.sendStopAbuseReportCaptureBroadcast(anytimeUIAndroidPanelAppV2);
            anytimeUIAndroidPanelAppV2.logButtonClick(clickEventButtonId2);
        }
    }

    public void initializeScreenshotButton(View view, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, ClickEventButtonId clickEventButtonId) {
        Log.d(TAG, "Initializing screenshot button");
        view.setOnClickListener(new View.OnClickListener(anytimeUIAndroidPanelAppV2, clickEventButtonId) {
            /* class com.oculus.panelapp.anytimeui.v2.util.$$Lambda$ScreenCaptureButtonUtil$AH2crNZ8ZhC8AlJ3bgNv4oaFh50 */
            private final /* synthetic */ AnytimeUIAndroidPanelAppV2 f$1;
            private final /* synthetic */ ClickEventButtonId f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onClick(View view) {
                ScreenCaptureButtonUtil.this.lambda$initializeScreenshotButton$68$ScreenCaptureButtonUtil(this.f$1, this.f$2, view);
            }
        });
        Log.d(TAG, "Initialized screenshot button");
    }

    public /* synthetic */ void lambda$initializeScreenshotButton$68$ScreenCaptureButtonUtil(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, ClickEventButtonId clickEventButtonId, View view) {
        Log.d(TAG, "Screenshot V2 Path");
        anytimeUIAndroidPanelAppV2.actionNavigate(SystemUXRoute.AUI_TABLET_NONE, "");
        this.mScreenCaptureUtil.sendTakeScreenshotBroadcast();
        maybeQuitOnScreenCapture(anytimeUIAndroidPanelAppV2);
        anytimeUIAndroidPanelAppV2.logButtonClick(clickEventButtonId);
    }

    private void maybeQuitOnScreenCapture(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        if (this.mScreenCaptureUtil.shouldQuitOnScreenCapture()) {
            anytimeUIAndroidPanelAppV2.actionQuitAndHide();
        }
    }
}
