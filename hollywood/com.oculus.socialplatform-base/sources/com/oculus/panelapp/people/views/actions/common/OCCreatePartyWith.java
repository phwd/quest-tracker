package com.oculus.panelapp.people.views.actions.common;

import android.content.Context;
import android.net.Uri;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.socialtablet.deviceconfig.DeviceConfigSocialPlatformMC;
import com.oculus.horizoncontent.social.SocialUserFriendshipStatus;
import com.oculus.panelapp.people.PeopleTabletPanelApp;
import com.oculus.panelapp.people.views.PeopleUserAdapterItem;
import com.oculus.panelapp.people.views.actions.PeopleUserActionHandler;
import com.oculus.panelapp.people.views.actions.PeopleUserActionType;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.telemetry.SourceConstants;
import java.util.UUID;

public class OCCreatePartyWith extends CommonPeopleUserAction {
    public Context mContext;
    public PeopleUserAdapterItem mUser;

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public ClickEventButtonId getButtonId() {
        return ClickEventButtonId.PEOPLE_TAB_USER_CARD_CREATE_PARTY_WITH;
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public String getTargetUserId() {
        return this.mUser.getID();
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public PeopleUserActionType getType() {
        return PeopleUserActionType.CREATE_PARTY_WITH;
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public boolean isRelevant() {
        if (DeviceConfigHelper.getBoolean(this.mContext, DeviceConfigSocialPlatformMC.AUI_PEOPLE_TAB_PARTIES_KILL_SWITCH) || this.mUser.mUser.getFriendship() != SocialUserFriendshipStatus.ARE_FRIENDS) {
            return false;
        }
        return true;
    }

    @Override // com.oculus.panelapp.people.views.actions.common.CommonPeopleUserAction
    public void performAction(PeopleTabletPanelApp peopleTabletPanelApp, Context context, PeopleUserActionHandler peopleUserActionHandler) {
        peopleTabletPanelApp.actionNavigate(SystemUXRoute.SOCIAL_CREATE_VR_INVITE_DIALOG, new Uri.Builder().appendQueryParameter("thread_key", String.valueOf(this.mUser.getID())).appendQueryParameter("correlation_id", UUID.randomUUID().toString()).appendQueryParameter("source", SourceConstants.OC_PEOPLE_CREATE_PARTY_WITH_BUTTON).build().toString());
        peopleUserActionHandler.onSuccess();
    }

    public OCCreatePartyWith(PeopleUserAdapterItem peopleUserAdapterItem, Context context) {
        this.mUser = peopleUserAdapterItem;
        this.mContext = context;
    }
}
