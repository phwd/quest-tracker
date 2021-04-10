package com.oculus.panelapp.dialog.commonsystemdialogs;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import androidx.core.os.EnvironmentCompat;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsDeviceSection;
import com.oculus.panelapp.dialog.CommonDialog;
import com.oculus.panelapp.dialog.R;
import com.oculus.systemdialog.CommonSystemDialog;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import java.util.Map;

public final class OculusLinkDisconnectedDialog extends CommonDialog {
    private static final String PARAM_DISCONNECT_REASON = "disconnect_reason";
    private static final String TAG = LoggingUtil.tag(OculusLinkDisconnectedDialog.class);
    private final String mDisconnectReason;
    private long mRegisteredObserver = OculusLinkConnectionHelper.registerShouldDismissDisconnectedDialog(new Runnable(new Handler(Looper.getMainLooper())) {
        /* class com.oculus.panelapp.dialog.commonsystemdialogs.$$Lambda$OculusLinkDisconnectedDialog$K_5acD0JEWgpjcPUBDXzLsaQgDs */
        private final /* synthetic */ Handler f$1;

        {
            this.f$1 = r2;
        }

        public final void run() {
            OculusLinkDisconnectedDialog.this.lambda$new$3$OculusLinkDisconnectedDialog(this.f$1);
        }
    });

    public OculusLinkDisconnectedDialog(Context context, Map<String, String> map) {
        this.mDisconnectReason = getStringParameterOrDefault(map, PARAM_DISCONNECT_REASON, EnvironmentCompat.MEDIA_UNKNOWN);
        setDialogConfiguration(context);
    }

    public /* synthetic */ void lambda$new$3$OculusLinkDisconnectedDialog(Handler handler) {
        handler.post(new Runnable() {
            /* class com.oculus.panelapp.dialog.commonsystemdialogs.$$Lambda$OculusLinkDisconnectedDialog$6JMyzvn8sfU7wyz9qzTFgxBjA */

            public final void run() {
                OculusLinkDisconnectedDialog.this.lambda$null$2$OculusLinkDisconnectedDialog();
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: dismissDialog */
    public void lambda$null$2$OculusLinkDisconnectedDialog() {
        if (this.mRegisteredObserver != 0) {
            setPendingSyntheticButtonClick(new DialogButton("cancel"));
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

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private void setDialogConfiguration(Context context) {
        char c;
        Resources resources = context.getResources();
        String string = resources.getString(R.string.oculus_link_disconnected_dialog_description_unknown_error);
        String str = this.mDisconnectReason;
        switch (str.hashCode()) {
            case -1859669827:
                if (str.equals("cable_unplugged")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1548364921:
                if (str.equals("flaky_cable")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -591123491:
                if (str.equals("session_timed_out")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -32966342:
                if (str.equals(SettingsDeviceSection.DISCONNECT_REASON_PC_NOT_DETECTED)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1857793895:
                if (str.equals("incompatible_packet_version")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            string = resources.getString(R.string.oculus_link_disconnected_dialog_description_flaky_cable);
        } else if (c == 1) {
            string = resources.getString(R.string.oculus_link_disconnected_dialog_description_cable_unplugged);
        } else if (c == 2) {
            string = resources.getString(R.string.oculus_link_disconnected_dialog_description_incompat_packet_version);
        } else if (c == 3) {
            string = resources.getString(R.string.oculus_link_disconnected_dialog_description_session_timed_out);
        } else if (c == 4) {
            string = resources.getString(R.string.oculus_link_disconnected_dialog_description_pc_not_detected);
        }
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(CommonSystemDialog.OCULUS_LINK_DISCONNECTED.getDialogId(), resources.getString(R.string.oculus_link_disconnected_dialog_title), string);
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText("cancel", resources.getString(R.string.oculus_link_disconnected_dialog_button_acknowledge)));
        dialogDefinitionCustom.setControllerBackButton(new DialogButton("cancel"));
        setPendingDialogConfiguration(dialogDefinitionCustom);
    }
}
