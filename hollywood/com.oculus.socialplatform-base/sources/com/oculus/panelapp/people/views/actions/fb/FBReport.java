package com.oculus.panelapp.people.views.actions.fb;

import android.content.Context;
import android.net.Uri;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.panelapp.people.FBPeopleTabletPanelApp;
import com.oculus.panelapp.people.views.PeopleUserAdapterItem;
import com.oculus.panelapp.people.views.actions.PeopleUserActionHandler;
import com.oculus.panelapp.people.views.actions.PeopleUserActionType;
import com.oculus.vrshell.SystemUXRoute;

public class FBReport extends FBPeopleUserAction {
    public PeopleUserAdapterItem mUser;

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public boolean isRelevant() {
        return true;
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public ClickEventButtonId getButtonId() {
        return ClickEventButtonId.PEOPLE_TAB_USER_CARD_REPORT;
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public String getTargetUserId() {
        return this.mUser.getID();
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public PeopleUserActionType getType() {
        return PeopleUserActionType.REPORT;
    }

    @Override // com.oculus.panelapp.people.views.actions.fb.FBPeopleUserAction
    public void performAction(FBPeopleTabletPanelApp fBPeopleTabletPanelApp, Context context, PeopleUserActionHandler peopleUserActionHandler) {
        fBPeopleTabletPanelApp.actionNavigate(SystemUXRoute.USER_REPORT, new Uri.Builder().encodedPath("/system_utilities/user_report/").appendPath(context.getPackageName()).appendPath("0").appendPath("fb_profile").appendQueryParameter("reportee_fbid", this.mUser.getID()).appendQueryParameter("reportee_name", this.mUser.mUser.mName).appendQueryParameter("reporter_fbid", fBPeopleTabletPanelApp.getViewerID()).build().toString());
        peopleUserActionHandler.onSuccess();
    }

    public FBReport(PeopleUserAdapterItem peopleUserAdapterItem) {
        this.mUser = peopleUserAdapterItem;
    }
}
