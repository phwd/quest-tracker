package com.oculus.modules.codegen;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class NativeModuleParcel {
    public abstract JSONObject convertToJSONObject();

    public abstract void setFromJSONObject(JSONObject jSONObject);

    protected static final <T extends Enum<T>> JSONArray convertEnumListToJSONArray(List<T> list) {
        JSONArray array = new JSONArray();
        for (T item : list) {
            array.put(item.name());
        }
        return array;
    }

    protected static final JSONArray convertObjectListToJSONArray(List<JSONObject> list) {
        JSONArray array = new JSONArray();
        for (JSONObject item : list) {
            array.put(item);
        }
        return array;
    }

    protected static final <T extends NativeModuleParcel> JSONArray convertParcelListToJSONArray(List<T> list) {
        JSONArray array = new JSONArray();
        for (T item : list) {
            array.put(item.convertToJSONObject());
        }
        return array;
    }

    protected static final JSONArray convertStringListToJSONArray(List<String> list) {
        JSONArray array = new JSONArray();
        for (String item : list) {
            array.put(item);
        }
        return array;
    }

    protected static final ArrayList<JSONObject> convertJSONArrayToObjectList(JSONArray array) {
        ArrayList<JSONObject> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add(array.optJSONObject(i));
        }
        return list;
    }

    protected static final <T extends NativeModuleParcel> ArrayList<T> convertJSONArrayToParcelList(JSONArray array, Class<T> cls) {
        ArrayList<T> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                T item = cls.newInstance();
                item.setFromJSONObject(array.optJSONObject(i));
                list.add(item);
            } catch (IllegalAccessException | InstantiationException e) {
            }
        }
        return list;
    }

    protected static final ArrayList<String> convertJSONArrayToStringList(JSONArray array) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add(array.optString(i));
        }
        return list;
    }
}
