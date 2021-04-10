package com.oculus.panelapp.androiddialog;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogResult;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class DialogAutomation {
    private static final String DIALOGS_KEY = "dialogs";
    private static final String ERROR_MESSAGE_KEY = "errorMessage";
    private static final String ERROR_RESULT = "error";
    private static final String RESULT_KEY = "result";
    private static final String SUCCESS_RESULT = "success";
    private static final String TAG = LoggingUtil.tag(DialogAutomation.class);

    enum Error {
        NO_CURRENT_DIALOG("no current dialog"),
        DIALOG_NOT_AUTOMATABLE("dialog is not automatable"),
        ACTION_NOT_FOR_CURRENT_DIALOG("action does not target current dialog"),
        ACTION_NOT_SUPPORTED("dialog does not support this action");

        private Error(String str) {
        }
    }

    private DialogAutomation() {
    }

    public static String getDialogQueryResponse(Dialog dialog) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("result", ERROR_RESULT);
            if (dialog == null) {
                jSONObject.put("errorMessage", Error.NO_CURRENT_DIALOG);
                return jSONObject.toString();
            }
            DialogDefinitionCustom dialogDefinition = dialog.getDialogDefinition();
            if (dialogDefinition == null) {
                jSONObject.put("errorMessage", Error.DIALOG_NOT_AUTOMATABLE);
                return jSONObject.toString();
            }
            JSONObject jSONObject2 = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(dialogDefinition.getDialogConfigurationJson());
            jSONObject2.put("result", "success");
            jSONObject2.put(DIALOGS_KEY, jSONArray);
            return jSONObject2.toString();
        } catch (JSONException e) {
            Log.e(TAG, "error building dialog query response", e);
            return jSONObject.toString();
        }
    }

    public static String getDialogActionResponse(Dialog dialog, JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("result", ERROR_RESULT);
            if (dialog == null) {
                jSONObject2.put("errorMessage", Error.NO_CURRENT_DIALOG);
                return jSONObject2.toString();
            }
            final DialogDefinitionCustom dialogDefinition = dialog.getDialogDefinition();
            if (dialogDefinition == null) {
                jSONObject2.put("errorMessage", Error.DIALOG_NOT_AUTOMATABLE);
                return jSONObject2.toString();
            }
            DialogResult fromJson = DialogResult.fromJson(jSONObject.toString());
            if (!fromJson.getDialogId().equals(dialog.getDialogDefinition().getDialogId())) {
                jSONObject2.put("errorMessage", Error.ACTION_NOT_FOR_CURRENT_DIALOG);
                return jSONObject2.toString();
            } else if (!new ArrayList<String>() {
                /* class com.oculus.panelapp.androiddialog.DialogAutomation.AnonymousClass1 */

                {
                    add(dialogDefinition.getPrimaryAction());
                    add(dialogDefinition.getSecondaryAction());
                    add(dialogDefinition.getTertiaryAction());
                }
            }.contains(fromJson.getDialogAction())) {
                jSONObject2.put("errorMessage", Error.ACTION_NOT_SUPPORTED);
                return jSONObject2.toString();
            } else {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("result", "success");
                return jSONObject3.toString();
            }
        } catch (JSONException e) {
            Log.e(TAG, "error building dialog action response", e);
            return jSONObject2.toString();
        }
    }
}
