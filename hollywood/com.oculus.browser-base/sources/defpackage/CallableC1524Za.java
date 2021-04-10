package defpackage;

import android.os.Binder;
import java.util.Objects;
import java.util.concurrent.Callable;
import org.chromium.base.ThreadUtils;

/* renamed from: Za  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CallableC1524Za implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractC2032cb f9351a;

    public CallableC1524Za(AbstractC2032cb cbVar) {
        this.f9351a = cbVar;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        this.f9351a.h.set(true);
        Object obj = null;
        try {
            obj = this.f9351a.c();
            Binder.flushPendingCommands();
            AbstractC2032cb cbVar = this.f9351a;
            Objects.requireNonNull(cbVar);
            if (cbVar instanceof AbstractC0500Ie) {
                cbVar.f = 2;
            } else {
                ThreadUtils.d(new RunnableC1402Xa(cbVar, obj));
            }
            return obj;
        } catch (Throwable th) {
            AbstractC2032cb cbVar2 = this.f9351a;
            Objects.requireNonNull(cbVar2);
            if (cbVar2 instanceof AbstractC0500Ie) {
                cbVar2.f = 2;
            } else {
                ThreadUtils.d(new RunnableC1402Xa(cbVar2, obj));
            }
            throw th;
        }
    }
}
