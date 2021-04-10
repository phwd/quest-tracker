package com.oculus.panelapp.social.actions;

import android.content.Context;
import android.content.res.Resources;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialUserFriendshipStatus;
import com.oculus.panelapp.people.views.actions.oc.OCUnFriend;
import com.oculus.panelapp.social.SocialLogger;
import com.oculus.panelapp.social.SocialPanelApp;
import com.oculus.panelapp.social.SocialUserAdapterItem;
import com.oculus.panelapp.social.SocialViewModel;
import com.oculus.panelapp.social.SocialViewWarningToaster;
import com.oculus.panelapp.social.actions.SocialUserAction;
import com.oculus.panelapp.social.utils.UpsellLoggingParameters;
import com.oculus.socialplatform.R;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogIcon;
import com.oculus.systemdialog.DialogInformationBox;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.tablet.logging.ClickEventButtonId;

public class UnFriend extends SocialUserAction {
    public static final String TAG = LoggingUtil.tag(UnFriend.class);
    public SocialUserAdapterItem mUser;

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public UpsellLoggingParameters upsellParams() {
        return null;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public ClickEventButtonId getButtonId(SocialUserAction.Source source) {
        if (source == SocialUserAction.Source.USER_CARD_MENU) {
            return ClickEventButtonId.AUIV2_SOCIAL_FRIENDS_CARD_UNFRIEND;
        }
        return ClickEventButtonId.AUIV2_SOCIAL_FRIENDS_ROW_UNFRIEND;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public SocialUserActionType getType() {
        return SocialUserActionType.UNFRIEND;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public boolean isRelevant() {
        return SocialUserFriendshipStatus.ARE_FRIENDS.equals(this.mUser.mUser.getFriendship());
    }

    public /* synthetic */ boolean lambda$preformAction$0$UnFriend(String str, final Context context, final SocialUserActionActionHandler socialUserActionActionHandler, final SocialViewModel socialViewModel, DialogResult dialogResult) {
        if (dialogResult.mDialogAction.equals(str)) {
            return false;
        }
        HorizonContentProviderHelper.unfriendUser(context, this.mUser.getID(), new HorizonContentProviderHelper.UnfriendUserCallback() {
            /* class com.oculus.panelapp.social.actions.UnFriend.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                socialUserActionActionHandler.onError();
                SocialLogger.logError(context, "unfriend_user", UnFriend.TAG, "Failed to unfriend user");
                Context context = context;
                SocialViewWarningToaster.showToast(context, "oculus_mobile_social_unfriend_error", context.getResources().getString(R.string.anytime_tablet_social_unfriend_failed), UnFriend.TAG);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.UnfriendUserCallback
            public void onSuccess(String str) {
                socialUserActionActionHandler.onSuccess();
                socialViewModel.removeUserOptimisticAndRefetch(str, SocialViewModel.SocialList.FRIENDS);
            }
        });
        return true;
    }

    public UnFriend(SocialUserAdapterItem socialUserAdapterItem) {
        this.mUser = socialUserAdapterItem;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public void preformAction(SocialPanelApp socialPanelApp, Context context, SocialUserActionActionHandler socialUserActionActionHandler) {
        SocialViewModel acquireSocialViewModel = socialPanelApp.acquireSocialViewModel();
        Resources resources = context.getResources();
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("anytime_tablet_social_modal_unfriend", resources.getString(R.string.anytime_tablet_social_modal_unfriend_title, this.mUser.mUser.mAlias), resources.getString(R.string.anytime_tablet_social_modal_unfriend_description, this.mUser.mUser.mAlias));
        dialogDefinitionCustom.mDialogPrimaryButton = new DialogButtonText(OCUnFriend.UNFRIEND_ACTION, resources.getString(R.string.anytime_tablet_social_modal_unfriend_action));
        dialogDefinitionCustom.mDialogSecondaryButton = new DialogButtonText("cancel", resources.getString(R.string.anytime_tablet_social_modal_cancel));
        dialogDefinitionCustom.mDialogControllerBackButton = new DialogButton("cancel");
        dialogDefinitionCustom.mDialogInformationBox = new DialogInformationBox(resources.getString(R.string.anytime_tablet_social_modal_unfriend_infobox), DialogIcon.InformationBox.INFO);
        dialogDefinitionCustom.mDialogResultHandler = new DialogResultHandler("cancel", context, socialUserActionActionHandler, acquireSocialViewModel) {
            /* class com.oculus.panelapp.social.actions.$$Lambda$UnFriend$DKLEeSJNmJjgjgOu6f3mGP5lk2 */
            public final /* synthetic */ String f$1;
            public final /* synthetic */ Context f$2;
            public final /* synthetic */ SocialUserActionActionHandler f$3;
            public final /* synthetic */ SocialViewModel f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return UnFriend.this.lambda$preformAction$0$UnFriend(this.f$1, this.f$2, this.f$3, this.f$4, dialogResult);
            }
        };
        socialPanelApp.getDialogManager().showDialog(dialogDefinitionCustom);
    }
}
