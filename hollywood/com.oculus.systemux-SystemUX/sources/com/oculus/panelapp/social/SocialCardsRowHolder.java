package com.oculus.panelapp.social;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialHorizontalUsersViewBinding;

public class SocialCardsRowHolder extends RecyclerView.ViewHolder {
    private SocialActionAdapter mAdapter;

    public SocialCardsRowHolder(AnytimeTabletSocialHorizontalUsersViewBinding anytimeTabletSocialHorizontalUsersViewBinding, Context context, SocialPanelApp socialPanelApp) {
        super(anytimeTabletSocialHorizontalUsersViewBinding.socialHorizontalCards);
        anytimeTabletSocialHorizontalUsersViewBinding.horizontalRecycler.setLayoutManager(new LinearLayoutManager(context, 0, false));
        anytimeTabletSocialHorizontalUsersViewBinding.horizontalRecycler.setHasFixedSize(true);
        this.mAdapter = new SocialActionAdapter(socialPanelApp);
        anytimeTabletSocialHorizontalUsersViewBinding.horizontalRecycler.setAdapter(this.mAdapter);
    }

    public void bindData(SocialCardsRowAdapterItem socialCardsRowAdapterItem) {
        this.mAdapter.setData(socialCardsRowAdapterItem.getItems());
    }
}
