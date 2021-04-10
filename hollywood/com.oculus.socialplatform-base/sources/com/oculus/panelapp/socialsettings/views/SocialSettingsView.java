package com.oculus.panelapp.socialsettings.views;

import X.AnonymousClass1uW;
import android.content.Context;
import android.util.AttributeSet;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.socialtablet.navbar.ProfileType;
import com.oculus.common.socialtablet.navbar.SocialTabletType;
import com.oculus.panelapp.socialsettings.SocialSettingsTabletPanelApp;
import com.oculus.panelapp.socialsettings.databinding.SocialSettingsTabletMainBinding;
import com.oculus.tablet.view.BaseView;
import com.oculus.vrshell.panels.AndroidPanelApp;

public class SocialSettingsView extends BaseView {
    public static final String TAG = LoggingUtil.tag(SocialSettingsView.class);
    public SocialSettingsTabletMainBinding mBinding;

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
    }

    @Override // com.oculus.tablet.view.BaseView
    public void destroy() {
        this.mBinding.socialTabletSideNav.destroy();
        this.mBinding.socialSettingsSideNav.destroy();
        SocialSettingsTabletMainBinding socialSettingsTabletMainBinding = this.mBinding;
        socialSettingsTabletMainBinding.socialSettingsActiveStatus.destroy();
        socialSettingsTabletMainBinding.socialSettingsNotifications.destroy();
        socialSettingsTabletMainBinding.socialSettingsMessengerAccount.destroy();
    }

    public void onGraphQLReady() {
        this.mBinding.socialSettingsActiveStatus.onGraphQLReady();
    }

    public void initialize(SocialSettingsTabletPanelApp socialSettingsTabletPanelApp, SocialSettingsTabletMainBinding socialSettingsTabletMainBinding) {
        super.initialize((AndroidPanelApp) socialSettingsTabletPanelApp, (AnonymousClass1uW) socialSettingsTabletMainBinding);
        socialSettingsTabletMainBinding.socialTabletSideNav.initialize(socialSettingsTabletPanelApp, SocialTabletType.SETTINGS, ProfileType.FACEBOOK);
        this.mBinding = socialSettingsTabletMainBinding;
        socialSettingsTabletMainBinding.socialSettingsSideNav.initialize(socialSettingsTabletPanelApp);
        socialSettingsTabletMainBinding.socialSettingsActiveStatus.initialize(socialSettingsTabletPanelApp);
        socialSettingsTabletMainBinding.socialSettingsNotifications.initialize(socialSettingsTabletPanelApp);
        socialSettingsTabletMainBinding.socialSettingsMessengerAccount.initialize(socialSettingsTabletPanelApp);
    }

    public SocialSettingsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
