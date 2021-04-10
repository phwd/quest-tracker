package defpackage;

import java.io.PrintStream;
import java.io.PrintWriter;

/* renamed from: Kh1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0633Kh1 extends AbstractC0389Gh1 {
    @Override // defpackage.AbstractC0389Gh1
    public void a(Throwable th, Throwable th2) {
    }

    @Override // defpackage.AbstractC0389Gh1
    public void b(Throwable th) {
        th.printStackTrace();
    }

    @Override // defpackage.AbstractC0389Gh1
    public void c(Throwable th, PrintStream printStream) {
        th.printStackTrace(printStream);
    }

    @Override // defpackage.AbstractC0389Gh1
    public void d(Throwable th, PrintWriter printWriter) {
        th.printStackTrace(printWriter);
    }
}
