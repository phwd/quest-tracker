package com.oculus.horizon.api.rating;

import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;

public class AddRatingRequest extends ApiRequest<Void> {
    public final String packageName;
    public final int rating;
    public final String reviewDescription;
    public final String reviewTitle;
    public final int versionCode;

    public AddRatingRequest(String str, int i, int i2, String str2, String str3) {
        this.packageName = str;
        this.versionCode = i;
        this.rating = i2;
        this.reviewTitle = str2;
        this.reviewDescription = str3;
    }

    public ImmutableMap<String, String> getParams() {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("package_name", this.packageName);
        A04.put("version_code", String.valueOf(this.versionCode));
        A04.put("quality_rating", String.valueOf(this.rating));
        String str = this.reviewTitle;
        if (str != null) {
            A04.put("review_title", str);
        }
        String str2 = this.reviewDescription;
        if (str2 != null) {
            A04.put("review_description", str2);
        }
        return A04.build();
    }
}
