package com.facebook.common.manifest;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ManifestConstants {

    public static class Metadata {
        public static final String KEY_BUILD_TIME = "com.facebook.build_time";
        public static final String KEY_VERSION_CONTROL_BRANCH = "com.facebook.versioncontrol.branch";
        public static final String KEY_VERSION_CONTROL_REVISION = "com.facebook.versioncontrol.revision";
    }
}
