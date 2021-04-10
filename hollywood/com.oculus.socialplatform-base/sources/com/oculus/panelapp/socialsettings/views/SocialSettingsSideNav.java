package com.oculus.panelapp.socialsettings.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import com.oculus.panelapp.socialsettings.SocialSettingsTabletPanelApp;
import com.oculus.panelapp.socialsettings.databinding.SocialSettingsSideNavBinding;

public class SocialSettingsSideNav extends FrameLayout {
    public SocialSettingsSideNavAdapter mAdapter;
    public SocialSettingsSideNavBinding mBinding;

    public void destroy() {
        this.mBinding.recyclerView.setAdapter(null);
    }

    public void initialize(SocialSettingsTabletPanelApp socialSettingsTabletPanelApp) {
        this.mAdapter.initialize(socialSettingsTabletPanelApp);
    }

    public SocialSettingsSideNav(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mBinding = SocialSettingsSideNavBinding.inflate(LayoutInflater.from(context), this, true);
        SocialSettingsSideNavAdapter socialSettingsSideNavAdapter = new SocialSettingsSideNavAdapter(context);
        this.mAdapter = socialSettingsSideNavAdapter;
        this.mBinding.recyclerView.setAdapter(socialSettingsSideNavAdapter);
    }
}
