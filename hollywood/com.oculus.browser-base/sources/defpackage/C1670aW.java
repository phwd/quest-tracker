package defpackage;

import com.google.android.gms.common.ConnectionResult;
import java.util.Set;

/* renamed from: aW  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1670aW implements AbstractC5895zB1, AbstractC3071ig {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC2129d7 f9434a;
    public final C4861t7 b;
    public AbstractC4757sY c = null;
    public Set d = null;
    public boolean e = false;
    public final /* synthetic */ C2021cW f;

    public C1670aW(C2021cW cWVar, AbstractC2129d7 d7Var, C4861t7 t7Var) {
        this.f = cWVar;
        this.f9434a = d7Var;
        this.b = t7Var;
    }

    @Override // defpackage.AbstractC3071ig
    public final void a(ConnectionResult connectionResult) {
        this.f.S.post(new RunnableC4022oB1(this, connectionResult));
    }

    public final void b(ConnectionResult connectionResult) {
        ZV zv = (ZV) this.f.P.get(this.b);
        SE0.c(zv.m.S);
        zv.b.disconnect();
        zv.e0(connectionResult);
    }
}
