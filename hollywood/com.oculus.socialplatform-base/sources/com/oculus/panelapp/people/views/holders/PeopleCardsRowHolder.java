package com.oculus.panelapp.people.views.holders;

import X.AnonymousClass1Ah;
import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.oculus.panelapp.people.PeopleTabletPanelApp;
import com.oculus.panelapp.people.databinding.PeopleTabletPeopleHorizontalUsersViewBinding;
import com.oculus.panelapp.people.views.PeopleActionAdapter;
import com.oculus.panelapp.people.views.PeopleCardsRowAdapterItem;

public class PeopleCardsRowHolder extends AnonymousClass1Ah {
    public PeopleActionAdapter mAdapter;

    public PeopleCardsRowHolder(PeopleTabletPeopleHorizontalUsersViewBinding peopleTabletPeopleHorizontalUsersViewBinding, Context context, PeopleTabletPanelApp peopleTabletPanelApp) {
        super(peopleTabletPeopleHorizontalUsersViewBinding.horizontalRecycler);
        peopleTabletPeopleHorizontalUsersViewBinding.horizontalRecycler.setLayoutManager(new LinearLayoutManager(context, 0, false));
        peopleTabletPeopleHorizontalUsersViewBinding.horizontalRecycler.mHasFixedSize = true;
        PeopleActionAdapter peopleActionAdapter = new PeopleActionAdapter(peopleTabletPanelApp);
        this.mAdapter = peopleActionAdapter;
        peopleTabletPeopleHorizontalUsersViewBinding.horizontalRecycler.setAdapter(peopleActionAdapter);
    }

    public void bindData(PeopleCardsRowAdapterItem peopleCardsRowAdapterItem) {
        this.mAdapter.setData(peopleCardsRowAdapterItem.mItems);
    }
}
