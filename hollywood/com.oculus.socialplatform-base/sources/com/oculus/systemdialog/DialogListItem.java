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
    public static final String TAG = LoggingUtil.tag(DialogListItem.class);
    @Nullable
    public final String mBodyText;
    public final String mId;
    @Nullable
    public final String mImage;
    @Nullable
    public final DialogListItemImageType mImageType;
    public boolean mPreselected = false;
    public final String mTitleText;

    @Nullable
    public String getBodyText() {
        return this.mBodyText;
    }

    public JSONObject getDialogListItemConfigurationIPCParcel() throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", this.mId);
            jSONObject.put(DIALOG_LIST_ITEM_TITLE_TEXT_KEY, this.mTitleText);
            String str = this.mBodyText;
            if (str != null) {
                jSONObject.put(DIALOG_LIST_ITEM_BODY_TEXT_KEY, str);
            }
            String str2 = this.mImage;
            if (str2 != null) {
                jSONObject.put("image", str2);
                DialogListItemImageType dialogListItemImageType = this.mImageType;
                if (!(dialogListItemImageType == null || dialogListItemImageType == DialogListItemImageType.GLYPH)) {
                    jSONObject.put(DIALOG_LIST_ITEM_IMAGE_TYPE_KEY, dialogListItemImageType.getIPCString());
                }
            }
            jSONObject.put(DIALOG_LIST_ITEM_PRESELECTED_KEY, this.mPreselected);
            return jSONObject;
        } catch (JSONException e) {
            Log.e(TAG, "Unable to get dialog list item configuration IPC parcel.", e);
            throw e;
        }
    }

    public String getId() {
        return this.mId;
    }

    @Nullable
    public String getImage() {
        return this.mImage;
    }

    @Nullable
    public DialogListItemImageType getImageType() {
        return this.mImageType;
    }

    public boolean getPreselected() {
        return this.mPreselected;
    }

    public String getTitleText() {
        return this.mTitleText;
    }

    public DialogListItem(String str, String str2, @Nullable String str3, @Nullable String str4, @Nullable DialogListItemImageType dialogListItemImageType) {
        this.mId = str;
        this.mTitleText = str2;
        this.mBodyText = str3;
        this.mImage = str4;
        this.mImageType = dialogListItemImageType;
    }

    public DialogListItem setPreselected(boolean z) {
        this.mPreselected = z;
        return this;
    }
}
