package com.oculus.common.ocauth;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.Executors;

public final class ExecutorUtil {
    private static final Scheduler SCHEDULER = Schedulers.from(Executors.newScheduledThreadPool(2));

    public static Scheduler getScheduler() {
        return SCHEDULER;
    }
}
