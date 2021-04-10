package com.oculus.messengervr.fbshared.flipperplugin;

import com.facebook.common.lsplugins.config.PluginsConfigProvider;

@PluginsConfigProvider
public final class MessengerPluginsProductionConfig {
    @PluginsConfigProvider.ProvideGlobalPartInterfaceLogger
    public static MessengerVrPluginPartInterfaceLogger getGlobalLogger() {
        return MessengerVrPluginPartInterfaceLogger.INSTANCE;
    }
}
