package com.oculus.common.init;

import java.util.ArrayList;

public class AppInitLock {
    public boolean initialized;
    public ArrayList<Listener> listeners = new ArrayList<>();

    public static abstract class Listener {
    }
}
