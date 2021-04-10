package com.oculus.panellib;

import java.util.ArrayList;
import java.util.List;

public class PanelEnvironment {
    private static List<Listener> sListeners = new ArrayList();

    public interface Listener {
        void onPanelEnvironment(String[] strArr);
    }

    public static void addListener(Listener l) {
        synchronized (sListeners) {
            sListeners.add(l);
        }
    }

    public static void onPanelEnvironment(String[] env) {
        synchronized (sListeners) {
            for (Listener l : sListeners) {
                l.onPanelEnvironment(env);
            }
            sListeners.clear();
        }
    }
}
