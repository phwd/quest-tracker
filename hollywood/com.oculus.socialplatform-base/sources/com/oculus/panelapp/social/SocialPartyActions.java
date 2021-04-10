package com.oculus.panelapp.social;

import android.content.Context;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialPartyType;
import com.oculus.socialplatform.R;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import com.oculus.vrshell.SystemUXRoute;
import java.util.List;

public class SocialPartyActions {
    public static final String TAG = LoggingUtil.tag(SocialPartyActions.class);
    public final Context mContext;
    public final SocialPanelApp mPanelApp;

    public void inviteUsersToParty(List<String> list, String str) {
        HorizonContentProviderHelper.inviteUsersToParty(this.mContext, list, str, new HorizonContentProviderHelper.MultipleIDCallback() {
            /* class com.oculus.panelapp.social.SocialPartyActions.AnonymousClass2 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialLogger.logError(SocialPartyActions.this.mContext, "invite_users_to_party", SocialPartyActions.TAG, "Failed to invite users to party");
                SocialPartyActions.this.mPanelApp.acquireSocialViewModel().loadPartyData();
                Context context = SocialPartyActions.this.mContext;
                SocialViewWarningToaster.showToast(context, "oculus_mobile_social_party_invite_error", context.getResources().getString(R.string.anytime_tablet_social_party_invite_failed), SocialPartyActions.TAG);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.MultipleIDCallback
            public void onSuccess(List<String> list) {
                SocialPartyActions.this.mPanelApp.acquireSocialViewModel().loadPartyData();
            }
        });
    }

    public void kickUserFromParty(String str, String str2) {
        HorizonContentProviderHelper.kickUserFromParty(this.mContext, str, str2, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.social.SocialPartyActions.AnonymousClass4 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialLogger.logError(SocialPartyActions.this.mContext, "kick_user_from_party", SocialPartyActions.TAG, "Failed to kick user from party");
                SocialPartyActions.this.mPanelApp.acquireSocialViewModel().loadPartyData();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                SocialPartyActions.this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_PARTY_KICK_SUCCESS);
                SocialPartyActions.this.mPanelApp.acquireSocialViewModel().loadPartyData();
            }
        });
    }

    public void startPartyWithUsers(final List<String> list, final SocialPartyType socialPartyType) {
        this.mPanelApp.actionNavigate(SystemUXRoute.PARTIES, TabletDeepLinkingUriUtil.AUI_SOCIAL_PARTY_VIEW_URI);
        HorizonContentProviderHelper.createPartyDEPRECATED(this.mContext, null, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.social.SocialPartyActions.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialLogger.logError(SocialPartyActions.this.mContext, "start_party_with_users", SocialPartyActions.TAG, "Failed to start party with users");
                Context context = SocialPartyActions.this.mContext;
                SocialViewWarningToaster.showToast(context, "oculus_mobile_social_party_start_error", context.getResources().getString(R.string.anytime_tablet_social_party_start_failed), SocialPartyActions.TAG);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                SocialPartyActions.this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_START_PARTY_SUCCESS);
                SocialPartyType socialPartyType = socialPartyType;
                if (socialPartyType.equals(SocialPartyType.JOINABLE_BY_FRIENDS)) {
                    SocialPartyActions.this.setPartyJoinType(socialPartyType, str);
                }
                if (list.size() > 0) {
                    SocialPartyActions.this.inviteUsersToParty(list, str);
                }
            }
        });
    }

    public SocialPartyActions(Context context, SocialPanelApp socialPanelApp) {
        this.mContext = context;
        this.mPanelApp = socialPanelApp;
    }

    public void setPartyJoinType(SocialPartyType socialPartyType, String str) {
        socialPartyType.toString();
        HorizonContentProviderHelper.setPartyTypeDEPRECATED(this.mContext, str, socialPartyType.toTypeForMutation(), new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.social.SocialPartyActions.AnonymousClass3 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialLogger.logError(SocialPartyActions.this.mContext, "set_party_join_type", SocialPartyActions.TAG, "Failed to set party join type");
                Context context = SocialPartyActions.this.mContext;
                SocialViewWarningToaster.showToast(context, "oculus_mobile_social_party_join_error", context.getResources().getString(R.string.anytime_tablet_social_party_privacy_update_failed), SocialPartyActions.TAG);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                SocialPartyActions.this.mPanelApp.acquireSocialViewModel().loadPartyData();
            }
        });
    }
}
