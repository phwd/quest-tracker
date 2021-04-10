package com.oculus.panelapp.people.views.actions.fb;

import android.content.Context;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.graphql.fb.FacebookGraphQLUtil;
import com.oculus.horizoncontent.social.SocialUserFriendshipStatus;
import com.oculus.panelapp.people.FBPeopleTabletPanelApp;
import com.oculus.panelapp.people.graphql.FBFriendingMutator;
import com.oculus.panelapp.people.views.PeopleUserAdapterItem;
import com.oculus.panelapp.people.views.actions.PeopleUserActionHandler;
import com.oculus.panelapp.people.views.actions.PeopleUserActionType;
import com.oculus.panelapp.people.views.actions.fb.FBCancelFriendRequest;
import com.oculus.panelapp.people.views.actions.fb.constants.FriendRequestCancelRef;
import com.oculus.vrshell.util.UiThreadExecutor;
import org.json.JSONObject;

public class FBCancelFriendRequest extends FBPeopleUserAction {
    public static final String TAG = LoggingUtil.tag(FBCancelFriendRequest.class);
    public FBFriendingMutator mFriendingMutator;
    public FriendRequestCancelRef mSource;
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

    @Override // com.oculus.panelapp.people.views.actions.fb.FBPeopleUserAction
    public void performAction(FBPeopleTabletPanelApp fBPeopleTabletPanelApp, Context context, final PeopleUserActionHandler peopleUserActionHandler) {
        this.mFriendingMutator.cancelFriendRequest(fBPeopleTabletPanelApp.getViewerID(), this.mUser.getID(), new FacebookGraphQLUtil.SuccessCallback() {
            /* class com.oculus.panelapp.people.views.actions.fb.FBCancelFriendRequest.AnonymousClass1 */

            public /* synthetic */ void lambda$callback$0$FBCancelFriendRequest$1(PeopleUserActionHandler peopleUserActionHandler) {
                FBCancelFriendRequest.this.mUser.mUser.mFriendship = SocialUserFriendshipStatus.CAN_REQUEST;
                peopleUserActionHandler.onSuccess();
            }

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.SuccessCallback
            public void callback(JSONObject jSONObject) {
                UiThreadExecutor.getInstance().execute(new Runnable(peopleUserActionHandler) {
                    /* class com.oculus.panelapp.people.views.actions.fb.$$Lambda$FBCancelFriendRequest$1$RP5Ln8xDXrlgmfqSLgLYHwaFCOs2 */
                    public final /* synthetic */ PeopleUserActionHandler f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        FBCancelFriendRequest.AnonymousClass1.this.lambda$callback$0$FBCancelFriendRequest$1(this.f$1);
                    }
                });
            }
        }, new FacebookGraphQLUtil.FailureCallback() {
            /* class com.oculus.panelapp.people.views.actions.fb.FBCancelFriendRequest.AnonymousClass2 */

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
            public void callback(Throwable th) {
                UiThreadExecutor.getInstance().execute(new Runnable() {
                    /* class com.oculus.panelapp.people.views.actions.fb.$$Lambda$FBCancelFriendRequest$2$Wu00i1d8nhytUUBSv3rGUxvAdcE2 */

                    public final void run() {
                        PeopleUserActionHandler.this.onError("");
                    }
                });
            }
        }, this.mSource);
    }

    public FBCancelFriendRequest(PeopleUserAdapterItem peopleUserAdapterItem, FBFriendingMutator fBFriendingMutator, FriendRequestCancelRef friendRequestCancelRef) {
        this.mUser = peopleUserAdapterItem;
        this.mSource = friendRequestCancelRef;
        this.mFriendingMutator = fBFriendingMutator;
    }
}
