package com.facebook.secure.context;

import java.util.ArrayList;
import java.util.List;

public abstract class IntentLaunchingPluginHolder {
    private static final List<IntentLaunchingPlugin> INTENT_LAUNCHING_PLUGINS = new ArrayList();

    public static void addPlugin(IntentLaunchingPlugin intentLaunchingPlugin) {
        INTENT_LAUNCHING_PLUGINS.add(intentLaunchingPlugin);
    }

    public static List<IntentLaunchingPlugin> getIntentLaunchingPlugins() {
        return INTENT_LAUNCHING_PLUGINS;
    }

    public static void clearPlugins() {
        INTENT_LAUNCHING_PLUGINS.clear();
    }
}
