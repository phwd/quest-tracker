package com.oculus.localmedia;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

public class FolderMediaCount {
    private int mAggregatedCount = 0;
    private int mCount = 0;

    public void add(FolderMediaCount count) {
        if (count != null) {
            this.mCount += count.mCount;
            this.mAggregatedCount += count.mAggregatedCount;
        }
    }

    public int getCount() {
        return this.mCount;
    }

    public int getAggregatedCount() {
        return this.mAggregatedCount;
    }

    public void increaseCount() {
        this.mCount++;
        this.mAggregatedCount++;
    }

    public void increaseAggregateCount() {
        this.mAggregatedCount++;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put("count", this.mCount);
            json.put("aggregatedCount", this.mAggregatedCount);
        } catch (JSONException e) {
            Log.e(LocalMediaManager.TAG, "Could not serialize to JSON");
        }
        return json;
    }
}
