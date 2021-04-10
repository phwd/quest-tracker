package com.oculus.systemdialog;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import org.json.JSONException;
import org.json.JSONObject;

public final class DialogInlineImage {
    public static final String DIALOG_INLINE_IMAGE_IMAGE_KEY = "image";
    public static final String DIALOG_INLINE_IMAGE_PLACEHOLDER_KEY = "placeholder";
    public static final String TAG = LoggingUtil.tag(DialogInlineImage.class);
    public final String mImage;
    public final String mPlaceholder;

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

    public DialogInlineImage(String str, String str2) {
        this.mPlaceholder = str;
        this.mImage = str2;
    }
}
