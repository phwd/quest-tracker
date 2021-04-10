package com.facebook.common.util;

import android.net.Uri;
import android.os.Bundle;
import com.facebook.common.util.ReflectionUtils;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.FalseOnNull;
import com.facebook.infer.annotation.PropagatesNullable;
import com.facebook.ipc.activity.ActivityConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BigIntegerNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.DecimalNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.FloatNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.LongNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.POJONode;
import com.fasterxml.jackson.databind.node.ShortNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtil {
    private static final String LOG_TAG = "com.facebook.common.util.JSONUtil";

    @FalseOnNull
    public static boolean hasNonNullKey(@Nullable JsonNode jsonNode) {
        return jsonNode != null && !jsonNode.isNull();
    }

    @Nullable
    public static String getString(@Nullable JsonNode jsonNode) {
        return getString(jsonNode, null);
    }

    public static String getStringOrThrow(JsonNode jsonNode) {
        return (String) Preconditions.checkNotNull(getString(jsonNode), "The JSON node is not textual or numeric");
    }

    public static String getString(@PropagatesNullable JsonNode jsonNode, @PropagatesNullable String str) {
        if (jsonNode == null || jsonNode.isNull()) {
            return str;
        }
        if (jsonNode.isTextual()) {
            return jsonNode.textValue();
        }
        return jsonNode.isNumber() ? jsonNode.numberValue().toString() : str;
    }

    public static long getLong(@Nullable JsonNode jsonNode) {
        return getLong(jsonNode, 0);
    }

    public static long getLong(@Nullable JsonNode jsonNode, long j) {
        if (jsonNode == null || jsonNode.isNull()) {
            return j;
        }
        if (!jsonNode.isTextual()) {
            return jsonNode.isNumber() ? jsonNode.longValue() : j;
        }
        try {
            return Long.parseLong(jsonNode.textValue());
        } catch (NumberFormatException unused) {
            return j;
        }
    }

    public static int getInt(@Nullable JsonNode jsonNode) {
        return getInt(jsonNode, 0);
    }

    public static int getInt(@Nullable JsonNode jsonNode, int i) {
        if (jsonNode == null || jsonNode.isNull()) {
            return i;
        }
        if (!jsonNode.isTextual()) {
            return jsonNode.isNumber() ? jsonNode.intValue() : i;
        }
        try {
            return Integer.parseInt(jsonNode.textValue());
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static double getDouble(JsonNode jsonNode) {
        return getDouble(jsonNode, 0.0d);
    }

    public static double getDouble(JsonNode jsonNode, double d) {
        if (jsonNode == null || jsonNode.isNull()) {
            return d;
        }
        if (!jsonNode.isTextual()) {
            return jsonNode.isNumber() ? jsonNode.doubleValue() : d;
        }
        try {
            return Double.parseDouble(jsonNode.textValue());
        } catch (NumberFormatException unused) {
            return d;
        }
    }

    public static float getFloat(JsonNode jsonNode) {
        return getFloat(jsonNode, 0.0f);
    }

    public static float getFloat(@Nullable JsonNode jsonNode, float f) {
        if (jsonNode == null || jsonNode.isNull()) {
            return f;
        }
        if (!jsonNode.isTextual()) {
            return jsonNode.isNumber() ? jsonNode.numberValue().floatValue() : f;
        }
        try {
            return Float.parseFloat(jsonNode.textValue());
        } catch (NumberFormatException unused) {
            return f;
        }
    }

    public static boolean getBoolean(@Nullable JsonNode jsonNode) {
        return getBoolean(jsonNode, false);
    }

    public static boolean getBoolean(@Nullable JsonNode jsonNode, boolean z) {
        if (jsonNode == null || jsonNode.isNull()) {
            return z;
        }
        if (jsonNode.isBoolean()) {
            return jsonNode.booleanValue();
        }
        if (jsonNode.isTextual()) {
            String textValue = jsonNode.textValue();
            if ("on".equals(textValue) || ActivityConstants.Extras.WATCH_FEED_INJECTION.equals(textValue) || "true".equals(textValue)) {
                return true;
            }
            return false;
        } else if (!jsonNode.isNumber()) {
            return z;
        } else {
            if (jsonNode.intValue() != 0) {
                return true;
            }
            return false;
        }
    }

    public static TriState getTriState(@Nullable JsonNode jsonNode) {
        if (jsonNode == null || jsonNode.isNull()) {
            return TriState.UNSET;
        }
        if (jsonNode.isBoolean()) {
            return TriState.valueOf(jsonNode.booleanValue());
        }
        boolean z = false;
        if (jsonNode.isTextual()) {
            String textValue = jsonNode.textValue();
            if ("on".equals(textValue) || ActivityConstants.Extras.WATCH_FEED_INJECTION.equals(textValue) || "true".equals(textValue)) {
                z = true;
            }
            return TriState.valueOf(z);
        } else if (!jsonNode.isNumber()) {
            return TriState.UNSET;
        } else {
            if (jsonNode.intValue() != 0) {
                z = true;
            }
            return TriState.valueOf(z);
        }
    }

    public static ImmutableList<String> getImmutableStringList(JsonNode jsonNode, String str) {
        ArrayNode arrayNode = getArrayNode(jsonNode, str);
        ImmutableList.Builder builder = new ImmutableList.Builder();
        Iterator<JsonNode> it = arrayNode.iterator();
        while (it.hasNext()) {
            builder.add((Object) getString(it.next()));
        }
        return builder.build();
    }

    public static Iterable<JsonNode> getArray(JsonNode jsonNode, String str) {
        return (Iterable) MoreObjects.firstNonNull(getNodeOrNull(jsonNode, str, ArrayNode.class), ImmutableList.of());
    }

    public static ArrayNode getArrayNode(JsonNode jsonNode, String str) {
        return (ArrayNode) getNodeOrEmpty(jsonNode, str, ArrayNode.class);
    }

    public static ObjectNode getObjectNode(JsonNode jsonNode, String str) {
        return (ObjectNode) getNodeOrEmpty(jsonNode, str, ObjectNode.class);
    }

    private static <T extends ContainerNode> T getNodeOrEmpty(JsonNode jsonNode, String str, Class<T> cls) {
        T t = (T) ((ContainerNode) getNodeOrNull(jsonNode, str, cls));
        return t != null ? t : (T) empty(cls);
    }

    @Nullable
    public static <T extends JsonNode> T getNodeOrNull(JsonNode jsonNode, String str, Class<T> cls) {
        JsonNode jsonNode2 = jsonNode.get(str);
        Preconditions.checkArgument(jsonNode2 == null || cls.isInstance(jsonNode2), "Node %s in not an %s in %s", str, cls.getSimpleName(), jsonNode);
        return cls.cast(jsonNode2);
    }

    public static <T extends ContainerNode> T empty(Class<T> cls) {
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        if (ArrayNode.class.equals(cls)) {
            return jsonNodeFactory.arrayNode();
        }
        if (ObjectNode.class.equals(cls)) {
            return jsonNodeFactory.objectNode();
        }
        throw new IllegalArgumentException("Unsupported node type: " + cls);
    }

    public static String get(JsonNode jsonNode, String str) {
        JsonNode jsonNode2 = jsonNode.get(str);
        if (jsonNode2 == null) {
            return "";
        }
        return getString(jsonNode2, "");
    }

    public static String getOrThrow(JsonNode jsonNode, String str) {
        return getNodeOrThrow(jsonNode, str).asText();
    }

    public static JsonNode getNodeOrThrow(JsonNode jsonNode, String str) {
        return (JsonNode) Preconditions.checkNotNull(jsonNode.get(str), "No key %s in %s", str, jsonNode);
    }

    public static Bundle toBundle(JSONObject jSONObject) throws JSONException {
        Bundle bundle = new Bundle();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = jSONObject.get(next);
            if (obj instanceof String) {
                bundle.putString(next, (String) obj);
            } else if (obj instanceof Integer) {
                bundle.putInt(next, ((Integer) obj).intValue());
            } else if (obj instanceof Float) {
                bundle.putFloat(next, ((Float) obj).floatValue());
            } else if (obj instanceof Double) {
                bundle.putDouble(next, ((Double) obj).doubleValue());
            } else if (obj instanceof JSONArray) {
                JSONArray jSONArray = (JSONArray) obj;
                if (jSONArray.length() == 0) {
                    bundle.putParcelableArrayList(next, Lists.newArrayList());
                } else if (jSONArray.get(0) instanceof JSONObject) {
                    bundle.putParcelableArrayList(next, toBundleArray(jSONArray));
                } else if (jSONArray.get(0) instanceof String) {
                    bundle.putStringArrayList(next, toStringArray(jSONArray));
                }
            } else if (obj instanceof JSONObject) {
                bundle.putBundle(next, toBundle((JSONObject) obj));
            }
        }
        return bundle;
    }

    public static ArrayList<Bundle> toBundleArray(JSONArray jSONArray) throws JSONException {
        ArrayList<Bundle> newArrayListWithCapacity = Lists.newArrayListWithCapacity(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            newArrayListWithCapacity.add(toBundle(jSONArray.getJSONObject(i)));
        }
        return newArrayListWithCapacity;
    }

    public static ArrayList<String> toStringArray(JSONArray jSONArray) throws JSONException {
        ArrayList<String> newArrayListWithCapacity = Lists.newArrayListWithCapacity(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            newArrayListWithCapacity.add(jSONArray.getString(i));
        }
        return newArrayListWithCapacity;
    }

    public static ImmutableList<String> toImmutableStringList(JSONArray jSONArray) throws JSONException {
        return ImmutableList.copyOf((Collection) toStringArray(jSONArray));
    }

    public static ObjectNode stringMapToObjectNode(Map<String, String> map) {
        ObjectNode objectNode = new ObjectNode(JsonNodeFactory.instance);
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                objectNode.put(entry.getKey(), entry.getValue());
            }
        }
        return objectNode;
    }

    public static ImmutableMap<String, String> objectNodeToStringMap(ObjectNode objectNode, ObjectMapper objectMapper) {
        HashMap hashMap = new HashMap(objectNode.size());
        Iterator<String> fieldNames = objectNode.fieldNames();
        while (fieldNames.hasNext()) {
            String next = fieldNames.next();
            JsonNode jsonNode = objectNode.get(next);
            if (!jsonNode.isArray()) {
                hashMap.put(next, jsonNode.asText());
            } else {
                try {
                    hashMap.put(next, objectMapper.writeValueAsString(jsonNode));
                } catch (JsonProcessingException e) {
                    hashMap.put(next, "");
                    BLog.e(LOG_TAG, "Failed to parse json list", e);
                }
            }
        }
        return ImmutableMap.copyOf(hashMap);
    }

    public static ObjectNode stringMapAsListToObjectNode(List<String> list) {
        ObjectNode objectNode = new ObjectNode(JsonNodeFactory.instance);
        int size = list.size();
        if (!list.isEmpty()) {
            for (int i = 0; i < size; i += 2) {
                objectNode.put(list.get(i), list.get(i + 1));
            }
        }
        return objectNode;
    }

    public static ArrayNode iterableToArrayNode(Iterable<?> iterable) {
        ArrayNode arrayNode = new ArrayNode(JsonNodeFactory.instance);
        Iterator<?> it = iterable.iterator();
        while (it.hasNext()) {
            arrayNode.add(it.next().toString());
        }
        return arrayNode;
    }

    public static <T> ArrayNode iterableToArrayNode(Iterable<T> iterable, Function<T, String> function) {
        ArrayNode arrayNode = new ArrayNode(JsonNodeFactory.instance);
        for (T t : iterable) {
            arrayNode.add(function.apply(t));
        }
        return arrayNode;
    }

    public static ArrayNode stringListToArrayNode(List<String> list) {
        ArrayNode arrayNode = new ArrayNode(JsonNodeFactory.instance);
        for (String str : list) {
            arrayNode.add(str);
        }
        return arrayNode;
    }

    public static ArrayNode stringsToArrayNode(String... strArr) {
        ArrayNode arrayNode = new ArrayNode(JsonNodeFactory.instance);
        for (String str : strArr) {
            arrayNode.add(str);
        }
        return arrayNode;
    }

    public static JsonNode toJson(Object obj) {
        return toJson(obj, false);
    }

    public static JsonNode toJson(Object obj, boolean z) {
        if (obj == null) {
            return NullNode.getInstance();
        }
        if (obj instanceof CharSequence) {
            return new TextNode(obj.toString());
        }
        if (obj instanceof Boolean) {
            return BooleanNode.valueOf(((Boolean) obj).booleanValue());
        }
        if (obj instanceof Float) {
            return FloatNode.valueOf(((Float) obj).floatValue());
        }
        if (obj instanceof Double) {
            return DoubleNode.valueOf(((Double) obj).doubleValue());
        }
        if (obj instanceof Short) {
            return ShortNode.valueOf(((Short) obj).shortValue());
        }
        if (obj instanceof Integer) {
            return IntNode.valueOf(((Integer) obj).intValue());
        }
        if (obj instanceof Long) {
            return LongNode.valueOf(((Long) obj).longValue());
        }
        if (obj instanceof BigDecimal) {
            return DecimalNode.valueOf((BigDecimal) obj);
        }
        if (obj instanceof BigInteger) {
            return BigIntegerNode.valueOf((BigInteger) obj);
        }
        if (obj instanceof Map) {
            ObjectNode objectNode = new ObjectNode(JsonNodeFactory.instance);
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                objectNode.put(entry.getKey().toString(), toJson(entry.getValue(), z));
            }
            return objectNode;
        } else if (obj instanceof Iterable) {
            ArrayNode arrayNode = new ArrayNode(JsonNodeFactory.instance);
            for (Object obj2 : (Iterable) obj) {
                arrayNode.add(toJson(obj2, z));
            }
            return arrayNode;
        } else if (obj instanceof Object[]) {
            ArrayNode arrayNode2 = new ArrayNode(JsonNodeFactory.instance);
            for (Object obj3 : (Object[]) obj) {
                arrayNode2.add(toJson(obj3, z));
            }
            return arrayNode2;
        } else if (ReflectionUtils.hasAnnotation(obj.getClass(), JsonSerialize.class)) {
            return new POJONode(obj);
        } else {
            if (z) {
                return toJson(new ReflectionUtils.FieldMap(obj), z);
            }
            throw new IllegalArgumentException("Can't convert to json: " + obj + ", of type: " + obj.getClass());
        }
    }

    public static Uri getUri(JsonNode jsonNode, String str) {
        return Uri.parse(get(jsonNode, str));
    }
}
