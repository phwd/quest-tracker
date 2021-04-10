package defpackage;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/* renamed from: WN  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class WN implements N9 {

    /* renamed from: a  reason: collision with root package name */
    public final Lz1 f9143a;
    public final Context b;
    public int c = 0;
    public boolean d = false;
    public int e = 0;
    public int f = -1;
    public boolean g = false;
    public boolean h = false;
    public Integer i;

    public WN(Context context) {
        this.f9143a = new Lz1(context);
        this.b = context;
    }

    @Override // defpackage.N9
    public void b(C5390wD0 wd0) {
        this.f9143a.e(wd0);
    }

    @Override // defpackage.N9
    public C3506lA1 d() {
        PendingIntent pendingIntent = null;
        PendingIntent broadcast = f() == 2 ? PendingIntent.getBroadcast(this.b, 0, new Intent(), 0) : null;
        if (f() == 2) {
            pendingIntent = PendingIntent.getBroadcast(this.b, 0, new Intent(), 0);
        }
        return AbstractC3412kf1.b(C2823hA1.a(this.b.getPackageName(), this.e, f(), this.c, this.f, pendingIntent, broadcast));
    }

    @Override // defpackage.N9
    public void e(C5390wD0 wd0) {
        this.f9143a.b(wd0);
    }

    public final int f() {
        if (!this.d) {
            return 1;
        }
        int i2 = this.c;
        return (i2 == 0 || i2 == 4 || i2 == 5 || i2 == 6) ? 2 : 3;
    }

    public final void g() {
        this.f9143a.d(new Iz1(this.c, 0, this.b.getPackageName()));
    }
}
