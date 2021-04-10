package com.oculus.panelapp.people.views.actions.fb;

import android.content.Context;
import android.net.Uri;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.panelapp.people.FBPeopleTabletPanelApp;
import com.oculus.panelapp.people.views.PeopleUserAdapterItem;
import com.oculus.panelapp.people.views.actions.PeopleUserActionHandler;
import com.oculus.panelapp.people.views.actions.PeopleUserActionType;
import com.oculus.vrshell.SystemUXRoute;

public class FBBlockOnMessenger extends FBPeopleUserAction {
    public PeopleUserAdapterItem mUser;

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public boolean isRelevant() {
        return true;
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public ClickEventButtonId getButtonId() {
        return ClickEventButtonId.PEOPLE_TAB_USER_CARD_BLOCK_ON_MESSENGER;
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public String getTargetUserId() {
        return this.mUser.getID();
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public PeopleUserActionType getType() {
        return PeopleUserActionType.BLOCK;
    }

    @Override // com.oculus.panelapp.people.views.actions.fb.FBPeopleUserAction
    public void performAction(FBPeopleTabletPanelApp fBPeopleTabletPanelApp, Context context, PeopleUserActionHandler peopleUserActionHandler) {
        fBPeopleTabletPanelApp.actionNavigate(Uri.parse(SystemUXRoute.MESSENGER_INTEGRITY.path()).buildUpon().appendQueryParameter("user_fbid", fBPeopleTabletPanelApp.getViewerID()).appendQueryParameter("target_fbid", this.mUser.getID()).appendQueryParameter("target_name", this.mUser.mUser.mName).build().toString());
        peopleUserActionHandler.onSuccess();
    }

    public FBBlockOnMessenger(PeopleUserAdapterItem peopleUserAdapterItem) {
        this.mUser = peopleUserAdapterItem;
    }
}
