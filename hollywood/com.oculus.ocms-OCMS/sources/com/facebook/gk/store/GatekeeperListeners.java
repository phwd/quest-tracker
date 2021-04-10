package com.facebook.gk.store;

public interface GatekeeperListeners {
    void registerListener(OnGatekeeperChangeListener onGatekeeperChangeListener, int i);

    void registerListeners(OnGatekeeperChangeListener onGatekeeperChangeListener, int... iArr);

    void unregisterListener(OnGatekeeperChangeListener onGatekeeperChangeListener, int i);

    void unregisterListeners(OnGatekeeperChangeListener onGatekeeperChangeListener, int... iArr);
}
