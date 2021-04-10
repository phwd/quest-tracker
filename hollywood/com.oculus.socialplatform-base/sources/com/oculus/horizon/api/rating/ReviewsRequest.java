package com.oculus.horizon.api.rating;

import X.AbstractC05710wh;
import com.google.common.collect.ImmutableMap;

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
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("item_id", this.mItemId);
        A04.put(KEY_COUNT, String.valueOf(this.mCount));
        A04.put(KEY_SORT_ORDER, this.mSortOption);
        ImmutableMap<String, String> immutableMap = this.mFilterOptions;
        if (immutableMap != null) {
            AbstractC05710wh<String> A0I = immutableMap.keySet().iterator();
            while (A0I.hasNext()) {
                String next = A0I.next();
                String str = this.mFilterOptions.get(next);
                if (!(str == null || str.length() == 0)) {
                    A04.put(next, this.mFilterOptions.get(next));
                }
            }
        }
        String str2 = this.mCursor;
        if (str2 != null) {
            A04.put("cursor", str2);
        }
        return A04.build();
    }
}
