package com.oculus.panelapp.anytimeui.v2.tablet.sharing;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.text.format.DateUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import com.facebook.common.stringformat.StringFormatUtil;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.user.LinkedAccountsInfo;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.util.AbuseReportCaptureUtil;
import com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil;
import com.oculus.tablet.view.ViewModelLifecycle;
import com.oculus.vrshell.panels.DensityUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SharingViewModel extends BaseObservable implements ViewModelLifecycle {
    private static final float CENTER_BIAS = 0.5f;
    private static final String[] FILE_PROJECTION = {"_id", "media_type", "_data"};
    private static final float LEFT_BIAS = 0.0f;
    private static final int START_MARGIN_DP = 12;
    private static final String TAG = LoggingUtil.tag(SharingViewModel.class);
    private long mAbuseRecordingSecondsElapsed;
    private boolean mCaptureAllowed = true;
    private Context mContext;
    private AsyncQueryHandle mFetchCurrentPartyAsyncQueryHandle;
    private AsyncQueryHandle mFetchLinkedAccountsInfoAsyncQueryHandle;
    private boolean mInviteEnabled = false;
    private boolean mIsAbuseReportRecording = false;
    private boolean mIsCasting = false;
    private boolean mIsLiveStreaming = false;
    private boolean mIsRecording = false;
    private boolean mLiveStreamAllowed = true;
    private AnytimeUIAndroidPanelAppV2 mPanelApp;
    @Nullable
    private CountDownTimer mReportRecordingTimer;
    private Resources mResources;
    private ScreenCaptureUtil.ChangeObserver mScreenCaptureChangeObserver;

    public SharingViewModel(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        Log.d(TAG, "Constructing ViewModel");
        this.mContext = context;
        this.mResources = context.getResources();
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        initializeScreenCaptureObserver();
    }

    @Override // com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
        Log.d(TAG, "Destroying ViewModel");
        this.mPanelApp.getScreenCaptureUtil().removeObserver(this.mScreenCaptureChangeObserver);
        clearFetchLinkedAccountsInfoAsyncQueryHandle();
        clearAbuseReportRecordingTimer();
    }

    private void initializeScreenCaptureObserver() {
        this.mScreenCaptureChangeObserver = new ScreenCaptureUtil.ChangeObserver() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.sharing.SharingViewModel.AnonymousClass1 */

            @Override // com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil.ChangeObserver
            public void onCaptureAllowedChanged(boolean z) {
                SharingViewModel.this.mCaptureAllowed = z;
                SharingViewModel.this.notifyPropertyChanged(BR.captureAllowed);
                SharingViewModel.this.notifyPropertyChanged(BR.videoRecordingButtonEnabled);
            }

            @Override // com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil.ChangeObserver
            public void onLocalStreamStateChanged(boolean z) {
                SharingViewModel.this.mIsCasting = z;
                SharingViewModel.this.notifyPropertyChanged(BR.castingText);
            }

            @Override // com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil.ChangeObserver
            public void onLiveStreamStatusChanged(boolean z, boolean z2) {
                SharingViewModel.this.mIsLiveStreaming = z;
                SharingViewModel.this.notifyPropertyChanged(BR.liveStreamText);
                SharingViewModel.this.mLiveStreamAllowed = z || z2;
                SharingViewModel.this.notifyPropertyChanged(BR.liveStreamAllowed);
            }

            @Override // com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil.ChangeObserver
            public void onVideoCaptureStateChanged(boolean z) {
                SharingViewModel.this.mIsRecording = z;
                SharingViewModel.this.notifyPropertyChanged(BR.recordingText);
                SharingViewModel.this.notifyPropertyChanged(BR.videoRecordingButtonEnabled);
            }

            @Override // com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil.ChangeObserver
            public void onAbuseReportCaptureStateChanged(boolean z, Optional<Long> optional) {
                if (SharingViewModel.this.mPanelApp.isGKEnabled(Gatekeeper.SOCIAL_ABUSE_REPORT_REMOVE_OVERLAY)) {
                    SharingViewModel.this.mIsAbuseReportRecording = z;
                    if (z) {
                        SharingViewModel.this.setAbuseReportRecordingTimer(optional);
                    } else {
                        SharingViewModel.this.clearAbuseReportRecordingTimer();
                    }
                    SharingViewModel.this.notifyPropertyChanged(BR.recordingText);
                    SharingViewModel.this.notifyPropertyChanged(BR.recordingIcon);
                    SharingViewModel.this.notifyPropertyChanged(BR.videoRecordingButtonEnabled);
                }
            }
        };
        ScreenCaptureUtil screenCaptureUtil = this.mPanelApp.getScreenCaptureUtil();
        screenCaptureUtil.addObserver(this.mScreenCaptureChangeObserver);
        screenCaptureUtil.lambda$refreshAllObservers$54$ScreenCaptureUtil(this.mScreenCaptureChangeObserver);
    }

    public void setIsAbuseReportRecording(boolean z) {
        this.mIsAbuseReportRecording = z;
        notifyPropertyChanged(BR.recordingText);
        notifyPropertyChanged(BR.videoRecordingButtonEnabled);
    }

    public void fetchIsInviteEnabled() {
        if (DeviceConfigHelper.getBoolean(this.mPanelApp.getContext(), Gatekeeper.SOCIAL_DESTINATION_INVITE_ENABLED)) {
            clearFetchLinkedAccountsInfoAsyncQueryHandle();
            this.mFetchLinkedAccountsInfoAsyncQueryHandle = HorizonContentProviderHelper.fetchLinkedAccountsInfoForViewer(this.mPanelApp.getContext(), new HorizonContentProviderHelper.FetchLinkedAccountsInfoForViewerCallback() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.sharing.SharingViewModel.AnonymousClass2 */

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchLinkedAccountsInfoForViewerCallback
                public void onSuccess(LinkedAccountsInfo linkedAccountsInfo) {
                    SharingViewModel.this.mInviteEnabled = linkedAccountsInfo.isFbLinked();
                    SharingViewModel.this.clearFetchLinkedAccountsInfoAsyncQueryHandle();
                    SharingViewModel.this.notifyPropertyChanged(BR.inviteEnabled);
                }

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
                public void onError() {
                    Log.e(SharingViewModel.TAG, "Error fetching viewer's linked accounts.");
                    SharingViewModel.this.mInviteEnabled = false;
                    SharingViewModel.this.clearFetchLinkedAccountsInfoAsyncQueryHandle();
                    SharingViewModel.this.notifyPropertyChanged(BR.inviteEnabled);
                }
            });
        }
    }

    public void fetchCurrentPartyID(final HorizonContentProviderHelper.SingleIDCallback singleIDCallback) {
        clearFetchCurrentPartyAsyncQueryHandle();
        this.mFetchCurrentPartyAsyncQueryHandle = HorizonContentProviderHelper.fetchCurrentPartyDEPRECATED(this.mPanelApp.getContext(), new HorizonContentProviderHelper.FetchCurrentPartyCallBack() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.sharing.SharingViewModel.AnonymousClass3 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchCurrentPartyCallBack
            public void onSuccess(SocialParty socialParty) {
                String str;
                String str2;
                String str3 = SharingViewModel.TAG;
                Object[] objArr = new Object[1];
                if (socialParty == null) {
                    str = com.facebook.debug.log.LoggingUtil.NO_HASHCODE;
                } else {
                    str = socialParty.toString();
                }
                objArr[0] = str;
                Log.d(str3, String.format("Successfully fetched party: %s", objArr));
                SharingViewModel.this.clearFetchCurrentPartyAsyncQueryHandle();
                if (socialParty == null) {
                    str2 = null;
                } else {
                    str2 = socialParty.getID();
                }
                singleIDCallback.onSuccess(str2);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                Log.e(SharingViewModel.TAG, "Failed to fetch party");
                SharingViewModel.this.clearFetchCurrentPartyAsyncQueryHandle();
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFetchLinkedAccountsInfoAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mFetchLinkedAccountsInfoAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mFetchLinkedAccountsInfoAsyncQueryHandle = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFetchCurrentPartyAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mFetchCurrentPartyAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mFetchCurrentPartyAsyncQueryHandle = null;
        }
    }

    @Bindable
    public boolean getInviteEnabled() {
        return this.mInviteEnabled;
    }

    private Cursor getMediaCursor() {
        return this.mContext.getContentResolver().query(MediaStore.Files.getContentUri("external"), FILE_PROJECTION, "(media_type = 1 or media_type = 3) and (_data like ? or _data like ?)", new String[]{"%/Oculus/VideoShots/%", "%/Oculus/Screenshots/%"}, "date_added DESC");
    }

    public List<RecentMedia> getLastNRecents(int i) {
        Bitmap bitmap;
        ArrayList arrayList = new ArrayList();
        Cursor mediaCursor = getMediaCursor();
        if (mediaCursor == null) {
            Log.e(TAG, "Unable to get cursor to query media files thumbnails");
        } else {
            int columnIndex = mediaCursor.getColumnIndex("media_type");
            int columnIndex2 = mediaCursor.getColumnIndex("_data");
            int columnIndex3 = mediaCursor.getColumnIndex("_id");
            while (arrayList.size() < i && mediaCursor.moveToNext()) {
                int i2 = mediaCursor.getInt(columnIndex3);
                String string = mediaCursor.getString(columnIndex2);
                boolean z = mediaCursor.getInt(columnIndex) != 1;
                if (z) {
                    bitmap = MediaStore.Video.Thumbnails.getThumbnail(this.mContext.getContentResolver(), (long) i2, 1, null);
                } else {
                    bitmap = MediaStore.Images.Thumbnails.getThumbnail(this.mContext.getContentResolver(), (long) i2, 1, null);
                }
                arrayList.add(new RecentMedia(new BitmapDrawable(this.mContext.getResources(), bitmap), string, z));
            }
            mediaCursor.close();
        }
        return arrayList;
    }

    public void setAbuseReportRecordingTimer(Optional<Long> optional) {
        if (optional.isPresent()) {
            Log.d(TAG, "setAbuseReportRecordingTimer");
            CountDownTimer countDownTimer = this.mReportRecordingTimer;
            if (countDownTimer == null) {
                this.mReportRecordingTimer = new CountDownTimer(AbuseReportCaptureUtil.ABUSE_CAPTURE_AV_RECORDING_TIMELIMIT - optional.get().longValue(), 1000) {
                    /* class com.oculus.panelapp.anytimeui.v2.tablet.sharing.SharingViewModel.AnonymousClass4 */

                    public void onTick(long j) {
                        SharingViewModel.this.mAbuseRecordingSecondsElapsed = AbuseReportCaptureUtil.ABUSE_CAPTURE_AV_RECORDING_TIMELIMIT - j;
                        SharingViewModel.this.notifyPropertyChanged(BR.recordingText);
                    }

                    public void onFinish() {
                        SharingViewModel.this.mPanelApp.getScreenCaptureUtil();
                        SharingViewModel.this.clearAbuseReportRecordingTimer();
                    }
                };
            } else {
                countDownTimer.cancel();
            }
            this.mReportRecordingTimer.start();
        }
    }

    public void clearAbuseReportRecordingTimer() {
        Log.d(TAG, "clearAbuseReportRecordingTimer");
        CountDownTimer countDownTimer = this.mReportRecordingTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        this.mAbuseRecordingSecondsElapsed = 0;
    }

    @Bindable
    public String getCastingText() {
        if (this.mIsCasting) {
            return this.mResources.getString(R.string.anytime_detail_button_localstream_stop);
        }
        return this.mResources.getString(R.string.anytime_detail_button_localstream);
    }

    @Bindable
    public String getLiveStreamText() {
        if (this.mIsLiveStreaming) {
            return this.mResources.getString(R.string.anytime_detail_button_livestream_stop);
        }
        return this.mResources.getString(R.string.anytime_detail_button_livestream);
    }

    @Bindable
    public String getRecordingText() {
        if (this.mIsRecording) {
            return this.mResources.getString(R.string.anytime_detail_button_videocapture_stop);
        }
        if (!this.mIsAbuseReportRecording) {
            return this.mResources.getString(R.string.anytime_detail_button_videocapture);
        }
        return StringFormatUtil.formatStrLocaleSafe("%s/%s", DateUtils.formatElapsedTime(this.mAbuseRecordingSecondsElapsed / 1000), AbuseReportCaptureUtil.getFormattedTimelimit());
    }

    @Bindable
    public Drawable getRecordingIcon() {
        if (this.mIsAbuseReportRecording) {
            return this.mResources.getDrawable(R.drawable.oc_icon_stop_circle_filled_24_ffffff, null);
        }
        return this.mResources.getDrawable(R.drawable.ic_tablet_sharing_videocapture_item, null);
    }

    @Bindable
    public boolean getCaptureAllowed() {
        return this.mCaptureAllowed;
    }

    @Bindable
    public boolean getVideoRecordingButtonEnabled() {
        return this.mCaptureAllowed || this.mIsRecording || this.mIsAbuseReportRecording;
    }

    @Bindable
    public boolean getLiveStreamAllowed() {
        return this.mLiveStreamAllowed;
    }

    @Bindable
    public boolean getIsAbuseReportRecording() {
        return this.mIsAbuseReportRecording;
    }

    public boolean isLivestreamButtonVisible() {
        return !this.mPanelApp.getSystemUXConfig().isEnterpriseMode;
    }

    public boolean isCastingButtonVisible() {
        return !this.mPanelApp.getSystemUXConfig().isEnterpriseMode || this.mPanelApp.getSystemUXConfig().isEnterpriseAdminModeEnabled;
    }

    public int getButtonAlignment() {
        return this.mPanelApp.getSystemUXConfig().isEnterpriseMode ? 2 : 1;
    }

    public float getButtonHorizontalBias() {
        return this.mPanelApp.getSystemUXConfig().isEnterpriseMode ? 0.0f : 0.5f;
    }

    public int getButtonStartMargin() {
        return DensityUtils.dipToPixelsInt((float) (this.mPanelApp.getSystemUXConfig().isEnterpriseMode ? 12 : 0), this.mContext.getResources().getDisplayMetrics());
    }

    public static class RecentMedia {
        public final String filePath;
        public final boolean isVideo;
        public final Drawable thumbnail;

        public RecentMedia(Drawable drawable, String str, boolean z) {
            this.thumbnail = drawable;
            this.filePath = str;
            this.isVideo = z;
        }
    }
}
