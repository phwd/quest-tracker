package com.oculus.panelapp.people.views.actions.oc;

import android.content.Context;
import android.content.res.Resources;
import androidx.annotation.NonNull;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
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

public class OCBlock extends OCPeopleUserAction {
    public static final String BLOCK_ACTION = "block";
    public static final String CANCEL_ACTION = "cancel";
    public static final String TAG = LoggingUtil.tag(OCBlock.class);
    @NonNull
    public PeopleUserAdapterItem mUser;

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public boolean isRelevant() {
        return true;
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public ClickEventButtonId getButtonId() {
        return ClickEventButtonId.PEOPLE_TAB_USER_CARD_BLOCK;
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public String getTargetUserId() {
        return this.mUser.getID();
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public PeopleUserActionType getType() {
        return PeopleUserActionType.BLOCK;
    }

    public /* synthetic */ boolean lambda$performAction$0$OCBlock(final Context context, final PeopleUserActionHandler peopleUserActionHandler, final OCPeopleTabletPanelApp oCPeopleTabletPanelApp, DialogResult dialogResult) {
        String str = dialogResult.mDialogAction;
        int hashCode = str.hashCode();
        if (hashCode != -1367724422) {
            if (hashCode == 93832333 && str.equals("block")) {
                HorizonContentProviderHelper.blockUser(context, this.mUser.getID(), new HorizonContentProviderHelper.BlockUserCallback() {
                    /* class com.oculus.panelapp.people.views.actions.oc.OCBlock.AnonymousClass1 */

                    @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
                    public void onError() {
                        peopleUserActionHandler.onError("");
                        Context context = context;
                        PeopleViewToaster.showErrorToast(context, "oculus_mobile_social_block_error", context.getResources().getString(R.string.people_tablet_block_failed), OCBlock.TAG);
                    }

                    @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BlockUserCallback
                    public void onSuccess(String str) {
                        peopleUserActionHandler.onSuccess();
                        OCFriendsFetcher oCFriendsFetcher = oCPeopleTabletPanelApp.mFriendsFetcher;
                        oCFriendsFetcher.incrementRefCount();
                        oCFriendsFetcher.removeFriend(OCBlock.this.mUser.mUser);
                        OCPeopleTabletPanelApp oCPeopleTabletPanelApp = oCPeopleTabletPanelApp;
                        oCPeopleTabletPanelApp.releaseFriendsFetcher();
                        oCPeopleTabletPanelApp.mAllConnectionsFetcher.removeFriend(OCBlock.this.mUser.mUser);
                    }
                });
                return true;
            }
        } else if (str.equals("cancel")) {
            return true;
        }
        return false;
    }

    public OCBlock(@NonNull PeopleUserAdapterItem peopleUserAdapterItem) {
        this.mUser = peopleUserAdapterItem;
    }

    @Override // com.oculus.panelapp.people.views.actions.oc.OCPeopleUserAction
    public void performAction(OCPeopleTabletPanelApp oCPeopleTabletPanelApp, Context context, PeopleUserActionHandler peopleUserActionHandler) {
        Resources resources = context.getResources();
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("people_tablet_block_modal", resources.getString(R.string.people_tablet_block_modal_title, this.mUser.mUser.mAlias), resources.getString(R.string.people_tablet_block_modal_description));
        dialogDefinitionCustom.mDialogPrimaryButton = new DialogButtonText("block", resources.getString(R.string.people_tablet_block_modal_action));
        dialogDefinitionCustom.mDialogSecondaryButton = new DialogButtonText("cancel", resources.getString(R.string.people_tablet_modal_cancel));
        dialogDefinitionCustom.mDialogControllerBackButton = new DialogButton("cancel");
        dialogDefinitionCustom.mDialogInformationBox = new DialogInformationBox(resources.getString(R.string.people_tablet_remove_friend_modal_infobox), DialogIcon.InformationBox.INFO);
        dialogDefinitionCustom.mDialogResultHandler = new DialogResultHandler(context, peopleUserActionHandler, oCPeopleTabletPanelApp) {
            /* class com.oculus.panelapp.people.views.actions.oc.$$Lambda$OCBlock$wThTexcYvvVKqFOeFivJkp9KTzY2 */
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
                return OCBlock.this.lambda$performAction$0$OCBlock(this.f$1, this.f$2, this.f$3, dialogResult);
            }
        };
        oCPeopleTabletPanelApp.mDialogManager.showDialog(dialogDefinitionCustom);
    }
}
