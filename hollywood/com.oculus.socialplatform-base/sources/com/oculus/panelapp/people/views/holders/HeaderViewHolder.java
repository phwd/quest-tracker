package com.oculus.panelapp.people.views.holders;

import X.AnonymousClass1Ah;
import com.oculus.panelapp.people.databinding.PeopleSearchResultHeaderBinding;

public class HeaderViewHolder extends AnonymousClass1Ah {
    public PeopleSearchResultHeaderBinding mBinding;

    public HeaderViewHolder(PeopleSearchResultHeaderBinding peopleSearchResultHeaderBinding) {
        super(peopleSearchResultHeaderBinding.peopleTabletHeader);
        this.mBinding = peopleSearchResultHeaderBinding;
    }

    public void bindTitle(String str) {
        this.mBinding.setTitle(str);
    }
}
