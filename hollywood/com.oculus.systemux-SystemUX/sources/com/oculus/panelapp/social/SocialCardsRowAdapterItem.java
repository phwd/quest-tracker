package com.oculus.panelapp.social;

import java.util.List;
import java.util.stream.Collectors;

public class SocialCardsRowAdapterItem implements SocialAdapterItem {
    private List<SocialAdapterItem> mItems;

    public SocialCardsRowAdapterItem(List<SocialAdapterItem> list) {
        this.mItems = list;
    }

    public List<SocialAdapterItem> getItems() {
        return this.mItems;
    }

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public SocialAdapterItemType getItemViewType() {
        return SocialAdapterItemType.CARD_ROW;
    }

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public String getID() {
        return SocialAdapterItemType.CARD_ROW.name();
    }

    public String toString() {
        return (String) this.mItems.stream().map($$Lambda$SocialCardsRowAdapterItem$pQEwBaDpyaDX13bPaQALXeJTZ5I.INSTANCE).collect(Collectors.joining("-", "{", "}"));
    }

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public boolean equals(SocialAdapterItem socialAdapterItem) {
        if (this == socialAdapterItem) {
            return true;
        }
        if (socialAdapterItem == null || getClass() != socialAdapterItem.getClass()) {
            return false;
        }
        SocialCardsRowAdapterItem socialCardsRowAdapterItem = (SocialCardsRowAdapterItem) socialAdapterItem;
        if (this.mItems.size() != socialCardsRowAdapterItem.getItems().size()) {
            return false;
        }
        for (int i = 0; i < this.mItems.size(); i++) {
            if (!this.mItems.get(i).equals(socialCardsRowAdapterItem.mItems.get(i))) {
                return false;
            }
        }
        return true;
    }
}
