package com.oculus.panelapp.people.views;

import X.AnonymousClass006;
import X.AnonymousClass1Ah;
import X.AnonymousClass1Aj;
import X.AnonymousClass1BN;
import X.AnonymousClass2OT;
import X.AnonymousClass2OV;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.VisibleForTesting;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.people.PeopleTabletPanelApp;
import com.oculus.panelapp.people.databinding.PeopleSearchResultHeaderBinding;
import com.oculus.panelapp.people.databinding.PeopleTabletEmptyBinding;
import com.oculus.panelapp.people.databinding.PeopleTabletPeopleHorizontalUsersViewBinding;
import com.oculus.panelapp.people.databinding.PeopleTabletPeopleListItemBinding;
import com.oculus.panelapp.people.views.holders.HeaderViewHolder;
import com.oculus.panelapp.people.views.holders.PeopleCardsRowHolder;
import com.oculus.panelapp.people.views.holders.PeopleEmptyViewHolder;
import com.oculus.panelapp.people.views.holders.UserViewHolder;
import java.util.ArrayList;
import java.util.List;

public class PeopleAdapter extends AnonymousClass1Aj<AnonymousClass1Ah> {
    public static final String TAG = LoggingUtil.tag(PeopleAdapter.class);
    @VisibleForTesting
    public List<PeopleAdapterItem> mItems = new ArrayList();
    public PeopleTabletPanelApp mPanelApp;

    @Override // X.AnonymousClass1Aj
    public int getItemCount() {
        return this.mItems.size();
    }

    @Override // X.AnonymousClass1Aj
    public int getItemViewType(int i) {
        return this.mItems.get(i).getItemViewType().ordinal();
    }

    @Override // X.AnonymousClass1Aj
    public void onBindViewHolder(AnonymousClass1Ah r3, int i) {
        if (r3 instanceof UserViewHolder) {
            ((UserViewHolder) r3).bindUser((PeopleUserAdapterItem) this.mItems.get(i));
        } else if (r3 instanceof PeopleCardsRowHolder) {
            ((PeopleCardsRowHolder) r3).bindData((PeopleCardsRowAdapterItem) this.mItems.get(i));
        } else if (r3 instanceof HeaderViewHolder) {
            ((HeaderViewHolder) r3).bindTitle(((HeaderAdapterItem) this.mItems.get(i)).mTitle);
        } else if (r3 instanceof PeopleEmptyViewHolder) {
            ((PeopleEmptyViewHolder) r3).bindData((PeopleEmptyAdapterItem) this.mItems.get(i));
        } else {
            Log.e(TAG, "Unhandled ViewHolder provided");
        }
    }

    @Override // X.AnonymousClass1Aj
    public AnonymousClass1Ah onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == PeopleAdapterItemType.USER.ordinal()) {
            Context context = viewGroup.getContext();
            return new UserViewHolder(PeopleTabletPeopleListItemBinding.inflate(LayoutInflater.from(context), viewGroup, false), context, this.mPanelApp);
        } else if (i == PeopleAdapterItemType.CARD_ROW.ordinal()) {
            Context context2 = viewGroup.getContext();
            return new PeopleCardsRowHolder(PeopleTabletPeopleHorizontalUsersViewBinding.inflate(LayoutInflater.from(context2), viewGroup, false), context2, this.mPanelApp);
        } else if (i == PeopleAdapterItemType.SEARCH_RESULTS_HEADER.ordinal()) {
            return new HeaderViewHolder(PeopleSearchResultHeaderBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
        } else {
            if (i == PeopleAdapterItemType.EMPTY.ordinal()) {
                return new PeopleEmptyViewHolder(PeopleTabletEmptyBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false), this.mPanelApp);
            }
            Log.e(TAG, AnonymousClass006.A03("Unhandled view type provided: ", i));
            return null;
        }
    }

    public void setData(List<PeopleAdapterItem> list) {
        AnonymousClass2OT A00 = AnonymousClass2OV.A00(new PeopleAdapterDiffCallback(list, this.mItems));
        this.mItems.clear();
        this.mItems.addAll(list);
        A00.A01(new AnonymousClass1BN(this));
    }

    public PeopleAdapter(PeopleTabletPanelApp peopleTabletPanelApp) {
        this.mPanelApp = peopleTabletPanelApp;
    }
}
