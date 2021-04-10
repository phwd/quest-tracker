package com.oculus.socialplatform.accounts.shared;

import android.net.Uri;

public class Constants {
    public static final String COLUMN_KEY_HAS_CHECKED_HAS_ACKNOWLEDGED_MESSENGER = "has_checked_has_acknowledged_messenger";
    public static final String COLUMN_KEY_HAS_SEEN_NEW_USER_AUTHENTICATION_DIALOG = "has_seen_new_user_authentication_dialog";
    public static final String COLUMN_KEY_MESSENGER_IS_AUTHENTICATED = "messenger_is_authenticated";
    public static final String COLUMN_KEY_USER_ID = "user_id";
    public static final String COLUMN_KEY_USER_NAME = "user_name";
    public static final String COLUMN_KEY_USER_PROFILE_PICTURE_URL = "user_profile_picture_url";
    public static final Uri CONTENT_PROVIDER_URI = Uri.parse("content://com.oculus.socialplatform.messengervraccounts/v1");
}
