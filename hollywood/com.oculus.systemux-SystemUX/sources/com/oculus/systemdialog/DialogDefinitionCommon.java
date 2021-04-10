package com.oculus.systemdialog;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class DialogDefinitionCommon extends DialogDefinitionBase {
    public static final String DIALOG_PARAMETERS_KEY = "parameters";
    private static final String TAG = LoggingUtil.tag(DialogDefinitionCommon.class);
    private Map<String, String> mParameters;

    public DialogDefinitionCommon(CommonSystemDialog commonSystemDialog) {
        this(commonSystemDialog, null);
    }

    public DialogDefinitionCommon(CommonSystemDialog commonSystemDialog, Map<String, String> map) {
        super(commonSystemDialog.getDialogId());
        if (map != null) {
            this.mParameters = map;
        } else {
            this.mParameters = new HashMap();
        }
    }

    public void setParameter(String str, String str2) {
        if (str == null || str2 == null) {
            Log.w(TAG, "Invalid parameter or value set to null.");
        } else {
            this.mParameters.put(str, str2);
        }
    }

    @Override // com.oculus.systemdialog.DialogDefinitionBase
    public String getDialogConfigurationIPCParcel() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", DialogDefinitionBase.DIALOG_TYPE_COMMON_VALUE);
            jSONObject.put("dialogId", this.mDialogId);
            JSONObject jSONObject2 = new JSONObject();
            for (Map.Entry<String, String> entry : this.mParameters.entrySet()) {
                jSONObject2.put(entry.getKey(), entry.getValue());
            }
            jSONObject.put(DIALOG_PARAMETERS_KEY, jSONObject2);
            return jSONObject.toString();
        } catch (JSONException e) {
            Log.e(TAG, "Unable to get dialog configuration JSON.", e);
            return "";
        }
    }
}
