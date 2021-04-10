package com.oculus.panelapp.debug;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.debug.DebugTabHost;
import com.oculus.vrshell.panels.SoundType;
import com.oculus.vrshell.panels.ViewState;

public final class ShellDebugView extends LinearLayout implements ViewState.Listener {
    private static final int[] TAB_IDS = {R.id.debug_tab_test_action_group, R.id.debug_tab_device, R.id.debug_tab_rendering_settings, R.id.debug_tab_gatekeepers, R.id.debug_tab_device_configs, R.id.debug_tab_vrdesktop_settings, R.id.debug_tab_status_group, R.id.debug_tab_quick_launch, R.id.debug_tab_preferences};
    private static final String TAG = LoggingUtil.tag(ShellDebugPanelApp.class);
    private ShellDebugPanelApp mPanelApp;

    @Override // com.oculus.vrshell.panels.ViewState.Listener
    public void onViewExit(View view) {
    }

    public ShellDebugView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void initialize(ShellDebugPanelApp shellDebugPanelApp) {
        this.mPanelApp = shellDebugPanelApp;
        DebugTabHost debugTabHost = (DebugTabHost) findViewById(R.id.debug_panel_tabhost);
        debugTabHost.setup();
        for (int i : TAB_IDS) {
            ((DebugTabHost.DebugTab) findViewById(i)).initialize(shellDebugPanelApp, debugTabHost);
        }
    }

    @Override // com.oculus.vrshell.panels.ViewState.Listener
    public void onViewEnter(View view) {
        this.mPanelApp.getCommandChannel().playAudio(SoundType.HOVER);
    }

    @Override // com.oculus.vrshell.panels.ViewState.Listener
    public void onViewClick(View view) {
        this.mPanelApp.getCommandChannel().playAudio(SoundType.SELECT);
    }
}
