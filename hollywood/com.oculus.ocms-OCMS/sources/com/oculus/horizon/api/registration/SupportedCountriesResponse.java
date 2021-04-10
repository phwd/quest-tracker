package com.oculus.horizon.api.registration;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;

public class SupportedCountriesResponse {
    public ArrayList<Data> data;

    public static class Data {
        public List<String> countries;
    }

    public ImmutableList<String> getCountries() {
        return ImmutableList.builder().addAll((Iterable) this.data.get(0).countries).build();
    }
}
