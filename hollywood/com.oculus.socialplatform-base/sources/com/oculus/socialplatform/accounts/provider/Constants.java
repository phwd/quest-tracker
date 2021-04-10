package com.oculus.socialplatform.accounts.provider;

import android.net.Uri;
import com.oculus.common.fbauth.FBAuthManager;
import com.oculus.common.socialtablet.fbauth.FBAccountManager;

public class Constants {
    public static final String MESSENGER_VR_FB_APP_ID = "581956559359077";
    public static final String MESSENGER_VR_OC_APP_ID = "3534234083363713";

    public static final class FbConnect {
        public static final String AUTHORITY = "com.oculus.horizon.fbconnect";
        public static final String COLUMN_KEY_ACCESS_TOKEN = "access_token";
        public static final String COLUMN_KEY_FB_APP_ID = "fb_app_id";
        public static final String COLUMN_KEY_USER_ID = "userid";
        public static final Uri FB_CONNECT_ACCOUNT_CONTENT_PROVIDER_URI = Uri.parse(FBAccountManager.FbConnectAccountConstants.URI_STRING);
        public static final Uri FB_CONNECT_APP_SCOPED_ACCESS_TOKEN_URI = Uri.parse(FBAuthManager.URI_STRING);
        public static final String PARAM_KEY_OVERRIDE_OC_APP_ID = "override_oc_app_id";
    }
}
