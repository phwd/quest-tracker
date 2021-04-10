package org.chromium.components.background_task_scheduler.internal;

import org.chromium.base.ContextUtils;
import org.chromium.components.background_task_scheduler.TaskInfo;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NativeTaskScheduler {
    public static void cancel(int i) {
        AbstractC2898hf.b().a(ContextUtils.getApplicationContext(), i);
    }

    public static boolean schedule(TaskInfo taskInfo) {
        return AbstractC2898hf.b().b(ContextUtils.getApplicationContext(), taskInfo);
    }
}
