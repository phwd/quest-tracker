package com.oculus.systemdialog;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import org.json.JSONException;
import org.json.JSONObject;

public final class DialogListTitle {
    public static final String DIALOG_LIST_TITLE_SHOW_PROGRESS_INDICATOR_KEY = "showProgressIndicator";
    public static final String DIALOG_LIST_TITLE_TEXT_KEY = "text";
    private static final String TAG = LoggingUtil.tag(DialogListTitle.class);
    private final boolean mShowProgressIndicator;
    private final String mText;

    public DialogListTitle(String str, boolean z) {
        this.mText = str;
        this.mShowProgressIndicator = z;
    }

    public JSONObject getDialogListTitleConfigurationIPCParcel() throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("text", this.mText);
            if (this.mShowProgressIndicator) {
                jSONObject.put(DIALOG_LIST_TITLE_SHOW_PROGRESS_INDICATOR_KEY, true);
            }
            return jSONObject;
        } catch (JSONException e) {
            Log.e(TAG, "Unable to get dialog list title configuration IPC parcel.", e);
            throw e;
        }
    }
}
