package com.facebook.mobileconfig.ui;

import android.util.Pair;
import androidx.annotation.Nullable;
import com.facebook.mobileconfig.namehandling.MobileConfigIdNameMappingLoader;
import com.facebook.mobileconfig.specifier.MobileConfigKeyUtils;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QEGKDefinitions {
    public List<GatekeeperDef> gatekeepers = new ArrayList();
    public MobileConfigIdNameMappingLoader idNameMap = new MobileConfigIdNameMappingLoader();
    public List<UniverseDef> universes = new ArrayList();

    public static class UniverseDef {
        public String currentExperiment = "";
        public String currentGroup = "";
        public List<ExperimentDef> experiments;
        public String name;
        public List<ParamDef> params;

        public static List<UniverseDef> parseJsonArray(JSONArray jSONArray) throws JSONException {
            LinkedList linkedList = new LinkedList();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                UniverseDef universeDef = new UniverseDef();
                universeDef.name = jSONObject.getString("name");
                universeDef.currentExperiment = jSONObject.optString("current_experiment");
                universeDef.currentGroup = jSONObject.optString("current_group");
                JSONArray optJSONArray = jSONObject.optJSONArray("params");
                if (optJSONArray != null) {
                    universeDef.params = ParamDef.parseJsonArray(optJSONArray);
                }
                JSONArray optJSONArray2 = jSONObject.optJSONArray("experiments");
                if (optJSONArray2 != null) {
                    universeDef.experiments = ExperimentDef.parseJsonArray(optJSONArray2);
                }
                linkedList.add(universeDef);
            }
            return linkedList;
        }
    }

    public static class ExperimentDef {
        public List<GroupDef> groups = new ArrayList();
        public String name;
        public List<ParamDef> params;
        public Integer size;

        public static List<ExperimentDef> parseJsonArray(JSONArray jSONArray) throws JSONException {
            LinkedList linkedList = new LinkedList();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                ExperimentDef experimentDef = new ExperimentDef();
                experimentDef.name = jSONObject.getString("name");
                if (jSONObject.has("size")) {
                    experimentDef.size = Integer.valueOf(jSONObject.getInt("size"));
                }
                JSONArray optJSONArray = jSONObject.optJSONArray("params");
                if (optJSONArray != null) {
                    experimentDef.params = ParamDef.parseJsonArray(optJSONArray);
                }
                JSONArray optJSONArray2 = jSONObject.optJSONArray("groups");
                if (optJSONArray2 != null) {
                    experimentDef.groups = GroupDef.parseJsonArray(optJSONArray2);
                }
                linkedList.add(experimentDef);
            }
            return linkedList;
        }
    }

    public static class GroupDef {
        public String name;
        public List<ParamDef> params;

        public static List<GroupDef> parseJsonArray(JSONArray jSONArray) throws JSONException {
            LinkedList linkedList = new LinkedList();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                GroupDef groupDef = new GroupDef();
                groupDef.name = jSONObject.getString("name");
                JSONArray optJSONArray = jSONObject.optJSONArray("params");
                if (optJSONArray != null) {
                    groupDef.params = ParamDef.parseJsonArray(optJSONArray);
                }
                linkedList.add(groupDef);
            }
            return linkedList;
        }
    }

    public static class ParamDef {
        public String config;
        public int key;
        public String paramName;
        public Object value;

        public Pair<String, String> getUniqueKey() {
            String str;
            if (!MobileConfigKeyUtils.isFakeParamKey(this.key) || (str = this.paramName) == null) {
                return new Pair<>(this.config, Integer.toString(this.key));
            }
            return new Pair<>(this.config, str);
        }

        public static List<ParamDef> parseJsonArray(JSONArray jSONArray) throws JSONException {
            LinkedList linkedList = new LinkedList();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                ParamDef paramDef = new ParamDef();
                paramDef.config = jSONObject.getString("config");
                paramDef.key = jSONObject.getInt("key");
                paramDef.value = jSONObject.opt("value");
                if (paramDef.value == JSONObject.NULL) {
                    paramDef.value = null;
                }
                paramDef.paramName = jSONObject.optString("param_name");
                linkedList.add(paramDef);
            }
            return linkedList;
        }
    }

    public static class GatekeeperDef {
        public String config;
        public int key;
        public String name;
        public String paramName;

        public Pair<String, String> getUniqueKey() {
            String str;
            if (!MobileConfigKeyUtils.isFakeParamKey(this.key) || (str = this.paramName) == null) {
                return new Pair<>(this.config, Integer.toString(this.key));
            }
            return new Pair<>(this.config, str);
        }

        public static List<GatekeeperDef> parseJsonArray(JSONArray jSONArray) throws JSONException {
            LinkedList linkedList = new LinkedList();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                GatekeeperDef gatekeeperDef = new GatekeeperDef();
                gatekeeperDef.name = jSONObject.getString("name");
                gatekeeperDef.config = jSONObject.getString("config");
                gatekeeperDef.paramName = jSONObject.getString("param_name");
                gatekeeperDef.key = jSONObject.getInt("key");
                linkedList.add(gatekeeperDef);
            }
            return linkedList;
        }
    }

    @Nullable
    public static QEGKDefinitions readFromString(@Nullable String str) {
        if (str == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            QEGKDefinitions qEGKDefinitions = new QEGKDefinitions();
            qEGKDefinitions.idNameMap.loadJsonResponseObject(jSONObject);
            JSONArray optJSONArray = jSONObject.optJSONArray("universes");
            if (optJSONArray != null) {
                qEGKDefinitions.universes.addAll(UniverseDef.parseJsonArray(optJSONArray));
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray("gatekeepers");
            if (optJSONArray2 != null) {
                qEGKDefinitions.gatekeepers.addAll(GatekeeperDef.parseJsonArray(optJSONArray2));
            }
            return qEGKDefinitions;
        } catch (JSONException unused) {
            return null;
        }
    }

    public static ParamDef createParamDef(String str, int i, Object obj) {
        ParamDef paramDef = new ParamDef();
        paramDef.config = str;
        paramDef.key = i;
        paramDef.value = obj;
        return paramDef;
    }

    public static GroupDef createGroupDef(String str, List<ParamDef> list) {
        GroupDef groupDef = new GroupDef();
        groupDef.name = str;
        groupDef.params = list;
        return groupDef;
    }

    public static ExperimentDef createExperimentDef(String str, int i, List<ParamDef> list, List<GroupDef> list2) {
        ExperimentDef experimentDef = new ExperimentDef();
        experimentDef.name = str;
        experimentDef.size = Integer.valueOf(i);
        experimentDef.params = list;
        experimentDef.groups = list2;
        return experimentDef;
    }

    public static UniverseDef createUniverseDef(String str, String str2, String str3, List<ExperimentDef> list, List<ParamDef> list2) {
        UniverseDef universeDef = new UniverseDef();
        universeDef.name = str;
        universeDef.currentExperiment = str2;
        universeDef.currentGroup = str3;
        universeDef.experiments = list;
        universeDef.params = list2;
        return universeDef;
    }
}
