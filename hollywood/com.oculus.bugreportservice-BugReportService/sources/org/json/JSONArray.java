package org.json;

import java.util.ArrayList;
import java.util.List;

public class JSONArray {
    private final List values = new ArrayList();

    public JSONArray put(Object obj) {
        this.values.add(obj);
        return this;
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
        jSONStringer.array();
        for (Object obj : this.values) {
            jSONStringer.value(obj);
        }
        jSONStringer.endArray();
    }

    public boolean equals(Object obj) {
        return (obj instanceof JSONArray) && ((JSONArray) obj).values.equals(this.values);
    }

    public int hashCode() {
        return this.values.hashCode();
    }
}
