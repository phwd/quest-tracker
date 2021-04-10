package defpackage;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Status;
import java.util.Objects;

/* renamed from: NB1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class NB1 extends XA1 {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC0502Ie1 f8532a;
    public final C0563Je1 b;
    public final C3495l7 c;

    public NB1(int i, AbstractC0502Ie1 ie1, C0563Je1 je1, C3495l7 l7Var) {
        super(i);
        this.b = je1;
        this.f8532a = ie1;
        this.c = l7Var;
    }

    @Override // defpackage.AbstractC5385wB1
    public final void a(Status status) {
        C0563Je1 je1 = this.b;
        Objects.requireNonNull(this.c);
        je1.c(AbstractC3666m7.a(status));
    }

    @Override // defpackage.AbstractC5385wB1
    public final void b(RuntimeException runtimeException) {
        this.b.c(runtimeException);
    }

    @Override // defpackage.AbstractC5385wB1
    public final void c(C2316eC1 ec1, boolean z) {
        C0563Je1 je1 = this.b;
        ec1.b.put(je1, Boolean.valueOf(z));
        OI1 oi1 = je1.f8303a;
        EA1 ea1 = new EA1(ec1, je1);
        Objects.requireNonNull(oi1);
        oi1.b.a(new C1819bH1(AbstractC0928Pe1.f8702a, ea1));
        oi1.e();
    }

    @Override // defpackage.AbstractC5385wB1
    public final void e(ZV zv) {
        try {
            this.f8532a.b(zv.b, this.b);
        } catch (DeadObjectException e) {
            throw e;
        } catch (RemoteException e2) {
            Status d = AbstractC5385wB1.d(e2);
            C0563Je1 je1 = this.b;
            Objects.requireNonNull(this.c);
            je1.c(AbstractC3666m7.a(d));
        } catch (RuntimeException e3) {
            this.b.c(e3);
        }
    }

    @Override // defpackage.XA1
    public final Feature[] f(ZV zv) {
        return this.f8532a.f8238a;
    }

    @Override // defpackage.XA1
    public final boolean g(ZV zv) {
        return this.f8532a.b;
    }
}
