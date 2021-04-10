package com.oculus.panelapp.people.views.actions.common;

import android.content.Context;
import android.net.Uri;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.socialtablet.deviceconfig.DeviceConfigSocialPlatformMC;
import com.oculus.common.socialtablet.parties.PartyNotificationHelper;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.panelapp.people.PeopleTabletPanelApp;
import com.oculus.panelapp.people.views.PeopleUserAdapterItem;
import com.oculus.panelapp.people.views.actions.PeopleUserActionHandler;
import com.oculus.panelapp.people.views.actions.PeopleUserActionType;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.telemetry.SourceConstants;
import java.util.UUID;

public class OCJoinParty extends CommonPeopleUserAction {
    public PeopleUserAdapterItem mUser;

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public ClickEventButtonId getButtonId() {
        return ClickEventButtonId.PEOPLE_TAB_USER_CARD_JOIN_PARTY;
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public String getTargetUserId() {
        return this.mUser.getID();
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public PeopleUserActionType getType() {
        return PeopleUserActionType.JOIN_PARTY;
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public boolean isRelevant() {
        return this.mUser.mUser.isInJoinableParty();
    }

    @Override // com.oculus.panelapp.people.views.actions.common.CommonPeopleUserAction
    public void performAction(PeopleTabletPanelApp peopleTabletPanelApp, Context context, PeopleUserActionHandler peopleUserActionHandler) {
        Uri build;
        SystemUXRoute systemUXRoute;
        SocialUser socialUser = this.mUser.mUser;
        if (socialUser == null) {
            peopleUserActionHandler.onError("");
        } else if (socialUser.mIsPartyFull) {
            PartyNotificationHelper.getSingleton().sendFriendPartyFullToast(context, "oc_join_party_full");
            peopleUserActionHandler.onError("oc_join_party_full");
        } else {
            if (DeviceConfigHelper.getBoolean(peopleTabletPanelApp.mContext, DeviceConfigSocialPlatformMC.JOIN_PARTY_FAR_FIELD_VIEW)) {
                build = new Uri.Builder().appendPath("join_party").appendQueryParameter("party_id", this.mUser.mUser.mCurrentPartyID).appendQueryParameter("correlation_id", UUID.randomUUID().toString()).appendQueryParameter("source", SourceConstants.OC_PEOPLE_JOIN_PARTY_CARD).build();
                systemUXRoute = SystemUXRoute.SOCIAL_OVERLAY_PANEL;
            } else {
                build = new Uri.Builder().encodedPath("").appendQueryParameter("party_id", this.mUser.mUser.mCurrentPartyID).appendQueryParameter("correlation_id", UUID.randomUUID().toString()).appendQueryParameter("source", SourceConstants.OC_PEOPLE_JOIN_PARTY_CARD).build();
                systemUXRoute = SystemUXRoute.SOCIAL_CONFIRM_JOIN_PARTY;
            }
            peopleTabletPanelApp.actionNavigate(systemUXRoute, build.toString());
            peopleUserActionHandler.onSuccess();
        }
    }

    public OCJoinParty(PeopleUserAdapterItem peopleUserAdapterItem) {
        this.mUser = peopleUserAdapterItem;
    }
}
