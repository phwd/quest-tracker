package com.oculus.panelapp.anytimeui.v2.tablet.profile;

import android.content.res.Resources;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionBase;
import com.oculus.systemdialog.DialogDefinitionCustom;

public class ProfileDialogs {
    public static final String CANCEL_ACTION = "cancel";
    public static final String CONFIRM_ACTION = "confirm";
    public static final String RETRY_ACTION = "retry";

    public static DialogDefinitionBase getUnfriendDialog(Resources resources, String str) {
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("anytime_tablet_profile_unfriend", resources.getString(R.string.anytime_tablet_profile_dialog_unfriend_title, str), resources.getString(R.string.anytime_tablet_profile_dialog_unfriend_body, str));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(CONFIRM_ACTION, resources.getString(R.string.anytime_tablet_profile_unfriend_button)));
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("cancel", resources.getString(R.string.anytime_tablet_profile_dialog_cancel_button)));
        dialogDefinitionCustom.setControllerBackButton(new DialogButton("cancel"));
        return dialogDefinitionCustom;
    }

    public static DialogDefinitionBase getBlockDialog(Resources resources, String str) {
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("anytime_tablet_profile_block", resources.getString(R.string.anytime_tablet_profile_dialog_block_title, str), resources.getString(R.string.anytime_tablet_profile_dialog_block_body, str));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(CONFIRM_ACTION, resources.getString(R.string.anytime_tablet_profile_block)));
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("cancel", resources.getString(R.string.anytime_tablet_profile_dialog_cancel_button)));
        dialogDefinitionCustom.setControllerBackButton(new DialogButton("cancel"));
        return dialogDefinitionCustom;
    }

    public static DialogDefinitionBase getCancelFriendRequestDialog(Resources resources) {
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("anytime_tablet_profile_cancel_friend_request", resources.getString(R.string.anytime_tablet_profile_dialog_cancel_friend_request_title), resources.getString(R.string.anytime_tablet_profile_dialog_cancel_friend_request_body));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(CONFIRM_ACTION, resources.getString(R.string.anytime_tablet_profile_cancel_friend_request_button)));
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("cancel", resources.getString(R.string.anytime_tablet_profile_dialog_close_button)));
        dialogDefinitionCustom.setControllerBackButton(new DialogButton("cancel"));
        return dialogDefinitionCustom;
    }

    public static DialogDefinitionBase getOfflineDialog(Resources resources) {
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("anytime_tablet_profile_modal_edit_avatar_offline", resources.getString(R.string.anytime_tablet_profile_offline_dialog_title), resources.getString(R.string.anytime_tablet_profile_offline_dialog_body));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(CONFIRM_ACTION, resources.getString(R.string.anytime_tablet_profile_offline_dialog_button)));
        dialogDefinitionCustom.setControllerBackButton(new DialogButton(CONFIRM_ACTION));
        return dialogDefinitionCustom;
    }

    public static DialogDefinitionBase getBioUpdateFailedDialog(Resources resources) {
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("anytime_tablet_social_modal_update_bio_failed", resources.getString(R.string.anytime_tablet_profile_update_bio_failed_title), resources.getString(R.string.anytime_tablet_profile_update_bio_failed_body));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(RETRY_ACTION, resources.getString(R.string.anytime_tablet_profile_update_bio_failed_retry)));
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("cancel", resources.getString(R.string.anytime_tablet_profile_update_bio_failed_cancel)));
        dialogDefinitionCustom.setControllerBackButton(new DialogButton("cancel"));
        return dialogDefinitionCustom;
    }
}
