package com.oculus.modules.codegen;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class NativeModuleParcel {
    public abstract JSONObject convertToJSONObject();

    public abstract void setFromJSONObject(JSONObject jSONObject);

    public final String convertToParcelString() {
        return convertToJSONObject().toString();
    }

    protected static final JSONArray convertBooleanListToJSONArray(List<Boolean> list) {
        JSONArray array = new JSONArray();
        for (Boolean item : list) {
            array.put(item);
        }
        return array;
    }

    protected static final JSONArray convertDoubleListToJSONArray(List<Double> list) {
        JSONArray array = new JSONArray();
        for (Double item : list) {
            array.put(item);
        }
        return array;
    }

    protected static final <T extends Enum<T>> JSONArray convertEnumListToJSONArray(List<T> list) {
        JSONArray array = new JSONArray();
        for (T item : list) {
            array.put(item.name());
        }
        return array;
    }

    protected static final JSONArray convertIntListToJSONArray(List<Integer> list) {
        JSONArray array = new JSONArray();
        for (Integer num : list) {
            array.put(num.intValue());
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

    protected static final JSONArray convertVoidListToJSONArray(List<Void> list) {
        JSONArray array = new JSONArray();
        for (Void item : list) {
            array.put(item);
        }
        return array;
    }

    protected static final ArrayList<Boolean> convertJSONArrayToBooleanList(JSONArray array) {
        ArrayList<Boolean> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add(Boolean.valueOf(array.optBoolean(i)));
        }
        return list;
    }

    protected static final ArrayList<Double> convertJSONArrayToDoubleList(JSONArray array) {
        ArrayList<Double> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add(Double.valueOf(array.optDouble(i)));
        }
        return list;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.util.ArrayList<T extends java.lang.Enum<T>> */
    /* JADX WARN: Multi-variable type inference failed */
    protected static final <T extends Enum<T>> ArrayList<T> convertJSONArrayToEnumList(JSONArray array, Class<T> cls) {
        ArrayList<T> list = (ArrayList<T>) new ArrayList();
        for (int i = 0; i < array.length(); i++) {
            list.add(Enum.valueOf(cls, array.optString(i)));
        }
        return list;
    }

    protected static final ArrayList<Integer> convertJSONArrayToIntList(JSONArray array) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add(Integer.valueOf(array.optInt(i)));
        }
        return list;
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

    protected static final ArrayList<Void> convertJSONArrayToVoidList(JSONArray array) {
        ArrayList<Void> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add(null);
        }
        return list;
    }
}
