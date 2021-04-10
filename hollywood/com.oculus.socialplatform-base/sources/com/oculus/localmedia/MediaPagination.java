package com.oculus.localmedia;

public class MediaPagination {
    public String mLastCursor;
    public String mStartCursor;
    public int mTotalCount;

    public static class Builder {
        public String mLastCursor;
        public String mStartCursor;
        public int mTotalCount = -1;

        public MediaPagination build() {
            return new MediaPagination(this.mTotalCount, this.mStartCursor, this.mLastCursor);
        }

        public Builder setLastCursor(String str) {
            this.mLastCursor = str;
            return this;
        }

        public Builder setStartCursor(String str) {
            this.mStartCursor = str;
            return this;
        }

        public Builder setTotalCount(int i) {
            this.mTotalCount = i;
            return this;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000f, code lost:
        if (r0.isEmpty() != false) goto L_0x0011;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject toJSON() {
        /*
            r5 = this;
            org.json.JSONObject r4 = new org.json.JSONObject
            r4.<init>()
            java.lang.String r0 = r5.mLastCursor     // Catch:{ JSONException -> 0x0042 }
            r3 = 1
            if (r0 == 0) goto L_0x0011
            boolean r0 = r0.isEmpty()     // Catch:{ JSONException -> 0x0042 }
            r2 = 1
            if (r0 == 0) goto L_0x0012
        L_0x0011:
            r2 = 0
        L_0x0012:
            java.lang.String r1 = r5.mStartCursor     // Catch:{ JSONException -> 0x0042 }
            if (r1 == 0) goto L_0x001d
            boolean r0 = r1.isEmpty()     // Catch:{ JSONException -> 0x0042 }
            if (r0 != 0) goto L_0x001d
            goto L_0x001e
        L_0x001d:
            r3 = 0
        L_0x001e:
            if (r3 == 0) goto L_0x0025
            java.lang.String r0 = "start_cursor"
            r4.put(r0, r1)     // Catch:{ JSONException -> 0x0042 }
        L_0x0025:
            if (r2 == 0) goto L_0x002e
            java.lang.String r1 = "end_cursor"
            java.lang.String r0 = r5.mLastCursor     // Catch:{ JSONException -> 0x0042 }
            r4.put(r1, r0)     // Catch:{ JSONException -> 0x0042 }
        L_0x002e:
            java.lang.String r0 = "has_next_page"
            r4.put(r0, r2)     // Catch:{ JSONException -> 0x0042 }
            java.lang.String r0 = "has_previous_page"
            r4.put(r0, r3)     // Catch:{ JSONException -> 0x0042 }
            int r1 = r5.mTotalCount     // Catch:{ JSONException -> 0x0042 }
            if (r1 < 0) goto L_0x0056
            java.lang.String r0 = "total_count"
            r4.put(r0, r1)     // Catch:{ JSONException -> 0x0042 }
            return r4
        L_0x0042:
            r3 = move-exception
            java.lang.String r2 = com.oculus.localmedia.LocalMediaManager.TAG
            java.lang.String r1 = "Could not serialize pageInfo : "
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r1)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r2, r0)
        L_0x0056:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.localmedia.MediaPagination.toJSON():org.json.JSONObject");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("MediaPagination{mTotalCount=");
        sb.append(this.mTotalCount);
        sb.append(", mStartCursor='");
        sb.append(this.mStartCursor);
        sb.append('\'');
        sb.append(", mLastCursor='");
        sb.append(this.mLastCursor);
        sb.append('\'');
        sb.append('}');
        return sb.toString();
    }

    public MediaPagination(int i, String str, String str2) {
        this.mTotalCount = i;
        this.mStartCursor = str;
        this.mLastCursor = str2;
    }

    public String getLastCursor() {
        return this.mLastCursor;
    }

    public String getStartCursor() {
        return this.mStartCursor;
    }

    public int getTotalCount() {
        return this.mTotalCount;
    }
}
