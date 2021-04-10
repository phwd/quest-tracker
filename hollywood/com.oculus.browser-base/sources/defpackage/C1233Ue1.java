package defpackage;

import org.chromium.components.background_task_scheduler.TaskInfo;

/* renamed from: Ue1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1233Ue1 implements TaskInfo.TimingInfo {

    /* renamed from: a  reason: collision with root package name */
    public final long f9039a;

    public C1233Ue1(C1172Te1 te1, AbstractC1050Re1 re1) {
        this.f9039a = te1.f8972a;
    }

    @Override // org.chromium.components.background_task_scheduler.TaskInfo.TimingInfo
    public void a(AbstractC1538Ze1 ze1) {
        ze1.c(this);
    }

    public String toString() {
        return "{" + "triggerAtMs: " + this.f9039a + "}";
    }
}
