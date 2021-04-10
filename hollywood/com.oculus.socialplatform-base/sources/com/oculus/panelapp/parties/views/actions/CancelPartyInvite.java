package com.oculus.panelapp.parties.views.actions;

import android.content.Context;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.panelapp.parties.PartiesTabletPanelApp;
import com.oculus.panelapp.parties.utils.PartyUtils;
import com.oculus.panelapp.parties.views.PartyUserCardAdapterItem;

public class CancelPartyInvite extends PartyAction {
    public PartyUserCardAdapterItem mAdapterItem;
    public ClickEventButtonId mButtonId;
    public PartiesTabletPanelApp mPanelApp;
    public SurfaceType mSurfaceType;

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public PartyActionType getType() {
        return PartyActionType.CANCEL_PARTY_INVITE;
    }

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public boolean isRelevant() {
        if (this.mAdapterItem.getPartyMembership() == SocialParty.PartyMembership.INVITED) {
            return true;
        }
        return false;
    }

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public void performAction(Context context, PartyActionHandler partyActionHandler) {
        PartyUserCardAdapterItem partyUserCardAdapterItem = this.mAdapterItem;
        SocialParty socialParty = partyUserCardAdapterItem.mParty;
        SocialUser socialUser = partyUserCardAdapterItem.mUser;
        if (socialParty != null && socialUser != null) {
            PartyUtils.cancelPartyInvite(context, socialUser.mID, socialParty.mID, this.mPanelApp, this.mButtonId, this.mSurfaceType, partyActionHandler);
        }
    }

    public CancelPartyInvite(PartyUserCardAdapterItem partyUserCardAdapterItem, PartiesTabletPanelApp partiesTabletPanelApp, ClickEventButtonId clickEventButtonId, SurfaceType surfaceType) {
        this.mAdapterItem = partyUserCardAdapterItem;
        this.mPanelApp = partiesTabletPanelApp;
        this.mButtonId = clickEventButtonId;
        this.mSurfaceType = surfaceType;
    }
}
