package com.facebook.mobileconfig.troubleshooting;

public interface BisectHelper {
    BisectState getCurrentState();

    void startBisection(String str, BisectCallback bisectCallback);

    boolean stopBisection();

    boolean userDidNotReproduceBug();

    boolean userDidReproduceBug();
}
