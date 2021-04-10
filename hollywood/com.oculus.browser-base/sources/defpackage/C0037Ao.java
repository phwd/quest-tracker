package defpackage;

import java.util.Objects;
import org.chromium.content.browser.ChildProcessLauncherHelperImpl;

/* renamed from: Ao  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0037Ao implements AbstractC5483wo {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ boolean f7697a;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ C0220Do c;

    public C0037Ao(C0220Do r1, boolean z, boolean z2) {
        this.c = r1;
        this.f7697a = z;
        this.b = z2;
    }

    @Override // defpackage.AbstractC5483wo
    public void a(C5653xo xoVar) {
        int i;
        C0220Do r5 = this.c;
        C5653xo xoVar2 = r5.g;
        if (xoVar2 == null) {
            i = 0;
        } else {
            i = xoVar2.r;
        }
        if (i != 0) {
            C0768Mo mo = (C0768Mo) r5.b;
            Objects.requireNonNull(mo);
            int i2 = xoVar2.r;
            if (i2 != 0 && ((ChildProcessLauncherHelperImpl) ChildProcessLauncherHelperImpl.f.remove(Integer.valueOf(i2))) != null) {
                ComponentCallbacks2C5632xh xhVar = mo.f8502a.l;
                if (xhVar != null && xhVar.F.remove(xoVar2)) {
                    if (xoVar2 == xhVar.f11625J) {
                        xhVar.f11625J = null;
                    } else {
                        xoVar2.l();
                    }
                }
                ChildProcessLauncherHelperImpl childProcessLauncherHelperImpl = mo.f8502a;
                C1317Vo vo = childProcessLauncherHelperImpl.k;
                if (vo != null) {
                    int size = (vo.I.size() - 1) - vo.b(xoVar2);
                    synchronized (childProcessLauncherHelperImpl.v) {
                        childProcessLauncherHelperImpl.w = size;
                    }
                    C1317Vo vo2 = mo.f8502a.k;
                    vo2.I.remove(vo2.b(xoVar2));
                    ComponentCallbacks2C5632xh xhVar2 = mo.f8502a.l;
                    if (xhVar2 != null) {
                        xhVar2.c();
                    }
                }
            }
        }
    }

    @Override // defpackage.AbstractC5483wo
    public void b(C5653xo xoVar) {
        AbstractC1220Ua0.a("ChildProcLauncher", "ChildProcessConnection.start failed, trying again", new Object[0]);
        this.c.f7911a.post(new RunnableC5993zo(this));
    }

    @Override // defpackage.AbstractC5483wo
    public void c() {
    }
}
