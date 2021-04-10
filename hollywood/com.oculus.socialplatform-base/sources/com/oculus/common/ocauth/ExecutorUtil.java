package com.oculus.common.ocauth;

import X.AbstractC12361xL;
import X.AnonymousClass1xJ;
import java.util.concurrent.Executors;

public final class ExecutorUtil {
    public static final AbstractC12361xL SCHEDULER = new AnonymousClass1xJ(Executors.newScheduledThreadPool(2));

    public static AbstractC12361xL getScheduler() {
        return SCHEDULER;
    }
}
