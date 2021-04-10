package com.oculus.panelapp.people.views.actions.oc;

import android.content.Context;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialUserFriendshipStatus;
import com.oculus.panelapp.people.OCPeopleTabletPanelApp;
import com.oculus.panelapp.people.fetchers.oc.OCPYMKFetcher;
import com.oculus.panelapp.people.views.PeopleUserAdapterItem;
import com.oculus.panelapp.people.views.PeopleViewToaster;
import com.oculus.panelapp.people.views.actions.PeopleUserActionHandler;
import com.oculus.panelapp.people.views.actions.PeopleUserActionType;
import com.oculus.socialplatform.R;

public class OCAddFriend extends OCPeopleUserAction {
    public static final String TAG = LoggingUtil.tag(OCAddFriend.class);
    public String mSource;
    public PeopleUserAdapterItem mUser;

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public ClickEventButtonId getButtonId() {
        return ClickEventButtonId.PEOPLE_TAB_USER_CARD_SEND_FRIEND_REQUEST;
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public String getTargetUserId() {
        return this.mUser.getID();
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public PeopleUserActionType getType() {
        return PeopleUserActionType.ADD_FRIEND;
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public boolean isRelevant() {
        if (this.mUser.mUser.getFriendship() == SocialUserFriendshipStatus.CAN_REQUEST) {
            return true;
        }
        return false;
    }

    @Override // com.oculus.panelapp.people.views.actions.oc.OCPeopleUserAction
    public void performAction(final OCPeopleTabletPanelApp oCPeopleTabletPanelApp, final Context context, final PeopleUserActionHandler peopleUserActionHandler) {
        HorizonContentProviderHelper.sendFriendRequest(context, this.mUser.getID(), this.mSource, new HorizonContentProviderHelper.SendFriendRequestCallback() {
            /* class com.oculus.panelapp.people.views.actions.oc.OCAddFriend.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                peopleUserActionHandler.onError("");
                Context context = context;
                PeopleViewToaster.showErrorToast(context, "oculus_mobile_social_send_friend_request_error", context.getResources().getString(R.string.people_tablet_send_friend_request_failed), OCAddFriend.TAG);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SendFriendRequestCallback
            public void onSuccess(String str) {
                OCAddFriend.this.mUser.mUser.mFriendship = SocialUserFriendshipStatus.OUTGOING_REQUEST;
                peopleUserActionHandler.onSuccess();
                OCPYMKFetcher oCPYMKFetcher = oCPeopleTabletPanelApp.mPYMKFetcher;
                oCPYMKFetcher.incrementRefCount();
                oCPYMKFetcher.notifyPeopleListObservers();
                oCPeopleTabletPanelApp.releasePYMKFetcher();
                Context context = context;
                PeopleViewToaster.showToast(context, "oculus_mobile_social_friend_request_sent", context.getResources().getString(R.string.people_tablet_send_friend_request_success, OCAddFriend.this.mUser.mUser.mAlias), "", R.drawable.oc_icon_info_filled_24, OCAddFriend.TAG);
            }
        });
    }

    public OCAddFriend(PeopleUserAdapterItem peopleUserAdapterItem, String str) {
        this.mUser = peopleUserAdapterItem;
        this.mSource = str;
    }
}
