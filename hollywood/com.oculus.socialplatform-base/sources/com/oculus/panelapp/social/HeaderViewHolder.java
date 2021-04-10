package com.oculus.panelapp.social;

import X.AnonymousClass1Ah;
import android.content.Context;
import android.view.View;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialListHeaderV2Binding;
import com.oculus.panelapp.social.utils.SocialBundleUtils;
import com.oculus.panelapp.social.utils.UpsellLoggingParameters;
import com.oculus.tablet.logging.ClickEventButtonId;

public class HeaderViewHolder extends AnonymousClass1Ah {
    public AnytimeTabletSocialListHeaderV2Binding mBinding;

    public HeaderViewHolder(AnytimeTabletSocialListHeaderV2Binding anytimeTabletSocialListHeaderV2Binding, Context context, SocialPanelApp socialPanelApp) {
        super(anytimeTabletSocialListHeaderV2Binding.socialListHeaderItem);
        this.mBinding = anytimeTabletSocialListHeaderV2Binding;
        anytimeTabletSocialListHeaderV2Binding.buttonAddFriend.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.social.$$Lambda$HeaderViewHolder$wAOBD8r5QTTZvWE2s9TLh9crz42 */

            public final void onClick(View view) {
                HeaderViewHolder.lambda$new$1(SocialPanelApp.this, view);
            }
        });
    }

    public static /* synthetic */ void lambda$new$1(SocialPanelApp socialPanelApp, View view) {
        socialPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_FRIENDS_FRIEND_ADD);
        SocialBundleUtils.PerformActionIfLinked(socialPanelApp, socialPanelApp.acquireSocialViewModel().mLinkedAccountsInfo, new Runnable() {
            /* class com.oculus.panelapp.social.$$Lambda$HeaderViewHolder$ToUAzlXEpYTdloxXLamkaXKMAI42 */

            public final void run() {
                HeaderViewHolder.lambda$null$0(SocialPanelApp.this);
            }
        }, new UpsellLoggingParameters("aui_v2_social_panel", "send_friend_request", "aui_v2_social_panel", SocialBundleConstants.FB_UPSELL_ADD_FRIEND_HEADER_BUTTON, "true", "friends"));
    }

    public void bindHeader(SocialAdapterHeader socialAdapterHeader) {
        this.mBinding.setName(socialAdapterHeader.mName);
        this.mBinding.setShowAddFriend(socialAdapterHeader.mIsFriendHeader);
    }
}
