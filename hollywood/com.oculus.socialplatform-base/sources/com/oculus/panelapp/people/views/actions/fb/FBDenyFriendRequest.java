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
import com.oculus.panelapp.people.views.actions.fb.FBDenyFriendRequest;
import com.oculus.panelapp.people.views.actions.fb.constants.FriendRequestResponseRef;
import com.oculus.vrshell.util.UiThreadExecutor;
import org.json.JSONObject;

public class FBDenyFriendRequest extends FBPeopleUserAction {
    public static final String TAG = LoggingUtil.tag(FBDenyFriendRequest.class);
    public FBFriendingMutator mFriendingMutator;
    public FriendRequestResponseRef mSource;
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

    @Override // com.oculus.panelapp.people.views.actions.fb.FBPeopleUserAction
    public void performAction(FBPeopleTabletPanelApp fBPeopleTabletPanelApp, Context context, final PeopleUserActionHandler peopleUserActionHandler) {
        this.mFriendingMutator.denyFriendRequest(fBPeopleTabletPanelApp.getViewerID(), getTargetUserId(), new FacebookGraphQLUtil.SuccessCallback() {
            /* class com.oculus.panelapp.people.views.actions.fb.FBDenyFriendRequest.AnonymousClass1 */

            public /* synthetic */ void lambda$callback$0$FBDenyFriendRequest$1(PeopleUserActionHandler peopleUserActionHandler) {
                FBDenyFriendRequest.this.mUser.mUser.mFriendship = SocialUserFriendshipStatus.CAN_REQUEST;
                peopleUserActionHandler.onSuccess();
            }

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.SuccessCallback
            public void callback(JSONObject jSONObject) {
                UiThreadExecutor.getInstance().execute(new Runnable(peopleUserActionHandler) {
                    /* class com.oculus.panelapp.people.views.actions.fb.$$Lambda$FBDenyFriendRequest$1$My6GyMaXwgeuAiN5gY_lR_4ZQ7A2 */
                    public final /* synthetic */ PeopleUserActionHandler f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        FBDenyFriendRequest.AnonymousClass1.this.lambda$callback$0$FBDenyFriendRequest$1(this.f$1);
                    }
                });
            }
        }, new FacebookGraphQLUtil.FailureCallback() {
            /* class com.oculus.panelapp.people.views.actions.fb.FBDenyFriendRequest.AnonymousClass2 */

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
            public void callback(Throwable th) {
                UiThreadExecutor.getInstance().execute(new Runnable() {
                    /* class com.oculus.panelapp.people.views.actions.fb.$$Lambda$FBDenyFriendRequest$2$BvmBWPwjs7t_yGgddKbG2V7QOpQ2 */

                    public final void run() {
                        PeopleUserActionHandler.this.onError("");
                    }
                });
            }
        }, this.mSource);
    }

    public FBDenyFriendRequest(PeopleUserAdapterItem peopleUserAdapterItem, FBFriendingMutator fBFriendingMutator, FriendRequestResponseRef friendRequestResponseRef) {
        this.mUser = peopleUserAdapterItem;
        this.mFriendingMutator = fBFriendingMutator;
        this.mSource = friendRequestResponseRef;
    }
}
