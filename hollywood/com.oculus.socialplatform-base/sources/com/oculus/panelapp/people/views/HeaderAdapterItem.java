package com.oculus.panelapp.people.views;

import X.AnonymousClass006;
import java.util.Objects;

public class HeaderAdapterItem implements PeopleAdapterItem {
    public String mTitle;

    @Override // com.oculus.panelapp.people.views.PeopleAdapterItem
    public boolean equals(PeopleAdapterItem peopleAdapterItem) {
        if (this == peopleAdapterItem) {
            return true;
        }
        if (peopleAdapterItem == null || getClass() != peopleAdapterItem.getClass()) {
            return false;
        }
        return Objects.equals(this.mTitle, ((HeaderAdapterItem) peopleAdapterItem).mTitle);
    }

    @Override // com.oculus.panelapp.people.views.PeopleAdapterItem
    public String getID() {
        return this.mTitle;
    }

    @Override // com.oculus.panelapp.people.views.PeopleAdapterItem
    public PeopleAdapterItemType getItemViewType() {
        return PeopleAdapterItemType.SEARCH_RESULTS_HEADER;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public int hashCode() {
        return this.mTitle.hashCode();
    }

    public String toString() {
        return AnonymousClass006.A07("HeaderAdapterItem{mTitle=", this.mTitle);
    }

    public HeaderAdapterItem(String str) {
        this.mTitle = str;
    }
}
