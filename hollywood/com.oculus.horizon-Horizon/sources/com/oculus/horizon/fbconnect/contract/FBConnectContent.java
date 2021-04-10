package com.oculus.horizon.fbconnect.contract;

import X.AnonymousClass006;
import X.C03030bw;
import android.net.Uri;
import com.facebook.internal.NativeProtocol;

public class FBConnectContent {
    public static final String AUTHORITY = "com.oculus.horizon.fbconnect";

    public static class Account {
        public static final String ACCESS_TOKEN = "accesstoken";
        public static final Uri CONTENT_URI = C03030bw.A00(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, FBConnectContent.AUTHORITY, "/account"));
        public static final String PROFILE_PICTURE = "profile_picture";
        public static final String USER_ID = "userid";
        public static final String USER_NAME = "user_name";
    }

    public static class AppScopedAccessToken {
        public static final String ACCESS_TOKEN = "access_token";
        public static final String FB_APP_ID = "fb_app_id";
        public static final String OVERRIDE_OC_APP_ID = "override_oc_app_id";
        public static final Uri URI = C03030bw.A00(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, FBConnectContent.AUTHORITY, "/app_scoped_access_token"));
    }
}
