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
    public static final String DIALOG_LIST_ITEMS_KEY = "items";
    public static final String DIALOG_LIST_SELECTION_CHANGE_ACTION = "listSelectionChange";
    public static final String DIALOG_LIST_SEND_SELECTION_CHANGE_ACTION = "sendSelectionChangeAction";
    public static final String DIALOG_LIST_TITLE_KEY = "title";
    public static final String DIALOG_LIST_TYPE_KEY = "type";
    public static final String TAG = LoggingUtil.tag(DialogList.class);
    public final List<DialogListItem> mDialogListItems;
    public boolean mDialogListShouldSendSelectionChangeAction = false;
    @Nullable
    public DialogListTitle mDialogListTitle;
    public final DialogListType mDialogListType;

    public DialogList addListItem(DialogListItem dialogListItem) {
        if (dialogListItem == null) {
            Log.w(TAG, "Dialog list item must be non-null.");
            return this;
        }
        this.mDialogListItems.add(dialogListItem);
        return this;
    }

    public JSONObject getDialogListConfigurationIPCParcel() throws JSONException {
        String iPCString;
        try {
            JSONObject jSONObject = new JSONObject();
            if (this.mDialogListType == null) {
                Log.w(TAG, "Dialog list type was specified to be null, defaulting to type NO_SELECT.");
            }
            DialogListType dialogListType = this.mDialogListType;
            if (dialogListType == null) {
                iPCString = DialogListType.NO_SELECT.getIPCString();
            } else {
                iPCString = dialogListType.getIPCString();
            }
            jSONObject.put("type", iPCString);
            DialogListTitle dialogListTitle = this.mDialogListTitle;
            if (dialogListTitle != null) {
                jSONObject.put("title", dialogListTitle.getDialogListTitleConfigurationIPCParcel());
            }
            JSONArray jSONArray = new JSONArray();
            List<DialogListItem> list = this.mDialogListItems;
            if (list != null) {
                for (DialogListItem dialogListItem : list) {
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

    public List<DialogListItem> getDialogListItems() {
        return this.mDialogListItems;
    }

    public List<DialogListItem> getListItems() {
        return this.mDialogListItems;
    }

    @Nullable
    public DialogListTitle getListTitle() {
        return this.mDialogListTitle;
    }

    public boolean getShouldSendSelectionChangeAction() {
        return this.mDialogListShouldSendSelectionChangeAction;
    }

    public DialogListTitle getTitle() {
        return this.mDialogListTitle;
    }

    public DialogListType getType() {
        return this.mDialogListType;
    }

    public DialogList(DialogListType dialogListType) {
        this.mDialogListType = dialogListType;
        this.mDialogListItems = new ArrayList();
    }

    public void setShouldSendSelectionChangeAction(boolean z) {
        this.mDialogListShouldSendSelectionChangeAction = z;
    }

    public DialogList setTitle(DialogListTitle dialogListTitle) {
        this.mDialogListTitle = dialogListTitle;
        return this;
    }
}
