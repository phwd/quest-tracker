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
        JSONArray jSONArray = new JSONArray();
        for (Boolean bool : list) {
            jSONArray.put(bool);
        }
        return jSONArray;
    }

    protected static final JSONArray convertDoubleListToJSONArray(List<Double> list) {
        JSONArray jSONArray = new JSONArray();
        for (Double d : list) {
            jSONArray.put(d);
        }
        return jSONArray;
    }

    protected static final <T extends Enum<T>> JSONArray convertEnumListToJSONArray(List<T> list) {
        JSONArray jSONArray = new JSONArray();
        for (T t : list) {
            jSONArray.put(t.name());
        }
        return jSONArray;
    }

    protected static final JSONArray convertIntListToJSONArray(List<Integer> list) {
        JSONArray jSONArray = new JSONArray();
        for (Integer num : list) {
            jSONArray.put(num.intValue());
        }
        return jSONArray;
    }

    protected static final JSONArray convertObjectListToJSONArray(List<JSONObject> list) {
        JSONArray jSONArray = new JSONArray();
        for (JSONObject jSONObject : list) {
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    protected static final <T extends NativeModuleParcel> JSONArray convertParcelListToJSONArray(List<T> list) {
        JSONArray jSONArray = new JSONArray();
        for (T t : list) {
            jSONArray.put(t.convertToJSONObject());
        }
        return jSONArray;
    }

    protected static final JSONArray convertStringListToJSONArray(List<String> list) {
        JSONArray jSONArray = new JSONArray();
        for (String str : list) {
            jSONArray.put(str);
        }
        return jSONArray;
    }

    protected static final JSONArray convertVoidListToJSONArray(List<Void> list) {
        JSONArray jSONArray = new JSONArray();
        for (Void r1 : list) {
            jSONArray.put(r1);
        }
        return jSONArray;
    }

    protected static final ArrayList<Boolean> convertJSONArrayToBooleanList(JSONArray jSONArray) {
        ArrayList<Boolean> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(Boolean.valueOf(jSONArray.optBoolean(i)));
        }
        return arrayList;
    }

    protected static final ArrayList<Double> convertJSONArrayToDoubleList(JSONArray jSONArray) {
        ArrayList<Double> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(Double.valueOf(jSONArray.optDouble(i)));
        }
        return arrayList;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.ArrayList<T extends java.lang.Enum<T>> */
    /* JADX WARN: Multi-variable type inference failed */
    protected static final <T extends Enum<T>> ArrayList<T> convertJSONArrayToEnumList(JSONArray jSONArray, Class<T> cls) {
        ArrayList<T> arrayList = (ArrayList<T>) new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(Enum.valueOf(cls, jSONArray.optString(i)));
        }
        return arrayList;
    }

    protected static final ArrayList<Integer> convertJSONArrayToIntList(JSONArray jSONArray) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(Integer.valueOf(jSONArray.optInt(i)));
        }
        return arrayList;
    }

    protected static final ArrayList<JSONObject> convertJSONArrayToObjectList(JSONArray jSONArray) {
        ArrayList<JSONObject> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.optJSONObject(i));
        }
        return arrayList;
    }

    protected static final <T extends NativeModuleParcel> ArrayList<T> convertJSONArrayToParcelList(JSONArray jSONArray, Class<T> cls) {
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

    protected static final ArrayList<String> convertJSONArrayToStringList(JSONArray jSONArray) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.optString(i));
        }
        return arrayList;
    }

    protected static final ArrayList<Void> convertJSONArrayToVoidList(JSONArray jSONArray) {
        ArrayList<Void> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(null);
        }
        return arrayList;
    }
}
