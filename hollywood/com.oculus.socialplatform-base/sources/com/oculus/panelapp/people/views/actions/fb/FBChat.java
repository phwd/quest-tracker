package com.oculus.panelapp.people.views.actions.fb;

import X.AnonymousClass006;
import android.content.Context;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.panelapp.people.FBPeopleTabletPanelApp;
import com.oculus.panelapp.people.views.PeopleUserAdapterItem;
import com.oculus.panelapp.people.views.actions.PeopleUserActionHandler;
import com.oculus.panelapp.people.views.actions.PeopleUserActionType;
import com.oculus.vrshell.SystemUXRoute;

public class FBChat extends FBPeopleUserAction {
    public PeopleUserAdapterItem mUser;

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public boolean isRelevant() {
        return true;
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public ClickEventButtonId getButtonId() {
        return ClickEventButtonId.PEOPLE_TAB_USER_CARD_CHAT;
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public String getTargetUserId() {
        return this.mUser.getID();
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public PeopleUserActionType getType() {
        return PeopleUserActionType.CHAT;
    }

    @Override // com.oculus.panelapp.people.views.actions.fb.FBPeopleUserAction
    public void performAction(FBPeopleTabletPanelApp fBPeopleTabletPanelApp, Context context, PeopleUserActionHandler peopleUserActionHandler) {
        String id = this.mUser.getID();
        if (id.equals("")) {
            fBPeopleTabletPanelApp.actionNavigate(SystemUXRoute.AUI_MESSENGER, "");
        } else {
            fBPeopleTabletPanelApp.actionNavigate(SystemUXRoute.AUI_MESSENGER, AnonymousClass006.A0B("/mailbox/", fBPeopleTabletPanelApp.getViewerID(), "/thread/", id));
        }
        peopleUserActionHandler.onSuccess();
    }

    public FBChat(PeopleUserAdapterItem peopleUserAdapterItem) {
        this.mUser = peopleUserAdapterItem;
    }
}
