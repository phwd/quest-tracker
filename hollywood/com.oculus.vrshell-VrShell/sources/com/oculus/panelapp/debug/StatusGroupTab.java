package com.oculus.panelapp.debug;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;
import androidx.annotation.Nullable;
import com.oculus.panelapp.debug.DebugTabHost;

public final class StatusGroupTab extends ScrollView implements DebugTabHost.DebugTab {
    @Override // com.oculus.panelapp.debug.DebugTabHost.DebugTab
    public void initialize(ShellDebugPanelApp shellDebugPanelApp, DebugTabHost debugTabHost) {
        debugTabHost.addTab("statusgroup", "Status", R.id.debug_tab_status_group);
        ((DebugTabHost.DebugTab) findViewById(R.id.debug_tab_tracking)).initialize(shellDebugPanelApp, debugTabHost);
        ((DebugTabHost.DebugTab) findViewById(R.id.debug_tab_xrsp)).initialize(shellDebugPanelApp, debugTabHost);
    }

    public StatusGroupTab(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
