package com.oculus.panellib;

import java.util.ArrayList;
import java.util.List;

public class PanelEnvironment {
    public static List<Listener> sListeners = new ArrayList();

    public interface Listener {
        void onPanelEnvironment(String[] strArr);
    }

    public static void addListener(Listener listener) {
        synchronized (sListeners) {
            sListeners.add(listener);
        }
    }

    public static void onPanelEnvironment(String[] strArr) {
        synchronized (sListeners) {
            for (Listener listener : sListeners) {
                listener.onPanelEnvironment(strArr);
            }
            sListeners.clear();
        }
    }
}
