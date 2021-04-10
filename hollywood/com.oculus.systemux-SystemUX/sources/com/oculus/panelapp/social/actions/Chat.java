package com.oculus.panelapp.social.actions;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.horizoncontent.social.SocialUserFriendshipStatus;
import com.oculus.panelapp.social.R;
import com.oculus.panelapp.social.SocialBundleConstants;
import com.oculus.panelapp.social.SocialLogger;
import com.oculus.panelapp.social.SocialPanelApp;
import com.oculus.panelapp.social.SocialUserAdapterItem;
import com.oculus.panelapp.social.actions.SocialUserAction;
import com.oculus.panelapp.social.utils.UpsellLoggingParameters;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionBase;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.vrshell.SystemUXRoute;

public class Chat extends SocialUserAction {
    private static final String TAG = LoggingUtil.tag(Chat.class);
    private SocialPanelApp mPanelApp;
    private SocialUserAdapterItem mUser;

    public Chat(SocialUserAdapterItem socialUserAdapterItem, SocialPanelApp socialPanelApp) {
        this.mUser = socialUserAdapterItem;
        this.mPanelApp = socialPanelApp;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public boolean isRelevant() {
        return this.mPanelApp.isGKEnabled(Gatekeeper.OCULUS_CHATS) && this.mUser.getUser().getFriendship() == SocialUserFriendshipStatus.ARE_FRIENDS;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public SocialUserActionType getType() {
        return SocialUserActionType.CHAT;
    }

    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public UpsellLoggingParameters upsellParams() {
        return new UpsellLoggingParameters("aui_v2_social_panel", SocialBundleConstants.FB_UPSELL_CHAT, "aui_v2_social_panel", SocialBundleConstants.FB_UPSELL_CHAT_BUTTON, SocialBundleConstants.FB_UPSELL_MUST_INTERACT, SocialBundleConstants.FB_UPSELL_MESSENGER_PRODUCT);
    }

    /* access modifiers changed from: package-private */
    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public ClickEventButtonId getButtonId(SocialUserAction.Source source) {
        if (source == SocialUserAction.Source.USER_CARD_MENU) {
            return ClickEventButtonId.AUIV2_SOCIAL_FRIENDS_CARD_CHAT_START;
        }
        return ClickEventButtonId.AUIV2_SOCIAL_FRIENDS_ROW_CHAT_START;
    }

    /* access modifiers changed from: package-private */
    @Override // com.oculus.panelapp.social.actions.SocialUserAction
    public void preformAction(SocialPanelApp socialPanelApp, Context context, SocialUserActionActionHandler socialUserActionActionHandler) {
        Log.d("SocialView", "Preforming action yo");
        if (this.mUser.getUser().getCanViewerMessage()) {
            Uri build = new Uri.Builder().encodedPath("").appendQueryParameter("thread_id", this.mUser.getID()).build();
            if (socialPanelApp.isGKEnabled(Gatekeeper.QUICK_MESSAGE_DIALOG)) {
                socialPanelApp.actionNavigate(SystemUXRoute.QUICK_MESSAGE, build.toString());
            } else {
                socialPanelApp.actionNavigate(SystemUXRoute.MESSAGES, build.toString());
            }
        } else {
            socialPanelApp.getDialogManager().showDialog(getCannotMessageUserDialog(this.mUser.getUser(), socialPanelApp, context.getResources()));
        }
    }

    private static DialogDefinitionBase getCannotMessageUserDialog(SocialUser socialUser, SocialPanelApp socialPanelApp, Resources resources) {
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("anytime_tablet_social_modal_send_message", resources.getString(R.string.anytime_tablet_social_modal_send_message_title), resources.getString(R.string.anytime_tablet_social_modal_send_message_cannot_message_description, socialUser.getAlias()));
        DialogButtonText dialogButtonText = new DialogButtonText("send", resources.getString(R.string.anytime_tablet_social_modal_send_message_send_action));
        dialogButtonText.setDisabled();
        dialogDefinitionCustom.setPrimaryButton(dialogButtonText);
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("cancel", resources.getString(R.string.anytime_tablet_social_modal_send_message_cancel_action)));
        dialogDefinitionCustom.setTertiaryButton(new DialogButtonText("all_chats", resources.getString(R.string.anytime_tablet_social_modal_send_message_all_chats_action)));
        dialogDefinitionCustom.setControllerBackButton(new DialogButton("cancel"));
        dialogDefinitionCustom.setDialogResultHandler(new DialogResultHandler(socialPanelApp, "all_chats") {
            /* class com.oculus.panelapp.social.actions.$$Lambda$Chat$3Z35RD87wusO4ZglYMI4xSOjoQ */
            private final /* synthetic */ SocialPanelApp f$1;
            private final /* synthetic */ String f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return Chat.lambda$getCannotMessageUserDialog$60(SocialUser.this, this.f$1, this.f$2, dialogResult);
            }
        });
        return dialogDefinitionCustom;
    }

    static /* synthetic */ boolean lambda$getCannotMessageUserDialog$60(SocialUser socialUser, SocialPanelApp socialPanelApp, String str, DialogResult dialogResult) {
        SocialLogger.logQuickMessageCannotMessageUserEvent(socialUser.getID(), socialPanelApp);
        if (!dialogResult.getDialogAction().equals(str)) {
            return false;
        }
        SocialLogger.logQuickMessageAllChatsClickedEvents(socialUser.getID(), socialPanelApp);
        socialPanelApp.actionNavigate(SystemUXRoute.MESSAGES, "");
        return true;
    }
}
