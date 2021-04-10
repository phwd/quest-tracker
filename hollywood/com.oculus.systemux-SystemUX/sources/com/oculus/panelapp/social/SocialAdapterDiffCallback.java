package com.oculus.panelapp.social;

import androidx.recyclerview.widget.DiffUtil;
import java.util.List;

public class SocialAdapterDiffCallback extends DiffUtil.Callback {
    private List<SocialAdapterItem> mNewItems;
    private List<SocialAdapterItem> mOldItems;

    public SocialAdapterDiffCallback(List<SocialAdapterItem> list, List<SocialAdapterItem> list2) {
        this.mNewItems = list;
        this.mOldItems = list2;
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public int getOldListSize() {
        return this.mOldItems.size();
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public int getNewListSize() {
        return this.mNewItems.size();
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public boolean areItemsTheSame(int i, int i2) {
        return this.mOldItems.get(i).getID().equals(this.mNewItems.get(i2).getID());
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public boolean areContentsTheSame(int i, int i2) {
        return this.mOldItems.get(i).equals(this.mNewItems.get(i2));
    }
}
