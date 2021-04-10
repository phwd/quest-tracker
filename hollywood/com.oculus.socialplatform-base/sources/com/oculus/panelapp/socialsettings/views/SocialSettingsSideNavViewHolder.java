package com.oculus.panelapp.socialsettings.views;

import X.AnonymousClass1Ah;
import android.view.View;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.socialsettings.SocialSettingsTabletPanelApp;
import com.oculus.panelapp.socialsettings.databinding.SocialSettingsSideNavItemBinding;

public class SocialSettingsSideNavViewHolder extends AnonymousClass1Ah {
    public SocialSettingsSideNavItemBinding mBinding;
    public OCButton mNavButton;

    public SocialSettingsSideNavViewHolder(SocialSettingsTabletPanelApp socialSettingsTabletPanelApp, SocialSettingsSideNavItemBinding socialSettingsSideNavItemBinding) {
        super(socialSettingsSideNavItemBinding.mRoot);
        OCButton oCButton = socialSettingsSideNavItemBinding.navButton;
        this.mNavButton = oCButton;
        this.mBinding = socialSettingsSideNavItemBinding;
        oCButton.mEventHandler = socialSettingsTabletPanelApp;
    }

    public void bind(SocialSettingsSideNavItem socialSettingsSideNavItem, boolean z) {
        this.mNavButton.setId(socialSettingsSideNavItem.getViewID());
        this.mNavButton.setSelected(z);
        this.mNavButton.setText(socialSettingsSideNavItem.getTitle(this.mBinding.mRoot.getContext().getResources()));
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mNavButton.setOnClickListener(onClickListener);
    }
}
