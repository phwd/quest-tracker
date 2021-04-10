package com.oculus.panelapp.messenger.views;

import X.AnonymousClass1uc;
import android.content.res.Resources;
import androidx.databinding.Bindable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.messengervr.interfaces.BlockedByViewerStatus;
import com.oculus.messengervr.interfaces.MessengerParticipant;
import com.oculus.socialplatform.R;
import com.oculus.tablet.view.ViewModelLifecycle;

public class OneOnOneThreadBlockedViewModel extends AnonymousClass1uc implements ViewModelLifecycle {
    public static final String TAG = LoggingUtil.tag(OneOnOneThreadBlockedViewModel.class);
    public BlockedByViewerStatus mBlockedByViewerStatus;
    public String mBlockedParticipantName;
    public boolean mIsReportButtonVisible;
    public final Resources mResources;

    @Override // com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
    }

    @Bindable
    public int getReportButtonVisibility() {
        if (this.mIsReportButtonVisible) {
            return 0;
        }
        return 8;
    }

    @Bindable
    public String getTitleText() {
        Resources resources;
        int i;
        if (this.mBlockedByViewerStatus == BlockedByViewerStatus.MESSAGE_BLOCKED) {
            resources = this.mResources;
            i = R.string.anytime_tablet_messenger_one_on_one_thread_message_blocked_title;
        } else {
            resources = this.mResources;
            i = R.string.anytime_tablet_messenger_one_on_one_thread_facebook_blocked_title;
        }
        return resources.getString(i, this.mBlockedParticipantName);
    }

    public void setReportButtonVisibility(boolean z) {
        this.mIsReportButtonVisible = z;
        notifyPropertyChanged(121);
    }

    public OneOnOneThreadBlockedViewModel(Resources resources) {
        this.mResources = resources;
    }

    public void setBlockedParticipant(MessengerParticipant messengerParticipant) {
        this.mBlockedParticipantName = messengerParticipant.getName();
        this.mBlockedByViewerStatus = messengerParticipant.getBlockedByViewerStatus();
        notifyPropertyChanged(70);
    }
}
