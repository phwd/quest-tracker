package defpackage;

import org.chromium.components.background_task_scheduler.TaskInfo;

/* renamed from: Ye1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1477Ye1 implements TaskInfo.TimingInfo {

    /* renamed from: a  reason: collision with root package name */
    public final long f9286a;
    public final long b;
    public final boolean c;
    public final boolean d;

    public C1477Ye1(C1416Xe1 xe1, AbstractC1050Re1 re1) {
        this.f9286a = xe1.f9225a;
        this.b = xe1.b;
        this.c = xe1.c;
        this.d = xe1.d;
    }

    public static boolean b(long j, long j2, long j3, long j4) {
        return (j4 - j) % j2 < j2 - j3 && j3 < j2;
    }

    @Override // org.chromium.components.background_task_scheduler.TaskInfo.TimingInfo
    public void a(AbstractC1538Ze1 ze1) {
        ze1.a(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        sb.append("intervalMs: ");
        sb.append(this.f9286a);
        sb.append(", ");
        if (this.c) {
            sb.append(", flexMs: ");
            sb.append(this.b);
            sb.append(", ");
        }
        sb.append("expiresAfterWindowEndTime (+flex): ");
        sb.append(this.d);
        sb.append("}");
        return sb.toString();
    }
}
