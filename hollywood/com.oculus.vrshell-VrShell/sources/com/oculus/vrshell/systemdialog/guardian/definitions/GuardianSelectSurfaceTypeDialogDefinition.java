package com.oculus.vrshell.systemdialog.guardian.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogList;
import com.oculus.systemdialog.DialogListItem;
import com.oculus.systemdialog.DialogListItemImageType;
import com.oculus.systemdialog.DialogListType;
import com.oculus.vrshell.R;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogActions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogDefinitions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogIcons;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogType;

public final class GuardianSelectSurfaceTypeDialogDefinition extends CustomSystemDialogDefinition {
    public GuardianSelectSurfaceTypeDialogDefinition(Context context, boolean z) {
        super(new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.SELECT_SURFACE_TYPE), context.getResources().getString(R.string.guardian_select_surface_type_title), context.getResources().getString(R.string.guardian_select_surface_type_body)).setSecondaryButton(new DialogButtonText(GuardianDialogActions.CANCEL_SURFACE_DRAWING, context.getResources().getString(R.string.guardian_cancel_type_selection_button))).setControllerBackButton(new DialogButton(GuardianDialogActions.CANCEL_SURFACE_DRAWING)).setList(createSurfaceTypeList(context, z)));
    }

    private static DialogList createSurfaceTypeList(Context context, boolean z) {
        DialogList dialogList = new DialogList(DialogListType.SINGLE_SELECT);
        dialogList.setShouldSendSelectionChangeAction(true);
        dialogList.addListItem(new DialogListItem(GuardianDialogActions.DRAW_COUCH, context.getResources().getString(R.string.guardian_select_couch_button), null, "apk://com.oculus.guardianresources/assets/oc_icon_couch_filled_24@1x.png", DialogListItemImageType.GLYPH));
        dialogList.addListItem(new DialogListItem(GuardianDialogActions.DRAW_DESK, context.getResources().getString(R.string.guardian_select_desk_button), null, "apk://com.oculus.guardianresources/assets/oc_icon_couch_filled_24@1x.png", DialogListItemImageType.GLYPH));
        dialogList.addListItem(new DialogListItem(GuardianDialogActions.DRAW_WHITEBOARD, context.getResources().getString(R.string.guardian_select_whiteboard_button), null, GuardianDialogIcons.WHITEBOARD_FILLED, DialogListItemImageType.GLYPH));
        if (z) {
            dialogList.addListItem(new DialogListItem(GuardianDialogActions.EDIT_COUCH, context.getResources().getString(R.string.guardian_edit_couch_button), null, "apk://com.oculus.guardianresources/assets/oc_icon_couch_filled_24@1x.png", DialogListItemImageType.GLYPH));
            dialogList.addListItem(new DialogListItem(GuardianDialogActions.EDIT_DESK, context.getResources().getString(R.string.guardian_edit_desk_button), null, "apk://com.oculus.guardianresources/assets/oc_icon_couch_filled_24@1x.png", DialogListItemImageType.GLYPH));
            dialogList.addListItem(new DialogListItem(GuardianDialogActions.EDIT_WHITEBOARD, context.getResources().getString(R.string.guardian_edit_whiteboard_button), null, GuardianDialogIcons.WHITEBOARD_FILLED, DialogListItemImageType.GLYPH));
        }
        return dialogList;
    }
}
