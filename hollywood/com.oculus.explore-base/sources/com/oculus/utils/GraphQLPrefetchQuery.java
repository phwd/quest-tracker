package com.oculus.utils;

import org.json.JSONObject;

public class GraphQLPrefetchQuery {
    private long docId;
    private JSONObject extra;
    private JSONObject variables;

    public GraphQLPrefetchQuery(long docId2, JSONObject variables2, JSONObject extra2) {
        this.docId = docId2;
        this.variables = variables2;
        this.extra = extra2;
    }

    public long getDocId() {
        return this.docId;
    }

    public JSONObject getVariables() {
        return this.variables;
    }
}
