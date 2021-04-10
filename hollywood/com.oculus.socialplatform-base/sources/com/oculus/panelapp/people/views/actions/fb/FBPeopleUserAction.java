package com.oculus.panelapp.people.views.actions.fb;

import android.content.Context;
import androidx.annotation.Nullable;
import com.oculus.panelapp.people.FBPeopleTabletPanelApp;
import com.oculus.panelapp.people.views.actions.PeopleUserAction;
import com.oculus.panelapp.people.views.actions.PeopleUserActionHandler;

public abstract class FBPeopleUserAction extends PeopleUserAction {
    public abstract void performAction(FBPeopleTabletPanelApp fBPeopleTabletPanelApp, Context context, PeopleUserActionHandler peopleUserActionHandler);

    public void execute(FBPeopleTabletPanelApp fBPeopleTabletPanelApp, Context context, @Nullable PeopleUserActionHandler peopleUserActionHandler) {
        logButtonClick(fBPeopleTabletPanelApp);
        performAction(fBPeopleTabletPanelApp, context, getCallback(fBPeopleTabletPanelApp, peopleUserActionHandler, System.currentTimeMillis()));
    }
}
