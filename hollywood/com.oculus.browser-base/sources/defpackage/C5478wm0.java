package defpackage;

import android.content.Intent;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;

/* renamed from: wm0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5478wm0 {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC2418eq f11568a;
    public boolean b;
    public boolean c;
    public List d;
    public List e;
    public Boolean f;
    public boolean g;
    public boolean h;
    public boolean i;

    public C5478wm0(AbstractC2418eq eqVar) {
        this.f11568a = eqVar;
    }

    public final void a() {
        Boolean bool;
        Object obj = ThreadUtils.f10596a;
        if (this.g && (bool = this.f) != null && bool.booleanValue()) {
            this.h = true;
            if (!((AbstractActivityC0731Ma) this.f11568a).v()) {
                AbstractActivityC0731Ma ma = (AbstractActivityC0731Ma) this.f11568a;
                ma.Y.k = 1;
                C1321Vq.b().c(true, ma, null);
            }
        }
    }

    public void b(boolean z) {
        Object obj = ThreadUtils.f10596a;
        if (AbstractC1575Zv.e().g("disable-native-initialization")) {
            AbstractC1220Ua0.d("NIController", "Exit early and start Chrome without loading native library!", new Object[0]);
            return;
        }
        boolean a2 = SQ.a(false, ((AbstractActivityC0731Ma) this.f11568a).getIntent());
        this.f = Boolean.FALSE;
        C5138um0 um0 = new C5138um0(this);
        if (a2) {
            um0.c++;
            OG0.a().d();
            C5157us1 us1 = C2760gq.a().c;
            String str = us1.b;
            if (str != null) {
                RunnableC0549Ja ja = new RunnableC0549Ja(um0, str);
                um0.b = ja;
                PostTask.b(C3070if1.e, ja, 0);
            } else {
                us1.b = "";
                RunnableC0549Ja ja2 = new RunnableC0549Ja(um0, "");
                um0.b = ja2;
                PostTask.b(C3070if1.e, ja2, 0);
            }
        }
        um0.f11434a = z;
        um0.c++;
        new Thread(new RunnableC0366Ga(um0)).start();
    }

    public final void c() {
        this.f11568a.d();
        List<Intent> list = this.d;
        if (list != null) {
            for (Intent intent : list) {
                this.f11568a.R(intent);
            }
            this.d = null;
        }
        if (this.e != null) {
            for (int i2 = 0; i2 < this.e.size(); i2++) {
                C5308vm0 vm0 = (C5308vm0) this.e.get(i2);
                this.f11568a.a(vm0.f11497a, vm0.b, vm0.c);
            }
            this.e = null;
        }
    }
}
