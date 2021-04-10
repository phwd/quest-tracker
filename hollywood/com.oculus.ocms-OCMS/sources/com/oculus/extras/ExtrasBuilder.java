package com.oculus.extras;

import java.util.HashMap;
import java.util.Map;

public class ExtrasBuilder {
    private Map<String, String> mData;

    public ExtrasBuilder() {
        this(new HashMap());
    }

    public ExtrasBuilder(Map<String, String> map) {
        this.mData = new HashMap(map);
    }

    public ExtrasBuilder putString(String str, String str2) {
        if (str2 == null) {
            this.mData.remove(str);
        } else {
            this.mData.put(str, str2);
        }
        return this;
    }

    public ExtrasBuilder putLong(String str, long j) {
        return putString(str, Long.toString(j));
    }

    public ExtrasBuilder putInt(String str, int i) {
        return putString(str, Integer.toString(i));
    }

    public ExtrasBuilder putFloat(String str, float f) {
        return putString(str, Float.toString(f));
    }

    public ExtrasBuilder putBoolean(String str, boolean z) {
        return putString(str, Boolean.toString(z));
    }

    public ExtrasBuilder putExtras(Extras extras) {
        this.mData.putAll(extras.getData());
        return this;
    }

    public ExtrasBuilder remove(String str) {
        this.mData.remove(str);
        return this;
    }

    public Map<String, String> getData() {
        return this.mData;
    }

    public Extras build() {
        return new Extras(this.mData);
    }
}
