package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.notifications;

import android.util.Log;
import com.oculus.assistant.service.api.panel.AssistantDialogContract;
import com.oculus.common.logutilities.LoggingUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationUserControlCategory {
    private static final String DEVELOPER_APPLICATION_CATEGORY = "DEVELOPER_APPLICATIONS";
    private static final String TAG = LoggingUtil.tag(NotificationUserControlCategory.class);
    private final String mCategory;
    private final String mDescription;
    private final List<NotificationUserControlCategoryPreference> mPreferences;
    private final String mTitle;

    public NotificationUserControlCategory(String str, String str2, String str3, List<NotificationUserControlCategoryPreference> list) {
        this.mCategory = str;
        this.mTitle = str2;
        this.mDescription = str3;
        this.mPreferences = list;
    }

    public String getCategory() {
        return this.mCategory;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public List<NotificationUserControlCategoryPreference> getPreferences() {
        return this.mPreferences;
    }

    public static NotificationUserControlCategory fromJSON(JSONObject jSONObject) {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("preferences");
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                NotificationUserControlCategoryPreference fromJSON = NotificationUserControlCategoryPreference.fromJSON(jSONArray.getJSONObject(i));
                if (fromJSON != null) {
                    arrayList.add(fromJSON);
                }
            }
            return new NotificationUserControlCategory(jSONObject.getString("category"), jSONObject.getString("title"), jSONObject.getString(AssistantDialogContract.Dialog.DESCRIPTION), arrayList);
        } catch (JSONException e) {
            Log.d(TAG, "Error parsing json", e);
            return null;
        }
    }

    public boolean isDeveloperApplication() {
        return DEVELOPER_APPLICATION_CATEGORY.equals(this.mCategory);
    }
}
