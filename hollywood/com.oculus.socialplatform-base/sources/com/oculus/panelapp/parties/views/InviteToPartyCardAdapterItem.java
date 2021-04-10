package com.oculus.panelapp.parties.views;

import X.AnonymousClass006;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.common.socialtablet.parties.PartyNotificationHelper;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.panelapp.parties.PartiesTabletPanelApp;
import com.oculus.panelapp.parties.views.actions.FBInviteToParty;
import com.oculus.panelapp.parties.views.actions.OCInviteToParty;
import java.util.Objects;

public class InviteToPartyCardAdapterItem implements PartyAdapterItem {
    public FBInviteToParty mFbAction;
    public OCInviteToParty mOcAction;
    public SocialParty mParty;

    @Override // com.oculus.panelapp.parties.views.PartyAdapterItem
    public boolean equals(PartyAdapterItem partyAdapterItem) {
        if (this != partyAdapterItem) {
            if (partyAdapterItem == null || getClass() != partyAdapterItem.getClass()) {
                return false;
            }
            InviteToPartyCardAdapterItem inviteToPartyCardAdapterItem = (InviteToPartyCardAdapterItem) partyAdapterItem;
            if (!this.mOcAction.equals(inviteToPartyCardAdapterItem.mOcAction) || !this.mFbAction.equals(inviteToPartyCardAdapterItem.mFbAction)) {
                return false;
            }
        }
        return true;
    }

    public FBInviteToParty getFBAction() {
        return this.mFbAction;
    }

    @Override // com.oculus.panelapp.parties.views.PartyAdapterItem
    public String getID() {
        return AnonymousClass006.A09(this.mFbAction.toString(), ":", this.mOcAction.toString());
    }

    @Override // com.oculus.panelapp.parties.views.PartyAdapterItem
    public PartyAdapterItemType getItemViewType() {
        return PartyAdapterItemType.INVITE_TO_PARTY_CARD;
    }

    public OCInviteToParty getOCAction() {
        return this.mOcAction;
    }

    public SocialParty getParty() {
        return this.mParty;
    }

    public int hashCode() {
        return Objects.hash(this.mFbAction, this.mOcAction);
    }

    public String toString() {
        return String.format("InviteToPartyCardAdapterItem{mFbAction=%s, mOcAction=%s}", this.mFbAction.toString(), this.mOcAction.toString());
    }

    public InviteToPartyCardAdapterItem(PartiesTabletPanelApp partiesTabletPanelApp, SocialParty socialParty) {
        this.mFbAction = new FBInviteToParty(partiesTabletPanelApp, socialParty, ClickEventButtonId.PARTIES_TAB_FB_ADD_TO_PARTY_BUTTON, SurfaceType.PARTY_MEMBER_LIST, PartyNotificationHelper.getSingleton());
        this.mOcAction = new OCInviteToParty(partiesTabletPanelApp, socialParty);
        this.mParty = socialParty;
    }
}
