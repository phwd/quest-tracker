package com.oculus.panelapp.social.actions;

import android.content.Context;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.panelapp.social.SocialPanelApp;
import com.oculus.panelapp.social.SocialUserAdapterItem;
import com.oculus.panelapp.social.actions.SocialUserAction;
import com.oculus.panelapp.social.utils.UpsellLoggingParameters;
import com.oculus.socialdialogs.SocialPartyDialogs;
import com.oculus.systemdialog.DialogDefinitionBase;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.tablet.logging.ClickEventButtonId;

public class KickFromParty extends SocialUserAction {
    private SocialPanelApp mPanelApp;
    private SocialUserAdapterItem mUser;

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public UpsellLoggingParameters upsellParams() {
        return null;
    }

    public KickFromParty(SocialUserAdapterItem socialUserAdapterItem, SocialPanelApp socialPanelApp) {
        this.mUser = socialUserAdapterItem;
        this.mPanelApp = socialPanelApp;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public boolean isRelevant() {
        SocialParty party = this.mUser.getParty();
        SocialUser user = this.mUser.getUser();
        if (party == null || user == null) {
            return false;
        }
        boolean isUserPartyLeader = party.isUserPartyLeader(this.mPanelApp.getLocalUserId());
        boolean z = party.getUserPartyMembership(user) == SocialParty.PartyMembership.MEMBER;
        boolean equals = user.getUserType().equals(SocialUser.UserRowType.PARTY_MEMBER);
        if (!z || !isUserPartyLeader || !equals) {
            return false;
        }
        return true;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public SocialUserActionType getType() {
        return SocialUserActionType.KICK_FROM_PARTY;
    }

    /* access modifiers changed from: package-private */
    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public ClickEventButtonId getButtonId(SocialUserAction.Source source) {
        return ClickEventButtonId.AUIV2_SOCIAL_PARTIES_PARTY_KICK_START;
    }

    /* access modifiers changed from: package-private */
    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public void preformAction(SocialPanelApp socialPanelApp, Context context, SocialUserActionActionHandler socialUserActionActionHandler) {
        SocialParty partyData = socialPanelApp.acquireSocialViewModel().getPartyData();
        if (partyData != null) {
            $$Lambda$KickFromParty$eYqqf9tQZlqhp4qmJMW8BdSTYn8 r0 = new DialogResultHandler(partyData, context, socialPanelApp) {
                /* class com.oculus.panelapp.social.actions.$$Lambda$KickFromParty$eYqqf9tQZlqhp4qmJMW8BdSTYn8 */
                private final /* synthetic */ SocialParty f$1;
                private final /* synthetic */ Context f$2;
                private final /* synthetic */ SocialPanelApp f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                @Override // com.oculus.systemdialog.DialogResultHandler
                public final boolean handleDialogResult(DialogResult dialogResult) {
                    return KickFromParty.this.lambda$preformAction$61$KickFromParty(this.f$1, this.f$2, this.f$3, dialogResult);
                }
            };
            DialogDefinitionBase partyKickDialog = SocialPartyDialogs.getPartyKickDialog(this.mUser.getUser().getAlias(), context.getResources());
            partyKickDialog.setDialogResultHandler(r0);
            this.mPanelApp.getDialogManager().showDialog(partyKickDialog);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0030  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ boolean lambda$preformAction$61$KickFromParty(com.oculus.horizoncontent.social.SocialParty r5, android.content.Context r6, com.oculus.panelapp.social.SocialPanelApp r7, com.oculus.systemdialog.DialogResult r8) {
        /*
            r4 = this;
            java.lang.String r8 = r8.getDialogAction()
            int r0 = r8.hashCode()
            r1 = -1367724422(0xffffffffae7a2e7a, float:-5.68847E-11)
            r2 = 0
            r3 = 1
            if (r0 == r1) goto L_0x001f
            r1 = 3291718(0x323a46, float:4.61268E-39)
            if (r0 == r1) goto L_0x0015
            goto L_0x0029
        L_0x0015:
            java.lang.String r0 = "kick"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0029
            r8 = r2
            goto L_0x002a
        L_0x001f:
            java.lang.String r0 = "cancel"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0029
            r8 = r3
            goto L_0x002a
        L_0x0029:
            r8 = -1
        L_0x002a:
            if (r8 == 0) goto L_0x0030
            if (r8 == r3) goto L_0x002f
            goto L_0x0042
        L_0x002f:
            return r2
        L_0x0030:
            com.oculus.panelapp.social.SocialUserAdapterItem r8 = r4.mUser
            java.lang.String r8 = r8.getID()
            java.lang.String r5 = r5.getID()
            com.oculus.panelapp.social.SocialPartyActions r0 = new com.oculus.panelapp.social.SocialPartyActions
            r0.<init>(r6, r7)
            r0.kickUserFromParty(r8, r5)
        L_0x0042:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.social.actions.KickFromParty.lambda$preformAction$61$KickFromParty(com.oculus.horizoncontent.social.SocialParty, android.content.Context, com.oculus.panelapp.social.SocialPanelApp, com.oculus.systemdialog.DialogResult):boolean");
    }
}
