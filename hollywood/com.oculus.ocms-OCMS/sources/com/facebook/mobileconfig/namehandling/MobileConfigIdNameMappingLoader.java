package com.facebook.mobileconfig.namehandling;

import com.facebook.debug.log.BLog;
import com.facebook.mobileconfig.impl.MobileConfigFilesOnDiskUtils;
import com.facebook.mobileconfig.metadata.ParamsMapList;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MobileConfigIdNameMappingLoader {
    public static final String ID_NAME_MAPPING_FILE_NAME = "id_name_mapping.json";
    public static final String POSSIBLE_SUB_DIR = "mobileconfig";
    public static final String TAG = "MobileConfigIdNameMappingLoader";
    public Map<Integer, Map<String, String>> map = new HashMap();

    public boolean valid() {
        return !this.map.isEmpty();
    }

    public static ParamsMapList loadIdNameMapAndGetNamedParamsList(String str, ParamsMapList paramsMapList) {
        MobileConfigIdNameMappingLoader mobileConfigIdNameMappingLoader = new MobileConfigIdNameMappingLoader();
        mobileConfigIdNameMappingLoader.loadIdNameMappingFile(str);
        if (!mobileConfigIdNameMappingLoader.valid() || paramsMapList == null || paramsMapList.entries.isEmpty()) {
            return null;
        }
        return mobileConfigIdNameMappingLoader.getNamedParamsMapList(paramsMapList);
    }

    public void loadIdNameMappingFile(String str) {
        File file = new File(str);
        if (file.isDirectory()) {
            file = new File(str, ID_NAME_MAPPING_FILE_NAME);
            if (!file.exists()) {
                file = new File(new File(str, POSSIBLE_SUB_DIR), ID_NAME_MAPPING_FILE_NAME);
            }
        }
        if (file.exists()) {
            loadIdNameMappingFile(file);
        }
    }

    public void loadIdNameMappingFile(File file) {
        try {
            parseIdNameJsonObject(new JSONArray(MobileConfigFilesOnDiskUtils.getFileContentAsString(file.toString())));
        } catch (Exception e) {
            BLog.e(TAG, e.toString(), e);
        }
    }

    public void loadJsonResponseObject(JSONObject jSONObject) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("id_to_names");
            if (optJSONArray != null) {
                parseIdNameJsonObject(optJSONArray);
            }
        } catch (JSONException e) {
            BLog.e(TAG, e.toString(), e);
        }
    }

    public void loadFromJsonResponse(String str) {
        try {
            loadJsonResponseObject(new JSONObject(str));
        } catch (JSONException e) {
            BLog.e(TAG, e.toString(), e);
        }
    }

    private void parseIdNameJsonObject(JSONArray jSONArray) throws JSONException {
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                String string = jSONArray.getString(i);
                if (string != null) {
                    String[] split = string.split(":");
                    Integer valueOf = Integer.valueOf(Integer.parseInt(split[0]));
                    String str = split[1];
                    HashMap hashMap = new HashMap();
                    hashMap.put("config_name", str);
                    for (int i2 = 2; i2 < split.length; i2 += 2) {
                        hashMap.put(split[i2], split[i2 + 1]);
                    }
                    this.map.put(valueOf, hashMap);
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0073  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.mobileconfig.metadata.ParamsMapList getNamedParamsMapList(com.facebook.mobileconfig.metadata.ParamsMapList r20) {
        /*
        // Method dump skipped, instructions count: 195
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.mobileconfig.namehandling.MobileConfigIdNameMappingLoader.getNamedParamsMapList(com.facebook.mobileconfig.metadata.ParamsMapList):com.facebook.mobileconfig.metadata.ParamsMapList");
    }
}
