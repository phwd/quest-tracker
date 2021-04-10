package com.oculus.horizon.api.store;

import com.oculus.horizon.api.common.Item;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.List;

@SingleEntryMapResponse
public class ApplicationSearchResponse {
    public Results results;

    public static class Results {
        public List<Item> nodes;
    }
}
