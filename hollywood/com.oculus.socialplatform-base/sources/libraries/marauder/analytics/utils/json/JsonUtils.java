package libraries.marauder.analytics.utils.json;

import javax.annotation.Nullable;

public class JsonUtils {
    public static boolean isValidInputType(Object obj) {
        if ((obj instanceof JsonString) || (obj instanceof JsonArray) || (obj instanceof JsonMap)) {
            return true;
        }
        return false;
    }

    public static String serializeV2(@Nullable String str) {
        if (str == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        serialize(sb, str);
        return sb.toString();
    }

    public static String serialize(double d) {
        return String.valueOf(d);
    }

    public static String serialize(long j) {
        return String.valueOf(j);
    }

    public static String serialize(Object obj) {
        if ((obj instanceof Integer) || (obj instanceof Float) || (obj instanceof Long) || (obj instanceof Double) || (obj instanceof Boolean) || (obj instanceof Byte) || (obj instanceof Short)) {
            return String.valueOf(obj);
        }
        return serialize(String.valueOf(obj)).toString();
    }

    public static String serialize(boolean z) {
        return String.valueOf(z);
    }

    public static StringBuilder serialize(String str) {
        StringBuilder sb = new StringBuilder();
        serialize(sb, str);
        return sb;
    }

    public static void serialize(StringBuilder sb, double d) {
        sb.append(String.valueOf(d));
    }

    public static void serialize(StringBuilder sb, long j) {
        sb.append(String.valueOf(j));
    }

    public static void serialize(StringBuilder sb, Object obj) {
        if ((obj instanceof Integer) || (obj instanceof Float) || (obj instanceof Long) || (obj instanceof Double) || (obj instanceof Boolean) || (obj instanceof Byte) || (obj instanceof Short)) {
            sb.append(String.valueOf(obj));
        } else {
            serialize(sb, String.valueOf(obj));
        }
    }

    public static void serialize(StringBuilder sb, @Nullable String str) {
        if (str == null) {
            sb.append("null");
        } else {
            JsonUtilsEscape.escape(sb, str);
        }
    }

    public static void serialize(StringBuilder sb, boolean z) {
        sb.append(String.valueOf(z));
    }
}
