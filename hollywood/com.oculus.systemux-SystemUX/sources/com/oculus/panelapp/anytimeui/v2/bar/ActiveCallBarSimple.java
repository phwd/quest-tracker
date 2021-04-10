package com.oculus.panelapp.anytimeui.v2.bar;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.anytimeui.databinding.AnytimeBarActiveCallBarSimpleV2Binding;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.social.SocialViewModel;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.vrshell.SystemUXRoute;

public final class ActiveCallBarSimple extends ConstraintLayout {
    private static final String TAG = LoggingUtil.tag(ActiveCallBarSimple.class);
    private AnytimeBarActiveCallBarSimpleV2Binding mBinding;
    private OCButton mExpandedButton;
    private AnytimeUIAndroidPanelAppV2 mPanelApp;
    private SocialViewModel mSocialViewModel;

    public ActiveCallBarSimple(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void initialize(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, OCButton oCButton) {
        Log.d(TAG, "Initializing bar lower section");
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mBinding = AnytimeBarActiveCallBarSimpleV2Binding.inflate(LayoutInflater.from(getContext()), this, true);
        this.mSocialViewModel = this.mPanelApp.acquireSocialViewModel();
        this.mBinding.setViewModel(this.mSocialViewModel);
        this.mExpandedButton = oCButton;
        initializeButton();
        Log.d(TAG, "Initialized bar lower section");
    }

    private void initializeButton() {
        this.mBinding.activeCallButton.setEventHandler(this.mPanelApp);
        $$Lambda$ActiveCallBarSimple$Pr_R0HpvBhoooYlASC597WCZ07k r0 = new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$ActiveCallBarSimple$Pr_R0HpvBhoooYlASC597WCZ07k */

            public final void onClick(View view) {
                ActiveCallBarSimple.this.lambda$initializeButton$85$ActiveCallBarSimple(view);
            }
        };
        this.mBinding.activeCallButton.setOnClickListener(r0);
        this.mExpandedButton.setOnClickListener(r0);
    }

    public /* synthetic */ void lambda$initializeButton$85$ActiveCallBarSimple(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_ACTIVE_CALL_BAR_SIMPLIFIED_ACTIVE_CALL_BUTTON);
        if (this.mPanelApp.isGKEnabled(Gatekeeper.SOCIAL_NEW_PARTIES_PANEL_APP)) {
            Log.d(TAG, "Navigating to new parties");
            this.mPanelApp.actionNavigate(SystemUXRoute.AUI_PARTIES, "");
            return;
        }
        Log.d(TAG, "Navigating to old parties");
        this.mPanelApp.actionNavigate(SystemUXRoute.AUI_SOCIAL, "");
    }

    public void updateUI() {
        this.mSocialViewModel.updateActiveCallBar();
    }

    public void onShow() {
        initializeButton();
    }

    public void onHide() {
        this.mBinding.activeCallButton.setEventHandler(null);
        this.mExpandedButton.setOnClickListener(null);
        this.mSocialViewModel = null;
        this.mPanelApp.releaseSocialViewModel();
    }
}
