package com.oculus.platform.provider;

import android.net.Uri;

public class OculusContent {
    public static final String AUTHORITY = "com.oculus.horizon";
    public static final Uri CONTENT_URI = Uri.parse("content://com.oculus.horizon");

    public static class LinkedAccounts {
        public static final String ACCESS_TOKEN = "access_token";
        public static final Uri CONTENT_URI = Uri.parse("content://com.oculus.horizon.linkedaccounts/accounts");
        public static final String SERVICE_PROVIDER = "service_provider";
        public static final String USER_ID = "user_id";
    }

    public static class Profile {
        public static final Uri CONTENT_URI = Uri.parse("content://com.oculus.horizon/profile");
        public static final String ID = "id";
        public static final String IMAGE_URL = "image_url";
        public static final String LOCALE = "locale";
        public static final String USERNAME = "username";
    }
}
