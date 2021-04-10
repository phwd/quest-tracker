package com.oculus.panelapp.people.views;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.cli.HelpFormatter;

public class PeopleCardsRowAdapterItem implements PeopleAdapterItem {
    public List<PeopleAdapterItem> mItems;

    @Override // com.oculus.panelapp.people.views.PeopleAdapterItem
    public boolean equals(PeopleAdapterItem peopleAdapterItem) {
        if (this != peopleAdapterItem) {
            if (peopleAdapterItem != null && getClass() == peopleAdapterItem.getClass()) {
                PeopleCardsRowAdapterItem peopleCardsRowAdapterItem = (PeopleCardsRowAdapterItem) peopleAdapterItem;
                if (this.mItems.size() == peopleCardsRowAdapterItem.mItems.size()) {
                    for (int i = 0; i < this.mItems.size(); i++) {
                        if (this.mItems.get(i).equals(peopleCardsRowAdapterItem.mItems.get(i))) {
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    @Override // com.oculus.panelapp.people.views.PeopleAdapterItem
    public String getID() {
        return PeopleAdapterItemType.CARD_ROW.name();
    }

    @Override // com.oculus.panelapp.people.views.PeopleAdapterItem
    public PeopleAdapterItemType getItemViewType() {
        return PeopleAdapterItemType.CARD_ROW;
    }

    public List<PeopleAdapterItem> getItems() {
        return this.mItems;
    }

    public String toString() {
        return (String) this.mItems.stream().map($$Lambda$PeopleCardsRowAdapterItem$VezA5_dFBlM7YoLWtJrekD9182.INSTANCE).collect(Collectors.joining(HelpFormatter.DEFAULT_OPT_PREFIX, "{", "}"));
    }

    public PeopleCardsRowAdapterItem(List<PeopleAdapterItem> list) {
        this.mItems = list;
    }
}
