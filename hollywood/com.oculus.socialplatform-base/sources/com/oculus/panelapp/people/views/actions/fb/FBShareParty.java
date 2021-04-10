package com.oculus.panelapp.people.views.actions.fb;

import android.content.Context;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.socialtablet.parties.PartyNotificationHelper;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.panelapp.people.FBPeopleTabletPanelApp;
import com.oculus.panelapp.people.views.PeopleUserAdapterItem;
import com.oculus.panelapp.people.views.actions.PeopleUserActionHandler;
import com.oculus.panelapp.people.views.actions.PeopleUserActionType;
import java.util.HashMap;

public class FBShareParty extends FBPeopleUserAction {
    public static final String TAG = LoggingUtil.tag(FBShareParty.class);
    public boolean mHasLinkSharing;
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
        return PeopleUserActionType.FB_SHARE_PARTY;
    }

    @Override // com.oculus.panelapp.people.views.actions.fb.FBPeopleUserAction
    public void performAction(FBPeopleTabletPanelApp fBPeopleTabletPanelApp, final Context context, final PeopleUserActionHandler peopleUserActionHandler) {
        if (!this.mHasLinkSharing) {
            PartyNotificationHelper.getSingleton().sendLinkSharingOffToast(context, "party_link_sharing_off");
            peopleUserActionHandler.onError("party_link_sharing_off");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("message_thread_key", this.mUser.getID());
        HorizonContentProviderHelper.createOrUpdateCustomParty(context, hashMap, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.people.views.actions.fb.FBShareParty.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                peopleUserActionHandler.onError("");
                Log.e(FBShareParty.TAG, "Error adding thread to party");
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                peopleUserActionHandler.onSuccess();
                PartyNotificationHelper.getSingleton().sendFBInviteSharedConfirmationToast(context, "oc_fb_party_link_sent", FBShareParty.this.mUser.mUser.mName);
            }
        });
    }

    public FBShareParty(PeopleUserAdapterItem peopleUserAdapterItem, boolean z) {
        this.mUser = peopleUserAdapterItem;
        this.mHasLinkSharing = z;
    }
}
