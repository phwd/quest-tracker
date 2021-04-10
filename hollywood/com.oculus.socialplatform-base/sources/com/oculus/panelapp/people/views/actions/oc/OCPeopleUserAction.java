package com.oculus.panelapp.people.views.actions.oc;

import android.content.Context;
import androidx.annotation.Nullable;
import com.oculus.panelapp.people.OCPeopleTabletPanelApp;
import com.oculus.panelapp.people.views.actions.PeopleUserAction;
import com.oculus.panelapp.people.views.actions.PeopleUserActionHandler;

public abstract class OCPeopleUserAction extends PeopleUserAction {
    public abstract void performAction(OCPeopleTabletPanelApp oCPeopleTabletPanelApp, Context context, PeopleUserActionHandler peopleUserActionHandler);

    public void execute(OCPeopleTabletPanelApp oCPeopleTabletPanelApp, Context context, @Nullable PeopleUserActionHandler peopleUserActionHandler) {
        logButtonClick(oCPeopleTabletPanelApp);
        PeopleUserActionHandler callback = getCallback(oCPeopleTabletPanelApp, peopleUserActionHandler, System.currentTimeMillis());
        if (!maybeLaunchFbLinkingUpsell(oCPeopleTabletPanelApp, callback)) {
            performAction(oCPeopleTabletPanelApp, context, callback);
        }
    }
}
