package com.oculus.panelapp.people.views.actions.oc;

import android.content.Context;
import android.content.res.Resources;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.horizoncontent.social.SocialUserFriendshipStatus;
import com.oculus.panelapp.people.OCPeopleTabletPanelApp;
import com.oculus.panelapp.people.fetchers.oc.OCFriendsFetcher;
import com.oculus.panelapp.people.views.PeopleUserAdapterItem;
import com.oculus.panelapp.people.views.PeopleViewToaster;
import com.oculus.panelapp.people.views.actions.PeopleUserActionHandler;
import com.oculus.panelapp.people.views.actions.PeopleUserActionType;
import com.oculus.socialplatform.R;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogIcon;
import com.oculus.systemdialog.DialogInformationBox;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;

public class OCUnFriend extends OCPeopleUserAction {
    public static final String CANCEL_ACTION = "cancel";
    public static final String TAG = LoggingUtil.tag(OCUnFriend.class);
    public static final String UNFRIEND_ACTION = "unfriend";
    public PeopleUserAdapterItem mUser;

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public ClickEventButtonId getButtonId() {
        return ClickEventButtonId.PEOPLE_TAB_USER_CARD_UNFRIEND;
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public String getTargetUserId() {
        return this.mUser.getID();
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public PeopleUserActionType getType() {
        return PeopleUserActionType.UNFRIEND;
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public boolean isRelevant() {
        return SocialUserFriendshipStatus.ARE_FRIENDS.equals(this.mUser.mUser.getFriendship());
    }

    public /* synthetic */ boolean lambda$performAction$0$OCUnFriend(final Context context, final PeopleUserActionHandler peopleUserActionHandler, final OCPeopleTabletPanelApp oCPeopleTabletPanelApp, DialogResult dialogResult) {
        String str = dialogResult.mDialogAction;
        int hashCode = str.hashCode();
        if (hashCode != -1367724422) {
            if (hashCode == -379780489 && str.equals(UNFRIEND_ACTION)) {
                HorizonContentProviderHelper.unfriendUser(context, this.mUser.getID(), new HorizonContentProviderHelper.UnfriendUserCallback() {
                    /* class com.oculus.panelapp.people.views.actions.oc.OCUnFriend.AnonymousClass1 */

                    @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
                    public void onError() {
                        peopleUserActionHandler.onError("");
                        Context context = context;
                        PeopleViewToaster.showErrorToast(context, "oculus_mobile_social_unfriend_error", context.getResources().getString(R.string.people_tablet_unfriend_failed), OCUnFriend.TAG);
                    }

                    @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.UnfriendUserCallback
                    public void onSuccess(String str) {
                        peopleUserActionHandler.onSuccess();
                        SocialUser socialUser = OCUnFriend.this.mUser.mUser;
                        socialUser.mFriendship = SocialUserFriendshipStatus.CAN_REQUEST;
                        OCFriendsFetcher oCFriendsFetcher = oCPeopleTabletPanelApp.mFriendsFetcher;
                        oCFriendsFetcher.incrementRefCount();
                        oCFriendsFetcher.removeFriend(socialUser);
                        OCPeopleTabletPanelApp oCPeopleTabletPanelApp = oCPeopleTabletPanelApp;
                        oCPeopleTabletPanelApp.releaseFriendsFetcher();
                        oCPeopleTabletPanelApp.mAllConnectionsFetcher.removeFriend(OCUnFriend.this.mUser.mUser);
                    }
                });
                return true;
            }
        } else if (str.equals("cancel")) {
            return true;
        }
        return false;
    }

    public OCUnFriend(PeopleUserAdapterItem peopleUserAdapterItem) {
        this.mUser = peopleUserAdapterItem;
    }

    @Override // com.oculus.panelapp.people.views.actions.oc.OCPeopleUserAction
    public void performAction(OCPeopleTabletPanelApp oCPeopleTabletPanelApp, Context context, PeopleUserActionHandler peopleUserActionHandler) {
        Resources resources = context.getResources();
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("people_tablet_unfriend_modal", resources.getString(R.string.people_tablet_unfriend_modal_title, this.mUser.mUser.mAlias), resources.getString(R.string.people_tablet_unfriend_modal_description, this.mUser.mUser.mAlias));
        dialogDefinitionCustom.mDialogPrimaryButton = new DialogButtonText(UNFRIEND_ACTION, resources.getString(R.string.people_tablet_unfriend_modal_action));
        dialogDefinitionCustom.mDialogSecondaryButton = new DialogButtonText("cancel", resources.getString(R.string.people_tablet_modal_cancel));
        dialogDefinitionCustom.mDialogControllerBackButton = new DialogButton("cancel");
        dialogDefinitionCustom.mDialogInformationBox = new DialogInformationBox(resources.getString(R.string.people_tablet_remove_friend_modal_infobox), DialogIcon.InformationBox.INFO);
        dialogDefinitionCustom.mDialogResultHandler = new DialogResultHandler(context, peopleUserActionHandler, oCPeopleTabletPanelApp) {
            /* class com.oculus.panelapp.people.views.actions.oc.$$Lambda$OCUnFriend$cHazNNFpMed5i6QYBzmBd58ogI2 */
            public final /* synthetic */ Context f$1;
            public final /* synthetic */ PeopleUserActionHandler f$2;
            public final /* synthetic */ OCPeopleTabletPanelApp f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return OCUnFriend.this.lambda$performAction$0$OCUnFriend(this.f$1, this.f$2, this.f$3, dialogResult);
            }
        };
        oCPeopleTabletPanelApp.mDialogManager.showDialog(dialogDefinitionCustom);
    }
}
