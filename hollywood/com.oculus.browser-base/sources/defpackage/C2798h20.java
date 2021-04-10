package defpackage;

import java.util.Objects;
import org.chromium.base.task.PostTask;

/* renamed from: h20  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2798h20 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C4677s20 f10044a;
    public final C4506r20 b;
    public final int c;
    public final C5922zL0 d;
    public final int e;

    public C2798h20(C4677s20 s20, C4506r20 r20, int i, C5922zL0 zl0, int i2) {
        this.f10044a = s20;
        this.b = r20;
        this.c = i;
        this.d = zl0;
        this.e = i2;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C4677s20 s20 = this.f10044a;
        C4506r20 r20 = this.b;
        int i = this.c;
        C5922zL0 zl0 = this.d;
        int i2 = this.e;
        Objects.requireNonNull(s20);
        if (((Boolean) obj).booleanValue()) {
            PostTask.b(C3070if1.b, new RunnableC2969i20(s20, r20, i, zl0), 0);
        } else {
            r20.a(null, i, i2);
        }
    }
}
