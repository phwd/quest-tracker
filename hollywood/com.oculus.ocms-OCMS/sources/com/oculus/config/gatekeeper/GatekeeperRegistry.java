package com.oculus.config.gatekeeper;

import com.google.common.collect.ImmutableSet;

public interface GatekeeperRegistry {
    ImmutableSet<String> getRegisteredGatekeepers();

    boolean isGatekeeperRegistered(String str);
}
