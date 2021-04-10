package com.oculus.http.common.graphql;

import X.AnonymousClass0IR;
import X.AnonymousClass13N;
import com.google.common.collect.ImmutableMap;
import java.util.Map;

public class GraphQLParamsHelper {
    public static final AnonymousClass13N GSON_CONVERTER = new AnonymousClass13N();
    public static final String KEY_CLIENT_MUTATION_ID = "client_mutation_id";

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

    public static String encodeMutationParams(Map<String, ?> map) {
        AnonymousClass13N r3 = GSON_CONVERTER;
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.A01(map);
        A04.put("client_mutation_id", AnonymousClass0IR.A00().toString());
        return r3.A06(new Input(A04.build()));
    }

    public static String encodeMutationParamsAsData(Map<String, ?> map) {
        AnonymousClass13N r3 = GSON_CONVERTER;
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.A01(map);
        A04.put("client_mutation_id", AnonymousClass0IR.A00().toString());
        return r3.A06(new Data(A04.build()));
    }

    public static String encodeParams(Map<String, ?> map) {
        return GSON_CONVERTER.A06(map);
    }
}
