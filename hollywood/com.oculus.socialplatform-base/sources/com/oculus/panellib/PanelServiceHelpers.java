package com.oculus.panellib;

import android.os.Bundle;
import android.os.Debug;
import java.util.ArrayList;

public final class PanelServiceHelpers {
    public static final String TAG = "CPanelService";

    public static String[] envArrayFromBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : bundle.keySet()) {
            CharSequence charSequence = bundle.getCharSequence(str);
            if (charSequence != null) {
                String charSequence2 = charSequence.toString();
                arrayList.add(str);
                arrayList.add(charSequence2);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static String envArrayToString(String[] strArr) {
        if (strArr == null) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        int i = 0;
        boolean z = true;
        while (i < strArr.length) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(strArr[i]);
            sb.append("=\"");
            sb.append(strArr[i + 1]);
            sb.append("\"");
            i += 2;
            z = false;
        }
        sb.append("}");
        return sb.toString();
    }

    public static void waitForDebuggerIfNeeded(Bundle bundle) {
        if (bundle.containsKey("_oc_shell_panel_app_wait_for_debugger")) {
            Debug.waitForDebugger();
        }
    }
}
