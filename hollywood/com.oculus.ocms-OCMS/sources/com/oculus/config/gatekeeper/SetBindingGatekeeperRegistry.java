package com.oculus.config.gatekeeper;

import com.google.common.collect.ImmutableSet;
import java.util.Collection;
import java.util.Set;

public class SetBindingGatekeeperRegistry implements GatekeeperRegistry {
    private final ImmutableSet<String> mGatekeeperSet;

    public SetBindingGatekeeperRegistry(Set<String> set) {
        this.mGatekeeperSet = ImmutableSet.copyOf((Collection) set);
    }

    @Override // com.oculus.config.gatekeeper.GatekeeperRegistry
    public ImmutableSet<String> getRegisteredGatekeepers() {
        return this.mGatekeeperSet;
    }

    @Override // com.oculus.config.gatekeeper.GatekeeperRegistry
    public boolean isGatekeeperRegistered(String str) {
        return getRegisteredGatekeepers().contains(str);
    }
}
