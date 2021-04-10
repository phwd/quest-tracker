package com.oculus.panelapp.socialandroidbackpanel.views.start_messenger_call;

import X.AnonymousClass1uW;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.google.common.collect.ImmutableMap;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.LoggingConstants;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.socialandroidbackpanel.SocialAndroidBackPanelApp;
import com.oculus.panelapp.socialandroidbackpanel.databinding.StartMessengerCallViewBinding;
import com.oculus.panelapp.socialandroidbackpanel.views.SocialAndroidBackPanelView;
import com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorCallback;
import com.oculus.tablet.view.BaseView;
import java.util.Map;

public class StartMessengerCallView extends BaseView implements SocialAndroidBackPanelView {
    public static final String TAG = LoggingUtil.tag(StartMessengerCallView.class);
    public StartMessengerCallViewBinding mBinding;
    public final Context mContext;
    public ErrorCallback mErrorCallback;
    public SocialAndroidBackPanelApp mPanelApp;

    @Override // com.oculus.panelapp.socialandroidbackpanel.views.SocialAndroidBackPanelView, com.oculus.tablet.view.BaseView
    public void destroy() {
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
    }

    private void initializePrimaryButton() {
        OCButton oCButton = this.mBinding.startMessengerCallPrimaryButton;
        if (oCButton != null) {
            oCButton.mEventHandler = this.mPanelApp;
            oCButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.socialandroidbackpanel.views.start_messenger_call.$$Lambda$StartMessengerCallView$o4PzotMbDsPc2UXRKB_OKe65E2 */

                public final void onClick(View view) {
                    StartMessengerCallView.this.lambda$initializePrimaryButton$0$StartMessengerCallView(view);
                }
            });
        }
    }

    private void initializeSecondaryButton() {
        OCButton oCButton = this.mBinding.startMessengerCallSecondaryButton;
        if (oCButton != null) {
            oCButton.mEventHandler = this.mPanelApp;
            oCButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.socialandroidbackpanel.views.start_messenger_call.$$Lambda$StartMessengerCallView$MBdDH1oBItqZsSU9jx8kywU53M2 */

                public final void onClick(View view) {
                    StartMessengerCallView.this.lambda$initializeSecondaryButton$1$StartMessengerCallView(view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$initializePrimaryButton$0$StartMessengerCallView(View view) {
        this.mPanelApp.getLogger().logButtonClick(ClickEventButtonId.START_MESSENGER_CALL_CONFIRM, SurfaceType.START_MESSENGER_CALL, buildLogParams(null));
    }

    public /* synthetic */ void lambda$initializeSecondaryButton$1$StartMessengerCallView(View view) {
        this.mPanelApp.getLogger().logButtonClick(ClickEventButtonId.START_MESSENGER_CALL_CANCEL, SurfaceType.START_MESSENGER_CALL, buildLogParams(null));
        this.mPanelApp.quitApp();
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.views.SocialAndroidBackPanelView
    public boolean onControllerBackButton() {
        this.mPanelApp.getLogger().logButtonClick(ClickEventButtonId.START_MESSENGER_CALL_CANCEL, SurfaceType.START_MESSENGER_CALL, buildLogParams(null));
        this.mPanelApp.quitApp();
        return true;
    }

    public StartMessengerCallView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    private Map<String, String> buildLogParams(Long l) {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        if (l != null) {
            A04.put(LoggingConstants.TIME_TO_COMPLETE_MS, Long.toString(l.longValue()));
        }
        return A04.build();
    }

    public void initialize(SocialAndroidBackPanelApp socialAndroidBackPanelApp, AnonymousClass1uW r2, ErrorCallback errorCallback) {
        super.initialize(socialAndroidBackPanelApp, r2);
        this.mPanelApp = socialAndroidBackPanelApp;
        this.mErrorCallback = errorCallback;
        this.mBinding = (StartMessengerCallViewBinding) r2;
        initializePrimaryButton();
        initializeSecondaryButton();
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.views.SocialAndroidBackPanelView, com.oculus.tablet.view.BaseView
    public void onShow(@Nullable String str) {
    }
}
