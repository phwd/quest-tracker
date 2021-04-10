package com.facebook.papaya.client.platform;

import X.AnonymousClass08;
import X.HE;
import X.HF;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class PlatformThreadUtil {
    public static ScheduledExecutorService createExecutor(String str) {
        int hashCode = str.hashCode();
        if (hashCode != -1247348971) {
            if (hashCode == 2038093062 && str.equals("random_thread")) {
                return Executors.newScheduledThreadPool(5, new HF());
            }
        } else if (str.equals("scheduling_thread")) {
            return Executors.newSingleThreadScheduledExecutor(new HE());
        }
        throw new IllegalArgumentException(AnonymousClass08.A04("No executor for type ", str));
    }
}
