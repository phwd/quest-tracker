package com.oculus.horizon.social.api;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.List;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class GetQueuedRemoteLaunchesResponse {
    public final Viewer viewer;

    public static class DeeplinkTarget {
        public String deeplink_message_for_deeplink_target;
        public String id;
        public final String launch_params_as_json_string;
        public PartyForDeeplinkTarget party_for_deeplink_target;
        @Nullable
        public final String system_route_params_mobile;
        public final List<VRAppsForDeeplinkTarget> vr_apps_for_deeplink_target;
    }

    public static class PartyForDeeplinkTarget {
        public String id;
    }

    public static class QueuedRemoteLaunch {
        public final DeeplinkTarget deeplink_target;
        public final String id;
    }

    public static class User {
        public String id;
        public final QueuedRemoteLaunch latest_queued_remote_launch;
    }

    public static class VRAppsForDeeplinkTarget {
        public final String display_name;
        public final String id;
        public final String package_name;
    }

    public static class Viewer {
        public final User user;
    }
}
