package com.oculus.panelapp.social;

import androidx.recyclerview.widget.RecyclerView;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialOfflineBinding;

public class SocialOfflineViewHolder extends RecyclerView.ViewHolder {
    private final AnytimeTabletSocialOfflineBinding mBinding;

    public SocialOfflineViewHolder(AnytimeTabletSocialOfflineBinding anytimeTabletSocialOfflineBinding) {
        super(anytimeTabletSocialOfflineBinding.getRoot());
        this.mBinding = anytimeTabletSocialOfflineBinding;
    }

    public void bindData(SocialOfflineAdapter socialOfflineAdapter) {
        this.mBinding.setTitle(socialOfflineAdapter.getTitle());
        this.mBinding.setSubtitle(socialOfflineAdapter.getSubtitle());
    }
}
