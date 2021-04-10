package com.oculus.panelapp.social.actions;

import android.content.Context;
import android.content.res.Resources;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialUserFriendshipStatus;
import com.oculus.panelapp.social.R;
import com.oculus.panelapp.social.SocialLogger;
import com.oculus.panelapp.social.SocialPanelApp;
import com.oculus.panelapp.social.SocialUserAdapterItem;
import com.oculus.panelapp.social.SocialViewModel;
import com.oculus.panelapp.social.SocialViewWarningToaster;
import com.oculus.panelapp.social.actions.SocialUserAction;
import com.oculus.panelapp.social.utils.UpsellLoggingParameters;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogIcon;
import com.oculus.systemdialog.DialogInformationBox;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.tablet.logging.ClickEventButtonId;

public class UnFriend extends SocialUserAction {
    private static final String TAG = LoggingUtil.tag(UnFriend.class);
    private SocialUserAdapterItem mUser;

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public UpsellLoggingParameters upsellParams() {
        return null;
    }

    public UnFriend(SocialUserAdapterItem socialUserAdapterItem) {
        this.mUser = socialUserAdapterItem;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public boolean isRelevant() {
        return SocialUserFriendshipStatus.ARE_FRIENDS.equals(this.mUser.getUser().getFriendship());
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public SocialUserActionType getType() {
        return SocialUserActionType.UNFRIEND;
    }

    /* access modifiers changed from: package-private */
    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public ClickEventButtonId getButtonId(SocialUserAction.Source source) {
        if (source == SocialUserAction.Source.USER_CARD_MENU) {
            return ClickEventButtonId.AUIV2_SOCIAL_FRIENDS_CARD_UNFRIEND;
        }
        return ClickEventButtonId.AUIV2_SOCIAL_FRIENDS_ROW_UNFRIEND;
    }

    /* access modifiers changed from: package-private */
    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public void preformAction(SocialPanelApp socialPanelApp, Context context, SocialUserActionActionHandler socialUserActionActionHandler) {
        SocialViewModel acquireSocialViewModel = socialPanelApp.acquireSocialViewModel();
        Resources resources = context.getResources();
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("anytime_tablet_social_modal_unfriend", resources.getString(R.string.anytime_tablet_social_modal_unfriend_title, this.mUser.getUser().getAlias()), resources.getString(R.string.anytime_tablet_social_modal_unfriend_description, this.mUser.getUser().getAlias()));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText("unfriend", resources.getString(R.string.anytime_tablet_social_modal_unfriend_action)));
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("cancel", resources.getString(R.string.anytime_tablet_social_modal_cancel)));
        dialogDefinitionCustom.setControllerBackButton(new DialogButton("cancel"));
        dialogDefinitionCustom.setInformationBox(new DialogInformationBox(resources.getString(R.string.anytime_tablet_social_modal_unfriend_infobox), DialogIcon.InformationBox.INFO));
        dialogDefinitionCustom.setDialogResultHandler(new DialogResultHandler("cancel", context, socialUserActionActionHandler, acquireSocialViewModel, socialPanelApp) {
            /* class com.oculus.panelapp.social.actions.$$Lambda$UnFriend$LorPzc_3WCCGO67nZftO_njOo4 */
            private final /* synthetic */ String f$1;
            private final /* synthetic */ Context f$2;
            private final /* synthetic */ SocialUserActionActionHandler f$3;
            private final /* synthetic */ SocialViewModel f$4;
            private final /* synthetic */ SocialPanelApp f$5;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
                this.f$5 = r6;
            }

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return UnFriend.this.lambda$preformAction$62$UnFriend(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, dialogResult);
            }
        });
        socialPanelApp.getDialogManager().showDialog(dialogDefinitionCustom);
    }

    public /* synthetic */ boolean lambda$preformAction$62$UnFriend(String str, final Context context, final SocialUserActionActionHandler socialUserActionActionHandler, final SocialViewModel socialViewModel, final SocialPanelApp socialPanelApp, DialogResult dialogResult) {
        if (dialogResult.getDialogAction().equals(str)) {
            return false;
        }
        HorizonContentProviderHelper.unfriendUser(context, this.mUser.getID(), new HorizonContentProviderHelper.UnfriendUserCallback() {
            /* class com.oculus.panelapp.social.actions.UnFriend.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.UnfriendUserCallback
            public void onSuccess(String str) {
                socialUserActionActionHandler.onSuccess();
                socialViewModel.removeUserOptimisticAndRefetch(str, SocialViewModel.SocialList.FRIENDS);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                socialUserActionActionHandler.onError();
                SocialLogger.logError(socialPanelApp, "unfriend_user", UnFriend.TAG, "Failed to unfriend user");
                Context context = context;
                SocialViewWarningToaster.showToast(context, "oculus_mobile_social_unfriend_error", context.getResources().getString(R.string.anytime_tablet_social_unfriend_failed), UnFriend.TAG);
            }
        });
        return true;
    }
}
