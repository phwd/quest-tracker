package com.oculus.panelapp.debug;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import androidx.annotation.Nullable;
import com.oculus.authapi.OVRAuth;
import com.oculus.notifications.NotificationConstants;
import com.oculus.notifications.NotificationSender;
import com.oculus.panelapp.debug.DebugTabHost;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.util.CallerInfoHelper;

public final class ControlTab extends ScrollView implements DebugTabHost.DebugTab {
    private static final String LOCAL_ACCOUNT_MODE_ENABLED = "local_account_mode_enabled";
    private final OVRAuth mOVRAuth;
    private int mTestNotifId;

    static /* synthetic */ int access$008(ControlTab controlTab) {
        int i = controlTab.mTestNotifId;
        controlTab.mTestNotifId = i + 1;
        return i;
    }

    public ControlTab(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mOVRAuth = new OVRAuth(context.getApplicationContext(), new OVRAuth.CallerInfoProvider() {
            /* class com.oculus.panelapp.debug.ControlTab.AnonymousClass1 */

            @Override // com.oculus.authapi.OVRAuth.CallerInfoProvider
            public Intent attachCallerInfo(Intent intent) {
                return CallerInfoHelper.attachCallerInfo(intent, ControlTab.this.getContext(), null);
            }
        });
    }

    @Override // com.oculus.panelapp.debug.DebugTabHost.DebugTab
    public void initialize(final ShellDebugPanelApp shellDebugPanelApp, DebugTabHost debugTabHost) {
        this.mTestNotifId = 5;
        ((Button) findViewById(R.id.debug_action_graphql)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.ControlTab.AnonymousClass2 */

            public void onClick(View view) {
                shellDebugPanelApp.getCommandChannel().sendCommand("shellDebug graphql 1 \"graphql?doc_id=2992430400788127\"");
            }
        });
        ((Button) findViewById(R.id.debug_action_close)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.ControlTab.AnonymousClass3 */

            public void onClick(View view) {
                shellDebugPanelApp.getCommandChannel().sendCommand("quitAndHide");
            }
        });
        ((Button) findViewById(R.id.debug_action_exit_shell)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.ControlTab.AnonymousClass4 */

            public void onClick(View view) {
                shellDebugPanelApp.getCommandChannel().sendCommand("shellDebug shutdown");
            }
        });
        ((Button) findViewById(R.id.debug_action_crash_shell)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.ControlTab.AnonymousClass5 */

            public void onClick(View view) {
                shellDebugPanelApp.getCommandChannel().sendCommand("shellDebug crash");
            }
        });
        ((Button) findViewById(R.id.debug_action_send_notification)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.ControlTab.AnonymousClass6 */

            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), NotificationSender.class);
                intent.putExtra("title", "Test Title");
                intent.putExtra("text", "Test Text About Notification");
                intent.putExtra(NotificationConstants.KEY_PRIORITY, 2);
                intent.putExtra(NotificationConstants.KEY_AUI_PERSIST, true);
                intent.putExtra("id", ControlTab.this.mTestNotifId);
                ControlTab.access$008(ControlTab.this);
                NotificationSender.sendTestNotif(ControlTab.this.getContext(), intent);
            }
        });
        ((Button) findViewById(R.id.debug_action_setup_guardian)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.ControlTab.AnonymousClass7 */

            public void onClick(View view) {
                shellDebugPanelApp.getCommandChannel().sendCommand("shellDebug setupGuardian");
            }
        });
        ((Button) findViewById(R.id.debug_action_clear_all_maps)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.ControlTab.AnonymousClass8 */

            public void onClick(View view) {
                shellDebugPanelApp.getCommandChannel().sendCommand("shellDebug clearMaps");
            }
        });
        ((Button) findViewById(R.id.debug_action_enter_local_account_mode)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.ControlTab.AnonymousClass9 */

            public void onClick(View view) {
                ControlTab.this.mOVRAuth.logout(new ResultReceiver(null) {
                    /* class com.oculus.panelapp.debug.ControlTab.AnonymousClass9.AnonymousClass1 */

                    /* access modifiers changed from: protected */
                    public void onReceiveResult(int i, Bundle bundle) {
                        shellDebugPanelApp.getPreferencesManager().set(ControlTab.LOCAL_ACCOUNT_MODE_ENABLED, true);
                        shellDebugPanelApp.getDialogManager().showDialog(ControlTab.this.getRestartLocalAccoundModeDialog());
                    }
                });
            }
        });
        ((Button) findViewById(R.id.debug_action_exit_local_account_mode)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.ControlTab.AnonymousClass10 */

            public void onClick(View view) {
                shellDebugPanelApp.getPreferencesManager().set(ControlTab.LOCAL_ACCOUNT_MODE_ENABLED, false);
                shellDebugPanelApp.getDialogManager().showDialog(ControlTab.this.getRestartLocalAccoundModeDialog());
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private DialogDefinitionCustom getRestartLocalAccoundModeDialog() {
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("debug_restart_lam_dialog", "Restart Device", "Your local account mode state has been updated. Restart the device manually for the change to take effect.");
        dialogDefinitionCustom.setBackButton(new DialogButton("back"));
        return dialogDefinitionCustom;
    }
}
