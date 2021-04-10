package com.oculus.panelapp.socialandroidbackpanel.views.application_invites;

import X.AnonymousClass1uW;
import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.socialandroidbackpanel.SocialAndroidBackPanelApp;
import com.oculus.panelapp.socialandroidbackpanel.databinding.ApplicationInvitesViewBinding;
import com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorCallback;
import com.oculus.tablet.view.BaseView;

public class ApplicationInvitesView extends BaseView {
    public static final String TAG = LoggingUtil.tag(ApplicationInvitesView.class);
    public ApplicationInvitesViewBinding mBinding;
    public final ApplicationInvitesViewModel mViewModel = new ApplicationInvitesViewModel();

    @Override // com.oculus.tablet.view.BaseView
    public void destroy() {
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
    }

    public ApplicationInvitesView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void initialize(SocialAndroidBackPanelApp socialAndroidBackPanelApp, AnonymousClass1uW r3, ErrorCallback errorCallback) {
        super.initialize(socialAndroidBackPanelApp, r3);
        ApplicationInvitesViewBinding applicationInvitesViewBinding = (ApplicationInvitesViewBinding) r3;
        this.mBinding = applicationInvitesViewBinding;
        applicationInvitesViewBinding.setApplicationInvitesViewModel(this.mViewModel);
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onShow(@Nullable String str) {
    }
}
