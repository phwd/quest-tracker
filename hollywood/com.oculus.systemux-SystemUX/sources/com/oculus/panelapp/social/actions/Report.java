package com.oculus.panelapp.social.actions;

import android.content.Context;
import com.facebook.common.stringformat.StringFormatUtil;
import com.oculus.panelapp.social.SocialPanelApp;
import com.oculus.panelapp.social.SocialUserAdapterItem;
import com.oculus.panelapp.social.actions.SocialUserAction;
import com.oculus.panelapp.social.utils.UpsellLoggingParameters;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.vrshell.SystemUXRoute;

public class Report extends SocialUserAction {
    private SocialUserAdapterItem mUser;

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public boolean isRelevant() {
        return true;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public UpsellLoggingParameters upsellParams() {
        return null;
    }

    public Report(SocialUserAdapterItem socialUserAdapterItem) {
        this.mUser = socialUserAdapterItem;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public SocialUserActionType getType() {
        return SocialUserActionType.REPORT;
    }

    /* access modifiers changed from: package-private */
    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public ClickEventButtonId getButtonId(SocialUserAction.Source source) {
        if (source == SocialUserAction.Source.USER_CARD_MENU) {
            return ClickEventButtonId.AUIV2_SOCIAL_FRIENDS_CARD_REPORT;
        }
        return ClickEventButtonId.AUIV2_SOCIAL_FRIENDS_ROW_REPORT;
    }

    /* access modifiers changed from: package-private */
    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public void preformAction(SocialPanelApp socialPanelApp, Context context, SocialUserActionActionHandler socialUserActionActionHandler) {
        socialPanelApp.actionNavigate(SystemUXRoute.BLOCKANDREPORT, StringFormatUtil.formatStrLocaleSafe("%s?sdk_user_id=%s", socialPanelApp.getReturnComponent(), this.mUser.getID()));
    }
}
