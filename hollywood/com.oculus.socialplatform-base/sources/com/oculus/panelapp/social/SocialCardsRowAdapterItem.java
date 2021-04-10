package com.oculus.panelapp.social;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.cli.HelpFormatter;

public class SocialCardsRowAdapterItem implements SocialAdapterItem {
    public List<SocialAdapterItem> mItems;

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public boolean equals(SocialAdapterItem socialAdapterItem) {
        if (this != socialAdapterItem) {
            if (socialAdapterItem != null && getClass() == socialAdapterItem.getClass()) {
                SocialCardsRowAdapterItem socialCardsRowAdapterItem = (SocialCardsRowAdapterItem) socialAdapterItem;
                if (this.mItems.size() == socialCardsRowAdapterItem.mItems.size()) {
                    for (int i = 0; i < this.mItems.size(); i++) {
                        if (this.mItems.get(i).equals(socialCardsRowAdapterItem.mItems.get(i))) {
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public String getID() {
        return SocialAdapterItemType.CARD_ROW.name();
    }

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public SocialAdapterItemType getItemViewType() {
        return SocialAdapterItemType.CARD_ROW;
    }

    public List<SocialAdapterItem> getItems() {
        return this.mItems;
    }

    public String toString() {
        return (String) this.mItems.stream().map($$Lambda$SocialCardsRowAdapterItem$_uaLAmcQMStCvJ3D3nKddFV7wgs2.INSTANCE).collect(Collectors.joining(HelpFormatter.DEFAULT_OPT_PREFIX, "{", "}"));
    }

    public SocialCardsRowAdapterItem(List<SocialAdapterItem> list) {
        this.mItems = list;
    }
}
