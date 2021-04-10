package com.facebook.gk.store;

import java.util.Map;

public interface PreExistingGatekeeperStatesProvider {
    Map<String, Boolean> getPreExistingGatekeeperStates();
}
