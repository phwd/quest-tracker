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
    public int mMuteState;
    public SocialPanelApp mPanelApp;
    public SocialUserAdapterItem mUser;

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public UpsellLoggingParameters upsellParams() {
        return null;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public ClickEventButtonId getButtonId(SocialUserAction.Source source) {
        if (this.mMuteState == 1) {
            return ClickEventButtonId.AUIV2_SOCIAL_PARTIES_UNMUTE_SELF;
        }
        return ClickEventButtonId.AUIV2_SOCIAL_PARTIES_MUTE_SELF;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public SocialUserActionType getType() {
        SocialUserAdapterItem socialUserAdapterItem = this.mUser;
        SocialParty socialParty = socialUserAdapterItem.mParty;
        SocialUser socialUser = socialUserAdapterItem.mUser;
        if (socialParty == null || socialUser == null || this.mMuteState != 1) {
            return SocialUserActionType.SELF_MUTE;
        }
        return SocialUserActionType.SELF_UNMUTE;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public boolean isRelevant() {
        SocialUserAdapterItem socialUserAdapterItem = this.mUser;
        SocialUser socialUser = socialUserAdapterItem.mUser;
        if (socialUser == null || socialUserAdapterItem.mParty == null || socialUser.mUserType != SocialUser.UserRowType.PARTY_CONTROLS) {
            return false;
        }
        return true;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public void preformAction(SocialPanelApp socialPanelApp, Context context, final SocialUserActionActionHandler socialUserActionActionHandler) {
        SocialUserAdapterItem socialUserAdapterItem = this.mUser;
        SocialParty socialParty = socialUserAdapterItem.mParty;
        SocialUser socialUser = socialUserAdapterItem.mUser;
        if (socialParty != null && socialUser != null) {
            final int i = 1;
            if (this.mMuteState == 1) {
                i = 2;
            }
            HorizonContentProviderHelper.setPartyLocalMuteAsync(context, i, new HorizonContentProviderHelper.MuteStateCallback() {
                /* class com.oculus.panelapp.social.actions.SelfMute.AnonymousClass1 */

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
                public void onError() {
                    socialUserActionActionHandler.onError();
                }

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
            });
        }
    }

    public SelfMute(SocialUserAdapterItem socialUserAdapterItem, Context context) {
        this.mUser = socialUserAdapterItem;
        this.mMuteState = HorizonContentProviderHelper.getPartyLocalMute(context);
    }
}
