package com.oculus.panelapp.social.actions;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.horizoncontent.social.SocialUserFriendshipStatus;
import com.oculus.panelapp.social.SocialBundleConstants;
import com.oculus.panelapp.social.SocialLogger;
import com.oculus.panelapp.social.SocialPanelApp;
import com.oculus.panelapp.social.SocialUserAdapterItem;
import com.oculus.panelapp.social.actions.SocialUserAction;
import com.oculus.panelapp.social.utils.UpsellLoggingParameters;
import com.oculus.socialplatform.R;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionBase;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.vrshell.SystemUXRoute;

public class Chat extends SocialUserAction {
    public static final String TAG = LoggingUtil.tag(Chat.class);
    public SocialPanelApp mPanelApp;
    public SocialUserAdapterItem mUser;

    public static /* synthetic */ boolean lambda$getCannotMessageUserDialog$0(Context context, SocialUser socialUser, String str, SocialPanelApp socialPanelApp, DialogResult dialogResult) {
        SocialLogger.logQuickMessageCannotMessageUserEvent(context, socialUser.mID);
        if (!dialogResult.mDialogAction.equals(str)) {
            return false;
        }
        SocialLogger.logQuickMessageAllChatsClickedEvents(context, socialUser.mID);
        socialPanelApp.actionNavigate(SystemUXRoute.MESSAGES, "");
        return true;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public ClickEventButtonId getButtonId(SocialUserAction.Source source) {
        if (source == SocialUserAction.Source.USER_CARD_MENU) {
            return ClickEventButtonId.AUIV2_SOCIAL_FRIENDS_CARD_CHAT_START;
        }
        return ClickEventButtonId.AUIV2_SOCIAL_FRIENDS_ROW_CHAT_START;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public SocialUserActionType getType() {
        return SocialUserActionType.CHAT;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public boolean isRelevant() {
        if (!this.mPanelApp.isGKEnabled(Gatekeeper.OCULUS_CHATS) || this.mUser.mUser.getFriendship() != SocialUserFriendshipStatus.ARE_FRIENDS) {
            return false;
        }
        return true;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public void preformAction(SocialPanelApp socialPanelApp, Context context, SocialUserActionActionHandler socialUserActionActionHandler) {
        SystemUXRoute systemUXRoute;
        if (this.mUser.mUser.mCanViewerMessage) {
            Uri build = new Uri.Builder().encodedPath("").appendQueryParameter("thread_id", this.mUser.getID()).build();
            if (socialPanelApp.isGKEnabled(Gatekeeper.QUICK_MESSAGE_DIALOG)) {
                systemUXRoute = SystemUXRoute.QUICK_MESSAGE;
            } else {
                systemUXRoute = SystemUXRoute.MESSAGES;
            }
            socialPanelApp.actionNavigate(systemUXRoute, build.toString());
            return;
        }
        socialPanelApp.getDialogManager().showDialog(getCannotMessageUserDialog(context, this.mUser.mUser, socialPanelApp, context.getResources()));
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public UpsellLoggingParameters upsellParams() {
        return new UpsellLoggingParameters("aui_v2_social_panel", SocialBundleConstants.FB_UPSELL_CHAT, "aui_v2_social_panel", SocialBundleConstants.FB_UPSELL_CHAT_BUTTON, "true", SocialBundleConstants.FB_UPSELL_MESSENGER_PRODUCT);
    }

    public Chat(SocialUserAdapterItem socialUserAdapterItem, SocialPanelApp socialPanelApp) {
        this.mUser = socialUserAdapterItem;
        this.mPanelApp = socialPanelApp;
    }

    public static DialogDefinitionBase getCannotMessageUserDialog(Context context, SocialUser socialUser, SocialPanelApp socialPanelApp, Resources resources) {
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("anytime_tablet_social_modal_send_message", resources.getString(R.string.anytime_tablet_social_modal_send_message_title), resources.getString(R.string.anytime_tablet_social_modal_send_message_cannot_message_description, socialUser.mAlias));
        DialogButtonText dialogButtonText = new DialogButtonText("send", resources.getString(R.string.anytime_tablet_social_modal_send_message_send_action));
        dialogButtonText.mButtonDisabled = true;
        dialogDefinitionCustom.mDialogPrimaryButton = dialogButtonText;
        dialogDefinitionCustom.mDialogSecondaryButton = new DialogButtonText("cancel", resources.getString(R.string.anytime_tablet_social_modal_send_message_cancel_action));
        dialogDefinitionCustom.mDialogTertiaryButton = new DialogButtonText("all_chats", resources.getString(R.string.anytime_tablet_social_modal_send_message_all_chats_action));
        dialogDefinitionCustom.mDialogControllerBackButton = new DialogButton("cancel");
        dialogDefinitionCustom.mDialogResultHandler = new DialogResultHandler(context, socialUser, "all_chats", socialPanelApp) {
            /* class com.oculus.panelapp.social.actions.$$Lambda$Chat$1MRRzblaO7ntJ3ufUx6FZJqmyj42 */
            public final /* synthetic */ Context f$0;
            public final /* synthetic */ SocialUser f$1;
            public final /* synthetic */ String f$2;
            public final /* synthetic */ SocialPanelApp f$3;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return Chat.lambda$getCannotMessageUserDialog$0(this.f$0, this.f$1, this.f$2, this.f$3, dialogResult);
            }
        };
        return dialogDefinitionCustom;
    }
}
