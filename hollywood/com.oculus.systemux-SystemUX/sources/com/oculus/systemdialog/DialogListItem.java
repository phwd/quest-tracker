package com.oculus.systemdialog;

import android.util.Log;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import org.json.JSONException;
import org.json.JSONObject;

public final class DialogListItem {
    public static final String DIALOG_LIST_ITEM_BODY_TEXT_KEY = "bodyText";
    public static final String DIALOG_LIST_ITEM_ID_KEY = "id";
    public static final String DIALOG_LIST_ITEM_IMAGE_KEY = "image";
    public static final String DIALOG_LIST_ITEM_IMAGE_TYPE_KEY = "imageType";
    public static final String DIALOG_LIST_ITEM_PRESELECTED_KEY = "preselected";
    public static final String DIALOG_LIST_ITEM_TITLE_TEXT_KEY = "titleText";
    private static final String TAG = LoggingUtil.tag(DialogListItem.class);
    @Nullable
    private final String mBodyText;
    private final String mId;
    @Nullable
    private final String mImage;
    @Nullable
    private final DialogListItemImageType mImageType;
    private boolean mPreselected = false;
    private final String mTitleText;

    public DialogListItem(String str, String str2, @Nullable String str3, @Nullable String str4, @Nullable DialogListItemImageType dialogListItemImageType) {
        this.mId = str;
        this.mTitleText = str2;
        this.mBodyText = str3;
        this.mImage = str4;
        this.mImageType = dialogListItemImageType;
    }

    public void setPreselected(boolean z) {
        this.mPreselected = z;
    }

    public JSONObject getDialogListItemConfigurationIPCParcel() throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", this.mId);
            jSONObject.put(DIALOG_LIST_ITEM_TITLE_TEXT_KEY, this.mTitleText);
            if (this.mBodyText != null) {
                jSONObject.put(DIALOG_LIST_ITEM_BODY_TEXT_KEY, this.mBodyText);
            }
            if (this.mImage != null) {
                jSONObject.put("image", this.mImage);
                if (!(this.mImageType == null || this.mImageType == DialogListItemImageType.GLYPH)) {
                    jSONObject.put(DIALOG_LIST_ITEM_IMAGE_TYPE_KEY, this.mImageType.getIPCString());
                }
            }
            jSONObject.put(DIALOG_LIST_ITEM_PRESELECTED_KEY, this.mPreselected);
            return jSONObject;
        } catch (JSONException e) {
            Log.e(TAG, "Unable to get dialog list item configuration IPC parcel.", e);
            throw e;
        }
    }
}
