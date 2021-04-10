package com.oculus.panelapp.dialog.commonsystemdialogs;

import android.content.Context;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.dialog.CommonDialog;
import com.oculus.panelapp.dialog.DialogPanelApp;
import com.oculus.panelapp.dialog.R;
import com.oculus.systemdialog.CommonSystemDialog;
import com.oculus.systemdialog.CommonSystemDialogActions;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.SystemUXRoute;

public final class AppDownloadFailureLowStorageDialog extends CommonDialog {
    private static final String TAG = LoggingUtil.tag(AppDownloadFailureLowStorageDialog.class);
    final DialogPanelApp mDialogPanelApp;

    public AppDownloadFailureLowStorageDialog(Context context, DialogPanelApp dialogPanelApp) {
        this.mDialogPanelApp = dialogPanelApp;
        context.getResources();
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(CommonSystemDialog.APP_DOWNLOAD_FAILURE_LOW_STORAGE.getDialogId(), context.getResources().getString(R.string.app_download_failure_low_storage_title), context.getResources().getString(R.string.app_download_failure_low_storage_body));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(CommonSystemDialogActions.CONTINUE, context.getResources().getString(R.string.app_download_failure_low_storage_continue)));
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("close", context.getResources().getString(R.string.app_download_failure_low_storage_close)));
        dialogDefinitionCustom.setControllerBackButton(new DialogButton("close"));
        setPendingDialogConfiguration(dialogDefinitionCustom);
    }

    @Override // com.oculus.panelapp.dialog.CommonDialog
    public void onAction(String str, String[] strArr) {
        if (CommonSystemDialogActions.CONTINUE.equals(str)) {
            DialogPanelApp dialogPanelApp = this.mDialogPanelApp;
            dialogPanelApp.queueRawCommand("launch " + SystemUXRoute.STORAGE_MANAGER.path());
        }
    }
}
