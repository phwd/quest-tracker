package com.oculus.panelapp.social.actions;

import android.content.Context;
import android.content.res.Resources;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
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
import javax.annotation.Nullable;

public class Block extends SocialUserAction {
    public static final String TAG = LoggingUtil.tag(Block.class);
    public SocialUserAdapterItem mUser;

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public boolean isRelevant() {
        return true;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public void preformAction(SocialPanelApp socialPanelApp, Context context, SocialUserActionActionHandler socialUserActionActionHandler) {
        SocialViewModel acquireSocialViewModel = socialPanelApp.acquireSocialViewModel();
        Resources resources = context.getResources();
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("anytime_tablet_social_modal_block", resources.getString(R.string.anytime_tablet_social_modal_block_title, this.mUser.mUser.mAlias), resources.getString(R.string.anytime_tablet_social_modal_block_description));
        dialogDefinitionCustom.mDialogPrimaryButton = new DialogButtonText("block", resources.getString(R.string.anytime_tablet_social_modal_block_action));
        dialogDefinitionCustom.mDialogSecondaryButton = new DialogButtonText("cancel", resources.getString(R.string.anytime_tablet_social_modal_cancel));
        dialogDefinitionCustom.mDialogControllerBackButton = new DialogButton("cancel");
        dialogDefinitionCustom.mDialogInformationBox = new DialogInformationBox(resources.getString(R.string.anytime_tablet_social_modal_unfriend_infobox), DialogIcon.InformationBox.INFO);
        dialogDefinitionCustom.mDialogResultHandler = new DialogResultHandler("cancel", socialPanelApp, context, socialUserActionActionHandler, acquireSocialViewModel) {
            /* class com.oculus.panelapp.social.actions.$$Lambda$Block$Iyocgdid8sD6ZyqyWlgCCO5QMMQ2 */
            public final /* synthetic */ String f$1;
            public final /* synthetic */ SocialPanelApp f$2;
            public final /* synthetic */ Context f$3;
            public final /* synthetic */ SocialUserActionActionHandler f$4;
            public final /* synthetic */ SocialViewModel f$5;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
                this.f$5 = r6;
            }

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return Block.this.lambda$preformAction$0$Block(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, dialogResult);
            }
        };
        socialPanelApp.getDialogManager().showDialog(dialogDefinitionCustom);
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    @Nullable
    public UpsellLoggingParameters upsellParams() {
        return null;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public SocialUserActionType getType() {
        return SocialUserActionType.BLOCK;
    }

    public /* synthetic */ boolean lambda$preformAction$0$Block(String str, SocialPanelApp socialPanelApp, final Context context, final SocialUserActionActionHandler socialUserActionActionHandler, final SocialViewModel socialViewModel, DialogResult dialogResult) {
        if (!dialogResult.mDialogAction.equals(str)) {
            socialPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_FRIENDS_BLOCK_DIALOG_CONFIRM);
            HorizonContentProviderHelper.blockUser(context, this.mUser.getID(), new HorizonContentProviderHelper.BlockUserCallback() {
                /* class com.oculus.panelapp.social.actions.Block.AnonymousClass1 */

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
                public void onError() {
                    socialUserActionActionHandler.onError();
                    SocialLogger.logError(context, "block_user", Block.TAG, "Failed to block user");
                    Context context = context;
                    SocialViewWarningToaster.showToast(context, "oculus_mobile_social_block_error", context.getResources().getString(R.string.anytime_tablet_social_block_failed), Block.TAG);
                }

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BlockUserCallback
                public void onSuccess(String str) {
                    socialUserActionActionHandler.onSuccess();
                    socialViewModel.removeUserOptimisticAndRefetch(str, SocialViewModel.SocialList.FRIENDS);
                }
            });
            return true;
        }
        socialPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_FRIENDS_BLOCK_DIALOG_CANCEL);
        return false;
    }

    public Block(SocialUserAdapterItem socialUserAdapterItem) {
        this.mUser = socialUserAdapterItem;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public ClickEventButtonId getButtonId(SocialUserAction.Source source) {
        return ClickEventButtonId.AUIV2_SOCIAL_FRIENDS_BLOCK_DIALOG_OPEN;
    }
}
