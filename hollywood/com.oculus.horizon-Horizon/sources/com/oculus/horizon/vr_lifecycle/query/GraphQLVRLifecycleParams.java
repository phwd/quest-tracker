package com.oculus.horizon.vr_lifecycle.query;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.concurrent.Immutable;

@Immutable
public class GraphQLVRLifecycleParams {
    public static final String ARGUMENT_DEVICE_ID = "device_id";
    public static final String ARGUMENT_LAST_APPS_IN_VR = "last_apps_in_vr";
    public static final String ARGUMENT_SESSION_LENGTH_MS = "session_length_ms";
    public static final String ARGUMENT_TIME_SINCE_SESSION_MS = "time_since_session_ms";
    public final String deviceId;
    public final ImmutableList<RecentAppInfo> lastAppsInVR;
    public final long sessionLengthMs;
    public final long timeSinceSessionMs;

    public static class RecentAppInfo {
        public final long last_start_time;
        public final long last_usage_time;
        public final String package_name;
        public final long total_usage_time;

        public RecentAppInfo(String str, long j, long j2, long j3) {
            this.package_name = str;
            this.total_usage_time = j;
            this.last_start_time = j2;
            this.last_usage_time = j3;
        }
    }

    public GraphQLVRLifecycleParams(String str, ImmutableList<RecentAppInfo> immutableList, long j, long j2) {
        this.deviceId = str;
        this.lastAppsInVR = immutableList;
        this.sessionLengthMs = j;
        this.timeSinceSessionMs = j2;
    }

    public final String toString() {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("device_id", this.deviceId);
        A01.put(ARGUMENT_LAST_APPS_IN_VR, this.lastAppsInVR);
        A01.put(ARGUMENT_SESSION_LENGTH_MS, Long.valueOf(this.sessionLengthMs));
        A01.put(ARGUMENT_TIME_SINCE_SESSION_MS, Long.valueOf(this.timeSinceSessionMs));
        return GraphQLParamsHelper.encodeMutationParams(A01.build(), ImmutableMap.A01().build());
    }
}
