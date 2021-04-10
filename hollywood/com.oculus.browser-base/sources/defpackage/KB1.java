package defpackage;

import com.google.android.gms.common.api.Status;
import java.util.Objects;

/* renamed from: KB1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class KB1 extends AbstractC5385wB1 {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC4439qg f8349a;

    public KB1(int i, AbstractC4439qg qgVar) {
        super(i);
        this.f8349a = qgVar;
    }

    @Override // defpackage.AbstractC5385wB1
    public final void a(Status status) {
        this.f8349a.l(status);
    }

    @Override // defpackage.AbstractC5385wB1
    public final void b(RuntimeException runtimeException) {
        String simpleName = runtimeException.getClass().getSimpleName();
        String localizedMessage = runtimeException.getLocalizedMessage();
        StringBuilder sb = new StringBuilder(String.valueOf(localizedMessage).length() + simpleName.length() + 2);
        sb.append(simpleName);
        sb.append(": ");
        sb.append(localizedMessage);
        this.f8349a.l(new Status(10, sb.toString()));
    }

    @Override // defpackage.AbstractC5385wB1
    public final void c(C2316eC1 ec1, boolean z) {
        AbstractC4439qg qgVar = this.f8349a;
        ec1.f9839a.put(qgVar, Boolean.valueOf(z));
        FA1 fa1 = new FA1(ec1, qgVar);
        Objects.requireNonNull(qgVar);
        SE0.b(true, "Callback cannot be null.");
        synchronized (qgVar.b) {
            if (qgVar.e()) {
                ec1.f9839a.remove(qgVar);
            } else {
                qgVar.f.add(fa1);
            }
        }
    }

    @Override // defpackage.AbstractC5385wB1
    public final void e(ZV zv) {
        try {
            this.f8349a.k(zv.b);
        } catch (RuntimeException e) {
            b(e);
        }
    }
}
