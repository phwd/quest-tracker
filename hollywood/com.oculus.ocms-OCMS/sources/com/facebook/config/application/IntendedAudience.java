package com.facebook.config.application;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public enum IntendedAudience {
    PUBLIC,
    FACEBOOK,
    DEVELOPMENT
}
