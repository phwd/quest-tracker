package com.facebook.common.componentmap;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public enum ComponentMapType {
    NONE,
    FRAGMENT_CHROME_ACTIVITY,
    TRANSPARENT_FRAGMENT_CHROME_ACTIVITY,
    REACT_FRAGMENT_ACTIVITY,
    FOX_FRAGMENT_ACTIVITY,
    TRANSPARENT_REACT_FRAGMENT_ACTIVITY
}
