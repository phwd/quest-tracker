package com.facebook.crudolib.urimap;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public enum UriCallerScope {
    SAME_APP,
    SAME_KEY_APP,
    FAMILY_APP,
    PUBLIC
}
