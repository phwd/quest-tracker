package com.oculus.horizon.api.store;

import com.oculus.horizon.api.common.Item;
import com.oculus.horizon.api.common.PageInfo;
import java.util.ArrayList;

public class SectionItemsResponse {
    public Items all_items;

    public static class Items {
        public ArrayList<Item> nodes;
        public PageInfo page_info;
    }

    public PageInfo getPageInfo() {
        return this.all_items.page_info;
    }
}
