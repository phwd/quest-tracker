package com.oculus.panelapp.people.views.actions.common;

import android.content.Context;
import androidx.annotation.Nullable;
import com.oculus.panelapp.people.PeopleTabletPanelApp;
import com.oculus.panelapp.people.views.actions.PeopleUserAction;
import com.oculus.panelapp.people.views.actions.PeopleUserActionHandler;

public abstract class CommonPeopleUserAction extends PeopleUserAction {
    public abstract void performAction(PeopleTabletPanelApp peopleTabletPanelApp, Context context, PeopleUserActionHandler peopleUserActionHandler);

    public void execute(PeopleTabletPanelApp peopleTabletPanelApp, Context context, @Nullable PeopleUserActionHandler peopleUserActionHandler) {
        logButtonClick(peopleTabletPanelApp);
        PeopleUserActionHandler callback = getCallback(peopleTabletPanelApp, peopleUserActionHandler, System.currentTimeMillis());
        if (!maybeLaunchFbLinkingUpsell(peopleTabletPanelApp, callback)) {
            performAction(peopleTabletPanelApp, context, callback);
        }
    }
}
