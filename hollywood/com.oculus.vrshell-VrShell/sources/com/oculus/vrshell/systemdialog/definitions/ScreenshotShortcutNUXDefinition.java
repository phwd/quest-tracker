package com.oculus.vrshell.systemdialog.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogHeroImage;
import com.oculus.vrshell.R;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.SystemDialogActions;

public final class ScreenshotShortcutNUXDefinition extends CustomSystemDialogDefinition {
    public ScreenshotShortcutNUXDefinition(Context context) {
        super(new DialogDefinitionCustom(SystemUXRoute.SCREENSHOT_SHORTCUT_NUX.path(), context.getResources().getString(R.string.screenshot_shortcut_title), context.getResources().getString(R.string.screenshot_shortcut_body)).setPrimaryButton(new DialogButtonText(SystemDialogActions.OK_ACTION, context.getResources().getString(R.string.screenshot_shortcut_ok))).setHeroImage(new DialogHeroImage("apk://com.oculus.vrshell.home/assets/photo_shortcut_nux_512x288.ktx", 1.778f, "0xFF1A1A1A")));
    }
}
