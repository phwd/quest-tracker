package com.oculus.horizon.api.graphql;

import X.AnonymousClass0KD;
import X.C08780ya;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Map;

public class GraphQLParamsHelper {
    public static final C08780ya GSON_CONVERTER = new C08780ya();
    public static final String KEY_ARGS = "args";
    public static final String KEY_CLIENT_MUTATION_ID = "client_mutation_id";
    public static final String KEY_MACHINE_ID = "machine_id";
    public static final String SENSITIVE_STRING_VALUE = "sensitive_string_value";

    public static class Data<T> {
        public T data;

        public Data(T t) {
            this.data = t;
        }
    }

    public static class Input<T> {
        public T input;

        public Input(T t) {
            this.input = t;
        }
    }

    public static String encodeAsNestedInputParams(Map<String, String> map) {
        return GSON_CONVERTER.A06(new Input(map));
    }

    public static String encodeMutationParamsUnwrapped(Map<String, ?> map) {
        C08780ya r3 = GSON_CONVERTER;
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.A00(map.entrySet());
        A01.put("client_mutation_id", AnonymousClass0KD.A00().toString());
        return r3.A06(A01.build());
    }

    public static String encodeParams(Map<String, ?> map) {
        return GSON_CONVERTER.A06(map);
    }

    public static String encodeRankReviewParams(Map<String, String> map) {
        C08780ya r3 = GSON_CONVERTER;
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.A00(map.entrySet());
        A01.put("client_mutation_id", AnonymousClass0KD.A00().toString());
        return r3.A06(new Data(A01.build()));
    }

    public static String encodeSearchTypeaheadParams(Map<String, String> map) {
        C08780ya r4 = GSON_CONVERTER;
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        ImmutableMap.Builder A012 = ImmutableMap.A01();
        A012.A00(map.entrySet());
        A012.put("type_filters", new ArrayList());
        A01.put(KEY_ARGS, A012.build());
        return r4.A06(A01.build());
    }

    public static String encodeUniversalSearchParams(Map<String, String> map, String str) {
        C08780ya r4 = GSON_CONVERTER;
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        ImmutableMap.Builder A012 = ImmutableMap.A01();
        A012.A00(map.entrySet());
        A012.put("custom_filters", ImmutableMap.A02("cinema_video_paid_only", "true"));
        A01.put(KEY_ARGS, A012.build());
        A01.put("machine_id", str);
        return r4.A06(A01.build());
    }

    public static ImmutableMap<String, String> sensitiveString(String str) {
        return ImmutableMap.A02(SENSITIVE_STRING_VALUE, str);
    }

    public static String encodeMutationParams(Map<String, ?> map) {
        C08780ya r3 = GSON_CONVERTER;
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.A00(map.entrySet());
        A01.put("client_mutation_id", AnonymousClass0KD.A00().toString());
        return r3.A06(new Input(A01.build()));
    }

    public static String encodeMutationParams(Map<String, ?> map, Map<String, ?> map2) {
        C08780ya r4 = GSON_CONVERTER;
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.A00(map2.entrySet());
        ImmutableMap.Builder A012 = ImmutableMap.A01();
        A012.A00(map.entrySet());
        A012.put("client_mutation_id", AnonymousClass0KD.A00().toString());
        A01.put("input", r4.A06(A012.build()));
        return r4.A06(A01.build());
    }
}
