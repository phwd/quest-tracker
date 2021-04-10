package com.fasterxml.jackson.databind.util;

public abstract class ViewMatcher {
    public abstract boolean isVisibleForView(Class<?> cls);
}
