package com.oculus.horizon.api.graphql;

import X.AnonymousClass0IR;
import X.AnonymousClass13N;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Map;

public class GraphQLParamsHelper {
    public static final AnonymousClass13N GSON_CONVERTER = new AnonymousClass13N();
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
        AnonymousClass13N r3 = GSON_CONVERTER;
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.A01(map);
        A04.put("client_mutation_id", AnonymousClass0IR.A00().toString());
        return r3.A06(A04.build());
    }

    public static String encodeParams(Map<String, ?> map) {
        return GSON_CONVERTER.A06(map);
    }

    public static String encodeRankReviewParams(Map<String, String> map) {
        AnonymousClass13N r3 = GSON_CONVERTER;
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.A01(map);
        A04.put("client_mutation_id", AnonymousClass0IR.A00().toString());
        return r3.A06(new Data(A04.build()));
    }

    public static String encodeSearchTypeaheadParams(Map<String, String> map) {
        AnonymousClass13N r4 = GSON_CONVERTER;
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        ImmutableMap.Builder A042 = ImmutableMap.A04();
        A042.A01(map);
        A042.put("type_filters", new ArrayList());
        A04.put(KEY_ARGS, A042.build());
        return r4.A06(A04.build());
    }

    public static String encodeUniversalSearchParams(Map<String, String> map, String str) {
        AnonymousClass13N r4 = GSON_CONVERTER;
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        ImmutableMap.Builder A042 = ImmutableMap.A04();
        A042.A01(map);
        A042.put("custom_filters", ImmutableMap.A05("cinema_video_paid_only", "true"));
        A04.put(KEY_ARGS, A042.build());
        A04.put("machine_id", str);
        return r4.A06(A04.build());
    }

    public static ImmutableMap<String, String> sensitiveString(String str) {
        return ImmutableMap.A05(SENSITIVE_STRING_VALUE, str);
    }

    public static String encodeMutationParams(Map<String, ?> map) {
        AnonymousClass13N r3 = GSON_CONVERTER;
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.A01(map);
        A04.put("client_mutation_id", AnonymousClass0IR.A00().toString());
        return r3.A06(new Input(A04.build()));
    }

    public static String encodeMutationParams(Map<String, ?> map, Map<String, ?> map2) {
        AnonymousClass13N r4 = GSON_CONVERTER;
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.A01(map2);
        ImmutableMap.Builder A042 = ImmutableMap.A04();
        A042.A01(map);
        A042.put("client_mutation_id", AnonymousClass0IR.A00().toString());
        A04.put("input", r4.A06(A042.build()));
        return r4.A06(A04.build());
    }
}
