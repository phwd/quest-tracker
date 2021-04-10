package com.oculus.panelapp.assistant.dialogs;

import android.content.Context;
import com.oculus.assistant.service.api.panel.AssistantDialogContract;
import com.oculus.panelapp.assistant.AssistantPanelApp;
import com.oculus.panelapp.assistant.ui.SectionView;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MultiSuggestionDialog extends AssistantDialog implements IAssistantDialog {
    @Override // com.oculus.panelapp.assistant.dialogs.ISurface, com.oculus.panelapp.assistant.dialogs.AssistantDialog
    public String getSurfaceType() {
        return AssistantDialogContract.DialogTypes.MULTISUGGESTION_DIALOG;
    }

    public MultiSuggestionDialog(Context context, AssistantPanelApp assistantPanelApp, String str, String str2) {
        super(context, assistantPanelApp, str, str2);
    }

    public void addSection(SectionView sectionView) {
        addView(sectionView);
    }

    public static MultiSuggestionDialog createDialog(Context context, AssistantPanelApp assistantPanelApp, JSONObject jSONObject) throws JSONException {
        MultiSuggestionDialog multiSuggestionDialog = new MultiSuggestionDialog(context, assistantPanelApp, jSONObject.optString("id", UUID.randomUUID().toString()), jSONObject.optString("title", ""));
        multiSuggestionDialog.applyJson(jSONObject);
        if (jSONObject.has(AssistantDialogContract.MultiselectionDialog.SECTIONS)) {
            JSONArray jSONArray = jSONObject.getJSONArray(AssistantDialogContract.MultiselectionDialog.SECTIONS);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                String optString = jSONObject2.optString(AssistantDialogContract.MultiselectionDialog.Section.HEADER, "");
                SectionView sectionView = new SectionView(context);
                sectionView.setHeader(optString);
                sectionView.setItemClickedListener(new SectionView.OnItemClickedListener() {
                    /* class com.oculus.panelapp.assistant.dialogs.$$Lambda$MultiSuggestionDialog$lnQn2LJFfZef0psk8E0FzhaGYto */

                    @Override // com.oculus.panelapp.assistant.ui.SectionView.OnItemClickedListener
                    public final void onItemClicked(SectionView.SectionItem sectionItem) {
                        MultiSuggestionDialog.this.sendActionClick(sectionItem.getId());
                    }
                });
                multiSuggestionDialog.addSection(sectionView);
                if (jSONObject2.has(AssistantDialogContract.MultiselectionDialog.Section.ITEMS)) {
                    JSONArray jSONArray2 = jSONObject2.getJSONArray(AssistantDialogContract.MultiselectionDialog.Section.ITEMS);
                    for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                        JSONObject jSONObject3 = jSONArray2.getJSONObject(i2);
                        sectionView.addSectionItem(new SectionView.SectionItem(jSONObject3.optString("id", UUID.randomUUID().toString()), jSONObject3.getString(AssistantDialogContract.MultiselectionDialog.Items.LABEL)));
                    }
                }
            }
        }
        return multiSuggestionDialog;
    }
}
