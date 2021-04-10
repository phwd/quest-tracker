package com.facebook.gk.store;

public interface GatekeeperStoreManager {
    void cleanupUserGatekeepers(String str);

    void load();

    void loadUserGatekeepersIfExists(String str);

    void reset();

    void setChangeNotifier(GatekeeperChangeNotifier gatekeeperChangeNotifier);
}
