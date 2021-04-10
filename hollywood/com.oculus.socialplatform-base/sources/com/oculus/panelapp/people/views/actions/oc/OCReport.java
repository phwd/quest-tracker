package com.oculus.panelapp.people.views.actions.oc;

import android.content.Context;
import androidx.annotation.NonNull;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.panelapp.people.OCPeopleTabletPanelApp;
import com.oculus.panelapp.people.views.PeopleUserAdapterItem;
import com.oculus.panelapp.people.views.actions.PeopleUserActionHandler;
import com.oculus.panelapp.people.views.actions.PeopleUserActionType;
import com.oculus.vrshell.SystemUXRoute;

public class OCReport extends OCPeopleUserAction {
    public static final String TAG = LoggingUtil.tag(OCReport.class);
    @NonNull
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

    @Override // com.oculus.panelapp.people.views.actions.oc.OCPeopleUserAction
    public void performAction(OCPeopleTabletPanelApp oCPeopleTabletPanelApp, Context context, PeopleUserActionHandler peopleUserActionHandler) {
        oCPeopleTabletPanelApp.actionNavigate(SystemUXRoute.BLOCKANDREPORT, String.format(null, "%s?sdk_user_id=%s", "com.oculus.vrshell", this.mUser.getID()));
    }

    public OCReport(@NonNull PeopleUserAdapterItem peopleUserAdapterItem) {
        this.mUser = peopleUserAdapterItem;
    }
}
