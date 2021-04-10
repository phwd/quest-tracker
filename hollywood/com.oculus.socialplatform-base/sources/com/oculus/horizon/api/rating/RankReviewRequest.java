package com.oculus.horizon.api.rating;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.common.Item;
import javax.annotation.Nullable;

public class RankReviewRequest {
    public String mQualityRatingId;
    @Nullable
    public String mRankByUser;

    public RankReviewRequest(String str, Item.QualityRating.Rank rank) {
        this.mQualityRatingId = str;
        this.mRankByUser = rank.rankString;
    }

    public ImmutableMap<String, String> getParams() {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("quality_rating_id", this.mQualityRatingId);
        String str = this.mRankByUser;
        if (!(str == null || "".compareTo(str) == 0)) {
            A04.put("rank_by_user", str);
        }
        return A04.build();
    }
}
