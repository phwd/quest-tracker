package com.oculus.panelapp.people.model;

import com.oculus.horizoncontent.social.FBSocialUser;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialUser;
import java.util.Set;
import javax.annotation.Nullable;

public final class FBViewerSocialParty implements IViewerSocialParty {
    @Nullable
    public String mCurrentPartyID;
    @Nullable
    public Set<String> mCurrentPartyInvitedUserIDs;
    public boolean mHasLinkSharing;
    @Nullable
    public Set<String> mInvitedPartyIDs;

    @Override // com.oculus.panelapp.people.model.IViewerSocialParty
    public boolean isFull() {
        return false;
    }

    @Override // com.oculus.panelapp.people.model.IViewerSocialParty
    public boolean getHasLinkSharing() {
        return this.mHasLinkSharing;
    }

    @Override // com.oculus.panelapp.people.model.IViewerSocialParty
    public String getID() {
        return this.mCurrentPartyID;
    }

    @Override // com.oculus.panelapp.people.model.IViewerSocialParty
    public SocialParty.PartyMembership getUserPartyMembership(SocialUser socialUser) {
        Set<String> set;
        String str = this.mCurrentPartyID;
        if (str != null && str.equalsIgnoreCase(socialUser.mCurrentPartyID)) {
            return SocialParty.PartyMembership.MEMBER;
        }
        if (!(socialUser instanceof FBSocialUser) || (set = this.mCurrentPartyInvitedUserIDs) == null || !set.contains(((FBSocialUser) socialUser).mVrPersonaID)) {
            return SocialParty.PartyMembership.NONE;
        }
        return SocialParty.PartyMembership.INVITED;
    }

    @Override // com.oculus.panelapp.people.model.IViewerSocialParty
    public boolean isViewerInvitedToParty(String str) {
        Set<String> set = this.mInvitedPartyIDs;
        if (set == null || !set.contains(str)) {
            return false;
        }
        return true;
    }

    public void setCurrentPartyID(String str) {
        this.mCurrentPartyID = str;
    }

    public void setCurrentPartyInvitedUserIDs(Set<String> set) {
        this.mCurrentPartyInvitedUserIDs = set;
    }

    public void setHasLinkSharing(boolean z) {
        this.mHasLinkSharing = z;
    }

    public void setInvitedPartyIDs(Set<String> set) {
        this.mInvitedPartyIDs = set;
    }
}
