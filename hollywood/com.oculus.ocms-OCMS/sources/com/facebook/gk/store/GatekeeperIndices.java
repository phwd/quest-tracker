package com.facebook.gk.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.concurrent.Immutable;

@Immutable
public class GatekeeperIndices {
    private final Map<String, Integer> mGatekeeperIndices;

    public GatekeeperIndices(GatekeeperStoreConfig gatekeeperStoreConfig) {
        this.mGatekeeperIndices = generateGatekeeperIndices(gatekeeperStoreConfig.getGatekeeperNames());
    }

    private static Map<String, Integer> generateGatekeeperIndices(ArrayList<String> arrayList) {
        int size = arrayList.size();
        HashMap hashMap = new HashMap(size);
        for (int i = 0; i < size; i++) {
            hashMap.put(arrayList.get(i), Integer.valueOf(i));
        }
        return hashMap;
    }

    public Integer get(String str) {
        return this.mGatekeeperIndices.get(str);
    }
}
