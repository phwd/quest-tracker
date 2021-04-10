package com.oculus.panelapp.anytimeui.v2.tablet.destinationui;

import android.content.Context;
import android.graphics.Outline;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.databinding.ViewDataBinding;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.LoggingConstants;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletDestinationUiViewV2Binding;
import com.oculus.panelapp.anytimeui.tooltip.TooltipController;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.sharing.SharingViewModel;
import com.oculus.panelapp.anytimeui.v2.util.PauseStateResult;
import com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureButtonUtil;
import com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil;
import com.oculus.tablet.databinding.OsigButtonBorderlessBinding;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.tablet.logging.SectionTrackerEvent;
import com.oculus.tablet.view.BaseView;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.DensityUtils;
import com.oculus.vrshell.panels.telemetry.SourceConstants;
import java.util.Optional;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class DestinationUIView extends BaseView {
    private static final String TAG = LoggingUtil.tag(DestinationUIView.class);
    private static final String TOOLTIP_ID_LIVE_STREAM_BUTTON = "anytime_tablet_destination_ui_tooltip_live_stream_button_v2";
    private static final String TOOLTIP_ID_RECORD_VIDEO_BUTTON = "anytime_tablet_destination_ui_tooltip_record_video_button_v2";
    private static final String TOOLTIP_ID_SCREENSHOT_BUTTON = "anytime_tablet_destination_ui_tooltip_screenshot_button_v2";
    private AnytimeTabletDestinationUiViewV2Binding mBinding;
    private DestinationUIViewModel mDestinationUIViewModel;
    private boolean mIsCapturing;
    private boolean mIsCapturingAbuseReport;
    private AnytimeUIAndroidPanelAppV2 mPanelApp;
    private ScreenCaptureButtonUtil mScreenCaptureButtonUtil;
    private ScreenCaptureUtil.ChangeObserver mScreenCaptureChangeObserver;
    private ScreenCaptureUtil mScreenCaptureUtil;
    private SharingViewModel mSharingViewModel;
    private TooltipController mTooltipController;

    public DestinationUIView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.d(TAG, "Constructing DestinationUIView");
    }

    public void initialize(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, ViewDataBinding viewDataBinding) {
        super.initialize((AndroidPanelApp) anytimeUIAndroidPanelAppV2, viewDataBinding);
        Log.d(TAG, "Initializing DestinationUIView");
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mBinding = (AnytimeTabletDestinationUiViewV2Binding) viewDataBinding;
        this.mDestinationUIViewModel = anytimeUIAndroidPanelAppV2.acquireDestinationUIViewModel();
        this.mSharingViewModel = anytimeUIAndroidPanelAppV2.acquireSharingViewModel();
        this.mBinding.setDestinationUIViewModel(this.mDestinationUIViewModel);
        this.mBinding.setSharingViewModel(this.mSharingViewModel);
        this.mTooltipController = this.mPanelApp.getSystemTooltipController();
        this.mScreenCaptureUtil = anytimeUIAndroidPanelAppV2.getScreenCaptureUtil();
        this.mScreenCaptureButtonUtil = anytimeUIAndroidPanelAppV2.getScreenCaptureButtonUtil();
        this.mScreenCaptureChangeObserver = new ScreenCaptureUtil.ChangeObserver() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.destinationui.DestinationUIView.AnonymousClass1 */

            @Override // com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil.ChangeObserver
            public void onLocalStreamStateChanged(boolean z) {
                DestinationUIView.this.mBinding.castingButton.container.setSelected(z);
            }

            @Override // com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil.ChangeObserver
            public void onLiveStreamStatusChanged(boolean z, boolean z2) {
                DestinationUIView.this.mBinding.livestreamButton.container.setSelected(z);
                DestinationUIView.this.mTooltipController.setTooltipEnabled(DestinationUIView.TOOLTIP_ID_LIVE_STREAM_BUTTON, !z2);
            }

            @Override // com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil.ChangeObserver
            public void onVideoCaptureStateChanged(boolean z) {
                DestinationUIView.this.mIsCapturing = z;
                DestinationUIView.this.setIsRecordingButtonSelected();
            }

            @Override // com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil.ChangeObserver
            public void onCaptureAllowedChanged(boolean z) {
                DestinationUIView.this.mTooltipController.setTooltipEnabled(DestinationUIView.TOOLTIP_ID_RECORD_VIDEO_BUTTON, !z);
                DestinationUIView.this.mTooltipController.setTooltipEnabled(DestinationUIView.TOOLTIP_ID_SCREENSHOT_BUTTON, !z);
            }

            @Override // com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil.ChangeObserver
            public void onAbuseReportCaptureStateChanged(boolean z, Optional<Long> optional) {
                if (DestinationUIView.this.mPanelApp.isGKEnabled(Gatekeeper.SOCIAL_ABUSE_REPORT_REMOVE_OVERLAY)) {
                    DestinationUIView.this.mIsCapturingAbuseReport = z;
                    if (z) {
                        DestinationUIView.this.mSharingViewModel.setAbuseReportRecordingTimer(optional);
                    } else {
                        DestinationUIView.this.mSharingViewModel.clearAbuseReportRecordingTimer();
                    }
                    DestinationUIView.this.setIsRecordingButtonSelected();
                }
            }
        };
        this.mScreenCaptureButtonUtil.initializeCastingButton(this.mBinding.castingButton.button, this.mPanelApp, ClickEventButtonId.AUIV2_DESTINATION_UI_CASTING, ClickEventButtonId.AUIV2_DESTINATION_UI_CASTING_STOP);
        this.mScreenCaptureButtonUtil.initializeLiveStreamButton(this.mBinding.livestreamButton.button, this.mPanelApp, ClickEventButtonId.AUIV2_DESTINATION_UI_LIVESTREAM, ClickEventButtonId.AUIV2_DESTINATION_UI_LIVESTREAM_STOP);
        this.mScreenCaptureButtonUtil.initializeScreenRecordingButton(this.mBinding.screenrecordingButton.button, this.mPanelApp, ClickEventButtonId.AUIV2_DESTINATION_UI_RECORD_VIDEO, ClickEventButtonId.AUIV2_DESTINATION_UI_RECORD_VIDEO_STOP, ClickEventButtonId.AUIV2_DESTINATION_UI_RECORD_ABUSE_VIDEO_STOP);
        this.mScreenCaptureButtonUtil.initializeScreenshotButton(this.mBinding.screenshotButton.button, this.mPanelApp, ClickEventButtonId.AUIV2_DESTINATION_UI_TAKE_SCREENSHOT);
        initializeButtonHaptics();
        initializeInviteButton();
        initializeReportButton();
        initializePauseActions();
        this.mScreenCaptureUtil.addObserver(this.mScreenCaptureChangeObserver);
        this.mScreenCaptureUtil.lambda$refreshAllObservers$54$ScreenCaptureUtil(this.mScreenCaptureChangeObserver);
        this.mPanelApp.logSectionEntry(SectionTrackerEvent.PAUSE_TABLET);
    }

    private void initializeButtonHaptics() {
        for (OsigButtonBorderlessBinding osigButtonBorderlessBinding : new OsigButtonBorderlessBinding[]{this.mBinding.castingButton, this.mBinding.livestreamButton, this.mBinding.screenshotButton, this.mBinding.screenrecordingButton, this.mBinding.inviteButton, this.mBinding.reportButton}) {
            osigButtonBorderlessBinding.button.setEventHandler(this.mPanelApp);
        }
    }

    @Override // com.oculus.tablet.view.BaseView
    public void destroy() {
        Log.d(TAG, "Destroying DestinationUIView");
        this.mPanelApp.releaseDestinationUIViewModel();
        this.mPanelApp.releaseSharingViewModel();
        this.mScreenCaptureUtil.removeObserver(this.mScreenCaptureChangeObserver);
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onShow(String str) {
        Log.d(TAG, "Showing DestinationUIView");
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
        Log.d(TAG, "Hiding DestinationUIView");
    }

    private void initializeInviteButton() {
        Log.d(TAG, "Initializing invite button");
        this.mBinding.inviteButton.button.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.destinationui.$$Lambda$DestinationUIView$vqNvPUy1f9qLYZNqMzqSdNPsEH4 */

            public final void onClick(View view) {
                DestinationUIView.this.lambda$initializeInviteButton$172$DestinationUIView(view);
            }
        });
        this.mSharingViewModel.fetchIsInviteEnabled();
        Log.d(TAG, "Initialized invite button");
    }

    public /* synthetic */ void lambda$initializeInviteButton$172$DestinationUIView(View view) {
        if (DeviceConfigHelper.getBoolean(getContext(), Gatekeeper.SOCIAL_PARTY_INVITE_FROM_DESTINATION_UI)) {
            this.mSharingViewModel.fetchCurrentPartyID(new HorizonContentProviderHelper.SingleIDCallback() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.destinationui.DestinationUIView.AnonymousClass2 */

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
                public void onSuccess(String str) {
                    if (str == null) {
                        DestinationUIView.this.mPanelApp.actionNavigate(SystemUXRoute.SOCIAL_CREATE_VR_INVITE_DIALOG, new Uri.Builder().appendQueryParameter(LoggingConstants.CORRELATION_ID, UUID.randomUUID().toString()).appendQueryParameter("source", SourceConstants.DESTINATION_UI_INVITE_BUTTON).build().toString());
                        return;
                    }
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("deeplinkTargetId", str);
                        DestinationUIView.this.mPanelApp.actionNavigate(SystemUXRoute.SHARE_SHEET_V2, new Uri.Builder().encodedPath("").appendQueryParameter("type", "deeplink_target").appendQueryParameter("source", SourceConstants.DESTINATION_UI_INVITE_BUTTON).appendQueryParameter("payload", jSONObject.toString()).build().toString());
                    } catch (JSONException e) {
                        String str2 = DestinationUIView.TAG;
                        Log.e(str2, "Error building payload for share sheet: " + e.toString());
                    }
                }

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
                public void onError() {
                    Log.e(DestinationUIView.TAG, "Error fetching party ID for invite");
                }
            });
        } else {
            this.mPanelApp.actionNavigate(SystemUXRoute.INVITE_TO_APP, "");
        }
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_DESTINATION_UI_INVITE);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsRecordingButtonSelected() {
        this.mBinding.screenrecordingButton.container.setSelected(this.mIsCapturing || this.mIsCapturingAbuseReport);
    }

    private void initializeReportButton() {
        Log.d(TAG, "Initializing report button");
        this.mBinding.reportButton.button.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.destinationui.$$Lambda$DestinationUIView$VJJxLg7U0aEqLh1wy6AyvKA0Ymg */

            public final void onClick(View view) {
                DestinationUIView.this.lambda$initializeReportButton$173$DestinationUIView(view);
            }
        });
        Log.d(TAG, "Initialized report button");
    }

    public /* synthetic */ void lambda$initializeReportButton$173$DestinationUIView(View view) {
        this.mPanelApp.actionNavigate(SystemUXRoute.BLOCKANDREPORT, this.mPanelApp.getReturnComponent());
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_DESTINATION_UI_REPORT);
    }

    private void initializePauseActions() {
        Log.d(TAG, "Initializing pause actions");
        this.mBinding.quitButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.destinationui.$$Lambda$DestinationUIView$FwAHceNZYkMkpGmsAq0mAe5Ea40 */

            public final void onClick(View view) {
                DestinationUIView.this.lambda$initializePauseActions$174$DestinationUIView(view);
            }
        });
        this.mBinding.restartButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.destinationui.$$Lambda$DestinationUIView$xuVAtDdVRIvme1upBvzXhSWIp9s */

            public final void onClick(View view) {
                DestinationUIView.this.lambda$initializePauseActions$175$DestinationUIView(view);
            }
        });
        this.mBinding.resumeButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.destinationui.$$Lambda$DestinationUIView$ObYwOK2sv_tuGycESD2uimbqkes */

            public final void onClick(View view) {
                DestinationUIView.this.lambda$initializePauseActions$176$DestinationUIView(view);
            }
        });
        this.mBinding.appImage.setOutlineProvider(new ViewOutlineProvider() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.destinationui.DestinationUIView.AnonymousClass3 */

            public void getOutline(View view, Outline outline) {
                int dipToPixelsInt = DensityUtils.dipToPixelsInt(DestinationUIView.this.getResources().getDimension(R.dimen.octablet_border_radius), DestinationUIView.this.getContext().getResources().getDisplayMetrics());
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight() + dipToPixelsInt, (float) dipToPixelsInt);
            }
        });
        this.mBinding.appImage.setClipToOutline(true);
        Log.d(TAG, "Initialized paused actions");
    }

    public /* synthetic */ void lambda$initializePauseActions$174$DestinationUIView(View view) {
        if (this.mPanelApp.isGKEnabled(Gatekeeper.LIBRARY_STATE_AUTO_RESET)) {
            this.mPanelApp.maybeResetLibraryStateOnAppLaunchOrQuit(false);
        }
        this.mPanelApp.actionPauseStateResult(PauseStateResult.QUIT);
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_DESTINATION_UI_QUIT);
    }

    public /* synthetic */ void lambda$initializePauseActions$175$DestinationUIView(View view) {
        if (this.mPanelApp.isGKEnabled(Gatekeeper.LIBRARY_STATE_AUTO_RESET)) {
            this.mPanelApp.maybeResetLibraryStateOnAppLaunchOrQuit(false);
        }
        this.mPanelApp.actionPauseStateResult(PauseStateResult.QUIT);
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_DESTINATION_UI_RESTART);
    }

    public /* synthetic */ void lambda$initializePauseActions$176$DestinationUIView(View view) {
        this.mPanelApp.actionPauseStateResult(PauseStateResult.RESUME);
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_DESTINATION_UI_RESUME);
    }
}
