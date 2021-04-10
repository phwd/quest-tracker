package com.oculus.panelapp.social.actions;

import android.content.Context;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.panelapp.social.SocialPanelApp;
import com.oculus.panelapp.social.SocialPartyActions;
import com.oculus.panelapp.social.SocialUserAdapterItem;
import com.oculus.panelapp.social.actions.SocialUserAction;
import com.oculus.panelapp.social.utils.UpsellLoggingParameters;
import com.oculus.socialdialogs.SocialPartyDialogs;
import com.oculus.systemdialog.DialogDefinitionBase;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.tablet.logging.ClickEventButtonId;

public class KickFromParty extends SocialUserAction {
    public SocialPanelApp mPanelApp;
    public SocialUserAdapterItem mUser;

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public UpsellLoggingParameters upsellParams() {
        return null;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public SocialUserActionType getType() {
        return SocialUserActionType.KICK_FROM_PARTY;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public boolean isRelevant() {
        SocialUserAdapterItem socialUserAdapterItem = this.mUser;
        SocialParty socialParty = socialUserAdapterItem.mParty;
        SocialUser socialUser = socialUserAdapterItem.mUser;
        if (socialParty == null || socialUser == null) {
            return false;
        }
        boolean isUserPartyLeader = socialParty.isUserPartyLeader(this.mPanelApp.getLocalUserId());
        boolean z = false;
        if (socialParty.getUserPartyMembership(socialUser) == SocialParty.PartyMembership.MEMBER) {
            z = true;
        }
        boolean equals = socialUser.mUserType.equals(SocialUser.UserRowType.PARTY_MEMBER);
        if (!z || !isUserPartyLeader || !equals) {
            return false;
        }
        return true;
    }

    public /* synthetic */ boolean lambda$preformAction$0$KickFromParty(SocialParty socialParty, Context context, SocialPanelApp socialPanelApp, DialogResult dialogResult) {
        String str = dialogResult.mDialogAction;
        int hashCode = str.hashCode();
        if (hashCode != -1367724422) {
            if (hashCode == 3291718 && str.equals(SocialPartyDialogs.KICK_ACTION)) {
                new SocialPartyActions(context, socialPanelApp).kickUserFromParty(this.mUser.getID(), socialParty.mID);
            }
        } else if (str.equals("cancel")) {
            return false;
        }
        return true;
    }

    public KickFromParty(SocialUserAdapterItem socialUserAdapterItem, SocialPanelApp socialPanelApp) {
        this.mUser = socialUserAdapterItem;
        this.mPanelApp = socialPanelApp;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public void preformAction(SocialPanelApp socialPanelApp, Context context, SocialUserActionActionHandler socialUserActionActionHandler) {
        SocialParty socialParty = socialPanelApp.acquireSocialViewModel().mParty;
        if (socialParty != null) {
            $$Lambda$KickFromParty$uJKoovcXKgy9Y5rwLI3AoO2AeA2 r2 = new DialogResultHandler(socialParty, context, socialPanelApp) {
                /* class com.oculus.panelapp.social.actions.$$Lambda$KickFromParty$uJKoovcXKgy9Y5rwLI3AoO2AeA2 */
                public final /* synthetic */ SocialParty f$1;
                public final /* synthetic */ Context f$2;
                public final /* synthetic */ SocialPanelApp f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                @Override // com.oculus.systemdialog.DialogResultHandler
                public final boolean handleDialogResult(DialogResult dialogResult) {
                    return KickFromParty.this.lambda$preformAction$0$KickFromParty(this.f$1, this.f$2, this.f$3, dialogResult);
                }
            };
            DialogDefinitionBase partyKickDialog = SocialPartyDialogs.getPartyKickDialog(this.mUser.mUser.mAlias, context.getResources());
            partyKickDialog.mDialogResultHandler = r2;
            this.mPanelApp.getDialogManager().showDialog(partyKickDialog);
        }
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public ClickEventButtonId getButtonId(SocialUserAction.Source source) {
        return ClickEventButtonId.AUIV2_SOCIAL_PARTIES_PARTY_KICK_START;
    }
}
