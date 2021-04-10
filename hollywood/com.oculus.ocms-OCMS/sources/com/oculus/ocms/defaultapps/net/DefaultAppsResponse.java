package com.oculus.ocms.defaultapps.net;

import com.oculus.horizon.api.common.Item;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.List;

@SingleEntryMapResponse
public class DefaultAppsResponse {
    public Node node;

    public static class Node {
        public List<Item> default_applications;
        public Item default_environment;
    }
}
