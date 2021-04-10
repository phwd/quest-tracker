package com.oculus.panelapp.social;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialListHeaderV2Binding;
import com.oculus.panelapp.social.utils.SocialBundleUtils;
import com.oculus.panelapp.social.utils.UpsellLoggingParameters;
import com.oculus.tablet.logging.ClickEventButtonId;

public class HeaderViewHolder extends RecyclerView.ViewHolder {
    private AnytimeTabletSocialListHeaderV2Binding mBinding;

    public HeaderViewHolder(AnytimeTabletSocialListHeaderV2Binding anytimeTabletSocialListHeaderV2Binding, Context context, SocialPanelApp socialPanelApp) {
        super(anytimeTabletSocialListHeaderV2Binding.socialListHeaderItem);
        this.mBinding = anytimeTabletSocialListHeaderV2Binding;
        this.mBinding.buttonAddFriend.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.social.$$Lambda$HeaderViewHolder$wAOBD8r5QTTZvWE2s9TLh9crz4 */

            public final void onClick(View view) {
                HeaderViewHolder.lambda$new$1(SocialPanelApp.this, view);
            }
        });
    }

    static /* synthetic */ void lambda$new$1(SocialPanelApp socialPanelApp, View view) {
        SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_FRIENDS_FRIEND_ADD, socialPanelApp);
        SocialBundleUtils.PerformActionIfLinked(socialPanelApp, socialPanelApp.acquireSocialViewModel().getLinkedAccountsInfo(), new Runnable() {
            /* class com.oculus.panelapp.social.$$Lambda$HeaderViewHolder$ToUAzlXEpYTdloxXLamkaXKMAI4 */

            public final void run() {
                HeaderViewHolder.lambda$null$0(SocialPanelApp.this);
            }
        }, new UpsellLoggingParameters("aui_v2_social_panel", SocialBundleConstants.FB_UPSELL_SEND_FRIEND_REQUEST, "aui_v2_social_panel", SocialBundleConstants.FB_UPSELL_ADD_FRIEND_HEADER_BUTTON, SocialBundleConstants.FB_UPSELL_MUST_INTERACT, SocialBundleConstants.FB_UPSELL_FRIEND_PRODUCT));
    }

    public void bindHeader(SocialAdapterHeader socialAdapterHeader) {
        this.mBinding.setName(socialAdapterHeader.getName());
        this.mBinding.setShowAddFriend(socialAdapterHeader.getIsFriendHeader());
    }
}
