package com.oculus.panelapp.socialsettings.views;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.LoggingConstants;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.ocui.OCLink;
import com.oculus.ocui.OCToggle;
import com.oculus.panelapp.socialsettings.SocialSettingsTabletPanelApp;
import com.oculus.panelapp.socialsettings.databinding.SocialSettingsActiveStatusBinding;
import com.oculus.panelapp.socialsettings.graphql.SocialSettingsGraphQL;
import com.oculus.tablet.view.BaseView;

public class SocialSettingsActiveStatus extends BaseView {
    public static final String ACTIVE_STATUS_LEARN_MORE = "https://www.facebook.com/help/448141485230424";
    public static final String TAG = LoggingUtil.tag(SocialSettingsActiveStatus.class);
    public SocialSettingsActiveStatusBinding mBinding;
    public Context mContext;
    public SocialSettingsTabletPanelApp mPanelApp;
    public SocialLogger mSocialLogger;
    public SocialSettingsActiveStatusViewModel mViewModel;

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
    }

    @Override // com.oculus.tablet.view.BaseView
    public void destroy() {
        OCToggle oCToggle = this.mBinding.activeStatusToggle;
        oCToggle.mEventHandler = null;
        oCToggle.setOnClickListener(null);
    }

    public void initialize(SocialSettingsTabletPanelApp socialSettingsTabletPanelApp) {
        this.mPanelApp = socialSettingsTabletPanelApp;
        this.mSocialLogger = socialSettingsTabletPanelApp.getLogger();
        SocialSettingsActiveStatusBinding socialSettingsActiveStatusBinding = this.mBinding;
        socialSettingsActiveStatusBinding.activeStatusToggle.mEventHandler = socialSettingsTabletPanelApp;
        OCLink oCLink = socialSettingsActiveStatusBinding.activeStatusBody;
        oCLink.mEventHandler = socialSettingsTabletPanelApp;
        oCLink.mLinkHandler = new OCLink.OCLinkHandler() {
            /* class com.oculus.panelapp.socialsettings.views.$$Lambda$a6QpiIVSwflbMUOsK3YVv14gXQ2 */

            @Override // com.oculus.ocui.OCLink.OCLinkHandler
            public final void open(String str, String str2) {
                SocialSettingsTabletPanelApp.this.actionNavigate(str, str2);
            }
        };
        oCLink.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.socialsettings.views.$$Lambda$SocialSettingsActiveStatus$M9XpL_aXHN3vHt_v8f1F1lgKOlE2 */

            public final void onClick(View view) {
                SocialSettingsActiveStatus.this.lambda$initialize$0$SocialSettingsActiveStatus(view);
            }
        });
    }

    public /* synthetic */ void lambda$initialize$0$SocialSettingsActiveStatus(View view) {
        this.mSocialLogger.logButtonClick(ClickEventButtonId.SOCIAL_SETTINGS_ACTIVE_STATUS_LEARN_MORE, SurfaceType.ACTIVE_STATUS);
    }

    public /* synthetic */ void lambda$onGraphQLReady$1$SocialSettingsActiveStatus(SocialSettingsGraphQL socialSettingsGraphQL, View view) {
        socialSettingsGraphQL.setMessengerActiveStatus(this.mBinding.activeStatusToggle.isChecked());
        this.mSocialLogger.logButtonClick(ClickEventButtonId.SOCIAL_SETTINGS_ACTIVE_STATUS_TOGGLE, SurfaceType.ACTIVE_STATUS);
    }

    public void onGraphQLReady() {
        SocialSettingsGraphQL socialSettingsGraphQL = this.mPanelApp.mSocialSettingsGraphQL;
        this.mBinding.activeStatusToggle.setOnClickListener(new View.OnClickListener(socialSettingsGraphQL) {
            /* class com.oculus.panelapp.socialsettings.views.$$Lambda$SocialSettingsActiveStatus$L8LnlcR490ICISHYg8RvIEAJ3WQ2 */
            public final /* synthetic */ SocialSettingsGraphQL f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                SocialSettingsActiveStatus.this.lambda$onGraphQLReady$1$SocialSettingsActiveStatus(this.f$1, view);
            }
        });
        final long currentTimeMillis = System.currentTimeMillis();
        socialSettingsGraphQL.getMessengerActiveStatus(new SocialSettingsGraphQL.SocialSettingsCallback() {
            /* class com.oculus.panelapp.socialsettings.views.SocialSettingsActiveStatus.AnonymousClass1 */

            @Override // com.oculus.panelapp.socialsettings.graphql.SocialSettingsGraphQL.SocialSettingsCallback
            public void onFailure() {
                SocialSettingsActiveStatus.this.mSocialLogger.logActionSuccess(ActionId.SET_ACTIVE_STATUS_TOGGLE, ClickEventButtonId.SOCIAL_SETTINGS_ACTIVE_STATUS_TOGGLE, SurfaceType.ACTIVE_STATUS, LoggingConstants.TIME_TO_COMPLETE_MS, Long.toString(System.currentTimeMillis() - currentTimeMillis));
            }

            @Override // com.oculus.panelapp.socialsettings.graphql.SocialSettingsGraphQL.SocialSettingsCallback
            public void onSuccess(final boolean z) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    /* class com.oculus.panelapp.socialsettings.views.SocialSettingsActiveStatus.AnonymousClass1.AnonymousClass1 */

                    public void run() {
                        ActionId actionId;
                        SocialSettingsActiveStatus.this.mBinding.activeStatusToggle.setChecked(z);
                        SocialSettingsActiveStatus.this.mViewModel.setIsLoaded(true);
                        long currentTimeMillis = System.currentTimeMillis();
                        AnonymousClass1 r2 = AnonymousClass1.this;
                        String l = Long.toString(currentTimeMillis - currentTimeMillis);
                        SocialLogger socialLogger = SocialSettingsActiveStatus.this.mSocialLogger;
                        if (z) {
                            actionId = ActionId.SET_ACTIVE_STATUS_ACTIVE;
                        } else {
                            actionId = ActionId.SET_ACTIVE_STATUS_INACTIVE;
                        }
                        socialLogger.logActionSuccess(actionId, ClickEventButtonId.SOCIAL_SETTINGS_ACTIVE_STATUS_TOGGLE, SurfaceType.ACTIVE_STATUS, LoggingConstants.TIME_TO_COMPLETE_MS, l);
                    }
                });
            }
        });
    }

    public SocialSettingsActiveStatus(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        SocialSettingsActiveStatusBinding inflate = SocialSettingsActiveStatusBinding.inflate(LayoutInflater.from(context), this, true);
        this.mBinding = inflate;
        this.mContext = context;
        SocialSettingsActiveStatusViewModel socialSettingsActiveStatusViewModel = new SocialSettingsActiveStatusViewModel();
        this.mViewModel = socialSettingsActiveStatusViewModel;
        inflate.setViewModel(socialSettingsActiveStatusViewModel);
        this.mViewModel.setIsLoaded(false);
    }
}
