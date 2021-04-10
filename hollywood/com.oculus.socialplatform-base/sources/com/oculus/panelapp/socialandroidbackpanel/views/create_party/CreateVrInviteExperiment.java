package com.oculus.panelapp.socialandroidbackpanel.views.create_party;

import X.AnonymousClass1uc;
import androidx.databinding.Bindable;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.socialandroidbackpanel.SocialAndroidBackPanelApp;
import com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel;
import com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorType;
import com.oculus.panelapp.socialandroidbackpanel.views.social.SocialUserViewModel;

public class CreateVrInviteExperiment extends AnonymousClass1uc implements SocialUserViewModel.SocialViewModelDataObserver {
    public static final String TAG = LoggingUtil.tag(CreateVrInviteExperiment.class);
    public CreateVrInviteDialog mCreateVrInviteDialog;
    public CreateVrInviteViewModel mCreateVrInviteViewModel;
    public boolean mIsReady = false;
    public final SocialAndroidBackPanelApp mPanelApp;
    public SocialUserViewModel mSocialUserViewModel;

    public void setIsReady() {
        this.mIsReady = true;
        notifyPropertyChanged(33);
    }

    public void destroy() {
        this.mSocialUserViewModel.unregisterObserver(this);
    }

    @Bindable
    public boolean getIsReady() {
        return this.mIsReady;
    }

    public boolean getSkipDestinationStep() {
        return this.mPanelApp.isGKEnabled(Gatekeeper.SOCIAL_SKIP_DESTINATION_STEP);
    }

    public void initialize(CreateVrInviteDialog createVrInviteDialog, CreateVrInviteViewModel createVrInviteViewModel, SocialUserViewModel socialUserViewModel) {
        this.mCreateVrInviteDialog = createVrInviteDialog;
        this.mSocialUserViewModel = socialUserViewModel;
        this.mCreateVrInviteViewModel = createVrInviteViewModel;
        CreateVrInviteViewModel.InviteFlow.StepNames stepNames = createVrInviteViewModel.mInviteFlow.mStep.mStepName;
        CreateVrInviteViewModel.InviteFlow.StepNames stepNames2 = CreateVrInviteViewModel.InviteFlow.StepNames.PROFILE_CONFIRMATION_ONLY;
        if (stepNames == stepNames2) {
            socialUserViewModel.registerObserver(this);
            this.mCreateVrInviteViewModel.setInviteStep(stepNames2);
            return;
        }
        setIsReady();
    }

    public /* synthetic */ void lambda$onSocialUserViewModelDataUpdated$0$CreateVrInviteExperiment(ErrorType errorType) {
        this.mCreateVrInviteDialog.lambda$setFutureProfileReminderStatus$8$CreateVrInviteDialog(errorType);
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.views.social.SocialUserViewModel.SocialViewModelDataObserver
    public void onSocialUserViewModelDataUpdated(SocialUserViewModel socialUserViewModel) {
        if (this.mSocialUserViewModel.mHasSeenVRInviteProfileNux) {
            this.mCreateVrInviteViewModel.createPartyAndSendInvites(true, new CreateVrInviteViewModel.Callback.Error() {
                /* class com.oculus.panelapp.socialandroidbackpanel.views.create_party.$$Lambda$CreateVrInviteExperiment$I4QbuYgmqZZaReuh01lviGyEqpA2 */

                @Override // com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel.Callback.Error
                public final void onError(ErrorType errorType) {
                    CreateVrInviteExperiment.this.lambda$onSocialUserViewModelDataUpdated$0$CreateVrInviteExperiment(errorType);
                }
            });
        } else {
            setIsReady();
        }
    }

    public CreateVrInviteExperiment(SocialAndroidBackPanelApp socialAndroidBackPanelApp) {
        this.mPanelApp = socialAndroidBackPanelApp;
    }
}
