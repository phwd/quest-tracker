package com.oculus.common.init;

import java.util.ArrayList;

public class AppInitLock {
    public boolean initialized;
    public ArrayList<Listener> listeners = new ArrayList<>();

    public static abstract class Listener {
    }

    public final synchronized void A00() {
        while (!this.initialized) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
