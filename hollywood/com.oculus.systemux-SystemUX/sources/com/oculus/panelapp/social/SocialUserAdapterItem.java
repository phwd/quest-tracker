package com.oculus.panelapp.social;

import com.facebook.debug.log.LoggingUtil;
import com.oculus.horizoncontent.social.SocialGroupLaunchResponse;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialUser;
import java.util.Objects;
import javax.annotation.Nullable;

public class SocialUserAdapterItem implements SocialAdapterItem {
    @Nullable
    private SocialParty mParty;
    private SocialUser mUser;

    public SocialUserAdapterItem(SocialUser socialUser, SocialParty socialParty) {
        this.mParty = socialParty;
        this.mUser = socialUser;
    }

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public SocialAdapterItemType getItemViewType() {
        return SocialAdapterItemType.USER;
    }

    public String toString() {
        Object obj;
        StringBuilder sb = new StringBuilder();
        sb.append("SocialUserAdapterItem{mUser=");
        sb.append(this.mUser);
        sb.append(", mPartyMembership=");
        SocialParty socialParty = this.mParty;
        if (socialParty == null) {
            obj = LoggingUtil.NO_HASHCODE;
        } else {
            obj = socialParty.getUserPartyMembership(this.mUser);
        }
        sb.append(obj);
        sb.append('}');
        return sb.toString();
    }

    @Nullable
    public SocialParty.PartyMembership getPartyMembership() {
        SocialParty socialParty = this.mParty;
        if (socialParty != null) {
            return socialParty.getUserPartyMembership(getUser());
        }
        return null;
    }

    @Nullable
    public SocialParty getParty() {
        return this.mParty;
    }

    public boolean getIsUserInvitedByLocalUserToParty(String str) {
        SocialParty socialParty = this.mParty;
        if (socialParty != null) {
            return socialParty.getIsUserInvitedByLocalUserToParty(str, this.mUser);
        }
        return false;
    }

    public SocialGroupLaunchResponse getGroupLaunchResponse() {
        SocialParty socialParty = this.mParty;
        if (socialParty == null || socialParty.getProposedGroupLaunchAppDestination() == null) {
            return null;
        }
        return this.mParty.getProposedGroupLaunchAppDestination().getUserResponse(this.mUser.getID());
    }

    public SocialUser getUser() {
        return this.mUser;
    }

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public String getID() {
        return this.mUser.getID();
    }

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public boolean equals(SocialAdapterItem socialAdapterItem) {
        if (this == socialAdapterItem) {
            return true;
        }
        if (socialAdapterItem == null || getClass() != socialAdapterItem.getClass()) {
            return false;
        }
        SocialUserAdapterItem socialUserAdapterItem = (SocialUserAdapterItem) socialAdapterItem;
        return Objects.equals(this.mUser, socialUserAdapterItem.mUser) && Objects.equals(getPartyMembership(), socialUserAdapterItem.getPartyMembership()) && Objects.equals(getGroupLaunchResponse(), socialUserAdapterItem.getGroupLaunchResponse()) && Objects.equals(this.mParty, socialUserAdapterItem.mParty);
    }

    public int hashCode() {
        return this.mUser.hashCode();
    }
}
