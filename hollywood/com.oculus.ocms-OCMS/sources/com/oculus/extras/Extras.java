package com.oculus.extras;

import android.os.Bundle;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class Extras {
    private final ImmutableMap<String, String> mData;

    public Extras() {
        this.mData = ImmutableMap.of();
    }

    public Extras(Map<String, String> map) {
        this.mData = ImmutableMap.copyOf(map);
    }

    public Extras(@Nullable Bundle bundle) {
        HashMap hashMap = new HashMap();
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                Object obj = bundle.get(str);
                if (obj instanceof String) {
                    hashMap.put(str, (String) obj);
                } else {
                    throw new ClassCastException("Extras key is not string value: " + str + " = " + obj);
                }
            }
        }
        this.mData = ImmutableMap.copyOf(hashMap);
    }

    public static ExtrasBuilder builder() {
        return new ExtrasBuilder();
    }

    public static Extras empty() {
        return new ExtrasBuilder().build();
    }

    public ExtrasBuilder buildUpon() {
        return new ExtrasBuilder(this.mData);
    }

    public Map<String, String> getData() {
        return this.mData;
    }

    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        UnmodifiableIterator<Map.Entry<String, String>> it = this.mData.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> next = it.next();
            bundle.putString(next.getKey(), next.getValue());
        }
        return bundle;
    }

    public boolean hasKey(String str) {
        return this.mData.containsKey(str);
    }

    public String getString(String str, String str2) {
        return hasKey(str) ? this.mData.get(str) : str2;
    }

    public long getLong(String str, long j) {
        if (hasKey(str)) {
            try {
                return Long.parseLong(this.mData.get(str));
            } catch (NumberFormatException unused) {
            }
        }
        return j;
    }

    public int getInt(String str, int i) {
        if (hasKey(str)) {
            try {
                return Integer.parseInt(this.mData.get(str));
            } catch (NumberFormatException unused) {
            }
        }
        return i;
    }

    public float getFloat(String str, float f) {
        if (hasKey(str)) {
            try {
                return Float.parseFloat(this.mData.get(str));
            } catch (NumberFormatException unused) {
            }
        }
        return f;
    }

    public boolean getBoolean(String str, boolean z) {
        return hasKey(str) ? Boolean.parseBoolean(this.mData.get(str)) : z;
    }

    public String toString() {
        return this.mData.toString();
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof Extras)) {
            return Objects.equal(this.mData, ((Extras) obj).mData);
        }
        return false;
    }

    public int hashCode() {
        return this.mData.hashCode();
    }
}
