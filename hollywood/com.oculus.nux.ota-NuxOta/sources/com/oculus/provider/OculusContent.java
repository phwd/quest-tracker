package com.oculus.provider;

import android.net.Uri;
import com.oculus.common.build.BuildConstants;

public class OculusContent {
    public static final String AUTHORITY = BuildConstants.PACKAGE_NAME_HORIZON;
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static class Profile {
        public static final Uri CONTENT_URI = Uri.parse("content://" + OculusContent.AUTHORITY + "/profile");
    }
}
