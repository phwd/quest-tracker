package libraries.marauder.analytics.utils.json;

public class JsonArray implements JsonType {
    public StringBuilder array = new StringBuilder("[");

    public void addStringEntry(String str) {
        StringBuilder sb = this.array;
        if (sb.length() != 1) {
            sb.append(",");
        }
        JsonUtils.serialize(sb, str);
    }

    public String toString() {
        StringBuilder sb = this.array;
        sb.append("]");
        String obj = sb.toString();
        sb.deleteCharAt(sb.length() - 1);
        return obj;
    }

    public void addEntry(String str) {
        StringBuilder sb = this.array;
        if (sb.length() != 1) {
            sb.append(",");
        }
        sb.append(str);
    }

    public void addEntry(JsonType jsonType) {
        StringBuilder sb = this.array;
        if (sb.length() != 1) {
            sb.append(",");
        }
        sb.append(jsonType);
    }
}
