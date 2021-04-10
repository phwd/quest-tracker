package com.oculus.partiescontent;

import android.net.Uri;

public class PartiesContent {
    public static final String CONTENT_AUTHORITY_BASE = "content://com.oculus.socialplatform.partiesapi";
    public static final Uri CONTENT_AUTHORITY_BASE_URI = Uri.parse(CONTENT_AUTHORITY_BASE);

    public static class Callbacks {
        public static final String APP_CHAT_AVAILABILITY_CHANGED = "/app_chat_availability_changed";
        public static final String PARTY_MIC_STATE_CHANGED = "/sync_mic_state";
        public static final String PARTY_VOIP_CONNECTION_STATUS_CHANGED = "/set_voip_connection_status";
    }
}
