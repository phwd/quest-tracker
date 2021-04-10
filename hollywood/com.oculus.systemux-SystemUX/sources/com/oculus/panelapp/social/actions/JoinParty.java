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
    private static final String TAG = LoggingUtil.tag(JoinParty.class);
    private SocialParty mJoinableParty;
    private SocialUserAdapterItem mUser;

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public UpsellLoggingParameters upsellParams() {
        return null;
    }

    public JoinParty(SocialUserAdapterItem socialUserAdapterItem, SocialParty socialParty) {
        this.mUser = socialUserAdapterItem;
        this.mJoinableParty = socialParty;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public boolean isRelevant() {
        SocialParty.PartyMembership partyMembership = this.mUser.getPartyMembership();
        this.mUser.getParty();
        return this.mJoinableParty != null && (partyMembership == null || partyMembership == SocialParty.PartyMembership.NONE);
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public SocialUserActionType getType() {
        return SocialUserActionType.JOIN_PARTY;
    }

    /* access modifiers changed from: package-private */
    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public ClickEventButtonId getButtonId(SocialUserAction.Source source) {
        return ClickEventButtonId.AUIV2_SOCIAL_PARTIES_JOIN_OPEN_PARTY;
    }

    /* access modifiers changed from: package-private */
    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public void preformAction(SocialPanelApp socialPanelApp, Context context, SocialUserActionActionHandler socialUserActionActionHandler) {
        socialPanelApp.actionNavigate(new Uri.Builder().encodedPath(SystemUXRoute.SOCIAL_JOIN_PARTY.path()).appendQueryParameter("party_id", this.mJoinableParty.getID()).appendQueryParameter("source", "auiv2_user_card").build().toString(), "");
    }
}
