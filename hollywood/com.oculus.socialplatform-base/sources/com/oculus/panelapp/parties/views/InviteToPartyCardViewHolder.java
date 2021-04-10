package com.oculus.panelapp.parties.views;

import X.AnonymousClass1Ah;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.common.socialtablet.deviceconfig.DeviceConfigSocialPlatformMC;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.parties.PartiesTabletPanelApp;
import com.oculus.panelapp.parties.databinding.InviteToPartyCardBinding;
import com.oculus.panelapp.parties.databinding.InviteToPartyCtaButtonBinding;
import com.oculus.panelapp.parties.views.actions.PartyAction;
import com.oculus.panelapp.parties.views.actions.PartyActionHandler;

public class InviteToPartyCardViewHolder extends AnonymousClass1Ah {
    public InviteToPartyCardBinding mBinding;
    public Context mContext;
    public InviteToPartyCardViewModel mInviteToPartyCardViewModel;
    public PartiesTabletPanelApp mPanelApp;

    public InviteToPartyCardViewHolder(InviteToPartyCardBinding inviteToPartyCardBinding, Context context, PartiesTabletPanelApp partiesTabletPanelApp) {
        super(inviteToPartyCardBinding.inviteToPartyActionCard);
        this.mContext = context;
        this.mPanelApp = partiesTabletPanelApp;
        this.mBinding = inviteToPartyCardBinding;
        InviteToPartyCardViewModel inviteToPartyCardViewModel = new InviteToPartyCardViewModel();
        this.mInviteToPartyCardViewModel = inviteToPartyCardViewModel;
        inviteToPartyCardViewModel.mIsFbActionEnabled = DeviceConfigHelper.getBoolean(partiesTabletPanelApp.mContext, DeviceConfigSocialPlatformMC.OCULUS_PARTIES_INVITE_FROM_FB_ENTRYPOINT);
        this.mBinding.setInviteToPartyCardViewModel(this.mInviteToPartyCardViewModel);
        initializeHoverOverlay();
    }

    private void bindCTA(InviteToPartyCtaButtonBinding inviteToPartyCtaButtonBinding, PartyAction partyAction, ClickEventButtonId clickEventButtonId) {
        OCButton oCButton = inviteToPartyCtaButtonBinding.inviteToPartyCtaButton;
        oCButton.mEventHandler = this.mPanelApp;
        oCButton.setOnClickListener(new View.OnClickListener(partyAction, clickEventButtonId, inviteToPartyCtaButtonBinding) {
            /* class com.oculus.panelapp.parties.views.$$Lambda$InviteToPartyCardViewHolder$8LyMog1rJMLMKAtSV8DupAPUuIs2 */
            public final /* synthetic */ PartyAction f$1;
            public final /* synthetic */ ClickEventButtonId f$2;
            public final /* synthetic */ InviteToPartyCtaButtonBinding f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void onClick(View view) {
                InviteToPartyCardViewHolder.this.lambda$bindCTA$0$InviteToPartyCardViewHolder(this.f$1, this.f$2, this.f$3, view);
            }
        });
    }

    private void initializeHoverOverlay() {
        this.mBinding.cardHoverOverlay.setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.panelapp.parties.views.$$Lambda$InviteToPartyCardViewHolder$I4xKolQlKbt4KI6Zd6OFHh8XXM2 */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return InviteToPartyCardViewHolder.this.lambda$initializeHoverOverlay$1$InviteToPartyCardViewHolder(view, motionEvent);
            }
        });
    }

    public void bindData(InviteToPartyCardAdapterItem inviteToPartyCardAdapterItem) {
        this.mInviteToPartyCardViewModel.setInviteToPartyCardAdapterItem(inviteToPartyCardAdapterItem);
        InviteToPartyCardBinding inviteToPartyCardBinding = this.mBinding;
        InviteToPartyCtaButtonBinding inviteToPartyCtaButtonBinding = inviteToPartyCardBinding.overlayFbActionCtaButton;
        InviteToPartyCtaButtonBinding inviteToPartyCtaButtonBinding2 = inviteToPartyCardBinding.overlayOcActionCtaButton;
        bindCTA(inviteToPartyCtaButtonBinding, inviteToPartyCardAdapterItem.mFbAction, ClickEventButtonId.PARTIES_TAB_FB_ADD_TO_PARTY_BUTTON);
        bindCTA(inviteToPartyCtaButtonBinding2, inviteToPartyCardAdapterItem.mOcAction, ClickEventButtonId.PARTIES_TAB_OC_ADD_TO_PARTY_BUTTON);
    }

    public /* synthetic */ void lambda$bindCTA$0$InviteToPartyCardViewHolder(PartyAction partyAction, ClickEventButtonId clickEventButtonId, final InviteToPartyCtaButtonBinding inviteToPartyCtaButtonBinding, View view) {
        if (partyAction != null) {
            this.mPanelApp.mSocialLogger.logButtonClick(clickEventButtonId, SurfaceType.PARTY_MEMBER_LIST);
            inviteToPartyCtaButtonBinding.setIsLoading(true);
            AnonymousClass1 r1 = null;
            if (partyAction.getType().isAsyncAction()) {
                r1 = new PartyActionHandler() {
                    /* class com.oculus.panelapp.parties.views.InviteToPartyCardViewHolder.AnonymousClass1 */

                    @Override // com.oculus.panelapp.parties.views.actions.PartyActionHandler
                    public void onError() {
                        inviteToPartyCtaButtonBinding.setIsLoading(false);
                    }

                    @Override // com.oculus.panelapp.parties.views.actions.PartyActionHandler
                    public void onSuccess() {
                        inviteToPartyCtaButtonBinding.setIsLoading(false);
                    }
                };
            }
            partyAction.execute(this.mContext, r1);
        }
    }

    public /* synthetic */ boolean lambda$initializeHoverOverlay$1$InviteToPartyCardViewHolder(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 9) {
            this.mInviteToPartyCardViewModel.setIsHoverOverlayShowing(true);
        } else if (motionEvent.getAction() == 10) {
            this.mInviteToPartyCardViewModel.setIsHoverOverlayShowing(false);
            return false;
        }
        return false;
    }
}
