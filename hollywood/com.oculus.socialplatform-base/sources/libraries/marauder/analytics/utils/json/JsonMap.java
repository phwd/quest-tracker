package libraries.marauder.analytics.utils.json;

import javax.annotation.Nullable;

public class JsonMap implements JsonType {
    public StringBuilder map = new StringBuilder("{");

    private void addKeyToMap(String str) {
        StringBuilder sb = this.map;
        if (sb.length() != 1) {
            sb.append(",");
        }
        JsonUtils.serialize(sb, str);
        this.map.append(":");
    }

    public boolean isEmpty() {
        if (this.map.length() != 1) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = this.map;
        sb.append("}");
        String obj = sb.toString();
        sb.deleteCharAt(sb.length() - 1);
        return obj;
    }

    public void addEntry(String str, double d) {
        addKeyToMap(str);
        JsonUtils.serialize(this.map, d);
    }

    public void addEntry(String str, long j) {
        addKeyToMap(str);
        JsonUtils.serialize(this.map, j);
    }

    public void addEntry(String str, Object obj) {
        addKeyToMap(str);
        JsonUtils.serialize(this.map, obj);
    }

    public void addEntry(String str, @Nullable String str2) {
        addKeyToMap(str);
        JsonUtils.serialize(this.map, str2);
    }

    public void addEntry(String str, JsonType jsonType) {
        if (JsonUtils.isValidInputType(jsonType)) {
            addKeyToMap(str);
            this.map.append(jsonType.toString());
            return;
        }
        StringBuilder sb = new StringBuilder("illegal input type ");
        sb.append(jsonType);
        throw new IllegalStateException(sb.toString());
    }

    public void addEntry(String str, boolean z) {
        addKeyToMap(str);
        JsonUtils.serialize(this.map, z);
    }
}
