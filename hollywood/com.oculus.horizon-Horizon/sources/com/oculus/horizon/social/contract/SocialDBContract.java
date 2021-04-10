package com.oculus.horizon.social.contract;

import X.AnonymousClass006;
import android.net.Uri;
import com.facebook.internal.NativeProtocol;

public class SocialDBContract {

    public static class Parties {
        public static final String AUTHORITY;
        public static final Uri CONTENT_URI;

        static {
            String A05 = AnonymousClass006.A05("com.oculus.horizon", ".parties");
            AUTHORITY = A05;
            CONTENT_URI = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, A05, "/parties"));
        }
    }

    public static class Presence {
        public static final String ARG_USER_ID = "userId";
        public static final String AUTHORITY;
        public static final Uri CONTENT_URI;

        static {
            String A05 = AnonymousClass006.A05("com.oculus.horizon", ".social");
            AUTHORITY = A05;
            CONTENT_URI = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, A05, "/presence"));
        }
    }
}
