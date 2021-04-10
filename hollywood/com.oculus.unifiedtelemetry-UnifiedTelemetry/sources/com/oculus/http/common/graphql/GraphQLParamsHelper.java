package com.oculus.http.common.graphql;

import X.HY;

public class GraphQLParamsHelper {
    public static final HY GSON_CONVERTER = new HY();
    public static final String KEY_CLIENT_MUTATION_ID = "client_mutation_id";

    public static class Data<T> {
        public T data;
    }

    public static class Input<T> {
        public T input;
    }
}
