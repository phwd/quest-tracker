package defpackage;

import android.content.Context;
import org.chromium.components.background_task_scheduler.TaskInfo;

/* renamed from: kf  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3410kf implements AbstractC1538Ze1 {

    /* renamed from: a  reason: collision with root package name */
    public Context f10292a;
    public TaskInfo b;
    public boolean c;
    public final /* synthetic */ C3581lf d;

    public C3410kf(C3581lf lfVar, Context context, TaskInfo taskInfo) {
        this.d = lfVar;
        this.f10292a = context;
        this.b = taskInfo;
    }

    @Override // defpackage.AbstractC1538Ze1
    public void a(C1477Ye1 ye1) {
        this.c = this.d.f10361a.b(this.f10292a, this.b);
    }

    @Override // defpackage.AbstractC1538Ze1
    public void b(C1355We1 we1) {
        this.c = this.d.f10361a.b(this.f10292a, this.b);
    }

    @Override // defpackage.AbstractC1538Ze1
    public void c(C1233Ue1 ue1) {
        this.c = this.d.b.b(this.f10292a, this.b);
    }
}
