package com.oculus.panelapp.parties.views.actions;

import android.content.Context;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.panelapp.parties.PartiesTabletPanelApp;
import com.oculus.panelapp.parties.utils.PartyUtils;
import com.oculus.socialdialogs.SocialPartyDialogs;
import com.oculus.systemdialog.DialogDefinitionBase;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;

public class Block extends PartyAction {
    public ClickEventButtonId mButtonId;
    public PartiesTabletPanelApp mPanelApp;
    public SurfaceType mSurfaceType;
    public SocialUser mUser;

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public PartyActionType getType() {
        return PartyActionType.BLOCK;
    }

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public boolean isRelevant() {
        SocialUser.UserRowType userRowType;
        SocialUser socialUser = this.mUser;
        if (socialUser == null || (userRowType = socialUser.mUserType) == SocialUser.UserRowType.PARTY_CONTROLS || userRowType == SocialUser.UserRowType.BLOCKED_USER || userRowType == SocialUser.UserRowType.BLOCKED_INVITED) {
            return false;
        }
        return true;
    }

    public /* synthetic */ boolean lambda$performAction$0$Block(Context context, PartyActionHandler partyActionHandler, DialogResult dialogResult) {
        String str = dialogResult.mDialogAction;
        int hashCode = str.hashCode();
        if (hashCode != -1367724422) {
            if (hashCode == 93832333 && str.equals("block")) {
                PartyUtils.blockUser(context, this.mUser.mID, this.mButtonId, this.mSurfaceType, this.mPanelApp, partyActionHandler);
            }
        } else if (str.equals("cancel")) {
            return false;
        }
        return true;
    }

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public void performAction(Context context, PartyActionHandler partyActionHandler) {
        SocialUser socialUser = this.mUser;
        if (socialUser != null) {
            DialogDefinitionBase blockDialog = SocialPartyDialogs.getBlockDialog(socialUser.mAlias, context.getResources());
            blockDialog.mDialogResultHandler = new DialogResultHandler(context, partyActionHandler) {
                /* class com.oculus.panelapp.parties.views.actions.$$Lambda$Block$sEVLoagmT0xpSbKZXB_n5hvFPMo2 */
                public final /* synthetic */ Context f$1;
                public final /* synthetic */ PartyActionHandler f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                @Override // com.oculus.systemdialog.DialogResultHandler
                public final boolean handleDialogResult(DialogResult dialogResult) {
                    return Block.this.lambda$performAction$0$Block(this.f$1, this.f$2, dialogResult);
                }
            };
            this.mPanelApp.mDialogManager.showDialog(blockDialog);
        }
    }

    public Block(SocialUser socialUser, PartiesTabletPanelApp partiesTabletPanelApp, ClickEventButtonId clickEventButtonId, SurfaceType surfaceType) {
        this.mUser = socialUser;
        this.mPanelApp = partiesTabletPanelApp;
        this.mButtonId = clickEventButtonId;
        this.mSurfaceType = surfaceType;
    }
}
