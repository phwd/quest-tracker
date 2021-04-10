package com.facebook.voltron.metadata;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public enum AppModulePackaging {
    UNDEFINED,
    BUILT_IN,
    DOWNLOADABLE,
    BUILT_IN_AND_DOWNLOADABLE;

    public boolean isBuiltIn() {
        return this == BUILT_IN || this == BUILT_IN_AND_DOWNLOADABLE;
    }

    public boolean isDownloadable() {
        return this == DOWNLOADABLE || this == BUILT_IN_AND_DOWNLOADABLE;
    }
}
