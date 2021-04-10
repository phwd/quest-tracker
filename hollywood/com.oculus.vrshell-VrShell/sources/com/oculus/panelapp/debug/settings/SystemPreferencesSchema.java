package com.oculus.panelapp.debug.settings;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.PreferencesManager;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SystemPreferencesSchema {
    private static final String SCHEMA_CONTENTS_KEY = "contents";
    public static final String SCHEMA_FILE_DEBUG_SETTINGS = "debug_settings_schema";
    public static final String SCHEMA_FILE_SHELL_SETTINGS = "preferences_vrshell_schema";
    private static final String SCHEMA_PREFKEY_KEY = "key";
    private static final String SCHEMA_TYPE_KEY = "type";
    private static final String SCHEMA_TYPE_VALUE_BOOLEAN = "boolean";
    private static final String SCHEMA_TYPE_VALUE_FLOAT = "float";
    private static final String SCHEMA_TYPE_VALUE_INTEGER = "integer";
    private static final String SCHEMA_UI_CATEGORY_KEY = "ui_category";
    private static final String SCHEMA_UI_DESCRIPTION_KEY = "ui_description";
    private static final String SCHEMA_UI_VALUES_KEY = "ui_values";
    private static final String TAG = LoggingUtil.tag(SystemPreferencesSchema.class);

    public static JSONObject getPreferencesMap(Context context, String str) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier(str, "raw", context.getPackageName());
        if (identifier == 0) {
            return null;
        }
        try {
            InputStream openRawResource = resources.openRawResource(identifier);
            try {
                byte[] bArr = new byte[openRawResource.available()];
                openRawResource.read(bArr);
                JSONObject jSONObject = new JSONObject(new String(bArr));
                if (openRawResource != null) {
                    try {
                        openRawResource.close();
                    } catch (Exception unused) {
                    }
                }
                return jSONObject;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } catch (Exception unused2) {
            return null;
        }
        throw th;
    }

    public static List<JSONObject> getPreferencesSettings(JSONObject jSONObject, Set<String> set) {
        JSONArray optJSONArray;
        ArrayList arrayList = new ArrayList();
        if (!(jSONObject == null || (optJSONArray = jSONObject.optJSONArray(SCHEMA_CONTENTS_KEY)) == null)) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                try {
                    String optString = optJSONArray.getJSONObject(i).optString(SCHEMA_PREFKEY_KEY);
                    if (set == null || set.contains(optString)) {
                        arrayList.add(optJSONArray.getJSONObject(i));
                    }
                } catch (JSONException unused) {
                }
            }
        }
        return arrayList;
    }

    public static void generateSettingsFromSchema(PreferencesManager preferencesManager, Map<String, List<Setting>> map, String str, List<JSONObject> list) {
        Setting setting;
        for (JSONObject jSONObject : list) {
            try {
                String optString = jSONObject.optString(SCHEMA_PREFKEY_KEY);
                String string = jSONObject.getString("type");
                String optString2 = jSONObject.optString(SCHEMA_UI_CATEGORY_KEY);
                JSONArray optJSONArray = jSONObject.optJSONArray(SCHEMA_UI_VALUES_KEY);
                String optString3 = jSONObject.optString(SCHEMA_UI_DESCRIPTION_KEY);
                String format = !TextUtils.isEmpty(optString3) ? String.format(Locale.ROOT, "%s\n%s", optString, optString3) : optString;
                char c = 65535;
                int hashCode = string.hashCode();
                if (hashCode != 64711720) {
                    if (hashCode != 97526364) {
                        if (hashCode == 1958052158) {
                            if (string.equals(SCHEMA_TYPE_VALUE_INTEGER)) {
                                c = 1;
                            }
                        }
                    } else if (string.equals(SCHEMA_TYPE_VALUE_FLOAT)) {
                        c = 2;
                    }
                } else if (string.equals(SCHEMA_TYPE_VALUE_BOOLEAN)) {
                    c = 0;
                }
                if (c == 0) {
                    setting = new SystemPreferencesBooleanSetting(preferencesManager, optString, format);
                } else if (c == 1) {
                    setting = new SystemPreferencesIntegerSetting(preferencesManager, optString, format, getPresetsFromArray(optJSONArray));
                } else if (c != 2) {
                    Log.w(TAG, "Unknown or Unsuppported type specified in schema: " + string);
                    setting = null;
                } else {
                    setting = new SystemPreferencesFloatSetting(preferencesManager, optString, format, getPresetsFromArray(optJSONArray));
                }
                if (setting != null) {
                    if (TextUtils.isEmpty(optString2)) {
                        optString2 = str;
                    }
                    if (!map.containsKey(optString2)) {
                        map.put(optString2, new ArrayList());
                    }
                    map.get(optString2).add(setting);
                }
            } catch (JSONException unused) {
                Log.w(TAG, "Failed to parse all settings due to a JSON parsing error.");
            }
        }
    }

    private static List<String> getPresetsFromArray(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                arrayList.add(jSONArray.getString(i));
            } catch (JSONException unused) {
                Log.w(TAG, "Failed to parse presets due to a JSON parsing error.");
            }
        }
        return arrayList;
    }
}
