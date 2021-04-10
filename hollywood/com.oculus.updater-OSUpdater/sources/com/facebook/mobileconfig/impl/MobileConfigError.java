package com.facebook.mobileconfig.impl;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public enum MobileConfigError {
    NO_FLATBUFFER_SCHEMA_HASH,
    NO_CONFIG_TABLE_SCHEMA_HASH,
    FLATBUFFER_SCHEMA_MISMATCH,
    FLATBUFFER_MAGIC_MISMATCH,
    CONFIG_TABLE_SCHEMA_MISMATCH,
    CONFIG_TABLE_MAGIC_MISMATCH,
    OVERRIDES_EXIST
}
