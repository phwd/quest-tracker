package com.oculus.panelapp.debug;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.debug.DebugTabHost;
import com.oculus.panelapp.debug.settings.Setting;
import com.oculus.panelapp.debug.settings.SystemPreferencesSchema;
import com.oculus.vrshell.util.AndroidSystemProperties;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class RenderingSettingsTab extends LinearLayout implements DebugTabHost.DebugTab {
    private static final String ENVIRONMENT_ARG_OC_SHELL_PIXEL_DENSITY = "_oc_shell_pixel_density";
    private static final String ENVIRONMENT_ARG_OC_SHELL_RENDER_SCALE = "_oc_shell_render_scale";
    private static final String TAG = LoggingUtil.tag(RenderingSettingsTab.class);
    private final FeatureFlagsListAdapter mAdapter = new FeatureFlagsListAdapter();
    private final Map<String, List<Setting>> mRenderSettings;

    public RenderingSettingsTab(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAdapter.shouldRenderHeaders(false);
        this.mRenderSettings = new HashMap();
    }

    @Override // com.oculus.panelapp.debug.DebugTabHost.DebugTab
    public void initialize(final ShellDebugPanelApp shellDebugPanelApp, DebugTabHost debugTabHost) {
        debugTabHost.addTab("rendering_settings", "Rendering Settings", R.id.debug_tab_rendering_settings);
        ((TextView) findViewById(R.id.debug_text_current_rendering_quality)).setText(shellDebugPanelApp.getEnvironmentArg(ENVIRONMENT_ARG_OC_SHELL_PIXEL_DENSITY));
        ((TextView) findViewById(R.id.debug_text_current_panel_scale)).setText(shellDebugPanelApp.getEnvironmentArg(ENVIRONMENT_ARG_OC_SHELL_RENDER_SCALE));
        ((TextView) findViewById(R.id.debug_text_system_rendering_quality)).setText(AndroidSystemProperties.getSystemPropertyString("ro.ovr.displaydensity", "<unset>"));
        ((TextView) findViewById(R.id.debug_text_system_panel_scale)).setText(AndroidSystemProperties.getSystemPropertyString("ro.ovr.layoutscale", "<unset>"));
        ((Button) findViewById(R.id.debug_action_exit_shell)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.RenderingSettingsTab.AnonymousClass1 */

            public void onClick(View view) {
                shellDebugPanelApp.getCommandChannel().sendCommand("shellDebug shutdown");
            }
        });
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.debug_rendering_settings_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SystemPreferencesSchema.generateSettingsFromSchema(shellDebugPanelApp.getPreferencesManager(), this.mRenderSettings, "Pixel Density", SystemPreferencesSchema.getPreferencesSettings(SystemPreferencesSchema.getPreferencesMap(getContext(), SystemPreferencesSchema.SCHEMA_FILE_DEBUG_SETTINGS), new HashSet(Arrays.asList("debug_allow_override_pixel_density", "debug_override_display_density", "debug_override_render_density"))));
        this.mAdapter.setItems(this.mRenderSettings);
        recyclerView.setAdapter(this.mAdapter);
    }
}
