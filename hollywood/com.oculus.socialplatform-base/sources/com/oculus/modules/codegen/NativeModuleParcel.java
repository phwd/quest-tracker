package com.oculus.modules.codegen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class NativeModuleParcel {
    public abstract JSONObject convertToJSONObject();

    public abstract void setFromJSONObject(JSONObject jSONObject);

    public static final JSONArray convertBooleanListToJSONArray(List<Boolean> list) {
        JSONArray jSONArray = new JSONArray();
        for (Boolean bool : list) {
            jSONArray.put(bool);
        }
        return jSONArray;
    }

    public static final JSONArray convertDoubleListToJSONArray(List<Double> list) {
        JSONArray jSONArray = new JSONArray();
        for (Double d : list) {
            jSONArray.put(d);
        }
        return jSONArray;
    }

    public static final <T extends Enum<T>> JSONArray convertEnumListToJSONArray(List<T> list) {
        JSONArray jSONArray = new JSONArray();
        for (T t : list) {
            jSONArray.put(t.name());
        }
        return jSONArray;
    }

    public static final JSONArray convertIntListToJSONArray(List<Integer> list) {
        JSONArray jSONArray = new JSONArray();
        for (Integer num : list) {
            jSONArray.put(num.intValue());
        }
        return jSONArray;
    }

    public static final ArrayList<Boolean> convertJSONArrayToBooleanList(JSONArray jSONArray) {
        ArrayList<Boolean> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(Boolean.valueOf(jSONArray.optBoolean(i)));
        }
        return arrayList;
    }

    public static final ArrayList<Double> convertJSONArrayToDoubleList(JSONArray jSONArray) {
        ArrayList<Double> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(Double.valueOf(jSONArray.optDouble(i)));
        }
        return arrayList;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.ArrayList<T extends java.lang.Enum<T>> */
    /* JADX WARN: Multi-variable type inference failed */
    public static final <T extends Enum<T>> ArrayList<T> convertJSONArrayToEnumList(JSONArray jSONArray, Class<T> cls) {
        ArrayList<T> arrayList = (ArrayList<T>) new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(Enum.valueOf(cls, jSONArray.optString(i)));
        }
        return arrayList;
    }

    public static final ArrayList<Integer> convertJSONArrayToIntList(JSONArray jSONArray) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(Integer.valueOf(jSONArray.optInt(i)));
        }
        return arrayList;
    }

    public static final ArrayList<JSONObject> convertJSONArrayToObjectList(JSONArray jSONArray) {
        ArrayList<JSONObject> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.optJSONObject(i));
        }
        return arrayList;
    }

    public static final <T extends NativeModuleParcel> ArrayList<T> convertJSONArrayToParcelList(JSONArray jSONArray, Class<T> cls) {
        ArrayList<T> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                T newInstance = cls.newInstance();
                newInstance.setFromJSONObject(jSONArray.optJSONObject(i));
                arrayList.add(newInstance);
            } catch (IllegalAccessException | InstantiationException unused) {
            }
        }
        return arrayList;
    }

    public static final ArrayList<String> convertJSONArrayToStringList(JSONArray jSONArray) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.optString(i));
        }
        return arrayList;
    }

    public static final ArrayList<Void> convertJSONArrayToVoidList(JSONArray jSONArray) {
        ArrayList<Void> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(null);
        }
        return arrayList;
    }

    public static final JSONArray convertObjectListToJSONArray(List<JSONObject> list) {
        JSONArray jSONArray = new JSONArray();
        for (JSONObject jSONObject : list) {
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    public static final <T extends NativeModuleParcel> JSONArray convertParcelListToJSONArray(List<T> list) {
        JSONArray jSONArray = new JSONArray();
        for (T t : list) {
            jSONArray.put(t.convertToJSONObject());
        }
        return jSONArray;
    }

    public static final JSONArray convertStringListToJSONArray(List<String> list) {
        JSONArray jSONArray = new JSONArray();
        for (String str : list) {
            jSONArray.put(str);
        }
        return jSONArray;
    }

    public static final JSONArray convertVoidListToJSONArray(List<Void> list) {
        JSONArray jSONArray = new JSONArray();
        Iterator<Void> it = list.iterator();
        while (it.hasNext()) {
            it.next();
            jSONArray.put((Object) null);
        }
        return jSONArray;
    }

    public final String convertToParcelString() {
        return convertToJSONObject().toString();
    }
}
