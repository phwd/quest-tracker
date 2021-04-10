package com.oculus.panelapp.social.actions;

import android.content.Context;
import com.oculus.horizoncontent.social.SocialPartyType;
import com.oculus.panelapp.social.SocialBundleConstants;
import com.oculus.panelapp.social.SocialPanelApp;
import com.oculus.panelapp.social.SocialPartyActions;
import com.oculus.panelapp.social.SocialUserAdapterItem;
import com.oculus.panelapp.social.actions.SocialUserAction;
import com.oculus.panelapp.social.utils.UpsellLoggingParameters;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import com.oculus.vrshell.SystemUXRoute;
import java.util.ArrayList;

public class CreatePartyWith extends SocialUserAction {
    public SocialUserAdapterItem mUser;

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public ClickEventButtonId getButtonId(SocialUserAction.Source source) {
        return null;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public SocialUserActionType getType() {
        return SocialUserActionType.CREATE_PARTY_WITH;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public boolean isRelevant() {
        if (this.mUser.mParty == null) {
            return true;
        }
        return false;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public void preformAction(SocialPanelApp socialPanelApp, Context context, SocialUserActionActionHandler socialUserActionActionHandler) {
        socialPanelApp.actionNavigate(SystemUXRoute.PARTIES, TabletDeepLinkingUriUtil.AUI_SOCIAL_PARTY_VIEW_URI);
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.mUser.getID());
        new SocialPartyActions(context, socialPanelApp).startPartyWithUsers(arrayList, SocialPartyType.CLOSED);
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public UpsellLoggingParameters upsellParams() {
        return new UpsellLoggingParameters("aui_v2_social_panel", SocialBundleConstants.FB_UPSELL_SEND_PARTY_INVITE, "aui_v2_social_panel", SocialBundleConstants.FB_UPSELL_SEND_PARTY_INVITE_MENU_BUTTON, "true", SocialBundleConstants.FB_UPSELL_PARTIES_PRODUCT);
    }

    public CreatePartyWith(SocialUserAdapterItem socialUserAdapterItem) {
        this.mUser = socialUserAdapterItem;
    }
}
