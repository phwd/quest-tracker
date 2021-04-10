package com.oculus.panelapp.social;

import com.oculus.horizoncontent.social.SocialGroupLaunchAppDestination;
import com.oculus.horizoncontent.social.SocialGroupLaunchResponse;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialUser;
import java.util.Objects;
import javax.annotation.Nullable;

public class SocialUserAdapterItem implements SocialAdapterItem {
    @Nullable
    public SocialParty mParty;
    public SocialUser mUser;

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public boolean equals(SocialAdapterItem socialAdapterItem) {
        if (this != socialAdapterItem) {
            if (socialAdapterItem == null || getClass() != socialAdapterItem.getClass()) {
                return false;
            }
            SocialUserAdapterItem socialUserAdapterItem = (SocialUserAdapterItem) socialAdapterItem;
            if (!Objects.equals(this.mUser, socialUserAdapterItem.mUser) || !Objects.equals(getPartyMembership(), socialUserAdapterItem.getPartyMembership()) || !Objects.equals(getGroupLaunchResponse(), socialUserAdapterItem.getGroupLaunchResponse()) || !Objects.equals(this.mParty, socialUserAdapterItem.mParty)) {
                return false;
            }
        }
        return true;
    }

    public SocialGroupLaunchResponse getGroupLaunchResponse() {
        SocialGroupLaunchAppDestination socialGroupLaunchAppDestination;
        SocialParty socialParty = this.mParty;
        if (socialParty == null || (socialGroupLaunchAppDestination = socialParty.mSocialProposedGroupLaunchAppDestination) == null) {
            return null;
        }
        return socialGroupLaunchAppDestination.getUserResponse(this.mUser.mID);
    }

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public String getID() {
        return this.mUser.mID;
    }

    public boolean getIsUserInvitedByLocalUserToParty(String str) {
        SocialParty socialParty = this.mParty;
        if (socialParty != null) {
            return socialParty.getIsUserInvitedByLocalUserToParty(str, this.mUser);
        }
        return false;
    }

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public SocialAdapterItemType getItemViewType() {
        return SocialAdapterItemType.USER;
    }

    @Nullable
    public SocialParty getParty() {
        return this.mParty;
    }

    @Nullable
    public SocialParty.PartyMembership getPartyMembership() {
        SocialParty socialParty = this.mParty;
        if (socialParty != null) {
            return socialParty.getUserPartyMembership(this.mUser);
        }
        return null;
    }

    public SocialUser getUser() {
        return this.mUser;
    }

    public int hashCode() {
        return this.mUser.hashCode();
    }

    public String toString() {
        Object userPartyMembership;
        StringBuilder sb = new StringBuilder("SocialUserAdapterItem{mUser=");
        sb.append(this.mUser);
        sb.append(", mPartyMembership=");
        SocialParty socialParty = this.mParty;
        if (socialParty == null) {
            userPartyMembership = "null";
        } else {
            userPartyMembership = socialParty.getUserPartyMembership(this.mUser);
        }
        sb.append(userPartyMembership);
        sb.append('}');
        return sb.toString();
    }

    public SocialUserAdapterItem(SocialUser socialUser, SocialParty socialParty) {
        this.mParty = socialParty;
        this.mUser = socialUser;
    }
}
