package com.oculus.panelapp.dialog.commonsystemdialogs.localstream;

import android.content.Context;
import android.content.res.Resources;
import com.oculus.panelapp.dialog.CommonDialog;
import com.oculus.panelapp.dialog.R;
import com.oculus.systemdialog.CommonSystemDialog;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;

public class LocalStreamToBrowserDialog extends CommonDialog {
    private static final String DISABLE_ACTION = "disable";
    private static final String DONE_ACTION = "done";
    private static final String HIDE_LOCAL_STREAM_TO_BROWSER_DIALOG_PREF_KEY = "hide_local_stream_to_browser_description";
    private static final String STOP_CASTING_ACTION = "stop_casting";
    private final Context mContext;

    public LocalStreamToBrowserDialog(Context context) {
        this.mContext = context;
        setDialogConfiguration(context);
    }

    private void setDialogConfiguration(Context context) {
        Resources resources = context.getResources();
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(CommonSystemDialog.LOCAL_STREAM_TO_BROWSER.getDialogId(), resources.getString(R.string.local_stream_to_browser_dialog_title), resources.getString(R.string.local_stream_to_browser_dialog_description));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(DONE_ACTION, resources.getString(R.string.local_stream_to_browser_dialog_button_done)));
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText(DISABLE_ACTION, resources.getString(R.string.local_stream_to_browser_dialog_disable_warning)));
        dialogDefinitionCustom.setTertiaryButton(new DialogButtonText(STOP_CASTING_ACTION, resources.getString(R.string.local_stream_to_browser_dialog_stop_casting)));
        setPendingDialogConfiguration(dialogDefinitionCustom);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0030  */
    @Override // com.oculus.panelapp.dialog.CommonDialog
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onAction(java.lang.String r3, java.lang.String[] r4) {
        /*
            r2 = this;
            int r4 = r3.hashCode()
            r0 = 1671308008(0x639e22e8, float:5.8342016E21)
            r1 = 1
            if (r4 == r0) goto L_0x001a
            r0 = 1675652358(0x63e06d06, float:8.279853E21)
            if (r4 == r0) goto L_0x0010
            goto L_0x0024
        L_0x0010:
            java.lang.String r4 = "stop_casting"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0024
            r3 = r1
            goto L_0x0025
        L_0x001a:
            java.lang.String r4 = "disable"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0024
            r3 = 0
            goto L_0x0025
        L_0x0024:
            r3 = -1
        L_0x0025:
            if (r3 == 0) goto L_0x0030
            if (r3 == r1) goto L_0x002a
            goto L_0x003d
        L_0x002a:
            android.content.Context r3 = r2.mContext
            com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamUtils.broadcastLocalStreamStopIntent(r3)
            goto L_0x003d
        L_0x0030:
            android.content.Context r3 = r2.mContext
            com.oculus.vrshell.sharedprefs.PrefKey r4 = com.oculus.vrshell.sharedprefs.SharedKeys.SETTINGS
            java.lang.String r0 = "hide_local_stream_to_browser_description"
            com.oculus.vrshell.sharedprefs.PrefKey r4 = r4.extend(r0)
            com.oculus.vrshell.sharedprefs.SharedPreferencesHelper.putBoolean(r3, r4, r1)
        L_0x003d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamToBrowserDialog.onAction(java.lang.String, java.lang.String[]):void");
    }
}
