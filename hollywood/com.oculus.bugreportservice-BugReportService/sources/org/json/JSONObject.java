package org.json;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class JSONObject {
    private static final Double NEGATIVE_ZERO = Double.valueOf(-0.0d);
    public static final Object NULL = new Object() {
        /* class org.json.JSONObject.AnonymousClass1 */

        public boolean equals(Object obj) {
            return obj == this || obj == null;
        }

        public String toString() {
            return "null";
        }

        public int hashCode() {
            return Objects.hashCode(null);
        }
    };
    private final LinkedHashMap nameValuePairs;

    public JSONObject() {
        this.nameValuePairs = new LinkedHashMap();
    }

    public JSONObject(JSONTokener jSONTokener) {
        Object nextValue = jSONTokener.nextValue();
        if (nextValue instanceof JSONObject) {
            this.nameValuePairs = ((JSONObject) nextValue).nameValuePairs;
        } else {
            JSON.typeMismatch(nextValue, "JSONObject");
            throw null;
        }
    }

    public JSONObject(String str) {
        this(new JSONTokener(str));
    }

    public JSONObject put(String str, boolean z) {
        LinkedHashMap linkedHashMap = this.nameValuePairs;
        checkName(str);
        linkedHashMap.put(str, Boolean.valueOf(z));
        return this;
    }

    public JSONObject put(String str, long j) {
        LinkedHashMap linkedHashMap = this.nameValuePairs;
        checkName(str);
        linkedHashMap.put(str, Long.valueOf(j));
        return this;
    }

    public JSONObject put(String str, Object obj) {
        if (obj == null) {
            this.nameValuePairs.remove(str);
            return this;
        }
        if (obj instanceof Number) {
            JSON.checkDouble(((Number) obj).doubleValue());
        }
        LinkedHashMap linkedHashMap = this.nameValuePairs;
        checkName(str);
        linkedHashMap.put(str, obj);
        return this;
    }

    /* access modifiers changed from: package-private */
    public String checkName(String str) {
        if (str != null) {
            return str;
        }
        throw new JSONException("Names must be non-null");
    }

    public Object get(String str) {
        Object obj = this.nameValuePairs.get(str);
        if (obj != null) {
            return obj;
        }
        throw new JSONException("No value for " + str);
    }

    public Object opt(String str) {
        return this.nameValuePairs.get(str);
    }

    public boolean getBoolean(String str) {
        Object obj = get(str);
        Boolean bool = JSON.toBoolean(obj);
        if (bool != null) {
            return bool.booleanValue();
        }
        JSON.typeMismatch(str, obj, "boolean");
        throw null;
    }

    public long getLong(String str) {
        Object obj = get(str);
        Long l = JSON.toLong(obj);
        if (l != null) {
            return l.longValue();
        }
        JSON.typeMismatch(str, obj, "long");
        throw null;
    }

    public String getString(String str) {
        Object obj = get(str);
        String json = JSON.toString(obj);
        if (json != null) {
            return json;
        }
        JSON.typeMismatch(str, obj, "String");
        throw null;
    }

    public String optString(String str) {
        return optString(str, "");
    }

    public String optString(String str, String str2) {
        String json = JSON.toString(opt(str));
        return json != null ? json : str2;
    }

    public String toString() {
        try {
            JSONStringer jSONStringer = new JSONStringer();
            writeTo(jSONStringer);
            return jSONStringer.toString();
        } catch (JSONException unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void writeTo(JSONStringer jSONStringer) {
        jSONStringer.object();
        for (Map.Entry entry : this.nameValuePairs.entrySet()) {
            jSONStringer.key((String) entry.getKey());
            jSONStringer.value(entry.getValue());
        }
        jSONStringer.endObject();
    }

    public static String numberToString(Number number) {
        if (number != null) {
            double doubleValue = number.doubleValue();
            JSON.checkDouble(doubleValue);
            if (number.equals(NEGATIVE_ZERO)) {
                return "-0";
            }
            long longValue = number.longValue();
            if (doubleValue == ((double) longValue)) {
                return Long.toString(longValue);
            }
            return number.toString();
        }
        throw new JSONException("Number must be non-null");
    }
}
