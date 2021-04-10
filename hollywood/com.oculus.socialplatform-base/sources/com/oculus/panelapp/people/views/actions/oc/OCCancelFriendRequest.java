package com.oculus.panelapp.people.views.actions.oc;

import android.content.Context;
import androidx.annotation.NonNull;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialUserFriendshipStatus;
import com.oculus.panelapp.people.OCPeopleTabletPanelApp;
import com.oculus.panelapp.people.views.PeopleUserAdapterItem;
import com.oculus.panelapp.people.views.PeopleViewToaster;
import com.oculus.panelapp.people.views.actions.PeopleUserActionHandler;
import com.oculus.panelapp.people.views.actions.PeopleUserActionType;
import com.oculus.socialplatform.R;

public class OCCancelFriendRequest extends OCPeopleUserAction {
    public static final String TAG = LoggingUtil.tag(OCCancelFriendRequest.class);
    public String mSource;
    @NonNull
    public PeopleUserAdapterItem mUser;

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public ClickEventButtonId getButtonId() {
        return ClickEventButtonId.PEOPLE_TAB_USER_CARD_CANCEL_FRIEND_REQUEST;
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public String getTargetUserId() {
        return this.mUser.getID();
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public PeopleUserActionType getType() {
        return PeopleUserActionType.CANCEL_FRIEND_REQUEST;
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public boolean isRelevant() {
        if (this.mUser.mUser.getFriendship() == SocialUserFriendshipStatus.OUTGOING_REQUEST) {
            return true;
        }
        return false;
    }

    @Override // com.oculus.panelapp.people.views.actions.oc.OCPeopleUserAction
    public void performAction(OCPeopleTabletPanelApp oCPeopleTabletPanelApp, final Context context, final PeopleUserActionHandler peopleUserActionHandler) {
        HorizonContentProviderHelper.cancelFriendRequest(context, this.mUser.getID(), this.mSource, new HorizonContentProviderHelper.CancelFriendRequestCallback() {
            /* class com.oculus.panelapp.people.views.actions.oc.OCCancelFriendRequest.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                peopleUserActionHandler.onError("");
                Context context = context;
                PeopleViewToaster.showErrorToast(context, "oculus_mobile_social_cancel_friend_request_error", context.getResources().getString(R.string.people_tablet_cancel_friend_request_failed), OCCancelFriendRequest.TAG);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.CancelFriendRequestCallback
            public void onSuccess(String str) {
                OCCancelFriendRequest.this.mUser.mUser.mFriendship = SocialUserFriendshipStatus.CAN_REQUEST;
                peopleUserActionHandler.onSuccess();
            }
        });
    }

    public OCCancelFriendRequest(PeopleUserAdapterItem peopleUserAdapterItem, String str) {
        this.mUser = peopleUserAdapterItem;
        this.mSource = str;
    }
}
