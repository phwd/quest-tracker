package com.facebook.common.dextricks.stats;

import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.atomic.AtomicReference;

@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class ClassLoadingStats {
    private static final AtomicReference<ClassLoadingStats> sInstance = new AtomicReference<>();

    public abstract void decrementDexFileQueries();

    /* access modifiers changed from: protected */
    public abstract int getClassLoadsAttempted();

    /* access modifiers changed from: protected */
    public abstract int getClassLoadsFailed();

    /* access modifiers changed from: protected */
    public abstract int getDexFileQueries();

    /* access modifiers changed from: protected */
    public abstract int getIncorrectDfaGuesses();

    /* access modifiers changed from: protected */
    public abstract int getLocatorAssistedClassLoads();

    public abstract void incrementClassLoadsAttempted();

    public abstract void incrementClassLoadsFailed();

    public abstract void incrementDexFileQueries(int i);

    public abstract void incrementIncorrectDfaGuesses();

    public abstract void incrementLocatorAssistedClassLoads();

    public static ClassLoadingStats getInstance() {
        if (sInstance.get() == null) {
            return new NullClassLoadingStats();
        }
        return sInstance.get();
    }

    public static ClassLoadingStats setInstance(ClassLoadingStats classLoadingStats) {
        sInstance.getAndSet(classLoadingStats);
        return classLoadingStats;
    }

    public SnapshotStats getDifference(SnapshotStats snapshotStats) {
        return new SnapshotStats(getClassLoadsAttempted() - snapshotStats.classLoadsAttempted, getClassLoadsFailed() - snapshotStats.classLoadsFailed, getDexFileQueries() - snapshotStats.dexFileQueries, getLocatorAssistedClassLoads() - snapshotStats.locatorAssistedClassLoads, getIncorrectDfaGuesses() - snapshotStats.incorrectDfaGuesses);
    }

    public SnapshotStats getSnapShot() {
        return new SnapshotStats(getClassLoadsAttempted(), getClassLoadsFailed(), getDexFileQueries(), getLocatorAssistedClassLoads(), getIncorrectDfaGuesses());
    }

    public static class SnapshotStats {
        public final int classLoadsAttempted;
        public final int classLoadsFailed;
        public final int dexFileQueries;
        public final int incorrectDfaGuesses;
        public final int locatorAssistedClassLoads;

        public SnapshotStats(int i, int i2, int i3, int i4, int i5) {
            this.classLoadsAttempted = i;
            this.classLoadsFailed = i2;
            this.dexFileQueries = i3;
            this.locatorAssistedClassLoads = i4;
            this.incorrectDfaGuesses = i5;
        }

        public String toString() {
            return String.format("[ Class Load Attempts: %d, Class Loads Failed: %d, Dex Queries: %d, Locator-assisted Class Loads: %d, Incorrect DFA Guesses: %d ]", Integer.valueOf(this.classLoadsAttempted), Integer.valueOf(this.classLoadsFailed), Integer.valueOf(this.dexFileQueries), Integer.valueOf(this.locatorAssistedClassLoads), Integer.valueOf(this.incorrectDfaGuesses));
        }
    }

    private static class NullClassLoadingStats extends ClassLoadingStats {
        @Override // com.facebook.common.dextricks.stats.ClassLoadingStats
        public void decrementDexFileQueries() {
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.common.dextricks.stats.ClassLoadingStats
        public int getClassLoadsAttempted() {
            return 0;
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.common.dextricks.stats.ClassLoadingStats
        public int getClassLoadsFailed() {
            return 0;
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.common.dextricks.stats.ClassLoadingStats
        public int getDexFileQueries() {
            return 0;
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.common.dextricks.stats.ClassLoadingStats
        public int getIncorrectDfaGuesses() {
            return 0;
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.common.dextricks.stats.ClassLoadingStats
        public int getLocatorAssistedClassLoads() {
            return 0;
        }

        @Override // com.facebook.common.dextricks.stats.ClassLoadingStats
        public void incrementClassLoadsAttempted() {
        }

        @Override // com.facebook.common.dextricks.stats.ClassLoadingStats
        public void incrementClassLoadsFailed() {
        }

        @Override // com.facebook.common.dextricks.stats.ClassLoadingStats
        public void incrementDexFileQueries(int i) {
        }

        @Override // com.facebook.common.dextricks.stats.ClassLoadingStats
        public void incrementIncorrectDfaGuesses() {
        }

        @Override // com.facebook.common.dextricks.stats.ClassLoadingStats
        public void incrementLocatorAssistedClassLoads() {
        }

        private NullClassLoadingStats() {
        }
    }
}
