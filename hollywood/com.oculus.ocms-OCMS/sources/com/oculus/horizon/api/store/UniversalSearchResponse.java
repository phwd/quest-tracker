package com.oculus.horizon.api.store;

import com.oculus.horizon.api.common.Item;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.util.collection.CollectionUtil;
import java.util.List;

@SingleEntryMapResponse
public class UniversalSearchResponse {
    public List<UniversalSearchFilter> filters;
    public Results results;

    public static class Results {
        public List<UniversalSearchResultItem> nodes;
    }

    public static class UniversalSearchFilter {
        public int count;
        public String display_name;
        public String name;
    }

    public static class UniversalSearchResultItem {
        public Item object;
        public String unit_type;
    }

    public List<Item> getResults() {
        return CollectionUtil.mapList(this.results.nodes, new CollectionUtil.Predicate<UniversalSearchResultItem, Item>() {
            /* class com.oculus.horizon.api.store.UniversalSearchResponse.AnonymousClass1 */

            public Item function(UniversalSearchResultItem universalSearchResultItem) {
                if (universalSearchResultItem.object.getItemForUniversalSearch() == null) {
                    UniversalSearchResponse.this.adjustCounts();
                }
                return universalSearchResultItem.object.getItemForUniversalSearch();
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void adjustCounts() {
        CollectionUtil.mapList(this.filters, new CollectionUtil.Predicate<UniversalSearchFilter, UniversalSearchFilter>() {
            /* class com.oculus.horizon.api.store.UniversalSearchResponse.AnonymousClass2 */

            public UniversalSearchFilter function(UniversalSearchFilter universalSearchFilter) {
                if (!(universalSearchFilter == null || universalSearchFilter.name == null || !universalSearchFilter.name.equals("cinema_video"))) {
                    universalSearchFilter.count--;
                }
                return universalSearchFilter;
            }
        });
    }
}
