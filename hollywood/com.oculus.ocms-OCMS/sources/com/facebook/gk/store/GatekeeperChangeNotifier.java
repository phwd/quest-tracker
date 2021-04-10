package com.facebook.gk.store;

import java.util.List;

public interface GatekeeperChangeNotifier {
    void gatekeepersChanged(GatekeeperStore gatekeeperStore, List<Integer> list);
}
