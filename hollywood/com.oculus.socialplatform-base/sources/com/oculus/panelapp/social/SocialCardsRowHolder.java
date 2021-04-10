package com.oculus.panelapp.social;

import X.AnonymousClass1Ah;
import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialHorizontalUsersViewBinding;

public class SocialCardsRowHolder extends AnonymousClass1Ah {
    public SocialActionAdapter mAdapter;

    public SocialCardsRowHolder(AnytimeTabletSocialHorizontalUsersViewBinding anytimeTabletSocialHorizontalUsersViewBinding, Context context, SocialPanelApp socialPanelApp) {
        super(anytimeTabletSocialHorizontalUsersViewBinding.socialHorizontalCards);
        anytimeTabletSocialHorizontalUsersViewBinding.horizontalRecycler.setLayoutManager(new LinearLayoutManager(context, 0, false));
        anytimeTabletSocialHorizontalUsersViewBinding.horizontalRecycler.mHasFixedSize = true;
        SocialActionAdapter socialActionAdapter = new SocialActionAdapter(socialPanelApp);
        this.mAdapter = socialActionAdapter;
        anytimeTabletSocialHorizontalUsersViewBinding.horizontalRecycler.setAdapter(socialActionAdapter);
    }

    public void bindData(SocialCardsRowAdapterItem socialCardsRowAdapterItem) {
        this.mAdapter.setData(socialCardsRowAdapterItem.mItems);
    }
}
