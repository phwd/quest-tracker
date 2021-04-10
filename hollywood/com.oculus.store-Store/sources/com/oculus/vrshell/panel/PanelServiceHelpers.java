package com.oculus.vrshell.panel;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import java.util.ArrayList;

public final class PanelServiceHelpers {
    private static final String TAG = "PanelService";

    public static void waitForDebuggerIfNeeded(Bundle b) {
        if (b.containsKey("_oc_shell_panel_app_wait_for_debugger")) {
            Log.d(TAG, "Waiting for debugger.");
            Debug.waitForDebugger();
            Log.d(TAG, "Debugger connected.");
        }
    }

    public static String[] envArrayFromBundle(Bundle b) {
        if (b == null) {
            return null;
        }
        ArrayList<String> envArray = new ArrayList<>();
        for (String k : b.keySet()) {
            CharSequence cs = b.getCharSequence(k);
            if (cs != null) {
                String v = cs.toString();
                envArray.add(k);
                envArray.add(v);
            }
        }
        return (String[]) envArray.toArray(new String[envArray.size()]);
    }

    public static String envArrayToString(String[] envArray) {
        if (envArray == null) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        boolean first = true;
        for (int i = 0; i < envArray.length; i += 2) {
            if (!first) {
                sb.append(", ");
            }
            sb.append(envArray[i]);
            sb.append("=\"");
            sb.append(envArray[i + 1]);
            sb.append("\"");
            first = false;
        }
        sb.append("}");
        return sb.toString();
    }
}
