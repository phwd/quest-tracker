package com.oculus.horizon.api.graphql;

import com.facebook.common.identifiers.SafeUUIDGenerator;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Map;

public class GraphQLParamsHelper {
    private static final Gson GSON_CONVERTER = new Gson();
    private static final String KEY_ARGS = "args";
    private static final String KEY_CLIENT_MUTATION_ID = "client_mutation_id";
    private static final String KEY_MACHINE_ID = "machine_id";
    private static final String SENSITIVE_STRING_VALUE = "sensitive_string_value";

    public static ImmutableMap<String, String> sensitiveString(String str) {
        return ImmutableMap.of(SENSITIVE_STRING_VALUE, str);
    }

    public static String encodeParams(Map<String, ?> map) {
        return GSON_CONVERTER.toJson(map);
    }

    public static String encodeMutationParams(Map<String, ?> map) {
        return GSON_CONVERTER.toJson(new Input(ImmutableMap.builder().putAll(map).put(KEY_CLIENT_MUTATION_ID, SafeUUIDGenerator.randomUUID().toString()).build()));
    }

    public static String encodeRankReviewParams(Map<String, String> map) {
        return GSON_CONVERTER.toJson(new Data(ImmutableMap.builder().putAll(map).put(KEY_CLIENT_MUTATION_ID, SafeUUIDGenerator.randomUUID().toString()).build()));
    }

    public static String encodeUniversalSearchParams(Map<String, String> map, String str) {
        return GSON_CONVERTER.toJson(ImmutableMap.builder().put(KEY_ARGS, ImmutableMap.builder().putAll(map).put("custom_filters", ImmutableMap.of("cinema_video_paid_only", "true")).build()).put("machine_id", str).build());
    }

    public static String encodeSearchTypeaheadParams(Map<String, String> map) {
        return GSON_CONVERTER.toJson(ImmutableMap.builder().put(KEY_ARGS, ImmutableMap.builder().putAll(map).put("type_filters", new ArrayList()).build()).build());
    }

    public static String encodeMutationParams(Map<String, ?> map, Map<String, ?> map2) {
        return GSON_CONVERTER.toJson(ImmutableMap.builder().putAll(map2).put("input", encodeParams(ImmutableMap.builder().putAll(map).put(KEY_CLIENT_MUTATION_ID, SafeUUIDGenerator.randomUUID().toString()).build())).build());
    }

    public static String encodeMutationParamsUnwrapped(Map<String, ?> map) {
        return GSON_CONVERTER.toJson(ImmutableMap.builder().putAll(map).put(KEY_CLIENT_MUTATION_ID, SafeUUIDGenerator.randomUUID().toString()).build());
    }

    public static String encodeAsNestedInputParams(Map<String, String> map) {
        return GSON_CONVERTER.toJson(new Input(map));
    }

    public static class Input<T> {
        public T input;

        public Input(T t) {
            this.input = t;
        }
    }

    public static class Data<T> {
        public T data;

        public Data(T t) {
            this.data = t;
        }
    }
}
