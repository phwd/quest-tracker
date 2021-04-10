package com.oculus.panelapp.debug;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.debug.DebugTabHost;
import com.oculus.vrshell.panels.views.ShellButton;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class CopresenceTab extends LinearLayout implements DebugTabHost.DebugTab, IShellToPanelCommandHandler {
    private static final String IPC_COMMAND_STATUS = "copresence status";
    private static final String TAG = LoggingUtil.tag(CopresenceTab.class);
    private ShellButton mConnectButton;
    private ShellButton mDisconnectButton;
    private ShellButton mEditAvatarButton;
    private TextView mStatusTextView;
    private Timer mStatusTimer;

    public CopresenceTab(@NonNull Context context) {
        this(context, null);
    }

    public CopresenceTab(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mStatusTimer = new Timer();
    }

    @Override // com.oculus.panelapp.debug.DebugTabHost.DebugTab
    public void initialize(final ShellDebugPanelApp shellDebugPanelApp, DebugTabHost debugTabHost) {
        Log.d(TAG, "Initializing copresence tab");
        this.mStatusTextView = (TextView) findViewById(R.id.debug_copresence_status_text);
        this.mStatusTextView.setText("Disabled. Check 'oc_vrshell_copresence' gatekeeper.");
        this.mConnectButton = (ShellButton) findViewById(R.id.debug_action_connect);
        this.mConnectButton.setEnabled(false);
        this.mConnectButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.CopresenceTab.AnonymousClass1 */

            public void onClick(View view) {
                shellDebugPanelApp.getCommandChannel().sendCommand("copresence connect");
            }
        });
        this.mDisconnectButton = (ShellButton) findViewById(R.id.debug_action_disconnect);
        this.mDisconnectButton.setEnabled(false);
        this.mDisconnectButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.CopresenceTab.AnonymousClass2 */

            public void onClick(View view) {
                shellDebugPanelApp.getCommandChannel().sendCommand("copresence disconnect");
            }
        });
        this.mEditAvatarButton = (ShellButton) findViewById(R.id.debug_action_launchavatareditor);
        this.mEditAvatarButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.CopresenceTab.AnonymousClass3 */

            public void onClick(View view) {
                shellDebugPanelApp.getCommandChannel().launch("systemux://avatareditor", "/?version=V2");
            }
        });
        shellDebugPanelApp.registerCommandHandler(this);
        this.mStatusTimer.schedule(new TimerTask() {
            /* class com.oculus.panelapp.debug.CopresenceTab.AnonymousClass4 */

            public void run() {
                shellDebugPanelApp.getCommandChannel().sendCommand(CopresenceTab.IPC_COMMAND_STATUS);
            }
        }, 0, 1000);
    }

    @Override // com.oculus.panelapp.debug.IShellToPanelCommandHandler
    public void handleGenericMessage(String str) {
        String str2;
        if (str.startsWith(IPC_COMMAND_STATUS)) {
            int nextInt = new Scanner(str).skip(IPC_COMMAND_STATUS).nextInt();
            if (nextInt == 0) {
                str2 = "Incompatible ShellEnv build. Use 'maui sl shellenv-staging'.";
            } else if (nextInt == 1) {
                this.mConnectButton.setEnabled(true);
                this.mDisconnectButton.setEnabled(false);
                str2 = "Ready to connect.";
            } else if (nextInt != 2) {
                str2 = "Unknown Status " + nextInt;
            } else {
                this.mConnectButton.setEnabled(false);
                this.mDisconnectButton.setEnabled(true);
                str2 = "Connected.";
            }
            this.mStatusTextView.setText(str2);
        }
    }
}
