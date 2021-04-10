package com.facebook.secure.context;

import java.util.ArrayList;
import java.util.List;

public abstract class IntentLaunchingPluginHolder {
    private static final List<Object> INTENT_LAUNCHING_PLUGINS = new ArrayList();

    public static List<Object> getIntentLaunchingPlugins() {
        return INTENT_LAUNCHING_PLUGINS;
    }
}
