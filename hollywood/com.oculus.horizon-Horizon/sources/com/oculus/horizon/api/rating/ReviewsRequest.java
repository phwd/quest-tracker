package com.oculus.horizon.api.rating;

import X.AbstractC07380s1;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

public class ReviewsRequest {
    public static final String KEY_COUNT = "count";
    public static final String KEY_CURSOR = "cursor";
    public static final String KEY_ITEM_ID = "item_id";
    public static final String KEY_SORT_ORDER = "sort_order";
    public final int mCount;
    public final String mCursor;
    public final ImmutableMap<String, String> mFilterOptions;
    public final String mItemId;
    public final String mSortOption;

    public ReviewsRequest(String str, String str2, int i, String str3, ImmutableMap immutableMap) {
        this.mItemId = str;
        this.mCursor = str2;
        this.mCount = i;
        this.mSortOption = str3;
        this.mFilterOptions = immutableMap;
    }

    public ImmutableMap<String, String> getParams() {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("item_id", this.mItemId);
        A01.put("count", String.valueOf(this.mCount));
        A01.put(KEY_SORT_ORDER, this.mSortOption);
        ImmutableMap<String, String> immutableMap = this.mFilterOptions;
        if (immutableMap != null) {
            ImmutableSet<K> immutableSet = immutableMap.A01;
            if (immutableSet == null) {
                immutableSet = (ImmutableSet<K>) immutableMap.A07();
                immutableMap.A01 = immutableSet;
            }
            AbstractC07380s1<K> A0K = immutableSet.iterator();
            while (A0K.hasNext()) {
                K next = A0K.next();
                String str = this.mFilterOptions.get(next);
                if (!(str == null || str.length() == 0)) {
                    A01.put(next, this.mFilterOptions.get(next));
                }
            }
        }
        String str2 = this.mCursor;
        if (str2 != null) {
            A01.put("cursor", str2);
        }
        return A01.build();
    }
}
