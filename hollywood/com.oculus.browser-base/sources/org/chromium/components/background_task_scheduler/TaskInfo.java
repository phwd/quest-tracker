package org.chromium.components.background_task_scheduler;

import android.os.Bundle;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TaskInfo {

    /* renamed from: a  reason: collision with root package name */
    public final int f10811a;
    public final Bundle b;
    public final int c;
    public final boolean d;
    public final boolean e;
    public final boolean f;
    public final TimingInfo g;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public interface TimingInfo {
        void a(AbstractC1538Ze1 ze1);
    }

    public TaskInfo(C1111Se1 se1, AbstractC1050Re1 re1) {
        this.f10811a = se1.f8905a;
        Bundle bundle = se1.b;
        this.b = bundle == null ? new Bundle() : bundle;
        this.c = se1.c;
        this.d = se1.d;
        this.e = se1.e;
        this.f = se1.f;
        this.g = se1.g;
    }

    @Deprecated
    public static C1111Se1 a(int i, long j, long j2) {
        C1294Ve1 ve1 = new C1294Ve1();
        ve1.f9097a = j;
        ve1.c = true;
        ve1.b = j2;
        C1355We1 a2 = ve1.a();
        C1111Se1 se1 = new C1111Se1(i);
        se1.g = a2;
        return se1;
    }

    public static C1111Se1 b(int i, TimingInfo timingInfo) {
        C1111Se1 se1 = new C1111Se1(i);
        se1.g = timingInfo;
        return se1;
    }

    public String toString() {
        StringBuilder j = AbstractC2531fV.j("{", "taskId: ");
        j.append(this.f10811a);
        j.append(", extras: ");
        j.append(this.b);
        j.append(", requiredNetworkType: ");
        j.append(this.c);
        j.append(", requiresCharging: ");
        j.append(this.d);
        j.append(", isPersisted: ");
        j.append(this.e);
        j.append(", updateCurrent: ");
        j.append(this.f);
        j.append(", timingInfo: ");
        j.append(this.g);
        j.append("}");
        return j.toString();
    }
}
