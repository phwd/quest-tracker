package com.oculus.panelapp.anytimeui.v2.tablet.sharing;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import com.oculus.auth.service.contract.ServiceContract;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.systemdialog.CommonSystemDialog;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionBase;
import com.oculus.systemdialog.DialogDefinitionCommon;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.vrshell.sharedprefs.SharedKeys;
import com.oculus.vrshell.sharedprefs.SharedPreferencesHelper;

public final class StartBrowserCastingDialogHandler {
    private static final String ANSWER_RECEIVED_INTENT_ACTION = "broadcast_cast_www_answer_received";
    private static final String ENTERPRISE_CAST_TO_BROWSER_SUCCESS_DIALOG_ID = "common_system_dialog_enterprise_cast_to_browser_success";
    private static final String HIDE_LOCAL_STREAM_TO_BROWSER_DIALOG_PREF_KEY = "hide_local_stream_to_browser_description";
    private static final String HORIZON_COMPONENT_NAME = "com.oculus.horizon.service_media.OVRMediaService";
    private static final String HORIZON_MESSAGE_TYPE_KEY = "message_type";
    private static final String HORIZON_PACKAGE_NAME = "com.oculus.horizon";
    private static final String HORIZON_STOP_LOCAL_STREAM_ACTION = "com.oculus.horizon.STOP_LOCAL_STREAM";
    private static final String OFFER_SET_INTENT_ACTION = "broadcast_cast_www_offer_set";
    private static final String STOP_CASTING_ACTION = "stop_casting";
    private final Context mContext;
    private final AnytimeUIAndroidPanelAppV2 mPanelApp;
    private final BrowserCastingOfferSetReceiver mReceiver = new BrowserCastingOfferSetReceiver();

    public StartBrowserCastingDialogHandler(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        this.mContext = context;
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        initializeReceiver();
    }

    public void destroy() {
        this.mContext.unregisterReceiver(this.mReceiver);
    }

    public void onCastingStateChange(boolean z) {
        String dialogId = this.mPanelApp.getDialogManager().getDialogId();
        if (this.mPanelApp.getSystemUXConfig().isEnterpriseMode && !z && ENTERPRISE_CAST_TO_BROWSER_SUCCESS_DIALOG_ID.equals(dialogId)) {
            this.mPanelApp.getDialogManager().hideDialog();
        }
    }

    private void initializeReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(OFFER_SET_INTENT_ACTION);
        this.mContext.registerReceiver(this.mReceiver, intentFilter);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private DialogDefinitionBase getConsumerSuccessDialog() {
        if (!SharedPreferencesHelper.getBoolean(this.mContext, SharedKeys.SETTINGS.extend(HIDE_LOCAL_STREAM_TO_BROWSER_DIALOG_PREF_KEY), false)) {
            return new DialogDefinitionCommon(CommonSystemDialog.LOCAL_STREAM_TO_BROWSER);
        }
        return null;
    }

    private DialogDefinitionBase getEnterpriseSuccessDialog() {
        Resources resources = this.mPanelApp.getContext().getResources();
        String string = resources.getString(R.string.enterprise_casting_success_dialog_title);
        String string2 = resources.getString(R.string.enterprise_casting_success_dialog_description);
        String string3 = resources.getString(R.string.enterprise_casting_success_dialog_button_done);
        String string4 = resources.getString(R.string.enterprise_casting_success_dialog_button_stop);
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(ENTERPRISE_CAST_TO_BROWSER_SUCCESS_DIALOG_ID, string, string2);
        DialogButtonText dialogButtonText = new DialogButtonText("done", string3);
        DialogButtonText dialogButtonText2 = new DialogButtonText(STOP_CASTING_ACTION, string4);
        dialogDefinitionCustom.setPrimaryButton(dialogButtonText);
        dialogDefinitionCustom.setSecondaryButton(dialogButtonText2);
        dialogDefinitionCustom.setDialogResultHandler(new DialogResultHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.sharing.$$Lambda$StartBrowserCastingDialogHandler$EqitM5xckzOT5bZW2dOC6A4deA */

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return StartBrowserCastingDialogHandler.this.lambda$getEnterpriseSuccessDialog$493$StartBrowserCastingDialogHandler(dialogResult);
            }
        });
        return dialogDefinitionCustom;
    }

    public /* synthetic */ boolean lambda$getEnterpriseSuccessDialog$493$StartBrowserCastingDialogHandler(DialogResult dialogResult) {
        if (dialogResult.getDialogAction().equals(STOP_CASTING_ACTION)) {
            stopCasting();
        }
        this.mPanelApp.getDialogManager().hideDialog();
        return true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private DialogDefinitionBase getEnterprisePinDialog(String str) {
        DialogDefinitionCommon dialogDefinitionCommon = new DialogDefinitionCommon(CommonSystemDialog.ENTERPRISE_CAST_TO_BROWSER_PIN);
        dialogDefinitionCommon.setParameter(ServiceContract.EXTRA_PIN, str);
        dialogDefinitionCommon.setDialogResultHandler(new DialogResultHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.sharing.$$Lambda$StartBrowserCastingDialogHandler$21LkFig_CcRe5W3YzrjrnQs1WwM */

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return StartBrowserCastingDialogHandler.this.lambda$getEnterprisePinDialog$494$StartBrowserCastingDialogHandler(dialogResult);
            }
        });
        return dialogDefinitionCommon;
    }

    public /* synthetic */ boolean lambda$getEnterprisePinDialog$494$StartBrowserCastingDialogHandler(DialogResult dialogResult) {
        if (!dialogResult.getDialogAction().equals(ANSWER_RECEIVED_INTENT_ACTION)) {
            return false;
        }
        this.mPanelApp.getDialogManager().hideDialog();
        this.mPanelApp.getDialogManager().showDialog(getEnterpriseSuccessDialog());
        return true;
    }

    private void stopCasting() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.horizon", HORIZON_COMPONENT_NAME));
        intent.putExtra(HORIZON_MESSAGE_TYPE_KEY, HORIZON_STOP_LOCAL_STREAM_ACTION);
        this.mContext.startService(intent);
    }

    /* access modifiers changed from: private */
    public class BrowserCastingOfferSetReceiver extends BroadcastReceiver {
        private BrowserCastingOfferSetReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            DialogDefinitionBase dialogDefinitionBase;
            String stringExtra = intent.getStringExtra(ServiceContract.EXTRA_PIN);
            if ((stringExtra == null || stringExtra.isEmpty()) && !StartBrowserCastingDialogHandler.this.mPanelApp.getSystemUXConfig().isEnterpriseMode) {
                dialogDefinitionBase = StartBrowserCastingDialogHandler.this.getConsumerSuccessDialog();
            } else {
                dialogDefinitionBase = StartBrowserCastingDialogHandler.this.getEnterprisePinDialog(stringExtra);
            }
            if (dialogDefinitionBase != null) {
                StartBrowserCastingDialogHandler.this.mPanelApp.getDialogManager().showDialog(dialogDefinitionBase);
            }
        }
    }
}
