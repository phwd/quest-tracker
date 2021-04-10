package com.oculus.panelapp.parties.views;

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
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.parties.PartiesTabletPanelApp;
import com.oculus.panelapp.parties.databinding.InviteToPartyCardBinding;
import com.oculus.panelapp.parties.databinding.PartyUserCardBinding;
import java.util.ArrayList;
import java.util.List;

public class PartyAdapter extends AnonymousClass1Aj<AnonymousClass1Ah> {
    public static final String TAG = LoggingUtil.tag(PartyAdapter.class);
    public List<PartyAdapterItem> mItems = new ArrayList();
    public PartiesTabletPanelApp mPanelApp;

    public void destroy() {
        this.mPanelApp = null;
        setData(new ArrayList());
    }

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
        if (r3 instanceof PartyUserCardViewHolder) {
            ((PartyUserCardViewHolder) r3).bindUser((PartyUserCardAdapterItem) this.mItems.get(i));
        } else if (r3 instanceof InviteToPartyCardViewHolder) {
            ((InviteToPartyCardViewHolder) r3).bindData((InviteToPartyCardAdapterItem) this.mItems.get(i));
        } else {
            Log.e(TAG, "Unhandled ViewHolder provided");
        }
    }

    @Override // X.AnonymousClass1Aj
    public AnonymousClass1Ah onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == PartyAdapterItemType.USER_CARD.ordinal()) {
            Context context = viewGroup.getContext();
            return new PartyUserCardViewHolder(PartyUserCardBinding.inflate(LayoutInflater.from(context), viewGroup, false), context, this.mPanelApp);
        } else if (i == PartyAdapterItemType.INVITE_TO_PARTY_CARD.ordinal()) {
            Context context2 = viewGroup.getContext();
            return new InviteToPartyCardViewHolder(InviteToPartyCardBinding.inflate(LayoutInflater.from(context2), viewGroup, false), context2, this.mPanelApp);
        } else {
            Log.e(TAG, AnonymousClass006.A03("Unhandled view type provided: ", i));
            return null;
        }
    }

    @Override // X.AnonymousClass1Aj
    public void onViewAttachedToWindow(AnonymousClass1Ah r2) {
        if (r2 instanceof PartyUserCardViewHolder) {
            ((PartyUserCardViewHolder) r2).subscribeToSpeakingUpdates();
        }
        super.onViewAttachedToWindow(r2);
    }

    @Override // X.AnonymousClass1Aj
    public void onViewDetachedFromWindow(AnonymousClass1Ah r2) {
        if (r2 instanceof PartyUserCardViewHolder) {
            PartyUserCardViewHolder partyUserCardViewHolder = (PartyUserCardViewHolder) r2;
            partyUserCardViewHolder.unsubscribeFromSpeakingUpdates();
            partyUserCardViewHolder.unregisterPartyObserver();
        }
        super.onViewDetachedFromWindow(r2);
    }

    public void setData(List<PartyAdapterItem> list) {
        AnonymousClass2OT A00 = AnonymousClass2OV.A00(new PartyAdapterDiffCallback(list, this.mItems));
        this.mItems.clear();
        this.mItems.addAll(list);
        A00.A01(new AnonymousClass1BN(this));
    }

    public PartyAdapter(PartiesTabletPanelApp partiesTabletPanelApp) {
        this.mPanelApp = partiesTabletPanelApp;
    }
}
