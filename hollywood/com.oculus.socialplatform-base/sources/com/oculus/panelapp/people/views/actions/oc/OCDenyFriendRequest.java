package com.oculus.panelapp.people.views.actions.oc;

import android.content.Context;
import androidx.annotation.NonNull;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialUserFriendshipStatus;
import com.oculus.panelapp.people.OCPeopleTabletPanelApp;
import com.oculus.panelapp.people.fetchers.oc.OCFriendRequestFetcher;
import com.oculus.panelapp.people.views.PeopleUserAdapterItem;
import com.oculus.panelapp.people.views.PeopleViewToaster;
import com.oculus.panelapp.people.views.actions.PeopleUserActionHandler;
import com.oculus.panelapp.people.views.actions.PeopleUserActionType;
import com.oculus.socialplatform.R;

public class OCDenyFriendRequest extends OCPeopleUserAction {
    public static final String TAG = LoggingUtil.tag(OCDenyFriendRequest.class);
    @NonNull
    public PeopleUserAdapterItem mUser;

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public ClickEventButtonId getButtonId() {
        return ClickEventButtonId.PEOPLE_TAB_USER_CARD_DENY_FRIEND_REQUEST;
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public String getTargetUserId() {
        return this.mUser.getID();
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public PeopleUserActionType getType() {
        return PeopleUserActionType.DENY_FRIEND_REQUEST;
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public boolean isRelevant() {
        if (this.mUser.mUser.getFriendship() == SocialUserFriendshipStatus.INCOMING_REQUEST) {
            return true;
        }
        return false;
    }

    @Override // com.oculus.panelapp.people.views.actions.oc.OCPeopleUserAction
    public void performAction(final OCPeopleTabletPanelApp oCPeopleTabletPanelApp, final Context context, final PeopleUserActionHandler peopleUserActionHandler) {
        HorizonContentProviderHelper.rejectFriendRequest(context, this.mUser.getID(), new HorizonContentProviderHelper.RejectFriendRequestCallback() {
            /* class com.oculus.panelapp.people.views.actions.oc.OCDenyFriendRequest.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                peopleUserActionHandler.onError("");
                Context context = context;
                PeopleViewToaster.showErrorToast(context, "people_tablet_reject_friend_request_failed", context.getResources().getString(R.string.people_tablet_accept_friend_request_failed), OCDenyFriendRequest.TAG);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.RejectFriendRequestCallback
            public void onSuccess(String str) {
                OCDenyFriendRequest.this.mUser.mUser.mFriendship = SocialUserFriendshipStatus.CAN_REQUEST;
                peopleUserActionHandler.onSuccess();
                OCFriendRequestFetcher oCFriendRequestFetcher = oCPeopleTabletPanelApp.mFriendRequestFetcher;
                oCFriendRequestFetcher.incrementRefCount();
                oCFriendRequestFetcher.removeUserFriendRequest(OCDenyFriendRequest.this.mUser.mUser);
                oCPeopleTabletPanelApp.releaseFriendRequestFetcher();
            }
        });
    }

    public OCDenyFriendRequest(@NonNull PeopleUserAdapterItem peopleUserAdapterItem) {
        this.mUser = peopleUserAdapterItem;
    }
}
