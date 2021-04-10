package com.oculus.panelapp.social;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialGuidedActionCardBinding;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialUserCardBinding;
import java.util.ArrayList;
import java.util.List;

public class SocialActionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = LoggingUtil.tag(SocialActionAdapter.class);
    private List<SocialAdapterItem> mItems = new ArrayList();
    private SocialPanelApp mPanelApp;

    public SocialActionAdapter(SocialPanelApp socialPanelApp) {
        this.mPanelApp = socialPanelApp;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.mItems.get(i).getItemViewType().ordinal();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == SocialAdapterItemType.USER.ordinal()) {
            return new UserCardViewHolder(AnytimeTabletSocialUserCardBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false), viewGroup.getContext(), this.mPanelApp);
        }
        if (i == SocialAdapterItemType.GUIDED_ACTION.ordinal()) {
            return new SocialGuidedActionViewHolder(AnytimeTabletSocialGuidedActionCardBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false), viewGroup.getContext(), this.mPanelApp);
        }
        String str = TAG;
        Log.e(str, "Unhandled view type provided: " + i);
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof UserCardViewHolder) {
            ((UserCardViewHolder) viewHolder).bindUser((SocialUserAdapterItem) this.mItems.get(i));
        } else if (viewHolder instanceof SocialGuidedActionViewHolder) {
            ((SocialGuidedActionViewHolder) viewHolder).bindData((SocialGuidedActionAdapterItem) this.mItems.get(i));
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
