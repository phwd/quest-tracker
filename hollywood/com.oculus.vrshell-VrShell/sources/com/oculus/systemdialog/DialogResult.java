package com.oculus.systemdialog;

import android.util.Log;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class DialogResult {
    public static final String DIALOG_ACTION_KEY = "action";
    public static final String DIALOG_AUTO_CLOSE_KEY = "autoClose";
    public static final String DIALOG_ID_KEY = "dialogId";
    public static final String DIALOG_SELECTED_LIST_ITEM_IDS_KEY = "listSelection";
    private static final String TAG = LoggingUtil.tag(DialogResult.class);
    private final String mDialogAction;
    private final boolean mDialogAutoClose;
    private final String mDialogId;
    private final List<String> mDialogSelectedListItemIds;

    @Nullable
    public static DialogResult fromJson(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("dialogId");
            String string2 = jSONObject.getString(DIALOG_ACTION_KEY);
            boolean optBoolean = jSONObject.optBoolean(DIALOG_AUTO_CLOSE_KEY, true);
            JSONArray optJSONArray = jSONObject.optJSONArray(DIALOG_SELECTED_LIST_ITEM_IDS_KEY);
            ArrayList arrayList = new ArrayList();
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    arrayList.add(optJSONArray.getString(i));
                }
            }
            return new DialogResult(string, string2, optBoolean, arrayList);
        } catch (JSONException unused) {
            String str2 = TAG;
            Log.e(str2, "Unable to parse dialog result from: " + str);
            return null;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{dialogId: ");
        sb.append(this.mDialogId);
        sb.append(", dialogAction: ");
        sb.append(this.mDialogAction);
        sb.append(", dialogAutoClose: ");
        sb.append(this.mDialogAutoClose);
        sb.append(", dialogSelectedListItemIds: ");
        List<String> list = this.mDialogSelectedListItemIds;
        sb.append(list == null ? "[]" : list.toString());
        sb.append("}");
        return sb.toString();
    }

    public String getDialogId() {
        return this.mDialogId;
    }

    public String getDialogAction() {
        return this.mDialogAction;
    }

    public boolean getDialogAutoClose() {
        return this.mDialogAutoClose;
    }

    public List<String> getDialogSelectedListItemIds() {
        return this.mDialogSelectedListItemIds;
    }

    private DialogResult(String str, String str2, boolean z, List<String> list) {
        this.mDialogId = str;
        this.mDialogAction = str2;
        this.mDialogAutoClose = z;
        this.mDialogSelectedListItemIds = list;
    }
}
