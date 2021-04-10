package com.oculus.panelapp.anytimeui.v2.tablet.sharing;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.databinding.ViewDataBinding;
import com.facebook.common.stringformat.StringFormatUtil;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletSharingViewV2Binding;
import com.oculus.panelapp.anytimeui.tooltip.TooltipController;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.sharing.SharingViewModel;
import com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureButtonUtil;
import com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.tablet.logging.SectionTrackerEvent;
import com.oculus.tablet.view.BaseView;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.views.ShellButton;
import com.oculus.vrshell.util.ThreadExecutor;
import com.oculus.vrshell.util.UiThreadExecutor;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

public class SharingView extends BaseView {
    private static final String MEDIA_PREVIEW_RECENT_CAPTURE_SOURCE = "aui_recent_captures_max_view";
    private static final String TAG = LoggingUtil.tag(SharingView.class);
    private static final String TOOLTIP_ID_LIVE_STREAM_BUTTON = "anytime_tablet_sharing_tooltip_live_stream_button_v2";
    private static final String TOOLTIP_ID_RECORD_VIDEO_BUTTON = "anytime_tablet_sharing_tooltip_record_video_button_v2";
    private static final String TOOLTIP_ID_SCREENSHOT_BUTTON = "anytime_tablet_sharing_tooltip_screenshot_button_v2";
    private AnytimeTabletSharingViewV2Binding mBinding;
    private boolean mIsCapturing;
    private boolean mIsCapturingAbuseReport;
    private ContentObserver mMediaContentObserver;
    private AnytimeUIAndroidPanelAppV2 mPanelApp;
    private ScreenCaptureButtonUtil mScreenCaptureButtonUtil;
    private ScreenCaptureUtil.ChangeObserver mScreenCaptureChangeObserver;
    private ScreenCaptureUtil mScreenCaptureUtil;
    private SharingViewModel mSharingViewModel;
    private StartBrowserCastingDialogHandler mStartBrowserCastingDialogHandler;
    private TooltipController mTooltipController;

    public SharingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.d(TAG, "Constructing SharingView");
    }

    public void initialize(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, ViewDataBinding viewDataBinding) {
        super.initialize((AndroidPanelApp) anytimeUIAndroidPanelAppV2, viewDataBinding);
        Log.d(TAG, "Initializing SharingView");
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mBinding = (AnytimeTabletSharingViewV2Binding) viewDataBinding;
        this.mSharingViewModel = anytimeUIAndroidPanelAppV2.acquireSharingViewModel();
        this.mScreenCaptureUtil = anytimeUIAndroidPanelAppV2.getScreenCaptureUtil();
        this.mScreenCaptureButtonUtil = anytimeUIAndroidPanelAppV2.getScreenCaptureButtonUtil();
        this.mTooltipController = this.mPanelApp.getSystemTooltipController();
        this.mStartBrowserCastingDialogHandler = new StartBrowserCastingDialogHandler(getContext(), anytimeUIAndroidPanelAppV2);
        this.mBinding.setViewModel(this.mSharingViewModel);
        this.mBinding.loadingDots.container.initialize();
        this.mBinding.loadingDots.container.start();
        refreshRecentsRow();
        this.mScreenCaptureChangeObserver = new ScreenCaptureUtil.ChangeObserver() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.sharing.SharingView.AnonymousClass1 */

            @Override // com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil.ChangeObserver
            public void onLocalStreamStateChanged(boolean z) {
                SharingView.this.mStartBrowserCastingDialogHandler.onCastingStateChange(z);
                SharingView.this.mBinding.castingButton.setSelected(z);
            }

            @Override // com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil.ChangeObserver
            public void onLiveStreamStatusChanged(boolean z, boolean z2) {
                SharingView.this.mBinding.livestreamButton.setSelected(z);
                SharingView.this.mTooltipController.setTooltipEnabled(SharingView.TOOLTIP_ID_LIVE_STREAM_BUTTON, !z2);
            }

            @Override // com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil.ChangeObserver
            public void onVideoCaptureStateChanged(boolean z) {
                SharingView.this.mIsCapturing = z;
                SharingView.this.setIsRecordingButtonSelected();
            }

            @Override // com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil.ChangeObserver
            public void onCaptureAllowedChanged(boolean z) {
                SharingView.this.mTooltipController.setTooltipEnabled(SharingView.TOOLTIP_ID_RECORD_VIDEO_BUTTON, !z);
                SharingView.this.mTooltipController.setTooltipEnabled(SharingView.TOOLTIP_ID_SCREENSHOT_BUTTON, !z);
            }

            @Override // com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil.ChangeObserver
            public void onAbuseReportCaptureStateChanged(boolean z, Optional<Long> optional) {
                if (SharingView.this.mPanelApp.isGKEnabled(Gatekeeper.SOCIAL_ABUSE_REPORT_REMOVE_OVERLAY)) {
                    SharingView.this.mIsCapturingAbuseReport = z;
                    SharingView.this.setIsRecordingButtonSelected();
                }
            }
        };
        this.mScreenCaptureButtonUtil.initializeCastingButton(this.mBinding.castingButton, this.mPanelApp, ClickEventButtonId.AUIV2_SHARING_CASTING, ClickEventButtonId.AUIV2_SHARING_CASTING_STOP);
        this.mScreenCaptureButtonUtil.initializeLiveStreamButton(this.mBinding.livestreamButton, this.mPanelApp, ClickEventButtonId.AUIV2_SHARING_LIVESTREAM, ClickEventButtonId.AUIV2_SHARING_LIVESTREAM_STOP);
        this.mScreenCaptureButtonUtil.initializeScreenRecordingButton(this.mBinding.screenrecordingButton, this.mPanelApp, ClickEventButtonId.AUIV2_SHARING_RECORD_VIDEO, ClickEventButtonId.AUIV2_SHARING_RECORD_VIDEO_STOP, ClickEventButtonId.AUIV2_SHARING_RECORD_ABUSE_VIDEO_STOP);
        this.mScreenCaptureButtonUtil.initializeScreenshotButton(this.mBinding.screenshotButton, this.mPanelApp, ClickEventButtonId.AUIV2_SHARING_TAKE_SCREENSHOT);
        initializeCameraRollButton();
        this.mBinding.screenrecordingButton.setEventHandler(this.mPanelApp);
        this.mBinding.livestreamButton.setEventHandler(this.mPanelApp);
        this.mBinding.screenshotButton.setEventHandler(this.mPanelApp);
        this.mBinding.castingButton.setEventHandler(this.mPanelApp);
        this.mScreenCaptureUtil.addObserver(this.mScreenCaptureChangeObserver);
        this.mScreenCaptureUtil.lambda$refreshAllObservers$54$ScreenCaptureUtil(this.mScreenCaptureChangeObserver);
        this.mMediaContentObserver = new ContentObserver(new Handler()) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.sharing.SharingView.AnonymousClass2 */

            public boolean deliverSelfNotifications() {
                return true;
            }

            public void onChange(boolean z) {
                onChange(z, null);
            }

            public void onChange(boolean z, Uri uri) {
                SharingView.this.refreshRecentsRow();
            }
        };
        getContext().getContentResolver().registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true, this.mMediaContentObserver);
        getContext().getContentResolver().registerContentObserver(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, true, this.mMediaContentObserver);
        this.mPanelApp.logSectionEntry(SectionTrackerEvent.SHARING_TABLET);
    }

    @Override // com.oculus.tablet.view.BaseView
    public void destroy() {
        Log.d(TAG, "Destroying SharingView");
        this.mStartBrowserCastingDialogHandler.destroy();
        this.mPanelApp.releaseSharingViewModel();
        this.mScreenCaptureUtil.removeObserver(this.mScreenCaptureChangeObserver);
        getContext().getContentResolver().unregisterContentObserver(this.mMediaContentObserver);
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onShow(String str) {
        Log.d(TAG, "Showing SharingView");
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
        Log.d(TAG, "Hiding SharingView");
    }

    private void initializeCameraRollButton() {
        this.mBinding.camerarollButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.sharing.$$Lambda$SharingView$kBuD5X7zN07joXOVAjqIy0rO0c */

            public final void onClick(View view) {
                SharingView.this.lambda$initializeCameraRollButton$489$SharingView(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeCameraRollButton$489$SharingView(View view) {
        this.mPanelApp.actionNavigate(SystemUXRoute.CAMERA_ROLL, this.mPanelApp.getReturnComponent());
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SHARING_CAMERA_ROLL);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsRecordingButtonSelected() {
        this.mBinding.screenrecordingButton.setSelected(this.mIsCapturing || this.mIsCapturingAbuseReport);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void refreshRecentsRow() {
        ThreadExecutor.getInstance().execute(new Callable() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.sharing.$$Lambda$SharingView$If9UysEGkNNoPqliTvdqjDgfOk */

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SharingView.this.lambda$refreshRecentsRow$491$SharingView();
            }
        });
    }

    public /* synthetic */ Object lambda$refreshRecentsRow$491$SharingView() throws Exception {
        List<SharingViewModel.RecentMedia> lastNRecents = this.mSharingViewModel.getLastNRecents(6);
        UiThreadExecutor.getInstance().execute(new Runnable(lastNRecents, lastNRecents.size()) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.sharing.$$Lambda$SharingView$QZVov7dcWWYQ4F7G2a6UlyBaaZo */
            private final /* synthetic */ List f$1;
            private final /* synthetic */ int f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                SharingView.this.lambda$null$490$SharingView(this.f$1, this.f$2);
            }
        });
        return null;
    }

    public /* synthetic */ void lambda$null$490$SharingView(List list, int i) {
        this.mBinding.recentCaptureTilesRow.removeAllViews();
        int i2 = 0;
        int i3 = 0;
        for (SharingViewModel.RecentMedia recentMedia : list.subList(0, Math.min(i, this.mPanelApp.getSystemUXConfig().isEnterpriseMode ? 6 : 5))) {
            addRecentToRecentsRow(recentMedia, i3);
            i3++;
        }
        this.mBinding.recentsNullState.setVisibility(i > 0 ? 8 : 0);
        this.mBinding.recentCaptureTilesRow.setVisibility(i > 0 ? 0 : 8);
        ShellButton shellButton = this.mBinding.camerarollButton;
        if (i <= 0 || this.mPanelApp.getSystemUXConfig().isEnterpriseMode) {
            i2 = 8;
        }
        shellButton.setVisibility(i2);
        this.mBinding.loadingDots.container.setVisibility(8);
        this.mBinding.loadingDots.container.end();
    }

    private void addRecentToRecentsRow(SharingViewModel.RecentMedia recentMedia, int i) {
        SharingThumbnailButton sharingThumbnailButton = new SharingThumbnailButton(getContext(), null);
        sharingThumbnailButton.setIsVideo(recentMedia.isVideo);
        sharingThumbnailButton.setThumbnail(recentMedia.thumbnail);
        if (this.mPanelApp.getSystemUXConfig().isEnterpriseMode) {
            sharingThumbnailButton.setEnabled(false);
        } else {
            sharingThumbnailButton.setOnClickListener(new View.OnClickListener(recentMedia, i) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.sharing.$$Lambda$SharingView$YNi2_BVwjsUPaEIyJ49aW54VI */
                private final /* synthetic */ SharingViewModel.RecentMedia f$1;
                private final /* synthetic */ int f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onClick(View view) {
                    SharingView.this.lambda$addRecentToRecentsRow$492$SharingView(this.f$1, this.f$2, view);
                }
            });
        }
        this.mBinding.recentCaptureTilesRow.addView(sharingThumbnailButton);
    }

    public /* synthetic */ void lambda$addRecentToRecentsRow$492$SharingView(SharingViewModel.RecentMedia recentMedia, int i, View view) {
        this.mPanelApp.actionNavigate(SystemUXRoute.MEDIA_PREVIEW, StringFormatUtil.formatStrLocaleSafe("?filepath=%s&source=%s", recentMedia.filePath, MEDIA_PREVIEW_RECENT_CAPTURE_SOURCE));
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SHARING_RECENT_TILE.getSubButtonId(Integer.toString(i + 1)));
    }
}
