package com.oculus.panelapp.social;

import android.content.Context;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialPartyType;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import com.oculus.vrshell.SystemUXRoute;
import java.util.List;

public class SocialPartyActions {
    private static final String TAG = LoggingUtil.tag(SocialPartyActions.class);
    private final Context mContext;
    private final SocialPanelApp mPanelApp;

    public SocialPartyActions(Context context, SocialPanelApp socialPanelApp) {
        this.mContext = context;
        this.mPanelApp = socialPanelApp;
    }

    public void startPartyWithUsers(final List<String> list, final SocialPartyType socialPartyType) {
        this.mPanelApp.actionNavigate(SystemUXRoute.PARTIES, TabletDeepLinkingUriUtil.AUI_SOCIAL_PARTY_VIEW_URI);
        HorizonContentProviderHelper.createPartyDEPRECATED(this.mContext, null, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.social.SocialPartyActions.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_START_PARTY_SUCCESS, SocialPartyActions.this.mPanelApp);
                if (socialPartyType.equals(SocialPartyType.JOINABLE_BY_FRIENDS)) {
                    SocialPartyActions.this.setPartyJoinType(socialPartyType, str);
                }
                if (list.size() > 0) {
                    SocialPartyActions.this.inviteUsersToParty(list, str);
                }
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialLogger.logError(SocialPartyActions.this.mPanelApp, "start_party_with_users", SocialPartyActions.TAG, "Failed to start party with users");
                SocialViewWarningToaster.showToast(SocialPartyActions.this.mContext, "oculus_mobile_social_party_start_error", SocialPartyActions.this.mContext.getResources().getString(R.string.anytime_tablet_social_party_start_failed), SocialPartyActions.TAG);
            }
        });
    }

    public void inviteUsersToParty(List<String> list, String str) {
        HorizonContentProviderHelper.inviteUsersToParty(this.mContext, list, str, new HorizonContentProviderHelper.MultipleIDCallback() {
            /* class com.oculus.panelapp.social.SocialPartyActions.AnonymousClass2 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.MultipleIDCallback
            public void onSuccess(List<String> list) {
                SocialPartyActions.this.mPanelApp.acquireSocialViewModel().loadPartyData();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialLogger.logError(SocialPartyActions.this.mPanelApp, "invite_users_to_party", SocialPartyActions.TAG, "Failed to invite users to party");
                SocialPartyActions.this.mPanelApp.acquireSocialViewModel().loadPartyData();
                SocialViewWarningToaster.showToast(SocialPartyActions.this.mContext, "oculus_mobile_social_party_invite_error", SocialPartyActions.this.mContext.getResources().getString(R.string.anytime_tablet_social_party_invite_failed), SocialPartyActions.TAG);
            }
        });
    }

    public void setPartyJoinType(SocialPartyType socialPartyType, String str) {
        String str2 = TAG;
        Log.d(str2, "updating join privacy to " + socialPartyType.toString());
        HorizonContentProviderHelper.setPartyTypeDEPRECATED(this.mContext, str, socialPartyType.toTypeForMutation(), new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.social.SocialPartyActions.AnonymousClass3 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                SocialPartyActions.this.mPanelApp.acquireSocialViewModel().loadPartyData();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialLogger.logError(SocialPartyActions.this.mPanelApp, "set_party_join_type", SocialPartyActions.TAG, "Failed to set party join type");
                SocialViewWarningToaster.showToast(SocialPartyActions.this.mContext, "oculus_mobile_social_party_join_error", SocialPartyActions.this.mContext.getResources().getString(R.string.anytime_tablet_social_party_privacy_update_failed), SocialPartyActions.TAG);
            }
        });
    }

    public void kickUserFromParty(String str, String str2) {
        HorizonContentProviderHelper.kickUserFromParty(this.mContext, str, str2, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.social.SocialPartyActions.AnonymousClass4 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_PARTY_KICK_SUCCESS, SocialPartyActions.this.mPanelApp);
                SocialPartyActions.this.mPanelApp.acquireSocialViewModel().loadPartyData();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialLogger.logError(SocialPartyActions.this.mPanelApp, "kick_user_from_party", SocialPartyActions.TAG, "Failed to kick user from party");
                SocialPartyActions.this.mPanelApp.acquireSocialViewModel().loadPartyData();
            }
        });
    }
}
