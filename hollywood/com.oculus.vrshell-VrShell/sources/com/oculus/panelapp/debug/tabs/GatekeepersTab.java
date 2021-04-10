package com.oculus.panelapp.debug.tabs;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.debug.DebugTabHost;
import com.oculus.panelapp.debug.R;
import com.oculus.panelapp.debug.ShellDebugPanelApp;
import com.oculus.panelapp.debug.settings.ActionSetting;
import com.oculus.panelapp.debug.settings.GatekeeperSetting;
import com.oculus.vrshell.util.PreferenceStringHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class GatekeepersTab extends FilteredSettingsTab {
    private static final String PREF_OVERRIDE_GATEKEEPERS = "Override_Gatekeepers";
    private static final String PREF_SERVER_GATEKEEPERS = "Gatekeepers";
    private static final String TAG = LoggingUtil.tag(GatekeepersTab.class);

    public GatekeepersTab(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.oculus.panelapp.debug.DebugTabHost.DebugTab
    public void initialize(ShellDebugPanelApp shellDebugPanelApp, DebugTabHost debugTabHost) {
        debugTabHost.addTab("gatekeepers", PREF_SERVER_GATEKEEPERS, R.id.debug_tab_gatekeepers);
        JSONObject gKMap = getGKMap(this.mContext, PREF_SERVER_GATEKEEPERS);
        JSONObject gKMap2 = getGKMap(this.mContext, PREF_OVERRIDE_GATEKEEPERS);
        Iterator<String> keys = gKMap2.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                gKMap.put(next, gKMap2.optBoolean(next, false));
            } catch (JSONException unused) {
            }
        }
        ArrayList<String> arrayList = new ArrayList();
        Iterator<String> keys2 = gKMap.keys();
        while (keys2.hasNext()) {
            arrayList.add(keys2.next());
        }
        Collections.sort(arrayList);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new ActionSetting("Clear Server GKs (requires restart)", new Runnable() {
            /* class com.oculus.panelapp.debug.tabs.$$Lambda$GatekeepersTab$Map4s_frlnex4LokcGOhw5ZHjek */

            public final void run() {
                GatekeepersTab.this.lambda$initialize$0$GatekeepersTab();
            }
        }));
        arrayList2.add(new ActionSetting("Clear Override GKs (requires restart)", new Runnable() {
            /* class com.oculus.panelapp.debug.tabs.$$Lambda$GatekeepersTab$PD2S9zedIZqVqFNLLaGoJxztehA */

            public final void run() {
                GatekeepersTab.this.lambda$initialize$1$GatekeepersTab();
            }
        }));
        for (String str : arrayList) {
            arrayList2.add(new GatekeeperSetting(this.mContext, str, gKMap.optBoolean(str, false)));
        }
        HashMap hashMap = new HashMap();
        hashMap.put(PREF_SERVER_GATEKEEPERS, arrayList2);
        initializeSettings(hashMap);
    }

    public /* synthetic */ void lambda$initialize$0$GatekeepersTab() {
        Intent intent = new Intent("com.oculus.vrshell.automation.intent.action.GATEKEEPER");
        intent.setPackage("com.oculus.vrshell");
        intent.putExtra("gk_action", "clearAll");
        intent.putExtra("gk_name", "server");
        this.mContext.sendBroadcast(intent);
    }

    public /* synthetic */ void lambda$initialize$1$GatekeepersTab() {
        Intent intent = new Intent("com.oculus.vrshell.automation.intent.action.GATEKEEPER");
        intent.setPackage("com.oculus.vrshell");
        intent.putExtra("gk_action", "clearAll");
        intent.putExtra("gk_name", "override");
        this.mContext.sendBroadcast(intent);
    }

    private JSONObject getGKMap(Context context, String str) {
        try {
            return new JSONObject(PreferenceStringHelper.getPreferenceString(context, str));
        } catch (JSONException unused) {
            return new JSONObject();
        }
    }
}
