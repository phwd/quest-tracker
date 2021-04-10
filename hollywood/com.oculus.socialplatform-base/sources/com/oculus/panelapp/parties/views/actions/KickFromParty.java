package com.oculus.panelapp.parties.views.actions;

import android.content.Context;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.panelapp.parties.PartiesTabletPanelApp;
import com.oculus.panelapp.parties.utils.PartyUtils;
import com.oculus.panelapp.parties.views.PartyUserCardAdapterItem;
import com.oculus.socialdialogs.SocialPartyDialogs;
import com.oculus.systemdialog.DialogDefinitionBase;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;

public class KickFromParty extends PartyAction {
    public PartyUserCardAdapterItem mAdapterItem;
    public ClickEventButtonId mButtonId;
    public PartiesTabletPanelApp mPanelApp;
    public SurfaceType mSurfaceType;

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public void performAction(Context context, PartyActionHandler partyActionHandler) {
        PartyUserCardAdapterItem partyUserCardAdapterItem = this.mAdapterItem;
        SocialParty socialParty = partyUserCardAdapterItem.mParty;
        SocialUser socialUser = partyUserCardAdapterItem.mUser;
        if (socialParty != null && socialUser != null) {
            $$Lambda$KickFromParty$5ehusEQWMYoGJ17oHy8ca9Qp4M2 r2 = new DialogResultHandler(context, socialUser, socialParty, partyActionHandler) {
                /* class com.oculus.panelapp.parties.views.actions.$$Lambda$KickFromParty$5ehusEQWMYoGJ17oHy8ca9Qp4M2 */
                public final /* synthetic */ Context f$1;
                public final /* synthetic */ SocialUser f$2;
                public final /* synthetic */ SocialParty f$3;
                public final /* synthetic */ PartyActionHandler f$4;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                    this.f$4 = r5;
                }

                @Override // com.oculus.systemdialog.DialogResultHandler
                public final boolean handleDialogResult(DialogResult dialogResult) {
                    return KickFromParty.this.lambda$performAction$0$KickFromParty(this.f$1, this.f$2, this.f$3, this.f$4, dialogResult);
                }
            };
            DialogDefinitionBase partyKickDialog = SocialPartyDialogs.getPartyKickDialog(socialUser.mAlias, context.getResources());
            partyKickDialog.mDialogResultHandler = r2;
            this.mPanelApp.mDialogManager.showDialog(partyKickDialog);
        }
    }

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public PartyActionType getType() {
        return PartyActionType.KICK_FROM_PARTY;
    }

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public boolean isRelevant() {
        PartyUserCardAdapterItem partyUserCardAdapterItem = this.mAdapterItem;
        SocialParty socialParty = partyUserCardAdapterItem.mParty;
        SocialUser socialUser = partyUserCardAdapterItem.mUser;
        if (socialParty == null || socialUser == null) {
            return false;
        }
        String localUserId = this.mPanelApp.getLocalUserId();
        boolean isUserPartyLeader = socialParty.isUserPartyLeader(localUserId);
        boolean equals = localUserId.equals(socialUser.mID);
        boolean z = false;
        if (socialParty.getUserPartyMembership(socialUser) == SocialParty.PartyMembership.MEMBER) {
            z = true;
        }
        if (!isUserPartyLeader || !z || equals) {
            return false;
        }
        return true;
    }

    public /* synthetic */ boolean lambda$performAction$0$KickFromParty(Context context, SocialUser socialUser, SocialParty socialParty, PartyActionHandler partyActionHandler, DialogResult dialogResult) {
        String str = dialogResult.mDialogAction;
        int hashCode = str.hashCode();
        if (hashCode != -1367724422) {
            if (hashCode == 3291718 && str.equals(SocialPartyDialogs.KICK_ACTION)) {
                PartyUtils.kickUserFromParty(context, socialUser.mID, socialParty.mID, this.mPanelApp, this.mButtonId, this.mSurfaceType, partyActionHandler);
            }
        } else if (str.equals("cancel")) {
            return false;
        }
        return true;
    }

    public KickFromParty(PartyUserCardAdapterItem partyUserCardAdapterItem, PartiesTabletPanelApp partiesTabletPanelApp, ClickEventButtonId clickEventButtonId, SurfaceType surfaceType) {
        this.mAdapterItem = partyUserCardAdapterItem;
        this.mPanelApp = partiesTabletPanelApp;
        this.mButtonId = clickEventButtonId;
        this.mSurfaceType = surfaceType;
    }
}
