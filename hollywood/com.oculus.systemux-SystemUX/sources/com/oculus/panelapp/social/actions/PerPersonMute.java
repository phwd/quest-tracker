package com.oculus.panelapp.social.actions;

import android.content.Context;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.panelapp.social.SocialPanelApp;
import com.oculus.panelapp.social.SocialUserAdapterItem;
import com.oculus.panelapp.social.actions.SocialUserAction;
import com.oculus.panelapp.social.utils.UpsellLoggingParameters;
import com.oculus.tablet.logging.ClickEventButtonId;

public class PerPersonMute extends SocialUserAction {
    private int mMuteState;
    private SocialPanelApp mPanelApp;
    private SocialUserAdapterItem mUser;

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public UpsellLoggingParameters upsellParams() {
        return null;
    }

    public PerPersonMute(SocialUserAdapterItem socialUserAdapterItem, SocialPanelApp socialPanelApp) {
        this.mUser = socialUserAdapterItem;
        this.mPanelApp = socialPanelApp;
        SocialParty party = this.mUser.getParty();
        this.mMuteState = party != null ? party.getIsUserMutedByViewer(socialUserAdapterItem.getID()) : false ? 1 : 2;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public boolean isRelevant() {
        boolean isGKEnabled = this.mPanelApp.isGKEnabled(Gatekeeper.PARTIES_PER_PERSON_MUTE);
        SocialUser user = this.mUser.getUser();
        if (user == null || this.mUser.getParty() == null || !isGKEnabled || user.getUserType() != SocialUser.UserRowType.PARTY_MEMBER) {
            return false;
        }
        return true;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public SocialUserActionType getType() {
        SocialParty party = this.mUser.getParty();
        SocialUser user = this.mUser.getUser();
        if (party == null || user == null) {
            return SocialUserActionType.PER_PERSON_MUTE;
        }
        return this.mMuteState == 1 ? SocialUserActionType.PER_PERSON_UNMUTE : SocialUserActionType.PER_PERSON_MUTE;
    }

    /* access modifiers changed from: package-private */
    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public ClickEventButtonId getButtonId(SocialUserAction.Source source) {
        if (this.mMuteState == 1) {
            return ClickEventButtonId.AUIV2_SOCIAL_PARTIES_PER_PERSON_UNMUTE;
        }
        return ClickEventButtonId.AUIV2_SOCIAL_PARTIES_PER_PERSON_MUTE;
    }

    /* access modifiers changed from: package-private */
    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public void preformAction(SocialPanelApp socialPanelApp, Context context, final SocialUserActionActionHandler socialUserActionActionHandler) {
        SocialParty party = this.mUser.getParty();
        SocialUser user = this.mUser.getUser();
        if (party != null && user != null) {
            int i = 1;
            if (this.mMuteState == 1) {
                i = 2;
            }
            HorizonContentProviderHelper.setPartyPerPersonMuteStatusAsync(context, this.mUser.getID(), i, new HorizonContentProviderHelper.PerPersonMuteStateCallback() {
                /* class com.oculus.panelapp.social.actions.PerPersonMute.AnonymousClass1 */

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.PerPersonMuteStateCallback
                public void onSuccess(int i) {
                    PerPersonMute.this.mMuteState = i;
                    PerPersonMute.this.mPanelApp.acquireSocialViewModel().loadPartyData();
                    socialUserActionActionHandler.onSuccess();
                }

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
                public void onError() {
                    socialUserActionActionHandler.onError();
                }
            });
        }
    }
}
