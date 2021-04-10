package com.oculus.panelapp.social.actions;

import android.content.Context;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.panelapp.social.SocialPanelApp;
import com.oculus.panelapp.social.SocialUserAdapterItem;
import com.oculus.panelapp.social.actions.SocialUserAction;
import com.oculus.panelapp.social.utils.UpsellLoggingParameters;
import com.oculus.tablet.logging.ClickEventButtonId;

public class SelfMute extends SocialUserAction {
    private int mMuteState;
    private SocialPanelApp mPanelApp;
    private SocialUserAdapterItem mUser;

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public UpsellLoggingParameters upsellParams() {
        return null;
    }

    public SelfMute(SocialUserAdapterItem socialUserAdapterItem, Context context) {
        this.mUser = socialUserAdapterItem;
        this.mMuteState = HorizonContentProviderHelper.getPartyLocalMute(context);
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public boolean isRelevant() {
        SocialUser user = this.mUser.getUser();
        if (user == null || this.mUser.getParty() == null || user.getUserType() != SocialUser.UserRowType.PARTY_CONTROLS) {
            return false;
        }
        return true;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public SocialUserActionType getType() {
        SocialParty party = this.mUser.getParty();
        SocialUser user = this.mUser.getUser();
        if (party == null || user == null) {
            return SocialUserActionType.SELF_MUTE;
        }
        return this.mMuteState == 1 ? SocialUserActionType.SELF_UNMUTE : SocialUserActionType.SELF_MUTE;
    }

    /* access modifiers changed from: package-private */
    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public ClickEventButtonId getButtonId(SocialUserAction.Source source) {
        if (this.mMuteState == 1) {
            return ClickEventButtonId.AUIV2_SOCIAL_PARTIES_UNMUTE_SELF;
        }
        return ClickEventButtonId.AUIV2_SOCIAL_PARTIES_MUTE_SELF;
    }

    /* access modifiers changed from: package-private */
    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public void preformAction(SocialPanelApp socialPanelApp, Context context, final SocialUserActionActionHandler socialUserActionActionHandler) {
        SocialParty party = this.mUser.getParty();
        SocialUser user = this.mUser.getUser();
        if (party != null && user != null) {
            final int i = 1;
            if (this.mMuteState == 1) {
                i = 2;
            }
            HorizonContentProviderHelper.setPartyLocalMuteAsync(context, i, new HorizonContentProviderHelper.MuteStateCallback() {
                /* class com.oculus.panelapp.social.actions.SelfMute.AnonymousClass1 */

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.MuteStateCallback
                public void onSuccess(int i) {
                    int i2 = i;
                    if (i == i2) {
                        SelfMute.this.mMuteState = i2;
                        socialUserActionActionHandler.onSuccess();
                        return;
                    }
                    socialUserActionActionHandler.onError();
                }

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
                public void onError() {
                    socialUserActionActionHandler.onError();
                }
            });
        }
    }
}
