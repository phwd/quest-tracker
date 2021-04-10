package com.oculus.panelapp.social.actions;

import android.content.Context;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.panelapp.social.SocialLogger;
import com.oculus.panelapp.social.SocialPanelApp;
import com.oculus.panelapp.social.SocialUserAdapterItem;
import com.oculus.panelapp.social.SocialViewModel;
import com.oculus.panelapp.social.SocialViewWarningToaster;
import com.oculus.panelapp.social.actions.SocialUserAction;
import com.oculus.panelapp.social.utils.UpsellLoggingParameters;
import com.oculus.socialplatform.R;
import com.oculus.tablet.logging.ClickEventButtonId;
import javax.annotation.Nullable;

public class CancelPartyInvite extends SocialUserAction {
    public static final String TAG = LoggingUtil.tag(CancelPartyInvite.class);
    public SocialUserAdapterItem mUser;

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    @Nullable
    public UpsellLoggingParameters upsellParams() {
        return null;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public ClickEventButtonId getButtonId(SocialUserAction.Source source) {
        if (source == SocialUserAction.Source.USER_CARD_MENU) {
            return ClickEventButtonId.AUIV2_SOCIAL_FRIENDS_CARD_REPORT;
        }
        return ClickEventButtonId.AUIV2_SOCIAL_FRIENDS_ROW_REPORT;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public SocialUserActionType getType() {
        return SocialUserActionType.CANCEL_PARTY_INVITE;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public boolean isRelevant() {
        if (this.mUser.getPartyMembership() == SocialParty.PartyMembership.INVITED) {
            return true;
        }
        return false;
    }

    public CancelPartyInvite(SocialUserAdapterItem socialUserAdapterItem) {
        this.mUser = socialUserAdapterItem;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public void preformAction(SocialPanelApp socialPanelApp, final Context context, final SocialUserActionActionHandler socialUserActionActionHandler) {
        String str;
        final SocialViewModel acquireSocialViewModel = socialPanelApp.acquireSocialViewModel();
        SocialParty socialParty = acquireSocialViewModel.mParty;
        if (socialParty == null || (str = socialParty.mID) == null) {
            SocialLogger.logError(context, "uninvite_user_to_party", TAG, "Failed to uninvite user to party because the party was null.");
        } else {
            HorizonContentProviderHelper.cancelPartyInvite(context, this.mUser.getID(), str, new HorizonContentProviderHelper.SingleIDCallback() {
                /* class com.oculus.panelapp.social.actions.CancelPartyInvite.AnonymousClass1 */

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
                public void onError() {
                    SocialLogger.logError(context, "cancel_party_invite_to_user", CancelPartyInvite.TAG, "Failed to cancel invite");
                    socialUserActionActionHandler.onError();
                    Context context = context;
                    SocialViewWarningToaster.showToast(context, "oculus_mobile_social_party_cancel_invite_error", context.getResources().getString(R.string.anytime_tablet_social_party_uninvite_failed), CancelPartyInvite.TAG);
                }

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
                public void onSuccess(String str) {
                    socialUserActionActionHandler.onSuccess();
                    acquireSocialViewModel.loadPartyData();
                }
            });
        }
    }
}
