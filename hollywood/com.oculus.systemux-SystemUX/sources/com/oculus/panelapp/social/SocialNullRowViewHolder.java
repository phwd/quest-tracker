package com.oculus.panelapp.social;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.horizoncontent.user.LinkedAccountsInfo;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialListNullRowV2Binding;
import com.oculus.panelapp.social.utils.SocialBundleUtils;
import com.oculus.panelapp.social.utils.UpsellLoggingParameters;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.vrshell.SystemUXRoute;

public class SocialNullRowViewHolder extends RecyclerView.ViewHolder {
    private static final String FB_UPSELL_ACTION_PARAM = "send_friend_request";
    private static final String FB_UPSELL_CONTAINER_PARAM = "aui_v2_social_panel";
    private static final String FB_UPSELL_ENTRY_POINT = "aui_v2_social_upsell";
    private static final String FB_UPSELL_MUST_INTERACT_PARAM = "true";
    private static final String FB_UPSELL_PRODUCT_PARAM = "friends";
    private static final String FB_UPSELL_SOURCE_PARAM = "aui_v2_social_upsell";
    private AnytimeTabletSocialListNullRowV2Binding mBinding;
    private LinkedAccountsInfo mLinkedAccountsInfo;
    private SocialPanelApp mPanelApp;

    public SocialNullRowViewHolder(AnytimeTabletSocialListNullRowV2Binding anytimeTabletSocialListNullRowV2Binding, SocialPanelApp socialPanelApp) {
        super(anytimeTabletSocialListNullRowV2Binding.getRoot());
        this.mBinding = anytimeTabletSocialListNullRowV2Binding;
        this.mPanelApp = socialPanelApp;
    }

    public void bindRow(SocialAdapterNullRow socialAdapterNullRow) {
        this.mLinkedAccountsInfo = socialAdapterNullRow.getLinkedAccountsInfo();
        this.mBinding.setIsFBLinked(this.mLinkedAccountsInfo.isFbLinked());
        initializeCTA();
    }

    private void initializeCTA() {
        UpsellLoggingParameters upsellLoggingParameters = new UpsellLoggingParameters("aui_v2_social_upsell", "send_friend_request", "aui_v2_social_panel", "aui_v2_social_upsell", "true", "friends");
        if (this.mBinding.getIsFBLinked()) {
            this.mBinding.nullRowCta.setOnClickListener(new View.OnClickListener(upsellLoggingParameters) {
                /* class com.oculus.panelapp.social.$$Lambda$SocialNullRowViewHolder$C3Ym2soeW1_zwzeXUWRXE99eNzU */
                private final /* synthetic */ UpsellLoggingParameters f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    SocialNullRowViewHolder.this.lambda$initializeCTA$15$SocialNullRowViewHolder(this.f$1, view);
                }
            });
        } else {
            this.mBinding.nullRowCta.setOnClickListener(new View.OnClickListener(upsellLoggingParameters) {
                /* class com.oculus.panelapp.social.$$Lambda$SocialNullRowViewHolder$4lHa5dgGYD7d_mldiT47IhhRIro */
                private final /* synthetic */ UpsellLoggingParameters f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    SocialNullRowViewHolder.this.lambda$initializeCTA$16$SocialNullRowViewHolder(this.f$1, view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$initializeCTA$15$SocialNullRowViewHolder(UpsellLoggingParameters upsellLoggingParameters, View view) {
        SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_UPSELL_FRIEND, this.mPanelApp);
        SocialBundleUtils.PerformActionIfLinked(this.mPanelApp, this.mLinkedAccountsInfo, new Runnable() {
            /* class com.oculus.panelapp.social.$$Lambda$SocialNullRowViewHolder$iZzRH6PLHftpymOCBpg9KN5Dlk */

            public final void run() {
                SocialNullRowViewHolder.this.lambda$null$14$SocialNullRowViewHolder();
            }
        }, upsellLoggingParameters);
    }

    public /* synthetic */ void lambda$null$14$SocialNullRowViewHolder() {
        this.mPanelApp.actionNavigate(SystemUXRoute.SOCIAL_ADD_FRIENDS, "");
    }

    public /* synthetic */ void lambda$initializeCTA$16$SocialNullRowViewHolder(UpsellLoggingParameters upsellLoggingParameters, View view) {
        SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_UPSELL_FB_LINK, this.mPanelApp);
        SocialBundleUtils.ShowUpsell(this.mPanelApp, upsellLoggingParameters);
    }
}
