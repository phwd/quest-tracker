package com.oculus.panelapp.social;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialHorizontalUsersViewBinding;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialListHeaderV2Binding;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialListItemV2Binding;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialListNullRowV2Binding;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialOfflineBinding;
import java.util.ArrayList;
import java.util.List;

public class SocialAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = LoggingUtil.tag(SocialAdapter.class);
    private List<SocialAdapterItem> mItems = new ArrayList();
    private SocialPanelApp mPanelApp;

    public SocialAdapter(SocialPanelApp socialPanelApp) {
        this.mPanelApp = socialPanelApp;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.mItems.get(i).getItemViewType().ordinal();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == SocialAdapterItemType.USER.ordinal()) {
            return new UserViewHolder(AnytimeTabletSocialListItemV2Binding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false), viewGroup.getContext(), this.mPanelApp);
        }
        if (i == SocialAdapterItemType.CARD_ROW.ordinal()) {
            return new SocialCardsRowHolder(AnytimeTabletSocialHorizontalUsersViewBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false), viewGroup.getContext(), this.mPanelApp);
        }
        if (i == SocialAdapterItemType.HEADER.ordinal()) {
            return new HeaderViewHolder(AnytimeTabletSocialListHeaderV2Binding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false), viewGroup.getContext(), this.mPanelApp);
        }
        if (i == SocialAdapterItemType.NULL.ordinal()) {
            return new SocialNullRowViewHolder(AnytimeTabletSocialListNullRowV2Binding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false), this.mPanelApp);
        }
        if (i == SocialAdapterItemType.OFFLINE.ordinal()) {
            return new SocialOfflineViewHolder(AnytimeTabletSocialOfflineBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
        }
        String str = TAG;
        Log.e(str, "Unhandled view type provided: " + i);
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof SocialCardsRowHolder) {
            ((SocialCardsRowHolder) viewHolder).bindData((SocialCardsRowAdapterItem) this.mItems.get(i));
        } else if (viewHolder instanceof UserViewHolder) {
            ((UserViewHolder) viewHolder).bindUser((SocialUserAdapterItem) this.mItems.get(i));
        } else if (viewHolder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) viewHolder).bindHeader((SocialAdapterHeader) this.mItems.get(i));
        } else if (viewHolder instanceof SocialNullRowViewHolder) {
            ((SocialNullRowViewHolder) viewHolder).bindRow((SocialAdapterNullRow) this.mItems.get(i));
        } else if (viewHolder instanceof SocialOfflineViewHolder) {
            ((SocialOfflineViewHolder) viewHolder).bindData((SocialOfflineAdapter) this.mItems.get(i));
        } else {
            Log.e(TAG, "Unhandled ViewHolder provided");
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof UserViewHolder) {
            ((UserViewHolder) viewHolder).hideOverflowMenu();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mItems.size();
    }

    public void setData(List<SocialAdapterItem> list) {
        DiffUtil.DiffResult calculateDiff = DiffUtil.calculateDiff(new SocialAdapterDiffCallback(list, this.mItems));
        this.mItems.clear();
        this.mItems.addAll(list);
        calculateDiff.dispatchUpdatesTo(this);
    }
}
