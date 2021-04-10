package com.facebook.gk.store;

import java.util.ArrayList;

public interface GatekeeperStoreConfig {
    ArrayList<String> getGatekeeperNames();

    String getGatekeeperNamesHash();

    int getNumberOfGatekeepers();
}
