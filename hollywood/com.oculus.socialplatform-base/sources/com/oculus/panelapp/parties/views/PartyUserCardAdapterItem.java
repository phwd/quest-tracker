package com.oculus.panelapp.parties.views;

import androidx.annotation.Nullable;
import com.oculus.horizoncontent.social.SocialGroupLaunchAppDestination;
import com.oculus.horizoncontent.social.SocialGroupLaunchResponse;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialUser;
import java.util.Objects;

public class PartyUserCardAdapterItem implements PartyAdapterItem {
    @Nullable
    public SocialParty mParty;
    public SocialUser mUser;

    @Override // com.oculus.panelapp.parties.views.PartyAdapterItem
    public boolean equals(PartyAdapterItem partyAdapterItem) {
        SocialParty socialParty;
        if (this != partyAdapterItem) {
            if (partyAdapterItem == null || getClass() != partyAdapterItem.getClass()) {
                return false;
            }
            PartyUserCardAdapterItem partyUserCardAdapterItem = (PartyUserCardAdapterItem) partyAdapterItem;
            if (!Objects.equals(this.mUser, partyUserCardAdapterItem.mUser) || !Objects.equals(getPartyMembership(), partyUserCardAdapterItem.getPartyMembership()) || (socialParty = this.mParty) == null || !socialParty.equals(partyUserCardAdapterItem.mParty)) {
                return false;
            }
        }
        return true;
    }

    @Nullable
    public SocialGroupLaunchResponse getGroupLaunchResponse() {
        SocialGroupLaunchAppDestination socialGroupLaunchAppDestination;
        SocialParty socialParty = this.mParty;
        if (socialParty == null || (socialGroupLaunchAppDestination = socialParty.mSocialProposedGroupLaunchAppDestination) == null) {
            return null;
        }
        return socialGroupLaunchAppDestination.getUserResponse(getID());
    }

    @Override // com.oculus.panelapp.parties.views.PartyAdapterItem
    public String getID() {
        return this.mUser.mID;
    }

    @Override // com.oculus.panelapp.parties.views.PartyAdapterItem
    public PartyAdapterItemType getItemViewType() {
        return PartyAdapterItemType.USER_CARD;
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

    public boolean isLeader() {
        SocialUser socialUser = this.mParty.mPartyLeader;
        if (socialUser == null || !socialUser.mID.equals(this.mUser.mID)) {
            return false;
        }
        return true;
    }

    public String toString() {
        Object userPartyMembership;
        StringBuilder sb = new StringBuilder("PartyUserCardAdapterItem{mUser=");
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

    public PartyUserCardAdapterItem(SocialUser socialUser, SocialParty socialParty) {
        this.mParty = socialParty;
        this.mUser = socialUser;
    }
}
