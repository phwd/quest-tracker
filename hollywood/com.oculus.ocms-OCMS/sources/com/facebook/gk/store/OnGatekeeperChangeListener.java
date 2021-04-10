package com.facebook.gk.store;

import com.facebook.common.listeners.Listener;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class OnGatekeeperChangeListener implements Listener<GatekeeperStore, Integer> {
    public abstract void onGatekeeperChange(GatekeeperStore gatekeeperStore, int i);

    public void call(GatekeeperStore gatekeeperStore, Integer num) {
        onGatekeeperChange(gatekeeperStore, num.intValue());
    }
}
