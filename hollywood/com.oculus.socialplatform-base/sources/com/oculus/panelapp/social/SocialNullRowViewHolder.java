package com.oculus.panelapp.social;

import X.AnonymousClass1Ah;
import android.view.View;
import com.oculus.horizoncontent.user.LinkedAccountsInfo;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialListNullRowV2Binding;
import com.oculus.panelapp.social.utils.SocialBundleUtils;
import com.oculus.panelapp.social.utils.UpsellLoggingParameters;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.views.ShellButton;

public class SocialNullRowViewHolder extends AnonymousClass1Ah {
    public static final String FB_UPSELL_ACTION_PARAM = "send_friend_request";
    public static final String FB_UPSELL_CONTAINER_PARAM = "aui_v2_social_panel";
    public static final String FB_UPSELL_ENTRY_POINT = "aui_v2_social_upsell";
    public static final String FB_UPSELL_MUST_INTERACT_PARAM = "true";
    public static final String FB_UPSELL_PRODUCT_PARAM = "friends";
    public static final String FB_UPSELL_SOURCE_PARAM = "aui_v2_social_upsell";
    public AnytimeTabletSocialListNullRowV2Binding mBinding;
    public LinkedAccountsInfo mLinkedAccountsInfo;
    public SocialPanelApp mPanelApp;

    public SocialNullRowViewHolder(AnytimeTabletSocialListNullRowV2Binding anytimeTabletSocialListNullRowV2Binding, SocialPanelApp socialPanelApp) {
        super(anytimeTabletSocialListNullRowV2Binding.mRoot);
        this.mBinding = anytimeTabletSocialListNullRowV2Binding;
        this.mPanelApp = socialPanelApp;
    }

    private void initializeCTA() {
        UpsellLoggingParameters upsellLoggingParameters = new UpsellLoggingParameters("aui_v2_social_upsell", "send_friend_request", "aui_v2_social_panel", "aui_v2_social_upsell", "true", "friends");
        AnytimeTabletSocialListNullRowV2Binding anytimeTabletSocialListNullRowV2Binding = this.mBinding;
        boolean z = anytimeTabletSocialListNullRowV2Binding.mIsFBLinked;
        ShellButton shellButton = anytimeTabletSocialListNullRowV2Binding.nullRowCta;
        if (z) {
            shellButton.setOnClickListener(new View.OnClickListener(upsellLoggingParameters) {
                /* class com.oculus.panelapp.social.$$Lambda$SocialNullRowViewHolder$XhuEfZrVvADl7GoC6D1vbQ1AqQ82 */
                public final /* synthetic */ UpsellLoggingParameters f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    SocialNullRowViewHolder.this.lambda$initializeCTA$1$SocialNullRowViewHolder(this.f$1, view);
                }
            });
        } else {
            shellButton.setOnClickListener(new View.OnClickListener(upsellLoggingParameters) {
                /* class com.oculus.panelapp.social.$$Lambda$SocialNullRowViewHolder$GZTGARekBU20pa3JUALukuCL42w2 */
                public final /* synthetic */ UpsellLoggingParameters f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    SocialNullRowViewHolder.this.lambda$initializeCTA$2$SocialNullRowViewHolder(this.f$1, view);
                }
            });
        }
    }

    public void bindRow(SocialAdapterNullRow socialAdapterNullRow) {
        LinkedAccountsInfo linkedAccountsInfo = socialAdapterNullRow.mLinkedAccountsInfo;
        this.mLinkedAccountsInfo = linkedAccountsInfo;
        this.mBinding.setIsFBLinked(linkedAccountsInfo.isFbLinked());
        initializeCTA();
    }

    public /* synthetic */ void lambda$initializeCTA$1$SocialNullRowViewHolder(UpsellLoggingParameters upsellLoggingParameters, View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_UPSELL_FRIEND);
        SocialBundleUtils.PerformActionIfLinked(this.mPanelApp, this.mLinkedAccountsInfo, new Runnable() {
            /* class com.oculus.panelapp.social.$$Lambda$SocialNullRowViewHolder$Nr8xAA6su5T7RTIFiSbSrVWsNno2 */

            public final void run() {
                SocialNullRowViewHolder.this.lambda$null$0$SocialNullRowViewHolder();
            }
        }, upsellLoggingParameters);
    }

    public /* synthetic */ void lambda$initializeCTA$2$SocialNullRowViewHolder(UpsellLoggingParameters upsellLoggingParameters, View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_UPSELL_FB_LINK);
        SocialBundleUtils.ShowUpsell(this.mPanelApp, upsellLoggingParameters);
    }

    public /* synthetic */ void lambda$null$0$SocialNullRowViewHolder() {
        this.mPanelApp.actionNavigate(SystemUXRoute.SOCIAL_ADD_FRIENDS, "");
    }
}
