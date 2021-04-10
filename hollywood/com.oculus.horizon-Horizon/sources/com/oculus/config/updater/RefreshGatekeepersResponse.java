package com.oculus.config.updater;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;

public class RefreshGatekeepersResponse {
    public final ArrayList<Data> data;

    public static class Data {
        public final List<Boolean> results;
    }

    public ImmutableList<Boolean> getGatekeeperValues() {
        ImmutableList.Builder A02 = ImmutableList.A02();
        A02.A03(this.data.get(0).results);
        return A02.build();
    }
}
