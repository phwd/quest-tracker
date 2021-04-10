package com.oculus.horizon.api.rating;

import com.facebook.common.string.StringUtil;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;

public class ReviewsRequest {
    private static final String KEY_COUNT = "count";
    private static final String KEY_CURSOR = "cursor";
    private static final String KEY_ITEM_ID = "item_id";
    private static final String KEY_SORT_ORDER = "sort_order";
    private final int mCount;
    private final String mCursor;
    private final ImmutableMap<String, String> mFilterOptions;
    private final String mItemId;
    private final String mSortOption;

    public ReviewsRequest(String str, String str2, int i, String str3, ImmutableMap immutableMap) {
        this.mItemId = str;
        this.mCursor = str2;
        this.mCount = i;
        this.mSortOption = str3;
        this.mFilterOptions = immutableMap;
    }

    public ImmutableMap<String, String> getParams() {
        ImmutableMap.Builder put = ImmutableMap.builder().put("item_id", this.mItemId).put(KEY_COUNT, String.valueOf(this.mCount)).put(KEY_SORT_ORDER, this.mSortOption);
        ImmutableMap<String, String> immutableMap = this.mFilterOptions;
        if (immutableMap != null) {
            UnmodifiableIterator<String> it = immutableMap.keySet().iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (!StringUtil.isEmptyOrNull(this.mFilterOptions.get(next))) {
                    put = put.put(next, this.mFilterOptions.get(next));
                }
            }
        }
        String str = this.mCursor;
        if (str != null) {
            put.put("cursor", str);
        }
        return put.build();
    }
}
