package com.oculus.horizon.notifications.reachability.api;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;

public class ReachabilityChecksMutationParams {
    public static final String PARAM_JSON = "reachability_json";
    public final String reachabilityPayload;

    public enum ReachabilityChecksType {
        TYPE_PUSH_REACHABLE("pushReachable"),
        TYPE_NOTIFICATIONS_ENABLED("notificationsEnabled");
        
        public final String text;

        /* access modifiers changed from: public */
        ReachabilityChecksType(String str) {
            this.text = str;
        }

        public String toString() {
            return this.text;
        }
    }

    public ReachabilityChecksMutationParams(String str) {
        this.reachabilityPayload = str;
    }

    public final String toString() {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put(PARAM_JSON, this.reachabilityPayload);
        return GraphQLParamsHelper.encodeMutationParams(A01.build());
    }
}
