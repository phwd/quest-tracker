package com.oculus.panelapp.people.views.actions.oc;

import android.content.Context;
import androidx.annotation.NonNull;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialUserFriendshipStatus;
import com.oculus.panelapp.people.OCPeopleTabletPanelApp;
import com.oculus.panelapp.people.fetchers.oc.OCFriendRequestFetcher;
import com.oculus.panelapp.people.fetchers.oc.OCFriendsFetcher;
import com.oculus.panelapp.people.views.PeopleUserAdapterItem;
import com.oculus.panelapp.people.views.PeopleViewToaster;
import com.oculus.panelapp.people.views.actions.PeopleUserActionHandler;
import com.oculus.panelapp.people.views.actions.PeopleUserActionType;
import com.oculus.socialplatform.R;

public class OCAcceptFriendRequest extends OCPeopleUserAction {
    public static final String TAG = LoggingUtil.tag(OCAcceptFriendRequest.class);
    @NonNull
    public PeopleUserAdapterItem mUser;

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public ClickEventButtonId getButtonId() {
        return ClickEventButtonId.PEOPLE_TAB_USER_CARD_ACCEPT_FRIEND_REQUEST;
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public String getTargetUserId() {
        return this.mUser.getID();
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public PeopleUserActionType getType() {
        return PeopleUserActionType.ACCEPT_FRIEND_REQUEST;
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
        HorizonContentProviderHelper.acceptFriendRequest(context, this.mUser.getID(), "PROFILE", new HorizonContentProviderHelper.AcceptFriendRequestCallback() {
            /* class com.oculus.panelapp.people.views.actions.oc.OCAcceptFriendRequest.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                peopleUserActionHandler.onError("");
                Context context = context;
                PeopleViewToaster.showErrorToast(context, "oculus_mobile_social_accept_friend_request_error", context.getResources().getString(R.string.people_tablet_accept_friend_request_failed), OCAcceptFriendRequest.TAG);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.AcceptFriendRequestCallback
            public void onSuccess(String str) {
                OCAcceptFriendRequest.this.mUser.mUser.mFriendship = SocialUserFriendshipStatus.ARE_FRIENDS;
                peopleUserActionHandler.onSuccess();
                OCFriendRequestFetcher oCFriendRequestFetcher = oCPeopleTabletPanelApp.mFriendRequestFetcher;
                oCFriendRequestFetcher.incrementRefCount();
                oCFriendRequestFetcher.removeUserFriendRequest(OCAcceptFriendRequest.this.mUser.mUser);
                OCFriendsFetcher oCFriendsFetcher = oCPeopleTabletPanelApp.mFriendsFetcher;
                oCFriendsFetcher.incrementRefCount();
                oCFriendsFetcher.addUserToFriends(OCAcceptFriendRequest.this.mUser.mUser);
                OCPeopleTabletPanelApp oCPeopleTabletPanelApp = oCPeopleTabletPanelApp;
                oCPeopleTabletPanelApp.releaseFriendRequestFetcher();
                oCPeopleTabletPanelApp.releaseFriendsFetcher();
            }
        });
    }

    public OCAcceptFriendRequest(@NonNull PeopleUserAdapterItem peopleUserAdapterItem) {
        this.mUser = peopleUserAdapterItem;
    }
}
