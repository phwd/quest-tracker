package com.oculus.vrshell.systemdialog.definitions;

import android.content.Context;
import android.net.Uri;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.SystemDialogActions;

public final class DeveloperErrorDialogDefinition extends CustomSystemDialogDefinition {
    private static final String PARAM_MESSAGE = "message";
    private static final String PARAM_TITLE = "title";

    public DeveloperErrorDialogDefinition(Context context, String str) {
        super(build(context, str));
    }

    private static DialogDefinitionCustom build(Context context, String str) {
        Uri parse = Uri.parse(str);
        return new DialogDefinitionCustom(parse.buildUpon().clearQuery().build().toString(), parse.getQueryParameter("title"), parse.getQueryParameter("message")).setPrimaryButton(new DialogButtonText(SystemDialogActions.OK_ACTION, "Confirm")).setControllerBackButton(new DialogButton("close"));
    }
}
