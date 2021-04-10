package com.oculus.panelapp.social.actions;

import android.content.Context;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.panelapp.social.R;
import com.oculus.panelapp.social.SocialBundleConstants;
import com.oculus.panelapp.social.SocialLogger;
import com.oculus.panelapp.social.SocialPanelApp;
import com.oculus.panelapp.social.SocialUserAdapterItem;
import com.oculus.panelapp.social.SocialViewWarningToaster;
import com.oculus.panelapp.social.actions.SocialUserAction;
import com.oculus.panelapp.social.utils.UpsellLoggingParameters;
import com.oculus.tablet.logging.ClickEventButtonId;
import java.util.ArrayList;
import java.util.List;

public class AddToParty extends SocialUserAction {
    private static final String TAG = LoggingUtil.tag(AddToParty.class);
    private SocialUserAdapterItem mUser;

    public AddToParty(SocialUserAdapterItem socialUserAdapterItem) {
        this.mUser = socialUserAdapterItem;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public boolean isRelevant() {
        SocialParty.PartyMembership partyMembership = this.mUser.getPartyMembership();
        return (partyMembership == null || partyMembership == SocialParty.PartyMembership.NONE) && this.mUser.getParty() != null;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public SocialUserActionType getType() {
        return SocialUserActionType.ADD_TO_PARTY;
    }

    /* access modifiers changed from: package-private */
    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public ClickEventButtonId getButtonId(SocialUserAction.Source source) {
        if (source == SocialUserAction.Source.USER_CARD_MENU) {
            return ClickEventButtonId.AUIV2_SOCIAL_PARTIES_CARD_FRIEND_INVITE;
        }
        return ClickEventButtonId.AUIV2_SOCIAL_PARTIES_ROW_FRIEND_INVITE;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public UpsellLoggingParameters upsellParams() {
        return new UpsellLoggingParameters("aui_v2_social_panel", SocialBundleConstants.FB_UPSELL_SEND_PARTY_INVITE, "aui_v2_social_panel", SocialBundleConstants.FB_UPSELL_SEND_PARTY_INVITE_MENU_BUTTON, SocialBundleConstants.FB_UPSELL_MUST_INTERACT, SocialBundleConstants.FB_UPSELL_PARTIES_PRODUCT);
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public void preformAction(final SocialPanelApp socialPanelApp, final Context context, final SocialUserActionActionHandler socialUserActionActionHandler) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.mUser.getID());
        SocialParty partyData = socialPanelApp.acquireSocialViewModel().getPartyData();
        if (partyData == null) {
            SocialLogger.logError(socialPanelApp, "invite_users_to_party", TAG, "Failed to invite users to party because the party was null.");
        } else {
            HorizonContentProviderHelper.inviteUsersToParty(context, arrayList, partyData.getID(), new HorizonContentProviderHelper.MultipleIDCallback() {
                /* class com.oculus.panelapp.social.actions.AddToParty.AnonymousClass1 */

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.MultipleIDCallback
                public void onSuccess(List<String> list) {
                    socialUserActionActionHandler.onSuccess();
                    socialPanelApp.acquireSocialViewModel().loadPartyData();
                }

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
                public void onError() {
                    socialUserActionActionHandler.onError();
                    SocialLogger.logError(socialPanelApp, "invite_users_to_party", AddToParty.TAG, "Failed to invite users to party");
                    socialPanelApp.acquireSocialViewModel().loadPartyData();
                    Context context = context;
                    SocialViewWarningToaster.showToast(context, "oculus_mobile_social_party_invite_error", context.getResources().getString(R.string.anytime_tablet_social_party_invite_failed), AddToParty.TAG);
                }
            });
        }
    }
}
