package com.oculus.util.thread;

import com.facebook.ultralight.Dependencies;

@Dependencies({})
public class ThreadUtils {
    public static final String CANT_BE_UI_THREAD = "This operation must not be run on UI thread.";
    public static final String MUST_BE_UI_THREAD = "This operation must be run on UI thread.";
}
