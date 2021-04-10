package com.oculus.panelapp.people.views.actions.fb;

import android.content.Context;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.panelapp.people.FBPeopleTabletPanelApp;
import com.oculus.panelapp.people.views.PeopleUserAdapterItem;
import com.oculus.panelapp.people.views.actions.PeopleUserActionHandler;
import com.oculus.panelapp.people.views.actions.PeopleUserActionType;

public class FBViewProfile extends FBPeopleUserAction {
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

    @Override // com.oculus.panelapp.people.views.actions.fb.FBPeopleUserAction
    public void performAction(FBPeopleTabletPanelApp fBPeopleTabletPanelApp, Context context, PeopleUserActionHandler peopleUserActionHandler) {
        fBPeopleTabletPanelApp.actionNavigateToProfile(this.mUser.getID());
    }

    public FBViewProfile(PeopleUserAdapterItem peopleUserAdapterItem) {
        this.mUser = peopleUserAdapterItem;
    }
}
