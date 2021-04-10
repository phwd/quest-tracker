package com.oculus.panelapp.debug.tabs;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.PreferencesManager;
import com.oculus.panelapp.debug.DebugTabHost;
import com.oculus.panelapp.debug.R;
import com.oculus.panelapp.debug.ShellDebugPanelApp;
import com.oculus.panelapp.debug.settings.SystemPreferencesSchema;
import java.util.HashMap;
import org.json.JSONObject;

public class PreferencesTab extends FilteredSettingsTab {
    private static final String TAG = LoggingUtil.tag(PreferencesTab.class);

    public PreferencesTab(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.oculus.panelapp.debug.DebugTabHost.DebugTab
    public void initialize(ShellDebugPanelApp shellDebugPanelApp, DebugTabHost debugTabHost) {
        debugTabHost.addTab("preferences", "Preferences", R.id.debug_tab_preferences);
        PreferencesManager preferencesManager = shellDebugPanelApp.getPreferencesManager();
        HashMap hashMap = new HashMap();
        JSONObject preferencesMap = SystemPreferencesSchema.getPreferencesMap(this.mContext, SystemPreferencesSchema.SCHEMA_FILE_DEBUG_SETTINGS);
        JSONObject preferencesMap2 = SystemPreferencesSchema.getPreferencesMap(this.mContext, SystemPreferencesSchema.SCHEMA_FILE_SHELL_SETTINGS);
        SystemPreferencesSchema.generateSettingsFromSchema(preferencesManager, hashMap, "Debug Preferences", SystemPreferencesSchema.getPreferencesSettings(preferencesMap, null));
        SystemPreferencesSchema.generateSettingsFromSchema(preferencesManager, hashMap, "Shell Preferences", SystemPreferencesSchema.getPreferencesSettings(preferencesMap2, null));
        initializeSettings(hashMap);
    }
}
