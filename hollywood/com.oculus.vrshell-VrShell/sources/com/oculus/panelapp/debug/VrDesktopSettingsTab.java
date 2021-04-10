package com.oculus.panelapp.debug;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.debug.DebugTabHost;
import com.oculus.panelapp.debug.settings.Setting;
import com.oculus.panelapp.debug.settings.SystemPreferencesSchema;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class VrDesktopSettingsTab extends LinearLayout implements DebugTabHost.DebugTab {
    private static final String TAG = LoggingUtil.tag(VrDesktopSettingsTab.class);
    private final FeatureFlagsListAdapter mAdapter = new FeatureFlagsListAdapter();
    private final Map<String, List<Setting>> mVrDesktopSettings;

    public VrDesktopSettingsTab(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAdapter.shouldRenderHeaders(false);
        this.mVrDesktopSettings = new HashMap();
    }

    @Override // com.oculus.panelapp.debug.DebugTabHost.DebugTab
    public void initialize(ShellDebugPanelApp shellDebugPanelApp, DebugTabHost debugTabHost) {
        debugTabHost.addTab("vrdesktop_settings", "VRDesktop Settings", R.id.debug_tab_vrdesktop_settings);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.debug_vrdesktop_settings_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SystemPreferencesSchema.generateSettingsFromSchema(shellDebugPanelApp.getPreferencesManager(), this.mVrDesktopSettings, "VRDesktop Settings", SystemPreferencesSchema.getPreferencesSettings(SystemPreferencesSchema.getPreferencesMap(getContext(), SystemPreferencesSchema.SCHEMA_FILE_DEBUG_SETTINGS), new HashSet(Arrays.asList("debug_vrdesktop_enable_customization", "debug_vrdesktop_override_width", "debug_vrdesktop_override_height", "debug_vrdesktop_override_density", "debug_vrdesktop_override_panel_shape"))));
        this.mAdapter.setItems(this.mVrDesktopSettings);
        recyclerView.setAdapter(this.mAdapter);
    }
}
