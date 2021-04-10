package com.facebook.crudolib.urimap;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public enum AccessScope {
    SAME_APP,
    SAME_KEY,
    PUBLIC
}
