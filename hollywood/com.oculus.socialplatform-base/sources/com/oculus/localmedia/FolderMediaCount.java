package com.oculus.localmedia;

import android.util.Log;
import com.oculus.horizon.api.rating.ReviewsRequest;
import org.json.JSONException;
import org.json.JSONObject;

public class FolderMediaCount {
    public int mAggregatedCount;
    public int mCount;

    public void add(FolderMediaCount folderMediaCount) {
        if (folderMediaCount != null) {
            this.mCount += folderMediaCount.mCount;
            this.mAggregatedCount += folderMediaCount.mAggregatedCount;
        }
    }

    public void increaseAggregateCount() {
        this.mAggregatedCount++;
    }

    public void increaseCount() {
        this.mCount++;
        this.mAggregatedCount++;
    }

    public JSONObject toJSON() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(ReviewsRequest.KEY_COUNT, this.mCount);
            jSONObject.put("aggregatedCount", this.mAggregatedCount);
            return jSONObject;
        } catch (JSONException unused) {
            Log.e(LocalMediaManager.TAG, "Could not serialize to JSON");
            return jSONObject;
        }
    }

    public int getAggregatedCount() {
        return this.mAggregatedCount;
    }

    public int getCount() {
        return this.mCount;
    }

    public FolderMediaCount() {
        this.mCount = 0;
        this.mAggregatedCount = 0;
    }

    public FolderMediaCount(int i, int i2) {
        this();
        this.mCount = i;
        this.mAggregatedCount = i2;
    }
}
