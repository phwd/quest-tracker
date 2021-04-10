package com.facebook.storage.attribution;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public enum Infra {
    ANDROID_CONTEXT,
    STASH,
    CASK,
    DATABASE,
    IG_CACHE,
    TEMP_FILE_MANAGER
}
