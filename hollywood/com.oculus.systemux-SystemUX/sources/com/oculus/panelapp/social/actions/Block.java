package com.oculus.panelapp.social.actions;

import android.content.Context;
import android.content.res.Resources;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.panelapp.social.R;
import com.oculus.panelapp.social.SocialLogger;
import com.oculus.panelapp.social.SocialPanelApp;
import com.oculus.panelapp.social.SocialUserAdapterItem;
import com.oculus.panelapp.social.SocialViewModel;
import com.oculus.panelapp.social.SocialViewWarningToaster;
import com.oculus.panelapp.social.actions.SocialUserAction;
import com.oculus.panelapp.social.utils.UpsellLoggingParameters;
import com.oculus.socialdialogs.SocialPartyDialogs;
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
    private static final String TAG = LoggingUtil.tag(Block.class);
    private SocialUserAdapterItem mUser;

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public boolean isRelevant() {
        return true;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    @Nullable
    public UpsellLoggingParameters upsellParams() {
        return null;
    }

    public Block(SocialUserAdapterItem socialUserAdapterItem) {
        this.mUser = socialUserAdapterItem;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public SocialUserActionType getType() {
        return SocialUserActionType.BLOCK;
    }

    /* access modifiers changed from: package-private */
    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public ClickEventButtonId getButtonId(SocialUserAction.Source source) {
        return ClickEventButtonId.AUIV2_SOCIAL_FRIENDS_BLOCK_DIALOG_OPEN;
    }

    /* access modifiers changed from: package-private */
    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public void preformAction(SocialPanelApp socialPanelApp, Context context, SocialUserActionActionHandler socialUserActionActionHandler) {
        SocialViewModel acquireSocialViewModel = socialPanelApp.acquireSocialViewModel();
        Resources resources = context.getResources();
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("anytime_tablet_social_modal_block", resources.getString(R.string.anytime_tablet_social_modal_block_title, this.mUser.getUser().getAlias()), resources.getString(R.string.anytime_tablet_social_modal_block_description));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(SocialPartyDialogs.BLOCK_ACTION, resources.getString(R.string.anytime_tablet_social_modal_block_action)));
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("cancel", resources.getString(R.string.anytime_tablet_social_modal_cancel)));
        dialogDefinitionCustom.setControllerBackButton(new DialogButton("cancel"));
        dialogDefinitionCustom.setInformationBox(new DialogInformationBox(resources.getString(R.string.anytime_tablet_social_modal_unfriend_infobox), DialogIcon.InformationBox.INFO));
        dialogDefinitionCustom.setDialogResultHandler(new DialogResultHandler("cancel", socialPanelApp, context, socialUserActionActionHandler, acquireSocialViewModel) {
            /* class com.oculus.panelapp.social.actions.$$Lambda$Block$AmNmquWxidmxwwPuug5oSJ_5c */
            private final /* synthetic */ String f$1;
            private final /* synthetic */ SocialPanelApp f$2;
            private final /* synthetic */ Context f$3;
            private final /* synthetic */ SocialUserActionActionHandler f$4;
            private final /* synthetic */ SocialViewModel f$5;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
                this.f$5 = r6;
            }

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return Block.this.lambda$preformAction$59$Block(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, dialogResult);
            }
        });
        socialPanelApp.getDialogManager().showDialog(dialogDefinitionCustom);
    }

    public /* synthetic */ boolean lambda$preformAction$59$Block(String str, final SocialPanelApp socialPanelApp, final Context context, final SocialUserActionActionHandler socialUserActionActionHandler, final SocialViewModel socialViewModel, DialogResult dialogResult) {
        if (!dialogResult.getDialogAction().equals(str)) {
            SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_FRIENDS_BLOCK_DIALOG_CONFIRM, socialPanelApp);
            HorizonContentProviderHelper.blockUser(context, this.mUser.getID(), new HorizonContentProviderHelper.BlockUserCallback() {
                /* class com.oculus.panelapp.social.actions.Block.AnonymousClass1 */

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BlockUserCallback
                public void onSuccess(String str) {
                    socialUserActionActionHandler.onSuccess();
                    socialViewModel.removeUserOptimisticAndRefetch(str, SocialViewModel.SocialList.FRIENDS);
                }

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
                public void onError() {
                    socialUserActionActionHandler.onError();
                    SocialLogger.logError(socialPanelApp, "block_user", Block.TAG, "Failed to block user");
                    Context context = context;
                    SocialViewWarningToaster.showToast(context, "oculus_mobile_social_block_error", context.getResources().getString(R.string.anytime_tablet_social_block_failed), Block.TAG);
                }
            });
            return true;
        }
        SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_FRIENDS_BLOCK_DIALOG_CANCEL, socialPanelApp);
        return false;
    }
}
