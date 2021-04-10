package com.oculus.horizon.social.api;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.List;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class GetQueuedRemoteLaunchesResponse {
    public Viewer viewer;

    public static class DeeplinkTarget {
        public String deeplink_message_for_deeplink_target;
        public String id;
        public String launch_params_as_json_string;
        public PartyForDeeplinkTarget party_for_deeplink_target;
        @Nullable
        public String system_route_params_mobile;
        public List<VRAppsForDeeplinkTarget> vr_apps_for_deeplink_target;
    }

    public static class PartyForDeeplinkTarget {
        public String id;
    }

    public static class QueuedRemoteLaunch {
        public DeeplinkTarget deeplink_target;
        public String id;
    }

    public static class User {
        public String id;
        public QueuedRemoteLaunch latest_queued_remote_launch;
    }

    public static class VRAppsForDeeplinkTarget {
        public String display_name;
        public String id;
        public String package_name;
    }

    public static class Viewer {
        public User user;
    }
}
