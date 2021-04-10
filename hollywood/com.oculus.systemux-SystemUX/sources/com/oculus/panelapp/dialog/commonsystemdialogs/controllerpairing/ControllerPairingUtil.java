package com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing;

import android.content.Context;
import android.content.res.Resources;
import com.oculus.panelapp.dialog.R;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogList;
import com.oculus.systemdialog.DialogListItem;
import com.oculus.systemdialog.DialogListItemImageType;
import com.oculus.systemdialog.DialogListType;
import java.util.concurrent.TimeUnit;

public final class ControllerPairingUtil {
    static String getTimeoutMessage(Context context, long j) {
        return String.format(context.getResources().getString(R.string.controller_pairing_timeout), Integer.valueOf(Math.round(((float) j) / ((float) TimeUnit.SECONDS.toMillis(1)))));
    }

    static DialogDefinitionCustom getFailureDialogConfiguration(Context context, ControllerPairingDialogStep controllerPairingDialogStep, String str, boolean z) {
        String str2;
        Resources resources = context.getResources();
        DialogList dialogList = new DialogList(DialogListType.NO_SELECT);
        dialogList.addListItem(new DialogListItem("controller_pairing_failure_issue_battery", resources.getString(R.string.controller_pairing_failure_issue_battery), null, "apk://com.oculus.systemux/assets/oc_icon_battery_alt_charge_1_24.png", DialogListItemImageType.GLYPH));
        dialogList.addListItem(new DialogListItem("controller_pairing_failure_issue_press_both_buttons", resources.getString(R.string.controller_pairing_two_failure_issue_press_both_buttons), null, "apk://com.oculus.systemux/assets/oc_icon_touchpad_1_24.png", DialogListItemImageType.GLYPH));
        if (z) {
            str2 = resources.getString(R.string.controller_pairing_failure_description_hands);
        } else {
            str2 = resources.getString(R.string.controller_pairing_failure_description_gaze);
        }
        return new DialogDefinitionCustom(controllerPairingDialogStep.getFailureDialogId(), resources.getString(R.string.controller_pairing_failure_title), str2).setList(dialogList).setSecondaryButton(new DialogButtonText("close", resources.getString(R.string.controller_pairing_dialog_cancel))).setPrimaryButton(new DialogButtonText(str, resources.getString(R.string.controller_pairing_failure_retry)));
    }
}
