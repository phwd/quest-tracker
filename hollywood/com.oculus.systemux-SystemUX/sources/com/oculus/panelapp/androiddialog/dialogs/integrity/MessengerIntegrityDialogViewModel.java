package com.oculus.panelapp.androiddialog.dialogs.integrity;

import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.androiddialog.BR;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.tablet.view.ViewModelLifecycle;

public class MessengerIntegrityDialogViewModel extends BaseObservable implements ViewModelLifecycle {
    private static final String TAG = LoggingUtil.tag(MessengerIntegrityDialogViewModel.class);
    private ActionType mActionType;
    private boolean mFailedToFbBlock;
    private boolean mFailedToFbUnblock;
    private boolean mFailedToMessengerBlock;
    private boolean mFailedToMessengerUnblock;
    private boolean mIsBlockUnblockViewLoading;
    private boolean mIsFbBlockingInProgress;
    private boolean mIsFbUnblockingInProgress;
    private boolean mIsMessengerBlockingInProgress;
    private boolean mIsMessengerUnblockingInProgress;
    private boolean mIsSelectorVisible;
    private boolean mIsTargetFbBlocked;
    private boolean mIsTargetMessengerBlocked;
    private boolean mOnlyShowBlocked;
    private final Resources mResources;
    private String mTargetName;

    public MessengerIntegrityDialogViewModel(Resources resources) {
        this.mResources = resources;
        Log.d(TAG, "Constructing ViewModel");
    }

    @Override // com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
        Log.d(TAG, "Destroying ViewModel");
    }

    @Bindable
    public String getDialogTitle() {
        if (!this.mIsSelectorVisible) {
            return this.mResources.getString(R.string.messenger_integrity_dialog_title_block_target_user, this.mTargetName);
        } else if (this.mActionType != ActionType.Block) {
            return this.mResources.getString(R.string.messenger_integrity_dialog_title_report_member);
        } else {
            if (this.mOnlyShowBlocked) {
                return this.mResources.getString(R.string.messenger_integrity_dialog_title_blocked_members);
            }
            return this.mResources.getString(R.string.messenger_integrity_dialog_title_block_member);
        }
    }

    @Bindable
    public String getFbSectionButtonText() {
        if (this.mFailedToFbBlock) {
            return this.mResources.getString(R.string.messenger_block_facebook_block_btn_failed);
        }
        if (this.mFailedToFbUnblock) {
            return this.mResources.getString(R.string.messenger_block_facebook_unblock_btn_failed);
        }
        if (this.mIsTargetFbBlocked) {
            return this.mResources.getString(R.string.messenger_block_facebook_unblock_btn);
        }
        return this.mResources.getString(R.string.messenger_block_facebook_block_btn);
    }

    @Bindable
    public int getBlockUnblockViewSpinnerVisibility() {
        return (this.mIsSelectorVisible || !this.mIsBlockUnblockViewLoading) ? 8 : 0;
    }

    @Bindable
    public int getFbBlockSectionVisibility() {
        return (isBlockUnblockViewHidden() || this.mIsTargetFbBlocked) ? 8 : 0;
    }

    @Bindable
    public String getFbSectionTitle() {
        if (!this.mIsTargetFbBlocked) {
            return this.mResources.getString(R.string.messenger_block_facebook_block_section_block_title);
        }
        return this.mResources.getString(R.string.messenger_block_facebook_block_section_unblock_title, this.mTargetName);
    }

    @Bindable
    public boolean getFbSectionButtonEnabled() {
        return !this.mIsFbBlockingInProgress && !this.mIsFbUnblockingInProgress;
    }

    @Bindable
    public int getFbUnblockSectionVisibility() {
        return (isBlockUnblockViewHidden() || !this.mIsTargetFbBlocked) ? 8 : 0;
    }

    @Bindable
    public int getFbSectionVisibility() {
        return isBlockUnblockViewHidden() ? 8 : 0;
    }

    @Bindable
    public String getFbUnblockMessengerExplanation() {
        if (this.mIsTargetMessengerBlocked) {
            return this.mResources.getString(R.string.facebook_unblock_messenger_still_blocked_explanation, this.mTargetName);
        }
        return this.mResources.getString(R.string.facebook_unblock_messenger_no_longer_blocked_explanation, this.mTargetName);
    }

    @Bindable
    public String getMessengerSectionTitle() {
        if (this.mIsTargetMessengerBlocked || this.mIsTargetFbBlocked) {
            return this.mResources.getString(R.string.messenger_integrity_dialog_messenger_unblock_section_title);
        }
        return this.mResources.getString(R.string.messenger_integrity_dialog_messenger_block_section_title);
    }

    @Bindable
    public boolean getMessengerSectionButtonEnabled() {
        return !this.mIsTargetFbBlocked && !this.mIsMessengerBlockingInProgress && !this.mIsMessengerUnblockingInProgress;
    }

    @Bindable
    public String getMessengerSectionButtonText() {
        if (this.mFailedToMessengerBlock) {
            return this.mResources.getString(R.string.messenger_block_messenger_block_btn_failed);
        }
        if (this.mFailedToMessengerUnblock) {
            return this.mResources.getString(R.string.messenger_integrity_dialog_messenger_unblock_btn_failed);
        }
        if (this.mIsTargetMessengerBlocked || this.mIsTargetFbBlocked) {
            return this.mResources.getString(R.string.messenger_integrity_dialog_messenger_unblock_btn);
        }
        return this.mResources.getString(R.string.messenger_block_messenger_block_btn);
    }

    @Bindable
    public float getMessengerSectionTitleAlpha() {
        if (!this.mIsTargetFbBlocked) {
            return 1.0f;
        }
        TypedValue typedValue = new TypedValue();
        this.mResources.getValue(R.dimen.ocopacity_disabled_opacity, typedValue, true);
        return typedValue.getFloat();
    }

    @Bindable
    public int getMessengerBlockSectionVisibility() {
        return (isBlockUnblockViewHidden() || this.mIsTargetMessengerBlocked || this.mIsTargetFbBlocked) ? 8 : 0;
    }

    @Bindable
    public int getMessengerUnblockSectionVisibility() {
        return (isBlockUnblockViewHidden() || !this.mIsTargetMessengerBlocked || this.mIsTargetFbBlocked) ? 8 : 0;
    }

    @Bindable
    public int getFbBlockedMessengerUnblockSectionVisibility() {
        return (isBlockUnblockViewHidden() || !this.mIsTargetFbBlocked) ? 8 : 0;
    }

    @Bindable
    public int getMessengerSectionVisibility() {
        return isBlockUnblockViewHidden() ? 8 : 0;
    }

    @Bindable
    public int getParticipantSelectorVisibility() {
        return this.mIsSelectorVisible ? 0 : 8;
    }

    @Bindable
    public int getSectionDividerVisibility() {
        return isBlockUnblockViewHidden() ? 8 : 0;
    }

    public boolean getSelectorVisible() {
        return this.mIsSelectorVisible;
    }

    @Bindable
    public String getTargetName() {
        return this.mTargetName;
    }

    public boolean getIsTargetFbBlocked() {
        return this.mIsTargetFbBlocked;
    }

    public boolean getIsTargetMessengerBlocked() {
        return this.mIsTargetMessengerBlocked;
    }

    public void setActionType(ActionType actionType) {
        this.mActionType = actionType;
        notifyPropertyChanged(BR.dialogTitle);
    }

    public void setFailedToFbBlock(boolean z) {
        this.mFailedToFbBlock = z;
        notifyPropertyChanged(BR.fbSectionButtonText);
    }

    public void setFailedToFbUnblock(boolean z) {
        this.mFailedToFbUnblock = z;
        notifyPropertyChanged(BR.fbSectionButtonText);
    }

    public void setFailedToMessengerBlock(boolean z) {
        this.mFailedToMessengerBlock = z;
        notifyPropertyChanged(BR.messengerSectionButtonText);
    }

    public void setFailedToMessengerUnblock(boolean z) {
        this.mFailedToMessengerUnblock = z;
        notifyPropertyChanged(BR.messengerSectionButtonText);
    }

    public void setFbBlockingInProgress(boolean z) {
        this.mIsFbBlockingInProgress = z;
        notifyPropertyChanged(BR.fbSectionButtonEnabled);
    }

    public void setFbUnblockingInProgress(boolean z) {
        this.mIsFbUnblockingInProgress = z;
        notifyPropertyChanged(BR.fbSectionButtonEnabled);
    }

    public void setIsBlockUnblockViewLoading(boolean z) {
        this.mIsBlockUnblockViewLoading = z;
        notifyPropertyChanged(BR.fbSectionVisibility);
        notifyPropertyChanged(BR.fbBlockSectionVisibility);
        notifyPropertyChanged(BR.fbBlockedMessengerUnblockSectionVisibility);
        notifyPropertyChanged(BR.fbUnblockSectionVisibility);
        notifyPropertyChanged(BR.sectionDividerVisibility);
        notifyPropertyChanged(BR.messengerSectionVisibility);
        notifyPropertyChanged(BR.messengerBlockSectionVisibility);
        notifyPropertyChanged(BR.messengerUnblockSectionVisibility);
        notifyPropertyChanged(BR.blockUnblockViewSpinnerVisibility);
    }

    public void setIsSelectorVisible(boolean z) {
        this.mIsSelectorVisible = z;
        notifyPropertyChanged(BR.dialogTitle);
        notifyPropertyChanged(BR.fbBlockSectionVisibility);
        notifyPropertyChanged(BR.fbBlockedMessengerUnblockSectionVisibility);
        notifyPropertyChanged(BR.fbUnblockSectionVisibility);
        notifyPropertyChanged(BR.fbSectionVisibility);
        notifyPropertyChanged(BR.sectionDividerVisibility);
        notifyPropertyChanged(BR.messengerBlockSectionVisibility);
        notifyPropertyChanged(BR.messengerUnblockSectionVisibility);
        notifyPropertyChanged(BR.messengerSectionVisibility);
        notifyPropertyChanged(BR.participantSelectorVisibility);
        notifyPropertyChanged(BR.blockUnblockViewSpinnerVisibility);
    }

    public void setMessengerBlockingInProgress(boolean z) {
        this.mIsMessengerBlockingInProgress = z;
        notifyPropertyChanged(BR.messengerSectionButtonEnabled);
    }

    public void setMessengerUnblockingInProgress(boolean z) {
        this.mIsMessengerUnblockingInProgress = z;
        notifyPropertyChanged(BR.messengerSectionButtonEnabled);
    }

    public void setOnlyShowBlocked(boolean z) {
        this.mOnlyShowBlocked = z;
        notifyPropertyChanged(BR.dialogTitle);
    }

    public void setTargetFbBlocked(boolean z) {
        this.mIsTargetFbBlocked = z;
        notifyPropertyChanged(BR.fbBlockSectionVisibility);
        notifyPropertyChanged(BR.fbBlockedMessengerUnblockSectionVisibility);
        notifyPropertyChanged(BR.fbUnblockSectionVisibility);
        notifyPropertyChanged(BR.fbSectionButtonText);
        notifyPropertyChanged(BR.fbSectionTitle);
        notifyPropertyChanged(BR.messengerSectionTitleAlpha);
        notifyPropertyChanged(BR.messengerSectionButtonEnabled);
        notifyPropertyChanged(BR.messengerSectionButtonText);
        notifyPropertyChanged(BR.messengerSectionTitle);
        notifyPropertyChanged(BR.messengerBlockSectionVisibility);
        notifyPropertyChanged(BR.messengerUnblockSectionVisibility);
    }

    public void setTargetMessengerBlocked(boolean z) {
        this.mIsTargetMessengerBlocked = z;
        notifyPropertyChanged(BR.messengerBlockSectionVisibility);
        notifyPropertyChanged(BR.messengerUnblockSectionVisibility);
        notifyPropertyChanged(BR.messengerSectionButtonText);
        notifyPropertyChanged(BR.messengerSectionTitle);
        notifyPropertyChanged(BR.fbUnblockMessengerExplanation);
    }

    public void setTargetName(String str) {
        this.mTargetName = str;
        notifyPropertyChanged(BR.targetName);
        notifyPropertyChanged(BR.dialogTitle);
        notifyPropertyChanged(BR.fbSectionTitle);
        notifyPropertyChanged(BR.fbUnblockMessengerExplanation);
    }

    private boolean isBlockUnblockViewHidden() {
        return this.mIsSelectorVisible || this.mIsBlockUnblockViewLoading;
    }
}
