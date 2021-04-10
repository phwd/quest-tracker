package com.oculus.panelapp.social.actions;

import android.content.Context;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.horizoncontent.social.SocialUserFriendshipStatus;
import com.oculus.panelapp.social.R;
import com.oculus.panelapp.social.SocialBundleConstants;
import com.oculus.panelapp.social.SocialLogger;
import com.oculus.panelapp.social.SocialPanelApp;
import com.oculus.panelapp.social.SocialUserAdapterItem;
import com.oculus.panelapp.social.SocialViewModel;
import com.oculus.panelapp.social.SocialViewWarningToaster;
import com.oculus.panelapp.social.actions.SocialUserAction;
import com.oculus.panelapp.social.utils.UpsellLoggingParameters;
import com.oculus.tablet.logging.ClickEventButtonId;

public class AddFriend extends SocialUserAction {
    private static final String TAG = LoggingUtil.tag(AddFriend.class);
    private SocialUserAdapterItem mUser;

    public AddFriend(SocialUserAdapterItem socialUserAdapterItem) {
        this.mUser = socialUserAdapterItem;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public boolean isRelevant() {
        return this.mUser.getUser().getFriendship() == SocialUserFriendshipStatus.CAN_REQUEST || this.mUser.getUser().getUserType() == SocialUser.UserRowType.SUGGESTED_FRIEND;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public SocialUserActionType getType() {
        return SocialUserActionType.ADD_FRIEND;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public UpsellLoggingParameters upsellParams() {
        return new UpsellLoggingParameters("aui_v2_social_panel", SocialBundleConstants.FB_UPSELL_SEND_FRIEND_REQUEST, "aui_v2_social_panel", SocialBundleConstants.FB_UPSELL_ADD_FRIEND_BUTTON, SocialBundleConstants.FB_UPSELL_MUST_INTERACT, SocialBundleConstants.FB_UPSELL_FRIEND_PRODUCT);
    }

    /* access modifiers changed from: package-private */
    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public ClickEventButtonId getButtonId(SocialUserAction.Source source) {
        if (source == SocialUserAction.Source.USER_CARD_MENU) {
            return ClickEventButtonId.AUIV2_SOCIAL_PYMK_CARD_SEND_REQUEST;
        }
        return ClickEventButtonId.AUIV2_SOCIAL_PYMK_ROW_SEND_REQUEST;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public void preformAction(final SocialPanelApp socialPanelApp, final Context context, final SocialUserActionActionHandler socialUserActionActionHandler) {
        final SocialViewModel acquireSocialViewModel = socialPanelApp.acquireSocialViewModel();
        HorizonContentProviderHelper.sendFriendRequest(context, this.mUser.getID(), "PROFILE", new HorizonContentProviderHelper.SendFriendRequestCallback() {
            /* class com.oculus.panelapp.social.actions.AddFriend.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SendFriendRequestCallback
            public void onSuccess(String str) {
                socialUserActionActionHandler.onSuccess();
                acquireSocialViewModel.removeUserOptimisticAndRefetch(str, SocialViewModel.SocialList.FRIENDS);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                socialUserActionActionHandler.onError();
                SocialLogger.logError(socialPanelApp, SocialBundleConstants.FB_UPSELL_SEND_FRIEND_REQUEST, AddFriend.TAG, "Failed to send friend request");
                Context context = context;
                SocialViewWarningToaster.showToast(context, "oculus_mobile_social_send_friend_request_error", context.getResources().getString(R.string.anytime_tablet_social_friend_request_failed), AddFriend.TAG);
            }
        });
    }
}
