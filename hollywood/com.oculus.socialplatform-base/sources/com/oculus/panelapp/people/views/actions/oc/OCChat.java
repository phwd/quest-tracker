package com.oculus.panelapp.people.views.actions.oc;

import android.content.Context;
import android.net.Uri;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.panelapp.people.OCPeopleTabletPanelApp;
import com.oculus.panelapp.people.views.PeopleUserAdapterItem;
import com.oculus.panelapp.people.views.actions.PeopleUserActionHandler;
import com.oculus.panelapp.people.views.actions.PeopleUserActionType;
import com.oculus.vrshell.SystemUXRoute;

public class OCChat extends OCPeopleUserAction {
    public PeopleUserAdapterItem mUser;

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

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public boolean isRelevant() {
        return this.mUser.mUser.mCanViewerMessage;
    }

    @Override // com.oculus.panelapp.people.views.actions.oc.OCPeopleUserAction
    public void performAction(OCPeopleTabletPanelApp oCPeopleTabletPanelApp, Context context, PeopleUserActionHandler peopleUserActionHandler) {
        String id = this.mUser.getID();
        if (id.equals("")) {
            oCPeopleTabletPanelApp.actionNavigate(SystemUXRoute.AUI_CHATS, "");
        } else {
            oCPeopleTabletPanelApp.actionNavigate(SystemUXRoute.AUI_CHATS, new Uri.Builder().encodedPath("").appendPath("mailbox").appendPath(oCPeopleTabletPanelApp.getViewerID()).appendPath("thread").appendPath(id).toString());
        }
        peopleUserActionHandler.onSuccess();
    }

    public OCChat(PeopleUserAdapterItem peopleUserAdapterItem) {
        this.mUser = peopleUserAdapterItem;
    }
}
