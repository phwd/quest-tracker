package com.oculus.panelapp.androiddialog.dialogs.integrity.block;

import android.content.res.Resources;
import android.util.Log;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.androiddialog.BR;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.tablet.view.ViewModelLifecycle;

public class FacebookBlockDialogViewModel extends BaseObservable implements ViewModelLifecycle {
    private static final String TAG = LoggingUtil.tag(FacebookBlockDialogViewModel.class);
    private boolean mDidBlockFail;
    private boolean mDidUnblockFail;
    private boolean mIsBlockingInProgress;
    private boolean mIsTargetBlocked;
    private boolean mIsUnblockingInProgress;
    private final Resources mResources;
    private String mTargetName;

    public FacebookBlockDialogViewModel(Resources resources) {
        this.mResources = resources;
        Log.d(TAG, "Constructing ViewModel");
    }

    @Override // com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
        Log.d(TAG, "Destroying ViewModel");
    }

    @Bindable
    public boolean getBlockButtonEnabled() {
        return !this.mIsBlockingInProgress;
    }

    @Bindable
    public String getBlockButtonText() {
        if (this.mDidBlockFail) {
            return this.mResources.getString(R.string.facebook_block_dialog_block_btn_failed);
        }
        return this.mResources.getString(R.string.facebook_block_dialog_block_btn);
    }

    @Bindable
    public boolean getUnblockButtonEnabled() {
        return !this.mIsUnblockingInProgress;
    }

    @Bindable
    public String getUnblockButtonText() {
        if (this.mDidUnblockFail) {
            return this.mResources.getString(R.string.facebook_block_dialog_unblock_btn_failed);
        }
        return this.mResources.getString(R.string.facebook_block_dialog_unblock_btn);
    }

    @Bindable
    public int getBlockSectionVisibility() {
        return this.mIsTargetBlocked ? 8 : 0;
    }

    @Bindable
    public int getUnblockSectionVisibility() {
        return this.mIsTargetBlocked ? 0 : 8;
    }

    @Bindable
    public String getTargetName() {
        return this.mTargetName;
    }

    public void setBlockFailed() {
        this.mDidBlockFail = true;
        notifyPropertyChanged(BR.blockButtonText);
    }

    public void setUnblockFailed() {
        this.mDidUnblockFail = true;
        notifyPropertyChanged(BR.unblockButtonText);
    }

    public void setIsBlockingInProgress(boolean z) {
        this.mIsBlockingInProgress = z;
        notifyPropertyChanged(BR.blockButtonEnabled);
    }

    public void setIsUnblockingInProgress(boolean z) {
        this.mIsUnblockingInProgress = z;
        notifyPropertyChanged(BR.unblockButtonEnabled);
    }

    public void setIsTargetBlocked(boolean z) {
        this.mIsTargetBlocked = z;
        notifyPropertyChanged(BR.blockSectionVisibility);
        notifyPropertyChanged(BR.unblockSectionVisibility);
    }

    public void setTargetName(String str) {
        this.mTargetName = str;
        notifyPropertyChanged(BR.targetName);
    }
}
