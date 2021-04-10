package org.chromium.components.background_task_scheduler.internal;

import android.os.Bundle;
import org.chromium.components.background_task_scheduler.TaskInfo;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TaskInfoBridge {
    public static TaskInfo.TimingInfo createExactInfo(long j) {
        C1172Te1 te1 = new C1172Te1();
        te1.f8972a = j;
        return new C1233Ue1(te1, null);
    }

    public static TaskInfo.TimingInfo createOneOffInfo(long j, long j2, boolean z) {
        C1294Ve1 ve1 = new C1294Ve1();
        ve1.b = j2;
        ve1.d = z;
        if (j > 0) {
            ve1.f9097a = j;
            ve1.c = true;
        }
        return ve1.a();
    }

    public static TaskInfo.TimingInfo createPeriodicInfo(long j, long j2, boolean z) {
        C1416Xe1 xe1 = new C1416Xe1();
        xe1.f9225a = j;
        xe1.d = z;
        if (j2 > 0) {
            xe1.b = j2;
            xe1.c = true;
        }
        return xe1.a();
    }

    public static TaskInfo createTaskInfo(int i, TaskInfo.TimingInfo timingInfo, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("serialized_task_extras", str);
        C1111Se1 b = TaskInfo.b(i, timingInfo);
        b.c = 1;
        b.d = false;
        b.f = true;
        b.e = true;
        b.b = bundle;
        return b.a();
    }
}
