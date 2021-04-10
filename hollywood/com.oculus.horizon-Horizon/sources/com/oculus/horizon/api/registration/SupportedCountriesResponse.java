package com.oculus.horizon.api.registration;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;

public class SupportedCountriesResponse {
    public final ArrayList<Data> data;

    public static class Data {
        public final List<String> countries;
    }

    public ImmutableList<String> getCountries() {
        ImmutableList.Builder A02 = ImmutableList.A02();
        A02.A03(this.data.get(0).countries);
        return A02.build();
    }
}
