package com.oculus.http.common.graphql;

import com.facebook.common.identifiers.SafeUUIDGenerator;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import java.util.Map;

public class GraphQLParamsHelper {
    private static final Gson GSON_CONVERTER = new Gson();
    private static final String KEY_CLIENT_MUTATION_ID = "client_mutation_id";

    public static String encodeParams(Map<String, ?> map) {
        return GSON_CONVERTER.toJson(map);
    }

    public static String encodeMutationParams(Map<String, ?> map) {
        return GSON_CONVERTER.toJson(new Input(ImmutableMap.builder().putAll(map).put(KEY_CLIENT_MUTATION_ID, SafeUUIDGenerator.randomUUID().toString()).build()));
    }

    public static String encodeMutationParamsAsData(Map<String, ?> map) {
        return GSON_CONVERTER.toJson(new Data(ImmutableMap.builder().putAll(map).put(KEY_CLIENT_MUTATION_ID, SafeUUIDGenerator.randomUUID().toString()).build()));
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
