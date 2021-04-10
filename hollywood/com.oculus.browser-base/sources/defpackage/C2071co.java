package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Arrays;

/* renamed from: co  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2071co extends AbstractC2412eo {
    public final C5653xo[] k;
    public final ArrayList l;

    public C2071co(Handler handler, Runnable runnable, String str, String str2, boolean z, boolean z2, boolean z3, int i, C1559Zn zn) {
        super(handler, runnable, str, str2, null, z, z2, z3, null);
        this.k = new C5653xo[i];
        this.l = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            this.l.add(Integer.valueOf(i2));
        }
    }

    @Override // defpackage.AbstractC2412eo
    public C5653xo c(Context context, Bundle bundle, AbstractC5483wo woVar) {
        if (this.l.isEmpty()) {
            return null;
        }
        int intValue = ((Integer) this.l.remove(0)).intValue();
        String str = this.d;
        C5653xo a2 = this.j.a(context, new ComponentName(str, this.e + intValue), null, this.g, this.h, bundle, null);
        this.k[intValue] = a2;
        a2.m(this.i, woVar);
        return a2;
    }

    @Override // defpackage.AbstractC2412eo
    public void d(C5653xo xoVar) {
        int indexOf = Arrays.asList(this.k).indexOf(xoVar);
        if (indexOf == -1) {
            AbstractC1220Ua0.a("ChildConnAllocator", "Unable to find connection to free.", new Object[0]);
            return;
        }
        this.k[indexOf] = null;
        this.l.add(Integer.valueOf(indexOf));
    }

    @Override // defpackage.AbstractC2412eo
    public int e() {
        return this.k.length;
    }
}
