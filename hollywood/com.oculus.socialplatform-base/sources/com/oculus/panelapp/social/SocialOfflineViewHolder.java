package com.oculus.panelapp.social;

import X.AnonymousClass1Ah;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialOfflineBinding;

public class SocialOfflineViewHolder extends AnonymousClass1Ah {
    public final AnytimeTabletSocialOfflineBinding mBinding;

    public SocialOfflineViewHolder(AnytimeTabletSocialOfflineBinding anytimeTabletSocialOfflineBinding) {
        super(anytimeTabletSocialOfflineBinding.mRoot);
        this.mBinding = anytimeTabletSocialOfflineBinding;
    }

    public void bindData(SocialOfflineAdapter socialOfflineAdapter) {
        this.mBinding.setTitle(socialOfflineAdapter.mTitle);
        this.mBinding.setSubtitle(socialOfflineAdapter.mSubtitle);
    }
}
