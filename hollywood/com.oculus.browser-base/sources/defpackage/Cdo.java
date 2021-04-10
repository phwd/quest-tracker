package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

/* renamed from: do  reason: invalid class name and default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Cdo extends AbstractC2412eo {
    public final int k;
    public final C5271va l = new C5271va(0);
    public int m;

    public Cdo(Handler handler, Runnable runnable, String str, String str2, String str3, boolean z, boolean z2, boolean z3, int i, C1559Zn zn) {
        super(handler, runnable, str, str2, str3, z, z2, z3, null);
        this.k = i;
    }

    @Override // defpackage.AbstractC2412eo
    public C5653xo c(Context context, Bundle bundle, AbstractC5483wo woVar) {
        C5653xo f = f(context, bundle);
        if (f == null) {
            return null;
        }
        this.l.add(f);
        f.m(this.i, woVar);
        return f;
    }

    @Override // defpackage.AbstractC2412eo
    public void d(C5653xo xoVar) {
        this.l.remove(xoVar);
    }

    @Override // defpackage.AbstractC2412eo
    public int e() {
        return -1;
    }

    public final C5653xo f(Context context, Bundle bundle) {
        ComponentName componentName = null;
        if (this.l.N >= this.k) {
            return null;
        }
        ComponentName componentName2 = new ComponentName(this.d, this.e);
        if (this.f != null) {
            componentName = new ComponentName(this.d, this.f);
        }
        String num = Integer.toString(this.m);
        this.m++;
        return this.j.a(context, componentName2, componentName, this.g, this.h, bundle, num);
    }
}
