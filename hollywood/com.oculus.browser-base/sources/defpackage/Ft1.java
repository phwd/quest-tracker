package defpackage;

import android.content.Context;
import android.view.ViewStub;
import org.chromium.base.Callback;

/* renamed from: Ft1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Ft1 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8047a;
    public final UH0 b;
    public final Jt1 c;
    public final MZ d;
    public final Callback e;
    public final Callback f;

    public Ft1(ViewStub viewStub, MZ mz, Callback callback, Callback callback2) {
        this.f8047a = viewStub.getContext();
        this.d = mz;
        this.e = callback;
        this.f = callback2;
        UH0 uh0 = new UH0(Gt1.h);
        this.b = uh0;
        Jt1 jt1 = new Jt1(viewStub);
        this.c = jt1;
        ZH0.a(uh0, jt1, new C5840yt1());
    }
}
