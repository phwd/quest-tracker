package com.oculus.systemdialog;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import org.json.JSONException;
import org.json.JSONObject;

public final class DialogListTitle {
    public static final String DIALOG_LIST_TITLE_SHOW_PROGRESS_INDICATOR_KEY = "showProgressIndicator";
    public static final String DIALOG_LIST_TITLE_TEXT_KEY = "text";
    public static final String TAG = LoggingUtil.tag(DialogListTitle.class);
    public final boolean mShowProgressIndicator;
    public final String mText;

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

    public boolean getShowProgressIndicator() {
        return this.mShowProgressIndicator;
    }

    public String getText() {
        return this.mText;
    }

    public DialogListTitle(String str, boolean z) {
        this.mText = str;
        this.mShowProgressIndicator = z;
    }
}
