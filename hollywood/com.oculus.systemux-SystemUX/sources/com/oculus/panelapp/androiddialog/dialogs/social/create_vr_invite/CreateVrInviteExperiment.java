package com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite;

import android.util.Log;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.androiddialog.AndroidDialogPanelApp;
import com.oculus.panelapp.androiddialog.dialogs.error.ErrorType;
import com.oculus.panelapp.androiddialog.dialogs.social.SocialUserViewModel;
import com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel;

public class CreateVrInviteExperiment extends BaseObservable implements SocialUserViewModel.SocialViewModelDataObserver {
    private static final String TAG = LoggingUtil.tag(CreateVrInviteExperiment.class);
    private CreateVrInviteDialog mCreateVrInviteDialog;
    private CreateVrInviteViewModel mCreateVrInviteViewModel;
    private boolean mIsReady = false;
    private final AndroidDialogPanelApp mPanelApp;
    private SocialUserViewModel mSocialUserViewModel;

    public CreateVrInviteExperiment(AndroidDialogPanelApp androidDialogPanelApp) {
        Log.d(TAG, "CreateVrInviteExperiment created");
        this.mPanelApp = androidDialogPanelApp;
    }

    public void initialize(CreateVrInviteDialog createVrInviteDialog, CreateVrInviteViewModel createVrInviteViewModel, SocialUserViewModel socialUserViewModel) {
        Log.d(TAG, "initializing");
        this.mCreateVrInviteDialog = createVrInviteDialog;
        this.mSocialUserViewModel = socialUserViewModel;
        this.mCreateVrInviteViewModel = createVrInviteViewModel;
        if (this.mCreateVrInviteViewModel.getInviteStep().getStepName() == CreateVrInviteViewModel.InviteFlow.StepNames.PROFILE_CONFIRMATION_ONLY) {
            Log.d(TAG, "skip destination step");
            this.mSocialUserViewModel.registerObserver(this);
            this.mCreateVrInviteViewModel.setInviteStep(CreateVrInviteViewModel.InviteFlow.StepNames.PROFILE_CONFIRMATION_ONLY);
            return;
        }
        setIsReady();
    }

    public void destroy() {
        this.mSocialUserViewModel.unregisterObserver(this);
    }

    public boolean getSkipDestinationStep() {
        boolean deviceConfig = this.mPanelApp.getDeviceConfig(Gatekeeper.SOCIAL_SKIP_DESTINATION_STEP);
        String str = TAG;
        Log.d(str, "Checking QE for skipping destination step: " + String.valueOf(deviceConfig));
        return deviceConfig;
    }

    public void setIsReady() {
        this.mIsReady = true;
        notifyPropertyChanged(BR.isReady);
    }

    @Bindable
    public boolean getIsReady() {
        return this.mIsReady;
    }

    @Override // com.oculus.panelapp.androiddialog.dialogs.social.SocialUserViewModel.SocialViewModelDataObserver
    public void onSocialUserViewModelDataUpdated(SocialUserViewModel socialUserViewModel) {
        boolean hasSeenVRInviteProfileNux = this.mSocialUserViewModel.getHasSeenVRInviteProfileNux();
        String str = TAG;
        Log.d(str, "onSocialUserViewModelDataUpdated: " + hasSeenVRInviteProfileNux);
        if (hasSeenVRInviteProfileNux) {
            this.mCreateVrInviteViewModel.createPartyNoButtonClickLogging(new CreateVrInviteViewModel.Callback.Error() {
                /* class com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.$$Lambda$CreateVrInviteExperiment$Fn5coSObdHFMVY_bMdATySoU3EE */

                @Override // com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel.Callback.Error
                public final void onError(ErrorType errorType) {
                    CreateVrInviteExperiment.this.lambda$onSocialUserViewModelDataUpdated$106$CreateVrInviteExperiment(errorType);
                }
            });
        } else {
            setIsReady();
        }
    }

    public /* synthetic */ void lambda$onSocialUserViewModelDataUpdated$106$CreateVrInviteExperiment(ErrorType errorType) {
        this.mCreateVrInviteDialog.lambda$setFutureProfileReminderStatus$100$CreateVrInviteDialog(errorType);
    }
}
