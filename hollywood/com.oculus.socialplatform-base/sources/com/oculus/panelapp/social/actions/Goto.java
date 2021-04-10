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
    public SocialUserAdapterItem mUser;

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public UpsellLoggingParameters upsellParams() {
        return null;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public ClickEventButtonId getButtonId(SocialUserAction.Source source) {
        if (source == SocialUserAction.Source.USER_CARD_MENU) {
            return ClickEventButtonId.AUIV2_SOCIAL_PARTIES_GOTO_USER_FROM_USER_CARD;
        }
        return ClickEventButtonId.AUIV2_SOCIAL_PARTIES_GOTO_USER_FROM_USER_VIEW;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public SocialUserActionType getType() {
        return SocialUserActionType.GOTO;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public boolean isRelevant() {
        if (this.mUser.mUser.mDeepLink != null) {
            return true;
        }
        return false;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public void preformAction(SocialPanelApp socialPanelApp, Context context, SocialUserActionActionHandler socialUserActionActionHandler) {
        SocialDeeplinkPresence socialDeeplinkPresence = this.mUser.mUser.mDeepLink;
        String str = socialDeeplinkPresence.mPackageName;
        String str2 = socialDeeplinkPresence.mAppID;
        String str3 = socialDeeplinkPresence.mDeepLinkLaunchParams;
        socialPanelApp.actionNavigate(str, str3);
        socialPanelApp.logSocialPartyEvent(SocialPartyEvent.PARTY_DIALOG_JOIN_BUTTON_APP_LAUNCH, "deeplink_package_name", str, "deeplink_app_id", str2, "deeplink_launch_params", str3);
    }

    public Goto(SocialUserAdapterItem socialUserAdapterItem) {
        this.mUser = socialUserAdapterItem;
    }
}
