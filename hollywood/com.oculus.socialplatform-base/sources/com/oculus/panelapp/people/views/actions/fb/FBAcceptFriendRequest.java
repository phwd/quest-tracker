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
import com.oculus.panelapp.people.views.actions.fb.FBAcceptFriendRequest;
import com.oculus.panelapp.people.views.actions.fb.constants.FriendRequestResponseRef;
import com.oculus.vrshell.util.UiThreadExecutor;
import org.json.JSONObject;

public class FBAcceptFriendRequest extends FBPeopleUserAction {
    public static final String TAG = LoggingUtil.tag(FBAcceptFriendRequest.class);
    public FBFriendingMutator mFriendingMutator;
    public FriendRequestResponseRef mSource;
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

    @Override // com.oculus.panelapp.people.views.actions.fb.FBPeopleUserAction
    public void performAction(FBPeopleTabletPanelApp fBPeopleTabletPanelApp, Context context, final PeopleUserActionHandler peopleUserActionHandler) {
        this.mFriendingMutator.acceptFriendRequest(fBPeopleTabletPanelApp.getViewerID(), getTargetUserId(), new FacebookGraphQLUtil.SuccessCallback() {
            /* class com.oculus.panelapp.people.views.actions.fb.FBAcceptFriendRequest.AnonymousClass1 */

            public /* synthetic */ void lambda$callback$0$FBAcceptFriendRequest$1(PeopleUserActionHandler peopleUserActionHandler) {
                FBAcceptFriendRequest.this.mUser.mUser.mFriendship = SocialUserFriendshipStatus.ARE_FRIENDS;
                peopleUserActionHandler.onSuccess();
            }

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.SuccessCallback
            public void callback(JSONObject jSONObject) {
                UiThreadExecutor.getInstance().execute(new Runnable(peopleUserActionHandler) {
                    /* class com.oculus.panelapp.people.views.actions.fb.$$Lambda$FBAcceptFriendRequest$1$xBQJU6Z9DqEC3AFxWWfqsMp7YOg2 */
                    public final /* synthetic */ PeopleUserActionHandler f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        FBAcceptFriendRequest.AnonymousClass1.this.lambda$callback$0$FBAcceptFriendRequest$1(this.f$1);
                    }
                });
            }
        }, new FacebookGraphQLUtil.FailureCallback() {
            /* class com.oculus.panelapp.people.views.actions.fb.FBAcceptFriendRequest.AnonymousClass2 */

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
            public void callback(Throwable th) {
                UiThreadExecutor.getInstance().execute(new Runnable() {
                    /* class com.oculus.panelapp.people.views.actions.fb.$$Lambda$FBAcceptFriendRequest$2$4KiSgr75PQzmpwz0OGvO4oibIts2 */

                    public final void run() {
                        PeopleUserActionHandler.this.onError("");
                    }
                });
            }
        }, this.mSource);
    }

    public FBAcceptFriendRequest(PeopleUserAdapterItem peopleUserAdapterItem, FBFriendingMutator fBFriendingMutator, FriendRequestResponseRef friendRequestResponseRef) {
        this.mUser = peopleUserAdapterItem;
        this.mFriendingMutator = fBFriendingMutator;
        this.mSource = friendRequestResponseRef;
    }
}
