package com.facebook.common.errorreporting;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ErrorReport {

    public enum Impact {
        LOGGING,
        MEDIUM_SEVERITY,
        HIGH_SEVERITY,
        EXTREME_SEVERITY,
        CRASH_LIKE,
        FAIL_FUNCTIONAL,
        FAIL_CONTENT_CREATION,
        PRIVACY,
        LEGACY,
        LEGACY_FAIL_HARDER
    }

    public enum Module {
        NEWSFEED,
        STORY_VIEWER,
        REACT_NATIVE,
        LITHO,
        INFRA,
        LOGGING,
        LEGACY
    }
}
