package org.json;

/* access modifiers changed from: package-private */
public class JSON {
    static double checkDouble(double d) {
        if (!Double.isInfinite(d) && !Double.isNaN(d)) {
            return d;
        }
        throw new JSONException("Forbidden numeric value: " + d);
    }

    static Boolean toBoolean(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (!(obj instanceof String)) {
            return null;
        }
        String str = (String) obj;
        if ("true".equalsIgnoreCase(str)) {
            return true;
        }
        return "false".equalsIgnoreCase(str) ? false : null;
    }

    static Long toLong(Object obj) {
        if (obj instanceof Long) {
            return (Long) obj;
        }
        if (obj instanceof Number) {
            return Long.valueOf(((Number) obj).longValue());
        }
        if (!(obj instanceof String)) {
            return null;
        }
        try {
            return Long.valueOf((long) Double.parseDouble((String) obj));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    static String toString(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj != null) {
            return String.valueOf(obj);
        }
        return null;
    }

    public static JSONException typeMismatch(Object obj, Object obj2, String str) {
        if (obj2 == null) {
            throw new JSONException("Value at " + obj + " is null.");
        }
        throw new JSONException("Value " + obj2 + " at " + obj + " of type " + obj2.getClass().getName() + " cannot be converted to " + str);
    }

    public static JSONException typeMismatch(Object obj, String str) {
        if (obj == null) {
            throw new JSONException("Value is null.");
        }
        throw new JSONException("Value " + obj + " of type " + obj.getClass().getName() + " cannot be converted to " + str);
    }
}
