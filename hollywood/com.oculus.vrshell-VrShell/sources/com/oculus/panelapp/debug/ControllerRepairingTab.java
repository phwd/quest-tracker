package com.oculus.panelapp.debug;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.debug.DebugTabHost;
import com.oculus.vrshell.panels.views.ShellButton;

class ControllerRepairingTab extends LinearLayout implements DebugTabHost.DebugTab {
    private static final String TAG = LoggingUtil.tag(ControllerRepairingTab.class);

    public ControllerRepairingTab(@NonNull Context context) {
        this(context, null);
    }

    public ControllerRepairingTab(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.oculus.panelapp.debug.DebugTabHost.DebugTab
    public void initialize(final ShellDebugPanelApp shellDebugPanelApp, DebugTabHost debugTabHost) {
        Log.d(TAG, "Initializing controller repairing tab");
        ((ShellButton) findViewById(R.id.controller_repairing_button)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.ControllerRepairingTab.AnonymousClass1 */

            public void onClick(View view) {
                shellDebugPanelApp.getCommandChannel().sendCommand("launch systemux://dialog/controller-pairing");
            }
        });
    }
}
