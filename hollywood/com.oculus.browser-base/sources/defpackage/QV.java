package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import java.util.Collections;
import java.util.Set;

/* renamed from: QV  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class QV {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8765a;
    public final C2470f7 b;
    public final AbstractC1787b7 c;
    public final C4861t7 d;
    public final Looper e;
    public final int f;
    public final C3495l7 g;
    public final C2021cW h;

    public QV(Context context, C2470f7 f7Var, AbstractC1787b7 b7Var, PV pv) {
        SE0.i(context, "Null context is not permitted.");
        SE0.i(f7Var, "Api must not be null.");
        SE0.i(pv, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        Context applicationContext = context.getApplicationContext();
        this.f8765a = applicationContext;
        this.b = f7Var;
        this.c = b7Var;
        this.e = pv.c;
        this.d = new C4861t7(f7Var, b7Var);
        C2021cW a2 = C2021cW.a(applicationContext);
        this.h = a2;
        this.f = a2.N.getAndIncrement();
        this.g = pv.b;
        Handler handler = a2.S;
        handler.sendMessage(handler.obtainMessage(7, this));
    }

    public C3629lv a() {
        C3629lv lvVar = new C3629lv();
        lvVar.f10385a = null;
        Set emptySet = Collections.emptySet();
        if (lvVar.b == null) {
            lvVar.b = new C5271va(0);
        }
        lvVar.b.addAll(emptySet);
        lvVar.d = this.f8765a.getClass().getName();
        lvVar.c = this.f8765a.getPackageName();
        return lvVar;
    }

    public AbstractC4439qg b(AbstractC4439qg qgVar) {
        qgVar.i();
        C2021cW cWVar = this.h;
        KB1 kb1 = new KB1(1, qgVar);
        Handler handler = cWVar.S;
        handler.sendMessage(handler.obtainMessage(4, new C4875tB1(kb1, cWVar.O.get(), this)));
        return qgVar;
    }

    public AbstractC2129d7 c(Looper looper, ZV zv) {
        C3800mv a2 = a().a();
        C2470f7 f7Var = this.b;
        SE0.k(f7Var.f9899a != null, "This API was constructed with a SimpleClientBuilder. Use getSimpleClientBuilder");
        return f7Var.f9899a.b(this.f8765a, looper, a2, this.c, zv, zv);
    }

    public BinderC5725yB1 d(Context context, Handler handler) {
        return new BinderC5725yB1(context, handler, a().a(), BinderC5725yB1.f11670a);
    }

    public final OI1 e(int i, AbstractC0502Ie1 ie1) {
        C0563Je1 je1 = new C0563Je1();
        C2021cW cWVar = this.h;
        NB1 nb1 = new NB1(i, ie1, je1, this.g);
        Handler handler = cWVar.S;
        handler.sendMessage(handler.obtainMessage(4, new C4875tB1(nb1, cWVar.O.get(), this)));
        return je1.f8303a;
    }
}
