package defpackage;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

/* renamed from: Jh1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0572Jh1 extends AbstractC0389Gh1 {

    /* renamed from: a  reason: collision with root package name */
    public final C0511Ih1 f8308a = new C0511Ih1();

    @Override // defpackage.AbstractC0389Gh1
    public void a(Throwable th, Throwable th2) {
        if (th2 != th) {
            Objects.requireNonNull(th2, "The suppressed exception cannot be null.");
            this.f8308a.a(th, true).add(th2);
            return;
        }
        throw new IllegalArgumentException("Self suppression is not allowed.", th2);
    }

    @Override // defpackage.AbstractC0389Gh1
    public void b(Throwable th) {
        th.printStackTrace();
        List<Throwable> a2 = this.f8308a.a(th, false);
        if (a2 != null) {
            synchronized (a2) {
                for (Throwable th2 : a2) {
                    System.err.print("Suppressed: ");
                    th2.printStackTrace();
                }
            }
        }
    }

    @Override // defpackage.AbstractC0389Gh1
    public void c(Throwable th, PrintStream printStream) {
        th.printStackTrace(printStream);
        List<Throwable> a2 = this.f8308a.a(th, false);
        if (a2 != null) {
            synchronized (a2) {
                for (Throwable th2 : a2) {
                    printStream.print("Suppressed: ");
                    th2.printStackTrace(printStream);
                }
            }
        }
    }

    @Override // defpackage.AbstractC0389Gh1
    public void d(Throwable th, PrintWriter printWriter) {
        th.printStackTrace(printWriter);
        List<Throwable> a2 = this.f8308a.a(th, false);
        if (a2 != null) {
            synchronized (a2) {
                for (Throwable th2 : a2) {
                    printWriter.print("Suppressed: ");
                    th2.printStackTrace(printWriter);
                }
            }
        }
    }
}
