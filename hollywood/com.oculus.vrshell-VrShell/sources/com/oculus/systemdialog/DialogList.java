package com.oculus.systemdialog;

import android.util.Log;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class DialogList {
    private static final String DIALOG_LIST_ITEMS_KEY = "items";
    public static final String DIALOG_LIST_SELECTION_CHANGE_ACTION = "listSelectionChange";
    private static final String DIALOG_LIST_SEND_SELECTION_CHANGE_ACTION = "sendSelectionChangeAction";
    private static final String DIALOG_LIST_TITLE_KEY = "title";
    private static final String DIALOG_LIST_TYPE_KEY = "type";
    private static final String TAG = LoggingUtil.tag(DialogList.class);
    private List<DialogListItem> mDialogListItems;
    private boolean mDialogListShouldSendSelectionChangeAction = false;
    @Nullable
    private DialogListTitle mDialogListTitle;
    private final DialogListType mDialogListType;

    public DialogList(DialogListType dialogListType) {
        this.mDialogListType = dialogListType;
        this.mDialogListItems = new ArrayList();
    }

    public void setTitle(DialogListTitle dialogListTitle) {
        this.mDialogListTitle = dialogListTitle;
    }

    public void addListItem(DialogListItem dialogListItem) {
        if (dialogListItem == null) {
            Log.w(TAG, "Dialog list item must be non-null.");
            return;
        }
        if (this.mDialogListItems == null) {
            Log.w(TAG, "Dialog list items array was not initialized.");
            this.mDialogListItems = new ArrayList();
        }
        this.mDialogListItems.add(dialogListItem);
    }

    public void setShouldSendSelectionChangeAction(boolean z) {
        this.mDialogListShouldSendSelectionChangeAction = z;
    }

    public JSONObject getDialogListConfigurationIPCParcel() throws JSONException {
        String str;
        try {
            JSONObject jSONObject = new JSONObject();
            if (this.mDialogListType == null) {
                Log.w(TAG, "Dialog list type was specified to be null, defaulting to type NO_SELECT.");
            }
            if (this.mDialogListType == null) {
                str = DialogListType.NO_SELECT.getIPCString();
            } else {
                str = this.mDialogListType.getIPCString();
            }
            jSONObject.put("type", str);
            if (this.mDialogListTitle != null) {
                jSONObject.put("title", this.mDialogListTitle.getDialogListTitleConfigurationIPCParcel());
            }
            JSONArray jSONArray = new JSONArray();
            if (this.mDialogListItems != null) {
                for (DialogListItem dialogListItem : this.mDialogListItems) {
                    jSONArray.put(dialogListItem.getDialogListItemConfigurationIPCParcel());
                }
            }
            jSONObject.put(DIALOG_LIST_ITEMS_KEY, jSONArray);
            if (this.mDialogListShouldSendSelectionChangeAction) {
                jSONObject.put(DIALOG_LIST_SEND_SELECTION_CHANGE_ACTION, true);
            }
            return jSONObject;
        } catch (JSONException e) {
            Log.e(TAG, "Unable to get dialog list configuration IPC parcel.", e);
            throw e;
        }
    }
}
