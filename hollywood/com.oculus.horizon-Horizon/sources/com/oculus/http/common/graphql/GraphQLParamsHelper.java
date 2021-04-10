package com.oculus.http.common.graphql;

import X.AnonymousClass0KD;
import X.C08780ya;
import com.google.common.collect.ImmutableMap;
import java.util.Map;

public class GraphQLParamsHelper {
    public static final C08780ya GSON_CONVERTER = new C08780ya();
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

    public static String A00(Map<String, ?> map) {
        C08780ya r3 = GSON_CONVERTER;
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.A00(map.entrySet());
        A01.put("client_mutation_id", AnonymousClass0KD.A00().toString());
        return r3.A06(new Input(A01.build()));
    }

    public static String A01(Map<String, ?> map) {
        C08780ya r3 = GSON_CONVERTER;
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.A00(map.entrySet());
        A01.put("client_mutation_id", AnonymousClass0KD.A00().toString());
        return r3.A06(new Data(A01.build()));
    }
}
