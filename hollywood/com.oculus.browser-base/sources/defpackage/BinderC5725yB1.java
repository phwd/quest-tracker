package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.signin.internal.zak;
import java.util.Set;

/* renamed from: yB1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class BinderC5725yB1 extends JB1 implements WV, XV {

    /* renamed from: a  reason: collision with root package name */
    public static Y6 f11670a = ZA1.c;
    public final Context b;
    public final Handler c;
    public final Y6 d;
    public Set e;
    public C3800mv f;
    public AbstractC5045uB1 g;
    public AbstractC5895zB1 h;

    public BinderC5725yB1(Context context, Handler handler, C3800mv mvVar, Y6 y6) {
        this.b = context;
        this.c = handler;
        SE0.i(mvVar, "ClientSettings must not be null");
        this.f = mvVar;
        this.e = mvVar.b;
        this.d = y6;
    }

    @Override // defpackage.AbstractC5215vB1
    public final void L(zak zak) {
        this.c.post(new AB1(this, zak));
    }

    @Override // defpackage.AbstractC0482Hx
    public final void c(int i) {
        this.g.disconnect();
    }

    @Override // defpackage.AbstractC0777Ms0
    public final void e0(ConnectionResult connectionResult) {
        ((C1670aW) this.h).b(connectionResult);
    }

    @Override // defpackage.AbstractC0482Hx
    public final void f(Bundle bundle) {
        ((C4752sV0) this.g).z(this);
    }
}
