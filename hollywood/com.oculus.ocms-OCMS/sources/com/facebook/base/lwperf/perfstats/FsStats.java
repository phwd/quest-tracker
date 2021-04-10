package com.facebook.base.lwperf.perfstats;

import android.os.ConditionVariable;
import com.facebook.common.statfs.StatFsHelper;
import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class FsStats {
    private static final long BYTES_PER_KB_SHIFT = 10;

    public FsStats() {
        FsStatsReporter.getInstance().getAvailableDiskSpaceKbAsync();
    }

    public long getAvailableDiskSpaceKb() {
        return FsStatsReporter.getInstance().getAvailableDiskSpaceKbAwait();
    }

    private static final class FsStatsReporter {
        private final AtomicLong mAvailableSpace;
        private final ExecutorService mExecutor;
        private final ConditionVariable mHasThreadRunCompleted;
        private final AtomicBoolean mIsRunning;

        /* access modifiers changed from: private */
        public static final class SingletonHolder {
            public static final FsStatsReporter instance = new FsStatsReporter();

            private SingletonHolder() {
            }
        }

        public static FsStatsReporter getInstance() {
            return SingletonHolder.instance;
        }

        private FsStatsReporter() {
            this.mExecutor = Executors.newSingleThreadExecutor();
            this.mHasThreadRunCompleted = new ConditionVariable(true);
            this.mIsRunning = new AtomicBoolean(false);
            this.mAvailableSpace = new AtomicLong(-1);
        }

        public void getAvailableDiskSpaceKbAsync() {
            if (!this.mIsRunning.getAndSet(true)) {
                this.mHasThreadRunCompleted.close();
                this.mExecutor.execute(new Runnable() {
                    /* class com.facebook.base.lwperf.perfstats.FsStats.FsStatsReporter.AnonymousClass1 */

                    public void run() {
                        FsStatsReporter.this.mAvailableSpace.set(StatFsHelper.getInstance().getAvailableStorageSpace(StatFsHelper.StorageType.INTERNAL) >> 10);
                        FsStatsReporter.this.mHasThreadRunCompleted.open();
                        FsStatsReporter.this.mIsRunning.set(false);
                    }
                });
            }
        }

        public long getAvailableDiskSpaceKbAwait() {
            this.mHasThreadRunCompleted.block();
            return this.mAvailableSpace.get();
        }
    }
}
