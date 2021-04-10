package com.oculus.panelapp.people.views;

import com.oculus.horizoncontent.social.SocialUser;
import java.util.Objects;

public class PeopleUserAdapterItem implements PeopleAdapterItem {
    public SocialUser mUser;

    @Override // com.oculus.panelapp.people.views.PeopleAdapterItem
    public boolean equals(PeopleAdapterItem peopleAdapterItem) {
        if (this == peopleAdapterItem) {
            return true;
        }
        if (peopleAdapterItem == null || getClass() != peopleAdapterItem.getClass()) {
            return false;
        }
        return Objects.equals(this.mUser, ((PeopleUserAdapterItem) peopleAdapterItem).mUser);
    }

    @Override // com.oculus.panelapp.people.views.PeopleAdapterItem
    public String getID() {
        return this.mUser.mID;
    }

    @Override // com.oculus.panelapp.people.views.PeopleAdapterItem
    public PeopleAdapterItemType getItemViewType() {
        return PeopleAdapterItemType.USER;
    }

    public SocialUser getUser() {
        return this.mUser;
    }

    public int hashCode() {
        return this.mUser.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("PeopleUserAdapterItem{mUser=");
        sb.append(this.mUser);
        return sb.toString();
    }

    public PeopleUserAdapterItem(SocialUser socialUser) {
        this.mUser = socialUser;
    }
}
