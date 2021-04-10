package com.oculus.panelapp.social;

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
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialHorizontalUsersViewBinding;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialListHeaderV2Binding;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialListItemV2Binding;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialListNullRowV2Binding;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialOfflineBinding;
import java.util.ArrayList;
import java.util.List;

public class SocialAdapter extends AnonymousClass1Aj<AnonymousClass1Ah> {
    public static final String TAG = LoggingUtil.tag(SocialAdapter.class);
    public List<SocialAdapterItem> mItems = new ArrayList();
    public SocialPanelApp mPanelApp;

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
        if (r3 instanceof SocialCardsRowHolder) {
            ((SocialCardsRowHolder) r3).bindData((SocialCardsRowAdapterItem) this.mItems.get(i));
        } else if (r3 instanceof UserViewHolder) {
            ((UserViewHolder) r3).bindUser((SocialUserAdapterItem) this.mItems.get(i));
        } else if (r3 instanceof HeaderViewHolder) {
            ((HeaderViewHolder) r3).bindHeader((SocialAdapterHeader) this.mItems.get(i));
        } else if (r3 instanceof SocialNullRowViewHolder) {
            ((SocialNullRowViewHolder) r3).bindRow((SocialAdapterNullRow) this.mItems.get(i));
        } else if (r3 instanceof SocialOfflineViewHolder) {
            ((SocialOfflineViewHolder) r3).bindData((SocialOfflineAdapter) this.mItems.get(i));
        } else {
            Log.e(TAG, "Unhandled ViewHolder provided");
        }
    }

    @Override // X.AnonymousClass1Aj
    public AnonymousClass1Ah onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == SocialAdapterItemType.USER.ordinal()) {
            Context context = viewGroup.getContext();
            return new UserViewHolder(AnytimeTabletSocialListItemV2Binding.inflate(LayoutInflater.from(context), viewGroup, false), context, this.mPanelApp);
        } else if (i == SocialAdapterItemType.CARD_ROW.ordinal()) {
            Context context2 = viewGroup.getContext();
            return new SocialCardsRowHolder(AnytimeTabletSocialHorizontalUsersViewBinding.inflate(LayoutInflater.from(context2), viewGroup, false), context2, this.mPanelApp);
        } else if (i == SocialAdapterItemType.HEADER.ordinal()) {
            Context context3 = viewGroup.getContext();
            return new HeaderViewHolder(AnytimeTabletSocialListHeaderV2Binding.inflate(LayoutInflater.from(context3), viewGroup, false), context3, this.mPanelApp);
        } else if (i == SocialAdapterItemType.NULL.ordinal()) {
            return new SocialNullRowViewHolder(AnytimeTabletSocialListNullRowV2Binding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false), this.mPanelApp);
        } else {
            if (i == SocialAdapterItemType.OFFLINE.ordinal()) {
                return new SocialOfflineViewHolder(AnytimeTabletSocialOfflineBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
            }
            Log.e(TAG, AnonymousClass006.A03("Unhandled view type provided: ", i));
            return null;
        }
    }

    @Override // X.AnonymousClass1Aj
    public void onViewDetachedFromWindow(AnonymousClass1Ah r2) {
        if (r2 instanceof UserViewHolder) {
            ((UserViewHolder) r2).hideOverflowMenu();
        }
    }

    public void setData(List<SocialAdapterItem> list) {
        AnonymousClass2OT A00 = AnonymousClass2OV.A00(new SocialAdapterDiffCallback(list, this.mItems));
        this.mItems.clear();
        this.mItems.addAll(list);
        A00.A01(new AnonymousClass1BN(this));
    }

    public SocialAdapter(SocialPanelApp socialPanelApp) {
        this.mPanelApp = socialPanelApp;
    }
}
