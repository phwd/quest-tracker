package com.oculus.panelapp.parties.views;

import X.AnonymousClass2OU;
import java.util.List;

public class PartyAdapterDiffCallback extends AnonymousClass2OU {
    public List<PartyAdapterItem> mNewItems;
    public List<PartyAdapterItem> mOldItems;

    @Override // X.AnonymousClass2OU
    public boolean areContentsTheSame(int i, int i2) {
        return this.mOldItems.get(i).equals(this.mNewItems.get(i2));
    }

    @Override // X.AnonymousClass2OU
    public boolean areItemsTheSame(int i, int i2) {
        return this.mOldItems.get(i).getID().equals(this.mNewItems.get(i2).getID());
    }

    @Override // X.AnonymousClass2OU
    public int getNewListSize() {
        return this.mNewItems.size();
    }

    @Override // X.AnonymousClass2OU
    public int getOldListSize() {
        return this.mOldItems.size();
    }

    public PartyAdapterDiffCallback(List<PartyAdapterItem> list, List<PartyAdapterItem> list2) {
        this.mNewItems = list;
        this.mOldItems = list2;
    }
}
