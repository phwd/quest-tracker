package defpackage;

import android.content.Context;
import org.chromium.base.Callback;

/* renamed from: ps0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4305ps0 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f11094a;
    public final Callback b;
    public final C4957ti1 c = new C4957ti1("QueryTiles.Omnibox");
    public C1059Rh1 d;
    public MZ e;
    public Integer f;

    public C4305ps0(Context context, Callback callback) {
        this.f11094a = context;
        this.b = callback;
    }

    public final C1059Rh1 a() {
        if (this.d == null) {
            C0937Ph1 ph1 = new C0937Ph1();
            ph1.f8707a = "QueryTiles.Omnibox";
            this.d = new C1059Rh1(this.f11094a, new C0998Qh1(ph1, null), new C3621ls0(this), new C3792ms0(this));
        }
        return this.d;
    }
}
