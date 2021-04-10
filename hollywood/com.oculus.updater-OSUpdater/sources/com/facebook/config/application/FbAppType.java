package com.facebook.config.application;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Nullsafe(Nullsafe.Mode.LOCAL)
@Immutable
public class FbAppType {
    @Nullable
    private static volatile FbAppType sAppType;

    @Nullable
    @Deprecated
    public static FbAppType getAppType() {
        return sAppType;
    }
}
