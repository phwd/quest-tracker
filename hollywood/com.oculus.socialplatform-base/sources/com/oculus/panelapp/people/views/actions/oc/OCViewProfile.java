package com.oculus.panelapp.people.views.actions.oc;

import android.content.Context;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.panelapp.people.OCPeopleTabletPanelApp;
import com.oculus.panelapp.people.views.PeopleUserAdapterItem;
import com.oculus.panelapp.people.views.actions.PeopleUserActionHandler;
import com.oculus.panelapp.people.views.actions.PeopleUserActionType;

public class OCViewProfile extends OCPeopleUserAction {
    public PeopleUserAdapterItem mUser;

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public boolean isRelevant() {
        return true;
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public ClickEventButtonId getButtonId() {
        return ClickEventButtonId.PEOPLE_TAB_USER_CARD_VIEW_PROFILE;
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public String getTargetUserId() {
        return this.mUser.getID();
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public PeopleUserActionType getType() {
        return PeopleUserActionType.VIEW_PROFILE;
    }

    @Override // com.oculus.panelapp.people.views.actions.oc.OCPeopleUserAction
    public void performAction(OCPeopleTabletPanelApp oCPeopleTabletPanelApp, Context context, PeopleUserActionHandler peopleUserActionHandler) {
        oCPeopleTabletPanelApp.actionNavigateToProfile(this.mUser.getID());
    }

    public OCViewProfile(PeopleUserAdapterItem peopleUserAdapterItem) {
        this.mUser = peopleUserAdapterItem;
    }
}
