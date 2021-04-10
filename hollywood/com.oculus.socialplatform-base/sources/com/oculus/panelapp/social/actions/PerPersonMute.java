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
            return ClickEventButtonId.AUIV2_SOCIAL_PARTIES_PER_PERSON_UNMUTE;
        }
        return ClickEventButtonId.AUIV2_SOCIAL_PARTIES_PER_PERSON_MUTE;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public SocialUserActionType getType() {
        SocialUserAdapterItem socialUserAdapterItem = this.mUser;
        SocialParty socialParty = socialUserAdapterItem.mParty;
        SocialUser socialUser = socialUserAdapterItem.mUser;
        if (socialParty == null || socialUser == null || this.mMuteState != 1) {
            return SocialUserActionType.PER_PERSON_MUTE;
        }
        return SocialUserActionType.PER_PERSON_UNMUTE;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public boolean isRelevant() {
        boolean isGKEnabled = this.mPanelApp.isGKEnabled(Gatekeeper.PARTIES_PER_PERSON_MUTE);
        SocialUserAdapterItem socialUserAdapterItem = this.mUser;
        SocialUser socialUser = socialUserAdapterItem.mUser;
        if (socialUser == null || socialUserAdapterItem.mParty == null || !isGKEnabled || socialUser.mUserType != SocialUser.UserRowType.PARTY_MEMBER) {
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
            int i = 1;
            if (this.mMuteState == 1) {
                i = 2;
            }
            HorizonContentProviderHelper.setPartyPerPersonMuteStatusAsync(context, socialUserAdapterItem.getID(), i, new HorizonContentProviderHelper.PerPersonMuteStateCallback() {
                /* class com.oculus.panelapp.social.actions.PerPersonMute.AnonymousClass1 */

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
                public void onError() {
                    socialUserActionActionHandler.onError();
                }

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.PerPersonMuteStateCallback
                public void onSuccess(int i) {
                    PerPersonMute perPersonMute = PerPersonMute.this;
                    perPersonMute.mMuteState = i;
                    perPersonMute.mPanelApp.acquireSocialViewModel().loadPartyData();
                    socialUserActionActionHandler.onSuccess();
                }
            });
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0016, code lost:
        if (r1 == false) goto L_0x0018;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public PerPersonMute(com.oculus.panelapp.social.SocialUserAdapterItem r3, com.oculus.panelapp.social.SocialPanelApp r4) {
        /*
            r2 = this;
            r2.<init>()
            r2.mUser = r3
            r2.mPanelApp = r4
            com.oculus.horizoncontent.social.SocialParty r0 = r3.mParty
            if (r0 == 0) goto L_0x0018
            java.lang.String r1 = r3.getID()
            java.util.HashSet<java.lang.String> r0 = r0.mUsersMutedByViewer
            boolean r1 = r0.contains(r1)
            r0 = 1
            if (r1 != 0) goto L_0x0019
        L_0x0018:
            r0 = 2
        L_0x0019:
            r2.mMuteState = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.social.actions.PerPersonMute.<init>(com.oculus.panelapp.social.SocialUserAdapterItem, com.oculus.panelapp.social.SocialPanelApp):void");
    }
}
