package com.oculus.defaultapps.net;

import com.oculus.horizon.api.common.Item;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.List;

@SingleEntryMapResponse
public class DefaultAppsResponse {
    public final Node node;

    public static class Node {
        public final List<Item> default_applications;
        public final Item default_environment;
    }
}
