package com.oculus.panelapp.people.views;

import X.AnonymousClass2OU;
import androidx.annotation.Nullable;
import java.util.List;

public class PeopleAdapterDiffCallback extends AnonymousClass2OU {
    public List<PeopleAdapterItem> mNewItems;
    public List<PeopleAdapterItem> mOldItems;

    @Override // X.AnonymousClass2OU
    public boolean areContentsTheSame(int i, int i2) {
        return this.mOldItems.get(i).equals(this.mNewItems.get(i2));
    }

    @Override // X.AnonymousClass2OU
    public boolean areItemsTheSame(int i, int i2) {
        return this.mOldItems.get(i).getID().equals(this.mNewItems.get(i2).getID());
    }

    @Override // X.AnonymousClass2OU
    @Nullable
    public Object getChangePayload(int i, int i2) {
        return this.mNewItems.get(i2);
    }

    @Override // X.AnonymousClass2OU
    public int getNewListSize() {
        return this.mNewItems.size();
    }

    @Override // X.AnonymousClass2OU
    public int getOldListSize() {
        return this.mOldItems.size();
    }

    public PeopleAdapterDiffCallback(List<PeopleAdapterItem> list, List<PeopleAdapterItem> list2) {
        this.mNewItems = list;
        this.mOldItems = list2;
    }
}
