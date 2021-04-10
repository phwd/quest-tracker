package com.oculus.panelapp.parties.views;

import android.content.res.Resources;
import com.oculus.horizoncontent.social.SocialGroupLaunchAppDestination;
import com.oculus.socialplatform.R;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionBase;
import com.oculus.systemdialog.DialogDefinitionCustom;

public class PartiesGroupLaunchDialogs {
    public static final String CANCEL_ACTION = "cancel";

    public static DialogDefinitionBase getAppLaunchDialog(Resources resources, SocialGroupLaunchAppDestination socialGroupLaunchAppDestination) {
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("parties_panel_app_modal_group_launch_app_launch_dialog", resources.getString(R.string.parties_tablet_social_modal_group_launch_app_launch_title, socialGroupLaunchAppDestination.mAppDisplayName), "");
        dialogDefinitionCustom.mDialogSecondaryButton = new DialogButtonText("cancel", resources.getString(R.string.parties_tablet_social_modal_group_launch_app_launch_cancel));
        dialogDefinitionCustom.mDialogControllerBackButton = new DialogButton("cancel");
        return dialogDefinitionCustom;
    }
}
