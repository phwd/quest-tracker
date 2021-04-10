package com.oculus.localmedia;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

public class MediaPagination {
    private String mLastCursor;
    private String mStartCursor;
    private int mTotalCount;

    public MediaPagination(int totalCount, String startCursor, String lastCursor) {
        this.mTotalCount = totalCount;
        this.mStartCursor = startCursor;
        this.mLastCursor = lastCursor;
    }

    public String getStartCursor() {
        return this.mStartCursor;
    }

    public String getLastCursor() {
        return this.mLastCursor;
    }

    public int getTotalCount() {
        return this.mTotalCount;
    }

    public JSONObject toJSON() {
        boolean hasPreviousPage = true;
        JSONObject json = new JSONObject();
        try {
            boolean hasNextPage = this.mLastCursor != null && !this.mLastCursor.isEmpty();
            if (this.mStartCursor == null || this.mStartCursor.isEmpty()) {
                hasPreviousPage = false;
            }
            if (hasPreviousPage) {
                json.put("start_cursor", this.mStartCursor);
            }
            if (hasNextPage) {
                json.put("end_cursor", this.mLastCursor);
            }
            json.put("has_next_page", hasNextPage);
            json.put("has_previous_page", hasPreviousPage);
            if (this.mTotalCount >= 0) {
                json.put("total_count", this.mTotalCount);
            }
        } catch (JSONException e) {
            Log.e(LocalMediaManager.TAG, "Could not serialize pageInfo : " + e);
        }
        return json;
    }

    public String toString() {
        return "MediaPagination{mTotalCount=" + this.mTotalCount + ", mStartCursor='" + this.mStartCursor + '\'' + ", mLastCursor='" + this.mLastCursor + '\'' + '}';
    }

    public static class Builder {
        private String mLastCursor;
        private String mStartCursor;
        private int mTotalCount = -1;

        public Builder setTotalCount(int mTotalCount2) {
            this.mTotalCount = mTotalCount2;
            return this;
        }

        public Builder setStartCursor(String mStartCursor2) {
            this.mStartCursor = mStartCursor2;
            return this;
        }

        public Builder setLastCursor(String mLastCursor2) {
            this.mLastCursor = mLastCursor2;
            return this;
        }

        public MediaPagination build() {
            return new MediaPagination(this.mTotalCount, this.mStartCursor, this.mLastCursor);
        }
    }
}
