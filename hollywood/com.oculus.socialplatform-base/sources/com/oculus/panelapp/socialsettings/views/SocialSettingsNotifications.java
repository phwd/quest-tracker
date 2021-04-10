package com.oculus.panelapp.socialsettings.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.socialsettings.SocialSettingsTabletPanelApp;
import com.oculus.panelapp.socialsettings.databinding.SocialSettingsNotificationsBinding;
import com.oculus.tablet.view.BaseView;
import com.oculus.vrshell.SystemUXRoute;

public class SocialSettingsNotifications extends BaseView {
    public static final String TAG = LoggingUtil.tag(SocialSettingsNotifications.class);
    public final String MESSAGE_NOTIFICATIONS_SETTINGS_URI = "/notifications?category=MESSAGES";
    public SocialSettingsNotificationsBinding mBinding;

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
    }

    @Override // com.oculus.tablet.view.BaseView
    public void destroy() {
        OCButton oCButton = this.mBinding.notificationsButton;
        oCButton.mEventHandler = null;
        oCButton.setOnClickListener(null);
    }

    public void initialize(SocialSettingsTabletPanelApp socialSettingsTabletPanelApp) {
        OCButton oCButton = this.mBinding.notificationsButton;
        oCButton.mEventHandler = socialSettingsTabletPanelApp;
        oCButton.setOnClickListener(new View.OnClickListener(socialSettingsTabletPanelApp) {
            /* class com.oculus.panelapp.socialsettings.views.$$Lambda$SocialSettingsNotifications$4TWViJgp02XU4pPBUsGZWR2dw_w2 */
            public final /* synthetic */ SocialSettingsTabletPanelApp f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                SocialSettingsNotifications.this.lambda$initialize$0$SocialSettingsNotifications(this.f$1, view);
            }
        });
    }

    public /* synthetic */ void lambda$initialize$0$SocialSettingsNotifications(SocialSettingsTabletPanelApp socialSettingsTabletPanelApp, View view) {
        socialSettingsTabletPanelApp.actionNavigate(SystemUXRoute.SETTINGS, "/notifications?category=MESSAGES");
    }

    public SocialSettingsNotifications(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mBinding = SocialSettingsNotificationsBinding.inflate(LayoutInflater.from(context), this, true);
    }
}
