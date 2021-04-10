package com.oculus.horizon.api.store;

import com.oculus.horizon.api.common.Item;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.util.collection.CollectionUtil;
import java.util.List;

@SingleEntryMapResponse
public class UniversalSearchResponse {
    public final List<UniversalSearchFilter> filters;
    public final Results results;

    public static class Results {
        public final List<UniversalSearchResultItem> nodes;
    }

    public static class UniversalSearchFilter {
        public int count;
        public String display_name;
        public final String name;
    }

    public static class UniversalSearchResultItem {
        public final Item object;
        public String unit_type;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void adjustCounts() {
        CollectionUtil.A00(this.filters, new CollectionUtil.Predicate<UniversalSearchFilter, UniversalSearchFilter>() {
            /* class com.oculus.horizon.api.store.UniversalSearchResponse.AnonymousClass2 */

            public UniversalSearchFilter function(UniversalSearchFilter universalSearchFilter) {
                String str;
                if (!(universalSearchFilter == null || (str = universalSearchFilter.name) == null || !str.equals("cinema_video"))) {
                    universalSearchFilter.count--;
                }
                return universalSearchFilter;
            }

            /* Return type fixed from 'java.lang.Object' to match base method */
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            @Override // com.oculus.util.collection.CollectionUtil.Predicate
            public /* bridge */ /* synthetic */ UniversalSearchFilter function(UniversalSearchFilter universalSearchFilter) {
                UniversalSearchFilter universalSearchFilter2 = universalSearchFilter;
                function(universalSearchFilter2);
                return universalSearchFilter2;
            }
        });
    }

    public List<Item> getResults() {
        return CollectionUtil.A00(this.results.nodes, new CollectionUtil.Predicate<UniversalSearchResultItem, Item>() {
            /* class com.oculus.horizon.api.store.UniversalSearchResponse.AnonymousClass1 */

            public Item function(UniversalSearchResultItem universalSearchResultItem) {
                if (universalSearchResultItem.object.getItemForUniversalSearch() == null) {
                    UniversalSearchResponse.this.adjustCounts();
                }
                return universalSearchResultItem.object.getItemForUniversalSearch();
            }
        });
    }
}
