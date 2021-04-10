package defpackage;

import org.chromium.components.background_task_scheduler.TaskInfo;

/* renamed from: We1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1355We1 implements TaskInfo.TimingInfo {

    /* renamed from: a  reason: collision with root package name */
    public final long f9162a;
    public final long b;
    public final boolean c;
    public final boolean d;

    public C1355We1(C1294Ve1 ve1, AbstractC1050Re1 re1) {
        this.f9162a = ve1.f9097a;
        this.b = ve1.b;
        this.c = ve1.c;
        this.d = ve1.d;
    }

    @Override // org.chromium.components.background_task_scheduler.TaskInfo.TimingInfo
    public void a(AbstractC1538Ze1 ze1) {
        ze1.b(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        if (this.c) {
            sb.append("windowStartTimeMs: ");
            sb.append(this.f9162a);
            sb.append(", ");
        }
        sb.append("windowEndTimeMs: ");
        sb.append(this.b);
        sb.append(", ");
        sb.append("expiresAfterWindowEndTime (+flex): ");
        sb.append(this.d);
        sb.append("}");
        return sb.toString();
    }
}
