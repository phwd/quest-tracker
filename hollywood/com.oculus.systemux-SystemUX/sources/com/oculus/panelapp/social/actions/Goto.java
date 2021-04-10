package com.oculus.panelapp.social.actions;

import android.content.Context;
import com.oculus.horizoncontent.social.SocialDeeplinkPresence;
import com.oculus.panelapp.social.SocialPanelApp;
import com.oculus.panelapp.social.SocialUserAdapterItem;
import com.oculus.panelapp.social.actions.SocialUserAction;
import com.oculus.panelapp.social.logging.SocialPartyEvent;
import com.oculus.panelapp.social.utils.UpsellLoggingParameters;
import com.oculus.tablet.logging.ClickEventButtonId;

public class Goto extends SocialUserAction {
    private SocialUserAdapterItem mUser;

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public UpsellLoggingParameters upsellParams() {
        return null;
    }

    public Goto(SocialUserAdapterItem socialUserAdapterItem) {
        this.mUser = socialUserAdapterItem;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public boolean isRelevant() {
        return this.mUser.getUser().getDeepLink() != null;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public SocialUserActionType getType() {
        return SocialUserActionType.GOTO;
    }

    /* access modifiers changed from: package-private */
    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public ClickEventButtonId getButtonId(SocialUserAction.Source source) {
        if (source == SocialUserAction.Source.USER_CARD_MENU) {
            return ClickEventButtonId.AUIV2_SOCIAL_PARTIES_GOTO_USER_FROM_USER_CARD;
        }
        return ClickEventButtonId.AUIV2_SOCIAL_PARTIES_GOTO_USER_FROM_USER_VIEW;
    }

    /* access modifiers changed from: package-private */
    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public void preformAction(SocialPanelApp socialPanelApp, Context context, SocialUserActionActionHandler socialUserActionActionHandler) {
        SocialDeeplinkPresence deepLink = this.mUser.getUser().getDeepLink();
        String packageName = deepLink.getPackageName();
        String appID = deepLink.getAppID();
        String deepLinkLaunchParams = deepLink.getDeepLinkLaunchParams();
        socialPanelApp.actionNavigate(packageName, deepLinkLaunchParams);
        socialPanelApp.logSocialPartyEvent(SocialPartyEvent.PARTY_DIALOG_JOIN_BUTTON_APP_LAUNCH, "deeplink_package_name", packageName, "deeplink_app_id", appID, "deeplink_launch_params", deepLinkLaunchParams);
    }
}
