package com.oculus.horizon.api.rating;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.common.Item;
import javax.annotation.Nullable;

public class RankReviewRequest {
    private String mQualityRatingId;
    @Nullable
    private String mRankByUser;

    public RankReviewRequest(String str, Item.QualityRating.Rank rank) {
        this.mQualityRatingId = str;
        this.mRankByUser = rank.rankString;
    }

    public ImmutableMap<String, String> getParams() {
        ImmutableMap.Builder put = ImmutableMap.builder().put("quality_rating_id", this.mQualityRatingId);
        String str = this.mRankByUser;
        if (!(str == null || "".compareTo(str) == 0)) {
            put.put("rank_by_user", this.mRankByUser);
        }
        return put.build();
    }
}
