package com.oculus.panelapp.social.actions;

import android.content.Context;
import android.net.Uri;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.panelapp.social.SocialPanelApp;
import com.oculus.panelapp.social.SocialUserAdapterItem;
import com.oculus.panelapp.social.actions.SocialUserAction;
import com.oculus.panelapp.social.utils.UpsellLoggingParameters;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.vrshell.SystemUXRoute;

public class JoinParty extends SocialUserAction {
    public static final String TAG = LoggingUtil.tag(JoinParty.class);
    public SocialParty mJoinableParty;
    public SocialUserAdapterItem mUser;

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public UpsellLoggingParameters upsellParams() {
        return null;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public SocialUserActionType getType() {
        return SocialUserActionType.JOIN_PARTY;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000c, code lost:
        if (r3 == com.oculus.horizoncontent.social.SocialParty.PartyMembership.NONE) goto L_0x000e;
     */
    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isRelevant() {
        /*
            r4 = this;
            com.oculus.panelapp.social.SocialUserAdapterItem r0 = r4.mUser
            com.oculus.horizoncontent.social.SocialParty$PartyMembership r3 = r0.getPartyMembership()
            r2 = 0
            if (r3 == 0) goto L_0x000e
            com.oculus.horizoncontent.social.SocialParty$PartyMembership r0 = com.oculus.horizoncontent.social.SocialParty.PartyMembership.NONE
            r1 = 0
            if (r3 != r0) goto L_0x000f
        L_0x000e:
            r1 = 1
        L_0x000f:
            com.oculus.horizoncontent.social.SocialParty r0 = r4.mJoinableParty
            if (r0 == 0) goto L_0x0016
            if (r1 == 0) goto L_0x0016
            r2 = 1
        L_0x0016:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.social.actions.JoinParty.isRelevant():boolean");
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public void preformAction(SocialPanelApp socialPanelApp, Context context, SocialUserActionActionHandler socialUserActionActionHandler) {
        socialPanelApp.actionNavigate(new Uri.Builder().encodedPath(SystemUXRoute.SOCIAL_JOIN_PARTY.path()).appendQueryParameter("party_id", this.mJoinableParty.mID).appendQueryParameter("source", "auiv2_user_card").build().toString(), "");
    }

    public JoinParty(SocialUserAdapterItem socialUserAdapterItem, SocialParty socialParty) {
        this.mUser = socialUserAdapterItem;
        this.mJoinableParty = socialParty;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public ClickEventButtonId getButtonId(SocialUserAction.Source source) {
        return ClickEventButtonId.AUIV2_SOCIAL_PARTIES_JOIN_OPEN_PARTY;
    }
}
