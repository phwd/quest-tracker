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
import com.oculus.panelapp.people.views.actions.fb.FBAddFriend;
import com.oculus.panelapp.people.views.actions.fb.constants.FriendRequestHowFound;
import com.oculus.vrshell.util.UiThreadExecutor;
import org.json.JSONObject;

public class FBAddFriend extends FBPeopleUserAction {
    public static final String TAG = LoggingUtil.tag(FBAddFriend.class);
    public FBFriendingMutator mFriendingMutator;
    public FriendRequestHowFound mSource;
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

    @Override // com.oculus.panelapp.people.views.actions.fb.FBPeopleUserAction
    public void performAction(FBPeopleTabletPanelApp fBPeopleTabletPanelApp, Context context, final PeopleUserActionHandler peopleUserActionHandler) {
        this.mFriendingMutator.addFriend(fBPeopleTabletPanelApp.getViewerID(), this.mUser.getID(), new FacebookGraphQLUtil.SuccessCallback() {
            /* class com.oculus.panelapp.people.views.actions.fb.FBAddFriend.AnonymousClass1 */

            public /* synthetic */ void lambda$callback$0$FBAddFriend$1(PeopleUserActionHandler peopleUserActionHandler) {
                FBAddFriend.this.mUser.mUser.mFriendship = SocialUserFriendshipStatus.OUTGOING_REQUEST;
                peopleUserActionHandler.onSuccess();
            }

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.SuccessCallback
            public void callback(JSONObject jSONObject) {
                UiThreadExecutor.getInstance().execute(new Runnable(peopleUserActionHandler) {
                    /* class com.oculus.panelapp.people.views.actions.fb.$$Lambda$FBAddFriend$1$tsi60tjWI53e1h7ZphnNkBCjsw2 */
                    public final /* synthetic */ PeopleUserActionHandler f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        FBAddFriend.AnonymousClass1.this.lambda$callback$0$FBAddFriend$1(this.f$1);
                    }
                });
            }
        }, new FacebookGraphQLUtil.FailureCallback() {
            /* class com.oculus.panelapp.people.views.actions.fb.FBAddFriend.AnonymousClass2 */

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
            public void callback(Throwable th) {
                UiThreadExecutor.getInstance().execute(new Runnable() {
                    /* class com.oculus.panelapp.people.views.actions.fb.$$Lambda$FBAddFriend$2$uPZMcf5pS6egu7L4hOmQxWZkDs2 */

                    public final void run() {
                        PeopleUserActionHandler.this.onError("");
                    }
                });
            }
        }, this.mSource);
    }

    public FBAddFriend(PeopleUserAdapterItem peopleUserAdapterItem, FBFriendingMutator fBFriendingMutator, FriendRequestHowFound friendRequestHowFound) {
        this.mUser = peopleUserAdapterItem;
        this.mSource = friendRequestHowFound;
        this.mFriendingMutator = fBFriendingMutator;
    }
}
