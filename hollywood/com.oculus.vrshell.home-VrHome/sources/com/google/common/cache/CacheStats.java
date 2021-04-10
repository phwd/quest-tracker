package com.google.common.cache;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public final class CacheStats {
    private final long evictionCount;
    private final long hitCount;
    private final long loadExceptionCount;
    private final long loadSuccessCount;
    private final long missCount;
    private final long totalLoadTime;

    public CacheStats(long hitCount2, long missCount2, long loadSuccessCount2, long loadExceptionCount2, long totalLoadTime2, long evictionCount2) {
        Preconditions.checkArgument(hitCount2 >= 0);
        Preconditions.checkArgument(missCount2 >= 0);
        Preconditions.checkArgument(loadSuccessCount2 >= 0);
        Preconditions.checkArgument(loadExceptionCount2 >= 0);
        Preconditions.checkArgument(totalLoadTime2 >= 0);
        Preconditions.checkArgument(evictionCount2 >= 0);
        this.hitCount = hitCount2;
        this.missCount = missCount2;
        this.loadSuccessCount = loadSuccessCount2;
        this.loadExceptionCount = loadExceptionCount2;
        this.totalLoadTime = totalLoadTime2;
        this.evictionCount = evictionCount2;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.hitCount), Long.valueOf(this.missCount), Long.valueOf(this.loadSuccessCount), Long.valueOf(this.loadExceptionCount), Long.valueOf(this.totalLoadTime), Long.valueOf(this.evictionCount));
    }

    public boolean equals(Object object) {
        if (!(object instanceof CacheStats)) {
            return false;
        }
        CacheStats other = (CacheStats) object;
        if (this.hitCount == other.hitCount && this.missCount == other.missCount && this.loadSuccessCount == other.loadSuccessCount && this.loadExceptionCount == other.loadExceptionCount && this.totalLoadTime == other.totalLoadTime && this.evictionCount == other.evictionCount) {
            return true;
        }
        return false;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("hitCount", this.hitCount).add("missCount", this.missCount).add("loadSuccessCount", this.loadSuccessCount).add("loadExceptionCount", this.loadExceptionCount).add("totalLoadTime", this.totalLoadTime).add("evictionCount", this.evictionCount).toString();
    }
}
