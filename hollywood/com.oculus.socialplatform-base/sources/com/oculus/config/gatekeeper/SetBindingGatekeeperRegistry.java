package com.oculus.config.gatekeeper;

import com.google.common.collect.ImmutableSet;
import java.util.Set;

public class SetBindingGatekeeperRegistry implements GatekeeperRegistry {
    public final ImmutableSet<String> mGatekeeperSet;

    @Override // com.oculus.config.gatekeeper.GatekeeperRegistry
    public ImmutableSet<String> getRegisteredGatekeepers() {
        return this.mGatekeeperSet;
    }

    public SetBindingGatekeeperRegistry(Set<String> set) {
        this.mGatekeeperSet = ImmutableSet.A04(set);
    }

    @Override // com.oculus.config.gatekeeper.GatekeeperRegistry
    public boolean isGatekeeperRegistered(String str) {
        return getRegisteredGatekeepers().contains(str);
    }
}
