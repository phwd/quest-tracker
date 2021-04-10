package com.oculus.panelapp.dialog.commonsystemdialogs;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.dialog.CommonDialog;
import com.oculus.panelapp.dialog.DialogPanelApp;
import com.oculus.panelapp.dialog.R;
import com.oculus.systemdialog.CommonSystemDialog;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;

public final class OculusLinkAvailableDialog extends CommonDialog {
    private static final String DIALOG_ACTION_LAUNCH = "launch";
    private static final String TAG = LoggingUtil.tag(OculusLinkAvailableDialog.class);
    private final Context mContext;
    private final DialogPanelApp mDialogPanelApp;
    private long mRegisteredObserver;

    public OculusLinkAvailableDialog(Context context, DialogPanelApp dialogPanelApp) {
        Handler handler = new Handler(context.getMainLooper());
        this.mContext = context;
        this.mDialogPanelApp = dialogPanelApp;
        this.mRegisteredObserver = OculusLinkConnectionHelper.registerShouldDismissAvailableDialog(new Runnable(handler) {
            /* class com.oculus.panelapp.dialog.commonsystemdialogs.$$Lambda$OculusLinkAvailableDialog$tIED4LqVchXMdkjE_gCqUzxIiw */
            private final /* synthetic */ Handler f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                OculusLinkAvailableDialog.this.lambda$new$1$OculusLinkAvailableDialog(this.f$1);
            }
        });
        setDialogConfiguration(context);
    }

    public /* synthetic */ void lambda$new$1$OculusLinkAvailableDialog(Handler handler) {
        handler.post(new Runnable() {
            /* class com.oculus.panelapp.dialog.commonsystemdialogs.$$Lambda$OculusLinkAvailableDialog$cVn5Ey4wzYktLXY2lJjb__Qi594 */

            public final void run() {
                OculusLinkAvailableDialog.this.lambda$null$0$OculusLinkAvailableDialog();
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: dismissDialog */
    public void lambda$null$0$OculusLinkAvailableDialog() {
        if (this.mRegisteredObserver != 0) {
            setPendingSyntheticButtonClick(new DialogButton("close"));
        }
    }

    @Override // com.oculus.panelapp.dialog.CommonDialog
    public void onClose() {
        long j = this.mRegisteredObserver;
        if (j != 0) {
            OculusLinkConnectionHelper.unregisterShouldDismissDialog(j);
            this.mRegisteredObserver = 0;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.dialog.CommonDialog
    public void onAction(String str, String[] strArr) {
        if (DIALOG_ACTION_LAUNCH.equals(str)) {
            Log.i(TAG, "Requesting to vrshell to launch xrstreamingclient");
            this.mDialogPanelApp.queueRawCommand("launch com.oculus.xrstreamingclient");
        }
    }

    private void setDialogConfiguration(Context context) {
        Resources resources = context.getResources();
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(CommonSystemDialog.OCULUS_LINK_AVAILABLE.getDialogId(), resources.getString(R.string.oculus_link_available_dialog_title), resources.getString(R.string.oculus_link_available_dialog_description));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(DIALOG_ACTION_LAUNCH, resources.getString(R.string.oculus_link_available_dialog_button_launch)));
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("close", resources.getString(R.string.oculus_link_available_dialog_button_close)));
        dialogDefinitionCustom.setTertiaryButton(new DialogButtonText("disable", resources.getString(R.string.oculus_link_available_dialog_button_disable)));
        dialogDefinitionCustom.setControllerBackButton(new DialogButton("close"));
        setPendingDialogConfiguration(dialogDefinitionCustom);
    }
}
