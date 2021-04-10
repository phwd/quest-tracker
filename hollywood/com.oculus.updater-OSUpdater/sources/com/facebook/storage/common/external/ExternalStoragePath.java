package com.facebook.storage.common.external;

import androidx.annotation.Nullable;

public class ExternalStoragePath {
    @Nullable
    private static Runnable mBadUseCallback;

    public @interface Reason {
    }
}
