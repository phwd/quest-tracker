package com.oculus.systemdialog;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import org.json.JSONException;
import org.json.JSONObject;

public final class DialogInlineImage {
    private static final String DIALOG_INLINE_IMAGE_IMAGE_KEY = "image";
    private static final String DIALOG_INLINE_IMAGE_PLACEHOLDER_KEY = "placeholder";
    private static final String TAG = LoggingUtil.tag(DialogInlineImage.class);
    private final String mImage;
    private final String mPlaceholder;

    public DialogInlineImage(String str, String str2) {
        this.mPlaceholder = str;
        this.mImage = str2;
    }

    public JSONObject getDialogInlineImageConfigurationIPCParcel() throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(DIALOG_INLINE_IMAGE_PLACEHOLDER_KEY, this.mPlaceholder);
            jSONObject.put("image", this.mImage);
            return jSONObject;
        } catch (JSONException e) {
            Log.e(TAG, "Unable to get dialog inline image configuration IPC parcel.", e);
            throw e;
        }
    }
}
