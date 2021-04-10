package com.oculus.vrshell.systemdialog.definitions;

import android.content.Context;
import android.net.UrlQuerySanitizer;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.util.HorizonUtil;

public final class QuitAndLaunchDialogDefinition extends CustomSystemDialogDefinition {
    public QuitAndLaunchDialogDefinition(Context context, String str) {
        super(createQuitAndLaunchDialogDefinitionCustom(context, str));
    }

    private static DialogDefinitionCustom createQuitAndLaunchDialogDefinitionCustom(Context context, String str) {
        UrlQuerySanitizer urlQuerySanitizer = new UrlQuerySanitizer();
        urlQuerySanitizer.registerParameter("uri", new UrlQuerySanitizer.IllegalCharacterValueSanitizer(264));
        urlQuerySanitizer.setAllowUnregisteredParamaters(true);
        urlQuerySanitizer.parseUrl(str);
        String value = urlQuerySanitizer.getValue("currentComponent");
        String value2 = urlQuerySanitizer.getValue("targetType");
        String value3 = urlQuerySanitizer.getValue("targetComponent");
        String value4 = urlQuerySanitizer.getValue("uri");
        String safeApplicationName = HorizonUtil.getSafeApplicationName(context, value);
        String str2 = "ok|" + value + "|" + value2 + "|" + value3 + "|" + value4 + "|";
        String str3 = "cancel|" + value2 + "|" + value3 + "|";
        return new DialogDefinitionCustom(SystemUXRoute.QUIT_AND_LAUNCH.path(), context.getResources().getString(R.string.quit_and_launch_title, safeApplicationName), context.getResources().getString(R.string.quit_and_launch_desc, safeApplicationName)).setPrimaryButton(new DialogButtonText(str2, context.getResources().getString(R.string.quit_and_launch_confirm_button))).setSecondaryButton(new DialogButtonText(str3, context.getResources().getString(R.string.quit_and_launch_cancel_button))).setControllerBackButton(new DialogButton(str3));
    }
}
