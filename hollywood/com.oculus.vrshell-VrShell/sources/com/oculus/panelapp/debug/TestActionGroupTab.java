package com.oculus.panelapp.debug;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;
import androidx.annotation.Nullable;
import com.oculus.panelapp.debug.DebugTabHost;

public final class TestActionGroupTab extends ScrollView implements DebugTabHost.DebugTab {
    @Override // com.oculus.panelapp.debug.DebugTabHost.DebugTab
    public void initialize(ShellDebugPanelApp shellDebugPanelApp, DebugTabHost debugTabHost) {
        debugTabHost.addTab("testactions", "Test Actions", R.id.debug_tab_test_action_group);
        ((DebugTabHost.DebugTab) findViewById(R.id.debug_tab_control)).initialize(shellDebugPanelApp, debugTabHost);
        ((DebugTabHost.DebugTab) findViewById(R.id.debug_tab_vrcast)).initialize(shellDebugPanelApp, debugTabHost);
        ((DebugTabHost.DebugTab) findViewById(R.id.debug_tab_controller_repairing)).initialize(shellDebugPanelApp, debugTabHost);
        ((DebugTabHost.DebugTab) findViewById(R.id.debug_tab_copresence)).initialize(shellDebugPanelApp, debugTabHost);
        ((DebugTabHost.DebugTab) findViewById(R.id.debug_tab_system_dialog)).initialize(shellDebugPanelApp, debugTabHost);
    }

    public TestActionGroupTab(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
