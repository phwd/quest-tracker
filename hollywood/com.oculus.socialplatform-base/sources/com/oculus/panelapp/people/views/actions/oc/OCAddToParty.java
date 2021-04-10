package com.oculus.panelapp.people.views.actions.oc;

import android.content.Context;
import android.util.Log;
import com.google.common.collect.ImmutableList;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.socialtablet.parties.PartyNotificationHelper;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.panelapp.people.OCPeopleTabletPanelApp;
import com.oculus.panelapp.people.model.IViewerSocialParty;
import com.oculus.panelapp.people.views.PeopleUserAdapterItem;
import com.oculus.panelapp.people.views.PeopleViewToaster;
import com.oculus.panelapp.people.views.actions.PeopleUserActionHandler;
import com.oculus.panelapp.people.views.actions.PeopleUserActionType;
import com.oculus.socialplatform.R;
import java.util.List;

public class OCAddToParty extends OCPeopleUserAction {
    public static final String TAG = LoggingUtil.tag(OCAddToParty.class);
    public IViewerSocialParty mParty;
    public PeopleUserAdapterItem mUser;

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public boolean isRelevant() {
        return true;
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public ClickEventButtonId getButtonId() {
        return ClickEventButtonId.PEOPLE_TAB_USER_CARD_INVITE_TO_PARTY;
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public String getTargetUserId() {
        return this.mUser.getID();
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public PeopleUserActionType getType() {
        return PeopleUserActionType.INVITE_TO_PARTY;
    }

    @Override // com.oculus.panelapp.people.views.actions.oc.OCPeopleUserAction
    public void performAction(final OCPeopleTabletPanelApp oCPeopleTabletPanelApp, final Context context, final PeopleUserActionHandler peopleUserActionHandler) {
        if (this.mParty.isFull()) {
            PartyNotificationHelper.getSingleton().sendViewerPartyFullToast(context, "oc_invite_to_party_full");
            peopleUserActionHandler.onError("oc_invite_to_party_full");
            return;
        }
        HorizonContentProviderHelper.inviteUsersToParty(context, ImmutableList.A06(this.mUser.getID()), this.mParty.getID(), new HorizonContentProviderHelper.MultipleIDCallback() {
            /* class com.oculus.panelapp.people.views.actions.oc.OCAddToParty.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                Context context = context;
                String string = context.getResources().getString(R.string.people_tablet_invite_failed);
                String str = OCAddToParty.TAG;
                PeopleViewToaster.showErrorToast(context, "oculus_mobile_social_party_invite_error", string, str);
                peopleUserActionHandler.onError("");
                Log.e(str, "Error inviting user to party");
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.MultipleIDCallback
            public void onSuccess(List<String> list) {
                oCPeopleTabletPanelApp.mCurrentPartyFetcher.addUserToInvitedList(OCAddToParty.this.mUser.mUser);
                peopleUserActionHandler.onSuccess();
                PartyNotificationHelper.getSingleton().sendOCInviteSentConfirmationToast(context, "oc_party_invite_sent", OCAddToParty.this.mUser.mUser.mAlias);
            }
        });
    }

    public OCAddToParty(PeopleUserAdapterItem peopleUserAdapterItem, IViewerSocialParty iViewerSocialParty) {
        this.mUser = peopleUserAdapterItem;
        this.mParty = iViewerSocialParty;
    }
}
