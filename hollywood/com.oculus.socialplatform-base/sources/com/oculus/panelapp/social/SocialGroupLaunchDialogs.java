package com.oculus.panelapp.social;

import android.content.res.Resources;
import com.oculus.horizon.notifications.core.NotificationsIntentCreationUtils;
import com.oculus.horizoncontent.social.SocialGroupLaunchAppDestination;
import com.oculus.socialplatform.R;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionBase;
import com.oculus.systemdialog.DialogDefinitionCustom;

public class SocialGroupLaunchDialogs {
    public static DialogDefinitionBase getAppLaunchDialog(Resources resources, SocialGroupLaunchAppDestination socialGroupLaunchAppDestination) {
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("anytime_tablet_social_modal_group_launch_app_launch_dialog", resources.getString(R.string.anytime_tablet_social_modal_group_launch_app_launch_title, socialGroupLaunchAppDestination.mAppDisplayName), "");
        dialogDefinitionCustom.mDialogSecondaryButton = new DialogButtonText("cancel", resources.getString(R.string.anytime_tablet_social_modal_group_launch_app_launch_cancel));
        dialogDefinitionCustom.mDialogControllerBackButton = new DialogButton("cancel");
        return dialogDefinitionCustom;
    }

    public static DialogDefinitionBase getRiftUsersNotSupportedDialog(Resources resources) {
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("anytime_tablet_social_modal_group_launch__rift_users_not_supported_dialog", resources.getString(R.string.anytime_tablet_social_modal_group_launch_rift_users_not_supported_title), resources.getString(R.string.anytime_tablet_social_modal_group_launch_rift_users_not_supported_body));
        dialogDefinitionCustom.mDialogPrimaryButton = new DialogButtonText(NotificationsIntentCreationUtils.LAUNCH_CTA, resources.getString(R.string.anytime_tablet_social_modal_group_launch_rift_users_not_supported_launch_button));
        dialogDefinitionCustom.mDialogSecondaryButton = new DialogButtonText("cancel", resources.getString(R.string.anytime_tablet_social_modal_group_launch_rift_users_not_supported_cancel_button));
        dialogDefinitionCustom.mDialogControllerBackButton = new DialogButton("cancel");
        return dialogDefinitionCustom;
    }
}
