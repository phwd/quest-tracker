package com.oculus.socialplatform.provider.contract;

import X.C03030bw;
import android.net.Uri;

public class SocialPlatformContent {
    public static final String AUTHORITY = "com.oculus.socialplatform";

    public static class Parties3pApi {
        public static final String AUTHORITY = "com.oculus.socialplatform.partiesapi";
        public static final Uri BASE_PATH = C03030bw.A00("content://com.oculus.socialplatform.partiesapi");

        public static class In {
            public static final String CALLER_PACKAGE = "caller_package";
            public static final String EXCLUSIVE_MIC = "exclusive_mic";
            public static final String MIC_MUTE_STATE = "mic_mute_state";
            public static final String PACKAGE_NAME = "package_name";
            public static final String PARTY_ID = "party_id";
            public static final String VOIP_SUPPRESSED = "voip_suppressed";
            public static final String VOLUME = "volume";
        }

        public static class Out {
            public static final String MIC_MUTE_STATE = "mic_mute_state";
            public static final String STATE_JSON = "state_json";
            public static final String STATUS = "status";
            public static final String VOLUME = "volume";
        }

        public static class Queries {
            public static final Uri GET_DATA = C03030bw.A00("content://com.oculus.socialplatform.partiesapi/get_data");
            public static final Uri GET_MIC_MUTE_STATE = C03030bw.A00("content://com.oculus.socialplatform.partiesapi/get_mic_mute_state");
            public static final Uri GET_STATUS = C03030bw.A00("content://com.oculus.socialplatform.partiesapi/get_status");
            public static final Uri GET_VOLUME = C03030bw.A00("content://com.oculus.socialplatform.partiesapi/get_volume");
        }

        public static class Updates {
            public static final Uri ON_APP_SWITCH = C03030bw.A00("content://com.oculus.socialplatform.partiesapi/on_app_switch");
            public static final Uri RUN_PARTY_ENFORCER = C03030bw.A00("content://com.oculus.socialplatform.partiesapi/run_party_enforcer");
            public static final Uri SET_MIC_MUTE_STATE = C03030bw.A00("content://com.oculus.socialplatform.partiesapi/set_mic_mute_state");
            public static final Uri SET_SUPPRESSED = C03030bw.A00("content://com.oculus.socialplatform.partiesapi/set_suppressed");
            public static final Uri SET_VOLUME = C03030bw.A00("content://com.oculus.socialplatform.partiesapi/set_volume");
            public static final Uri START_CHAT = C03030bw.A00("content://com.oculus.socialplatform.partiesapi/start_chat");
            public static final Uri STOP_CHAT = C03030bw.A00("content://com.oculus.socialplatform.partiesapi/stop_chat");
        }
    }
}
