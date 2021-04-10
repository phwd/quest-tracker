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
import com.oculus.panelapp.people.databinding.PeopleTabletEmptyBinding;
import com.oculus.panelapp.people.databinding.PeopleTabletPeopleUserCardBinding;
import com.oculus.panelapp.people.databinding.PeopleTabletUpsellCardBinding;
import com.oculus.panelapp.people.views.holders.PeopleEmptyViewHolder;
import com.oculus.panelapp.people.views.holders.PeopleUpsellViewHolder;
import com.oculus.panelapp.people.views.holders.UserCardViewHolder;
import java.util.ArrayList;
import java.util.List;

public class PeopleActionAdapter extends AnonymousClass1Aj<AnonymousClass1Ah> {
    public static final String TAG = LoggingUtil.tag(PeopleActionAdapter.class);
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
        if (r3 instanceof UserCardViewHolder) {
            ((UserCardViewHolder) r3).bindUser((PeopleUserAdapterItem) this.mItems.get(i));
            return;
        }
        if (r3 instanceof PeopleUpsellViewHolder) {
            ((PeopleUpsellViewHolder) r3).bindData((PeopleUpsellAdapterItem) this.mItems.get(i));
        } else if (r3 instanceof PeopleEmptyViewHolder) {
            ((PeopleEmptyViewHolder) r3).bindData((PeopleEmptyAdapterItem) this.mItems.get(i));
        }
        Log.e(TAG, "Unhandled ViewHolder provided");
    }

    @Override // X.AnonymousClass1Aj
    public AnonymousClass1Ah onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == PeopleAdapterItemType.USER.ordinal()) {
            Context context = viewGroup.getContext();
            return new UserCardViewHolder(PeopleTabletPeopleUserCardBinding.inflate(LayoutInflater.from(context), viewGroup, false), context, this.mPanelApp);
        } else if (i == PeopleAdapterItemType.UPSELL_CARD.ordinal()) {
            return new PeopleUpsellViewHolder(PeopleTabletUpsellCardBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false), this.mPanelApp);
        } else {
            if (i == PeopleAdapterItemType.EMPTY.ordinal()) {
                return new PeopleEmptyViewHolder(PeopleTabletEmptyBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false), this.mPanelApp);
            }
            Log.e(TAG, AnonymousClass006.A03("Unhandled view type provided: ", i));
            return null;
        }
    }

    @Override // X.AnonymousClass1Aj
    public void onViewAttachedToWindow(AnonymousClass1Ah r2) {
        if (r2 instanceof UserCardViewHolder) {
            ((UserCardViewHolder) r2).onViewAttachedToWindow();
        }
        super.onViewAttachedToWindow(r2);
    }

    @Override // X.AnonymousClass1Aj
    public void onViewDetachedFromWindow(AnonymousClass1Ah r2) {
        if (r2 instanceof UserCardViewHolder) {
            ((UserCardViewHolder) r2).onViewDetachedFromWindow();
        }
        super.onViewDetachedFromWindow(r2);
    }

    public void setData(List<PeopleAdapterItem> list) {
        AnonymousClass2OT A00 = AnonymousClass2OV.A00(new PeopleAdapterDiffCallback(list, this.mItems));
        this.mItems.clear();
        this.mItems.addAll(list);
        A00.A01(new AnonymousClass1BN(this));
    }

    public PeopleActionAdapter(PeopleTabletPanelApp peopleTabletPanelApp) {
        this.mPanelApp = peopleTabletPanelApp;
    }
}
