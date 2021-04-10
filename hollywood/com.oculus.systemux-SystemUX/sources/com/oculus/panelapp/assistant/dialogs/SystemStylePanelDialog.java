package com.oculus.panelapp.assistant.dialogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.oculus.assistant.service.api.panel.AssistantDialogContract;
import com.oculus.panelapp.assistant.AssistantPanelApp;
import com.oculus.panelapp.assistant.R;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class SystemStylePanelDialog extends AssistantDialog {
    @Override // com.oculus.panelapp.assistant.dialogs.ISurface, com.oculus.panelapp.assistant.dialogs.AssistantDialog
    public String getSurfaceType() {
        return AssistantDialogContract.DialogTypes.SYSTEM_DIALOG;
    }

    public SystemStylePanelDialog(Context context, AssistantPanelApp assistantPanelApp, String str, String str2) {
        super(context, assistantPanelApp, str, str2);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.AssistantDialog
    public View onCreateView(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.system_dialog, (ViewGroup) null, false);
    }

    public static SystemStylePanelDialog createDialog(Context context, AssistantPanelApp assistantPanelApp, JSONObject jSONObject) throws JSONException {
        SystemStylePanelDialog systemStylePanelDialog = new SystemStylePanelDialog(context, assistantPanelApp, jSONObject.optString("id", UUID.randomUUID().toString()), jSONObject.optString("title", ""));
        systemStylePanelDialog.applyJson(jSONObject);
        if (jSONObject.has(AssistantDialogContract.Dialog.DESCRIPTION)) {
            ((TextView) systemStylePanelDialog.findViewById(R.id.description)).setText(jSONObject.getString(AssistantDialogContract.Dialog.DESCRIPTION));
        }
        return systemStylePanelDialog;
    }
}
