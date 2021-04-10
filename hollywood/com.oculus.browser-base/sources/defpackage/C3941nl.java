package defpackage;

import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;

/* renamed from: nl  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3941nl extends MZ {
    public C3770ml b;

    public C3941nl(PZ pz, C3770ml mlVar) {
        super(pz);
        this.b = mlVar;
    }

    @Override // defpackage.MZ
    public void a() {
    }

    @Override // defpackage.MZ
    public void b() {
    }

    @Override // defpackage.MZ
    public void c(LZ lz, Callback callback) {
        PostTask.b(C3070if1.c, new RunnableC2745gl(this, lz, callback, System.currentTimeMillis()), 0);
    }

    @Override // defpackage.MZ
    public void d(LZ lz, Callback callback) {
        PostTask.b(C3070if1.c, new RunnableC3086il(this, lz, callback, System.currentTimeMillis()), 0);
    }

    @Override // defpackage.MZ
    public int e() {
        return 1;
    }
}
