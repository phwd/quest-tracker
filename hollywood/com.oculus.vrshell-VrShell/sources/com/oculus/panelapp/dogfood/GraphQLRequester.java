package com.oculus.panelapp.dogfood;

import org.json.JSONObject;

public interface GraphQLRequester {
    public static final String GRAPHQL_ASSIGNMENTS = "dogfood_assignments?device_serial=%s&build_version=%s";

    void handleFailedGraphQlResponse(String str);

    void handleGraphQlResponse(JSONObject jSONObject);
}
