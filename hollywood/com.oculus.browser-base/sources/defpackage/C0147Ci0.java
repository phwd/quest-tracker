package defpackage;

import java.util.Objects;

/* renamed from: Ci0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0147Ci0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C0330Fi0 f7831a;

    public C0147Ci0(C0330Fi0 fi0) {
        this.f7831a = fi0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C0330Fi0 fi0 = this.f7831a;
        C5037u9 u9Var = (C5037u9) obj;
        Objects.requireNonNull(fi0);
        C5887z9 z9Var = u9Var.e;
        fi0.e = z9Var;
        z9Var.f11727J.add(fi0);
        C5887z9 z9Var2 = (C5887z9) fi0.e;
        Objects.requireNonNull(z9Var2);
        C4697s9 s9Var = new C4697s9(z9Var2);
        fi0.c = s9Var;
        s9Var.G = new RunnableC0269Ei0();
        fi0.h.m(AbstractC0513Ii0.b, s9Var);
        fi0.d.m(fi0.c);
        fi0.b = u9Var.d;
    }
}
