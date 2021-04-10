package com.oculus.panelapp.people.model;

import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialUser;
import java.util.List;
import javax.annotation.Nullable;

public final class OCViewerSocialParty implements IViewerSocialParty {
    @Nullable
    public SocialParty mCurrentParty;
    @Nullable
    public List<SocialParty> mInvitedParties;

    @Override // com.oculus.panelapp.people.model.IViewerSocialParty
    public boolean getHasLinkSharing() {
        return false;
    }

    @Override // com.oculus.panelapp.people.model.IViewerSocialParty
    public boolean isViewerInvitedToParty(String str) {
        List<SocialParty> list;
        if (!(str == null || (list = this.mInvitedParties) == null)) {
            for (SocialParty socialParty : list) {
                if (str.equalsIgnoreCase(socialParty.mID)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.oculus.panelapp.people.model.IViewerSocialParty
    public String getID() {
        SocialParty socialParty = this.mCurrentParty;
        if (socialParty == null) {
            return null;
        }
        return socialParty.mID;
    }

    public SocialParty getParty() {
        return this.mCurrentParty;
    }

    @Override // com.oculus.panelapp.people.model.IViewerSocialParty
    public SocialParty.PartyMembership getUserPartyMembership(SocialUser socialUser) {
        SocialParty socialParty = this.mCurrentParty;
        if (socialParty != null) {
            return socialParty.getUserPartyMembership(socialUser);
        }
        return SocialParty.PartyMembership.NONE;
    }

    @Override // com.oculus.panelapp.people.model.IViewerSocialParty
    public boolean isFull() {
        SocialParty socialParty = this.mCurrentParty;
        if (socialParty == null || !socialParty.isFull()) {
            return false;
        }
        return true;
    }

    public void setCurrentParty(SocialParty socialParty) {
        this.mCurrentParty = socialParty;
    }

    public void setInvitedParties(List<SocialParty> list) {
        this.mInvitedParties = list;
    }
}
