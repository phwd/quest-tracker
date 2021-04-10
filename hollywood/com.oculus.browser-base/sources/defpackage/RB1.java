package defpackage;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Status;

/* renamed from: RB1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class RB1 extends XA1 {

    /* renamed from: a  reason: collision with root package name */
    public final C0563Je1 f8815a;
    public final C5378w90 b;

    public RB1(C5378w90 w90, C0563Je1 je1) {
        super(4);
        this.f8815a = je1;
        this.b = w90;
    }

    @Override // defpackage.AbstractC5385wB1
    public void a(Status status) {
        this.f8815a.c(new C3324k7(status));
    }

    @Override // defpackage.AbstractC5385wB1
    public void b(RuntimeException runtimeException) {
        this.f8815a.c(runtimeException);
    }

    @Override // defpackage.AbstractC5385wB1
    public final /* bridge */ /* synthetic */ void c(C2316eC1 ec1, boolean z) {
    }

    @Override // defpackage.AbstractC5385wB1
    public final void e(ZV zv) {
        try {
            h(zv);
        } catch (DeadObjectException e) {
            this.f8815a.c(new C3324k7(AbstractC5385wB1.d(e)));
            throw e;
        } catch (RemoteException e2) {
            this.f8815a.c(new C3324k7(AbstractC5385wB1.d(e2)));
        } catch (RuntimeException e3) {
            this.f8815a.c(e3);
        }
    }

    @Override // defpackage.XA1
    public final Feature[] f(ZV zv) {
        C5859z.a(zv.g.get(this.b));
        return null;
    }

    @Override // defpackage.XA1
    public final boolean g(ZV zv) {
        C5859z.a(zv.g.get(this.b));
        return false;
    }

    public final void h(ZV zv) {
        C5859z.a(zv.g.remove(this.b));
        C0563Je1 je1 = this.f8815a;
        Boolean bool = Boolean.FALSE;
        OI1 oi1 = je1.f8303a;
        synchronized (oi1.f8618a) {
            if (!oi1.c) {
                oi1.c = true;
                oi1.d = bool;
                oi1.b.b(oi1);
            }
        }
    }
}
